package com.weekendesk.anki.card;

import java.util.Comparator;
import java.util.Optional;

/**
 * Util methods for {@link Card}.
 *
 * @author dfanaro
 */
public final class CardUtil {

    public static Comparator<Card> ALPHABETIC_ORDER_BY_QUESTION = Comparator.comparing(Card::getQuestion);
    public static Comparator<Card> ALPHABETIC_ORDER_BY_ANSWER = Comparator.comparing(Card::getQuestion);

    /**
     * Given a plain string representation of a card it tries to
     * create a new card with a question and an answer.
     *
     * @param rawCard  a simple string representation of a card
     * @param splitter the regex used to split the plain string card into a question and an answer
     * @return an {@link Optional} that may contain a {@link Card}
     */
    public static Optional<Card> newCardFromSimpleString(String rawCard, String splitter) {
        if (validCard(rawCard, splitter)) {
            String[] card = rawCard.split(splitter);
            return Optional.of(Card.of(card[0], card[1]));
        }
        return Optional.empty();
    }

    /**
     * Determines if a plain string representation of a card is valid.
     *
     * @param rawCard  a simple string representation of a card
     * @param splitter the regex used to split the plain string card into a question and an answer
     * @return true if the input string is a valid card, otherwise, false
     */
    public static boolean validCard(String rawCard, String splitter) {
        String[] twoSidedCard = rawCard.trim().split(splitter);
        return twoSidedCard.length == 2 && !twoSidedCard[0].isEmpty() && !twoSidedCard[1].isEmpty();
    }

}
