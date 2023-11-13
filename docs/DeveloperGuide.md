# Developer Guide

## Acknowledgements

+ Acknowledgement to the [CS2113 Website](https://nus-cs2113-ay2324s1.github.io/website/admin/tp-deliverables.html#deliverable-project-portfolio-page-ppp) for giving us design guidelines.
+ Acknowledgement to [Dr Akshay Narayan](https://www.comp.nus.edu.sg/cs/people/anarayan/), and Teaching Assistant for their guidance.
+ Acknowledgement to [team members](AboutUs.md) for their hard work.
+ Acknowledgement to the [diagram tool](https://app.diagrams.net) for facilitating drawing of diagrams
+ Acknowledgement to [Developer Guide Example](https://se-education.org/addressbook-level3/DeveloperGuide.html#acknowledgements) for illustration.

## Design & implementation

### Design & Architecture

Given below is a quick overview of main components and how they interact with each other.

![High-level Design of TaskLinker](photo/OverallDesign.png)

**Duke components of the architecture**

The two main classes of Duke are FlashcardComponent and CalendarManager.
Those two classes carry out the bulk of the application. 

Duke prompts for user input. Duke also accesses and uses FlashcardComponent
and CalendarManager. So, when the user input is given, duke decided which one
to call based on the input.

As the input is processed and called by one of the two main classes, 
subsequent methods and features are called by children classes.

### `flashcard` package

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
- [`command` package](https://github.com/AY2324S1-CS2113-F11-3/tp/tree/master/src/main/java/seedu/duke/flashcard/command):
  Contains classes representing the different kinds of commands (`list 
  flashcards`, `create flashcard` etc.).
- [`exceptions` package](https://github.com/AY2324S1-CS2113-F11-3/tp/tree/master/src/main/java/seedu/duke/flashcard/exceptions):
  Contains classes representing custom exceptions that are specific to the 
  `flashcards` package.
- [`review` package](https://github.com/AY2324S1-CS2113-F11-3/tp/tree/master/src/main/java/seedu/duke/flashcard/review):
  Contains classes representing the different flashcard review modes (random 
  mode and spaced repetition mode).

See this high-level overview of the classes involved in providing the 
flashcard functionality:

![class diagram of classes providing flashcard functionality](Diagrams/flashcard-diagrams/overview_classes.svg)

This sequence diagram illustrates how they work together to process user 
input and execute the corresponding command:




### Storage Components

API: `FlashcardStorage.java`

![Flashcard Storage Sequence Diagram](photo/FlashcardStorage-0.png)

The `FlashcardStorage` component,
* can creates data directory and flashcards directory.
* can save flashcards in `flashcard.txt` in specific text format
* saves automatically when changes occur.
* component loads automatically when the program starts.  
* component can check if the saved txt file has been damaged.

`EventStorage` has similar structure. (It was omitted to avoid redundancy.)


### Calendar Components

API: `CalendarManager.java`

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
[Calendar package Class Diagram](photo/CalendarManagerClassDiagram.drawio.png)

CalendarManager Sequence Diagram:
[CalendarManager Sequence Diagram](photo/CalendarManagerSequenceDiagram.drawio.png)

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

### Testing launching and exiting the application
Launching the application
  1. download the jar file from the release page.
  2. open a terminal and navigate to the directory where the jar file is located.
  3. run the command `java -jar TaskLinker.jar`

Exiting the application
  1. type `exit` in the command box and press enter.
  2. the application will exit.

#### Testing adding an event to the calendar

Test Case #1 (Everything Works):

<pre>
Enter your command: <b>add event</b>
What's the event?: <b>Do HW</b>
When does it start?: <b>2023-12-20T12:30:30</b>
When does it end?: <b>2023-12-20T13:40:30</b>
</pre>

Test Case #2 (Start Time, End Time Error):

<pre>
Enter your command: <b>add event</b>
What's the event?: <b>Do HW</b>
When does it start?: <b>2023-12-20T12:30:30</b>
When does it end?: <b>2023-12-20T11:40:30</b>

  End time is before or equal to the start time. Please enter the correct end time.
</pre>

#### Testing adding a goal event to the calendar

Test Case #1 (Everything Works):

<pre>
Enter your command: <b>add goal event</b>
What's the event?: <b>Do Flashcards</b>
When does it end?: <b>2023-12-20T12:30:30</b>

Goal 'Do Flashcards' review 20 flashcards by: 2023-12-20T12:30:30 (Reviewed: 0) 
has been added to your Calendar
</pre>

Test Case #2 (Not an integer for # of flashcards):

<pre>
Enter your command: <b>add goal event</b>
What's the event?: <b>Do Flashcards</b>
When does it end?: <b>2023-12-20T12:30:30</b>
How many flashcard to review by then?: <b>r</b>

    Invalid integer input. Please try again.
</pre>

#### Testing deleting an event from the calendar

Test Case #1 (Everything Works):

<pre>
Enter your command: <b>delete event</b>
What's the event?: <b>hello</b>
    hello has been deleted from your Calendar!
</pre>

Test Case #2 (Incomplete command):

<pre>
Enter your command: <b>delete</b>
☹ OOPS!!! The description of a delete cannot be empty.
    Invalid integer input. Please try again.
</pre>

#### Testing finding an event from the Calendar

Test Case #1 (Everything Works):

<pre>
Enter your command: <b>find event</b>
What's the event?: <b>Do HW</b>
1. Event 'Do HW' From: 2023-12-20T12:30:30, To: 2023-12-20T13:30:30
    These events have been found
</pre>

Test Case #2 (Incomplete Command):

<pre>
Enter your command: <b>find</b>
☹ OOPS!!! The description of a find cannot be empty.
    Invalid integer input. Please try again.
</pre>

#### Testing listing all events from the Calendar

Test Case #1 (Everything Works):

<pre>
Enter your command: <b>list events</b>
    Here is a list of all your events: 
--------------------------------------------------------------------------------
1. Event 'Do User' From: 2023-12-20T12:30:30, To: 2023-12-20T13:30:30
--------------------------------------------------------------------------------
</pre>

Test Case #2 (Incomplete command):

<pre>
Enter your command: <b>list</b>
☹ OOPS!!! The description of a list cannot be empty.
    Invalid integer input. Please try again.
</pre>
