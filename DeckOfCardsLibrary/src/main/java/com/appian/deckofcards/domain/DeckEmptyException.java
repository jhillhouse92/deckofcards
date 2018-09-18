package com.appian.deckofcards.domain;


public class DeckEmptyException extends RuntimeException {

    public DeckEmptyException() {
        super("The Deck is empty and cannot be drawn, shuffled, or printed");
    }
}
