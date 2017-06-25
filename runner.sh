#!/usr/bin/env bash

BASEDIR=`dirname $0`

INITIAL_DECK_FILE_PATH=${BASEDIR}"/deck/deck"
RED_BOX_FILE_PATH=${BASEDIR}"/box/red"
ORANGE_BOX_FILE_PATH=${BASEDIR}"/box/orange"
GREEN_BOX_FILE_PATH=${BASEDIR}"/box/green"

INITIAL_DECK_SYSTEM_PROPERTY="-Dinitial.deck.file.path="${INITIAL_DECK_FILE_PATH}
RED_BOX_SYSTEM_PROPERTY="-Dred.box.file.path="${RED_BOX_FILE_PATH}
ORANGE_BOX_SYSTEM_PROPERTY="-Dorange.box.file.path="${ORANGE_BOX_FILE_PATH}
GREEN_BOX_SYSTEM_PROPERTY="-Dgreen.box.file.path="${GREEN_BOX_FILE_PATH}

JAVA_OPTS="${INITIAL_DECK_SYSTEM_PROPERTY} ${RED_BOX_SYSTEM_PROPERTY} ${ORANGE_BOX_SYSTEM_PROPERTY} ${GREEN_BOX_SYSTEM_PROPERTY}"


cleanBoxes() {
    > box/red
    > box/orange
    > box/green
}


while true; do
    clear
    java ${JAVA_OPTS} -jar bin/anki-dfanaro-jar-with-dependencies.jar
    echo
    echo
    echo "Select an option:"
    select op in "Start new session" "Reset game" "Exit"; do
        case ${op} in
            "Start new session" ) break;;
            "Reset game" ) cleanBoxes; break;;
            "Exit" ) exit;;
        esac
    done
done
