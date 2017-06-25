To generate a distributable file:

``mvn clean package assembly:single``

Folder structure of the distributable file:

```
anki-dfanaro-dist.[zip|tar.gz]
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