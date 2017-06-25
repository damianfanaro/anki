package com.weekendesk.anki.deck;

import com.weekendesk.anki.card.Card;
import com.weekendesk.anki.card.CardUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link Deck} class.
 *
 * @author dfanaro
 */
public class DeckTest {

    @Test
    public void cardsSortedByQuestion() {
        Card cardOne = Card.of("a", "1");
        Card cardTwo = Card.of("b", "2");
        Card cardThree = Card.of("c", "3");

        Deck sortedDeck = new Deck();
        sortedDeck.addCard(cardOne);
        sortedDeck.addCard(cardTwo);
        sortedDeck.addCard(cardThree);

        Deck unsortedDeck = new Deck();
        unsortedDeck.addCard(cardThree);
        unsortedDeck.addCard(cardTwo);
        unsortedDeck.addCard(cardOne);
        unsortedDeck.sortCards(CardUtil.ALPHABETIC_ORDER_BY_QUESTION);

        Assert.assertEquals(sortedDeck.toString(), unsortedDeck.toString());
    }

    @Test
    public void cardsSortedByAnswer() {
        Card cardOne = Card.of("a", "1");
        Card cardTwo = Card.of("b", "2");
        Card cardThree = Card.of("c", "3");

        Deck sortedDeck = new Deck();
        sortedDeck.addCard(cardOne);
        sortedDeck.addCard(cardTwo);
        sortedDeck.addCard(cardThree);

        Deck unsortedDeck = new Deck();
        unsortedDeck.addCard(cardThree);
        unsortedDeck.addCard(cardTwo);
        unsortedDeck.addCard(cardOne);
        unsortedDeck.sortCards(CardUtil.ALPHABETIC_ORDER_BY_ANSWER);

        Assert.assertEquals(sortedDeck.toString(), unsortedDeck.toString());
    }

    @Test
    public void emptyDeck() {
        Assert.assertTrue(new Deck().empty());
        Assert.assertTrue(new Deck().size() == 0);
    }

    @Test
    public void notEmptyDeck() {
        Card card = Card.of("a", "1");
        Deck deck = new Deck();
        deck.addCard(card);
        Assert.assertTrue(!deck.empty());
        Assert.assertTrue(deck.size() == 1);
    }

    @Test
    public void clearDeckCards() {
        Card card = Card.of("a", "1");
        Deck deck = new Deck();
        deck.addCard(card);
        deck.clearCards();
        Assert.assertTrue(deck.empty());
        Assert.assertTrue(deck.size() == 0);
    }

    @Test
    public void removeACard() {
        Card card = Card.of("a", "1");
        Deck deck = new Deck();
        deck.addCard(card);
        deck.remove(card);
        Assert.assertTrue(deck.empty());
        Assert.assertTrue(deck.size() == 0);
    }

}
