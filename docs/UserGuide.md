# User Guide

## Introduction

CashLeh? is a CLI application mainly supporting working adults and students who struggle with managing finances
(ranging from one-time and recurring expenses, to rent, utilities, interest rates and more). It allows them to track
both their earnings and spending habits in a seamless way to have a neat overview of their financial 
situation.

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

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
* Anything following the command will be ignored, i.e. `viewIncomes overview` will be interpreted just like `viewIncomes`.

Example of usage:

`viewIncomes`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add income `addIncome monthly salary /amt2500 /d2023-09-30`
* Delete income `deleteIncome 4`
* View incomes `viewIncomes`
