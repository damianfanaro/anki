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
        new Card(null, "Some answer");
    }

    @Test(expected = NullPointerException.class)
    public void exceptionWhenAnswerIsNull() {
        new Card("Some question", null);
    }

    @Test
    public void cardsEquality() {
        Card cardOne = new Card("Some question", "Some answer");
        Card cardTwo = new Card("Some question", "Some answer");
        Assert.assertTrue(cardOne.equals(cardTwo));
    }

    @Test
    public void cardsInequality() {
        Card cardOne = new Card("Some question", "Some answer");
        Card cardTwo = new Card("Some other question", "Some other answer");
        Assert.assertFalse(cardOne.equals(cardTwo));
    }

}
