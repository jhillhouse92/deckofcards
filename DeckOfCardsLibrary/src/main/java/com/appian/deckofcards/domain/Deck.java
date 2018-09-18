package com.appian.deckofcards.domain;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * The Deck is the core class to be used for the foundational component. When the constructor is invoked
 * it builds the deck as a stack for each Suit and through each of the possible values.
 *
 * The methods exposed will be dealOneCard() and shuffle().
 */
public class Deck {

    /**
     * The internal property for managing cards in the deck. An ArrayList is used vs other Stack implementations
     * because access to the underlying array at various indices needed to be possible. We access it and add to it
     * in a LIFO manner.
     */
    private ArrayList<Card> cards = new ArrayList();


    public Deck() {
        //for each suit and for each value King-Jack, 10-Ace build deck
        for (Suit suit : Suit.values()) {
            for (int i = 13; i >= 1; i--) {
                String value;

                switch (i) {
                    case 1:
                        value = "Ace";
                        break;
                    case 11:
                        value = "Jack";
                        break;
                    case 12:
                        value = "Queen";
                        break;
                    case 13:
                        value = "King";
                        break;
                    default:
                        value = Integer.toString(i);
                }

                Card card = new Card(suit, value);
                cards.add(card);
            }
        }
    }

    /**
     * <p>This operates as a Stack popping the last item of the list (last-in first-out).</p>
     * @return Card which has a Suit and value.
     * @throws DeckEmptyException once all cards have been dealt.
     * @see {@link com.appian.deckofcards.domain.Card}
     * @since 1.0
     */
    public Card dealOneCard() throws DeckEmptyException {
        try {
            return this.cards.remove(this.cards.size() - 1);
        } catch (IndexOutOfBoundsException exception) {
            throw new DeckEmptyException();
        }
    }

    /**
     * <p>We override the toString method to print out the deck in a LIFO manner.</p>
     * @return String representation of the Deck of each Card with its Suit and value.
     * @see {@link com.appian.deckofcards.domain.Card}
     * @since 1.0
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        ListIterator<Card> deckIterator = cards.listIterator(cards.size());

        while (deckIterator.hasPrevious()) {
            Card card = deckIterator.previous();
            sb.append(card.toString());
        }

        return sb.toString();
    }
}
