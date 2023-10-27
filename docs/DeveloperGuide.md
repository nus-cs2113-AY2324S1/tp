# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

### Handling User Inputs & Displaying Output

A lot of the initial project setup that deals with things like parsing, displaying outputs back to the user, exceptions, etc. are strongly inspired by the address book example project demoed in class. Specifically, we have classes that are responsible for various aspects of our project in order to follow the single-responsibility principle and limit the amount of coupling. 

One specific design implementation we chose was having a Command superclass that all commands in our application will inherit from. This allows every command we write in the future to be guaranteed to possess all methods specified in the superclass. Therefore we can call these methods in our main function without needing to worry about the differences between various commands because they all inherit from the same parent. Another alternative we considered was simply making each separate command its own function and calling them from our parser class. However, we want to keep the commands as high level as possible and avoid calling more lower-level functions if possible so we can provide more encapsulation to each command. Therefore, we decided to go with this class-based command hierarchy that was also found in the example address book repo.

Below is a class diagram that describes how our various components interact with one another:

![image](https://github.com/AY2324S1-CS2113-F11-1/tp/assets/54857388/21bd2aa9-8e50-49d2-b888-50446115f922)

The parser class is responsible for reading the user input and creating specific command objects. All of these subcommands inherit from a general Command parent class which has an `execute` method that can be called regardless of child class and will return a CommandResult object. The CommandResult object dictates what info (string or list of items) needs to be returned to the user for viewing. Both CommandResult and TextUi (responsible for actually displaying data from CommandResult to user) will only print objects from classes that inherit from the Printable interface. This ensures that objects in the `relevantItems` list are all serializable as a human-readable string to be displayed to the end user.

### Update Exercises from Log feature

The proposed feature is to allow users to update exercises from the ExerciseLog class to allow for full CRUD functionality of the class. Currently, users are able to create exercises and log them as well as view all the exercises that they have created, but in the case of a typo, they are unable to update the log to accomodate for the user error. To improve the user experience, this update feature has been proposed to allow users to have a better experience using the app.

The update feature will be implemented by allowing users to select the task that they want to edit by specifying the month and day that they logged this exercise at, as well as the corresponding exercise name and the number of calories that the exercise has burned. This is because each exercise is stored within Day objects, and in order to check if two exercises are equivalent, we check both the exerciseName and caloriesBurned fields. Thus, these parameters will need to be specified by the user in order to update the exercise log.

Below is an example of how this feature will work:

Step 1: Upon starting the app, the ExerciseLog class is initialized and a log is created for the user to log their exercises.

<img width="661" alt="Screenshot 2023-10-22 at 22 21 04" src="https://github.com/Remy9926/tp/assets/95456114/933b3636-eba7-442b-bcf2-aec22ef49dba">

Step 2: The user calls the log command with the specified parameters to add a new exercise to the list. However, the user notices that they made a typo and want to change the details of the exercise that they just logged.

<img width="682" alt="Screenshot 2023-10-22 at 22 23 59" src="https://github.com/Remy9926/tp/assets/95456114/318cd321-6516-4163-a4ce-4a9e5d5edf7b">

Step 3: The user calls the update command with the information of the old exercise as well as the new information that the user wants to update with. With this, the update is done.

<img width="652" alt="Screenshot 2023-10-22 at 22 25 09" src="https://github.com/Remy9926/tp/assets/95456114/d07d5570-11fc-426c-877d-feb31a338f0a">

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
