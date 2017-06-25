package com.weekendesk.anki.game;

import com.weekendesk.anki.deck.Deck;

/**
 * TODO: complete with description
 *
 * @author dfanaro
 */
public abstract class AnkiGame {

    protected final Deck initialDeck;

    protected Deck redDeck;
    protected Deck orangeDeck;
    protected Deck greenDeck;

    protected AnkiGame(Deck initialDeck) {
        this.initialDeck = initialDeck;
    }

    public void start() {
        openStudySession();
        studyCards();
        storeCards();
        closeStudySession();
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

    protected abstract void openStudySession();

    protected abstract void studyCards();

    protected abstract void storeCards();

    protected abstract void closeStudySession();

}
