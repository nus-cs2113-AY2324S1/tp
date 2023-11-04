# User Guide

## Introduction

Welcome to the FITNUS UserGuide! We're glad you're here and if you have any questions about how to operate the application, all the information will be here for you.

## Quick Start

In the terminal, make sure you are in the same directory (location) as the jar file is, and run `java -jar JAR_FILE_NAME` where `JAR_FILE_NAME` is the name of the jar file as stored on your local computer.

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `Duke` from [here](https://github.com/AY2324S1-CS2113-F11-1/tp/releases).

## Features 

{Give detailed description of each feature}

### Viewing help: `help`
Shows a message explaining how to access the help page.

Format: `help`

### Adding a meal: `meal_add`
Adds a new item to the list of todo items.

Format: `meal_add MEAL_NAME CALORIES`

* The `CALORIES` should be a standard number.
* The `MEAL_NAME` cannot contain space, use "_" as replacement.

Example of usage: 

`meal_add potatoes 100`

`meal_add baked_bread 66`

### Adding an exercise: `log`
Adds a new exercise to the Exercise Log.

Format: `log MONTH DAY EXERCISE_NAME CALORIES_BURNED`

* The `MONTH` is an integer specifying the month in which the exercise was performed (1-12 inclusive)
* The `DAY` is an integer specifying the day of the month in which the exercise was performed
* The `EXERCISE_NAME` is a string specifying the type of exercise performed
* The `CALORIES_BURNED` is an integer specifying the number of calories burned by the exercise

Example of usage:

`log 1 26 Basketball 179`

`log 12 24 Volleyball 5`

### Updating an exercise: `update`
Updates the specified exercise within the Exercise Log if the old exercise can be found. If the old exercise is not
found, then the user will not be prompted to provide new details.

Format: `update MONTH DAY OLD_EXERCISE_NAME OLD_CALORIES_BURNED` `NEW_EXERCISE_NAME NEW_CALORIES_BURNED`

* The `MONTH` is an integer specifying the month in which the exercise was performed (1-12 inclusive)
* The `DAY` is an integer specifying the day of the month in which the exercise was performed
* The `OLD_EXERCISE_NAME` is a string specifying the name of the exercise to be updated
* The `OLD_CALORIES_BURNED` is an integer specifying the number of calories burned by the exercise to be updated
* The `NEW_EXERCISE_NAME` is a string specifying the new exercise name
* The `NEW_CALORIES_BURNED` is an integer specifying the new number of calories burned

Example of usage:

`update 1 26 Basketball 179`
`Rugby 55`

`update 12 24 Volleyball 5`
`Hockey 98`

### Viewing Exercises: `view`
View the number of, or each exercise in a day, month, or the entire log.

Format `view VIEW_TYPE VIEW_SCOPE`

* The `VIEW_TYPE` is either `total` or `exercises` depending on whether you want to view the total number of exercises
or each exercise and its details
* The `VIEW_SCOPE` can take on either `all` to see all exercises in the log, `month MONTH` where `MONTH` is the month
whose exercises you want to view, or `month MONTH day DAY` where MONTH is the same as above, but `DAY` is the specific
day of the month whose exercises you want to view.

### Setting up a calorie goal: `set on`
Set up a calories goal to achieve.

Format: `set AMOUNT on Date`

The AMOUNT is in terms of kcal. Please use DD/MM/YYYY format for the date.

Note that this function is still under development. The future features includes: delete a goal, view your goal, save your achievement, etc.

### Exiting the program: `exit`
Exits the program.

Format: `exit`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: It is not suggested to transfer data between machines. The purpose of the app is developed solely for personal use. Nonetheless, you can copy the folder named `data` that is created in the same directory (location) as where you are running the application into your new computer to restore your data.

## Command Summary

* Log an exercise `log MONTH DAY EXERCISE_NAME CALORIES_BURNED`
* Update an existing exercise `update MONTH DAY OLD_EXERCISE_NAME OLD_CALORIES_BURNED`
`NEW_EXERCISE_NAME NEW_CALORIES_BURNED`
* View existing exercises `view VIEW_TYPE VIEW_SCOPE`
* Exit program `exit`
