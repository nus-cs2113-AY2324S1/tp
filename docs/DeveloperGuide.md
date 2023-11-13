# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

### flashcard package

The API of the flashcard package is defined in [FlashcardComponent.java](https://github.com/AY2324S1-CS2113-F11-3/tp/blob/master/src/main/java/seedu/duke/flashcard/FlashcardComponent.java).

The flashcard package is structured into multiple parts:

- [Flashcard.java](https://github.com/AY2324S1-CS2113-F11-3/tp/blob/master/src/main/java/seedu/duke/flashcard/Flashcard.java)
- [FlashcardCommandParser.java](https://github.com/AY2324S1-CS2113-F11-3/tp/blob/master/src/main/java/seedu/duke/flashcard/FlashcardCommandParser.java)
- [FlashcardComponent.java](https://github.com/AY2324S1-CS2113-F11-3/tp/blob/master/src/main/java/seedu/duke/flashcard/FlashcardComponent.java)
- [FlashcardDirectory.java](https://github.com/AY2324S1-CS2113-F11-3/tp/blob/master/src/main/java/seedu/duke/flashcard/FlashcardDirectory.java)
- [FlashcardList.java](https://github.com/AY2324S1-CS2113-F11-3/tp/blob/master/src/main/java/seedu/duke/flashcard/FlashcardList.java)
- [FlashcardUi.java](https://github.com/AY2324S1-CS2113-F11-3/tp/blob/master/src/main/java/seedu/duke/flashcard/FlashcardUi.java)
- [command package](https://github.com/AY2324S1-CS2113-F11-3/tp/tree/master/src/main/java/seedu/duke/flashcard/command)
- [exceptions package](https://github.com/AY2324S1-CS2113-F11-3/tp/tree/master/src/main/java/seedu/duke/flashcard/exceptions)
- [review package](https://github.com/AY2324S1-CS2113-F11-3/tp/tree/master/src/main/java/seedu/duke/flashcard/review)



### Storage Components

API: `FlashcardStorage.java`

![Flashcard Storage Sequence Diagram](photo/FlashcardStorage-0.png)

The `FlashcardStorage` component,
* can save flashcards in `flashcard.txt` in specific text format
* saves automatically when changes occur.
* component loads automatically when the program starts.  
* component can check if the saved txt file has been damaged.

`EventStorage` has same structure.


### Calendar Components

#### Command Package

The package has 7 files in it for users to command their calendar. Those files are 
AddEventCommand, DeleteAllEventsCommand, DeleteEventCommand, EventCommand, FindEventCommand
ListEventCommand, and UnknownCommand. The other 6 commands extend EventCommand, which is
an abstract class that forces other 6 commands to have an execute method.


Each command files execute its own commands. The UnknownCommand file handles the exceptions,
such as if the user commands something that doesn't exist

#### Calendar Package

The calendar package excluding the command package has 8 classes.
The Calendar class integrates flashcards and calendar events, allowing for interactions between the 2 packages.
The CalendarManager, EventStorage, & CalendarCommandParser manages user input, saves, and loads them.
The classes are associated with one another through instant accesses and other means. The 
CalendarManager directs the events and event list, which then are run on Duke.

Calendar package Class Diagram:
[Calendar package Class Diagram](photo/EventManager.drawio.png)

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
and track their flashcard and general academic progress in the courses they are
taking. 


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
