package com.weekendesk.anki.game;

/**
 * An Anki Session is an immutable
 * object that encapsulates a session
 * status for a specific moment
 * in time.
 *
 * @author dfanaro
 */
public final class AnkiSession {

    private final int redDeckSize;
    private final int orangeDeckSize;
    private final int greenDeckSize;
    private final boolean gameWon;

    private AnkiSession(int redDeckSize, int orangeDeckSize, int greenDeckSize, boolean gameWon) {
        this.redDeckSize = redDeckSize;
        this.orangeDeckSize = orangeDeckSize;
        this.greenDeckSize = greenDeckSize;
        this.gameWon = gameWon;
    }

    public static AnkiSession of(int redDeckSize, int orangeDeckSize, int greenDeckSize, boolean gameWon) {
        return new AnkiSession(redDeckSize, orangeDeckSize, greenDeckSize, gameWon);
    }

    public int getRedDeckSize() {
        return redDeckSize;
    }

    public int getOrangeDeckSize() {
        return orangeDeckSize;
    }

    public int getGreenDeckSize() {
        return greenDeckSize;
    }

    public boolean newGame() {
        return getRedDeckSize() == 0 && getOrangeDeckSize() == 0 && getGreenDeckSize() == 0;
    }

    public boolean gameWon() {
        return gameWon;
    }

}
