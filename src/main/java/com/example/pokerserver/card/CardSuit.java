package com.example.pokerserver.card;

import lombok.Getter;

@Getter
public enum CardSuit {
    CLUBS(0),
    DIAMONDS(1),
    HEARTS(2),
    SPADES(3);

    private final int order;

    CardSuit(int order) {
        this.order = order;
    }
}
