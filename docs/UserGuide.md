# User Guide

## Introduction

Are you ready to embark on a journey towards a healthier, 
more active lifestyle? Introducing FitTrack, 
your ultimate fitness and nutrition companion. 
FitTrack is more than just an app; 
it's your personal guide to achieving your health and fitness goals.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `FitTrack` from [here](https://github.com/AY2324S1-CS2113-W12-4/tp).
3. You should find the jar file in your default downloads folder. Please place the jar file into a separate folder that will be used as your `home folder`.
4. Open a command terminal, and change the current working directory to the `home folder`.
5. Type ```java -jar tp.jar``` in the terminal to open the application. You should see the welcome message "Hi!" on the next line.
6. The application is now ready for you to use! Type `help` to see a list of commands that you will be able to use in the application.


## Features 

* [Viewing help : `help`](#View-Help-Guide-help)
* [Exiting the application : `exit`](#Exiting-the-application-exit)
* [Viewing list of workout : `listWorkout`](#viewing-list-of-all-tasks-listworkout)
* [Adding a Meal : `addmeal`](#adding-a-meal-addmeal)
* [Checking daily calorie surplus limit : `checkcsl`](#check-daily-calorie-surplus-limit-checkcsl)
* [Checking Your Height : `checkHeight`](#checking-your-height-checkheight)
* [Checking Your Weight : `checkWeight`](#checking-your-weight-checkheight)
* [Delete a Meal : `deletemeal`](#delete-a-meal-deletemeal)
* [Delete a Workout : `deletework`](#delete-a-workout-deletework)
* [Set Daily Calorie Surplus Limit : `setcsl`](#set-daily-calorie-surplus-limit-setcsl)


### View Help Guide: `help`
Shows the list of commands with the command format and short explanation.

**Example of usage:**

```
help
```

**Expected output:**
```
Help List:
    1.
    2.
```

### Exiting the application: `exit`
Exits Skippy Chat Bot application.

**Example of usage:**

```
exit
```

**Expected output:**
```
Saving...
Goodbye! Hope to see you again soon!
```

### Viewing List of All Workouts: `listWorkout`
Lists all the workouts.

Format: `listWorkout`

**Example of usage:**
```
listWorkout
```

**Expected output:**
```
Here are your workouts:
1. Leg day
2. Walk
3. Run
```

### Editing Your Profile: `editProfile`
Allows user to edit their profile details.

Format: `editProfile`

Example of usage: 
```
editProfile
```
Expected output:
```
Done! I have edited your profile!
Name: John
Height: 180 cm
Weight: 70 kg
```

### Adding a Meal: `addmeal`
Allows user to add meals they have consumed.

Format: `addmeal <description>`

Example of usage:
```
addmeal pasta
```
Expected output:
```
Done! I have added a meal:
 1. pasta
```

### Adding a Workout: `addworkout`
Allows user to add workouts they have done.

Format: `addworkout <description>`

Example of usage:
```
addmeal run
```
Expected output:
```
Done! I have added a workout:
 1. run
```

### Check Daily Calorie Surplus Limit: `checkcsl`
Allows user to check their daily calorie surplus limit.

Format: `checkcsl`

Example of usage:
```
checkcsl
```
Expected output:
```
Daily Calorie Surplus Limit: 200 cal
```

### Checking Your Height: `checkHeight`
Allows user to check their current height in cm.

Format: `checkHeight`

Example of usage:
```
checkHeight
```
Expected output:
```
Your current height is 180 cm
```

### Checking Your Weight: `checkWeight`
Allows user to check their current weight in kg.

Format: `checkWeight`

Example of usage:
```
checkWeight
```
Expected output:
```
Your current weight is 70 kg
```

### Delete a Meal: `deletemeal`
Allows user to delete a meal they have added.

Format: `deletemeal <index of meal>`

Example of usage:
```
deletemeal 1
```
Expected output:
```
Sure! I've removed pasta from the list.
```

### Delete a Workout: `deletework`
Allows user to delete a workout they have added.

Format: `deletework <index of workout>`

Example of usage:
```
deletemeal 1
```
Expected output:
```
Sure! I've removed run from the list.
```

### Set Daily Calorie Surplus Limit: `setcsl`
Allows user to set their daily calorie surplus limit.

Format: `setcsl`

Example of usage:
```
setcsl
```
Expected output:
```
Daily Calorie Surplus Limit set to 200 cal
```

## FAQ

**Q**: How do I edit my profile? 

**A**: Simply type editProfile and hit enter. The App will prompt you to re-enter your details.

## Command Summary

* Add Meal `addmeal`
* Add Work `addwork`
* Check Daily Calorie Surplus Limit `checkcsl`
* Check Height `checkHeight`
* Check Weight `checkWeight`
* Delete Meal `deletemeal`
* Delete Work `deletework`
* Edit Profile `editProfile`
* Exit Application `bye`
* Help List `help`
* List all workouts `listWorkout`
* Set Daily Calorie Surplus Limit `setcsl`
