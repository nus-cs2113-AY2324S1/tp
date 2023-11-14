# Ong Choon Kit - Project Portfolio Page

## Project Overview
Welcome to 🪙NUScents🪙, the tailor-made financial tracker for SOC students at NUS. It is optimized for use via a
Command Line Interface (CLI) to offer a clutter-free solution for our users to manage and monitor their financial
activities. The project is written in Java, consisting of approximately 3k lines of code (LoC).

## Project Contributions

### Code Contributed

Explore my code contributions to the team project on [RepoSense](https://nus-cs2113-ay2324s1.github.io/tp-dashboard/?search=choonkit-nus&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-09-22&tabOpen=false).

### Implemented Enhancements

- **New Feature**: Net Balance
    - Added the functionality to calculate and show net balance based on the total existing allowance and expense transactions.

- **New Feature**: Budget Feature
    - Added the functionality to set a budget. Once the budget is set, it helps to reflect whether the total expenses are within or exceeding the budget set.

- **New Feature**: Persistent Storage for Budget
    - Reading and writing of the budget amount to the `budget.txt` file. It ensures that the budget is persistently stored and can be retrieved when needed.

- **Enhancement to Existing Feature**: Update transaction amount to use float value
    - To use positive float values for allowance and expense transaction amount instead of integers. Added exceptions to further ensure this is followed.

- **Bugfix**: Incorrect `transactionList` count
    - Fixed the bug where `count` is not accounted for properly and incremented when transactions are initialized from the `nuscents.txt` file.
    - Refer to GitHub issue [#111](https://github.com/AY2324S1-CS2113-T18-4/tp/issues/111).

- **Bugfix**: Arbitrary float values that cause unexpected response
    - Added a check to the `parseAllowance()`, `parseExpense()` and `parseBudget()` methods in `Parser.java` to ensure that the following conditions are met for the user entered amount. The allowance, expense and budget amount is positive, less than 100000, and has at most two decimal places. This ensures that arbitrary large or small float amounts are rejected and not used.
    - Refer to GitHub issues
      [#88](https://github.com/AY2324S1-CS2113-T18-4/tp/issues/88),
      [#94](https://github.com/AY2324S1-CS2113-T18-4/tp/issues/94),
      [#101](https://github.com/AY2324S1-CS2113-T18-4/tp/issues/101),
      [#107](https://github.com/AY2324S1-CS2113-T18-4/tp/issues/107),
      [#113](https://github.com/AY2324S1-CS2113-T18-4/tp/issues/113),
      [#119](https://github.com/AY2324S1-CS2113-T18-4/tp/issues/119),
      [#121](https://github.com/AY2324S1-CS2113-T18-4/tp/issues/121).

### Contributions to the User Guide (UG)
- Assisted with generating some of the examples referred to in the UG

### Contributions to the Developer Guide (DG)
- Design Section: Documented the main core components (based on the Architecture Diagram) of the project and the interaction flow between them.
- Implementation of Net Balance (within list transaction feature)
- Implementation of Budget Feature
- Product Scope: Documented the target user profile and value proposition sections
- Added the Non-Functional Requirements

### Contributions to team-based tasks
- Helped to set the GitHub issue tracker & assigned the respective issues related to the v2.0 milestone.