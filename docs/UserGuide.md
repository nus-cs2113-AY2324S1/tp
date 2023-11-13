# User Guide

## Introduction

QuizHUB is a local desktop app designed to help NUS students easily record examinable questions and generate quizzes
from their very own question bank to test their understanding via a Command Line Interface (CLI). Easily launch-able
on the go, QuizHUB is a versatile tool that aims to streamline and optimize the revision experience for NUS students
from all fields of study.

## Table of Contents

1. [List of All Features](#features)
2. [Frequently Asked Questions](#faq)
3. [Summary of CLI Command Format](#command-summary)

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `QuizHub` from [here](https://github.com/AY2324S1-CS2113-W12-1/tp/releases).
3. Familiarize yourself with the command structure used [here](#command-usage)

## Command Usage
1. All the commands are listed in the [Features](#features) section, 
   alternatively, the summary is available at [Command Summary](#command-summary) section
2. Some commands are single-word commands that do not accept parameters, such as
   the [Help](#getting-help-on-using-the-app-help) command. In this case, any arguments
   you may have entered would be ignored
   * Entering `help abc` would perform the same function as `help`
3. The `[]` in the commands denote a placeholder in the command which you should replace 
   with corresponding values.
   * `[question]` would indicate that you should replace the placeholder with a question
   * Specific requirements of the values would be found in the details in [Features](#features) section

<div style="page-break-after: always;"></div>

## Features

1. [Helper command](#getting-help-on-using-the-app-help)
2. [Add Short Answer question with modules and difficulty](#adding-short-answer-questions-and-their-answers-short)
3. [Add MCQ question with modules and difficulty](#adding-multiple-choice-questions-and-their-answers-mcq)
4. [Store or Edit the question/answer in local storage](#store-and-edit-question-pool-using-the-question-file)
5. [Delete question/answer](#delete-questions-delete)
6. [Find question/answer via question's description or via module name](#find-questionanswermodule-find)
7. [Edit question/answer](#edit-questionanswer-edit)
8. [Mark question difficulty](#mark-difficulty-of-questions-markdiff)
9. [Start a quiz session, specifying category of questions and in random/normal mode](#start-quiz-start)
10. [Permanently shuffle questions order for added challenge!](#shuffling-questions-shuffle)

### Getting help on using the app: `help`

[Back to Table of Contents](#table-of-contents)

Assists new users in familiarising with the application. Provides a list of commands for the user to get started
with the essentials.

Format: `help`

Example of usage:
* **Command**:`help` <br>
  **Output**: The list of commands, similar to the [Command Summary](#command-summary) <br>

Notes:
* Ver 2.1 will only list commands. Future versions will include autofill and scrolling
  (multiple pages of the help menu)

<div style="page-break-after: always;"></div>

### Adding short-answer questions and their answers: `short`

[Back to Table of Contents](#table-of-contents)

Adds a new short question and its answer to the question and answer bank along with the assigned module and
difficulty level.

Format: `short [question]/[answer]/[module]/[difficulty]`
* The difficulty level should only be of `easy`, `normal`, and `hard`
* The "/" character should be replaced "\slash" keyword instead.
* The "pipe" character is not allowed and will be removed if present in any fields.
* The answer cannot be exactly "\exitquiz" as it is an escape sequence

Example of usage:
* **Command**: `short What is the value of Pi to 2 decimal places?/3.14/math/easy` <br>
  **Output**: <br>
  ```
  I have added the following question OwO:
  [S][ ] What is the value of Pi to 2 decimal places? / 3.14 | math | EASY
  Now you have 1 questions in the list! UWU
  ```
* **Command**: `short What is 0.5 in fraction?/1\slash2/fraction/easy` <br>
  **Output**: <br>
  ```
  I have added the following question OwO:
  [S][ ] What is 0.5 in fraction? / 1/2 | fraction | EASY
  Now you have 2 questions in the list! UWU
  ```

Notes:
* If any fields are blank or invalid, the app will prompt you to re-enter the question-answer set,
  the app does not support blank fields

<div style="page-break-after: always;"></div>

### Adding multiple-choice questions and their answers: `mcq`

[Back to Table of Contents](#table-of-contents)

Adds a new mcq question and its answer to the question and answer bank along with the assigned module and
difficulty level.

Format: `mcq [question]/[option 1]/[option 2]/[option 3]/[option 4]/[answer index]/[module]/[difficulty]`

* The difficulty level should only be of `easy`, `normal`, and `hard`
* The "/" character should be replaced "\slash" keyword instead.
* The "pipe" character is not allowed and will be removed if present in any fields.

* **Command**: `mcq what is the value of pi?/2.71/9.81/3.14/0/3/math/Easy` <br>
  **Output**: <br>
  ```
  I have added the following question OwO:
  [M][ ] what is the value of pi? / 2.71 / 9.81 / 3.14 / 0 / 3 | math | EASY
  Now you have 3 questions in the list! UWU
  ```
* **Command**: `mcq is 5 \slash 3 rational?/yes/no/maybe/all of the above/1/fraction/easy` <br>
  **Output**: <br>
  ```
  I have added the following question OwO:
  [M][ ] is 5 / 3 rational? / yes / no / maybe / all of the above / 1 | fraction | EASY
  Now you have 6 questions in the list! UWU
  ```

<div style="page-break-after: always;"></div>

### List Questions `list`

[Back to Table of Contents](#table-of-contents)

List all the questions from the question and answer bank in the storage.

Format: `list`

Examples of usage:
* **Command**: `list` <br>
  **Output**:<br>
 ```
  1: [S][ ] What is the value of Pi to 2 decimal places? / 3.14 | math | EASY
  2: [S][ ] What is 0.5 in fraction? / 1/2 | fraction | EASY
  3: [M][ ] what is the value of pi? / 2.71 / 9.81 / 3.14 / 0 / 3 | math | EASY
  4: [M][ ] is 5 / 3 rational? / yes / no / maybe / all of the above / 1 | fraction | EASY
  ```

### Delete Questions `delete`

[Back to Table of Contents](#table-of-contents)

Deletes the question with the specified question number from the question and answer bank.

Format:
`delete [question number]`

Example of usage:
* **Command**: `delete 4` <br>
  **Output**: <br>
  ```
  Roger that! I have deleted the following question >w< ! 
      [M][ ] is 5 / 3 rational? / yes / no / maybe / all of the above / 1 | fraction | EASY
  Now you have 3 questions in the list! UWU
  ```

Notes:
* The program only supports deleting one question at a time, so entering multiple question numbers will result in the program prompting you to enter only 1 question number.
* The program accepts only a valid integer question number within the range of the number of available questions, any other form of inputs will result in the program prompting you to re-enter a valid command.

<div style="page-break-after: always;"></div>

### Find question/module `find`

[Back to Table of Contents](#table-of-contents)

Finds and displays all questions in the poll that match the criteria, 
either in the question field (with the `/description` keyword), 
or the module field (with the `/module` keyword) but not both. You may use partial matches.

Format: 
1. `find /description [question description]` Find a question by its question
2. `find /module [question module]` Find a question by its module
3. The "/" character is not allowed should be replaced "\slash" keyword instead.

Examples of usage:
* **Command**: `find /description Pi` <br>
  **Output**: <br>
  ```
  Here are questions that matched your search:
  1: [S][ ] What is the value of Pi to 2 decimal places? / 3.14 | math | EASY
  3: [M][ ] what is the value of pi? / 2.71 / 9.81 / 3.14 / 0 / 3 | math | EASY
  ```
* **Command**: `find /module fraction` <br>
  **Output**: <br>
  ```
    Here are questions that matched your search:
    2: [S][ ] What is 0.5 in fraction? / 1/2 | fraction | EASY
  ```

Notes:
* As long as the search keyword string is a substring of the question description of a question stored in the question and answer bank, it will be included in the search result. Please refer to the above examples as an illustration of this property.
* The search is non-case-sensitive. Please refer to the above examples as an illustration of this property.
* Ver 2.1 does not support finding by answer. 

<div style="page-break-after: always;"></div>

### Edit question/answer/option `edit`

[Back to Table of Contents](#table-of-contents)

Edits the description or answer of an existing question in the question and answer bank
by referencing the number of the question in the existing question list in the question
and answer bank.

Format:
1. Use `edit [question number] /description [newDescription]` to edit description
2. Use `edit [question number] /answer [newAnswer]` to edit answers
3. Use `edit [question number] /option[1...4] [newAnswer]` to edit a choice of MCQ question
4. The "/" character should be replaced "\slash" keyword instead
5. The "pipe" character is not allowed and will be removed if present in any fields

Examples of usage:
* **Command**: `edit 1 /description What is the value of Pi to 3 decimal places?` <br>
  **Output**:  <br>
  ```
      [S][ ] What is the value of Pi to 3 decimal places? / 3.14 | math | EASY
    Roger that! I have edited the following question >w< !
  ```
* **Command**: `edit 1 /answer 3.142` <br>
  **Output**: <br>
  ```
      [S][ ] What is the value of Pi to 3 decimal places? / 3.142 | math | EASY
    Roger that! I have edited the following question >w< !
  ```

* **Command**: `edit 3 /option1 2.713` <br>
  **Output**: <br>
  ```
      [M][ ] what is the value of pi? / 2.713 / 9.81 / 3.14 / 0 / 3 | math | EASY
    Roger that! I have edited the following question >w< !
  ```

Notes:
* If either the question number is invalid, or no new description or answer is provided,
  the app will prompt you to re-enter the edit command
* The app only supports changing either description, answer, or one option at a time, not both together

<div style="page-break-after: always;"></div>

### Start quiz `start`

[Back to Table of Contents](#table-of-contents)

Starts a quiz which fetches the specified category from the question bank in normal / random order to be tested.
The app will display the fetched questions one at a time on CLI and the user is required to enter answer through CLI.
Upon every user answer entry, the app feedbacks if the user answered correctly, loads the next question and updates
the score. When all questions are answered, the quiz terminates automatically and the final user quiz score is displayed.

Format:  `start /[quiz mode] [start details] /[qn mode] /[qn type]`
* Quiz Mode - Use `all` for all modules, or `module` with the corresponding module in `start details` for a selected module.
* Qn Mode - Use `normal` for standard sequence or `random` for random sequence
* Qn Type - Use `short` for `mcq` for short and mcq types, or `mix` for all types

Examples of usage:
* **Command**: `start /module num /normal /mix` <br>
  **Output**: starts the quiz - displays questions that pertains to module “num” in order defined in the list
* **Command**: `start /module num /random /short` <br>
  **Output**: starts the quiz - displays short answer questions that pertains to module “num” but in a randomised order
* **Command**: `start /all /random /mcq` <br>
  **Output**: starts the quiz - displays mcq questions directly from the list of questions in random order
* **Command**: `start /all /normal /mix` <br>
  **Output**: starts the quiz - displays questions directly from the list of questions in the order of the list

Taking the quiz:
* **Short Answer Questions**: Enter the exact case-insensitive answer and press enter
* **Multiple Choice Questions**: Enter the index for the correct answer
* **Terminating the Quiz**: Enter "\exitquiz" in any questions


Notes:
* If the specified module is not part of the list, an exception will be thrown and the user will be notified
  with a prompt to write the command in the specified format.

<div style="page-break-after: always;"></div>

### Shuffling Questions `shuffle`

[Back to Table of Contents](#table-of-contents)

Shuffle questions within question list to a random order,

Format: `shuffle`

**Output**:
```
    Questions are now shuffled!
    1: [S][ ] What is 0.5 in fraction? / 1/2 | fraction | EASY
    2: [M][ ] is 5 / 3 rational? / yes / no / maybe / all of the above / 1 | fraction | EASY
    3: [S][ ] What is the value of Pi to 2 decimal places? / 3.14 | math | EASY
    4: [M][ ] what is the value of pi? / 2.71 / 9.81 / 3.14 / 0 / 3 | math | EASY
```

Notes
* Shuffle will not work if there are no questions within the question bank

<div style="page-break-after: always;"></div>

### Mark difficulty of questions `markdiff`

[Back to Table of Contents](#table-of-contents)

Changes the difficulty level of an existing question in the question and answer bank
by referencing the number of the question in the existing question list in the question
and answer bank.

Format: `markdiff [question number] /[question difficulty]`

Examples of usage:
* **Command**: `markdiff 1 /easy` <br>
  **Output**:  <br>
  ```
  Roger that! I have marked the following question as easy >w< !
      [S][ ] What is 0.5 in fraction? / 1/2 | fraction | EASY
  ```
* **Command**: `markdiff 2 /Hard` <br>
  **Output**: <br>
  ```
  Roger that! I have marked the following question as hard >w< !
      [M][ ] is 5 / 3 rational? / yes / no / maybe / all of the above / 1 | fraction | HARD
  ```
Notes:
* If either the question number is invalid, or no difficulty level is provided,
  the app will prompt you to re-enter the mark difficulty command
* The app only supports fixed difficulty levels, assigning any difficulty level that
  is not `easy`, `normal`, or `hard` will trigger a warning message
* Input difficulty level is **not case-sensitive**

### Exit Program `bye`

[Back to Table of Contents](#table-of-contents)

Exits the program <br>
Format: `bye`

<div style="page-break-after: always;"></div>

### Store and Edit Question Pool Using The Question File

[Back to Table of Contents](#table-of-contents)

Storage:
* The storage operation is done automatically without the need for user input
* Any operation that updates the question pool will invoke the storage operation

Editing:
* You may directly update the storage file instead of adding questions with commands
* You should edit the `questionlist.txt` file in the working directory of the `QuizHub.jar` file
* If the `questionlist.txt` is not already present, you should run the program once for initialization
* You can edit the file in the format as described below.

Format:
* File header: The first line of the file should not be changed, questions will
begin from the second line onwards, with each question taking one line
* Short Answer Question: `S | <done/undone> | <question> / <answer> | <module> | <difficulty> `
  * S - Format identifier for short answer
  * done/undone - Use done or undone as indication
  * question/answer - Question and answer
  * module - Module for the question
  * difficulty - EASY, NORMAL or HARD
* Multiple Choice Question: `M | <done/undone> | <question> / <ans1> / <ans 2> / <ans 3> / <ans 4> / <correct ans> | <module> | <difficulty>`
  * M - Format identifier for multiple choice question
  * done/undone - Use done or undone as indication
  * question/ans1/ans2/ans3/ans4 - Questions and choices
  * correct ans - Index of correct answer from 1 through 4
  * Module - Module for the question
  * Difficulty - EASY, NORMAL or HARD
* Using arbitrary values may prevent the question from being loaded normally,
  please use the add question feature in the program if you are unsure.
* You may find a sample of the storage file in the next page

<div style="page-break-after: always;"></div>

Example of storage file:
```
Latest Questions
S | undone | Is Java Fully OOP? / yes | CS2113 | NORMAL
S | undone | How many different STs are there in a complete graph with 9 vertices? / 4782969 | CS2040C | HARD
S | undone | Does one-time-pad satisfy perfect secrecy / Yes | CS3235 | EASY
M | undone | derivative of x^2 / x^2 / 2x / x / x^3 / 2 | MA1521 | EASY
M | undone | Should we use arrowhead code / yes / no / maybe / unsure / 2 | CS2113 | EASY
M | undone | Name of Java mascot / duke / duck / dick / drew / 1 | CS2113 | EASY
```

<div style="page-break-after: always;"></div>

## FAQ

[Back to Table of Contents](#table-of-contents)

**Q**: How do I transfer my data to another computer?

**A**: Transfer the `questionlist.txt` generated to the same local directory as the .jar file of the program in another
computer. The .jar file should be in its own directory.

**Q**: Why does my `questionlist.txt` look slightly different from when I run `list`?

**A**: The `questionlist.txt` generated is not originally designed to be seen by 
the user. The different format is used in order to parse questions without error.

**Q**: What's the difference between shuffle and /random mode in `start`?

**A**: During user testing, it was found that users sometimes will 
revise by looking at all the questions with `list`, as such functionality to 
shuffle the questions permanently was added.

<div style="page-break-after: always;"></div>

## Command Summary

[Back to Table of Contents](#table-of-contents)

1. `help` - shows the list of commands you can use
2. `short [question]/[answer]/[module]/[difficulty]` - adds a short answer question and its answer to the list
3. `mcq [question]/[option 1]/[option 2]/[option 3]/[option 4]/[answer index]/[module]/[difficulty]` - 
 adds a multiple-choice question and its answer to the list
4. `list` - shows the list of questions and answers
5. `delete [question number]` - deletes the question and answer at the specified number
6. `find /description [description]` - displays all questions containing the specified description
7. `find /module [module]` - displays all questions that belong to the specified module
8. `edit [question number] /description [description]` - edits the description of the question with the specified
   number
9. `edit [question number] /answer [answer]` - edits the answer to the question with the specified number
10. `edit [question number] /option[number] [new value]` - edits the option of the question with the specified number (
    MCQ only)
11. `start /[quiz mode] [start details] /[qn mode] /[qn type]` - starts the quiz with option for /module or /all and /random or
    /normal mode, with MCQ, short answer or mixed question types
12. `shuffle` - shuffle quiz questions to a random order
13. `markdiff [question number] /[question difficulty]` - sets the difficulty of question with the specified number
14. `bye` - exits the program