package com.weekendesk.anki.game.impl;

import com.weekendesk.anki.card.Card;
import com.weekendesk.anki.deck.Deck;
import com.weekendesk.anki.deck.DeckFileReader;
import com.weekendesk.anki.deck.DeckFileWriter;
import com.weekendesk.anki.game.AnkiConstants;
import com.weekendesk.anki.game.AnkiGame;

import java.util.Scanner;

/**
 * Specific implementation of an {@link AnkiGame}
 * that interacts with the user through the console.
 *
 * @author dfanaro
 */
public class AnkiTerminal extends AnkiGame {

    private DeckFileReader deckFileReader;
    private DeckFileWriter deckFileWriter;
    private boolean newSession = true;
    private boolean memorizationCompleted = false;

    public AnkiTerminal(Deck deck, DeckFileReader deckFileReader, DeckFileWriter deckFileWriter) {
        super(deck);
        this.deckFileReader = deckFileReader;
        this.deckFileWriter = deckFileWriter;
    }

    @Override
    protected void openStudySession() {
        System.out.println("-------------------------------------------");
        System.out.println("******  WELCOME TO THE ANKI PROGRAM  ******");
        System.out.println("-------------------------------------------");
        System.out.print("\nOpening previous session... ");
        redDeck = deckFileReader.read(AnkiConstants.RED_BOX_FILE_NAME);
        orangeDeck = deckFileReader.read(AnkiConstants.ORANGE_BOX_FILE_NAME);
        greenDeck = deckFileReader.read(AnkiConstants.GREEN_BOX_FILE_NAME);
        if (redDeck.empty() && orangeDeck.empty() && greenDeck.empty()) {
            System.out.println("No previous session found. Starting a new one.");
            newSession = true;
        } else if (initialDeck.size() == greenDeck.size()) {
            System.out.println("You already know all the questions.");
            memorizationCompleted = true;
        } else {
            newSession = false;
            System.out.println("Session loaded:\n");
            System.out.println("\t" + redDeck.size() + " card/s in the red box");
            System.out.println("\t" + orangeDeck.size() + " card/s in the orange box");
            System.out.println("\t" + greenDeck.size() + " card/s in the green box");
        }
    }

    @Override
    protected void studyCards() {
        if (!memorizationCompleted) {
            if (newSession) {
                reviewDeck(initialDeck.getDeckCopy());
            }
            while (!redDeck.empty()) {
                reviewDeck(redDeck);
            }
            if (allCardsInGreenBox()) {
                System.out.println("\n\n<<< Congratulations! You have completed the study! >>>\n\n");
                memorizationCompleted = true;
            } else {
                moveCards();
            }
        }
    }

    @Override
    protected void storeCards() {
        deckFileWriter.write(redDeck, AnkiConstants.RED_BOX_FILE_NAME);
        deckFileWriter.write(orangeDeck, AnkiConstants.ORANGE_BOX_FILE_NAME);
        deckFileWriter.write(greenDeck, AnkiConstants.GREEN_BOX_FILE_NAME);
    }

    @Override
    protected void closeStudySession() {
        System.out.println("\n---");
        System.out.println("This session has ended.");
        if (!memorizationCompleted) {
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

    private boolean allCardsInGreenBox() {
        return initialDeck.size() == greenDeck.size();
    }

    private void moveCards() {
        orangeDeck.getCardsStream().forEach(redDeck::addCard);
        orangeDeck.clearCards();
        greenDeck.getCardsStream().forEach(orangeDeck::addCard);
        greenDeck.clearCards();
    }

    void pressEnter(Scanner scanner) {
        scanner.nextLine();
    }

    int getBoxNumber(Scanner scanner) {
        return scanner.nextInt();
    }

}
