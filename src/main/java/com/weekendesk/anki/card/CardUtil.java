package com.weekendesk.anki.card;

import java.util.Comparator;
import java.util.Optional;

/**
 * TODO: complete with description
 *
 * @author dfanaro
 */
public final class CardUtil {

    public static Comparator<Card> ALPHABETIC_ORDER_BY_QUESTION = Comparator.comparing(Card::getQuestion);
    public static Comparator<Card> ALPHABETIC_ORDER_BY_ANSWER = Comparator.comparing(Card::getQuestion);

    public static Optional<Card> newCardFromSimpleString(String rawCard, String splitter) {
        if (validCard(rawCard, splitter)) {
            String[] card = rawCard.split(splitter);
            return Optional.of(new Card(card[0], card[1]));
        }
        return Optional.empty();
    }

    public static boolean validCard(String card, String splitter) {
        String[] twoSidedCard = card.trim().split(splitter);
        return twoSidedCard.length == 2 && !twoSidedCard[0].isEmpty() && !twoSidedCard[1].isEmpty();
    }

}
