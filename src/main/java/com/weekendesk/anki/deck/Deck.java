package com.weekendesk.anki.deck;

import com.weekendesk.anki.card.Card;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * A deck is just a wrapper of a list of {@link Card}s.
 * <p>
 * It provides convenient methods like sorting, adding, removing.
 *
 * @author dfanaro
 */
public final class Deck {

    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public Stream<Card> getCardsStream() {
        return cards.stream();
    }

    public void sortCards(Comparator<Card> cardComparator) {
        cards.sort(cardComparator);
    }

    public int size() {
        return cards.size();
    }

    public boolean empty() {
        return cards.size() == 0;
    }

    public void remove(Card card) {
        cards.remove(card);
    }

    public void clearCards() {
        cards.clear();
    }

    public void addAll(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public Deck getDeckCopy() {
        Deck deck = new Deck();
        deck.addAll(cards);
        return deck;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "cards=" + cards +
                '}';
    }

}
