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
| v2.0    | user                      | add a goal event to my Calendar                                | remember how many flashcards needs to be reviewed by a certain deadline                       |

## Non-Functional Requirements

+ Software Requirements:
  1. The TaskLinker should be able to run on one of Windows, macOS, or Linux operating systems.
  2. The TaskLinker must be able to run on Java 11 or above Java versions.

+ Technical Requirements:
  1. Users should not manually interact with storage files.
  2. If the input is not in acceptable format, the program shouldn't execute anything
  3. If the correct command is not given, the program should not execute any command.
  4. The application should only work with English language.

+ General Requirements:
  1. The application should not crash on any given exceptions due to exceptions handling.
  2. The dates/time should always be in yyyy-MM-ddTHH:mm:ss format.
  3. The application must be able to support as many events as needed.
  4. The application must be able to support as many flashcards as needed.
  5. The application can be used by anyone who can read and type.


## Glossary

* *TaskLinker* - CLI-tool for helping university students memorize flashcards
  and track their flashcard and general academic progress in the courses they are
  taking.
* *Calendar* - A place where all the events are listed with start and end time.
* *Event* - A task to be done from a given time to end time.
* *Flashcard* - A study tool to memorize a given word.
* *Storage* - A file where the calendar and flashcards are stored.
* *Goal* - A goal to be accomplished by a given date/time on the calendar.
* *Review* - Study flashcards
* *Exception* - Something abnormal that should not happen.
* *UML Diagram* - A diagram where the design of the application is explained.
* *DeveloperGuide* - A guide for other developers to read to understand the application.
* *UserGuide* - A guide for the users  to read to understand the application.

## Instructions for manual testing

*Given below are the instructions for manual testing the TaskLinker.*

### Launching and exiting the application
Launching the application
  1. download the jar file from the release page.
  2. open a terminal and navigate to the directory where the jar file is located.
  3. run the command `java -jar TaskLinker.jar`

Exiting the application
  1. type `exit` in the command box and press enter.
  2. the application will exit.


