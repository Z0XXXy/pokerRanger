package com.example.pokerserver.poker;

import com.example.pokerserver.card.CardValue;
import com.example.pokerserver.card.CombinationUtils;
import com.example.pokerserver.card.Hand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PokerRanger {

    private final CombinationUtils combinationUtils;

    /**
     * Возвращает список id победителей в раздаче
     * @param playerHandsList - список рук участников
     * @return лист с id победителей в раздаче
     */

    public List<Long> compareAllHands(List<Hand> playerHandsList) {
        var resultList = new ArrayList<Long>();
        var sortedByOrder = playerHandsList.stream().sorted(Comparator.comparingInt(h -> combinationUtils.getCombination(h).getOrder())).toList();
        var allHighHand = sortedByOrder.stream().filter(hand ->
                combinationUtils.getCombination(hand).getOrder() == combinationUtils.getCombination(sortedByOrder.getLast()).getOrder()
        ).toList();
        if (allHighHand.size() == 1) {
            return List.of(allHighHand.getFirst().getPlayerId());
        } else {
            var playerIdToSortedForComparingCardsValues = new HashMap<Long, List<CardValue>>();
            allHighHand.forEach(hand -> {
                playerIdToSortedForComparingCardsValues.put(hand.getPlayerId(), combinationUtils.getCombination(hand).getSortedForComparingCards());
            });

            var  keyList = new ArrayList<>(playerIdToSortedForComparingCardsValues.keySet());
            resultList.addAll(keyList);
            for (int i = 0; i < 5; i++) {
                int finalI = i;
                var maxValueForCurrI = keyList.stream().map(playerIdToSortedForComparingCardsValues::get).mapToInt(cardValues -> cardValues.get(finalI).getOrder()).max().getAsInt();
                var removedKeys = keyList.stream().filter(key -> playerIdToSortedForComparingCardsValues.get(key).get(finalI).getOrder() != maxValueForCurrI).toList();
                resultList.removeAll(removedKeys);
                keyList.removeAll(removedKeys);
            }
        }
        return resultList;
    }

    private Map<Long, Integer> getPlayerIdToCombinationOrderMap(List<Hand> playerHandsList) {
        return playerHandsList.stream().collect(Collectors.toMap(Hand::getPlayerId, h -> combinationUtils.getCombination(h).getOrder()));
    }
}
