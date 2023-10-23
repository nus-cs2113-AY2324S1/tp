# User Guide

## Introduction

CashLeh? is a CLI application mainly supporting working adults and students who struggle with managing finances
(ranging from one-time and recurring expenses, to rent, utilities, interest rates and more). It allows them to track
both their earnings and spending habits in a seamless way to have a neat overview of their financial 
situation.

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `CashLeh?` from [github.com](https://github.com/AY2324S1-CS2113-W12-2/tp/releases/).
3. Copy it into an empty folder
4. Open the terminal and run the command `java javac- FILENAME.jar`
5. CashLeh? should launch and you can start interacting with the application

## Features

### Adding an income: `addIncome`
Adds an income with a description, amount and date.

Format: `addIncome DESCRIPTION /amtAMOUNT /dDATE`

* The `DESCRIPTION` cannot contain punctuation or any special characters.
* The `AMOUNT` must be a positive number.
* The `DATE` must be of format "yyyy-MM-dd".

Example of usage: 

`addIncome monthly salary /amt2500 /d2023-09-30`

`addIncome amazon purchase refund /amt50 /d2023-10-10`

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
