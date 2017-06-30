package com.weekendesk.anki.deck;

import com.weekendesk.anki.card.Card;
import com.weekendesk.anki.game.AnkiConstants;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

/**
 * Utility class that writes a deck to a file.
 *
 * @author dfanaro
 */
public class DeckFileWriter implements DeckStorage {

    /**
     * Given a {@link Deck} object, it tries to save its state
     * into a file in the filesystem with the given filename.
     *
     * @param deck     the deck to be written in a file
     * @param fileName the name of the file in the filesystem
     */
    public void write(Deck deck, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Iterator<Card> it = deck.getCardsStream().iterator(); it.hasNext(); ) {
                Card card = it.next();
                writer.write(card.getQuestion());
                writer.write(AnkiConstants.CARD_SPLITTER);
                writer.write(card.getAnswer());
                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println("An error has occurred when writing a deck to a file. Reason: " + e.getMessage());
        }
    }

    @Override
    public void saveRedDeck(Deck deck) {
        write(deck, AnkiConstants.RED_BOX_FILE_NAME);
    }

    @Override
    public void saveOrangeDeck(Deck deck) {
        write(deck, AnkiConstants.ORANGE_BOX_FILE_NAME);
    }

    @Override
    public void saveGreenDeck(Deck deck) {
        write(deck, AnkiConstants.GREEN_BOX_FILE_NAME);
    }

}
