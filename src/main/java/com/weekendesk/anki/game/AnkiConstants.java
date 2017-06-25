package com.weekendesk.anki.game;

/**
 * TODO: complete with description
 *
 * @author dfanaro
 */
public final class AnkiConstants {

    public static final String PIPE_CARD_SPLITTER = "\\|";

    public static final String RED_BOX_FILE_NAME = System.getProperty("red.box.file.path", "./src/main/resources/box/red");
    public static final String ORANGE_BOX_FILE_NAME = System.getProperty("orange.box.file.path", "./src/main/resources/box/orange");
    public static final String GREEN_BOX_FILE_NAME = System.getProperty("green.box.file.path", "./src/main/resources/box/green");

    public static final String DECK_PATH_SYSTEM_PROPERTY = System.getProperty("initial.deck.file.path", "./src/main/resources/deck");

}
