package com.appian.deckofcards.controllers;

import com.appian.deckofcards.domain.Card;
import com.appian.deckofcards.domain.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/deck")
public class DeckController {

    @Autowired
    public Deck deck;

    @RequestMapping("/dealOneCard")
    public Card dealOneCard() {
        return deck.dealOneCard();
    }

    @RequestMapping("/reset")
    @ResponseStatus(value = HttpStatus.OK)
    public void resetDeck() {
        deck.reset();
    }

    @RequestMapping("/shuffle")
    @ResponseStatus(value = HttpStatus.OK)
    public void shuffleDeck() {
        deck.shuffle();
    }
}
