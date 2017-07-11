### Anki Game

Anki is the Japanese word for **_memorization_**. For more information [click here](https://en.wikipedia.org/wiki/Anki_(software)).

#### Prerequisites:

> ♨ Java 1.8+ (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

> ☉ Maven 3.5+ (https://maven.apache.org/download.cgi)

#### To generate distributable files run:

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

#### Running:

To play the game just run the `runner.sh` script.

It will load the cards from the `deck` file. You can edit this file and add your cards.

Every line in the `deck` file correspond to a card with the following form:

`some question | some answer`

> Invalid lines will be ignored

#### Notes:

- This application does not contain special dependencies except for JUnit and Mockito for testing purposes.
- For simplicity, it just interacts with the user through the standard output stream in the display. 

### Anki Game With Docker:

This repo contains a [Dockerfile](./Dockerfile) at the root level, which means that the program is able to be built
as a Docker image and then it can be run inside a Docker container.

> INFO: You need Docker installed on your machine.

To build the Docker image with name **anki-game** run:

    docker build . -t anki-game

You can check that the image exist by running:

    docker images
    
Then, you can run the previous image with:

    docker run -it anki-game
    
The good part of using Docker is that one does not need to have Java and Maven installed.
The Dockerfile uses multi-stage builds when building the image.