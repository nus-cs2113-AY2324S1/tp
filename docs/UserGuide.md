# User Guide

## Introduction

Welcome to the FITNUS UserGuide! We're glad you're here and if you have any questions about how to operate the application, all the information will be here for you.

## Quick Start

In the terminal, make sure you are in the same directory (location) as the jar file is, and run `java -jar JAR_FILE_NAME` where `JAR_FILE_NAME` is the name of the jar file as stored on your local computer.

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `Duke` from [here](https://github.com/AY2324S1-CS2113-F11-1/tp/releases).

## Features 

### Notes about the input command format

- For each type command, a specific `COMMAND FORMAT` is required. Please go through the following guidelines to look for the details of each command.

- Both `CAPITAL LETTER` and `small letter` are acceptable. But consecutive white space between words will be eliminated automatically.
  
  e.g. `VIEWG`, `viewG`, `viewg` will be consider the same. This is to favour the typing habits of different users.

- In some cases, you may need to input a date information. FitNus app provides the following supported formats.
  
  1. Format: "yyyy/MM/dd" (e.g., "2023/08/22")
  2. Format: "dd/MM/yyyy" (e.g., "22/08/2023")
 
  Command example: `set 1000 on 22/08/2023` (For setting a new goal command)

  Please note that some **unexisting date with correct format** will be treated as last day of the month. If you find the input date is wrong, please use a delete command to help you. (e.g. 31/02/2021 will be treated as 28/02/2021, depends on the year.)
  
## Content of Command

1. Quick link to particular command.

|    Exercise | Goal  | Meal |  Others | 
| :------------ |:---------------:| -------------:| --------------:|
| [Add an Exercise](#log---adding-an-exercise)      | [Add a Goal](#set-on---setting-up-a-calorie-goal) | [Add a Meal](#meal_add---adding-a-meal) | [Help](#help---viewing-help) |
| [Update an Exercise](#update---updating-an-exercise)      | [Delete a Goal](#deleteg---delete-a-goal-from-current-goal-list)        |   [Delete a Meal](#meal_delete---deleting-a-meal) |  [Exit](#exiting-the-program-exit)     |
| [View Exercise](#view---viewing-exercises) | [Make Achievement](#achieve---turn-one-goal-as-achieved)        |    [List a Meal](#meal_list---listing-meals) |[View Achievement](#achievement---view-your-achieved-goals)       |
|  | [View Goals](#viewg---view-your-current-goal-list)        |     |       |

2. Frequently-asked-quesions([**FAQs**](#faqs))

## Command Type

### `help` - Viewing help
Shows a message explaining how to access the help page.

Format: `help`

### `meal_add` - Adding a meal
Adds a new item to the meal list.

Format: `meal_add MEAL_NAME CALORIES CATEGORY [DATE]`

* The `CALORIES` should be a standard number.
* The `MEAL_NAME` cannot contain space, use "_" as replacement.
* The `CATEGORY` should be a number of {0, 1, 2} or a string of {"staple_food", "snack", "beverage"}.
* The `DATE` should be a standard string that indicates a valid date, conforming with any format of {"yyyy/M/d", "d/M/yyyy", "yyyy-M-d", "d-M-yyyy"}.

Example of usage: 

`meal_add potatoes 100`

`meal_add baked_bread 66`

### `meal_delete` - Deleting a meal
Delete a meal from the existing list.

Format: `meal_delete INDEX`

* The `INDEX` should be a standard and positive number.

Example of usage: 

`meal_delete 1`

`meal_delete 100`

### `meal_list` - Listing meals
List all the meals that have been recorded.

Format: `meal_list [CATEGORY]`

* The `CATEGORY` should be a number of {0, 1, 2} or a string of {"staple_food", "snack", "beverage"} and can be omitted.

Example of usage: 

`meal_list`

`meal_list 1`

`meal_list snack`

### `log` - Adding an exercise
Adds a new exercise to the Exercise Log.

Format: `log MONTH DAY EXERCISE_NAME CALORIES_BURNED`

* The `MONTH` is an integer specifying the month in which the exercise was performed (1-12 inclusive)
* The `DAY` is an integer specifying the day of the month in which the exercise was performed
* The `EXERCISE_NAME` is a string specifying the type of exercise performed
* The `CALORIES_BURNED` is an integer specifying the number of calories burned by the exercise

Example of usage:

`log 1 26 Basketball 179`

`log 12 24 Volleyball 5`

### `updatelog` - Updating an exercise
Updates the specified exercise within the Exercise Log if the old exercise can be found. If the old exercise is not
found, then the user will not be prompted to provide new details.

Format: `updatelog MONTH DAY OLD_EXERCISE_NAME OLD_CALORIES_BURNED` `NEW_EXERCISE_NAME NEW_CALORIES_BURNED`

* The `MONTH` is an integer specifying the month in which the exercise was performed (1-12 inclusive)
* The `DAY` is an integer specifying the day of the month in which the exercise was performed
* The `OLD_EXERCISE_NAME` is a string specifying the name of the exercise to be updated
* The `OLD_CALORIES_BURNED` is an integer specifying the number of calories burned by the exercise to be updated
* The `NEW_EXERCISE_NAME` is a string specifying the new exercise name
* The `NEW_CALORIES_BURNED` is an integer specifying the new number of calories burned

Example of usage:

`updatelog 1 26 Basketball 179`
`Rugby 55`

`updatelog 12 24 Volleyball 5`
`Hockey 98`

### `viewlog` - Viewing Exercises
View the number of, or each exercise in a day, month, or the entire log.

Format `viewlog VIEW_TYPE VIEW_SCOPE`

* The `VIEW_TYPE` is either `total` or `exercises` depending on whether you want to view the total number of exercises
or each exercise and its details
* The `VIEW_SCOPE` can take on either `all` to see all exercises in the log, `month MONTH` where `MONTH` is the month
whose exercises you want to view, or `month MONTH day DAY` where MONTH is the same as above, but `DAY` is the specific
day of the month whose exercises you want to view.

Example of usage:

`viewlog exercises all`

`viewlog total month 1 day 24`

### `deletelog` - Deleting Exercises
Delete the exericse on the specified month and day

Format `deletelog MONTH DAY EXERCISE_NAME CALORIES_BURNED`

* The `MONTH` is an integer specifying the month in which the exercise was performed (1-12 inclusive)
* The `DAY` is an integer specifying the day of the month in which the exercise was performed
* The `OLD_EXERCISE_NAME` is a string specifying the name of the exercise to be deleted
* The `OLD_CALORIES_BURNED` is an integer specifying the number of calories burned by the exercise to be deleted

Example of usage:

`deletelog 6 16 Running 179`

`deletelog 2 27 Hockey 5`

### `set on` - Setting up a calorie goal
Set up a calorie goal to achieve. The goal are expected to help user accomplish a particular amount of calorie consumption before a **deadline**.

Format: `set AMOUNT on Date`

The AMOUNT is in terms of **kcal**. Please follows the provided formats at the beginning of this guide.

Example of outcome:
```
[Command entered:set 1000 on 11/11/2023]
Nice! I have added the following goal to your goals list: 
Consume 1000 kcal on Nov 11, 2023
```


### `viewG` - View your current goal list
Look for the content of current goal list.

Format: `viewG`

Example of outcome:
```
[Command entered:viewg]
Here you go! Remember to stick to your exercise and meal plans.
1. Consume 1000 kcal on Nov 11, 2023
2. Consume 1500 kcal on Nov 25, 2023
```

### `deleteG` - Delete a goal from current goal list
Remove a undersired goal from the goal list. Note that it is not encouraged to remove a goal from the goal list, unless the inserted goal contains errors or is near impossible to be accomplished.

Format: `deleteG Index`

You can retrieve the index by showing the goal list

Example of outcome:

```
[Command entered:deleteg 2]
Good. I have removed this goal: Consume 1500 kcal on Nov 25, 2023
Remember not to give up unaccomplished target!
You now have 1 goals to accomplish.
```

### `achieve` - Turn one goal as achieved
Be careful that this funnction will mark a goal as achieved by **removing** a goal from the current goal list and **permanantly** add a new achieved goal into your achievement list. (You can only destroy the whole list if you made any errors or want to give up an achievement.)

Format: `achieve Index`

Example of outcome:

```
[Command entered:achieve 1]
Congratulation! You have achieved one goal!
[Finished]Consume 1000 kcal on Nov 11, 2023 (:
```

### `achievement` - View your achieved goals
The achievement can only be shown. Any modification except adding a new record is not allowed.

Format: `achievement`

Expected outcome:

```
[Command entered:achievement]
Congratulation! See your achievements below: 
1. [A]Consume 1000 kcal on Nov 11, 2023
```

### Exiting the program: `exit`
Exits the program.

Format: `exit`

## FAQs

**Q1**: How do I transfer my data to another computer? 

**A**: It is not suggested to transfer data between machines. The purpose of the app is developed solely for personal use. Nonetheless, you can copy the folder named `data` that is created in the same directory (location) as where you are running the application into your new computer to restore your data.

**Q2**: Can I edit the data inside my list? e.g. change the calories consumption targets in the goal list

**A**: Sorry but we do not support the function to edit a list directly. But you can remove a unwanted data from a list and insert a new data again.

**Q3**: I have accidentally mark an unaccomplished goal as achieved. Could I change the achievement?

**A**: Sorry but we do not allow user to modify achievement list. If you really want to destory a false record, you must remove all the achievements and start a new save file. One way of doing this is to delete the file "achievement.txt" under the folder "data" inside your jar file repository.


## Command Summary

* Log an exercise `log MONTH DAY EXERCISE_NAME CALORIES_BURNED`
* Update an existing exercise `update MONTH DAY OLD_EXERCISE_NAME OLD_CALORIES_BURNED`
`NEW_EXERCISE_NAME NEW_CALORIES_BURNED`
* View existing exercises `view VIEW_TYPE VIEW_SCOPE`
* Insert a new calorie consumption goal `set AMOUNT on DD/MM/YYYY`
* Delete a calorie goal `deleteG Index`
* View the input calories goal `viewG`
* Achieve a calories goal `achieve Index`
* View achievement `achievement`
* Exit program `exit`


## Future Development

- We will update the achievement functionality such that we will have an algorithm to check whether you have achieved a particular goal by taking your exercise record and/or meal record into account.
- The goal list record will be updated at the start of the application to check if some current goals are not finished. It will mark such unfinished goal as failed automatically. User will also be allowed to delete such records by command.
- New feature to evaluate and seperate level of achievement
