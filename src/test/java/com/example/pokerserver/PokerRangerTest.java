package com.example.pokerserver;

import com.example.pokerserver.card.Card;
import com.example.pokerserver.card.CardSuit;
import com.example.pokerserver.card.CardValue;
import com.example.pokerserver.card.Hand;
import com.example.pokerserver.poker.PokerRanger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PokerRangerTest {
    public static final Long PLAYER_ONE_ID = 1L;
    public static final Long PLAYER_TWO_ID = 2L;
    public static final Long PLAYER_THREE_ID = 3L;
    public static final Long PLAYER_FOUR_ID = 4L;
    public static final Long PLAYER_FIVE_ID = 5L;
    public static final Long PLAYER_SIX_ID = 6L;
    public static final Long PLAYER_SEVEN_ID = 7L;
    public static final Long PLAYER_EIGHT_ID = 8L;
    public static final Long PLAYER_NINE_ID = 9L;

    @Autowired
    PokerRanger pokerRanger;

    @Test
    void checkHighCardWinner() {
        var hands = List.of(
                new Hand(
                        PLAYER_ONE_ID,
                        List.of(
                                new Card(CardSuit.CLUBS, CardValue.TEN),
                                new Card(CardSuit.DIAMONDS, CardValue.NINE),
                                new Card(CardSuit.CLUBS, CardValue.SEVEN),
                                new Card(CardSuit.CLUBS, CardValue.FIVE),
                                new Card(CardSuit.CLUBS, CardValue.TWO)
                        )
                ),
                new Hand(
                        PLAYER_TWO_ID,
                        List.of(
                                new Card(CardSuit.CLUBS, CardValue.TEN),
                                new Card(CardSuit.DIAMONDS, CardValue.NINE),
                                new Card(CardSuit.CLUBS, CardValue.SEVEN),
                                new Card(CardSuit.CLUBS, CardValue.FIVE),
                                new Card(CardSuit.CLUBS, CardValue.ACE)
                        )
                ),
                new Hand(
                        PLAYER_THREE_ID,
                        List.of(
                                new Card(CardSuit.CLUBS, CardValue.TEN),
                                new Card(CardSuit.DIAMONDS, CardValue.NINE),
                                new Card(CardSuit.CLUBS, CardValue.SEVEN),
                                new Card(CardSuit.CLUBS, CardValue.FIVE),
                                new Card(CardSuit.CLUBS, CardValue.KING)
                        )
                ),
                new Hand(
                        PLAYER_FOUR_ID,
                        List.of(
                                new Card(CardSuit.CLUBS, CardValue.TEN),
                                new Card(CardSuit.DIAMONDS, CardValue.NINE),
                                new Card(CardSuit.CLUBS, CardValue.SEVEN),
                                new Card(CardSuit.CLUBS, CardValue.FIVE),
                                new Card(CardSuit.CLUBS, CardValue.QUEEN)
                        )
                )
        );
        assertEquals(List.of(PLAYER_TWO_ID), pokerRanger.compareAllHands(hands));
    }

    @Test
    void checkPairWinner() {
        var hands = List.of(
                new Hand(
                    PLAYER_ONE_ID,
                    List.of(
                        new Card(CardSuit.CLUBS, CardValue.TEN),
                        new Card(CardSuit.DIAMONDS, CardValue.TEN),
                        new Card(CardSuit.CLUBS, CardValue.SEVEN),
                        new Card(CardSuit.CLUBS, CardValue.FIVE),
                        new Card(CardSuit.CLUBS, CardValue.TWO)
                    )
                ),
                new Hand(
                    PLAYER_TWO_ID,
                    List.of(
                            new Card(CardSuit.CLUBS, CardValue.SEVEN),
                            new Card(CardSuit.DIAMONDS, CardValue.TEN),
                            new Card(CardSuit.HEARTS, CardValue.SEVEN),
                            new Card(CardSuit.CLUBS, CardValue.FIVE),
                            new Card(CardSuit.CLUBS, CardValue.TWO)
                    )
                ),
                new Hand(
                        PLAYER_THREE_ID,
                        List.of(
                                new Card(CardSuit.CLUBS, CardValue.SIX),
                                new Card(CardSuit.DIAMONDS, CardValue.FIVE),
                                new Card(CardSuit.HEARTS, CardValue.SEVEN),
                                new Card(CardSuit.CLUBS, CardValue.FIVE),
                                new Card(CardSuit.CLUBS, CardValue.TWO)
                        )
                ),
                new Hand(
                        PLAYER_FOUR_ID,
                        List.of(
                                new Card(CardSuit.CLUBS, CardValue.TEN),
                                new Card(CardSuit.DIAMONDS, CardValue.TEN),
                                new Card(CardSuit.CLUBS, CardValue.SEVEN),
                                new Card(CardSuit.CLUBS, CardValue.FIVE),
                                new Card(CardSuit.CLUBS, CardValue.TWO)
                        )
                )
        );
        assertEquals(List.of(PLAYER_ONE_ID, PLAYER_FOUR_ID), pokerRanger.compareAllHands(hands));
    }

    @Test
    void checkTwoPairWinner() {
        var hands = List.of(
                new Hand(
                        PLAYER_ONE_ID,
                        List.of(
                                new Card(CardSuit.CLUBS, CardValue.TEN),
                                new Card(CardSuit.DIAMONDS, CardValue.TEN),
                                new Card(CardSuit.CLUBS, CardValue.SEVEN),
                                new Card(CardSuit.DIAMONDS, CardValue.SEVEN),
                                new Card(CardSuit.CLUBS, CardValue.TWO)
                        )
                ),
                new Hand(
                        PLAYER_TWO_ID,
                        List.of(
                                new Card(CardSuit.CLUBS, CardValue.TEN),
                                new Card(CardSuit.DIAMONDS, CardValue.TEN),
                                new Card(CardSuit.CLUBS, CardValue.SEVEN),
                                new Card(CardSuit.DIAMONDS, CardValue.SEVEN),
                                new Card(CardSuit.CLUBS, CardValue.THREE)
                        )
                ),
                new Hand(
                        PLAYER_THREE_ID,
                        List.of(
                                new Card(CardSuit.CLUBS, CardValue.ACE),
                                new Card(CardSuit.DIAMONDS, CardValue.ACE),
                                new Card(CardSuit.HEARTS, CardValue.SEVEN),
                                new Card(CardSuit.CLUBS, CardValue.FIVE),
                                new Card(CardSuit.CLUBS, CardValue.TWO)
                        )
                ),
                new Hand(
                        PLAYER_FOUR_ID,
                        List.of(
                                new Card(CardSuit.CLUBS, CardValue.TEN),
                                new Card(CardSuit.DIAMONDS, CardValue.EIGHT),
                                new Card(CardSuit.CLUBS, CardValue.SEVEN),
                                new Card(CardSuit.CLUBS, CardValue.FIVE),
                                new Card(CardSuit.CLUBS, CardValue.TWO)
                        )
                )
        );
        assertEquals(List.of(PLAYER_TWO_ID), pokerRanger.compareAllHands(hands));
    }

    @Test
    void checkThreeOfAKindWinner() {
        var hands = List.of(
                new Hand(
                        PLAYER_ONE_ID,
                        List.of(
                                new Card(CardSuit.CLUBS, CardValue.TEN),
                                new Card(CardSuit.DIAMONDS, CardValue.TEN),
                                new Card(CardSuit.HEARTS, CardValue.TEN),
                                new Card(CardSuit.DIAMONDS, CardValue.SEVEN),
                                new Card(CardSuit.CLUBS, CardValue.TWO)
                        )
                ),
                new Hand(
                        PLAYER_TWO_ID,
                        List.of(
                                new Card(CardSuit.CLUBS, CardValue.TEN),
                                new Card(CardSuit.DIAMONDS, CardValue.TEN),
                                new Card(CardSuit.HEARTS, CardValue.TEN),
                                new Card(CardSuit.DIAMONDS, CardValue.SEVEN),
                                new Card(CardSuit.CLUBS, CardValue.THREE)
                        )
                ),
                new Hand(
                        PLAYER_THREE_ID,
                        List.of(
                                new Card(CardSuit.CLUBS, CardValue.SEVEN),
                                new Card(CardSuit.DIAMONDS, CardValue.SEVEN),
                                new Card(CardSuit.HEARTS, CardValue.SEVEN),
                                new Card(CardSuit.CLUBS, CardValue.FIVE),
                                new Card(CardSuit.CLUBS, CardValue.ACE)
                        )
                ),
                new Hand(
                        PLAYER_FOUR_ID,
                        List.of(
                                new Card(CardSuit.CLUBS, CardValue.TEN),
                                new Card(CardSuit.DIAMONDS, CardValue.TEN),
                                new Card(CardSuit.CLUBS, CardValue.SEVEN),
                                new Card(CardSuit.CLUBS, CardValue.FIVE),
                                new Card(CardSuit.CLUBS, CardValue.KING)
                        )
                )
        );
        assertEquals(List.of(PLAYER_TWO_ID), pokerRanger.compareAllHands(hands));
    }

    @Test
    void checkStraightWinner() {
        var hands = List.of(
                new Hand(
                        PLAYER_ONE_ID,
                        List.of(
                                new Card(CardSuit.CLUBS, CardValue.SIX),
                                new Card(CardSuit.DIAMONDS, CardValue.FIVE),
                                new Card(CardSuit.HEARTS, CardValue.FOUR),
                                new Card(CardSuit.DIAMONDS, CardValue.THREE),
                                new Card(CardSuit.CLUBS, CardValue.TWO)
                        )
                ),
                new Hand(
                        PLAYER_TWO_ID,
                        List.of(
                                new Card(CardSuit.CLUBS, CardValue.FIVE),
                                new Card(CardSuit.DIAMONDS, CardValue.FOUR),
                                new Card(CardSuit.HEARTS, CardValue.ACE),
                                new Card(CardSuit.DIAMONDS, CardValue.TWO),
                                new Card(CardSuit.CLUBS, CardValue.THREE)
                        )
                ),
                new Hand(
                        PLAYER_THREE_ID,
                        List.of(
                                new Card(CardSuit.CLUBS, CardValue.SEVEN),
                                new Card(CardSuit.DIAMONDS, CardValue.SEVEN),
                                new Card(CardSuit.HEARTS, CardValue.SEVEN),
                                new Card(CardSuit.CLUBS, CardValue.FIVE),
                                new Card(CardSuit.CLUBS, CardValue.ACE)
                        )
                ),
                new Hand(
                        PLAYER_FOUR_ID,
                        List.of(
                                new Card(CardSuit.CLUBS, CardValue.TEN),
                                new Card(CardSuit.DIAMONDS, CardValue.TEN),
                                new Card(CardSuit.CLUBS, CardValue.SEVEN),
                                new Card(CardSuit.CLUBS, CardValue.FIVE),
                                new Card(CardSuit.CLUBS, CardValue.KING)
                        )
                )
        );
        assertEquals(List.of(PLAYER_ONE_ID), pokerRanger.compareAllHands(hands));
    }

    @Test
    void checkFlushWinner() {
        var hands = List.of(
                new Hand(
                        PLAYER_ONE_ID,
                        List.of(
                                new Card(CardSuit.CLUBS, CardValue.TEN),
                                new Card(CardSuit.CLUBS, CardValue.FIVE),
                                new Card(CardSuit.CLUBS, CardValue.FOUR),
                                new Card(CardSuit.CLUBS, CardValue.THREE),
                                new Card(CardSuit.CLUBS, CardValue.TWO)
                        )
                ),
                new Hand(
                        PLAYER_TWO_ID,
                        List.of(
                                new Card(CardSuit.CLUBS, CardValue.KING),
                                new Card(CardSuit.CLUBS, CardValue.FOUR),
                                new Card(CardSuit.CLUBS, CardValue.ACE),
                                new Card(CardSuit.CLUBS, CardValue.FIVE),
                                new Card(CardSuit.CLUBS, CardValue.THREE)
                        )
                ),
                new Hand(
                        PLAYER_THREE_ID,
                        List.of(
                                new Card(CardSuit.CLUBS, CardValue.KING),
                                new Card(CardSuit.CLUBS, CardValue.FOUR),
                                new Card(CardSuit.CLUBS, CardValue.ACE),
                                new Card(CardSuit.CLUBS, CardValue.FIVE),
                                new Card(CardSuit.CLUBS, CardValue.THREE)
                        )
                ),
                new Hand(
                        PLAYER_FOUR_ID,
                        List.of(
                                new Card(CardSuit.CLUBS, CardValue.TEN),
                                new Card(CardSuit.DIAMONDS, CardValue.TEN),
                                new Card(CardSuit.CLUBS, CardValue.SEVEN),
                                new Card(CardSuit.CLUBS, CardValue.FIVE),
                                new Card(CardSuit.CLUBS, CardValue.KING)
                        )
                )
        );
        assertEquals(List.of(PLAYER_TWO_ID, PLAYER_THREE_ID), pokerRanger.compareAllHands(hands));
    }

    @Test
    void checkFullHouseWinner() {
        var hands = List.of(
                new Hand(
                        PLAYER_ONE_ID,
                        List.of(
                                new Card(CardSuit.CLUBS, CardValue.TEN),
                                new Card(CardSuit.HEARTS, CardValue.TEN),
                                new Card(CardSuit.SPADES, CardValue.TEN),
                                new Card(CardSuit.CLUBS, CardValue.THREE),
                                new Card(CardSuit.SPADES, CardValue.THREE)
                        )
                ),
                new Hand(
                        PLAYER_TWO_ID,
                        List.of(
                                new Card(CardSuit.CLUBS, CardValue.TEN),
                                new Card(CardSuit.HEARTS, CardValue.TEN),
                                new Card(CardSuit.SPADES, CardValue.TEN),
                                new Card(CardSuit.CLUBS, CardValue.TWO),
                                new Card(CardSuit.SPADES, CardValue.TWO)
                        )
                ),
                new Hand(
                        PLAYER_THREE_ID,
                        List.of(
                                new Card(CardSuit.CLUBS, CardValue.SEVEN),
                                new Card(CardSuit.HEARTS, CardValue.SEVEN),
                                new Card(CardSuit.SPADES, CardValue.SEVEN),
                                new Card(CardSuit.CLUBS, CardValue.ACE),
                                new Card(CardSuit.SPADES, CardValue.ACE)
                        )
                ),
                new Hand(
                        PLAYER_FOUR_ID,
                        List.of(
                                new Card(CardSuit.CLUBS, CardValue.KING),
                                new Card(CardSuit.CLUBS, CardValue.FOUR),
                                new Card(CardSuit.CLUBS, CardValue.ACE),
                                new Card(CardSuit.CLUBS, CardValue.FIVE),
                                new Card(CardSuit.CLUBS, CardValue.THREE)
                        )
                )
        );
        assertEquals(List.of(PLAYER_ONE_ID), pokerRanger.compareAllHands(hands));
    }

    @Test
    void checkShell() {
        var hands = List.of(
            new Hand(
                    PLAYER_ONE_ID,
                    List.of(
                            new Card(CardSuit.CLUBS, CardValue.FOUR),
                            new Card(CardSuit.DIAMONDS, CardValue.FOUR),
                            new Card(CardSuit.HEARTS, CardValue.FOUR),
                            new Card(CardSuit.CLUBS, CardValue.EIGHT),
                            new Card(CardSuit.HEARTS, CardValue.EIGHT)
                    )
            ),
            new Hand(
                    PLAYER_TWO_ID,
                    List.of(
                            new Card(CardSuit.CLUBS, CardValue.FIVE),
                            new Card(CardSuit.DIAMONDS, CardValue.FIVE),
                            new Card(CardSuit.HEARTS, CardValue.FIVE),
                            new Card(CardSuit.CLUBS, CardValue.SEVEN),
                            new Card(CardSuit.HEARTS, CardValue.SEVEN)
                    )
            )
        );
        assertEquals(List.of(PLAYER_TWO_ID), pokerRanger.compareAllHands(hands));
    }
}
