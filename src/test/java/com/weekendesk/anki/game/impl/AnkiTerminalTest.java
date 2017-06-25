package com.weekendesk.anki.game.impl;

import com.weekendesk.anki.card.Card;
import com.weekendesk.anki.deck.Deck;
import com.weekendesk.anki.deck.DeckFileReader;
import com.weekendesk.anki.deck.DeckFileWriter;
import com.weekendesk.anki.game.AnkiConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link AnkiTerminal} class.
 *
 * @author dfanaro
 */
public class AnkiTerminalTest {

    private Deck deck;
    private DeckFileWriter mockedDeckWriter;

    @Before
    public void setUp() {
        deck = new Deck();
        deck.addCard(new Card("a", "1"));
        deck.addCard(new Card("b", "2"));
        deck.addCard(new Card("c", "3"));

        mockedDeckWriter = mock(DeckFileWriter.class);
        doNothing().when(mockedDeckWriter).write(any(Deck.class), eq(AnkiConstants.RED_BOX_FILE_NAME));
        doNothing().when(mockedDeckWriter).write(any(Deck.class), eq(AnkiConstants.ORANGE_BOX_FILE_NAME));
        doNothing().when(mockedDeckWriter).write(any(Deck.class), eq(AnkiConstants.GREEN_BOX_FILE_NAME));
    }

    @Test
    public void putAllCardsFromRedBoxToGreenBox() {
        DeckFileReader mockedDeckReader = mock(DeckFileReader.class);
        when(mockedDeckReader.read(AnkiConstants.RED_BOX_FILE_NAME)).thenReturn(deck.getDeckCopy());
        when(mockedDeckReader.read(AnkiConstants.ORANGE_BOX_FILE_NAME)).thenReturn(new Deck());
        when(mockedDeckReader.read(AnkiConstants.GREEN_BOX_FILE_NAME)).thenReturn(new Deck());

        AnkiTerminal ankiGame = spy(new AnkiTerminal(deck, mockedDeckReader, mockedDeckWriter));

        doNothing().when(ankiGame).pressEnter(any(Scanner.class));
        doReturn(3).when(ankiGame).getBoxNumber(any(Scanner.class));

        ankiGame.start();

        Assert.assertTrue(ankiGame.getRedDeck().size() == 0);
        Assert.assertTrue(ankiGame.getOrangeDeck().size() == 0);
        Assert.assertTrue(ankiGame.getGreenDeck().size() == deck.size());
    }

    @Test
    public void putAllCardsFromInitialDeckToOrangeBox() {
        DeckFileReader mockedDeckReader = mock(DeckFileReader.class);
        when(mockedDeckReader.read(AnkiConstants.RED_BOX_FILE_NAME)).thenReturn(new Deck());
        when(mockedDeckReader.read(AnkiConstants.ORANGE_BOX_FILE_NAME)).thenReturn(new Deck());
        when(mockedDeckReader.read(AnkiConstants.GREEN_BOX_FILE_NAME)).thenReturn(new Deck());

        AnkiTerminal ankiGame = spy(new AnkiTerminal(deck, mockedDeckReader, mockedDeckWriter));

        doNothing().when(ankiGame).pressEnter(any(Scanner.class));
        doReturn(2).when(ankiGame).getBoxNumber(any(Scanner.class));

        ankiGame.start();

        Assert.assertTrue(ankiGame.getRedDeck().size() == deck.size());
        Assert.assertTrue(ankiGame.getOrangeDeck().size() == 0);
        Assert.assertTrue(ankiGame.getGreenDeck().size() == 0);
    }

    @Test
    public void doNothingBecauseAllCardsAreInGreenBox() {
        DeckFileReader mockedDeckReader = mock(DeckFileReader.class);
        when(mockedDeckReader.read(AnkiConstants.RED_BOX_FILE_NAME)).thenReturn(new Deck());
        when(mockedDeckReader.read(AnkiConstants.ORANGE_BOX_FILE_NAME)).thenReturn(new Deck());
        when(mockedDeckReader.read(AnkiConstants.GREEN_BOX_FILE_NAME)).thenReturn(deck.getDeckCopy());

        AnkiTerminal ankiGame = spy(new AnkiTerminal(deck, mockedDeckReader, mockedDeckWriter));

        doNothing().when(ankiGame).pressEnter(any(Scanner.class));
        doReturn(3).when(ankiGame).getBoxNumber(any(Scanner.class));

        ankiGame.start();

        Assert.assertTrue(ankiGame.getRedDeck().size() == 0);
        Assert.assertTrue(ankiGame.getOrangeDeck().size() == 0);
        Assert.assertTrue(ankiGame.getGreenDeck().size() == deck.size());
    }

}
