# Developer Guide

## Acknowledgements

Referred to https://se-education.org/addressbook-level3/DeveloperGuide.html when drafting the DG

## Design & implementation

### Architecture

![](./images/architeture.png)

The Architecture Diagram given above explains the high-level design of the App.

The **User** sends command which is processed by Input

**Input**: Receives user input string, processes it and sends to CashLeh main app

**CashLeh**: main app

**UI**: Responsible for the printing onto CLI

**Parser**: Process the input string and calls respective methods

**FileStorage**: Reads data from, and writes data to file

### How the architecture components interact with each other

![](./images/main_example.png)

The Sequence Diagram above shows how the components interact with each other when the user enters a command.
A list of the accepted input commands is available in the [User Guide](./UserGuide.md).


### Setting, viewing and editing a budget feature

When CashLeh? is launched, after asking for the user's name, the app will ask whether the user would like to set a
budget to manage his/her transactions. CashLeh? will still work seamlessly, even if no budget is set, but some functions
(such as viewing your budget) will obviously not be available for use.

The option to set, view and edit a budget is managed by the `BudgetHandler`. An object of this class is constructed
with two attributes, of type `FinancialStatement` and `Budget` respectively. It features both a setter and a getter
which will be needed for the functioning of the overall budget feature. The BudgetHandler handles a budget which is
initially set to not active (activated only when the budget is actually set by the user through the command
`updateBudget`). On the other hand, the BudgetHandler is simultaneously responsible for measuring the Progress: It
sets the Progress' percentage and calls the method to display the progress bar chart through its own method
printBudget(). Below you can see a simple class diagram summarizing these relationships.

![](./images/budgetHandler_classDiagram.png)

When the user enters the command to view a budget `viewBudget`, the following happens:

![](./images/budgetHandler_sequenceDiagram.png)

**Step 1**\
The printBudget method of BudgetHandler is called.

**Step 2**\
The BudgetHandler self invokes its own method setBudgetPercentage() to retrieve the data from the
financial statement and create a new object of type Progress using the cash on hand and budget amount.

**Step 3**\
If the budget is not active (has not been initialised or deleted by the user), a CashLehBudgetException is thrown.
If the budget is active (has been initialised or updated by the user), two further conditions need to be checked before 
being able to continue: 
* in case of a budget deficit (net cash on hand <= 0) a serious warning is printed
* in case of a small surplus (less than 25% of the budget remaining) a basic warning is printed

**Step 4**\
Finally, a string containing information about the budget, the net cash from the financial statement and the progress bar
is built and passed to the method printMultipleText() by the Ui. A potential output example is given below.

```console
	You have set a budget of: 60.0
	Here's a quick view of how you're doing so far:
	You have a net cash on hand of: 10.0
	You still have the following percent of your budget left:

	[*****-------------------------] 16.67%
```

### String Tokenizer

The String Tokenizer assists in the parsing of user input. It is used to split the user input into a hashmap according to the delimiter/prefix of each command. This allows the developer to more easily customize the commands and add new commands without having to worry about the parsing of the user input.

> **Example**:\
> `addIncome money /amt 1000 /date 2021-10-10` with prefixes array `{"addIncome", "/amt", "/date"}` will be tokenized into a hash map with the following key-value pairs:\
> `addIncome: money`\
> `amt: 1000`\
> `date: 2021-10-10`

Delimiters can also be specified as optional by adding ":optional" to the end. This allows the user to omit the prefix when entering the command.

#### Implementation

The tokenize function is implemented by the StringTokenizer class. It takes in the user input and the prefixes array and returns a hashmap of the user input.

![](./images/stringTokenizer.png)
> Note: The lifeline for PrefixWithPosition should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

**Step 1**\
Check if all necessary prefixes are present in the user input. If not, throw an exception.

**Step 2**\
Generate list of PrefixWithPosition (containing the prefix/delimiter and the position in the string) for the delimiters in the user input

**Step 3**\
Sort the list of PrefixWithPosition by position in order to get the prefixes in the correct order

**Step 4**\
Create a hashmap of the user input by splitting the string using the prefixes' positions and adding the key-value pairs to the hashmap

### Transaction Filter
The filter function assists in filtering both expenses and incomes according to the specified criterion from user's input.

#### Implementation
![](./images/FilterSequenceDiagram.png)

**Step 1**\
The filterBy() method is called, returning a FindParser object

**Step 2**\
A FilterTransaction object is constructed, with the FindParser object as one of its parameters

**Step 3**\
From the expenseStatement and incomeStatement, the financialStatement is constructed. 

**Step 4**\
Execution of the FilterTransaction class calls method findTransaction(), which prints expenses and incomes that match the provided criterion

### File Storage

