# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}
### IncomeManager

the IncomeManager is facilitated by `IncomeParser`, `IncomeManager`, `IncomeList` and `Ui`.

1. The user inputs the command to add income. This input is then passed to `IncomeParser` to be parsed.
2. `IncomeParser` then parses the input and returns a `Income` object.
3. `IncomeManager` then adds the `Income` object to the `IncomeList`.
4. `IncomeManager` then returns a `String` to `Ui` to be printed out to the user.

![incomeManager_sequence_diagram.png](..%2Fimages%2FincomeManager_sequence_diagram.png)

### ExpenseManager

The ExpenseManager is facilitated by `ExpenseParser`,  `ExpenseManager`, `ExpenseList` and `Ui`

1. The user inputs the command to add expense.
2. This command is used to instantiate a ExpenseManager object which is executed.
3. During execution, the command is passed to ExpenseParser which extracts and parses the input fields.
4. Input field information is passed to the relevant Expense constructor which returns a Expense object.
5. Expense object is returned to ExpenseManager from ExpenseParser.
6. Expense obejct is returned to Duke from ExpenseManager.

![expenseManager_sequence_diagram.png](/Users/apple/Desktop/expenseManager_sequence_diagram.png)

### Find Command
The FindCommand is facilitated by `FindCommand`, `FindParser`, `Ui`,`ExpenseList` and `IncomeList`.

1. The user will first input the command to find the expense or income.
```
find /t <type> /cat [category] /de [description] /date [date]
```
2. The inputs will then be passed to `FindParser` to get the necessary fields such as type, category, description and date.
3. The FindCommand is then instantiated with the necessary fields.
4. Users can choose to search through the expense list or income list.
5. If users searches through the income list, either the description or date will be used to search through the income list.
6. If users searches through the expense list, either the category or date will be used to search through the expense list.
7. The execute method will then be called to search through the list and return the list of expenses or incomes that matches the search criteria.

![FindCommand_SequenceDiagram.png](..%2Fimages%2FFindCommand_SequenceDiagram.png)

### GetFromTxt Command
The GetFromTxt Command is facilitated by `IOException`,`Scanner`, `KaChinnnnngException`, `IncomeList` and `ExpenseList`
1. The user start the program and the command will start automatically with a default path
2. Once the command executed, it will try to create an txt file on the default path
3. If file failed to create, throw an `IOException`
4. Catch the `IOExceotion` and print error messages
5. Create a `Scanner` Object s to get content from txt file
6. If amount in txt file exceed limit or incorrect, throw `KaChinnnningException`
7. Read the content on the file and add corresponding object to incomes or expenses list

![FindCommand_SequenceDiagram.png](..%2Fimages%2FGetFromTxt.png)

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

| Version | As a ... | I want to ...                      | So that I can ...                                      |
|---------|----------|------------------------------------|--------------------------------------------------------|
| v1.0    |new user| see usage instructions             | refer to them when I forget how to use the application |
| v1.0    |user| add new income entry               | track all my incomes                                   |
| v1.0    |user| add new expense entry              | track all of my expenses                               |
| v1.0    |user| delete income entry                | remove incomes that I no longer want to track          |
| v1.0    |user| delete expense entry               | remove expenses that I no longer want to track         |
| v1.0    |user| list both income and expense entry | view all my expenses and incomes                       |
| v1.0    |user| check my balace                    | better bugdet my expenditures                          |


## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
