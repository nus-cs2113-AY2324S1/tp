# Cai Ting's Portfolio 

## Project: WildWatch
Welcome aboard fellow animal lover! We are really excited to have you here! 🐋
Wildwatch is a program for a clerk managing wildlife data in a wildlife reserve via the Command Line Interface (CLI).

---

## Summary of contributions 

### Code contributed: [link](https://nus-cs2113-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=lctxct&tabRepo=AY2324S1-CS2113T-W11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancements implemented:
* `add` command 
* interactive `add` command
* `export` command
* `FileCommand`, which abstracts out the process of saving entries to files
* Helped to enhance skeleton with some abstract classes / interfaces: `Command`, `Parser`
* Extended existing `InvalidInputException` to allow for more robust error handling. Through overloading, it is possible to make use of `InvalidInputErrorType`s so that error messages common to multiple commands may share an error message, else it is also possible to define custom error messages.
* `AddCommandParserTest` 

### Contributions to team-based tasks
* Helped with refactoring and decoupling of initial code for increased maintainability and ease of contribution. This was not an easy task, as by the time I had started looking at the code many of the components were already cemented and quite complex. It took me a full weekend to both thoroughly understand how parts of the code linked together + figure out how best to decouple the components to ensure that I would be able to preserve parts of code already written by my teammates as much as possible, and would not heavily affect ongoing development. In the end, changes I made allowed for error-handling to be settled by-command, allowing for more fine-grained error messages and hence a better user experience. 
* Created issues for v2.0 so that there'll be less chance of overlapping efforts and everyone has the chance to contribute by assigning themselves to an issue.
* Consolidated PE-D issues into one place, noting duplicate problems, so that it's easier to track and resolve issues (e.g. [link1](https://github.com/AY2324S1-CS2113T-W11-2/tp/issues/181), [link2](https://github.com/AY2324S1-CS2113T-W11-2/tp/issues/191)).

### Review/mentoring contributions 
* Helped with testing and created issues for bugs found (e.g. [link](https://github.com/AY2324S1-CS2113T-W11-2/tp/issues/115))

### Contributions beyond the project team
* Participated in the code review of other team's DG in tutorial 
* Helped to spot issues in other team's code during PE-D

### Contributions to the UG
* Created initial markdown skeleton 
* Created initial command reference table 
* `add` command
* `export` command
* Helped to review the UG 

### Contributions to the DG
* Contributed `AddSequenceDiagram.puml`
![AddSequenceDiagram](https://github.com/AY2324S1-CS2113T-W11-2/tp/assets/70379887/c3beaaca-ff23-4d09-95b1-30bd88b5997c)

