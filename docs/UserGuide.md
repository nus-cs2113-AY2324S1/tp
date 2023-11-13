# KaChinnnng User Guide

## Introduction
Welcome to KaChinnnng, a personal finance tracker that helps you keep track of your expenses and income.

## Quick Start


1. Ensure that you have Java 11 installed.
2. Download the latest version of `KaChinnnng` from [here](https://github.com/AY2324S1-CS2113-T18-3/tp/releases).
3. Copy the downloaded file to the folder you want to use as the home folder that allow to write access for your KaChinnnng.
4. Open a command terminal and navigate to the folder where you have copied the file to.
5. Type in the following to run the program
```
java -jar tp.jar
```
6. If successful you should see the following output
```
____________________________________________________________
Welcome to KaChinnnngggg! How may i assist you today?
____________________________________________________________
```
7. Type in desired commands and press enter to execute them. (note: commands are not case-sensitive)
8. "[ ]" denotes optional parameters
9. Do not add additional characters behind the command e.g. `add incomeabc /de salary /date 01/01/2020 /amt 1000`.

<div style="page-break-after: always;"></div>

## Features 

### View help : help
Shows basic commands executable by the program. 
User can choose to view the help function of individual command separately as well, which shows the description and format of that command

Format:
```
help
```
```
help add
```
```
help list
```
```
help delete
```
```
help edit
```
```
help balance
```
```
help update exchange rate
```
```
help find
```
```
help clear
```
```
help exit
```

<div style="page-break-after: always;"></div>

### Add an entry: add
Creates a new entry for income or expenses in the program.
Values of income and expense added has to be lower than 1 000 000.
Income and Expense values takes up to 2 decimal places.

To add an income (with foreign currency):
```
add income /de <description> /date <date> /amt [currency] <amount>
```
Note:
- Fields `/de`, `/date`, and `/amt` are case-sensitive and should be in the specified order.
- Users should not use `|` in the description as it is used as a delimiter in the storage file.

To add an expense (with foreign currency):
```
add expense /cat <category> /type <type> /de <description> /date <date> /amt [currency] <amount>
```
Note:
- Fields `/cat`, `/type`, `/de`, `date`, and `amt` are case-sensitive and should be in the specified order.
- Should users enter `/cat`, `/type` or the other fields that is case-sensitive, system will take it as missing field.
- Users should not use `|` in the description as it is used as a delimiter in the storage file.
- There are only 3 categories for expenses: `Food`, `Transport`, `Utilities`.
- There are 3 types associated with `Food` category: `Breakfast`, `Lunch`, `Dinner`, else it will default to `OTHER`.
- There are 4 types associated with `Transport` category: `Bus`, `Train`, `Taxi`, `Fuel`, else it will default to `OTHER`.
- There are 3 types associated with `Utilities` category: `Water`, `Electricity`, `Gas`, else it will default to `OTHER`.


Use case:
- Prompts users if any of the fields are empty.
- Amount takes up to 2 decimal places.
- format of date is `dd/MM/yyyy`.
- Date needs to be an existing date, and cannot be dates in the future.
- By default, amount is set to SGD.
- User can specify the currency by using the `update exchange rate` command before using the desired currency. Refer to the supported currencies for valid currency.
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
add expense /cat Food /type breakfast /de chicken sandwich /date 01/01/2020 
/amt USD 10.50
```
```
add expense /cat transport /type train /de train to school /date 10/10/2023 
/amt 10.00
```
Expected output after successfully add income to the list:
![add_income_expected.png](https://github.com/AY2324S1-CS2113-T18-3/tp/blob/master/images/income_expected.png?raw=true)<br>
Expected output after successfully add expense to the list:
![add_expense_expected.png](https://github.com/AY2324S1-CS2113-T18-3/tp/blob/master/images/expense_expected.png?raw=true)<br>

<div style="page-break-after: always;"></div>

### List all entries: list
Shows a full list of both the expenses and income created by the user.
User can choose to view the list of income/expenses separately as well.
There must be at least one entry in the list before using this command else it will show an error message.

Format:
```
list
```
List all entries in the list.
```
list incomes
```
List all income entries in the list.
```
list expenses
```
list all expense entries in the list.

### Delete an entry: delete
Deletes an entry from the list of income/expenses.
There must be at least one entry in the list before using this command else it will show an error message.

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
- `<index>` should be a positive integer that corresponds to an income/expense entry.

Expected output after successfully delete income from the list:
![delete_income_expected.png](https://github.com/AY2324S1-CS2113-T18-3/tp/blob/master/images/delete_expected.png?raw=true)<br>

<div style="page-break-after: always;"></div>

### Find an entry: find
Find an entry from the existing list of income/expenses.

Format:
```
find /t income [/de description] [/date date]
```
```
find /t expense [/cat category] [/de description] [/date date]
```

Example of usage:

```
find /t income /de salary
```
Finding income with description
```
find /t income /date 01/01/2020
```
Finding income with date
```
find /t expense /cat food /de sushi
```
Finding expense with category and description


Note:
- Fields `/t`, `/cat`, `/de`, `/date` are case-sensitive and should be in the specified order.
- Field `/cat` is only applicable for expenses.
- Field `/t` is compulsory.
- At least one of the optional fields [/c CATEGORY], [/de DESCRIPTION], [/date DATE] must be provided.
- Should users add additional characters behind find e.g. findABCDE, the system will still recognise it as find.

Use case:
- Prompts users if `/t <type>` field is empty.
- Prompts users if all the optional fields `/cat`, `/de`, `/date`. are empty.
- Format of date is `dd/MM/yyyy`. Users can also opt to search for entries by month `MM/yyyy`.
- Date needs to be an existing date, and cannot be dates in the future.

<div style="page-break-after: always;"></div>

### Clear all entries: Clear
Clear the expenses/income created by the user.
User can choose to clear both list as well.

Format:
```agsl
clear incomes
```

```agsl
clear expenses
```

```agsl
clear all
```

Example of successfully clear incomes list:
![clear_incomes_expected.png](https://github.com/AY2324S1-CS2113-T18-3/tp/blob/master/images/clear_income.png?raw=true)<br>
Example of successfully clear expenses list:
![clear_expenses_expected.png](https://github.com/AY2324S1-CS2113-T18-3/tp/blob/master/images/clear_expense.png?raw=true)<br>
Example of successfully clear both income and expense list:
![clear_all_expected.png](https://github.com/AY2324S1-CS2113-T18-3/tp/blob/master/images/clear_all.png?raw=true)<br>


### Check balance: balance
Check the balance for current financial records where balance = total income - total expenses.

Format:
```
balance
```
<div style="page-break-after: always;"></div>

### Edit an entry: edit 
Edit an entry from the list of income/expenses.

Format:
```
edit income <index> /de <description> /date <date> /amt [currency] <amount>
```
```
edit expense <index> /cat <category> /type <type> /de <description> /date <date> 
/amt [currency] <amount>
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
Example of successfully edit an income with foreign currency:
![clear_incomes_expected.png](https://github.com/AY2324S1-CS2113-T18-3/tp/blob/master/images/edit_income_expected.png?raw=true)<br>
Example of successfully edit an expense:
![edit_expense_expected.png](https://github.com/AY2324S1-CS2113-T18-3/tp/blob/master/images/edit_expense_expected.png?raw=true)<br>

Note:
- Fields `/cat`, `/type`, `/de`, `/date`, and `/amt` are case-sensitive and should be in the specified order.
- Should users enter `/cat`, `/type` or the other fields that is case-sensitive, system will take it as missing field.
- `<index>` should not be negative.
- `<index>` should not be out of bounds.
- `<amount>` takes up to 2 decimal places.

Use case:
- Prompts users if index is empty.
- Prompts users if any of the fields are empty.

<div style="page-break-after: always;"></div>

### List the supported currencies: list currencies
Show the foreign currencies supported by KaChinnnng.

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

<div style="page-break-after: always;"></div>

### Storage for income and expense entries
Save all the income and expense entries to the KaChinnnngggg.txt

All income and expense entries will be updated to the file for every command input.

There is no command to save to file manually since it's updating after every command

### Retrieve entries from text file and save to the income and expense list
Retrieve all the income and expense entries from the KaChinnnngggg.txt

At the beginning of executing the program, KaChinnnng will automatically retrieve all the entries storied in the KaChinnnngggg.txt

There is no command to get from file manually since it will run once at the beginning of program.



## FAQ


**Q**: How do I transfer my data to another computer? 

**A**: Move the txt file from the folder where the jar file located, 
to the folder where the jar file located in the new computer 

**Q**: What do I need to do when I see the `format incorrect from storage file` at the beginning of running the program

**A**: The system will automatically remove that line from the txt after you give the first command to the bot, 
if you don't want to remove that line, just terminate the program using control c right after you see the error,
and change the line in txt file to the correct format.

<div style="page-break-after: always;"></div>

## Command Summary



| Action               | Format, Examples                                                                                                                                                                                            |
|----------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Help                 | `help` <br> `help add` <br> `help list` <br> `help list` <br> `help delete` <br> `help edit` <br> `help balance` <br> `help update exchange rate` <br> `help clear` <br> `help find` <br> `help balance`    |
| Add Income           | `add income /de <description> /date <date> /amt [currency] <amount>` <br> e.g., `add income /de salary /date 01/01/2020 /amt 1000`                                                                          |
| Add Expense          | `add expense /cat <category> /type <type> /de <description> /date <date> /amt [currency] <amount>` <br> e.g., `add expense /cat Food /type lunch /de sushi /date 01/01/2020 /amt 10.50`                    |
| List                 | `list` <br> `list incomes` <br> `list expenses` <br> `list currencies` <br> `list exchange rates`                                                                                                           |
| Delete               | `delete expense <index>` <br> `delete income <index>`                                                                                                                                                       |
| Edit                 | `edit income <index> /de <description> /date <date> /amt [currency] <amount>` <br> `edit expense <index> /cat <category> /type <type> /de <description> /date <date> /amt [currency] <amount>`             |
| Update Exchange Rate | `update exchange rate <supported_currency> <rate>`                                                                                                                                                          |
| Clear                | `clear incomes`<br/> `clear expenses`<br/>`clear all`                                                                                                                                                       |
| Find                 | `find /t income /de [description] /date [date]`<br/> e.g. `find /t income /de salary` <br/>`find /t expense /cat [category] /de [description] /date [date]`<br/> e.g. `find /t expense /cat food /de sushi` |
| Balance              | `balance`                                                                                                                                                                                                   |
| Exit                 | `exit`                                                                                                                                                                                                      |
