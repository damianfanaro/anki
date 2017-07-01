package com.weekendesk.anki.game;

/**
 * Main contract for the Anki Game.
 * <p>
 * Implementations of this interface will
 * run the game and interact with a
 * {@link AnkiGameInteractor}.
 *
 * @author dfanaro
 */
public interface AnkiGame {

    void start(AnkiGameInteractor ankiGameInteractor);

    int getRedDeckSize();

    int getOrangeDeckSize();

    int getGreenDeckSize();

    boolean gameWon();

}
