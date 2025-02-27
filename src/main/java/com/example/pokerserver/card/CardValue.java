package com.example.pokerserver.card;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum CardValue {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13),
    ACE(14);

    @Setter
    private int order;

    CardValue(int order) {
        this.order = order;
    }
}
