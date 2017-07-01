package com.weekendesk.anki.game;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link AnkiSession} class.
 *
 * @author dfanaro
 */
public class AnkiSessionTest {

    @Test
    public void testSessionIsInNewGameState() {
        AnkiSession ankiSession = AnkiSession.of(0, 0, 0, false);
        Assert.assertTrue(ankiSession.newGame());
    }

    @Test
    public void testSessionIsNotInNewGameState() {
        AnkiSession ankiSession = AnkiSession.of(3, 0, 0, false);
        Assert.assertFalse(ankiSession.newGame());
    }

    @Test
    public void tesGameWon() {
        AnkiSession ankiSession = AnkiSession.of(0, 0, 3, true);
        Assert.assertTrue(ankiSession.gameWon());
    }

}
