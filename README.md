# Deck of Cards

This is a foundational part of an Appian product and has 2 projects:

1. Deck of Cards Domain Library
2. Deck of Cards REST API

The Trello board capturing the sprint and user stories is accessible [here](https://trello.com/b/Iqkrc32s/deck-of-cards).

## Getting Started

The core objectives of this component are to provide card shuffling functionality and the ability to deal one card at a
time until the end of a 52-deck. This README contains instructions for how to clone this repo, deploy the REST API with
Spring Boot using Gradle, any pre-requisites and notes for how to access the documentation to integrate with this
component.

To get started, clone the repo:

`git clone https://github.com/jhillhouse92/deckofcards.git`

## Prerequisites

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) or later
- [Gradle 4+](http://www.gradle.org/downloads)
- [IntelliJ IDEA - Optional](https://spring.io/guides/gs/intellij-idea/) - The Gradle plugin provided is idea but can
Eclipse can be added.

## Running the tests

`gradle test` runs the test. It creates the test results artifacts in `{projectFolder}/build/reports/tests/test/`.

`gradle test jacocoTestReport` runs the test coverage report. It creates the code coverage report in
`{projectFolder}/build/reports/jacoco/test/html/`.

## Accessing the Spring Boot REST API

The REST API is built with Spring Boot. To build and start the Spring Boot server use:

`gradle build && java -jar DeckOfCardsAPI/build/libs/deck-of-cards-api-0.1.0.jar`

The endpoints can then be accessed via curl:

```
curl localhost:8080/api/v1/deck/dealOneCard
curl localhost:8080/api/v1/deck/shuffle
curl localhost:8080/api/v1/deck/reset
```

## Documentation

The documentation included is the CRC model, Class Diagram, and Activity Diagram. This is in the Documentation folder.
In addition, JavaDoc is included and can be generated using `gradle javadoc`. The documentation will be created at
`{projectFolder}/build/docs/javadoc/`.

## License

MIT: http://rem.mit-license.org

## Acknowledgments

Thanks to the Appian Engineering team for giving me the opportunity to work on this project. 
