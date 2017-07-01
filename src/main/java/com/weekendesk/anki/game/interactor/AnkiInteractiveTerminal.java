package com.weekendesk.anki.game.interactor;

import com.weekendesk.anki.game.AnkiGameInteractor;
import com.weekendesk.anki.game.AnkiSession;
import com.weekendesk.anki.game.Box;

import java.util.Scanner;

/**
 * Concrete implementation of {@link AnkiGameInteractor}
 * that interacts with the end user via a terminal.
 *
 * @author dfanaro
 */
public class AnkiInteractiveTerminal implements AnkiGameInteractor {

    private final Scanner scanner;

    public AnkiInteractiveTerminal() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void notifyStartingSessionStatus(AnkiSession ankiSession) {
        if (ankiSession.newGame()) {
            System.out.println("No previous session found. Starting a new one.");
        } else if (ankiSession.gameWon()) {
            System.out.println("You already know all the questions.");
        } else {
            System.out.println("Session loaded:\n");
            System.out.println("\t" + ankiSession.getRedDeckSize() + " card/s in the red box");
            System.out.println("\t" + ankiSession.getOrangeDeckSize() + " card/s in the orange box");
            System.out.println("\t" + ankiSession.getGreenDeckSize() + " card/s in the green box");
        }
    }

    @Override
    public void notifyEndingSessionStatus(AnkiSession ankiSession) {
        System.out.println("\n---");
        System.out.println("This session has ended.");
        if (!ankiSession.gameWon()) {
            System.out.println("\nThe next session will contain:");
            System.out.println("\t- " + ankiSession.getRedDeckSize() + " card/s in the red box");
            System.out.println("\t- " + ankiSession.getOrangeDeckSize() + " card/s in the orange box");
            System.out.println("\t- " + ankiSession.getGreenDeckSize() + " card/s in the green box");
        }
    }

    @Override
    public void notifyGameWon() {
        System.out.println("\n\n<<< Congratulations! You have completed the study! >>>\n\n");
    }

    @Override
    public void showQuestion(String question) {
        System.out.println("\nQuestion: " + question + " [PRESS ENTER TO SEE THE ANSWER]");
        pressEnter();
    }

    @Override
    public void showAnswer(String answer) {
        System.out.println("Answer: " + answer + "\n");
        System.out.print("How good you did it ?  Select: 1 (not guessed), 2 (partially guessed) or 3 (guessed) >>> ");
    }

    @Override
    public Box getSelectedBox() {
        int selectedBox = scanner.nextInt();
        while (selectedBox != 1 && selectedBox != 2 && selectedBox != 3) {
            selectedBox = scanner.nextInt();
        }
        if (selectedBox == 1) {
            return Box.RED;
        } else if (selectedBox == 2) {
            return Box.ORANGE;
        } else {
            return Box.GREEN;
        }
    }

    private void pressEnter() {
        scanner.nextLine();
    }

}
