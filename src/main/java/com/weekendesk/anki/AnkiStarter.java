package com.weekendesk.anki;

import com.weekendesk.anki.game.AnkiGameFactory;
import com.weekendesk.anki.game.interactor.AnkiInteractiveTerminal;

/**
 * Starter Anki class. It contains the application's entry point.
 *
 * @author dfanaro
 */
public final class AnkiStarter {

    /**
     * Anki's entry point. It reads a deck from a file
     * and starts a new {@link AnkiInteractiveTerminal}.
     * <p>
     * It will retrieve the deck from a default file location unless
     * the following system property is set: <code>initial.deck.file.path</code>
     *
     * @param args of the program (no arguments required)
     */
    public static void main(String[] args) {

        System.out.println("-------------------------------------------");
        System.out.println("******  WELCOME TO THE ANKI PROGRAM  ******");
        System.out.println("-------------------------------------------");

        AnkiGameFactory.newAnkiGameImplDeckFromFileSystem().start(new AnkiInteractiveTerminal());

    }

}
