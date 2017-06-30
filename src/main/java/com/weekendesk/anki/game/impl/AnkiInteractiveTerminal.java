package com.weekendesk.anki.game.impl;

import com.weekendesk.anki.card.Card;
import com.weekendesk.anki.deck.Deck;
import com.weekendesk.anki.deck.DeckStorage;
import com.weekendesk.anki.game.AnkiGame;
import com.weekendesk.anki.deck.DeckLoader;

import java.util.Scanner;

/**
 * Specific implementation of an {@link AnkiGame}
 * that interacts with the user through the console.
 *
 * @author dfanaro
 */
public class AnkiInteractiveTerminal extends AnkiGame {

    public AnkiInteractiveTerminal(Deck deck, DeckLoader deckLoader, DeckStorage deckStorage) {
        super(deck, deckLoader, deckStorage);
    }

    @Override
    protected void startSession() {
        System.out.println("-------------------------------------------");
        System.out.println("******  WELCOME TO THE ANKI PROGRAM  ******");
        System.out.println("-------------------------------------------");
        System.out.print("\nOpening previous session... ");
        if (newGame()) {
            System.out.println("No previous session found. Starting a new one.");
        } else if (gameWon()) {
            System.out.println("You already know all the questions.");
        } else {
            System.out.println("Session loaded:\n");
            System.out.println("\t" + redDeck.size() + " card/s in the red box");
            System.out.println("\t" + orangeDeck.size() + " card/s in the orange box");
            System.out.println("\t" + greenDeck.size() + " card/s in the green box");
        }
    }

    @Override
    protected void studyCards() {
        if (!gameWon()) {
            if (newGame()) {
                reviewDeck(initialDeck.getDeckCopy());
            }
            while (stillCardsToStudy()) {
                reviewDeck(redDeck);
            }
            if (gameWon()) {
                System.out.println("\n\n<<< Congratulations! You have completed the study! >>>\n\n");
            } else {
                moveCards();
            }
        }
    }

    @Override
    protected void closeSession() {
        System.out.println("\n---");
        System.out.println("This session has ended.");
        if (!gameWon()) {
            System.out.println("\nThe next session will contain:");
            System.out.println("\t- " + redDeck.size() + " card/s in the red box");
            System.out.println("\t- " + orangeDeck.size() + " card/s in the orange box");
            System.out.println("\t- " + greenDeck.size() + " card/s in the green box");
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
            redDeck.addCard(card);
        } else if (selectedBox == 2) {
            orangeDeck.addCard(card);
        } else if (selectedBox == 3) {
            greenDeck.addCard(card);
        }
        pressEnter(scanner);
    }

    void pressEnter(Scanner scanner) {
        scanner.nextLine();
    }

    int getBoxNumber(Scanner scanner) {
        return scanner.nextInt();
    }

}
