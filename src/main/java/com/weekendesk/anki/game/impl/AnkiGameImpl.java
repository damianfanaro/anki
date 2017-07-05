package com.weekendesk.anki.game.impl;

import com.weekendesk.anki.card.Card;
import com.weekendesk.anki.deck.Deck;
import com.weekendesk.anki.deck.DeckLoader;
import com.weekendesk.anki.deck.DeckStorage;
import com.weekendesk.anki.game.AnkiGame;
import com.weekendesk.anki.game.AnkiGameInteractor;
import com.weekendesk.anki.game.AnkiSession;
import com.weekendesk.anki.game.Box;

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
public final class AnkiGameImpl implements AnkiGame {

    private final Deck initialDeck;
    private final DeckLoader deckLoader;
    private final DeckStorage deckStorage;

    private Deck redDeck;
    private Deck orangeDeck;
    private Deck greenDeck;
    private AnkiGameInteractor ankiGameInteractor;

    public AnkiGameImpl(Deck initialDeck, DeckLoader deckLoader, DeckStorage deckStorage) {
        this.initialDeck = initialDeck;
        this.deckLoader = deckLoader;
        this.deckStorage = deckStorage;
    }

    @Override
    public void start(AnkiGameInteractor interactor) {
        this.ankiGameInteractor = interactor;
        loadDecks();
        AnkiSession ankiSession = getCurrentSession();
        ankiGameInteractor.notifyStartingSessionStatus(ankiSession);
        studyCards();
        saveDecks();
        ankiSession = getCurrentSession();
        ankiGameInteractor.notifyEndingSessionStatus(ankiSession);
    }

    @Override
    public int getRedDeckSize() {
        return redDeck.size();
    }

    @Override
    public int getOrangeDeckSize() {
        return orangeDeck.size();
    }

    @Override
    public int getGreenDeckSize() {
        return greenDeck.size();
    }

    @Override
    public boolean gameWon() {
        return initialDeck.size() == greenDeck.size();
    }

    private void loadDecks() {
        redDeck = deckLoader.loadRedDeck();
        orangeDeck = deckLoader.loadOrangeDeck();
        greenDeck = deckLoader.loadGreenDeck();
    }

    private void saveDecks() {
        deckStorage.saveRedDeck(redDeck);
        deckStorage.saveOrangeDeck(orangeDeck);
        deckStorage.saveGreenDeck(greenDeck);
    }

    private void studyCards() {
        if (!gameWon()) {
            if (newGame()) {
                reviewDeck(initialDeck.getDeckCopy());
            }
            while (stillCardsToStudy()) {
                reviewDeck(redDeck);
            }
            if (gameWon()) {
                ankiGameInteractor.notifyGameWon();
            } else {
                moveCards();
            }
        }
    }

    private void moveCards() {
        orangeDeck.getCardsStream().forEach(redDeck::addCard);
        orangeDeck.clearCards();
        greenDeck.getCardsStream().forEach(orangeDeck::addCard);
        greenDeck.clearCards();
    }

    private void putCardIntoBox(Card card, Box box) {
        if (box == Box.RED) {
            redDeck.addCard(card);
        } else if (box == Box.ORANGE) {
            orangeDeck.addCard(card);
        } else {
            greenDeck.addCard(card);
        }
    }

    private void reviewDeck(Deck deck) {
        Deck clonedDeck = deck.getDeckCopy();
        clonedDeck.getCardsStream().forEach(card -> {
            ankiGameInteractor.showQuestion(card.getQuestion());
            ankiGameInteractor.showAnswer(card.getAnswer());
            deck.remove(card);
            Box box = ankiGameInteractor.getSelectedBox();
            putCardIntoBox(card, box);
        });
    }

    private boolean stillCardsToStudy() {
        return !redDeck.empty();
    }

    private boolean newGame() {
        return redDeck.empty() && orangeDeck.empty() && greenDeck.empty();
    }

    private AnkiSession getCurrentSession() {
        return AnkiSession.of(getRedDeckSize(), getOrangeDeckSize(), getGreenDeckSize(), gameWon());
    }

}
