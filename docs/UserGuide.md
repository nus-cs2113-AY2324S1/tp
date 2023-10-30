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
Adds an income with a description, amount and date.

Format: `addIncome DESCRIPTION /amt AMOUNT /d DATE /cat CATEGORY`

* The `DESCRIPTION` cannot contain punctuation or any special characters.
* The `AMOUNT` must be a positive number.
* The `DATE` is optional, it will default to the current date if not provided. It accepts a range of formats, `dd/mm/yyyy` is recommended.
* The `CATEGORY` is optional, it will default to OTHERS if it is not one of the exists categories.

Example of usage: 

`addIncome monthly salary /amt 2500 /date 30/09/2023`

`addIncome APPL /amt 500 /cat Investment`

`addIncome amazon purchase refund /amt 50 /date 2023-10-10`

### Deleting an income: `deleteIncome`
Deletes an income with a specific index.

Format: `deleteIncome INDEX`

* The `INDEX` must be a positive integer and cannot be larger than the number of income entries.

Example of usage:

`deleteIncome 4`

### Viewing previous incomes: `viewIncomes`
Shows sum of incomes and lists each income record with its description and amount.  
Format: `viewIncomes`  
* Anything following the command will be ignored, i.e. `viewIncomes overview` will be interpreted just 
like `viewIncomes`.

Example of usage:

`viewIncomes`

### Viewing the entire financial statement: `viewFinancialStatement`
Shows the net cash on hand and lists each income and expense record along with its description and amount.  
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

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

* Add income `addIncome monthly salary /amt2500 /d2023-09-30`
* Delete income `deleteIncome 4`
* View incomes `viewIncomes`
* View financial statement `viewFinancialStatement`
* View budget `viewBudget`
* Update budget `updateBudget 200`
* Delete budget `deleteBudget`
