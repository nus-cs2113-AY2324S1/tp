# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the
original source as well}

1. Reference to AB-3 Developer Guide

* [Source](https://se-education.org/addressbook-level3/DeveloperGuide.html#proposed-undoredo-feature)
* Used as template to structure this Developer Guide

## Design & implementation

<img src="UML Diagrams/Architecture_Diagram.png" width="280">

The architecture diagram given above explains the high level design of the application. The diagram depicts the key
component of the application that enables it to provide its functionalities.

Majority of the app's work is done by the following components

- Login System : Handles user authentication before enabling app usage
- Ui : Asks for user input by handling output messages
- Parser : Makes sense of user input
- Commands : List of various commands
- CommandResult : Execution of various commands

The section below will explain in more detail the design considerations, implementation and rationale of the various
components listed above.

---

### Login System Component

---
The login system component seeks to authenticate and login existing users or register a new user.

#### Design considerations

* There must be a way to check and verify users with a master list
* The search for existing users username and password must be fast
* Master list must be stored separately on hard drive of machine

#### Implementation and rationale

The login system class works in the following way. Upon booting up the application, a txt file
containing a current list of existing users will be loaded into a users attribute within the class in the form of a hash
table.When a user attempts to login to their account, the entered username and password is checked against
the current list of users in the hashtable. If the username and password matches, the user is logged in.

As for registering new users, newly inputted username and password will be saved to the users attribute and this
pair of username and password is then appended to the txt file containing current users. The updated user list will be
loaded into the users attribute when the application is booted up again.

The login system class uses the below methods to achieve its functionality

* `authenticateUserChoice()` - Decides whether the user chooses to register or login
* `newUserCreator()` - Creates a new user for future login
* `loginExistingUser()` - Login existing user
* `loadExistingUsers()` - Load existing users into hashtable for reference

Given below is an example of how the login system class works.

When the user first launches the programme, the Stocker object will be instantiated. The object will
invoke its own `run()` method which will call its own `start()` method. The Stocker object then instantiates a
new UI object which displays the login message by invoking `showLoginMessage()` method. At this point, Stocker object
will also instantiate a new login system object.

The login system object will invoke its own `run()` method to begin the login process. This method begins by loading
existing users into the users attribute of the login system class by `loadExistingusers()` method. it then invokes
`authenticateUserChoice()` to receive an input from the user to whether register or login a user. Based on the input of
the user, either `newUserCreator()` is launched or `loginExistingUser()` methods will be called to register or login a
user.

The following sequence diagram shows how the login system class works when the program is launched.

<img src="UML Diagrams/StockerToLoginSystem.png" width="280">

## Product scope

### Target user profile

- Works in the field of drug distribution, such as pharmacies and doctors' offices.
- Manages a large inventory of pharmaceuticals with varying details (expiration dates, manufacturers, storage
  conditions).
- Prefers desktop applications for their work.
- Proficient in fast typing.
- Favors typing over mouse interactions.
- Comfortable using Command Line Interface (CLI) applications for efficiency.
- Requires password-protected access to sensitive patient healthcare information.

### Value proposition

**Stocker** is designed to cater to the specific needs of drug distributors by offering the following benefits:

1. Quick access to real-time inventory information.
2. Efficient tracking of incoming stock.
3. Categorization of drugs based on various labels.
4. A prioritized list of urgently needed restocks for timely replenishment.
5. Assistance in generating comprehensive reports for stock turnover analysis.
6. Secure access through individual user accounts to safeguard patient healthcare data.
7. Enhanced user experience for experienced professionals who prefer keyboard commands and CLI interactions for seamless
   stock management.

## User Stories

Priorities: High (must have) - * * *, Medium (nice to have) - * *, Low (unlikely to have) - *

| Priority | Version | As a ...          | I want to ...                                        | So that I can ...                                           |
|----------|---------|-------------------|------------------------------------------------------|-------------------------------------------------------------|
| * * *    | v1.0    | Pharmacist        | Add drugs to track what drugs are available in stock | Reduce manual errors                                        |
| * * *    | v1.0    | Pharmacist        | Remove drugs to track what are no longer in used     | Ensure compliance                                           |
| * * *    | v1.0    | Receptionist      | View a list of products of that category             | Easily obtain an overview of the products                   |
| * * *    | v1.0    | First-time user   | See a list of all available actions                  | Better understand how to use the application                |
| * * *    | v1.0    | Inventory Manager | Find a specific drug currently in the system         | Check up its details and quantities specifically            |
| * * *    | v2.0    | user              | find a to-do item by name                            | locate a to-do without having to go through the entire list |

*(More to be added)*

## Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java 11 or above installed.

2. Should be able to hold up to 1000 drug entries without a noticeable sluggishness in performance for typical usage.

3. A user with above-average typing speed for regular English text (i.e. not code, not system admin commands) should be
   able to accomplish most of the tasks faster using commands than using the mouse.

*(More to be added)*

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
