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

### `add` - Adds drug into inventory list

Adds a drug to be tracked by the system.

Format:

add /n DRUG_NAME /d EXPIRY_DATE /q QUANTITY


Example of usage:

`add /n Panadol /d 12 June 2026 /q 300
`

Expected outcome:

```
added : Panadol
Expiry : 12 June 2026
Quantity : 300
There are now 1 type of drugs in the system
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
Here are a list of drugs in the system:
1. Panadol / 26 June 2023/  [300]
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
1. add
Format: add /n DRUG_NAME /d EXPIRY_DATE /q QUANTITY

2. delete
Format: delete /n DRUG_NAME

3. list
Format: list

4. find
Format: find KEYWORD [MORE_KEYWORDS]

5. help
Format : help
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
