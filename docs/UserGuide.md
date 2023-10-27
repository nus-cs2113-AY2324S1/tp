# User Guide

## Introduction

QuizHUB is a local desktop app designed to help NUS students easily record examinable questions and generate quizzes 
from their very own question bank to test their understanding via a Command Line Interface (CLI). Easily launchable 
on the go, QuizHUB is a versatile tool that aims to streamline and optimize the revision experience for NUS students 
from all fields of study.

## Quick Start

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `QuizHub` from [here](http://link.to/duke).

## Features 

1. help - shows the list of commands you can use
2. short [question]/[answer]/[module]/[difficulty] - adds a short answer question and its answer to the list,
3. list - shows the list of questions and answers,
4. delete [question number] - deletes the question and answer at the specified number,
5. find /[description] - displays all questions that contains the the specified description,
6. find /[module] - displays all questions that belong to the specified module,
7. edit [question number] /description [description] - edits the description of the question with the specified number,
8. edit [question number] /answer [answer] - edits the answer to the question with the specified number,
9. start /[quiz mode] [start details] /[qn mode] - starts the quiz with option for /module or /all and /random or 
/normal,
10. shuffle - shuffle quiz questions to a random order,
11. markdiff [question number] [question difficulty] - sets the difficulty of question with the specified number,
12. bye - exits the program

### Getting help on using the app `help`
Assist new users in getting to speed with the application. Provide a list of commands for the user to get started 
with the essentials. An instance of /help will be activated at the start. /help with a specific command thereafter will 
provide greater details on how to utilize that command.

Format: `help`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

* Command:`help` <br>
Output: /add - … /start - …

Notes:
* If the command does not exist after /help, the app will prompt you that the command does not exist and thereafter 
activate /help to list all the commands
* Ver 1.0 will only list commands. Future versions will include auto-filling and scrolling 
(multiple pages of the help menu)

### Adding questions and their answers to bank `short`
Adds a new short question and its answer to the question and answer bank along with the assigned module and
difficulty level.

Format: `short [question]/[answer]/[module]/[difficulty]`

Examples:
* Command: add What is the value of Pi to 2 decimal places?/3.14/math/easy <br>
Output: Question-Answer set added to bank!
* Command: add What fish is Nemo based off?/a Clownfish/trivia/easy <br>
Output: Question-Answer set added to bank!

Notes:
* If either the question or answer is left blank, the app will prompt you to re-enter the question-answer set, 
the app does not support blank questions/answers

### List Questions `list`
List all the questions from the question bank from the storage

Format: `list`

Examples:
* Command: list <br>
Output:<br>
1: [S][] 2 / 4 | num | EASY <br>
2: [S][] hi / hihi | word | EASY <br>
3: [S][] 6 / 12 | num | EASY <br>

### Delete Questions `delete`

### Find question/answer `find`


### Edit question/answer `edit`

### Start quiz `start`
Starts a quiz which fetches the specified category from the question bank in normal / random order to be tested. 
The app will display the fetched questions one at a time on CLI and hte user is required to enter answer through CLI. 
Upon every user answer entry, the app loads the next question and updates the score. When all questions are answered, 
the quiz terminates automatically and the user quiz score is displayed.

Format:  `start /[quiz mode] [start details] /[qn mode]`

Examples:
* Command: start /module num /normal <br>
Output: start the quiz - displays questions that pertains to module “num” in order defined in the list
* Command: start /module num /random <br>
Output: start the quiz - displays questions that pertains to module “num” but in a randomised order
* Command: start /all /random <br>
Output: start the quiz - displays questions directly from the list of questions in random order
* Command: start /all /normal <br>
Output: start the quiz - displays questions directly from the list of questions in the order of the list


Notes:
* If the specified module is not part of the list, an exception will be thrown and the user will be notified 
with a prompt to write the command in the specified format.

### Shuffling Questions `shuffle`
Shuffle questions within question list to a random order,

Format: `shuffle`

Notes
* Shuffle will not work if there is not questions within the question bank


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
