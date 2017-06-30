package com.weekendesk.anki.game;

import com.weekendesk.anki.deck.Deck;
import com.weekendesk.anki.deck.DeckFileReader;
import com.weekendesk.anki.deck.DeckFileWriter;
import com.weekendesk.anki.game.impl.AnkiInteractiveTerminal;

/**
 * Convenient factory of different
 * implementations of the Anki game.
 *
 * @author dfanaro
 */
public final class AnkiGameFactory {

    /**
     * Returns a new instance of the Anki Game
     * whose interaction with the user is made
     * through the console.
     *
     * @param deck the initial deck of cards
     * @return an {@link AnkiInteractiveTerminal} instance
     */
    public static AnkiGame newAnkiTerminal(Deck deck) {
        return new AnkiInteractiveTerminal(deck, new DeckFileReader(), new DeckFileWriter());
    }

}
