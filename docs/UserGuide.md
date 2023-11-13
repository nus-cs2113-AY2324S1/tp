# User Guide 💰

## Table of Contents
- [Introduction](#introduction-)
- [Prerequisite](#prerequisite-)
- [Quick Start](#quick-start-)
- [Features](#features-)
   - [Viewing help: `help`](#viewing-help-help)
   - [Adding an allowance: `allowance`](#adding-an-allowance-allowance)
   - [Adding an expense: `expense`](#adding-an-expense-expense)
   - [Adding a budget: `budget`](#adding-a-budget-budget)
   - [Deleting an entry: `delete`](#deleting-an-entry-delete)
   - [Listing all entries: `list`](#listing-all-entries-list)
   - [Filtering entries by category: `filter`](#filtering-entries-by-category-filter)
   - [Viewing an entry: `view`](#viewing-an-entry-view)
   - [Editing an entry: `edit`](#editing-an-entry-edit)
   - [Exiting the program: `exit`](#exiting-the-program-exit)
- [FAQ](#faq-)
- [Command Summary](#command-summary-)

-----------------------------------------------------------------------------------------------------------------------

## Introduction 🏦

Welcome to 🪙NUScents🪙, the tailor-made financial tracker for SOC students at
NUS. It is optimized for use via a Command Line Interface (CLI) to offer a clutter-free
solution for our users to manage and monitor their financial activities.

## Prerequisite 💵
Make sure you have Java `11` or above installed on your system.

## Quick Start 💷

1. Download the latest JAR file (`tp.jar`).
2. Copy the JAR file to the folder you want to use as the **home folder** for your financial tracker.
3. Open a command terminal, `cd` into the folder you put the jar file in.
4. Enter the following command to run the program:

   ```
   java -jar tp.jar
   ```

5. You should see a welcome message as shown below:

   ```
      Hello from
       __   __  __   __   ____   _____ _____ __   __ ________   ____
      |  \ |  |  |  |  |/     / /   __|     |  \ |  |__    __|/     /
      |   \|  |  |  |  |\   __\|   /  |   __|   \|  |  |  |   \   __\
      |       |  |  |  | \__   |  |   |   __|       |  |  |    \__   |
      |       |  |__|  |/      |   \__|     |       |  |  |   /      |
      |__|\___|________|______/ \_____|_____|__|\___|  |__|   |_____/
   
      ---------------------------------------------------------------------------------------------
      What can I do for you?
      Hint: To view a list of all possible commands, please enter 'help'.
      ---------------------------------------------------------------------------------------------
   ```

## Features 📊

- Words in UPPER_CASE are the parameters to be supplied by the user.
  e.g. in `allowance /amt AMOUNT`, `AMOUNT` is a parameter that can be used
  as `allowance /amt 1000`.
- Items in square brackets are optional.
  e.g. `allowance` can be used with or without `/note`.
- All parameters are case-insensitive, i.e. `/amt` and `/AMT` are the same.
- All categories are case-insensitive, i.e. `food` and `FOOD` are the same.
- The commands provided in the following examples are presented in a sequential order, and their expected outputs are for illustrative purposes only. It is important to note that using these commands in a different order may result in outputs that do not exactly match those shown in this guide. However, any such variations would still be consistent with the logical use of the application.

### Viewing help: `help`
To view a list of all possible commands, a brief description of their
functionality and syntax.

Format: `help`

Expected output:
```
---------------------------------------------------------------------------------------------
- `list` :
  List all entries.
- `allowance /amt AMT /date DATE /desc DESC [/note NOTE] [/cat CAT]` :
  Add an allowance.
- `expense /amt AMT /date DATE /desc DESC [/note NOTE] [/cat CAT]` :
  Add an expense.
- `delete INDEX` :
  Delete an entry.
- `budget AMOUNT` :
  Set a budget amount for expenses.
- `filter CATEGORY` :
  Filter by category.
- `view INDEX` :
  View entry detail.
- `edit INDEX ENTRY` :
  Edit an existing transaction (same format as adding a transaction).
- `exit` :
  Exit program.
---------------------------------------------------------------------------------------------
```

### Adding an allowance: `allowance`

To add a new allowance entry to the financial tracker, please adhere to the given format!  

> 📑 Note   
> Fields in square brackets `[ ]` can be omitted.   
> The categories are case-insensitive, i.e. `SALARY` and `salary` are the same.

Possible categories for allowance are:
- SALARY
- ALLOWANCE
- INVESTMENTS
- GIFTS
- OTHER_ALLOWANCE
- NO_ALLOWANCE_CATEGORY

Format:   
1) `allowance /amt AMOUNT /date DATE /desc DESCRIPTION [/note ADDITIONAL_INFORMATION] [/cat CATEGORY]`  
   e.g., `allowance /amt 200 /date 30-10-2023 /desc pocket money /note November /cat allowance`   
   Expected output:
```
---------------------------------------------------------------------------------------------
Got it. I've added this transaction:
  pocket money
Now you have 1 transactions in the list.
---------------------------------------------------------------------------------------------
```

2) `allowance /amt AMOUNT /date DATE /desc DESCRIPTION`  
   e.g., `allowance /amt 200 /date 30-10-2023 /desc pocket money`
   Expected output:
```
---------------------------------------------------------------------------------------------
Got it. I've added this transaction:
  pocket money
Now you have 2 transactions in the list.
---------------------------------------------------------------------------------------------
```

### Adding an expense: `expense`

To add a new expense entry to the financial tracker, please adhere to the given format!

> 📑 Note   
> Fields in square brackets `[ ]` can be omitted.   
> The categories are case-insensitive, i.e. `FOOD` and `food` are the same.

Possible categories for allowance are:
- FOOD
- ENTERTAINMENT
- TRANSPORTATION
- UTILITY
- RENT
- OTHER_EXPENSE
- NO_EXPENSE_CATEGORY:"" or "no_expense_category"
Note that letter case doesn't matter.

Format:   
1) `expense /amt AMOUNT /date DATE /desc DESCRIPTION [/note ADDITIONAL_INFORMATION] [/cat CATEGORY]`  
   e.g., `expense /amt 20 /date 30-10-2023 /desc lunch /note pasta /cat food`   
   Expected output:
```
---------------------------------------------------------------------------------------------
Got it. I've added this transaction:
  lunch
Now you have 3 transactions in the list.
---------------------------------------------------------------------------------------------
```

2) `expense /amt AMOUNT /date DATE /desc DESCRIPTION`   
   e.g., `expense /amt 20 /date 30-10-2023 /desc lunch`
   Expected output:
```
---------------------------------------------------------------------------------------------
Got it. I've added this transaction:
  lunch
Now you have 4 transactions in the list.
---------------------------------------------------------------------------------------------
```

### Adding a budget: `budget`

To add a budget to your expenses.   
Note: The budget must be larger than 0 as it does not make sense to set budget as 0.

Format: `budget AMOUNT`  
e.g., `budget 200`
Expected output:
```
---------------------------------------------------------------------------------------------
Budget set to:
  $200.0
---------------------------------------------------------------------------------------------
```

### Deleting an entry: `delete`

To delete an entry from the financial tracker, which can be either an allowance or an expense.

Format: `delete INDEX`  
e.g., `delete 1`
Expected output:
```
---------------------------------------------------------------------------------------------
Noted. I've removed this transaction:
  pocket money
Now you have 3 transactions in the list.
---------------------------------------------------------------------------------------------
```

### Listing all entries: `list`

To list all entries in the financial tracker.

Format: `list`
Expected output:
```
---------------------------------------------------------------------------------------------
Here are the transactions in your list:
---------------------------------------------------------------------------------------------
S/N    TYPE        AMOUNT      DATE                DESCRIPTION      NOTE        CATEGORY 
---------------------------------------------------------------------------------------------
1      Allowance   $200.00     30 October, 2023    pocket money     -           -     
2      Expense     $20.00      30 October, 2023    lunch            pasta       FOOD  
3      Expense     $20.00      30 October, 2023    lunch            -           -     
---------------------------------------------------------------------------------------------
Net Balance = $160.00
---------------------------------------------------------------------------------------------
Initial budget set to  = $200.00
Expenses  = $40.00
Total expenses are still within budget.
Remaining budget: $160.00
---------------------------------------------------------------------------------------------
```

### Filtering entries by category: `filter`

To filter entries in the financial tracker by category.

> 📑 Note
> The categories are case-insensitive, i.e. `SALARY` and `salary` are the same.

The current supported categories are:
- Allowance:
   - SALARY
   - ALLOWANCE
   - INVESTMENTS
   - GIFTS
   - OTHER_ALLOWANCE
   - NO_ALLOWANCE_CATEGORY
- Expenses:
   - FOOD
   - ENTERTAINMENT
   - TRANSPORTATION
   - UTILITY
   - RENT
   - OTHER_EXPENSE
   - NO_EXPENSE_CATEGORY

Format: `filter CATEGORY`  
e.g., `filter FOOD`
Expected output:
```
---------------------------------------------------------------------------------------------
Filtered transactions in the category FOOD:
---------------------------------------------------------------------------------------------
S/N    TYPE        AMOUNT   DATE                DESCRIPTION      NOTE        CATEGORY 
---------------------------------------------------------------------------------------------
1      Expense     $20.00   30 October, 2023    lunch            pasta       FOOD  
---------------------------------------------------------------------------------------------
Total amount for FOOD = 20.00
---------------------------------------------------------------------------------------------
```

### Viewing an entry: `view`

To view an entry in the financial tracker, which can be either an allowance or an expense.

Format: `view INDEX`  
e.g., `view 2`
Expected output:
```
---------------------------------------------------------------------------------------------
Following are details of the transaction:
TYPE: EXPENSE
DATE: 30 October, 2023
AMOUNT: 20.0
DESCRIPTION: lunch
NOTE: pasta
CATEGORY: FOOD
---------------------------------------------------------------------------------------------
```

### Editing an entry: `edit`

To edit an entry in the financial tracker, which can be either an allowance or an expense.

Format:
1) `edit INDEX allowance /amt AMOUNT /date DATE /desc DESCRIPTION [/note ADDITIONAL_INFORMATION] [/cat CATEGORY]`  
   e.g., `edit 1 allowance /amt 150 /date 30-10-2023 /desc pocket money`
   Expected output:
```
---------------------------------------------------------------------------------------------
Here are the transactions in your list:
---------------------------------------------------------------------------------------------
S/N    TYPE        AMOUNT      DATE                DESCRIPTION      NOTE        CATEGORY 
---------------------------------------------------------------------------------------------
1      Allowance   $150.00     30 October, 2023    pocket money     -           -     
2      Expense     $20.00      30 October, 2023    lunch            pasta       FOOD  
3      Expense     $20.00      30 October, 2023    lunch            -           -     
---------------------------------------------------------------------------------------------
Net Balance = $110.00
---------------------------------------------------------------------------------------------
```

2) `edit INDEX expense /amt AMOUNT /date DATE /desc DESCRIPTION [/note ADDITIONAL_INFORMATION] [/cat CATEGORY]`  
   e.g., `edit 3 expense /amt 20 /date 30-10-2023 /desc dinner /note noodles /cat food`
   Expected output:
```
---------------------------------------------------------------------------------------------
Here are the transactions in your list:
---------------------------------------------------------------------------------------------
S/N    TYPE        AMOUNT      DATE                DESCRIPTION      NOTE        CATEGORY 
---------------------------------------------------------------------------------------------
1      Allowance   $150.00     30 October, 2023    pocket money     -           -     
2      Expense     $20.00      30 October, 2023    lunch            pasta       FOOD  
3      Expense     $20.00      30 October, 2023    dinner           noodles     FOOD  
---------------------------------------------------------------------------------------------
Net Balance = $110.00
---------------------------------------------------------------------------------------------
```
### Exiting the program: `exit`

To exit the program.

Format: `exit`
Expected output:
```
---------------------------------------------------------------------------------------------
Thank you for using NUScents. Hope to see you again soon!
---------------------------------------------------------------------------------------------
```

## FAQ ❓

**Q**: How do I transfer my data to another computer?

**A**: There are 2 files used for storage, `./data/nuscents.txt` and `./data/hmac`. Copy both files over to the target
computer.

**Q**: What happens if I modify the storage file ?

**A**: The application is programmed to exit immediately if it detects that the storage file has been tampered with.
You will either need to revert the storage file back to the last known 'good state', or delete both the
`./data/nuscents.txt` and `./data/hmac` files and run the program again. Note that deleting the files will cause a loss
of previously stored data.

## Command Summary 💡

| Action            | Format, Examples                                                                                                                                                                                      |
|-------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Help**          | `help`                                                                                                                                                                                                |
| **Add Allowance** | `allowance /amt AMOUNT /date DATE /desc DESCRIPTION [/note ADDITIONAL_INFORMATION] [/cat CATEGORY]` <br> e.g., `allowance /amt 200 /date 30-10-2023 /desc pocket money /note November /cat allowance` |
| **Add Expense**   | `expense /amt AMOUNT /date DATE /desc DESCRIPTION [/note ADDITIONAL_INFORMATION] [/cat CATEGORY]` <br> e.g., `expense /amt 20 /date 30-10-2023 /desc lunch /note pasta /cat food`                     |
| **Add Budget**    | `budget AMOUNT`<br> e.g., `budget 200`                                                                                                                                                                |
| **Delete**        | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                   |
| **List**          | `list`                                                                                                                                                                                                |
| **Filter**        | `filter CATEGORY`                                                                                                                                                                                     |
| **View**          | `view INDEX`                                                                                                                                                                                          |
| **Edit**          | `edit INDEX ENTRY` <br> e.g., `edit 2 allowance /amt 200 /date 30-10-2023 /desc pocket money /note November /cat allowance`                                                                           |
| **Exit**          | `exit`                                                                                                                                                                                                |
