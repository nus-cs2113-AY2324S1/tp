# WildWatch User Guide 🐘

## Table of Contents 
- [Introduction](#introduction-)
    - [How to use the User Guide](#how-to-use-the-user-guide)
    - [Legend](#legend)
- [Quick Start](#quick-start-)
- [Features](#features-)
    - [Adding Entries](#1-adding-entries-add): `add`
        -  [Standard Mode](#11-standard-mode)
        -  [Interactive Mode](#12-interactive-mode-add-i)
    - [Deleting Entries](#2-deleting-entries-delete): `delete`
    - [Listing Entries](#3-listing-entries-list): `list`
    - [Editing Entries](#4-editing-entries-edit): `edit`
    - [Finding Entries](#5-finding-entries-find): `find`
    - [Summarizing Entries](#6-summarizing-entries-summary): `summary`
    - [Exporting Entries](#7-exporting-entries-export): `export`
    - [Getting Help](#8-getting-help--help): `help`
    - [Exiting](#9-exiting--bye): `bye`
    - [Saving Entries](#10-saving-entries)
- [FAQ](#faq-)
- [Bug Reporting](#bug-reporting-)
- [Command Summary](#command-summary-)
- [Glossary](#glossary-)

    
--------------------------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>
  
## Introduction 🐻
Welcome aboard fellow animal lover! We are really excited to have you here! 😉 Wildwatch is a program for a clerk managing wildlife data in a wildlife reserve via the [Command Line Interface (CLI)](#glossary-). The purpose of this user guide is to familiarize you with the program and help you when you face a problem using it. Its main job is to store and present animal data, this would make your job so much more convenient!

### How to use the User Guide  
- Are you new here?  
No worries, head to the [Quick Start](#quick-start-) page, and you will be up and running in no time!  
- Lost among the pages?  
Head to the [Table of Contents](#table-of-contents) to look for the right pages.  
- Need help with the functionalities?  
Head to the [Features](#features-) page for detailed guidance.  
- Do you have a question for us?  
Head to the [FAQ](#faq-) page.  
- Do you want a concise summary of all functionalities?  
Head to the [Command Summary](#command-summary-) page for a summary of all commands.
- Not sure what that word meant?  
Head to the [Glossary](#glossary-) page for its meaning.  

### Legend

| Symbol | Meaning |
| ------------ | ------------ |
|❗ IMPORTANT | These are important instructions that you should follow, failure to do so may be fatal. |
| ✏ Note | These are important details that you should take note of, failure to do so may not be fatal. |
| ⬆ Back to top | Click to scroll back up to the `Table of Contents`. |
| 🐵 🦊 🦁 | Your cute animal friends pop up to let you know that you have reached a new section. |

[⬆ Back to top](#table-of-contents)  
  
    
--------------------------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Quick Start 🐵

1. Ensure you have Java 11 or above installed in your Computer. (How do I install [Java 11](https://www.oracle.com/java/technologies/downloads/#java11)? What is my [Java version](https://www.java.com/en/download/help/version_manual.html)?)
   
2. Download the latest `WildWatch.jar` from [here](https://github.com/AY2324S1-CS2113T-W11-2/tp/releases).

3. Copy the file into the folder you want to use as the [home folder](#glossary-) for your WildWatch.

4. Open a [command terminal](https://www.freecodecamp.org/news/command-line-for-beginners/), `cd` into the home folder you put the jar file in, and run the following command: `java -jar WildWatch.jar`.
You should see the welcome screen as the diagram below.

5. Type commands  beside `>>>` and press `Enter` to execute it.   
(e.g. typing `help` and pressing `Enter` will show the help page).      

![image](https://github.com/AY2324S1-CS2113T-W11-2/tp/assets/69474977/c110856f-dd16-4c5c-8205-446d86425ddb)   

[⬆ Back to top](#table-of-contents)  
  
  
--------------------------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Features 🦊
> ❗ IMPORTANT    
> * Capitalized words between angle brackets `< >` is a field to be filled up appropriately by the user.  
>     * E.g., `delete <INDEX>` should be `delete 2`  
> * Anything between curly brackets `{ }` is an optional field that may or may not be filled up by the user.  
>     * E.g., `summary {<SPECIES>}` could be `summary lion` or `summary`.  

  
### 1. Adding Entries: `add`
Adds a new wildlife entry to the WildWatch program.
  
> ❗ IMPORTANT  
> * `<DATE>` has to be in the format **DD-MM-YYYY**
> * `<DATE>`, `<SPECIES>`, `<NAME>` cannot be the same for 2 separate entries.  
  
> ✏ Note    
> * `R/<REMARKS>` here is optional, meaning it may be left as blank.  
    
#### 1.1 Standard Mode 
Format: `add D/<DATE> S/<SPECIES> N/<NAME> {R/<REMARKS>}`

> ❗ IMPORTANT  
> * The fields must be filled up in the following order: `<DATE>`, `<SPECIES>`, `<NAME>`, `<REMARKS>`.  
  
Example:
* `add D/02-03-2023 S/Annam Leaf Turtle N/Ariel`
* `add D/02-03-2023 S/Low Land Gorilla N/Strong One R/Aggressive`
  
![image](https://github.com/AY2324S1-CS2113T-W11-2/tp/assets/69474977/d548f580-3098-4d9d-8635-07e0a989e47c)  

<div style="page-break-after: always;"></div>  
   
#### 1.2 Interactive Mode: `add i/`
Did you forget the fields to in the `add` command? Fear not, we've got your back!   
Simply type `add i/`, which triggers the interactive mode for the `add` command.   
It will prompt you each step of the way, notifying you if a mandatory field is left blank, so you don't have to worry about getting it wrong!   

Format: `add i/`
  
Example:  
![image](https://github.com/AY2324S1-CS2113T-W11-2/tp/assets/69474977/b255477f-fc2c-437c-a954-5b4c71945c26)  

[⬆ Back to top](#table-of-contents)

  

<div style="page-break-after: always;"></div>  
### 2. Deleting Entries: `delete`
Deletes an entry of the specified INDEX. 
The index refers to the index number shown in the displayed [entry list](#3-listing-entries-list).  
  
Format: `delete <INDEX>`  
   
>❗ IMPORTANT    
> * Index must be a positive integer 1, 2, 3, ...  
> * Deleted items are not recoverable  

Example:  
![image](https://github.com/AY2324S1-CS2113T-W11-2/tp/assets/69474977/587e450e-9219-4a42-b005-2b52e8c93de2)    
  
  
  
### 3. Listing Entries: `list`
Shows a list of all entries of the wildlife in WildWatch so far.  
Shows useful information about each entry.  
These include information such as the date, species, name, and remarks.  

Format: `list` 

Example:  
![image](https://github.com/AY2324S1-CS2113T-W11-2/tp/assets/69474977/ef21314f-fb23-4bd6-bcfd-fddfa8fb0330)

[⬆ Back to top](#table-of-contents)

  

<div style="page-break-after: always;"></div>  
### 4. Editing Entries: `edit`
Edits an entry in the program.  
  
Format: `edit I/<INDEX> {D/<DATE>} {S/<SPECIES>} {N/<NAME>} {R/<REMARKS>}`  
   
>❗ IMPORTANT  
> * `<DATE>` has to be in the format **DD-MM-YYYY**  
> * `I/<INDEX>` argument is required.  
> * The fields must be filled up in the following order: `<DATE>`, `<SPECIES>`, `<NAME>`, `<REMARKS>`.  
  
> ✏ Note  
> * `D/<DATE>` `S/<SPECIES>` `N/<NAME>` `R/<REMARKS>` fields are optional, but at least 1 needs to be filled up.  
  
Example:  
![image](https://github.com/AY2324S1-CS2113T-W11-2/tp/assets/69474977/93d3f7ce-f714-4149-953d-703d6d58c671)  

[⬆ Back to top](#table-of-contents)

  

<div style="page-break-after: always;"></div>  
### 5. Finding Entries: `find`  
Finds all the entries that match the search word.  

Format: `find <SEARCH>`

> ✏ Note  
> * `<SEARCH>` here could be the full-date (**DD-MM-YYYY**), or part of the name, the species, or the remark.

Example:  
![image](https://github.com/AY2324S1-CS2113T-W11-2/tp/assets/69474977/b77bd98a-6ea3-4378-aef2-bd436794b246)    
    
    
   
### 6. Summarizing Entries: `summary`
Shows a summary of all wildlife in the WildWatch system by the species type.

Format: `summary {<SPECIES>}`
  
> ✏ Note  
> * `<SPECIES>` here is optional.  
> * When specified, it will show a summary of the wildlife recorded for the specified species.  
  
Example:  
![image](https://github.com/AY2324S1-CS2113T-W11-2/tp/assets/69474977/4a089c9f-ef0a-4c57-96a1-48479614ac9c)

[⬆ Back to top](#table-of-contents)  

  

<div style="page-break-after: always;"></div>  
### 7. Exporting Entries: `export` 
Do you need to share the entries you've collected with someone else? No worries, we have your back! We provide a convenient way for you to export your data as a [Comma Separated Values (CSV)](#glossary-) file. CSV files can be imported into universally-used tools like Microsoft Excel, allowing you to share your data and insights effortlessly. We also guide you through the process of selecting the columns that you want to include in your CSV, if you only want to share some parts of the data. This would come in really handy!👍    

Format: `export {<FILENAME>}`

> ✏ Note  
> * `<FILENAME>` here is optional.   
> * However, if you choose to include it, note that it should end with the file extension `.csv` so that it can be recognized as a CSV file.  
> * If unspecified, your data will be written to the default file `WildWatch.csv`.   

Example:  
![image](https://github.com/AY2324S1-CS2113T-W11-2/tp/assets/69474977/e423b685-2b61-4e90-aee0-821fc4c44659)  

[⬆ Back to top](#table-of-contents)

   

<div style="page-break-after: always;"></div>    
### 8. Getting Help : `help`
The help page shows a brief overview on how to use all the commands.    
Additionally, you can also specify a command to view the datailed help page.    

Format: `help {<COMMAND>}`

> ✏ Note  
> * `<COMMAND>` here is optional.
> * It may be one of the following: `add`, `delete`, `list`, `edit`, `find`, `summary`, `export`, `bye`, `full`.

Example:  
![image](https://github.com/AY2324S1-CS2113T-W11-2/tp/assets/69474977/86dff05d-057a-4989-bcc0-d234b9b1b3ce)  
  
  
  
### 9. Exiting : `bye`
To close and save the program.

Format: `bye`
     
    
    
### 10. Saving Entries

Data of the entries are saved in the local storage automatically.   
Now, you can use the WildWatch with a peace of mind!  

[⬆ Back to top](#table-of-contents)
  
  
--------------------------------------------------------------------------------------------------------------------------------------
  
## FAQ 🦁
   
> **Q**: Why do I get the `OOPS!!! Invalid Date input :-(` error?  
> **A**: Ensure the day, month, year is separated by `-` e.g., `23-05-2023` in **DD-MM-YYYY** format.  
  
  
> **Q**: Can I add photos or other media to the entries?  
> **A**: Currently, WildWatch only supports text-based entries. You can add descriptions, but it does not support adding photos or media directly.
  
  
> **Q**: I accidentally deleted an entry. Is there a way to recover it?  
> **A**: Unfortunately, deleted entries are not recoverable, so it's important to be careful when using the delete command.
  
  
> **Q**: I made a mistake while adding an entry, can I edit or correct it?  
> **A**: Sure thing! You can edit an entry using the edit command. Simply specify the index of the entry you want to edit and provide the updated information. For example, you can use `edit I/1 D/02-03-2024` to edit the date of the first entry.  
  
  
> **Q**: I found a bug! What do I do?     
> **A**: Great! Please contact [Min](https://github.com/woodenclock), your help is greatly appreciated! Alternatively, you may refer to our [Bug Reporting](#bug-reporting-) section to report the bug yourself!   
    
[⬆ Back to top](#table-of-contents)  
    
   
--------------------------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Bug Reporting 🐝

You can do the following steps below to report an issue with our program.   
We value your feedback, and opening an issue on our GitHub repository is a great way to help us identify and address problems.
  
### Step-by-Step Instructions:

1. **Visit the GitHub Repository**
  - In your web browser, navigate to our [GitHub repository](https://github.com/AY2324S1-CS2113T-W11-2/tp).  

2. **Sign in to GitHub**
  - If you don't have a GitHub account, you'll need to create one. If you already have an account, sign in to GitHub.

3. **Go to the `Issues` Tab**
  - Once you're signed in, go to the "Issues" tab of our GitHub repository. It's usually located in the menu bar near the top of the page.

4. **Open a `New Issue`**
  - Click on the "New Issue" button to create a new issue.

5. **Describe the Issue**
  - In the issue creation page, you'll find a text box for the issue title and a larger text box for the issue description. Fill in the following details:
      - Title: A concise and descriptive title for the issue.
      - Description: Provide a detailed description of the issue. Include steps to reproduce the problem if applicable.

6. **Add Labels and Milestones (if needed)**
  - You can optionally add labels and milestones to categorize the issue. This can help us prioritize and address the issue more effectively.

7. **Submit the Issue**
  - Once you've filled in the issue title and description, click the "Submit new issue" button. Your issue will be created and submitted to the repository.

### Additional Tips:
- Before creating a new issue, please check if the issue you want to report already exists in the repository's issue list. This can help avoid duplicate reports.

Thank you for contributing to the improvement of our program. Your feedback is highly appreciated! 🙏

[⬆ Back to top](#table-of-contents)
  
  
--------------------------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Command Summary 🐯

| Action                    | Format                                                             |
|---------------------------|--------------------------------------------------------------------|
| Adding Entries               | `add D/<DATE> S/<SPECIES> N/<NAME> {R/<REMARKS>}`                  |
| Adding Entries (interactive) | `add i/`                                                           |
| Deleting Entries            | `delete <INDEX>`                                                   |
| Listing Entries              | `list`                                                             |
| Finding Entries            | `find <SEARCH>`
| Editing Entries              | `edit I/<INDEX> {D/<DATE>} {S/<SPECIES>} {N/<NAME>} {R/<REMARKS>}` |
| Summarizing Entries       | `summary {<SPECIES>}`                                              |
| Exporting Entries            | `export {<FILENAME>}`                                              |
| Getting Help              | `help {<COMMAND>}`                                                  |
| Exit                      | `bye`                                                              |
  
  
--------------------------------------------------------------------------------------------------------------------------------------

## Glossary 🐨
We are here to help you with terminologies used in the user guide, that may not be familiar to you.

| Terminology    | Meaning                                                     |
|---------------|------------------------------------------------------------|
| Command Line Interface   | A way to communicate with your computer using texts. |
| Home Folder | Folder in which your program resides |
| Comma-Separated Value | A file format that allows data to be saved in tabular format |
   
[⬆ Back to top](#table-of-contents)
  
--------------------------------------------------------------------------------------------------------------------------------------
