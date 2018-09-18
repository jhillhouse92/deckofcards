package com.appian.deckofcards.domain;

/**
 * RuntimeException thrown when {@link Deck#dealOneCard()} tries to deal the 53rd card or when {@link Deck#shuffle()} or
 * {@link Deck#toString()} is invoked and the deck is empty. The {@link Deck#reset()} method
 * is used to avoid this. After all cards are dealt, one must call {@link Deck#reset()} before trying to deal again,
 * shuffle again, or print the deck.
 */
public class DeckEmptyException extends RuntimeException {

    public DeckEmptyException() {
        super("The Deck is empty and cannot be drawn, shuffled, or printed");
    }
}
