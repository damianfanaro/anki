# ----------------------
# PROJECT BUILDING PHASE
# ----------------------

FROM maven:latest AS anki-build

COPY assembly/ /usr/share/anki/assembly/
COPY src/ /usr/share/anki/src/
COPY pom.xml /usr/share/anki/

RUN mvn clean package -f /usr/share/anki/pom.xml


# ---------------------------
# DOCKER IMAGE BUILDING PHASE
# ---------------------------

FROM anapsix/alpine-java

ENV ANKI_PATH /opt/anki

COPY --from=anki-build /usr/share/anki/target/anki-dfanaro-jar-with-dependencies.jar ${ANKI_PATH}/bin/anki-dfanaro-jar-with-dependencies.jar
COPY assembly/box/ ${ANKI_PATH}/box/
COPY assembly/deck/ ${ANKI_PATH}/deck/

ADD runner.sh ${ANKI_PATH}/runner.sh

RUN chmod a+x ${ANKI_PATH}/runner.sh

WORKDIR ${ANKI_PATH}

CMD ${ANKI_PATH}/runner.sh
