package com.weekendesk.anki.card;

import java.util.Objects;

/**
 * Immutable object representing a card.
 * <p>
 * A card just encapsulates a question along with its answer.
 *
 * @author dfanaro
 */
public final class Card {

    private final String question;
    private final String answer;

    private Card(String question, String answer) {
        Objects.requireNonNull(question, "Question cannot be null!");
        Objects.requireNonNull(answer, "Answer cannot be null!");
        this.question = question;
        this.answer = answer;
    }

    public static Card of(String question, String answer) {
        return new Card(question, answer);
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return question.equals(card.question) && answer.equals(card.answer);
    }

    @Override
    public int hashCode() {
        int result = question.hashCode();
        result = 31 * result + answer.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Card{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

}
