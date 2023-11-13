# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

### `flashcard` package

#### Package structure overview 

The API of the `flashcard` package is defined in [`FlashcardComponent.java`](https://github.com/AY2324S1-CS2113-F11-3/tp/blob/master/src/main/java/seedu/duke/flashcard/FlashcardComponent.java).

The flashcard package is structured into multiple parts:

- [`Flashcard.java`](https://github.com/AY2324S1-CS2113-F11-3/tp/blob/master/src/main/java/seedu/duke/flashcard/Flashcard.java):
  Represents a single flashcard with front text, back text as well as 
  its unique id and current difficulty level.
- [`FlashcardCommandParser.java`](https://github.com/AY2324S1-CS2113-F11-3/tp/blob/master/src/main/java/seedu/duke/flashcard/FlashcardCommandParser.java):
  Parses user inputs into the corresponding commands.
- [`FlashcardComponent.java`](https://github.com/AY2324S1-CS2113-F11-3/tp/blob/master/src/main/java/seedu/duke/flashcard/FlashcardComponent.java):
  Encapsulates all functionality of the `flashcard` package and exposes it 
  in one single, unified API.
- [`FlashcardDirectory.java`](https://github.com/AY2324S1-CS2113-F11-3/tp/blob/master/src/main/java/seedu/duke/flashcard/FlashcardDirectory.java):
  Helper class needed for storing flashcards after TaskLinker has been 
  exited; see [the DG section about storage components](#storage-components).
- [`FlashcardList.java`](https://github.com/AY2324S1-CS2113-F11-3/tp/blob/master/src/main/java/seedu/duke/flashcard/FlashcardList.java):
  Container class for a list of flashcards. Exposes a simple, unified API 
  for dealing with a list of flashcards.
- [`FlashcardUi.java`](https://github.com/AY2324S1-CS2113-F11-3/tp/blob/master/src/main/java/seedu/duke/flashcard/FlashcardUi.java):
  Responsible for interfacing with the user: dispatches commands to be 
  executed and shows their output to the user.
- [`flashcard.command` package](https://github.com/AY2324S1-CS2113-F11-3/tp/tree/master/src/main/java/seedu/duke/flashcard/command):
  Contains classes representing the different kinds of commands (`list 
  flashcards`, `create flashcard` etc.).
- [`flashcard.exceptions` package](https://github.com/AY2324S1-CS2113-F11-3/tp/tree/master/src/main/java/seedu/duke/flashcard/exceptions):
  Contains classes representing custom exceptions that are specific to the 
  `flashcards` package.
- [`flashcard.review` package](https://github.com/AY2324S1-CS2113-F11-3/tp/tree/master/src/main/java/seedu/duke/flashcard/review):
  Contains classes representing the different flashcard review modes (random 
  mode and spaced repetition mode).

This class diagram provides a high-level overview of how the classes in the 
top-level `flashcard` package integrate with each other:

![class diagram of classes providing flashcard functionality](Diagrams/flashcard-diagrams/overview_classes.svg)

#### Rough control flow overview

The process of processing the initial user input and figuring out which 
command to  execute based on this user input is handled by the 
`FlashcardComponent`, `FlashcardCommandParser` and `FlashcardUi` classes.

During their operation, they create an instance of the appropriate 
`FlashcardCommand` (from the `flashcard.command` package) and then execute 
it; thereby performing the action the user wanted.

Put into a sequence diagram flow, the above-mentioned workflow looks like this:

![sequence diagram of processing the list flashcards input](Diagrams/flashcard-diagrams/overview_sequence.svg)

##### `flashcard.command` package

The `flashcard.command` package contains the classes representing the 
different flashcard commands, e.g. the `ListFlashcardCommand` class 
represents the "list flashcards" command and so on.

![class diagram of flashcard.command package](Diagrams/flashcard-diagrams/command_package_classes.svg)

### Storage Components

API: `FlashcardStorage.java`

![Flashcard Storage Sequance Diagram](photo/FlashcardStorage-0.png)

The `FlashcardStorage` component,
* can save flashcards in `flashcard.txt` in specific text format
* saves automatically when changes occur.
* component loads automatically when the program starts.  
* component can check if the saved txt file has been damaged.

`EventStorage` has same structure.


### Calendar Components

#### Command Package

+ The package has 7 files in it for users to command their calendar. Those files are 
  AddEventCommand, DeleteAllEventsCommand, DeleteEventCommand, EventCommand, FindEventCommand
  ListEventCommand, and UnknownCommand. The other 6 commands extend EventCommand, which is
  an abstract class that forces other 6 commands to have an execute method.


+ Each command files execute its own commands. The UnknownCommand file handles the exceptions,
  such as if the user commands something that doesn't exist

#### Calendar Package

+ The calendar package excluding the command package has another 8 files.
  The empty calendar class is reserved for adding future implementations.
  The CalendarManager, EventStorage, & CalendarCommandParser manages user input, saves, and loads them.
  The classes are associated with one another through instant accesses and other means. The 
  CalendarManager directs the events and event list, which then are run on Duke.

## Product scope

### Target user profile

TaskLinker is tailored towards university students who use flashcards to 
study for their courses and need an easy way to schedule and plan the 
studying of their flashcards.

TaskLinker is a CLI tool and as such, it is tailored towards students who 
type fast and prefer a functional, but bare-bones app that runs in the 
terminal over a GUI that looks more impressive but is slower to use. 

As such, computer science students represent good target users of TaskLinker.

### Value proposition

TaskLinker is a CLI-tool for helping university students memorize flashcards
and track their flashcard progress as well as general academic progress in
the courses they are taking.

## User Stories

| Version | As a ...                  | I want to ...                                                  | So that I can ...                                                                             |
|---------|---------------------------|----------------------------------------------------------------|-----------------------------------------------------------------------------------------------|
| v1.0    | user                      | Delete event from my Calendar                                  | remove unnecessary events from the Calendar                                                   |
| v1.0    | user                      | Add event to my Calendar                                       | create new necessary events on my Calendar                                                    |
| v1.0    | user                      | List all events in my Calendar                                 | keep track of all my events and deadlines                                                     |
| v1.0    | user                      | list all my flashcards                                         | get a quick overview of my flashcard collection                                               |
| v1.0    | user                      | create new flashcards                                          | add new knowledge I want to study                                                             |
| v1.0    | user                      | review my flashcards                                           | so that I can memorize their contents                                                         |
| v2.0    | new user                  | be able to invoke a help command                               | get advice/tips for how to use TaskLinker if I don' know how                                  |
| v2.0    | user reviewing flashcards | rate how hard it was to remember their content                 | see harder flashcards more often and easy flashcards less frequently during reviews           |
| v2.0    | experienced user          | use the delete and review feature by typing a one-line command | be faster than having to go through the prompt-answer workflow these features usually require |
| v2.0    | user                      | find events from my Calendar                                   | find events without listing them all                                                          |
| v2.0    | user                      | delete all events from my Calendar                             | don't need to remove events one by one                                                        |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
