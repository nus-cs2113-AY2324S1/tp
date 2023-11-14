---
layout: page
title: Chung Zhi Xuan's Project Portfolio Page
---
### Project: NUScents

Welcome to 🪙NUScents🪙, the tailor-made financial tracker for SOC students at NUS. It is optimized for use via a 
Command Line Interface (CLI) to offer a clutter-free solution for our users to manage and monitor their financial 
activities. It is written in Java and has about 3kLoC.

Given below are my contributions to the project.

* **New Feature**: *Add list transactions command*
    * What it does: *Lists all the transactions in NUScents.*
    * Justification: *The `list` command was refactored from Spaceman Task Manager's implementation to make modifications on `Transaction` objects and its fields*
    * Credits: *Spaceman Task Manager, which NUScents' `list` feature was refactored from*


* **New Feature**: *Add feature to categorise expenses*
    * What it does: *Categorise expenses in NUScents.*
    * Justification: *Possible categories include `FOOD`, `ENTERTAINMENT`, `TRANSPORTATION`, `UTILITY`, `RENT`, 
  `OTHER_EXPENSE`.*


* **Bug fix**: *Ensure program detects invalid date*
  * Ensure a correct error message is displayed when an invalid date is provided by the user.


* **Code Contributed**: [RepoSense Link](https://nus-cs2113-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=spaceman03&tabRepo=AY2324S1-CS2113-T18-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


* **Project management & Contrubutions to team-based tasks**:
    * Imported code base from [Spaceman Task Manager](https://github.com/spaceman03/ip/) during the initial setup
    * Setup issues and milestones for v1.0


* **Enhancements to existing features**:
    * Display only certain length of the description when a long description is provided (Pull request [\#138](https://github.com/AY2324S1-CS2113-T18-4/tp/pull/138))


* **Documentation**:
    * User Guide:
        * Created a Table of Contents 
        * Created Quick Start section to guide new users (Pull request [\#59](https://github.com/AY2324S1-CS2113-T18-4/tp/pull/59))
        * Added a Command Summary section (Pull request [\#59](https://github.com/AY2324S1-CS2113-T18-4/tp/pull/59))
        * Added useful notes to guide new users
    * Developer Guide:
        * Added architecture diagram for NUScents (Pull request [\#65](https://github.com/AY2324S1-CS2113-T18-4/tp/pull/65))
        * Added implementation details for `add` transaction feature (Pull request [\#45](https://github.com/AY2324S1-CS2113-T18-4/tp/pull/45))
        * Documented the Ui component and the Data component
        * Added class diagram for the Data component
        * Added user stories for v1.0 and v2.0


* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#22](https://github.com/AY2324S1-CS2113-T18-4/tp/pull/22), [\#36](https://github.com/AY2324S1-CS2113-T18-4/tp/pull/36), [\#58](https://github.com/AY2324S1-CS2113-T18-4/tp/pull/36)
    * Reported bugs and suggestions for other teams in the class (Issues: [\#84](https://github.com/AY2324S1-CS2113-F11-3/tp/issues/84), [\#109](https://github.com/AY2324S1-CS2113-F11-3/tp/issues/109), [\#111](https://github.com/AY2324S1-CS2113-F11-3/tp/issues/111), [\#113](https://github.com/AY2324S1-CS2113-F11-3/tp/issues/113))


* **Tools**:
    * Used PlantUML for drawing UML diagrams for the DG
