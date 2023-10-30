# User Guide

## Introduction

QuizHUB is a local desktop app designed to help NUS students easily record examinable questions and generate quizzes
from their very own question bank to test their understanding via a Command Line Interface (CLI). Easily launch-able
on the go, QuizHUB is a versatile tool that aims to streamline and optimize the revision experience for NUS students
from all fields of study.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `QuizHub` from [here](http://link.to/duke).

## Features

1. Helper command
2. Add question/answer and group them in modules/difficulty
3. Stores the question/answer in local storage
4. Delete question/answer
5. Find question/answer via question's description or via module name
6. Edit question/answer
7. Mark question difficulty
8. Start a quiz session, specifying category of questions and in random/normal mode
9. Permanently shuffle questions order for added challenge!

### Getting help on using the app: `help`
Assists new users in familiarising with the application. Provides a list of commands for the user to get started
with the essentials.

Format: `help`

Example of usage:
* **Command**:`help` <br>
  **Output**: <br>
`Here are the list of commands you can use:` <br>
`1. help - shows the list of commands you can use,` <br>
`2. short [question]/[answer]/[module]/[difficulty] - adds a short answer question and its answer to the list,` <br>
`3. list - shows the list of questions and answers,` <br>
`4. delete [question number] - deletes the question and answer at the specified number,` <br>
`5. find /[description] - displays all questions that contains the specified description,` <br>
`6. find /[module] - displays all questions that belong to the specified module,` <br>
`7. edit [question number] /description [description] - edits the description of the question with the specified number,` <br>
`8. edit [question number] /answer [answer] - edits the answer to the question with the specified number,` <br>
`9. start /[quiz mode] [start details] /[qn mode] - starts the quiz with option for /module or /all and /random or /normal,` <br>
`10. shuffle - shuffle quiz questions to a random order,` <br>
`11. markdiff [question number] /[question difficulty] - sets the difficulty of question with the specified number,` <br>
`12. bye - exits the program` <br>

Notes:
* Ver 2.0 will only list commands. Future versions will include auto-filling and scrolling
  (multiple pages of the help menu)

### Adding short-answer questions and their answers: `short`
Adds a new short question and its answer to the question and answer bank along with the assigned module and
difficulty level.

Format: `short [question]/[answer]/[module]/[difficulty]`

Example of usage:
* **Command**: `add What is the value of Pi to 2 decimal places?/3.14/math/math` <br>
  **Output**: <br>
  `I have added the following question OwO:` <br>
  `[S] What is the value of Pi to 2 decimal places? / 3.14 | math | EASY` <br>
  `Now you have [no. of questions] questions in the list! UWU` <br> <br>
* **Command**: `add What fish is Nemo based off?/a Clownfish/trivia/easy` <br>
  **Output**: <br>
  `I have added the following question OwO:` <br>
  `[S][] What fish is Nemo based off? / a Clownfish | trivia | EASY` <br>
  `Now you have [no. of questions] questions in the list! UWU` <br> 

Notes:
* If either the question or answer is left blank, the app will prompt you to re-enter the question-answer set,
  the app does not support blank questions/answers

### List Questions `list`
List all the questions from the question and answer bank in the storage.

Format: `list`

Examples of usage:
* **Command**: `list` <br>
  **Output**:<br>
  1: `[S][] 2 / 4 | num | EASY` <br>
  2: `[S][] hi / hihi | word | EASY` <br>
  3: `[S][] 6 / 12 | num | EASY` <br>

### Delete Questions `delete`
Deletes the question with the specified question number from the question and answer bank.

Format:
`delete [qustion number]`

Example of usage:
* **Command**: `delete 2` <br>
  **Output**: <br>
  `Roger that! I have deleted the following question >w< !` <br>
  `[s][] [question description] / [ansewer] | [module] | [quesiton difficulty]` <br>
  `Now you have [no. of questions] questions in the list! UWU` <br>

Notes:
* The program only supports deleting one question at a time, so entering multiple question numbers will result in the program prompting you to enter only 1 question number.
* The program accepts only a valid integer question number within the range of the number of available questions, any other form of inputs will result in the program prompting you to re-enter a valid command.

### Find question/answer `find`
Finds and displays all questions in the question and answer bank that match the criteria (question description / answer) and keywords of the search.

Format:
1. `find /description [question description]`
2. `find /answer [answer]`

Examples of usage:
* **Command**: `find /description example description` <br>
  **Output**: <br>
  `Here are questions that matched your search:` <br>
  `1: [S][] EXAMPLE Description / [answer] | [module] | [question difficulty]` <br>
  `2: [S][] example description1 / [answer] | [module] | [question difficulty]` <br>
  `3: [S][] example description 2/ [answer] | [module] | [question difficulty]` <br> <br>
* **Command**: `find /answer example answer` <br>
  **Output**: <br>
  `Here are questions that matched your search:` <br>
  `1: [S][] [question description] / EXAMPLE Answer | [module] | [question difficulty]` <br>
  `2: [S][] [question description] / example answer1 | [module] | [question difficulty]` <br>
  `3: [S][] [question description] / example answer 2 | [module] | [question difficulty]` <br> <br>

Notes:
* As long as the search keyword string is a substring of the question description / answer of a question stored in the question and answer bank, it will be included in the search result. Please refer to the above examples as an illustration of this property.
* The search is non case-sensitive. Please refer to the above examples as an illustration of this property.

### Edit question/answer `edit`
Edits the description or answer of an existing question in the question and answer bank
by referencing the number of the question in the existing question list in the question
and answer bank.

Format:
1. `edit [question number] /description [newDescription]`
2. `edit [question number] /answer [newAnswer]`

Examples of usage:
* **Command**: `edit 1 /description change description!!!` <br>
  **Output**:  <br>
  `Roger that! I have edited the following question >w< !` <br>
  `[S][] change description!!! / [original answer] | [module] | [question difficulty]` <br>
  `Now you have [no. of questions] questions in the list! UWU` <br> <br>
* **Command**: `edit 2 /answer different answer???` <br>
  **Output**: <br>
  `Roger that! I have edited the following question >w< !` <br>
  `[S][] [original description] / different answer??? | [module] | [question difficulty]` <br>
  `Now you have [no. of questions] questions in the list! UWU` <br> <br>

Notes:
* If either the question number is invalid, or no new description or answer is provided,
  the app will prompt you to re-enter the edit command
* The app only supports changing either description or answer at a time, not both together

### Start quiz `start`
Starts a quiz which fetches the specified category from the question bank in normal / random order to be tested.
The app will display the fetched questions one at a time on CLI and the user is required to enter answer through CLI.
Upon every user answer entry, the app feedbacks if the user answered correctly, loads the next question and updates
the score. When all questions are answered, the quiz terminates automatically and the final user quiz score is displayed.

Format:  `start /[quiz mode] [start details] /[qn mode]`

Examples of usage:
* **Command**: `start /module num /normal` <br>
  **Output**: starts the quiz - displays questions that pertains to module “num” in order defined in the list
* **Command**: `start /module num /random` <br>
  **Output**: starts the quiz - displays questions that pertains to module “num” but in a randomised order
* **Command**: `start /all /random` <br>
  **Output**: starts the quiz - displays questions directly from the list of questions in random order
* **Command**: `start /all /normal` <br>
  **Output**: starts the quiz - displays questions directly from the list of questions in the order of the list


Notes:
* If the specified module is not part of the list, an exception will be thrown and the user will be notified
  with a prompt to write the command in the specified format.

### Shuffling Questions `shuffle`
Shuffle questions within question list to a random order,

Format: `shuffle`

Notes
* Shuffle will not work if there are no questions within the question bank

### Mark difficulty of questions `markdiff`
Changes the difficulty level of an existing question in the question and answer bank
by referencing the number of the question in the existing question list in the question
and answer bank.

Format: `markdiff [question number] [question difficulty]`

Examples of usage:
* **Command**: `markdiff 1 /easy` <br>
  **Output**:  <br>
  `Roger that! I have marked the following question as easy >w< !` <br>
  `[S][] [original description] / [original answer] | [module] | EASY` <br> <br>
* **Command**: `markdiff 2 /Hard` <br>
  **Output**: <br>
  `Roger that! I have marked the following question as hard >w< !` <br>
  `[S][] [original description] / [original answer] | [module] | HARD` <br> <br>

Notes:
* If either the question number is invalid, or no difficulty level is provided,
  the app will prompt you to re-enter the markdiff command
* The app only supports fixed difficulty levels, assigning any difficulty level that
  is not `easy`, `normal`, or `hard` will trigger a warning message
* Input difficulty level is CAPS-friendly

### Exit Program `bye`
Exits the program
Format: `bye`

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: Transfer the tasklist.txt generated to the same local directory as the jar file of the program in another
computer.

## Command Summary

1. `help` - shows the list of commands you can use
2. `short [question]/[answer]/[module]/[difficulty]` - adds a short answer question and its answer to the list,
3. `list` - shows the list of questions and answers,
4. `delete [question number]` - deletes the question and answer at the specified number,
5. `find /[description]` - displays all questions that contains the specified description,
6. `find /[module]` - displays all questions that belong to the specified module,
7. `edit [question number] /description [description]` - edits the description of the question with the specified number,
8. `edit [question number] /answer [answer]` - edits the answer to the question with the specified number,
9. `start /[quiz mode] [start details] /[qn mode]` - starts the quiz with option for /module or /all and /random or
   /normal,
10. `shuffle` - shuffle quiz questions to a random order,
11. `markdiff [question number] [question difficulty]` - sets the difficulty of question with the specified number,
12. `bye` - exits the program
