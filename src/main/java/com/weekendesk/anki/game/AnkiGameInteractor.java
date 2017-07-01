package com.weekendesk.anki.game;

/**
 * This contract establish the standard way
 * an {@link AnkiGame} implementation will interact
 * with an concrete implementation of this interface.
 * <p>
 * Implementations of this interface could be:
 * - Java Swing
 * - Console
 * - Web Site
 *
 * @author dfanaro
 */
public interface AnkiGameInteractor {

    void notifyGameWon();

    void notifyStartingSessionStatus(AnkiSession ankiSession);

    void notifyEndingSessionStatus(AnkiSession ankiSession);

    void showQuestion(String question);

    void showAnswer(String answer);

    Box getSelectedBox();

}
