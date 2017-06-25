To generate distributable files run:

``mvn clean package assembly:single``

The generated files can be found inside the `target` folder.

The folder structure within the distributable file is:

```
anki-dfanaro-dist.[ zip | tar.gz ]
    .
    ├── ANKI-PROGRAM.md
    ├── README.md
    ├── bin
    │   └── anki-dfanaro-jar-with-dependencies.jar
    ├── box
    │   ├── green
    │   ├── orange
    │   └── red
    ├── deck
    │   └── deck
    ├── runner.sh
    └── sources
        ├── assembly
        ├── pom.xml
        └── src
```

To play the game just run the `runner.sh` script.

It will load the cards from the `deck` file. You can edit this file and add your cards.

Every line in the `deck` file correspond to a card with the following form:

`some question | some answer`

> Invalid lines will be ignored