package com.appian.deckofcards.domain;

/**
 * A representation of the playing card with a Suit and Value; the Value can be Ace - 9 and Jack - King of a typical
 * playing card deck.
 */
public class Card {

    private Suit suit;
    private String value;

    /**
     * Constructor requires a Suit and a Value; this enforces that a Card can never be created without one. There is no
     * error checking of a value outside of the typical playing card deck however.
     * @param suit a Suit representing Diamonds, Hearts, Spades, or Clubs
     * @param value a Value can be Ace - 9 and Jack - King of a typical playing card deck, but this is not enforced.
     */
    public Card(Suit suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    /**
     * Gets the Suit of the Card
     * @return the Suit of the Card
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Sets the Suit of the Card
     * @param suit a Suit representing Diamonds, Hearts, Spades, or Clubs
     */
    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    /**
     * Gets the value of the Card
     * @return the value of the Card
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the card
     * @param value Can be Ace - 9 and Jack - King of a typical playing card deck, but this is not enforced.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Returns a String representation of the card containing it's value and Suit (for example, Ace of Spades).
     * @return a String representation of the card containing it's value and Suit (for example, Ace of Spades).
     */
    @Override
    public String toString() {
        return value + " of " + suit.toString() + "\n";
    }
}
