package com.appian.deckofcards.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

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
    private ArrayList<Card> cards;


    public Deck() {
        this.reset();
    }

    /**
     * <p>This operates as a Stack popping the last item of the list (last-in first-out).</p>
     * @return Card which has a Suit and value.
     * @throws DeckEmptyException once all cards have been dealt.
     * @see {@link com.appian.deckofcards.domain.Card}
     * @since 1.0
     */
    public Card dealOneCard() {
        try {
            return this.cards.remove(this.cards.size() - 1);
        } catch (IndexOutOfBoundsException exception) {
            throw new DeckEmptyException();
        }
    }

    /**
     * <p>This resets the deck by rebuilding it.</p>
     * @return void
     * @see {@link com.appian.deckofcards.domain.Card}
     * @since 1.0
     */
    public void reset() {
        //reset the cards ArrayList
        cards = new ArrayList<>();

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
     * <p>This shuffles the deck using a {@link #riffleShuffle(int) riffle shuffle}. It repeats the operation a
     * psuedo-random number of times to improve the shuffle effectiveness.</p>
     * @return void
     * @see {@link #riffleShuffle(int)}
     * @since 1.0
     */
    public void shuffle() {
        Random rand = new Random();

        //generate random number of times to shuffle 3 - 10 times
        int numberOfRiffleShuffles = rand.nextInt(8) + 3;
        riffleShuffle(numberOfRiffleShuffles);
    }

    /**
     * <p>This is the riffle shuffle. It splits the deck into and builds the deck again taking some from the first
     * half and some from the second half based on psuedo-random generators.</p>
     * @return void
     * @since 1.0
     */
    public void riffleShuffle(int numberOfIterations) {
        Random rand = new Random();

        for (int i = 0; i < numberOfIterations; i++) {
            ArrayList<Card> shuffledCards = new ArrayList<>();

            int halfLength = cards.size() / 2;
            List<Card> halfOne = cards.subList(0, halfLength);
            List<Card> halfTwo = cards.subList(halfLength, cards.size());

            int halfOneSize = halfOne.size();
            ListIterator<Card> halfOneIterator = halfOne.listIterator(halfOneSize);
            ListIterator<Card> halfTwoIterator = halfTwo.listIterator(halfTwo.size());

            while (halfOneIterator.hasPrevious() && halfTwoIterator.hasPrevious()) {
                //generate random number of cards to remove between 1 and 2
                int numberOfCardsToRemove = rand.nextInt(2) + 1;

                //generate random number to choose the half to remove the cards from (e.g. < 50 first half,
                // >= 50 second half)
                //using large numbers helped improve the randomness of which deck to choose from
                int halfToRemoveFrom = rand.nextInt(100);

                for (int j = 0; j < numberOfCardsToRemove &&
                        (halfOneIterator.hasPrevious() || halfTwoIterator.hasPrevious()); j++) {

                    if (halfToRemoveFrom < 50 && halfOneIterator.hasPrevious()) {
                        Card c = halfOneIterator.previous();
                        shuffledCards.add(c);
                    } else if (halfToRemoveFrom >= 50 && halfTwoIterator.hasPrevious()) {
                        Card c = halfTwoIterator.previous();
                        shuffledCards.add(c);
                    }
                }
            }

            //check again as one half may have cards remaining
            while (halfOneIterator.hasPrevious()) {
                shuffledCards.add(halfOneIterator.previous());
            }

            //check again as one half may have cards remaining
            while (halfTwoIterator.hasPrevious()) {
                shuffledCards.add(halfTwoIterator.previous());
            }

            cards = shuffledCards;
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
