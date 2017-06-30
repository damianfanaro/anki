package com.weekendesk.anki.deck;

/**
 * This contract defines the methods for storing
 * the three decks of the game.
 *
 * @author dfanaro
 */
public interface DeckStorage {

    void saveRedDeck(Deck deck);

    void saveOrangeDeck(Deck deck);

    void saveGreenDeck(Deck deck);

}
