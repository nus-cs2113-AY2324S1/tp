# User Guide

## Introduction

TaskLinker is a CLI-tool for helping university students memorize flashcards 
and track their flashcard and general academic progress in the courses they are
taking.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest jar from [the latest release on GitHub](https://github.com/AY2324S1-CS2113-F11-3/tp/releases).
3. Run the jar via `java -jar duke.jar`

## Features

### Flashcard-related features

#### General explanation of flashcards

#### Listing all flashcards: `list flashcards`

Prints out a list of all flashcards that shows each flashcard's front text 
and back text as well as its id and current difficulty level.

Format: `list flashcards`

#### Creating a new flashcard: `create flashcard`

Starts the process of adding a new flashcard.

After entering this command, you are prompted to first input the front page
text (once you have typed it out, submit it by pressing <ENTER>) and then the
back page text (once you have typed it out, submit it by pressing <ENTER>) of
your new flashcard.

After you've done this, a success message will be printed out. This
indicates that a new flashcard has been successfully created and saved.

Format: `create flashcard`

#### Reviewing your flashcards: `review flashcards`

Starts the process of reviewing flashcard.

After entering this command, you are prompted to select your exact review
mode from two choices:

- `random mode`: Randomly selects flashcards to review
- `spaced repetition mode`: Which flashcards are shown depends on how well
  you previously knew them. Flashcards which you couldn't remember well are
  shown more often, while flashcards which you remembered well are shown
  less often.

Input `a` to choose `random mode`, or input `b` to choose `spaced repetition
mode`.

Once you've selected a review mode, the actual review begins: the front page
of a flashcard is shown to you. You should now try and think of the answer
(the text on the back page of the flashcard); and once you're ready, press
<ENTER> to compare it to the actual back page.

In spaced repetition mode, after you have revealed the back page of a 
flashcard, you are prompted to rate how to difficult it was to remember. 
Select `E` if it was easy, `M` if it was moderately hard and `H` if it was 
quite hard. This information is used to adjust the difficulty of the 
flashcard you just reviewed.

Now, the process repeats and the next flashcard is shown to you.

If you want to quit the review process, simply input `q` or `quit` instead
of pressing <ENTER> to reveal the back page.

Format: `create flashcard`

#### Deleting a flashcard

Starts the process of deleting a flashcard.

After entering this command, you are prompted to input the id of the 
flashcard you want to delete. Input it into the terminal and then press enter.

If it was a valid id, the flashcard with that id is deleted; otherwise, an 
error message is shown and you are prompted to retry.

Format: `delete flashcard`

#### Deleting all flashcards: `delete all flashcards`

Deletes all flashcards that you have added so far.

Format: `delete all flashcards`




### General Explanation of Calendar Features

Here is your features list:

+ `add event` , `delete event`
+ `list events` , `find event`
+ `delete all events`

Users can use the above features to handle their events

#### Adding a todo: `todo`

Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.

Example of usage:

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`



#### Add an Event to the Calendar

**Adds an event to the calendar with start and end time** 

#### Usage

+ Format: `add event` [Command]
+ What's the event?: `Event name` [Event name]
+ When does it start?: `yyyy-mm-ddThh:mm:ss` [Start time]
+ When does it end?: `yyyy-mm-ddThh:mm:ss` [End time]

**Example of usage**:

+ `Enter your command: add event`
+ `What's the event?: Do HW`
+ `When does it start?: 2023-12-20T12:30:30`
+ `When does it end?: 2023-12-20T12:40:30`

#### Delete an Event From the Calendar

**deletes an event from the calendar with event name**

#### Usage

+ Format: `delete event` 
+ Enter the event name: `Event name` [Event name]

**Example of usage**:

+ `Enter your command: delete event`
+ `Enter the event name: Do HW`

#### Delete All Events From the Calendar

**deletes all events from the calendar**

#### Usage

+ Format: `delete all events`

**Example of usage**:

+ `Enter your command: delete all events`

#### Find an Event From the Calendar

**finds an event from the calendar**

##### Usage

+ Format: `find event`
+ What event are you looking for?: `event name`

**Example of usage**:

+ `Enter your command: find event`
+ `What event are you looking for?: Do HW`

#### List All Events From the Calendar

**Lists all events from the calendar**

#### Usage

+ Format: `list events`

**Example of usage**:

+ `Enter your command: find event`

## FAQ

**Q**: Where can I find my flashcard and caldendar data?

**A**: You can find it in `data/flashcards` and `data/events` folder.

Every event and flashcard are automatically save after each command.

**Q**: How do I transfer my data to another computer? 

**A**: You can transfer your data by copying & pasting the data folder.

## Command Summary

* List all flashcards: `list flashcards`
* Create a new flashcard: `create flashcard`
* Review your flashcards: `review flashcards`
* Create an event: `add event`
* Delete an event: `delete event`
* Delete all events: `delete all events`
* Find an event: `find event`
* List events: `list events` 
