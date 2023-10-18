# User Guide

## Introduction

Are you ready to embark on a journey towards a healthier, 
more active lifestyle? Introducing FitTrack, 
your ultimate fitness and nutrition companion. 
FitTrack is more than just an app; 
it's your personal guide to achieving your health and fitness goals.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `FitTrack` from [here](https://github.com/AY2324S1-CS2113-W12-4/tp/releases).
3. You should find the jar file in your default downloads folder. Please place the jar file into a separate folder that will be used as your `home folder`.
4. Open a command terminal, and change the current working directory to the `home folder`.
5. Type ```java -jar tp.jar``` in the terminal to open the application. You should see the welcome message "Hi!" on the next line.
6. The application is now ready for you to use! Type `help` to see a list of commands that you will be able to use in the application.


## Features 

* [Viewing help : `help`](#View-Help-Guide-help)
* [Exiting the application : `exit`](#Exiting-the-application-exit)
* [Viewing list of workout : `viewWorkout`](#viewing-list-of-all-workouts-viewworkout)
* [Adding a Meal : `addmeal`](#adding-a-meal-addmeal)
* [Delete a Meal : `deletemeal`](#delete-a-meal-deletemeal)
* [Delete a Workout : `deletework`](#delete-a-workout-deleteworkout)


### View Help Guide: `help`
Shows the list of commands with the command format and short explanation.

**Example of usage:**

```
help
```

**Expected output:**
```
`help` shows help message of the command.
Existing commands:
help, exit, editprofile, viewprofileaddmeal, deletemeal, viewmealsaddworkout, deleteworkout, viewworkouts
Type `help` or `help <COMMAND>` to view help.
```

### Exiting the application: `exit`
Exits Skippy Chat Bot application.

**Example of usage:**

```
exit
```

**Expected output:**
```
Goodbye! Hope to see you again soon!
```

### Viewing List of All Workouts: `viewWorkout`
Lists all the workouts.

Format: `viewworkouts`

**Example of usage:**
```
viewworkouts
```

**Expected output:**
```
These are the workouts you have done:

```

### Editing Your Profile: `editProfile`
Allows user to edit their profile details.

Format: `editProfile h/<height> w/<weight> l/<calories>`

Example of usage: 
```
editProfile h/170 w/70 l/100
```
Expected output:
```
I've edited the following:
Height: 170.0
Weight: 70.0
Daily calorie limit: 100.0
```

### Adding a Meal: `addmeal`
Allows user to add meals they have consumed.

Format: `addmeal <meal> /cals <calories>`

Example of usage:
```
addmeal pasta /cals 200
```
Expected output:
```
I've added the following meal:
Meal name: pasta 
Calories: 200.0
```

### Adding a Workout: `addworkout`
Allows user to add workouts they have done.

Format: `addworkout <workout> /cals <calories>`

Example of usage:
```
addworkout running /cals 180
```
Expected output:
```
I've added the following workout:
Workout name: running 
Calories: 180.0
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
I've deleted the following meal:
Meal name: pasta 
Calories: 200.0
```

### Delete a Workout: `deleteworkout`
Allows user to delete a workout they have added.

Format: `deleteworkout <index of workout>`

Example of usage:
```
deleteworkout 1
```
Expected output:
```
I've deleted workout 1
```

## FAQ

**Q**: How do I edit my profile? 

**A**: Simply type editProfile and hit enter. The App will prompt you to re-enter your details.

## Command Summary

* Add Meal `addmeal`
* Add Work `addwork`
* Delete Meal `deletemeal`
* Delete Work `deletework`
* Edit Profile `editProfile`
* Exit Application `bye`
* Help List `help`
* List all workouts `viewworkout`
