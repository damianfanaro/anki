package com.weekendesk.anki.game;

import com.weekendesk.anki.card.CardUtil;
import com.weekendesk.anki.deck.Deck;
import com.weekendesk.anki.deck.DeckFileReader;
import com.weekendesk.anki.deck.DeckFileWriter;
import com.weekendesk.anki.game.impl.AnkiGameImpl;

/**
 * Convenient factory of different
 * implementations of the Anki game.
 *
 * @author dfanaro
 */
public final class AnkiGameFactory {

    public static AnkiGame newAnkiGameImplDeckFromFileSystem() {
        DeckFileReader deckFileReader = new DeckFileReader();
        Deck deck = deckFileReader.read(AnkiConstants.DECK_PATH_SYSTEM_PROPERTY);
        deck.sortCards(CardUtil.ALPHABETIC_ORDER_BY_QUESTION);
        return new AnkiGameImpl(deck, new DeckFileReader(), new DeckFileWriter());
    }

}
