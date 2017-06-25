package com.weekendesk.anki.card;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link Card} class.
 *
 * @author dfanaro
 */
public class CardTest {

    @Test(expected = NullPointerException.class)
    public void exceptionWhenQuestionIsNull() {
        Card.of(null, "Some answer");
    }

    @Test(expected = NullPointerException.class)
    public void exceptionWhenAnswerIsNull() {
        Card.of("Some question", null);
    }

    @Test
    public void cardsEquality() {
        Card cardOne = Card.of("Some question", "Some answer");
        Card cardTwo = Card.of("Some question", "Some answer");
        Assert.assertTrue(cardOne.equals(cardTwo));
    }

    @Test
    public void cardsInequality() {
        Card cardOne = Card.of("Some question", "Some answer");
        Card cardTwo = Card.of("Some other question", "Some other answer");
        Assert.assertFalse(cardOne.equals(cardTwo));
    }

}
