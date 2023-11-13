# Bang Junhyeong - Project Portfolio Page

## Overview

TaskLinker is a CLI-tool for helping university students memorize flashcards and track their flashcard and general academic progress in the courses they are taking.

### Summary of Contributions

Given below are my contributions to the project.

#### Code contribution
- You can find the code contribution [here](https://nus-cs2113-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=junhyeong0411&tabRepo=AY2324S1-CS2113-F11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false).
- Codes for storage and directory
- Codes for part of calendar and flashcard which is related to storage
- Added javadoc for storage and directory classes/functions

#### Features
1. **Save Flashcards** : Automatically saves the changed flashcard data whenever the user enters the command.
2. **Load Flashcards** : Load the saved flashcards file when program starts
3. **Save Events** : Automatically saves the changed event data whenever the user enters the command.
4. **Load Events** : Load the saved events file when program starts
5. **Make Directories** : When user first start the program, creates the data directories
6. **Clean up wrong files** : If the saved file has been corrupted, deletes it and make a new file

#### Enhancements implemented
- **Storage**
  - **What it does**:  It automatically saves the data during the program runs. When the User restarts the program, it automatically retrieves the data. Data is stored along a specific format as a txt file. If someone forcibly changes the data and goes against the format, notify the user and create a new file.
  - **Justification**: The storage function is essential because the User cannot always turn on the program. Now, users can edit their flashcard and events lists at any time. In addition, the process of inspecting data files is essential because damage may occur in the process of moving or opening them.
  - **Enhancements**: In v1.0, only flashcards were saved, and the format was simple. In v2.0, events are also saved, and flashcard and events directory were splited. Also, the saving format kept changed because the structure of flashcard and events were changed according to the degree of implementation.
  - **Highlights**: It was difficult to maintain the developement of storage code because the format of the stored data had to be continuously changed. 

#### Contributions to the UG
[User Guide](https://ay2324s1-cs2113-f11-3.github.io/tp/UserGuide.html)
- Contributed for Q&A session about storage
- Gave an explanation how to move the data to other computers

#### Contributions to the DG:
[Developer Guide](https://ay2324s1-cs2113-f11-3.github.io/tp/DeveloperGuide.html)
- Described about FlashcardStorage and EventStorage classes.
- Added Sequance Diagram for FlashcardStorage

#### Contributions to team-based tasks
- Participated weekly team meeting
- Found errors in teammates' code and let them know
- Found errors in teammates' code and fixed it (eg. calculating globalMaxId for flashcard)
- Post some issues about bugs

#### Review/mentoring contributions
- Reviewed some PRs and added comments
- Summarized and delivered the TA's instructions for the team members who couldn't hear it.
