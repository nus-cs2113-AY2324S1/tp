# Yeon Jeho - Project Portfolio Page

## Overview
This is a document about Jeho's contribution in FitTrack project.

## Summary of Contributions

### Code contributed
* [RepoSense link](https://nus-cs2113-ay2324s1.github.io/tp-dashboard/?search=w12-4&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=ICubE-&tabRepo=AY2324S1-CS2113-W12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
* Main structure
  * `FitTrack`: main class
  * Skeleton of `Ui`, `UserProfile`, `MealList`, and `WorkoutList`
* Parser
  * `CommandParser`
  * `ParseException` and its children
* Data abstraction
  * `Calories`
  * `Height`
  * `Weight`
  * `Date`
  * `Meal`
  * `Workout`
  * `Gender` (partial contribution)
* Basic commands
  * `Command`: abstract parent class for all commands
  * `CommandResult`: class for result of command execution
  * `HelpCommand`
  * `ExitCommand`
  * `InvalidCommand`: not an actual command
* Exception handling
  * Done many parts of exception handling including which teammates missed

### Enhancements implemented
* Added date into `Meal` and `Workout` class
* Abstracted data such as `Calories`, `Meal`, ...

### Contributions to the UG
* Reformatted UG after PED
  * Was messy; format not unified
  * Synced features that has changed

### Contributions to the DG
* Added acknowledgment
* Added core sequence diagrams
* Edited parser component part
* Edited help function part
* Added help function diagram
* Edited handling an invalid input part
* Added sequence of invalid command diagram

### Contributions to team-based tasks
* Maintained [issue tracker](https://github.com/AY2324S1-CS2113-W12-4/tp/issues).
  * Prepared labels and milestones for issues.
  * Transferred user stories to the tracker.
  * Handled all duplicate issues made by PED.
* Managed a release.
  * Released v1.0.
* Fixed bugs reported by PED
  * 22 out of 31

### Review/mentoring contributions
* [Reviewed PRs](https://github.com/AY2324S1-CS2113-W12-4/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3AICubE-)
  * Reviewed other PRs using other methods such as messenger and in person, as well.
* Helped teammates who had a difficulty understanding the code.
