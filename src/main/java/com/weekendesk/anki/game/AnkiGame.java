package com.weekendesk.anki.game;

import com.weekendesk.anki.deck.Deck;
import com.weekendesk.anki.deck.DeckLoader;
import com.weekendesk.anki.deck.DeckStorage;

/**
 * The abstract {@link AnkiGame} class defines,
 * through a template method design pattern, how
 * the game should behave.
 * <p>
 * It is agnostic of the way it will interact
 * with the user. Sub-classes of this class
 * will provide specific details of such interaction.
 *
 * @author dfanaro
 */
public abstract class AnkiGame {

    private final DeckLoader deckLoader;
    private final DeckStorage deckStorage;

    protected final Deck initialDeck;

    protected Deck redDeck;
    protected Deck orangeDeck;
    protected Deck greenDeck;

    protected AnkiGame(Deck initialDeck, DeckLoader deckLoader, DeckStorage deckStorage) {
        this.initialDeck = initialDeck;
        this.deckLoader = deckLoader;
        this.deckStorage = deckStorage;
    }

    /**
     * Starts the Anki Game by executing
     * a well defined set of steps.
     */
    public void start() {
        loadDecks();
        startSession();
        studyCards();
        closeSession();
        saveDecks();
    }

    private void loadDecks() {
        redDeck = deckLoader.loadRedDeck();
        orangeDeck = deckLoader.loadOrangeDeck();
        greenDeck = deckLoader.loadGreenDeck();
    }

    protected abstract void startSession();

    protected abstract void studyCards();

    protected abstract void closeSession();

    private void saveDecks() {
        deckStorage.saveRedDeck(redDeck);
        deckStorage.saveOrangeDeck(orangeDeck);
        deckStorage.saveGreenDeck(greenDeck);
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

    protected boolean newGame() {
        return redDeck.empty() && orangeDeck.empty() && greenDeck.empty();
    }

    protected boolean gameWon() {
        return initialDeck.size() == greenDeck.size();
    }

    protected boolean stillCardsToStudy() {
        return !redDeck.empty();
    }

    protected void moveCards() {
        orangeDeck.getCardsStream().forEach(redDeck::addCard);
        orangeDeck.clearCards();
        greenDeck.getCardsStream().forEach(orangeDeck::addCard);
        greenDeck.clearCards();
    }

}
