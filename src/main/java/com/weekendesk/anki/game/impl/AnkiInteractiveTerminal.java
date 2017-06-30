package com.weekendesk.anki.game.impl;

import com.weekendesk.anki.card.Card;
import com.weekendesk.anki.deck.Deck;
import com.weekendesk.anki.deck.DeckStorage;
import com.weekendesk.anki.game.AnkiGame;
import com.weekendesk.anki.deck.DeckLoader;

import java.util.Scanner;

/**
 * This class uses {@link AnkiGame} and interacts
 * with the user through the console.
 *
 * @author dfanaro
 */
public class AnkiInteractiveTerminal {

    private final AnkiGame ankiGame;
    private final DeckLoader deckLoader;
    private final DeckStorage deckStorage;

    public AnkiInteractiveTerminal(Deck deck, DeckLoader deckLoader, DeckStorage deckStorage) {
        ankiGame = new AnkiGame(deck);
        this.deckLoader = deckLoader;
        this.deckStorage = deckStorage;
    }

    public void start() {
        ankiGame.loadDecks(deckLoader);
        showInitialSessionStatus();
        studyCards();
        showEndingSessionStatus();
        ankiGame.saveDecks(deckStorage);
    }

    private void showInitialSessionStatus() {
        System.out.println("-------------------------------------------");
        System.out.println("******  WELCOME TO THE ANKI PROGRAM  ******");
        System.out.println("-------------------------------------------");
        System.out.print("\nOpening previous session... ");
        if (ankiGame.newGame()) {
            System.out.println("No previous session found. Starting a new one.");
        } else if (ankiGame.gameWon()) {
            System.out.println("You already know all the questions.");
        } else {
            System.out.println("Session loaded:\n");
            System.out.println("\t" + ankiGame.getRedDeckSize() + " card/s in the red box");
            System.out.println("\t" + ankiGame.getOrangeDeckSize() + " card/s in the orange box");
            System.out.println("\t" + ankiGame.getGreenDeckSize() + " card/s in the green box");
        }
    }

    private void studyCards() {
        if (!ankiGame.gameWon()) {
            if (ankiGame.newGame()) {
                reviewDeck(ankiGame.getInitialDeckCopy());
            }
            while (ankiGame.stillCardsToStudy()) {
                reviewDeck(ankiGame.getRedDeck());
            }
            if (ankiGame.gameWon()) {
                System.out.println("\n\n<<< Congratulations! You have completed the study! >>>\n\n");
            } else {
                ankiGame.moveCards();
            }
        }
    }

    private void showEndingSessionStatus() {
        System.out.println("\n---");
        System.out.println("This session has ended.");
        if (!ankiGame.gameWon()) {
            System.out.println("\nThe next session will contain:");
            System.out.println("\t- " + ankiGame.getRedDeckSize() + " card/s in the red box");
            System.out.println("\t- " + ankiGame.getOrangeDeckSize() + " card/s in the orange box");
            System.out.println("\t- " + ankiGame.getGreenDeckSize() + " card/s in the green box");
        }
    }

    private void reviewDeck(Deck deck) {
        Scanner scanner = new Scanner(System.in);
        Deck clonedDeck = deck.getDeckCopy();
        clonedDeck.getCardsStream().forEach(card -> {
            System.out.println("\nQuestion: " + card.getQuestion() + " [PRESS ENTER TO SEE THE ANSWER]");
            pressEnter(scanner);
            System.out.println("Answer: " + card.getAnswer() + "\n");
            System.out.print("How good you did it ?  Select: 1 (not guessed), 2 (partially guessed) or 3 (guessed) >>> ");
            deck.remove(card);
            putCardIntoBox(scanner, card);
        });
    }

    private void putCardIntoBox(Scanner scanner, Card card) {
        int selectedBox = getBoxNumber(scanner);
        if (selectedBox == 1) {
            ankiGame.addCardToRedDeck(card);
        } else if (selectedBox == 2) {
            ankiGame.addCardToOrangeDeck(card);
        } else if (selectedBox == 3) {
            ankiGame.addCardToGreenDeck(card);
        }
        pressEnter(scanner);
    }

    void pressEnter(Scanner scanner) {
        scanner.nextLine();
    }

    int getBoxNumber(Scanner scanner) {
        return scanner.nextInt();
    }

    AnkiGame getAnkiGame() {
        return ankiGame;
    }

}
