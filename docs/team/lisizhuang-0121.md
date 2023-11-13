# Li Sizhuang - Project Portfolio Page

## Project: QuizHub
QuizHUB is a local desktop app designed to help NUS students easily record examinable questions and generate quizzes
from their very own question bank to test their understanding via a Command Line Interface (CLI). Easily launch-able
on the go, QuizHUB is a versatile tool that aims to streamline and optimize the revision experience for NUS students
from all fields of study.

## My Contributions
[Link to RepoSense](https://nus-cs2113-ay2324s1.github.io/tp-dashboard/?search=w12&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=lisizhuang-0121&tabRepo=AY2324S1-CS2113-W12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Project Management
* Manage milestones and issues in GitHub tracker
* Outline structure of classes and program flow in the overall design
* Convert user stories to tasks
* Manage code releases

### Features Added
* Initialized the skeleton code from Man Juncheng with all necessary classes 
to allow the team to work on the features [PR #7](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/7/)
* Designed the command parsing structure with inspirations from AddressBook-Level 3 and implemented the
classes necessary for handling invalid commands [PR #16](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/16/)
* Restructures the command parsing structure to improve coherence
[PR #80](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/80/)
* Standardize methods to convert question to Strings [#195](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/195)
* Standardize methods to check answer validity and correctness 
[#221](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/221)

<div style="page-break-after: always;"></div>

### Code Testing and Improvement
* Initialize and maintain Unit testing with Junit
  * Ensure cross-platform compatibility
  * Update test according to features to ensure 100% passing rate
  * Add testing for invalid command handling [PR #29](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/29/)
  * Fix test cases to pass CI checks [PR #89](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/89/)
  * Fix line separator issues in tests [PR #95](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/95/)

* Regularly conduct integration tests with various test cases to find and fix bugs
  * Use equivalence partition, system testing technique and debugging to locate possible bugs
  * Ensure that the user guide complies with the current features
  * Found and fixed bugs in edit command [PR #28](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/28/)
  * Found and fixed bugs in taking quizzes [PR #38](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/38/)
  * Found and fixed logic bugs in editing questions [PR #41](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/41/)
  * Found and fixed bugs in loading from file [PR #61](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/61/)
  * Fixed various bugs found during PE-D [PR #186](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/186),
    [PR #187](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/187),
    [PR #188](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/188)
  * Fix bugs in start quiz command [PR #224](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/224)
* Refactor code to maintain overall code quality
  * Ensure compliance with SE-Guide and checkstyle
  * Focuses on reducing arrowhead code, duplicate code, magic numbers, etc.
  * Fix code style issues [PR #37](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/37/)
  * Abstracted long methods in storage class [PR #60](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/60/)
  * Update user guides and reduce magic numbers [PR #126](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/126)
  * Standardize and remove deprecated names [PR #131](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/131)
  * Abstract displaying of questions to improve code reuse 
  [PR #201](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/201)
  * Improve code quality in question-list class [PR #203](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/203)

### Design Guide Contributions
* Formulated the design guide template
* Formatted and added user stories
* Added diagrams for overall execution of the program

### Community Contributions
* Reported bugs and suggested fixes during the practical exam dry run
