# User Guide

## Introduction

CashLeh? is a CLI application mainly supporting working adults and students who struggle with managing finances
(ranging from one-time and recurring expenses, to rent, utilities, interest rates and more). It allows them to track
both their earnings and spending habits in a seamless way to have a neat overview of their financial 
situation.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `CashLeh?` from [GitHub](https://github.com/AY2324S1-CS2113-W12-2/tp/releases/).
3. Copy it into an empty folder
4. Open the terminal and run the command `java javac- Cashleh.jar`
5. CashLeh? should launch and you can start interacting with the application

## Features

> **Note**\
> The delimiters in the commands can be input at any position in the command.
> 
> For example, `addExpense chicken rice /amt 100 /date 30-09-2022 /cat food` is equivalent to `addExpense chicken rice /date 30-09-2022 /cat food /amt 100`.

### Adding an income: `addIncome`
Adds an income with a description, amount, date and category.

Format: `addIncome DESCRIPTION /amt AMOUNT /date DATE /cat CATEGORY`

* The `DESCRIPTION` cannot contain punctuation or any special characters.
* The `AMOUNT` must be a positive number.
* The `DATE` is optional, it will default to the current date if not provided. It accepts a range of formats, `dd/mm/yyyy` is recommended. 
* THE `CATEGORY` is optional, if the provided input does not correspond to any of the preset categories <code>
(SALARY, ALLOWANCE, INVESTMENT, LOTTERY_GAMBLING)</code>, it will default to <code>OTHERS</code>


Example of usage: 

`addIncome monthly salary /amt 2500 /date 30/09/2023`

`addIncome APPL /amt 500 /cat Investment`

`addIncome amazon purchase refund /amt 50 /date 2023-10-10`

### Adding an expense: `addExpense`
Adds an expense with a description, amount, date and category.

Format: `addIncome DESCRIPTION /amt AMOUNT /date DATE /cat CATEGORY`

* The `DESCRIPTION` cannot contain punctuation or any special characters.
* The `AMOUNT` must be a positive number.
* The `DATE` is optional, it will default to the current date if not provided. It accepts a range of formats, `dd/mm/yyyy` is recommended.
* THE `CATEGORY` is optional, if the provided input does not correspond to any of the preset categories <code>
(FOOD_DRINK, SHOPPING, HOUSING, TRANSPORTATION, ENTERTAINMENT, UTILITIES)</code>, it will default to <code>OTHERS</code>

Example of usage:

`addExpense milk tea /amt 2.50 /date 30/09/2023 /cat FOOD_DRINK`

`addExpense textbook /amt 10`

### Deleting an income: `deleteIncome`
Deletes an income with a specific index.

Format: `deleteIncome INDEX`

* The `INDEX` must be a positive integer and cannot be larger than the number of income entries.

Example of usage:

`deleteIncome 4`

### Deleting an expense: `deleteExpense`
Deletes an expense with a specific index.

Format: `deleteExpense INDEX`

* The `INDEX` must be a positive integer and cannot be larger than the number of expense entries.

Example of usage:

`deleteExpense 4`

### Viewing previous incomes: `viewIncomes`
Shows sum of incomes and lists each income record with its description, amount, date and (optional) category (if input by user).  
Format: `viewIncomes`  
* Anything following the command will be ignored, i.e. `viewIncomes overview` will be interpreted just 
like `viewIncomes`.

Example of usage:

`viewIncomes`

### Viewing previous expenses: `viewExpenses`
Shows sum of expenses and lists each expense record with its description, amount, date and (optional) category (if input by user).  
Format: `viewExpenses`
* Anything following the command will be ignored, i.e. `viewExpenses overview` will be interpreted just
  like `viewExpenses`.

Example of usage:

`viewExpenses`

### Viewing the entire financial statement: `viewFinancialStatement`
Shows the net cash on hand and lists each income and expense record along with its description, amount, date and (optional) category.  
Format: `viewFinancialStatement`
* Anything following the command will be ignored, i.e. `viewFinancialStatement overview` will be interpreted just like 
`viewFinancialStatement`.

Example of usage:

`viewFinancialStatement`

### Viewing the current budget: `viewBudget`
Displays the current budget, the net cash on hand and a bar chart showing the leftover available budget.

Format: `viewBudget`
* Anything following the command will be ignored, i.e. `viewBudget overview` will be interpreted just like `viewBudget`.
* If no budget was set or the budget is too low, CashLeh? will print an error message.

Example of usage:

`viewBudget`

### Updating a budget/setting a new budget: `updateBudget`
Updates the budget to a new amount or creates a new budget if no previous budget was set.

Format: `updateBudget AMOUNT`
* The `AMOUNT` cannot be zero, CashLeh? will display an error message
* If the `AMOUNT` is negative, CashLeh? will take its absolute value

Example of usage:

`updateBudget 45`

### Deleting the budget: `deleteBudget`
Deletes the currently set budget.

Format: `deleteBudget`

Example of usage:

`deleteBudget`

### Filtering an expense: `filterExpense`
Displays expenses that match specific criteria provided by the user. 
Expenses can be filtered based on the following criteria: description, amount, date, or category.

Format: `filterExpense description /amt AMOUNT /date DATE /cat CATEGORY`
* All criteria are optional. User can choose to filter based on just one or multiple criteria at the same time
* If no criteria is provided, CashLeh? will display an error message

Examples of usage:

`filterExpense milk tea`

`filterExpense /amt 3.50`

`filterExpense milk tea /amt 3.50`

`filterExpense /cat FOOD_DRINK`

`filterExpense milk tea /date 25/10/2023`

### Filtering an income: `filterIncome`
Displays incomes that match specific criteria provided by the user.
Incomes can be filtered based on the following criteria: description, amount, date, or category.

Format: `filterIncome description /amt AMOUNT /date DATE /cat CATEGORY`
* All criteria are optional. User can choose to filter based on just one or multiple criteria at the same time
* If no criteria is provided, CashLeh? will display an error message

Examples of usage:

`filterIncome salary`

`filterIncome /amt 1000 /date 25/10/2023`

`filterIncome /cat SALARY`

`filterIncome /date 25/10/2023`

### Filtering a transaction: `filter`
Displays expenses and incomes that match specific criteria provided by the user.
Transactions can be filtered based on the following criteria: description, amount, date, or category.

Format: `filter description /amt AMOUNT /date DATE /cat CATEGORY`
* All criteria are optional. User can choose to filter based on just one or multiple criteria at the same time
* If no criteria is provided, CashLeh? will display an error message

Examples of usage:

`filter milk tea`

`filter milk tea /amt 3.50`

`filter /cat OTHERS`

`filter /date 25/10/2023`

### Exit: `exit`
Exit the app.

Format: `exit`

Example of usage:

`exit`

### Edit Income parameters: `editIncome` [coming soon]
Change a parameter of a specific income.

Format: `editIncome INDEX PARAMETER NEW_PARAM`
* The `INDEX` must be a positive integer and cannot be larger than the number of expense entries.
* The `PARAMETER` denotes the specific parameter to change, list of possible parameters: DESC, AMT, DATE, CAT
* The `NEW_PARAM` is the new parameter to replace the odd parameter

Example of usage:

`editIncome 2 AMT 2000`

`editIncome 1 DESC dinner`

### Edit Expense parameters: `editExpense` [coming soon]
Change a parameter of a specific expense.

Format: `editExpense INDEX PARAMETER NEW_PARAM`
* The `INDEX` must be a positive integer and cannot be larger than the number of expense entries.
* The `PARAMETER` denotes the specific parameter to change, list of possible parameters: DESC, AMT, DATE, CAT
* The `NEW_PARAM` is the new parameter to replace the odd parameter

Example of usage:

`editExpense 2 AMT 2000`

`editExpense 1 DESC weekly allowance`

### Set Password: `setPassword` [coming soon]
Set new password.

### Change Password: `changePassword` [coming soon]
Change Password.

## FAQ

**Q**: How do I transfer my data to another Computer?

**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data.

## Command Summary

* Add income `addIncome monthly salary /amt2500 /d2023-09-30`
* Delete income `deleteIncome 4`
* View incomes `viewIncomes`
* View financial statement `viewFinancialStatement`
* View budget `viewBudget`
* Update budget `updateBudget 200`
* Delete budget `deleteBudget`
