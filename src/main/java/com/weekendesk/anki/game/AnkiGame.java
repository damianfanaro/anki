package com.weekendesk.anki.game;

import com.weekendesk.anki.card.Card;
import com.weekendesk.anki.deck.Deck;
import com.weekendesk.anki.deck.DeckLoader;
import com.weekendesk.anki.deck.DeckStorage;

/**
 * This class maintains the state of the game.
 * In addition, it provides convenient methods
 * to perform operations like: load/save decks,
 * check if the game is finished, etc.
 * <p>
 * It is agnostic of the way it will interact
 * with the user. Other classes will use this one by
 * a composition and provide specific details of the
 * interaction with the user (e.g. console, Web UI, etc).
 *
 * @author dfanaro
 */
public final class AnkiGame {

    private final Deck initialDeck;

    private Deck redDeck;
    private Deck orangeDeck;
    private Deck greenDeck;

    public AnkiGame(Deck initialDeck) {
        this.initialDeck = initialDeck;
    }

    public void loadDecks(DeckLoader deckLoader) {
        redDeck = deckLoader.loadRedDeck();
        orangeDeck = deckLoader.loadOrangeDeck();
        greenDeck = deckLoader.loadGreenDeck();
    }

    public void saveDecks(DeckStorage deckStorage) {
        deckStorage.saveRedDeck(redDeck);
        deckStorage.saveOrangeDeck(orangeDeck);
        deckStorage.saveGreenDeck(greenDeck);
    }

    public Deck getInitialDeckCopy() {
        return initialDeck.getDeckCopy();
    }

    public Deck getRedDeck() {
        return redDeck;
    }

    public Deck getOrangeDeck() {
        return orangeDeck;
    }

    public Deck getGreenDeck() {
        return greenDeck;
    }

    public int getRedDeckSize() {
        return redDeck.size();
    }

    public int getOrangeDeckSize() {
        return orangeDeck.size();
    }

    public int getGreenDeckSize() {
        return greenDeck.size();
    }

    public boolean newGame() {
        return redDeck.empty() && orangeDeck.empty() && greenDeck.empty();
    }

    public boolean gameWon() {
        return initialDeck.size() == greenDeck.size();
    }

    public boolean stillCardsToStudy() {
        return !redDeck.empty();
    }

    public void moveCards() {
        orangeDeck.getCardsStream().forEach(redDeck::addCard);
        orangeDeck.clearCards();
        greenDeck.getCardsStream().forEach(orangeDeck::addCard);
        greenDeck.clearCards();
    }

    public void addCardToRedDeck(Card card) {
        redDeck.addCard(card);
    }

    public void addCardToOrangeDeck(Card card) {
        orangeDeck.addCard(card);
    }

    public void addCardToGreenDeck(Card card) {
        greenDeck.addCard(card);
    }

}
