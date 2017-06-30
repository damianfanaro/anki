package com.weekendesk.anki.deck;

/**
 * This contract defines the methods for loading
 * the three decks of the game.
 *
 * @author dfanaro
 */
public interface DeckLoader {

    Deck loadRedDeck();

    Deck loadOrangeDeck();

    Deck loadGreenDeck();

}
