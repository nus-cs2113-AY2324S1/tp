# KaChinnnng User Guide

## Introduction
welcome to KaChinnnng a personal finance tracker that helps you keep track of your expenses and income.

## Quick Start


1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `KaChinnnng` from [here](http://link.to/duke).
3. Copy the downloaded file to the folder you want to use as the home folder for your KaChinnnng.
4. open a command terminal and navigate to the folder where you have copied the file to.
5. type in the following to run the program
```
java -jar KaChinnnng.jar
```
6. if successful you should see the following output
```
____________________________________________________________
Welcome to KaChinnnngggg! How may i assist you today?
____________________________________________________________
```
7. type in desired commands and press enter to execute them. (note: commands are not case sensitive)

## Features 

{Give detailed description of each feature}

### View help : help
Shows basic commands executable by the program.

Format:
```
help
```

### Add an entry: add
Creates a new entry for income or expenses in the program.
Values of income and expense added has to be lower than 1000000.
Income and Expense values takes up to 2 decimal places.

To add an income:
```
add income /description <description> /date <date> /amount <amount>
```

To add an expense:
```
add expense /catergory <catergory> /description <description> /date <date> /amount <amount>
```

Use case:
- Prompts users if any of the fields are empty.
- There are only 3 categories for expenses: `Food`, `Transport`, `Utilities`.
- Amount is takes up to 2 decimal places.
- by default, amount is set to SGD.
- format of date is `dd/mm/yyyy`.
- Date needs to be an existing date, and cannot be dates in the future.

Example of usage:
```
add income /description salary /date 01/01/2020 /amount 1000
```
```
add expense /category Food /description lunch /date 01/01/2020 /amount 10.50
```

### List all entries: list
Shows a full list of both the expenses and income created by the user.
User can choose to view the list of income/expenses separately as well.

Format:
```
list
```
```
list income
```
```
list expense
```

### Delete an entry: delete
Deletes an entry from the list of income/expenses.

Format:
```
delete expense <index>
```
```
delete income <index>
```

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
