## James Hong Jey - Project Portfolio Page

### Project QuizHub
QuizHUB is a local desktop app designed to help NUS students easily record examinable questions and generate quizzes
from their very own question bank to test their understanding via a Command Line Interface (CLI). Easily launch-able
on the go, QuizHUB is a versatile tool that aims to streamline and optimize the revision experience for NUS students
from all fields of study.

## Summary of Contributions

[Link to RepoSense](https://nus-cs2113-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=James-Hong-Jey&tabRepo=AY2324S1-CS2113-W12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false())

### Project Management
* Facilitated the initial restructuring of the skeleton code to suit the QuizHub requirements
  [#8](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/8)
* Bug fixing and conforming to checkstyle
  [#76](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/76),
  [#88](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/88)

### New Feature 1: `CommandShortAnswer`
[#9](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/9)

- **What:** Enables users to create a new question of type Short Answer
- **Justification:** Core component of QuizHub, in order to allow users to create 
their own quizzes and benefit from the application.

### New Feature 2: `CommandFind`
[#51](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/51)

- **What:** Enables users to search for questions matching a certain description or module
- **Justification:** Users may have many questions and being able to locate specific questions
may help their learning.

<div style="page-break-after: always;"></div>

### New Feature 3: `CommandShuffle`
[#51](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/51)

- **What:** Enables users to permanently shuffle the order of questions
- **Justification:** When looking at questions using the `list` feature or the `start` normal setting,
the questions will be sorted based on the date created, which might not be ideal for revision. 
The shuffle feature hence provides the option to randomise it instead.

### New Feature 4: `CommandMultipleChoice`
[#147](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/147),
[#206](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/206)

- **What:** Enables users to create a new question of type Multiple Choice
- **Justification:** This enables the option for a more streamlined, accessible question type
in addition to the short answer questions. MCQ also allows the user to test for common mistakes.


### Code Testing
[#32](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/32),
[#76](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/76),
[#88](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/88)

Wrote multiple JUnit test cases and assertions for the features implemented above.

### User Guide Contributions
[#70](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/70)

Responsible for editing these sections:
* [`short`](https://ay2324s1-cs2113-w12-1.github.io/tp/UserGuide.html#features)
* [`find`](https://ay2324s1-cs2113-w12-1.github.io/tp/UserGuide.html#features)
* [`shuffle`](https://ay2324s1-cs2113-w12-1.github.io/tp/UserGuide.html#features)
* [Frequently Asked Questions](https://ay2324s1-cs2113-w12-1.github.io/tp/UserGuide.html#faq)

<div style="page-break-after: always;"></div>

### Developer Guide Contributions 
[#55](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/55),
[#70](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/70),
[#72](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/72),
[#130](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/130),
[#223](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/223)

Responsible for writing these sections:
* [System Architecture](https://ay2324s1-cs2113-w12-1.github.io/tp/DeveloperGuide.html#design--implementation)
* [Command Overview](https://ay2324s1-cs2113-w12-1.github.io/tp/DeveloperGuide.html#command-component)
* [Command Short Answer](https://ay2324s1-cs2113-w12-1.github.io/tp/DeveloperGuide.html#command-component)
* [Command Multiple Choice](https://ay2324s1-cs2113-w12-1.github.io/tp/DeveloperGuide.html#command-component)
* [Command Shuffle](https://ay2324s1-cs2113-w12-1.github.io/tp/DeveloperGuide.html#command-component)
* [Command Find](https://ay2324s1-cs2113-w12-1.github.io/tp/DeveloperGuide.html#command-component)

Also created and standardised the object diagrams for most of the Commands, 
and created the sequence diagram for the Command overview.

### Community Contributions
 * Reviewing other teams' Developer Guides 
 * Submitted 9 bugs during [PE-D](https://github.com/James-Hong-Jey/ped)