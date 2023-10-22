# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

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
