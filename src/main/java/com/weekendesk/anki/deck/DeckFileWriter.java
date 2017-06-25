package com.weekendesk.anki.deck;

import com.weekendesk.anki.card.Card;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

/**
 * TODO: complete with description
 *
 * @author dfanaro
 */
public class DeckFileWriter {

    public void write(Deck deck, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Iterator<Card> it = deck.getCardsStream().iterator(); it.hasNext(); ) {
                Card card = it.next();
                writer.write(card.getQuestion());
                writer.write("|");
                writer.write(card.getAnswer());
                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println("An error has occurred when writing a deck to a file. Reason: " + e.getMessage());
        }
    }

}
