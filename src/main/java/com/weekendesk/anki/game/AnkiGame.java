package com.weekendesk.anki.game;

import com.weekendesk.anki.deck.Deck;

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

    protected final Deck initialDeck;

    protected Deck redDeck;
    protected Deck orangeDeck;
    protected Deck greenDeck;

    protected AnkiGame(Deck initialDeck) {
        this.initialDeck = initialDeck;
    }

    /**
     * Starts the Anki Game by executing
     * a well defined set of steps.
     */
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
