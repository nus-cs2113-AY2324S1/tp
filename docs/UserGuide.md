# User Guide

## Table of contents

* [Introduction](#introduction)
* [Quick start](#quick-start)
* [Features](#features)
  * [Budget](#budget)
    * [Setting budget](#setting-a-budget-budget-set)
    * [Updating budget](#updating-budget-budget-update)
    * [Resetting budget](#resetting-budget-budget-reset)
    * [Deleting budget](#deleting-budget-budget-delete)
    * [Viewing budget](#viewing-budget-budget-view)
  * [Displaying Overview](#displaying-overview-overview)
  * [View Balance](#viewing-balance-balance)
  * [WatchList](#viewing-watchlist-watchlist)
    * [Adding Stock](#adding-stock-to-watchlist-addstock)
    * [Deleting Stock](#deleting-budget-budget-delete)
  * [Visualization](#Visualization)
  * [Exiting the program](#exiting-the-program-exit)
  * [Saving data](#saving-the-data)
  * [Loading data](#loading-the-data)
* [FAQ](#faq)
* [Command Summary](#command-summary)

## Introduction

Financial Planner is a Command Line Interface (CLI) application for managing your finances conveniently. 
It is optimized for use via the CLI and leverages your expertise in CLI and your ability to type fast and gives 
you a one-stop interface to access a plethora of features to manage your finances.

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features

{Give detailed description of each feature}

### Budget

#### Setting a budget: `budget set`

Sets a monthly budget.

Format: `budget set /b BUDGET`

* `BUDGET` has to be a positive number.

Example of usage: `budget set /b 500`

Example output:

```
A monthly budget of 500.00 has been set.
```

#### Updating budget: `budget update`

Updates budget to a new value.

Format: `budget update /b BUDGET`

* `Budget` has to be a positive number.
* There has to be an existing budget.

Example of usage: `budget update /b 1000`

Example output:

```
Budget has been updated:
Old initial budget: 500.00
Old current budget: 500.00
New initial budget: 1000.00
New current budget: 1000.00
```

#### Resetting budget: `budget reset`

Resets current budget to initial budget if they are different.

Format: `budget reset`

* Budget will be reset to initial budget or current balance, whichever is lower.

Example of usage: `budget reset`

Example output:

```
Budget has been reset to 1000.00.
```

#### Deleting budget: `budget delete`

Deletes existing budget.

Format: `budget delete`

Example of usage: `budget delete`

Example output:

```
Budget has been deleted.
```

#### Viewing budget: `budget view`

View existing budget.

Format: `budget view`

Example of usage: `budget view`

Example output:

```
You have a remaining budget of 1000.00.
```

### Displaying overview: `overview`

Displays an overview of user's financials.

Format: `overview`

Example of usage: `overview`

Example output:

```
Here is an overview of your financials:
Total balance: 3790.00
Highest income: 5000.00    Category: Salary
Highest expense: 500.00    Category: Others
Remaining budget for the month: 1000.00

Reminders:
No reminders added yet.
```

### Viewing balance: `balance`

View user's current balance.

Format: `balance`

Example of usage: `balance`

Example output:

```
Balance: 3790.00
```

### Viewing Watchlist: `watchlist`

View your current watchlist with stocks that you are interested in

Default watchlist: AAPL, GOOGL

Format: `watchlist`

Example of usage: `watchlist`

Example of output:

```
Symbol    Market    Price     Daily High     Daily Low     EquityName
AAPL      NASDAQ    168.22    168.96         166.84        Apple Inc 
GOOGL     NASDAQ    122.17    123.31         120.2057      Alphabet Inc - Class A
GME       NYSE      13.12     13.615         13.02         Gamestop Corporation - Class A
```

### Adding Stock to Watchlist: `addstock`

Add a stock that you are interested in monitoring into your personal WatchList

Format: `addstock /s STOCKCODE`

Example of usage: `addstock /s META`

Example of output:

```
You have successfully added:
Meta Platforms Inc - Class A
Use Watchlist to view it!
```

### Deleting Stock from Watchlist: `deletestock`

Delete a stock that you are no longer interested in monitoring from your personal WatchList

Format: `deletestock /s STOCKCODE`

Example of usage: `deletestock /s META`

Example of output:

```
You have successfully deleted: 
Meta Platforms Inc - Class A
Use watchlist command to view updated Watchlist
```

### Visualizing your cashflow: `vis`

Using this command to visualize your income or expenses in a pie chart or bar char

Format: `vis /t TYPE /c TOOL`

Example of usage: `vis /t income /c pie`

Example of output:

```
Displaying piechart for expense
```

![](images/vis/visOutput.png)

### Exiting the program: `exit`

Exits the program.

Format: `exit`

### Saving the data

Data is automatically saved upon exiting the program using the `exit` command. Closing the program without exiting 
will not save the data.

### Loading the data

Existing data will be automatically loaded when the program starts up.

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

| Action               | Format                    |
|----------------------|---------------------------|
| **Set budget**       | `budget set /b BUDGET`    |
| **Update budget**    | `budget update /b BUDGET` |
| **Reset budget**     | `budget reset`            |
| **Delete budget**    | `budget delete`           |
| **View budget**      | `budget view`             |
| **Display Overview** | `overview`                |
| **View balance**     | `balance`                 |
| **Exit program**     | `exit`                    |
