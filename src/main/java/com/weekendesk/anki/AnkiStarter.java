package com.weekendesk.anki;

import com.weekendesk.anki.card.CardUtil;
import com.weekendesk.anki.deck.Deck;
import com.weekendesk.anki.deck.DeckFileReader;
import com.weekendesk.anki.game.AnkiConstants;
import com.weekendesk.anki.game.AnkiGameFactory;
import com.weekendesk.anki.game.impl.AnkiTerminal;

/**
 * Starter Anki class. It contains the application's entry point.
 *
 * @author dfanaro
 */
public final class AnkiStarter {

    /**
     * Anki's entry point. It reads a deck from a file
     * and starts a new {@link AnkiTerminal}.
     * <p>
     * It will retrieve the deck from a default file location unless
     * the following system property is set: <code>initial.deck.file.path</code>
     *
     * @param args of the program (no arguments required)
     */
    public static void main(String[] args) {
        DeckFileReader deckFileReader = new DeckFileReader();
        Deck deck = deckFileReader.read(AnkiConstants.DECK_PATH_SYSTEM_PROPERTY);
        deck.sortCards(CardUtil.ALPHABETIC_ORDER_BY_QUESTION);
        AnkiGameFactory.newAnkiTerminal(deck).start();
    }

}
