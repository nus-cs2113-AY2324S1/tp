# User Guide

## Introduction

TaskLinker is a CLI-tool for helping students memorize flashcards and track 
their flashcard and generell academic progress in the courses they are taking.

## Quick Start

1. Ensure that you have Java 11 or above installed.
1. Down the latest jar from [the latest release on GitHub]
   (https://github.com/AY2324S1-CS2113-F11-3/tp/releases).
1. Run the jar via `java -jar duke.jar`

## General explanation of flashcards

## Features

### Listing all flashcards: `list flashcards`

Prints out a list of all flashcards.

Format: `list flashcards`

### Adding a todo: `todo`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.

Example of usage:

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

### Creating a new flashcard: `create flashcard`

Starts the process of adding a new flashcard.

After entering this command, you are prompted to first input the front page 
text (once you have typed it out, submit it by pressing <ENTER>) and then the 
back page text (once you have typed it out, submit it by pressing <ENTER>) of 
your new flashcard.

After you've done this, a success message will be printed out. This 
indicates that a new flashcard has been successfully created and saved.

Format: `create flashcard`

### Reviewing your flashcards: `review flashcards`

Starts the process of reviewing flashcard.

After entering this command, you are prompted to select your exact review 
mode from 3 choices:

- `random mode`: Randomly selects flashcards to review
- `spaced repetition mode`: Which flashcards are shown depends on how well 
  you previously knew them. Flashcards which you couldn't remember well are 
  shown more often, while flashcards which you remembered well are shown 
  less often.
- `review by tag mode`: Randomly selects flashcards with a certain tag to review

Input `a` to choose `random mode`, input `b` to choose `spaced repetition 
mode` and input `c` to choose `review by tag mode`.

Once you've selected a review mode, the actual review begins: the front page 
of a flashcard is shown to you. You should now try and think of the answer 
(the text on the back page of the flashcard); and once you're ready, press 
<ENTER> to compare it to the actual back page.

Now, the process repeats and the next flashcard is shown to you.

If you want to quit the review process, simply input `q` or `quit` instead 
of pressing <ENTER> to reveal the back page.

Format: `create flashcard`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

* List all flashcards: `list flashcards`
* Create a new flashcard: `create flashcard`
* Review your flashcards: `review flashcards`
