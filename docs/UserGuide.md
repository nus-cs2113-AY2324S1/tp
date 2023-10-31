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
4. Open the terminal and run the command `java javac- FILENAME.jar`
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
```
    ____________________________________________________________
	The following income was added:
	Income: monthly salary (Amount: 2500.0, Date: 30/09/2023)
	____________________________________________________________

```

`addIncome amazon purchase refund /amt 50 /cat OTHERS`
```
	____________________________________________________________
	The following income was added:
	Income: amazon purchase refund (Amount: 50.0, Date: 31/10/2023, Category: OTHERS)
	____________________________________________________________

```

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
```
    ____________________________________________________________
	The following expense was added:
	Expense: milk tea (Amount: 2.5, Date: 30/09/2023, Category: FOOD_DRINK)
	____________________________________________________________
```
`addExpense textbook /amt 10`
```
	____________________________________________________________
	The following expense was added:
	Expense: textbook (Amount: 10.0, Date: 31/10/2023)
	____________________________________________________________

```
### Deleting an income: `deleteIncome`
Deletes an income with a specific index.

Format: `deleteIncome INDEX`

* The `INDEX` must be a positive integer and cannot be larger than the number of income entries.

Example of usage:

`deleteIncome 2`
```
	____________________________________________________________
	Noted! CashLeh has removed the following income:
	Income: amazon purchase refund (Amount: 50.0, Date: 31/10/2023, Category: OTHERS)
	____________________________________________________________
```
### Deleting an expense: `deleteExpense`
Deletes an expense with a specific index.

Format: `deleteExpense INDEX`

* The `INDEX` must be a positive integer and cannot be larger than the number of expense entries.

Example of usage:

`deleteExpense 2`
```
	____________________________________________________________
	Noted! CashLeh has removed the following expense:
	Expense: textbook (Amount: 10.0, Date: 31/10/2023)
	____________________________________________________________
```

### Viewing previous incomes: `viewIncomes`
Shows sum of incomes and lists each income record with its description, amount, date and (optional) category (if input by user).  
Format: `viewIncomes`  
* Anything following the command will be ignored, i.e. `viewIncomes overview` will be interpreted just 
like `viewIncomes`.

Example of usage:

`viewIncomes`
```
+-----------------------------------------------------------------------------------------------------------------------+
|                                                   Income Statement                                                    |
+----------+--------------+--------------------+------------------------------+--------------------+--------------------+
|    ID    |     Type     |        Date        |         Description          |      Category      |       Amount       |
+----------+--------------+--------------------+------------------------------+--------------------+--------------------+
|    1     |    Income    |     2023-09-30     |        monthly salary        |         -          |     + $2500.0      |
+-----------------------------------------------------------------------------------------------------------------------+
| Total Income: $2500.0                                                                                                 |
+-----------------------------------------------------------------------------------------------------------------------+
```

### Viewing previous expenses: `viewExpenses`
Shows sum of expenses and lists each expense record with its description, amount, date and (optional) category (if input by user).  
Format: `viewExpenses`
* Anything following the command will be ignored, i.e. `viewExpenses overview` will be interpreted just
  like `viewExpenses`.

Example of usage:

`viewExpenses`
```
+-----------------------------------------------------------------------------------------------------------------------+
|                                                   Expense Statement                                                   |
+----------+--------------+--------------------+------------------------------+--------------------+--------------------+
|    ID    |     Type     |        Date        |         Description          |      Category      |       Amount       |
+----------+--------------+--------------------+------------------------------+--------------------+--------------------+
|    1     |   Expense    |     2023-09-30     |           milk tea           |     FOOD_DRINK     |       - $2.5       |
+-----------------------------------------------------------------------------------------------------------------------+
| Total Expense: $2.5                                                                                                   |
+-----------------------------------------------------------------------------------------------------------------------+
```

### Viewing the entire financial statement: `viewFinancialStatement`
Shows the net cash on hand and lists each income and expense record along with its description, amount, date and (optional) category.  
Format: `viewFinancialStatement`
* Anything following the command will be ignored, i.e. `viewFinancialStatement overview` will be interpreted just like 
`viewFinancialStatement`.

Example of usage:

`viewFinancialStatement`
```
+-----------------------------------------------------------------------------------------------------------------------+
|                                                  Financial Statement                                                  |
+----------+--------------+--------------------+------------------------------+--------------------+--------------------+
|    ID    |     Type     |        Date        |         Description          |      Category      |       Amount       |
+----------+--------------+--------------------+------------------------------+--------------------+--------------------+
|    1     |    Income    |     2023-09-30     |        monthly salary        |         -          |     + $2500.0      |
|    2     |   Expense    |     2023-09-30     |           milk tea           |     FOOD_DRINK     |       - $2.5       |
+-----------------------------------------------------------------------------------------------------------------------+
| Total Income: $2500.0                                                                                                 |
| Total Expense: $2.5                                                                                                   |
| Net Income: $2497.5                                                                                                   |
+-----------------------------------------------------------------------------------------------------------------------+
```

