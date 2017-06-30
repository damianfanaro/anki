package com.weekendesk.anki.deck;

import com.weekendesk.anki.card.CardUtil;
import com.weekendesk.anki.game.AnkiConstants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Utility class that reads a deck from a file.
 *
 * @author dfanaro
 */
public class DeckFileReader implements DeckLoader {

    /**
     * Reads a deck from a file.
     *
     * @param deckFilePath the file location of the deck
     * @return a deck with cards inside
     */
    public Deck read(String deckFilePath) {
        Deck deck = new Deck();
        try (Stream<String> stream = Files.lines(Paths.get(deckFilePath))) {
            stream.forEach(line -> CardUtil.newCardFromSimpleString(line, AnkiConstants.CARD_SPLITTER).ifPresent(deck::addCard));
        } catch (IOException e) {
            System.out.println("An error has occurred when reading a deck from file. Reason: " + e.getMessage());
        }
        return deck;
    }

    @Override
    public Deck loadRedDeck() {
        return read(AnkiConstants.RED_BOX_FILE_NAME);
    }

    @Override
    public Deck loadOrangeDeck() {
        return read(AnkiConstants.ORANGE_BOX_FILE_NAME);
    }

    @Override
    public Deck loadGreenDeck() {
        return read(AnkiConstants.GREEN_BOX_FILE_NAME);
    }

}
