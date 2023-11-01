# KaChinnnng User Guide

## Introduction
Welcome to KaChinnnng a personal finance tracker that helps you keep track of your expenses and income.

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
8. "[ ]" denotes optional parameters

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

To add an income (with foregin currency):
```
add income /de <description> /date <date> /amt [currency] <amount>
```

To add an expense (with foregin currency):
```
add expense /cat <catergory> /type <type> /de <description> /date <date> /amt [currency] <amount>
```

Use case:
- Prompts users if any of the fields are empty.
- There are only 3 categories for expenses: `Food`, `Transport`, `Utilities`.
- Amount is takes up to 2 decimal places.
- by default, amount is set to SGD.
- User can specify the currency. Refer to the supported currencies for valid currency.
- format of date is `dd/mm/yyyy`.
- Date needs to be an existing date, and cannot be dates in the future.

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
edit expense <index> /cat <catergory> /type <type> /de <description> /date <date> /amt [currency] <amount>
```
Example of usage:
```
edit income 1 /de end of year bonus /date 02/10/2023 /amt HKD 3000.00
```
```
edit expense 2 /cat food /type dinner /de dinner /date 01/10/2023 /amt 10.00
```

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

### List exisitng exchange rates: list exchange rates
List all updated exchange rates. All exchange rates that are updated
either via ExchangeRateFile or the command line will be shown. User can call the update exchange rates function to 
register the exchange rates into their KaChinnnng.

Note: Exchange rates are shown in SGD/{other currency}.

Format:
```
list exchange rates
```

### update exchange rate
Update exchange rate of a specific foreign currency. Exchange rates will be saved upon
successful update.

Note: rate specified should be in SGD/{foreign currency}.

Format:
```
update exchange rate <supported_currency> <rate>
```
Example
```
update exchange rate USD 0.8
```

## FAQ


**Q**: How do I transfer my data to another computer? 

**A**: Move the txt file from the folder where the jar file located, 
to the folder where the jar file located in the new computer 

**Q**: What do i need to do when I see the `format incorrect from storage file` at the beginning of running the program

**A**: The system will automatically remove that line from the txt after you give the first command to the bot, 
if you don't want to remove that line, just terminate the program using control/command c right after you see the error,
and change the line in txt file to the correct format.

## Command Summary

| Action | Format, Examples                                                                                                                                                                         |
| ------ |------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Help | `help`                                                                                                                                                                                   |
| Add Income | `add income /de <description> /date <date> /amt [currency] <amount>` <br> e.g., `add income /de salary /date 01/01/2020 /amt 1000`                                                       |
| Add Expense | `add expense /cat <catergory> /type <type> /de <description> /date <date> /amt [currency] <amount>` <br> e.g., `add expense /cat Food /type lunch /de sushi /date 01/01/2020 /amt 10.50` |
| List | `list` <br> `list income` <br> `list expense` <br> `list currencies` <br> `list exchange rates`|
| Delete | `delete expense <index>` <br> `delete income <index>`                                                                                                                                     |
| Edit | `edit income <index> /de <description> /date <date> /amt [currency] <amount>` <br> `edit expense <index> /cat <catergory> /type <type> /de <description> /date <date> /amt [currency] <amount>` |
| Update Exchange Rate | `update exchange rate <supported_currency> <rate>` |
| Clear |`clear`|
| Exit |`exit`|
