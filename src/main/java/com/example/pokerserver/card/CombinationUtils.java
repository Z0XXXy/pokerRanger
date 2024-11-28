package com.example.pokerserver.card;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CombinationUtils {

    @Cacheable(value = "combinations", key="#hand")
    public Combination getCombination(Hand hand) {
        var cardsInHand = hand.getCardsInHand();
        var valueSortedCards = cardsInHand.stream().sorted(Comparator.comparingInt(c -> c.getValue().getOrder())).toList();
        Map<CardValue, Integer> cardValueToCountInHandMap = new HashMap<>();
        var suitCounter = 0;
        var difInOnceCounter = 0;
        boolean isAceStraight = checkAceStraight(valueSortedCards);
        if (isAceStraight) {
            valueSortedCards.stream().filter(cv -> cv.getValue().equals(CardValue.ACE)).findFirst().ifPresent(cv -> {
                cv.getValue().setOrder(1);
            });
        }

        cardValueToCountInHandMap.put(valueSortedCards.getFirst().getValue(), cardValueToCountInHandMap.getOrDefault(valueSortedCards.getFirst().getValue(), 0) + 1);
        var lastSuit = valueSortedCards.getFirst().getSuit();

        for (int i = 1; i < valueSortedCards.size(); i++) {
            var card = valueSortedCards.get(i);
            cardValueToCountInHandMap.put(card.getValue(), cardValueToCountInHandMap.getOrDefault(card.getValue(), 0) + 1);
            if (card.getSuit().equals(lastSuit)) {
                suitCounter++;
            }
            lastSuit = card.getSuit();
            if (card.getValue().getOrder() - valueSortedCards.get(i - 1).getValue().getOrder() == 1) {
                difInOnceCounter++;
            }

        }

        Map<Integer, CardValue> countToCardValueMap = new HashMap<>();
        for (CardValue cardValue : cardValueToCountInHandMap.keySet()) {
            var currValue = cardValueToCountInHandMap.get(cardValue);
            if (countToCardValueMap.containsKey(currValue)) {
                countToCardValueMap.put(
                        currValue,
                        countToCardValueMap.get(currValue).getOrder() > cardValue.getOrder() ? countToCardValueMap.get(currValue) : cardValue
                );
            } else {
                countToCardValueMap.put(currValue, cardValue);
            }
        }

        boolean isFlush = suitCounter == 4;
        boolean isStraight = difInOnceCounter == 4 || isAceStraight;

        if (isFlush) {
            if (isStraight) {
                return valueSortedCards.getFirst().getValue().equals(CardValue.TEN)
                        ? getCombinationWithSortedForComparingCards(Combination.ROYAL_FLUSH, cardValueToCountInHandMap)
                        : getCombinationWithSortedForComparingCards(Combination.STRAIGHT_FLUSH, cardValueToCountInHandMap);
            }
            return getCombinationWithSortedForComparingCards(Combination.FLUSH, cardValueToCountInHandMap);
        } else if (isStraight) {
            return getCombinationWithSortedForComparingCards(Combination.STRAIGHT, cardValueToCountInHandMap);
        } else if (cardValueToCountInHandMap.keySet().size() == 4) {
            return getCombinationWithSortedForComparingCards(Combination.PAIR, cardValueToCountInHandMap);
        } else if (cardValueToCountInHandMap.keySet().size() == 3 && countToCardValueMap.containsKey(2)) {
            return getCombinationWithSortedForComparingCards(Combination.TWO_PAIR, cardValueToCountInHandMap);
        } else if (cardValueToCountInHandMap.keySet().size() == 3 && countToCardValueMap.containsKey(3)) {
            return getCombinationWithSortedForComparingCards(Combination.THREE_OF_A_KIND, cardValueToCountInHandMap);
        } else if (cardValueToCountInHandMap.keySet().size() == 2 && countToCardValueMap.containsKey(3)) {
            return getCombinationWithSortedForComparingCards(Combination.FULL_HOUSE, cardValueToCountInHandMap);
        } else if (cardValueToCountInHandMap.keySet().size() == 2 && countToCardValueMap.containsKey(4)) {
            return Combination.FOUR_OF_A_KIND;
        }
        return getCombinationWithSortedForComparingCards(Combination.HIGH_CARD, cardValueToCountInHandMap);
    }

    private Combination getCombinationWithSortedForComparingCards(
            Combination combination,
            Map<CardValue, Integer> cardValueToCountInHandMap
    ) {
        combination.setSortedForComparingCards(composeOrderedValues(cardValueToCountInHandMap));
        return combination;
    }

    private boolean checkAceStraight(List<Card> cards) {
        return (
                cards.get(0).getValue().equals(CardValue.TWO)
                        && cards.get(1).getValue().equals(CardValue.THREE)
                        && cards.get(2).getValue().equals(CardValue.FOUR)
                        && cards.get(3).getValue().equals(CardValue.FIVE)
                        && cards.get(4).getValue().equals(CardValue.ACE)
        );
    }

    public List<CardValue> composeOrderedValues(Map<CardValue, Integer> cardValueToCountInHandMap) {
        List<CardValue> resultValues = new ArrayList<>();
        var sortedKeySet = cardValueToCountInHandMap.keySet().stream().sorted((k1,k2) -> {
            if (!cardValueToCountInHandMap.get(k2).equals(cardValueToCountInHandMap.get(k1))) {
                return cardValueToCountInHandMap.get(k2).compareTo(cardValueToCountInHandMap.get(k1));
            } else {
                return Integer.compare(k2.getOrder(), k1.getOrder());
            }
        }).toList();
        for(CardValue key : sortedKeySet) {
            for (int i = 0; i < cardValueToCountInHandMap.get(key); i++) {
                resultValues.add(key);
            }
        }
        return  resultValues;
    }
}
