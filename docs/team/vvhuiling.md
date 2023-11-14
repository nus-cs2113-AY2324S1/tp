# Huang Huiling - Project Portfolio Page
## Project: NUScents
### Overview

Welcome to 🪙NUScents🪙, the tailor-made financial tracker for SOC students at NUS. It is optimized for use via a
Command Line Interface (CLI) to offer a clutter-free solution for our users to manage and monitor their financial
activities. It is written in Java and has about 3kLoC.


## Summary of Contributions


### Code Contributed
Explore my code contributions [here](https://nus-cs2113-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=vvhuiling&tabRepo=AY2324S1-CS2113-T18-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancements implemented
* **Feature Addition**: Transaction Viewing
    * **Function**: Enables detailed viewing of specific transactions.
    * **Impact**: implemented the viewCommand class, modified Transaction and TransactionList classes, and implemented Ui.showTransactionViewMessage method, laying the groundwork for future content display functionalities.

* **Feature Addition**: Transaction Filtering
    * **Function**: Displays a list of transactions within a specific category, including their net balance.
    * **Impact**: Developed the TransactionCategory interface, refined ExpenseCategory and AllowanceCategory to resolve conflicts.

* **Enhancement to Existing Feature**: Command Parsing Optimization in Expense and Allowance Features
  * Refined the `Expense` and `Allowance` features to parse commands with spaces and handle multiple commands concurrently.

* **Bugfix**: Dynamic Transformation of `TransactionList`
  * Converted TransactionList from a static structure to a dynamic framework using ArrayList,resolved the issue of inaccurate transaction count post-filtering, ensuring data integrity and reliability in the transaction management process.

### Contributions to the User Guide
* Incorporated examples of inputs and expected outputs for all features.
* Added 'view' feature to the 'Quick Start' section.
* Added 'filter' feature to the 'Quick Start' section.

### Contributions to the Developer Guide
* Detailed the implementation of the 'view' and 'filter' features.
* Created and integrated sequence diagrams for both features.
* Authored the 'Product Scope' section.

### Team-Based Tasks
* Developed JUnit tests, including those for TransactionList class, a core component of our application.
* Conducted extensive manual testing to identify issues such as logging and storage errors.
* Assessed all features from a user's perspective, contributing to the User Guide with expected outputs.

### Community contributions
* Reported 15 bugs and suggestions for peer teams[(Issues)](https://github.com/vvhuiling/ped/issues)