The FileStorage class in the CashLeh? application facilitates the reading and writing of financial transaction data to and from a file. It simplifies the process of managing user data, making it easily accessible and modifiable through the following methods:

#### Reading Data
![](./images/readFromFile.png)

**Step 1**\
The `readFromFile` method checks if the specified file exists. If not, it creates an empty file with the user's name as the file path

**Step 2**\
After ensuring the file exists, the method parses the data by reading each line from the file. It distinguishes between income and expense transactions based on a type identifier

**Step 3**\
For each transaction line, the method validates the format. It checks if essential details like transaction type, description, amount, and date are present. If any information is missing or the format is corrupted, a `CashLehFileCorruptedException` is thrown

**Step 4**\
Valid transactions are used to create `Income` or `Expense` objects, depending on their type. These objects are added to the provided `IncomeStatement` and `ExpenseStatement` to populate the financial data.

#### Writing Data
![](./images/writeToFile.png)

**Step 1**\
The `writeToFile` method opens the file corresponding to the user's name  and prepares to write the financial data into the file

**Step 2**\
The method iterates over the transactions stored in the `IncomeStatement` and `ExpenseStatement` objects to collect the data

**Step 3**\
For each transaction, the method writes data in a specific format. This includes the type identifier ('I' for income and 'E' for expenses) and transaction details, ensuring structured and organized data for future retrieval

**Step 4**\
If errors occur during the write process, such as file writing issues or issues with regards to obtaining the transaction information, the method throws a `CashLehWriteToFileException`

## Product scope
### Target user profile

* CashLeh? is a CLI program 
* It is designed to work for a single user
* CashLeh?'s target audience mainly comprises younger working adults and students who have limited incomes and need to 
carefully plan out their expenses and account for their earnings

### Value proposition

CashLeh? is a financial tracker made specifically for working adults who have just began to earn their first salaries
and wages and might need some support finding the correct spending balance. College students and young workers often
struggle with their countless expenses ranging from rent, utilities, groceries and food, tax etc. CashLeh?, though
very simple and intuitive, is supposed to make the process of financial management seamless for them and help them avoid
unnecessary expenses.

## User Stories

|Version| As a ...          | I want to ...                                      | So that I can ...                                            |
|--------|-------------------|----------------------------------------------------|--------------------------------------------------------------|
|v1.0| new user          | see usage instructions                             | refer to them when I forget how to use the application                         |
|v1.0| user              | be able to add my incomes and earnings             | easily manage them and obtain access to an overview                            |
|v1.0| user              | be able to delete my incomes and earnings          | correct any mistakes and errors                              |
|v1.0| user              | be able to view the sum of my incomes              | be fully aware of my income situation and manage my money                               |
|v1.0| user              | be able to view the list  of all incomes           | be able to view the entire history of incomes                             |
|v1.0| user              | be able to add my expenses and spendings             | easily manage them and obtain access to an overview                            |
|v1.0| user              | be able to delete my expenses and spendings          | correct any mistakes and errors                              |
|v1.0| user              | be able to view the sum of my expenses              | be fully aware of my expense situation and manage my money                               |
|v1.0| user              | be able to view the list  of all expenses           | be able to view the entire history of expenses         |
|v2.0| user              | be able to view both incomes and expenses together | have an overview of my net financial situation                           |
|v2.0| parsimonious user | be able to set an overall budget                   | have an upper limit for my relative expenses                            |
|v2.0| user              | be able to view my budget                          | view my financial situation and organise myself                              |
|v2.0| user              | be able to delete my previously set budget         | not care about an upper limit or eventually set a new budget                          |
|v2.0| user              | be able to label categories of each income         | can know which category the income belongs to                                  |
|v2.0| user              | be able to label categories of each expense        | can know which category the expense belongs to                                  |
|v2.0| user              | be able to filter my transactions                  | better understand my financial history and easily access information |

## Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java 11 or above installed.
2. The application should be easy to use for first-time users

## Glossary

* *Mainstream OS*: Windows, Linux, Unix, OS-X

## Instructions for manual testing

### Launch and shutdown

#### 1. Initial launch

1. Download the jar file and copy it into an empty folder
2. Open the terminal and run the command `java javac- Cashleh.jar`
3. CashLeh? should launch and you can start interacting with the application

### Refer to user guide for commands

## Proposed implementations of future features

* Create periodic income and expenses (subscription fees, salary etc.)
* View sum of income and expenses base on specified time range
* Password protection when starting app
* Able to reset password
* More personal information in addition to name (location, age, etc.)
* Tutorial/guide to use the app
* Able to set preferred currency, all transaction to be converted accordingly
* Able to change description, amount, date, and categories of transactions
* Able to view the transaction information in graphs and charts for visualisation