### Viewing the current budget: `viewBudget`
Displays the current budget, the net cash on hand and a bar chart showing the leftover available budget.

Format: `viewBudget`
* Anything following the command will be ignored, i.e. `viewBudget overview` will be interpreted just like `viewBudget`.
* If no budget was set or the budget is too low, CashLeh? will print an error message.

Example of usage:

`viewBudget`
```
	____________________________________________________________
	You have set a budget of: 45.0
	Here's a quick view of how you're doing so far:
	You have a net cash on hand of: 2497.5
	You still have the following percent of your budget left:

	[******************************] 100.00%
	____________________________________________________________
```

### Updating a budget/setting a new budget: `updateBudget`
Updates the budget to a new amount or creates a new budget if no previous budget was set.

Format: `updateBudget AMOUNT`
* The `AMOUNT` cannot be zero, CashLeh? will display an error message
* If the `AMOUNT` is negative, CashLeh? will take its absolute value

Example of usage:

`updateBudget 100`
```
    ____________________________________________________________
	The budget was updated to:
	100.0
	____________________________________________________________
```

### Deleting the budget: `deleteBudget`
Deletes the currently set budget.

Format: `deleteBudget`

Example of usage:

`deleteBudget`
```
	____________________________________________________________
	Alright, CashLeh has just deleted your previous budget!
	Watch out though as spending without budget ain't smart...
	____________________________________________________________
```
### Filtering an expense: `filterExpense`
Displays expenses that match specific criteria provided by the user. 
Expenses can be filtered based on the following criteria: description, amount, date, or category.

Format: `filterExpense description /amt AMOUNT /date DATE /cat CATEGORY`
* All criteria are optional. User can choose to filter based on just one or multiple criteria at the same time
* If no criteria is provided, CashLeh? will display the user's input, along with an error message

Examples of usage:

`filterExpense milk tea`
```
	____________________________________________________________
	Here are your corresponding expenses with <description>: milk tea ||
	Expense: milk tea (Amount: 2.5, Date: 30/09/2023, Category: FOOD_DRINK)
	____________________________________________________________
```
`filterExpense /amt 3.50`
```
	____________________________________________________________
	Your input is <amount>: 3.5 ||
	____________________________________________________________
	____________________________________________________________
	No such transaction recorded leh!
	____________________________________________________________
```
`filterExpense milk tea /amt 3.50`

`filterExpense /cat FOOD_DRINK`

`filterExpense milk tea /date 25/10/2023`

### Filtering an income: `filterIncome`
Displays incomes that match specific criteria provided by the user.
Incomes can be filtered based on the following criteria: description, amount, date, or category.

Format: `filterIncome description /amt AMOUNT /date DATE /cat CATEGORY`
* All criteria are optional. User can choose to filter based on just one or multiple criteria at the same time
* If no criteria is provided, CashLeh? will display the user's input, along with an error message

Examples of usage:

`filterIncome monthly salary`
```
	____________________________________________________________
	Here are your corresponding incomes with <description>: monthly salary ||
	Income: monthly salary (Amount: 2500.0, Date: 30/09/2023)
	____________________________________________________________
```

`filterIncome /amt 1000 /date 25/10/2023`

`filterIncome /cat SALARY`

`filterIncome /date 25/10/2023`
```
	____________________________________________________________
	Your input is <date>: 2023-10-25 ||
	____________________________________________________________
	____________________________________________________________
	No such transaction recorded leh!
	____________________________________________________________
```

### Filtering a transaction: `filter`
Displays expenses and incomes that match specific criteria provided by the user.
Transactions can be filtered based on the following criteria: description, amount, date, or category.

Format: `filter description /amt AMOUNT /date DATE /cat CATEGORY`
* All criteria are optional. User can choose to filter based on just one or multiple criteria at the same time
* If no criteria is provided, CashLeh? will display the user's input, along with an error message

Examples of usage:

`filter milk tea`
```
	____________________________________________________________
	Here are your corresponding transactions with <description>: milk tea ||
	[-] Expense: milk tea (Amount: 2.5, Date: 30/09/2023, Category: FOOD_DRINK)
	____________________________________________________________
```

`filter milk tea /amt 3.50`

`filter /cat OTHERS`

`filter /date 25/10/2023`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

* Add income `addIncome monthly salary /amt2500 /d2023-09-30`
* Add expense `addExpense milk tea /amt 2.50 /date 31/19/2023 /cat food`
* Delete income `deleteIncome 4`
* Delete expense `deleteExpense 1`
* View incomes `viewIncomes`
* View expenses `viewExpenses`
* View financial statement `viewFinancialStatement`
* View budget `viewBudget`
* Update budget `updateBudget 200`
* Delete budget `deleteBudget`
* Filter expenses `filterExpense`
* Filter incomes `filterIncome`
* Filter all transactions `filter`
* Exit `exit`
