package com.appian.deckofcards.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {

    public String expectedDeck = "Ace of Clubs\n2 of Clubs\n3 of Clubs\n4 of Clubs\n5 of Clubs\n6 of Clubs\n" +
            "7 of Clubs\n8 of Clubs\n9 of Clubs\n10 of Clubs\nJack of Clubs\nQueen of Clubs\n" +
            "King of Clubs\nAce of Spades\n2 of Spades\n3 of Spades\n4 of Spades\n5 of Spades\n" +
            "6 of Spades\n7 of Spades\n8 of Spades\n9 of Spades\n10 of Spades\nJack of Spades\n" +
            "Queen of Spades\nKing of Spades\nAce of Hearts\n2 of Hearts\n3 of Hearts\n4 of Hearts\n" +
            "5 of Hearts\n6 of Hearts\n7 of Hearts\n8 of Hearts\n9 of Hearts\n10 of Hearts\n" +
            "Jack of Hearts\nQueen of Hearts\nKing of Hearts\nAce of Diamonds\n2 of Diamonds\n" +
            "3 of Diamonds\n4 of Diamonds\n5 of Diamonds\n6 of Diamonds\n7 of Diamonds\n8 of Diamonds\n" +
            "9 of Diamonds\n10 of Diamonds\nJack of Diamonds\nQueen of Diamonds\nKing of Diamonds\n";

    @Test
    public void testDeckSetup() {
        Deck deck = new Deck();
        assertEquals(expectedDeck, deck.toString());
    }

    @Test
    public void testDealOneCard() {
        Deck deck = new Deck();
        Card c = deck.dealOneCard();

        assertEquals("Ace of Clubs\n", c.toString());
    }

    @Test(expected = DeckEmptyException.class)
    public void testDeal53Cards() {
        Deck deck = new Deck();

        for(int i = 1; i <= 53; i++) {
            deck.dealOneCard();
        }
    }

    @Test
    public void testResetDeck() {
        Deck deck = new Deck();
        deck.dealOneCard();

        deck.reset();
        Card c = deck.dealOneCard();
        assertEquals("Ace of Clubs\n", c.toString());
    }

    @Test()
    public void testResetDeckAfterDeckIsEmpty() {
        Deck deck = new Deck();

        try {
            for(int i = 1; i <= 53; i++) {
                if (i == 52) {
                    deck.reset();
                }

                deck.dealOneCard();
            }
        } catch (DeckEmptyException ex) {
            fail("This should not throw an exception as reset is called.");
        }
    }

    @Test
    public void testRiffleShuffle() {
        Deck deck = new Deck();
        deck.shuffle();
        assertNotEquals(expectedDeck, deck.toString());
    }
}
