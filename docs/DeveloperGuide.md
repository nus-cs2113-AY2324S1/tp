mT# Developer Guide

## Acknowledgements

We first give acknowledgement to Module Coordinator, Dr Akshay Narayan, and Teaching Assistant, Irving for guidance and supervision over the KaChinnnng project.

We utilised the following resources to aid us in the development of KaChinnnng:
-  [tp project](https://github.com/nus-cs2113-AY2324S1/tp) of the nus-cs2113-AY2324S1 organisation
-  [addressbook-level2](https://github.com/se-edu/addressbook-level2) & [addressbook-level3](https://github.com/se-edu/addressbook-level3) project by SE-EDU largely inspired the format and coding style of KaChinnnng.


## Design & implementation

Below are the design and implementation details of KaChinnnng.

### Design

The architectural diagram of KaChinnnng is as follows:

![ArchitectureDiagram.png](https://github.com/AY2324S1-CS2113-T18-3/tp/blob/master/images/ArchitectureDiagram.png?raw=true)

The KaChinnnng program will first enter the `run` state where the `storage` class will be accessed and data will be retrieved if the application has been used before. If the application has not been used before, the `storage` class will create a new file to store the data.

Next, the `User` will input various `commands` via the `Ui` class. The `commands` will then be parsed by the `Parser` class and the relevant `managers` will be called to execute the `commands`.

Once the `User` inputs has been successfully executed, `commands` will make use of the `Ui` class to output the relevant information to the user.

When the `User` exits the program, the `storage` class will be called to save the data to the local storage.

`Storage` will read from the local storage when the program is run again.

### Expense Class Overview

The Expenses are divided into three categories. Transport, Utilities and Food.
Each of the categories inherits from a base Expense class which in turn
inherits from the FinancialRecord class.

![expense_class_diagram.png](https://github.com/AY2324S1-CS2113-T18-3/tp/blob/master/images/expense_class_diagram.png?raw=true)
### IncomeManager

the IncomeManager is facilitated by `IncomeParser`, `IncomeManager`, `IncomeList` and `Ui`.

1. The user inputs the command to add income. This input is then passed to `IncomeParser` to be parsed.
2. `IncomeParser` then parses the input and returns a `Income` object.
3. `IncomeManager` then adds the `Income` object to the `IncomeList`.
4. `IncomeManager` then returns a `String` to `Ui` to be printed out to the user.

![IncomeManager_seqdiagram.drawio.png](https://github.com/AY2324S1-CS2113-T18-3/tp/blob/master/images/IncomeManager_SequenceDiagram(updated).png?raw=true)

### ExpenseManager

The ExpenseManager is facilitated by `ExpenseParser`,  `ExpenseManager`, `ExpenseList` and `Ui`

1. The user inputs the command to add expense.
2. This command is used to instantiate a ExpenseManager object which is executed.
3. During execution, the command is passed to ExpenseParser which extracts and parses the input fields.
4. Input field information is passed to the relevant Expense constructor which returns an Expense object.
5. Expense object is returned to ExpenseManager from ExpenseParser.
6. Expense object is returned to Duke from ExpenseManager.

![expenseManager_sequence_diagram.png](https://github.com/AY2324S1-CS2113-T18-3/tp/blob/master/images/expenseManager_sequence_diagram.png?raw=true)

### Find Command
The FindCommand is facilitated by `FindCommand`, `FindParser`, `Ui`,`ExpenseList` and `IncomeList`.

1. The user will first input the command to find the expense or income.
```
find /t <type> /cat [category] /de [description] /date [date]
```
2. The inputs will then be passed to `FindParser` to get the necessary fields such as type, category, description and date.
3. The FindCommand is then instantiated with the necessary fields.
4. Users can choose to search through the expense list or income list.
5. If users searches through the income list, either the `/de description` or `/date date` will be used to search through the income list, category will be ignored.
6. If users searches through the expense list, either the `/cat category`, `/de description` or `/date date` will be used to search through the expense list.
7. The execute method will then be called to search through the list and return the list of expenses or incomes that matches the search criteria.

![FindCommand_SequenceDiagram.png](https://github.com/AY2324S1-CS2113-T18-3/tp/blob/master/images/FindCommand_SequenceDiagram(updated).drawio.png?raw=true)

### GetFromTxt Command
The GetFromTxt Command is facilitated by `IOException`,`Scanner`, `KaChinnnnngException`, `IncomeList` and `ExpenseList`
1. The user start the program and the command will start automatically with a default path
2. Once the command executed, it will try to create a txt file on the default path
3. If file failed to create, throw an `IOException`
4. Catch the `IOException` and print error messages
5. Create a `Scanner` Object s to get content from txt file
6. If amount in txt file exceed limit or incorrect, throw `KaChinnnnngException`
7. Read the content on the file and add corresponding object to incomes or expenses list

![GetFromTxtCommands_SequenceDiagram.png](https://github.com/AY2324S1-CS2113-T18-3/tp/blob/master/images/GetFromTxt.png?raw=true)

### ExchangeRateManager
The Sequence Diagram below shows how the components interact with each other for the scenario 
where the user issues the command `update exchange rate USD 0.8`.
1. The user input the command `update exchange rate USD 0.8`
2. Duke will create the `UpdateExchangeRateCommand` object with the specified currency and rate
3. Duke will the execute the command object which will call `updateExchangeRate` in ExchangeRateManager
4. If the currency specified is not a supported currency or the rate specified is not a decimal with 0.001 and 3,000,000,
   a `KaChinnnningException` will be thrown
5. Once the update is successful, Ui will be called to show the completion message
6. Lastly, the updated exchange rates will be saved in the exchange rate file

![UpdateExchangeRate_SequenceDiagram.png](https://github.com/AY2324S1-CS2113-T18-3/tp/blob/master/images/UpdateExchangeRate_SequenceDiagram.png?raw=true)

This is facilitated by `Duke`, `Ui`, `UpdateExchangeRateCommand`, `ExchangeRateManager`'and `ExchangeRateFileHandler`.

### SaveToTxt Command
The SaveToTxt Command is facilitated by `FileWriter` ,`IncomeList` and `ExpenseList`
1. The user start the program and the function will run everytime after user input a command
2. Once the command executed, the function saveIncomeAndExpense will run automatically
3. It will run the function saveIncomeToTextFile first, the saveIncomeToTextFile will create a FileWriter and loop through the incomes list
4. It will get description, date, and amount from the income list for every single income record in the list and save the information into the text file
5. After that, the saveIncomeToTextFile will then call the saveExpenseToTextFile function 
6. The saveExpenseToTextFile will create a new FileWriter and loop through the expenses list 
7. It will get description, date, amount and type from the expenses list for every single expense record in the list and save the information into the text file

![SaveToTxt_SequenceDiagram.png](https://github.com/AY2324S1-CS2113-T18-3/tp/blob/master/images/SaveToTxt.png?raw=true)

## Product scope
### Target user profile
Target user profile:
- has a need to manage one's finance and track their financial health
- prefer desktop apps over mobile app/web app
- can type fast
- prefers typing to mouse interactions
- is reasonably comfortable using CLI applications

### Value proposition: 
KaChinnnng is a financial planner for users to track and improve their financial health. It allows users who are more comfortable with Command Line Interface (CLI) applications to record and track their financials faster than other Graphical User Interface (GUI) applications. Additionally, it provides a quick and easy currency conversion feature that allows users to convert their overseas expenses into SGD. By tracking one's financial health, one can make more informed financial decisions in the future.

## User Stories

| Version | As a ... | I want to ...                      | So that I can ...                                                     |
|---------|----------|------------------------------------|-----------------------------------------------------------------------|
| v1.0    | new user | see usage instructions             | refer to them when I forget how to use the application                |
| v1.0    | user     | add new income entry               | track all my incomes                                                  |
| v1.0    | user     | add new expense entry              | track all of my expenses                                              |
| v1.0    | user     | delete income entry                | remove incomes that I no longer want to track                         |
| v1.0    | user     | delete expense entry               | remove expenses that I no longer want to track                        |
| v1.0    | user     | list both income and expense entry | view all my expenses and incomes                                      |
| v1.0    | user     | check my balance                   | better budget my expenditures                                         |
| v2.0    | user     | search for past inputs             | better find and track my past expenses                                |
| v2.0    | user     | be able to change the currency     | better track my expenses in different currencies when i am travelling |
| v2.0    | user     | be able to edit my inputs          | make necessary changes to my inputs when a mistake is made            |
| v2.0    | user     | be able to save and load my inputs | access them the next time I use the app                               |
| v2.0    | user     | reset my inputs                    | start afresh                                                          |

## Non-Functional Requirements
- Domain rules:
    - The application should not crash under normal circumstances.
    - Dates can be entered in the format dd/MM/yyyy and should not be in the future.
    - Amount added should be in the format of 0.00 up to 2 decimal places and should not be negative.
    - The application only accepts commands in English.
    - Users should refrain from using special characters in the description/category/type/date field or in general.
    - Users should refrain from tampering with the storage and log files as it may result in unexpected issues.
 
- Constraints:
  - the total number of entries for Expense and Income should not exceed 100 each.
  - the amount input should be less than 1000000.

- Technical Requirements:
  - The application must be able to run on Windows, macOS and Linux.
- Technical Requirements:
  - KaChinnnng should be able to be used by users of all skill levels.
- Others:
  - Arguments should follow the format specified in the user guide.
  - Should the arguments not be in the provided format, the application will not execute the command.



## Glossary

| Term    | Definition                                                 |
|---------|------------------------------------------------------------|
| Income  | debit entry of user                                        |
| Expense | credit entry of user                                       |
| Balance | Net Amount                                                 |
| Ui      | User Interface, where user inputs commands and sees output |
| Parser  | Parses user input into commands                            |


## Instructions for manual testing

Given below are the instructions for manual testing of KaChinnnng.

### Launching and exiting the application
1. launching the application
   1. download the jar file from the release page.
   2. open a terminal and navigate to the directory where the jar file is located.
   3. run the command `java -jar tp.jar`
2. exiting the application
   1. type `exit` in the command box and press enter.
   2. the application will exit.

### Adding an income/expense
adding an income/expense
1. Test case:
   - To add an income: `add income /de salary /date 31/10/2023 /amt 5000.00`
   - To add an expense: `add expense /cat food /type lunch /de sushi /date 31/10/2023 /amt 20.00`
   - `/type` for expense includes (`OTHER`, `BREAKFAST`, `LUNCH`, `DINNER`) for the `food` category, (`TRAIN`, `BUS`, `TAXI`, `FUEL`, `OTHER`) for the `TRANSPORT` category and (`OTHER`,  `WATER`, `ELECTRICITY`, `GAS`) for the `UTILITIES` category.
   - Expected outcome: the income/expense will be added to the list and the balance will be updated accordingly and the user will be notified.
   
2. Test case:
   - income: `add income /de salary`
   - expense: `add expense /cat food /type lunch`
   - Expected outcome: the income/expense will not be added to the list and the user will be notified with an error message.
   
3. Test case:
   - Other invalid test cases includes:
   - income: `add income /de salary /date 31/11/2024 /amt 5000.00` where the date is in the future.
   - income: `add income /de salary /date 31-10-2023 /amt 5000.00` where the date is in the wrong format.
   - income: `add income /de salary /date 31/10/2023 /amt -5000.00` where the amount is negative.
   - expense: `add expense /cat food /type lunch /de lunch  /amt 5000.00` where there is missing fields

### Updating exchange rate
updating exchange rate
To check the supported currencies, type `list currencies`
1. Test case:
   - To update exchange rate: `update exchange rate USD 0.8`
   - Expected outcome: the exchange rate will be updated and the user will be notified.

### Listing all incomes/expenses
pre-requisite: list should already contain income/expense.
1. Test case: If there is no income/expense
   - For income: `list incomes`
   - For expense: `list expenses`
   - Expected outcome: no income/expense should be listed.
2. Test case: If there is income/expense
    - For income: `list incomes`
    - For expense: `list expenses`
    - Expected outcome: all income/expense should be listed.
3. Test case: to list both income and expense
    - `list`
    - Expected outcome: all income/expense should be listed.

### Deleting an income/expense
Deleting income/expense
pre-requisite: list should already contain income/expense. this can be checked via `list incomes`, `list expenses` or `list`
1. Test case: If there is index specified
   - For income/expense: `delete income <index>`
   - Expected outcome: the income/expense will be deleted from the list and the balance will be updated accordingly and the user will be notified.
2. Test case: If the index specified is invalid
    - For income/expense: `delete income <invalid_index>`
    - Expected outcome: `Oops! Income <invalid_index> does not exist`
3. Test case: If the index is missing
    - For income/expense: `delete income`
    - Expected outcome: `You're missing an argument`
4. Test case: If there index is not an integer
    - For income/expense: `delete income abc`
    - Expected outcome: `Oops! An integer index is expected`

### Clearing all incomes/expenses
Clearing all income/expense
pre-requisite: list should already contain income/expense. this can be checked via `list incomes`, `list expenses` or `list`
1. Test case: If there is income/expense
   - For income: `clear incomes`
   - For expense: `clear expenses`
   - Expected outcome: all income/expense will be deleted from the list and the balance will be updated accordingly and the user will be notified.

2. Test case: To clear both income and expense
   - `clear all`
   - Expected outcome: all income/expense will be deleted from the list and the balance will be updated accordingly and the user will be notified.
### Editing an income/expense
Editing income/expense
pre-requisite: list should already contain income/expense. this can be checked via `list incomes`, `list expenses` or `list`

1. Test case: If there is index specified
   - For income/expense: `edit income <index> income /de <description> /date <DD/MM/YYYY> /amt <amount>`
   - Expected outcome: the income/expense will be edited and the balance will be updated accordingly and the user will be notified.
2. Test case: If there is index specified invalid
   - For income/expense: `edit income <invalid_index> income /de <description> /date <DD/MM/YYYY> /amt <amount>`
   - Expected outcome: `Oops! Income <invaldi_index> does not exist`
3. Test case: Missing arguments
    Assuming Income 1 and Expense 1 exist:
    - income: `edit income 1 /de salary`
    - expense: `edit expense 1 /cat food /type lunch`
    - Expected outcome: the income/expense will not be edited and the user will be notified with an error message.
   
4. Test case: Other invalid test cases includes:
    Assuming index Income 1 exist:
    - income: `edit income 1 /de salary /date 31/11/2023 /amt 5000.00` where the date is in the future.
    - income: `edit income 1 /de salary /date 31-10-2023 /amt 5000.00` where the date is in the wrong format.
    - income: `edit income 1 /de salary /date 31/10/2023 /amt -5000.00` where the amount is negative.
    - expense: `edit expense 1 /cat food /type lunch /de lunch /amt 5000.00` where there is missing fields

### Finding an income/expense
Finding income/expense:
1. Pre-requisite: list should already contain income/expense. this can be checked via `list incomes`, `list expenses` or `list`
2. For income, at least one of the 2 optional fields have to be used to search for the income.
3. For expense, at least one of the 3 optional fields have to be used to search for the expense.
4. Test case:
   - For income: `find /t income /de salary`
   - For expense: `find /t expense /cat food /de sushi /date 31/10/2023`
   - Expected outcome: all income with description containing "salary" should be listed. all expense containing "food" in the category, "sushi" in the description and "31/10/2023" in the date should be listed.
   
5. Test case: If there is no matching income/expenses
   - Income: `find /t income /de bonus`
   - Expense: `find /t expense /cat food /de burger /date 31/10/2023`
   - Expected outcome: no income/expense should be listed.
