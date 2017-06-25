package com.weekendesk.anki.game;

import com.weekendesk.anki.deck.Deck;
import com.weekendesk.anki.deck.DeckFileReader;
import com.weekendesk.anki.deck.DeckFileWriter;
import com.weekendesk.anki.game.impl.AnkiTerminal;

/**
 * TODO: complete with description
 *
 * @author dfanaro
 */
public final class AnkiGameFactory {

    public static AnkiGame newAnkiTerminal(Deck deck) {
        return new AnkiTerminal(deck, new DeckFileReader(), new DeckFileWriter());
    }

}
