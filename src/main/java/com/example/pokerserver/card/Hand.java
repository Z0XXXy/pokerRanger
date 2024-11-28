package com.example.pokerserver.card;

import lombok.*;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Hand implements Serializable {
    @NonNull
    private Long playerId;
    private List<Card> cardsInHand;
}
