# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

== Design

.Sequence Diagram of Parser Parsing User Input
.component interactions for any user command
image::ParserParseCommand.png[]
*API*: link:{repoURL}/src/main/java/seedu/cafectrl/parser/Parser.java

When user input a string to `Main`,  it passes the full user input to `Parser` via `parseCommand`. In parseCommand, Parser
will find the matching keyword for different command from the user input, then it calls the respective `prepareCommand` 
method within `Parser`. `prepareCommand` generates the corresponding command class and return it to `parseCommand`, which
returns the `Command` back to `Main` for execution.

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
