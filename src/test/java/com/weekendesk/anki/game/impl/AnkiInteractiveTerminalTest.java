package com.weekendesk.anki.game.impl;

import com.weekendesk.anki.card.Card;
import com.weekendesk.anki.deck.Deck;
import com.weekendesk.anki.deck.DeckFileWriter;
import com.weekendesk.anki.deck.DeckLoader;
import com.weekendesk.anki.deck.DeckStorage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link AnkiInteractiveTerminal} class.
 *
 * @author dfanaro
 */
public class AnkiInteractiveTerminalTest {

    private Deck deck;
    private DeckStorage mockedDeckStorage;

    @Before
    public void setUp() {
        deck = new Deck();
        deck.addCard(Card.of("a", "1"));
        deck.addCard(Card.of("b", "2"));
        deck.addCard(Card.of("c", "3"));

        mockedDeckStorage = mock(DeckFileWriter.class);
        doNothing().when(mockedDeckStorage).saveRedDeck(any(Deck.class));
        doNothing().when(mockedDeckStorage).saveOrangeDeck(any(Deck.class));
        doNothing().when(mockedDeckStorage).saveGreenDeck(any(Deck.class));
    }

    @Test
    public void putAllCardsFromRedBoxToGreenBox() {
        DeckLoader mockedDeckLoader = mock(DeckLoader.class);
        when(mockedDeckLoader.loadRedDeck()).thenReturn(deck.getDeckCopy());
        when(mockedDeckLoader.loadOrangeDeck()).thenReturn(new Deck());
        when(mockedDeckLoader.loadGreenDeck()).thenReturn(new Deck());

        AnkiInteractiveTerminal interactiveTerminal = spy(new AnkiInteractiveTerminal(deck, mockedDeckLoader, mockedDeckStorage));

        doNothing().when(interactiveTerminal).pressEnter(any(Scanner.class));
        doReturn(greenBox()).when(interactiveTerminal).getBoxNumber(any(Scanner.class));

        interactiveTerminal.start();

        Assert.assertTrue(interactiveTerminal.getAnkiGame().getRedDeck().size() == 0);
        Assert.assertTrue(interactiveTerminal.getAnkiGame().getOrangeDeck().size() == 0);
        Assert.assertTrue(interactiveTerminal.getAnkiGame().getGreenDeck().size() == deck.size());
        Assert.assertTrue(interactiveTerminal.getAnkiGame().gameWon());
    }

    @Test
    public void putAllCardsFromInitialDeckToOrangeBox() {
        DeckLoader mockedDeckLoader = mock(DeckLoader.class);
        when(mockedDeckLoader.loadRedDeck()).thenReturn(new Deck());
        when(mockedDeckLoader.loadOrangeDeck()).thenReturn(new Deck());
        when(mockedDeckLoader.loadGreenDeck()).thenReturn(new Deck());

        AnkiInteractiveTerminal interactiveTerminal = spy(new AnkiInteractiveTerminal(deck, mockedDeckLoader, mockedDeckStorage));

        doNothing().when(interactiveTerminal).pressEnter(any(Scanner.class));
        doReturn(orangeBox()).when(interactiveTerminal).getBoxNumber(any(Scanner.class));

        interactiveTerminal.start();

        Assert.assertTrue(interactiveTerminal.getAnkiGame().getRedDeck().size() == deck.size());
        Assert.assertTrue(interactiveTerminal.getAnkiGame().getOrangeDeck().size() == 0);
        Assert.assertTrue(interactiveTerminal.getAnkiGame().getGreenDeck().size() == 0);
        Assert.assertFalse(interactiveTerminal.getAnkiGame().gameWon());
    }

    @Test
    public void doNothingBecauseAllCardsAreInGreenBox() {
        DeckLoader mockedDeckLoader = mock(DeckLoader.class);
        when(mockedDeckLoader.loadRedDeck()).thenReturn(new Deck());
        when(mockedDeckLoader.loadOrangeDeck()).thenReturn(new Deck());
        when(mockedDeckLoader.loadGreenDeck()).thenReturn(deck.getDeckCopy());

        AnkiInteractiveTerminal interactiveTerminal = spy(new AnkiInteractiveTerminal(deck, mockedDeckLoader, mockedDeckStorage));

        doNothing().when(interactiveTerminal).pressEnter(any(Scanner.class));
        doReturn(greenBox()).when(interactiveTerminal).getBoxNumber(any(Scanner.class));

        interactiveTerminal.start();

        Assert.assertTrue(interactiveTerminal.getAnkiGame().getRedDeck().size() == 0);
        Assert.assertTrue(interactiveTerminal.getAnkiGame().getOrangeDeck().size() == 0);
        Assert.assertTrue(interactiveTerminal.getAnkiGame().getGreenDeck().size() == deck.size());
        Assert.assertTrue(interactiveTerminal.getAnkiGame().gameWon());
    }

    private int orangeBox() {
        return 2;
    }

    private int greenBox() {
        return 3;
    }

}
