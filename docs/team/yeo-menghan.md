## Yeo Meng Han - Project Portfolio Page

### Project QuizHub
QuizHUB is a local desktop app designed to help NUS students easily record examinable questions and generate quizzes
from their very own question bank to test their understanding via a Command Line Interface (CLI). Easily launch-able
on the go, QuizHUB is a versatile tool that aims to streamline and optimize the revision experience for NUS students
from all fields of study.

## Summary of Contributions
- **Code Contributions:** [Link to reposense contribution](https://nus-cs2113-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=yeo-menghan&tabRepo=AY2324S1-CS2113-W12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false).
- Contributed extensively to CommandStart and CommandMCQ
- Spearheaded the user guide documentation
- Documentation for CommandEdit, CommandStart, CommandShuffle, CommandMarkDiff and CommandExit

### New Feature 1: `CommandStart`
Provided users to start a quiz
- Enhancement 1: Sequential quiz mode [Issue #47](https://github.com/AY2324S1-CS2113-W12-1/tp/issues/47)
- Enhancement 2: Random quiz mode [Issue #46](https://github.com/AY2324S1-CS2113-W12-1/tp/issues/46)
- Enhancement 3: MCQ / Short Ans / Mixed mode [Issue #198](https://github.com/AY2324S1-CS2113-W12-1/tp/issues/198)
- Enhancement 4: Exit Quiz [Issue #205](https://github.com/AY2324S1-CS2113-W12-1/tp/issues/205)
- Enhancement 3: Refactored StartQuiz in QuestionList

### New Feature 2: `CommandMultipleChoice`
Provide users with an alternative form of quizzing - MCQs
- Enhancement 1: Integrate MCQs in CommandStart [Issue #146](https://github.com/AY2324S1-CS2113-W12-1/tp/issues/146)
- Bug Fix 1: Fix referencing answer value instead of referencing answer index [Issue #208](https://github.com/AY2324S1-CS2113-W12-1/tp/issues/208)

### New Feature 3: `Logger Class`
[Issue #34](https://github.com/AY2324S1-CS2113-W12-1/tp/issues/34)
Created the logger class to provide easy debugging

### Feature Enhancement 1: `Duplication Check in ShortAns`
- Prevent duplication of question/answer pair in Short Answer input
[Issue #109](https://github.com/AY2324S1-CS2113-W12-1/tp/issues/109)

### Code Testing
- Introduced a system of test storage, test UI and test command meant for CommandStart for the team (later abstracted out by sizhuang)

#### CommandStart Test [Issue #33](https://github.com/AY2324S1-CS2113-W12-1/tp/pull/33)
- Test AddQuestionToListAndStorage 
- Test StartQuizWithQuestions 
- Test StartQuizWithNoQuestions
- Handed over to Juncheng for further implementation

#### CommandShuffle Test [Issue #91](https://github.com/AY2324S1-CS2113-W12-1/tp/issues/91)
- Test Order of questions will change
- Test Shuffle maintains size of the list
- Test Shuffle on empty list will throw exception

#### CommandShortAns Test [Issue #93](https://github.com/AY2324S1-CS2113-W12-1/tp/issues/93)
- Test Invalid command throw
- Test Missing & Additional fields
- Test Duplication handling
- Test Invalid difficulty
- Test Loading from file and existence of storage file
- Test Deletion of Short Ans in storage

### User Guide Contributions
- Created skeleton for the user guide for team to fill in 
- Written feature list & [cheatsheet](https://ay2324s1-cs2113-w12-1.github.io/tp/UserGuide.html#command-summary)
- Wrote user guide for [help command](https://ay2324s1-cs2113-w12-1.github.io/tp/UserGuide.html#getting-help-on-using-the-app-help), [edit command](https://ay2324s1-cs2113-w12-1.github.io/tp/UserGuide.html#edit-questionanswer-edit), [start command](https://ay2324s1-cs2113-w12-1.github.io/tp/UserGuide.html#start-quiz-start),
[shuffle command](https://ay2324s1-cs2113-w12-1.github.io/tp/UserGuide.html#shuffling-questions-shuffle), [markdiff command](https://ay2324s1-cs2113-w12-1.github.io/tp/UserGuide.html#mark-difficulty-of-questions-markdiff)

### Developer Guide Contrbutions
Provided description, class structure (UML diagrams) and implementation details for:
- [CommandEdit](https://ay2324s1-cs2113-w12-1.github.io/tp/DeveloperGuide.html#edit-command---edit-question--answer)
- [CommandStart](https://ay2324s1-cs2113-w12-1.github.io/tp/DeveloperGuide.html#start-command---start-quiz)
- [CommandShuffle](https://ay2324s1-cs2113-w12-1.github.io/tp/DeveloperGuide.html#shuffle-command)
- [CommandMarkDiff](https://ay2324s1-cs2113-w12-1.github.io/tp/DeveloperGuide.html#markdiff-command---mark-difficulty-of-entry) 
- [CommandExit](https://ay2324s1-cs2113-w12-1.github.io/tp/DeveloperGuide.html#class-structure-of-command-exit)

### Team-Base Contributions
- Refactored code to general code enhancements
- Performed code abstraction in questionlist and ui such as `ui.displayMessage` and `ui.displayMessageSameLine` to ensure consistent abstraction level of code
- Help team to setup gradle checkstyle and test requirements
- Assisted in issue tracker maintenance to ensure team members are assigned to the right issues and avoid potential work conflict


### Community Contributions
**Reviewing other team's UG and software program** [Reported total of 18 bugs during PED (top 10%)](https://github.com/yeo-menghan/ped/tree/main/files)