package com.example.pokerserver.card;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Card {
    private CardSuit suit;
    private CardValue value;
}
