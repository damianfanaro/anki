package com.weekendesk.anki.deck;

import com.weekendesk.anki.card.CardUtil;
import com.weekendesk.anki.game.AnkiConstants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * TODO: complete with description
 *
 * @author dfanaro
 */
public class DeckFileReader {

    public Deck read(String deckFilePath) {
        Deck deck = new Deck();
        try (Stream<String> stream = Files.lines(Paths.get(deckFilePath))) {
            stream.forEach(line -> CardUtil.newCardFromSimpleString(line, AnkiConstants.PIPE_CARD_SPLITTER).ifPresent(deck::addCard));
        } catch (IOException e) {
            System.out.println("An error has occurred when reading a deck from file. Reason: " + e.getMessage());
        }
        return deck;
    }

}
