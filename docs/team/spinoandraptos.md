## Man Juncheng - Project Portfolio Page

### Project: QuizHub
QuizHUB is a local desktop app designed to help NUS students easily record examinable questions and generate quizzes
from their very own question bank to test their understanding via a Command Line Interface (CLI). Easily launch-able
on the go, QuizHUB is a versatile tool that aims to streamline and optimize the revision experience for NUS students
from all fields of study.

### Summary of Contributions
- **Code Contributions:** [Link to reposense contribution](https://nus-cs2113-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=spinoandraptos&tabRepo=AY2324S1-CS2113-W12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false).
- The project structure of QuizHub is adapted from Juncheng's [ip](https://github.com/spinoandraptos/ip). Juncheng is
  responsible for migrating the skeleton code structure into the team repository and aligning the team for the initial
  integration cycle. [#6](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/6)
### New Feature 1: `Edit Question`
  [#10](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/10),
  [#20](https://github.com/AY2324S1-CS2113-W12-1/tp/issues/20)
  - **What:** Enables users to edit an existing question's question or answer for short answer questions
  - **Justification:** Users may have accidentally typed the question or answer incorrectly, or question answer needs
    to be updated following a change in syllabus
### Enhancement 1: `Reinforcement of Edit Question Command`
  [#113](https://github.com/AY2324S1-CS2113-W12-1/tp/issues/113),
  [#125](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/125)
  - **What:** Performs checks to ensure an `edit` command does not have excessive arguments and payloads, and handles
    much more comprehensive types of edge cases for user input
  - **Justification:** Ensures the program does not crash due to erroneous user commands to facilitate a 
    smoother experience for users under the stress of exam revisions
### New Feature 2: `Question Difficulty`
  [#57](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/57),
  [#45](https://github.com/AY2324S1-CS2113-W12-1/tp/issues/45)
  - **What:** Enables users to assign or change the difficulty level to a question
  - **Justification:** Allows users to identify questions which may require more practise to assist them in their revision
### Enhancement 2: `Reinforcement of Mark Difficulty Command`
  [#114](https://github.com/AY2324S1-CS2113-W12-1/tp/issues/114),
  [#125](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/125)
  - **What:** Performs checks to ensure a `markdiff` command does not have excessive arguments and payloads, and handles
  much more comprehensive types of edge cases for user input
  - **Justification:** Ensures the program does not crash due to erroneous user commands to facilitate a
  smoother experience for users under the stress of exam revisions
### New Feature 3: `Sort Question List by Module`
  [#59](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/59)
  - **What:** Enables users to find all questions belonging to specified modules and build a question list comprising
    solely of these questions
  - **Justification:** Provides users with the flexibility to attempt questions targeted towards only modules they wish
    to further revise on, facilitating higher studying efficiency
### Enhancement 3: `Parser Methods Abstraction`
  [#128](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/128),
  [#122](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/122),
  [#105](https://github.com/AY2324S1-CS2113-W12-1/tp/issues/105)
  - **What:** Broke down methods for parsing long and complicated user commands like `start` and `edit` into multiple
      short and abstracted methods which manage the parsing and exception handling of different segments of the user input
  - **Justification:** Parsing long and complicated user commands in a single method results in excessively long methods
    which not only violates OOP principles but also makes debugging challenging
### Code Testing and Improvement:
  [#125](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/125),
  [#101](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/101),
  [#62](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/62),
  [#36](https://github.com/AY2324S1-CS2113-W12-1/tp/issues/36),
  [#24](https://github.com/AY2324S1-CS2113-W12-1/tp/issues/24),
  [#92](https://github.com/AY2324S1-CS2113-W12-1/tp/issues/92)
  * Performed JUnit testing for commands to identify error-prone user input combinations
  - Ensured JUnit tests cover user input formats that are prone to bugs for the following commands:
    * CommandEdit
    * CommandList
    * CommandMarkDifficulty
    * CommandStart
### User Guide Contributions:
  Apart from proofreading the document, Juncheng added documentation for the following sections of the user guide: <br/>
    - [Edit Question Command](https://ay2324s1-cs2113-w12-1.github.io/tp/UserGuide.html#edit-questionanswer-edit) <br/>
    - [Mark Question Difficulty Command](https://ay2324s1-cs2113-w12-1.github.io/tp/UserGuide.html#mark-difficulty-of-questions-markdiff)
### Developer Guide Contributions:
  Juncheng added documentation and diagrams for the following sections of the developer guide, focusing on
  readability and simplicity: <br/>
    - [Overall Class Architecture Diagram](https://ay2324s1-cs2113-w12-1.github.io/tp/UML/Images/overallClassInteraction.png)<br/>
    - [Application Lifecycle diagram](https://ay2324s1-cs2113-w12-1.github.io/tp/UML/Images/applicationLifecycle.png) (adapted from Sizhuang's original diagram) <br/> 
    - [Parser Documentation](https://ay2324s1-cs2113-w12-1.github.io/tp/DeveloperGuide.html#parser-component) <br/>
    - [Parser Sequence Diagram](https://ay2324s1-cs2113-w12-1.github.io/tp/UML/Images/parser.png) <br/>
    - [Non-functional Requirements](https://ay2324s1-cs2113-w12-1.github.io/tp/DeveloperGuide.html#non-functional-requirements) <br/>
    - [Glossary](https://ay2324s1-cs2113-w12-1.github.io/tp/DeveloperGuide.html#glossary) <br/>

### Team-Based Task Contributions:
  - Helped to standardise the style of all UML diagrams through the creation of a `Styles.puml` template file which is applied
    to all UML diagrams created using PlantUML extension
  - Refactored code for general code enhancements
    * Performed code abstraction in `parser` class to avoid deep nesting and excessively long methods
    * Broke down methods which parse long and complicated user commands, such as `edit` and `start` into distinct 
    shorter methods that handles different segments of the user input and different exceptions
    * Performed code abstraction for methods that involve string literals and printing ui feedback to avoid 
    hard-coding and repeating string literals which is prone to errors
    * Refactored code to meet checkstyle requirements
  - Assisted in issue tracker maintenance to ensure team members are assigned to the right issues and avoid 
  potential work conflict

- ### Community Contributions
    - **Reviewing other team's UG and software program**: [Reported a total of 18 bugs for T17-3 (Top 10%)](https://github.com/spinoandraptos/ped)