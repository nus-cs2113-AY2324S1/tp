# KaChinnnng User Guide

## Introduction
Welcome to KaChinnnng a personal finance tracker that helps you keep track of your expenses and income.

## Quick Start


1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `KaChinnnng` from [here](https://github.com/AY2324S1-CS2113-T18-3/tp/releases).
3. Copy the downloaded file to the folder you want to use as the home folder that allow to write access for your KaChinnnng.
4. open a command terminal and navigate to the folder where you have copied the file to.
5. type in the following to run the program
```
java -jar tp.jar
```
6. if successful you should see the following output
```
____________________________________________________________
Welcome to KaChinnnngggg! How may i assist you today?
____________________________________________________________
```
7. type in desired commands and press enter to execute them. (note: commands are not case-sensitive)
8. "[ ]" denotes optional parameters

## Features 

Bryan-updatePPP


master
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

To add an income (with foreign currency):
```
add income /de <description> /date <date> /amt [currency] <amount>
```
Note:
- Fields `/de`, `/date`, and `/amt` are case-sensitive and should be in the specified order.
- Users should not use "|" in the description as it is used as a delimiter in the storage file.

To add an expense (with foreign currency):
```
add expense /cat <category> /type <type> /de <description> /date <date> /amt [currency] <amount>
```
Note:
- Fields `/cat`, `/type`, `/de`, `date`, and `amt` are case-sensitive and should be in the specified order.
- Should users enter `/cat`, `/type` or the other fields that is case-sensitive, system will take it as missing field.
- Users should not use "|" in the description as it is used as a delimiter in the storage file.
- There are only 3 categories for expenses: `Food`, `Transport`, `Utilities`.
- There are 3 types associated with `Food` category: `Breakfast`, `Lunch`, `Dinner`, else it will default to `OTHER`.
- There are 4 types associated with `Transport` category: `Bus`, `Train`, `Taxi`, `Fuel`, else it will default to `OTHER`.
- There are 3 types associated with `Utilities` category: `Water`, `Electricity`, `Gas`, else it will default to `OTHER`.


Use case:
- Prompts users if any of the fields are empty.
- Amount takes up to 2 decimal places.
- format of date is `dd/mm/yyyy`.
- Date needs to be an existing date, and cannot be dates in the future.
- By default, amount is set to SGD.
- User can specify the currency. Refer to the supported currencies for valid currency.
- Currency specified must have been updated. Refer to list exchange rates for more details.

Example of usage:
```
add income /de salary /date 01/01/2020 /amt 1000
```
```
add expense /cat transport /type taxi /de taxi to school /date 10/10/2023 /amt 10.00
```
```
add expense /cat Food /type lunch /de lunch /date 01/01/2020 /amt 10.50
```
```
add expense /cat Food /type breakfast /de chicken sandwich /date 01/01/2020 /amt USD 10.50
```
```
add expense /cat transport /type train /de train to school /date 10/10/2023 /amt 10.00
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


Use case:
- Prompts users if index is empty.
- `<index>` should not be negative.
- `<index>` should not be out of bounds.

### Clear all entries: Clear
Clearing all entries from the list of income/expenses.

Format:
```agsl
clear income
```

```agsl
clear expense
```

```agsl
clear all
```

### Find an entry: find
Find an entry from the existing list of income/expenses.

Format:
```
find /t income /de [description] /date [date]
```
```
find /t expense /cat [category] /de [description] /date [date]
```

Example of usage:
```
find /t income /de salary
```
```
find /t expense /cat food /de sushi
```

Note:
- Fields `/t`, `/cat`, `/de`, `date` are case-sensitive and should be in the specified order.
- Field `/cat` is only applicable for expenses.
- Field `/t` is compulsory.
- Should users add additional characters behind find e.g. findABCDE, the system will still recognise it as find.

Use case:
- Prompts users if `/t <type>` field is empty.
- Prompts users if all the optional fields `cat`, `/de`, `/date`. are empty.
- Format of date is `dd/mm/yyyy`. Users can also opt to search for entries by month `mm/yyyy`.
- Date needs to be an existing date, and cannot be dates in the future.


Note: <index> must be a positive integer that corresponds to an income/expense entry

### Clear a list
Clear all the entries on the income/expenses or both list.

Format:
```
clear incomes
```
```
clear expenses
```
```
clear all
```


### Check balance: balance
Check the balance for current financial records

Format:
```
balance
```

### Edit an entry: edit 
Edit an entry from the list of income/expenses.

Format:
```
edit income <index> /de <description> /date <date> /amt [currency] <amount>
```
```
edit expense <index> /cat <category> /type <type> /de <description> /date <date> /amt [currency] <amount>
```
Note:
- `index` must be a positive integer that corresponds to an income/expense entry
- Format of income/expense entry applies. Refer to the "Note" of add income and add expense feature

Example of usage:
```
edit income 1 /de end of year bonus /date 02/10/2023 /amt HKD 3000.00
```
```
edit expense 2 /cat food /type dinner /de dinner /date 01/10/2023 /amt 10.00
```

Note:
- Fields `/cat`, `/type`, `/de`, `date`, and `amt` are case-sensitive and should be in the specified order.
- Should users enter `/cat`, `/type` or the other fields that is case-sensitive, system will take it as missing field.
- `<index>` should not be negative.
- `<index>` should not be out of bounds.
- `<amount>` takes up to 2 decimal places.

Use case:
- Prompts users if index is empty.
- Prompts users if any of the fields are empty.


### List the supported currencies: list currencies
This function allows user to see the foreign currencies supported by KaChinnnng.

Note: The default currency is SGD.

Format:
```
list currencies
```
Example output:
```
These are the supported currencies:
MYR USD JPY KRW EUR THB HKD INR IDR AUD GBP CNY CAD TWD VND PHP
```

### List existing exchange rates: list exchange rates
List all updated exchange rates. All exchange rates that are updated or loaded
from ExchangeRate.txt will be shown. User can call the update exchange rates function to 
register new exchange rates into their KaChinnnng.

Note: Exchange rates are shown in SGD/{other currency}.

Format:
```
list exchange rates
```

### Update exchange rate
Update exchange rate of a specific foreign currency. Exchange rates will be saved upon
successful update.
  
Format:
```
update exchange rate <supported_currency> <rate>
```
Example of usage:
```
update exchange rate USD 0.8
```
Note:
- `<currency>` must be one of the foreign currencies supported by KaChinnnng.
- User may view the supported foreign currencies with `list currencies`.
- `<rate>` specified should be in SGD/{foreign currency}.
- `<rate>` must be a positive decimal that is between 0.001 and 3,000,000.
- The update of a previously used exchange rate will not retroactively affect entries made prior to the update.

### Storage for income and expense entries
Save all the income and expense entries to the KaChinnnngggg.txt

All income and expense entries will be update to the file for every command input.

There is no command to save to file manually since it's updating after every command

### Retrieve entries from text file and save to the income and expense list
Retrieve all the income and expense entries from the KaChinnnngggg.txt

At the beginning of executing the program, KaChinnnngggg will automatically retrieve all the entries storied in the KaChinnnngggg.txt

There is no command to get from file manually since it will run once at the beginning of program.


## FAQ


**Q**: How do I transfer my data to another computer? 

**A**: Move the txt file from the folder where the jar file located, 
to the folder where the jar file located in the new computer 

**Q**: What do I need to do when I see the `format incorrect from storage file` at the beginning of running the program

**A**: The system will automatically remove that line from the txt after you give the first command to the bot, 
if you don't want to remove that line, just terminate the program using control/command c right after you see the error,
and change the line in txt file to the correct format.

## Command Summary


| Action               | Format, Examples                                                                                                                                                                                            |
|----------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Help                 | `help`                                                                                                                                                                                                      |
| Add Income           | `add income /de <description> /date <date> /amt [currency] <amount>` <br> e.g., `add income /de salary /date 01/01/2020 /amt 1000`                                                                          |
| Add Expense          | `add expense /cat <catergory> /type <type> /de <description> /date <date> /amt [currency] <amount>` <br> e.g., `add expense /cat Food /type lunch /de sushi /date 01/01/2020 /amt 10.50`                    |
| List                 | `list` <br> `list income` <br> `list expense` <br> `list currencies` <br> `list exchange rates`                                                                                                             |
| Delete               | `delete expense <index>` <br> `delete income <index>`                                                                                                                                                       |
| Edit                 | `edit income <index> /de <description> /date <date> /amt [currency] <amount>` <br> `edit expense <index> /cat <catergory> /type <type> /de <description> /date <date> /amt [currency] <amount>`             |
| Update Exchange Rate | `update exchange rate <supported_currency> <rate>`                                                                                                                                                          |
| Clear                | `clear income`<br/> `clear expense`<br/>`clear all`                                                                                                                                                         |
| Find                 | `find /t income /de [description] /date [date]`<br/> e.g. `find /t income /de salary` <br/>`find /t expense /cat [category] /de [description] /date [date]`<br/> e.g. `find /t expense /cat food /de sushi` |
| Balance              | `balance`                                                                                                                                                                                                   |
| Exit                 | `exit`                                                                                                                                                                                                      |
                                                                                                                                                                                              |

                                                                                                                                                                                       |
