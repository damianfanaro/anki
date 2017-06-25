package com.weekendesk.anki.game;

/**
 * Constants of the system.
 * <p>
 * These constants tries to get the value
 * from the system property first, otherwise,
 * a default value is assigned. This way one
 * can customize part of the behavior of the system.
 *
 * @author dfanaro
 */
public final class AnkiConstants {

    public static final String CARD_SPLITTER = System.getProperty("deck.card.splitter", "\\|");

    public static final String RED_BOX_FILE_NAME = System.getProperty("red.box.file.path", "./src/main/resources/box/red");
    public static final String ORANGE_BOX_FILE_NAME = System.getProperty("orange.box.file.path", "./src/main/resources/box/orange");
    public static final String GREEN_BOX_FILE_NAME = System.getProperty("green.box.file.path", "./src/main/resources/box/green");

    public static final String DECK_PATH_SYSTEM_PROPERTY = System.getProperty("initial.deck.file.path", "./src/main/resources/deck");

}
