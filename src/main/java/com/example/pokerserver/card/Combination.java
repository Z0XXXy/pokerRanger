package com.example.pokerserver.card;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public enum Combination {

    HIGH_CARD(0),
    PAIR(1),
    TWO_PAIR(2),
    THREE_OF_A_KIND(3),
    STRAIGHT(4),
    FLUSH(5),
    FULL_HOUSE(6),
    FOUR_OF_A_KIND(7),
    STRAIGHT_FLUSH(8),
    ROYAL_FLUSH(9);

    @Setter
    private List<CardValue> sortedForComparingCards;
    private final int order;

    Combination(int order) {
        this.order = order;
    }
}
