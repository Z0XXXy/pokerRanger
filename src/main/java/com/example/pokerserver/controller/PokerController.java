package com.example.pokerserver.controller;

import com.example.pokerserver.card.Hand;
import com.example.pokerserver.poker.PokerRanger;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/poker")
@RequiredArgsConstructor
public class PokerController {
    private final PokerRanger pokerRanger;

    @PostMapping("/winners")
    public List<Long> getWinners(@RequestBody List<Hand> handsList) {
        return pokerRanger.compareAllHands(handsList);
    }
}
