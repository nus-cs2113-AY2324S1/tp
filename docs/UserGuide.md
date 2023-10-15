# User Guide

## Introduction

Stocker v1.o is a desktop app  that will provide quick access to currently available stock, 
track incoming stock and expiration dates, and categorize drugs based on different labels. 
It is optimized for use via a Command Line Interface (CLI). If you can type fast, Stocker 
can get your inventory management tasks done faster than traditional GUI apps.

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Stocker` from [here](http://link.to/duke).

## Features 

### Feature-Login System

Authentication system that allows user to register as a user or login
as an existing user.

### Feature-add

Adds a drug into the inventory list.

### Feature-delete

Deletes a drug being tracked by the system.

### Feature-list

List all drug information that is being tracked by the system.

### Feature-find

Finds drugs whose names contain any of the given keywords.

### Feature-help

List all currently available commands in current version, their 
uses and how to format them in the command line.


## Usage

### `Login System`- Create new user or login existing user

Login system is automatically launched at the start of the programme.

**Registering a user**

> Step 1 : Enter 1 to select option to register a user.
> 
> Step 2: Enter desired username and password.
> 
> Upon successful creation, registration success message is observed

Expected outcome:

```
Registration successful.
```

**Login an existing user**

> Step 1 : Enter 2 to select option to login a user.
>
> Step 2: Enter registered username and password.
>
> Upon successful creation, successful login message is observed.

Expected outcome:

```
Login Successful.
```

### `add` - Adds drug into inventory list

Adds a drug to be tracked by the system.

Format:

add /n DRUG_NAME /d EXPIRY_DATE /q QUANTITY


Example of usage:

`add /n Panadol /d 12 June 2026 /q 300
`

Expected outcome:

```
New drug added in the inventory: Panadol
```
### `delete` - Deletes a drug being tracked by the system

Deletes a drug being tracked by the system.

Format:

delete /n DRUG_NAM


Example of usage:

`delete /n Panadol
`

Expected outcome:

```
removed : Panadol
There are now 0 drugs in the system
```
### `list` - List all drug information that is being tracked by the system

List all drug information that is being tracked by the system.

Format:

list


Example of usage:

`list
`

Expected outcome:

```
1. Name: Panadol, Expiry Date: 12 June 2026/  Quantity: 300

Listed all drugs in the inventory.
```
### `find` - Finds drugs whose names contain any of the given keywords

Finds drugs whose names contain any of the given keywords.

Format:

list


Example of usage:

` find KEYWORD [MORE_KEYWORDS]
`
The search is case-insensitive, meaning that "aspirin" will match "Aspirin."

The order of the keywords does not matter. For example, "Painkiller Relief" will match "Relief Painkiller."

Only the drug name is searched.

Only full words will be matched. For example, "Pan" will not match "Panadol."

Drugs matching at least one keyword will be returned (i.e., OR search).


Expected outcome:

```
Here is a list of drugs matching your description:
1. Panadol / 26 June 2023/  [300]
```
### `help` - List currently available commands in current version, their uses and how to format them in the command line

List all currently available commands in current version, 
their uses and how to format them in the command line


Format:

help


Example of usage:

`help
`

Expected outcome:

```
|| 
|| add: Adds a new drug to the drug list. Parameters: NAME, EXPIRY DATE, QUANTITY,  
|| Example: add /n Doliprane /d 12/06/2035 /q 52
|| 
|| delete: Removes a drug from drug list. Parameters: Name  
|| Example: delete <Drug Name>
|| 
|| help: Shows program usage instructions. 
|| Example: help
|| 
|| list: List all drug information that is being tracked by the system. 
|| Example: list
|| 
|| find: Finds drug in inventory 
|| Example: find panadol
|| 
|| bye: Exits the program.
|| Example: bye
```

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* add :   `add /n DRUG_NAME /d EXPIRY_DATE /q QUANTITY`
* delete : `delete /n DRUG_NAME`
* list : `list`
* find : `find KEYWORD [MORE_KEYWORDS]`
* help : `help`
