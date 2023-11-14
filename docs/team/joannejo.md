# Joanne Ang - Project Portfolio Page

## Overview

SysLib is a command-line (CLI) library management software tailored for system librarians, especially those who are fast typists. 
It offers a wide range of features to manage library resources and events, from adding, editing, viewing, searching, deleting, to saving data file. 


### Summary of Contributions

#### Code Contributed

View the code I contributed [here](https://nus-cs2113-ay2324s1.github.io/tp-dashboard/?search=joannejo&breakdown=true).


#### Enhancements Implemented

**Enhancements**:
1. Resource classes
2. Add feature for resources
3. Parser for adding resources
4. Exception class
5. Output of messages

**Details**:

**1. Resource classes**

Implementation: `Book`, `EBook`, `CD`, `Magazine`, `EMagazine`, `Newspaper`, `ENewspaper` classes

Implemented the different resource classes for each resource has different attributes.

**2. Add feature for resources**

Implementation: `AddCommand`, `CreateResource` classes

Implemented the **add** feature to add resources into a list of all the resources in the library.

**3. Parser for adding resources**

Implementation: `ParseResource`, `ParseAttribute`, `ParseBook`, `ParseEBook`, `ParseCD`, `ParseMagazine`, `ParseEMagazine`, `ParseNewspaper`, `ParseENewspaper` classes &
`parseAddCommand()`, `checkFirstItem()` in `Parser` class

Implemented the validation and error handling of user input for the **add** feature.
Checks the user input for all attributes in all resources. Returns error messages when invalid.

**Validation checks for**:
- **Valid input**
- **Optional data**
  - Breaks down large data to smaller valid data  

Limitations of input were reduced.

**4. Exception class**

Implementation: `SysLibException` class

Catches **exceptions** related to SysLib and prevents program from exiting due to exceptions. 
Returns error messages to user so that the exception can be prevented with the right user input.

**5. Output of messages**

Implementation: `Messages` class & `formatSeparatorLineDivider()` in `MessageFormatter` class

Messages strings are stored in **Messages**. It consists of mainly error, warning and assertion messages.
**formatSeparatorLineDivider()** formats the messages neatly for output to user.


#### Contributions to the UG

Sections contributed:
- How to Use the User Guide
- Add a Listing
- FAQ
- Command Summary


#### Contributions to the DG

Sections contributed:
- Setting Up & Getting Started
- Add Resource Feature
- Add Feature Sequence Diagram
- Add Feature Activity Diagram
- Target User Profile
- Use Case: Add a book
- Instructions for Manual Testing
  - Launch & Shutdown
  - Adding a Book

#### Contributions to Team-Based Tasks

- Did general code enhancements
- Updated UG and DG not specific to feature
- Check consistency of documentations
- Managed issues

#### Reviewing / Mentoring Contributions

- Reviewed peer pull requests
- Bug tested during implementation

#### Contributions Beyond the Project Team

- Peer reviewed other team's project [here](https://github.com/nus-cs2113-AY2324S1/tp/pull/8)
- Tested other team's project during PE-D