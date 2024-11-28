package com.example.pokerserver;


import com.example.pokerserver.card.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class HandTest {

    public static final Long PLAYER_ID = 0L;

    @Autowired
    private CombinationUtils combinationUtils;

    @Test
    void isHandAreHighCard() {
        assertEquals(combinationUtils.getCombination(getHandWithHighCard()).getOrder(), Combination.HIGH_CARD.getOrder());
    }

    @Test
    void isHandArePair() {
        assertEquals(combinationUtils.getCombination(getHandWithPair()).getOrder(), Combination.PAIR.getOrder());
    }

    @Test
    void isHandAreTwoPair() {
        assertEquals(combinationUtils.getCombination(getHandWithTwoPair()).getOrder(), Combination.TWO_PAIR.getOrder());
    }

    @Test
    void isHandAreThreeOfKind() {
        assertEquals(combinationUtils.getCombination(getHandWithThreeOfKind()).getOrder(), Combination.THREE_OF_A_KIND.getOrder());
    }

    @Test
    void isHandAreStraight() {
        assertEquals(combinationUtils.getCombination(getHandWithStraight()).getOrder(), Combination.STRAIGHT.getOrder());
    }

    @Test
    void isHandAreAceStraight() {
        assertEquals(combinationUtils.getCombination(getHandWithAceStraight()).getOrder(), Combination.STRAIGHT.getOrder());
    }

    @Test
    void isHandAreFlush() {
        assertEquals(combinationUtils.getCombination(getHandWithFlush()).getOrder(), Combination.FLUSH.getOrder());
    }

    @Test
    void isHandAreFullHouse() {
        assertEquals(combinationUtils.getCombination(getHandWithFullHouse()).getOrder(), Combination.FULL_HOUSE.getOrder());
    }

    @Test
    void isHandAreFourOfKind() {
        assertEquals(combinationUtils.getCombination(getHandWithForOfKind()).getOrder(), Combination.FOUR_OF_A_KIND.getOrder());
    }

    @Test
    void isHandAreStraightFlush() {
        assertEquals(combinationUtils.getCombination(getHandWithStraightFlush()).getOrder(), Combination.STRAIGHT_FLUSH.getOrder());
    }

    @Test
    void isHandAreRoyalFlush() {
        assertEquals(combinationUtils.getCombination(getHandWithRoyalFlush()).getOrder(), Combination.ROYAL_FLUSH.getOrder());
    }
    private Hand getHandWithPair() {
        return new Hand(
                PLAYER_ID,
            List.of(
                    new Card(CardSuit.CLUBS, CardValue.FOUR),
                    new Card(CardSuit.HEARTS, CardValue.FOUR),
                    new Card(CardSuit.CLUBS, CardValue.ACE),
                    new Card(CardSuit.SPADES, CardValue.SEVEN),
                    new Card(CardSuit.SPADES, CardValue.TEN)
            )
        );
    }

    private Hand getHandWithHighCard() {
        return new Hand(
                PLAYER_ID,
                List.of(
                        new Card(CardSuit.CLUBS, CardValue.FOUR),
                        new Card(CardSuit.HEARTS, CardValue.TWO),
                        new Card(CardSuit.CLUBS, CardValue.ACE),
                        new Card(CardSuit.SPADES, CardValue.SEVEN),
                        new Card(CardSuit.SPADES, CardValue.TEN)
                )
        );
    }

    private Hand getHandWithTwoPair() {
        return new Hand(
                PLAYER_ID,
                List.of(
                        new Card(CardSuit.CLUBS, CardValue.FOUR),
                        new Card(CardSuit.HEARTS, CardValue.FOUR),
                        new Card(CardSuit.CLUBS, CardValue.SEVEN),
                        new Card(CardSuit.SPADES, CardValue.SEVEN),
                        new Card(CardSuit.SPADES, CardValue.TEN)
                )
        );
    }

    private Hand getHandWithThreeOfKind() {
        return new Hand(
                PLAYER_ID,
                List.of(
                        new Card(CardSuit.CLUBS, CardValue.FOUR),
                        new Card(CardSuit.HEARTS, CardValue.FOUR),
                        new Card(CardSuit.CLUBS, CardValue.SEVEN),
                        new Card(CardSuit.SPADES, CardValue.FOUR),
                        new Card(CardSuit.SPADES, CardValue.TEN)
                )
        );
    }

    private Hand getHandWithStraight() {
        return new Hand(
                PLAYER_ID,
                List.of(
                        new Card(CardSuit.CLUBS, CardValue.FOUR),
                        new Card(CardSuit.HEARTS, CardValue.FIVE),
                        new Card(CardSuit.CLUBS, CardValue.SEVEN),
                        new Card(CardSuit.SPADES, CardValue.SIX),
                        new Card(CardSuit.SPADES, CardValue.EIGHT)
                )
        );
    }

    private Hand getHandWithAceStraight() {
        return new Hand(
                PLAYER_ID,
                List.of(
                        new Card(CardSuit.CLUBS, CardValue.ACE),
                        new Card(CardSuit.HEARTS, CardValue.TWO),
                        new Card(CardSuit.CLUBS, CardValue.THREE),
                        new Card(CardSuit.SPADES, CardValue.FOUR),
                        new Card(CardSuit.SPADES, CardValue.FIVE)
                )
        );
    }

    private Hand getHandWithFlush() {
        return new Hand(
                PLAYER_ID,
                List.of(
                        new Card(CardSuit.CLUBS, CardValue.FOUR),
                        new Card(CardSuit.CLUBS, CardValue.FIVE),
                        new Card(CardSuit.CLUBS, CardValue.ACE),
                        new Card(CardSuit.CLUBS, CardValue.TEN),
                        new Card(CardSuit.CLUBS, CardValue.TWO)
                )
        );
    }

    private Hand getHandWithFullHouse() {
        return new Hand(
                PLAYER_ID,
                List.of(
                        new Card(CardSuit.CLUBS, CardValue.FOUR),
                        new Card(CardSuit.SPADES, CardValue.TEN),
                        new Card(CardSuit.HEARTS, CardValue.FOUR),
                        new Card(CardSuit.DIAMONDS, CardValue.FOUR),
                        new Card(CardSuit.SPADES, CardValue.TEN)
                )
        );
    }

    private Hand getHandWithForOfKind() {
        return new Hand(
                PLAYER_ID,
                List.of(
                        new Card(CardSuit.CLUBS, CardValue.FOUR),
                        new Card(CardSuit.SPADES, CardValue.FOUR),
                        new Card(CardSuit.HEARTS, CardValue.TEN),
                        new Card(CardSuit.DIAMONDS, CardValue.FOUR),
                        new Card(CardSuit.HEARTS, CardValue.FOUR)
                )
        );
    }

    private Hand getHandWithStraightFlush() {
        return new Hand(
                PLAYER_ID,
                List.of(
                        new Card(CardSuit.CLUBS, CardValue.SEVEN),
                        new Card(CardSuit.CLUBS, CardValue.FIVE),
                        new Card(CardSuit.CLUBS, CardValue.SIX),
                        new Card(CardSuit.CLUBS, CardValue.FOUR),
                        new Card(CardSuit.CLUBS, CardValue.EIGHT)
                )
        );
    }

    private Hand getHandWithRoyalFlush() {
        return new Hand(
                PLAYER_ID,
                List.of(
                        new Card(CardSuit.CLUBS, CardValue.ACE),
                        new Card(CardSuit.CLUBS, CardValue.KING),
                        new Card(CardSuit.CLUBS, CardValue.QUEEN),
                        new Card(CardSuit.CLUBS, CardValue.JACK),
                        new Card(CardSuit.CLUBS, CardValue.TEN)
                )
        );
    }
}
