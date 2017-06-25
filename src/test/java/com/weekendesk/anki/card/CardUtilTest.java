package com.weekendesk.anki.card;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link CardUtil} class.
 *
 * @author dfanaro
 */
public class CardUtilTest {

    @Test
    public void validCardFromSimpleString() {
        String splitter = "\\|";
        String card = "a|b";
        Assert.assertTrue(CardUtil.newCardFromSimpleString(card, splitter).isPresent());
        Assert.assertTrue(CardUtil.validCard(card, splitter));
    }

    @Test
    public void invalidCardFromSimpleString() {
        String splitter = "\\|";
        String card = "a|";
        Assert.assertFalse(CardUtil.newCardFromSimpleString(card, splitter).isPresent());
        Assert.assertFalse(CardUtil.validCard(card, splitter));
    }

}
