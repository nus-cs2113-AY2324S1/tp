---
layout: page
title: User Guide
---

EssenMakanan is a **desktop app for managing recipes and ingredients in your inventory, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). 
If you can type fast, EssenMakanan can get your recipes and ingredients management tasks done faster than traditional GUI apps.

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------
# User Guide

## Introduction

EssenMakanan is an app that keeps track of ingredients that a user has in the kitchen, stores recipes and provides steps on how to cook a specific recipe. This app will include a command line interface to use the available commands in the app.

## Quick Start

{Give steps to get started quickly}

1. The app requires Java 11 to be installed into your computer or laptop.
2. Download the latest `CulinaryCanvas.jar` fom [here](http://link.to/EssenMakanan)
3. Copy and move the file into the selected folder you want to put the app in.
4. Open your command line and input the command below to run the app:
`java -jar CulinaryCanvas.jar`

## Features 

{Give detailed description of each feature}

| Action               | Format                   | Example                                            | Notes |
|----------------------|--------------------------|----------------------------------------------------|-------|
| Add recipe           | add r/RECIPE_NAME        | add r/scramble egg                                 |       |
| Add ingredient       | add i/INGREDIENT_NAME    | add i/bread                                        |       |
| Delete Recipe        | delete r/RECIPE_NAME     |
| Delete Ingredient    | delete r/INGREDIENT_NAME | 
| View all ingredients | view i                   |
| View all recipes     | view r                   |
| View specific recipe | view r/RECIPE_ID         | View r/1 to show a recipe at index ‘1’ of the list |
| Help                 | help                     |


----
### System

1. Viewing help – `help`

    List all commands and the format and brief description of each command.

---
### Recipes 
2. View all recipes -  `view r`

   List all recipes available inside the app.


3. View specific recipe - `view r/RECIPE_ID`

   View the steps of a specified recipe with RECIPE_ID
  
    <br> Examples :
  
    * `view r/1` to show a recipe at index ‘1’ of the list.


4. Add new recipe - `add r/RECIPE_NAME`

   1. Message will appear to prompt user input for steps 
   2. To confirm step input, ENTER key 
   3. To end steps input: `end`
   4. Message will appear to prompt user input to add ingredients needed for recipe 
   5. Each ingredient input followed by an “enter” is one ingredient 
   6. To end input: `end`

   <br>  Example :
   
   * `add r/toast` to add a ‘toast’ recipe 
   * `add r/toast s/Put bread in toaster s/Wait until the toaster is done s/Take out the toast and enjoy i/bread`
   
     <br>Ingredients:
     * Bread only
     
      <br>Steps:
     * Step 1: “Put bread in toaster”
     * Step 2:“Wait until the toaster is done”
     * Step 3: “Take out the toast and enjoy” ENTER 


5. Delete a specific recipe - `delete r/RECIPE_ID`

    Delete the recipe from the recipe list.

   <br>  Example :
   
   * `delete r/1` to delete a recipe at index ‘1’



### Adding an ingredient: `add`
Adds a new item to the list of todo items.

Format: `add i/INGREDIENT_NAME|QUANTITY`

* The `INGREDIENT_NAME` can be in a natural language format.
* (Optional) The `QUANTITY` can be in natural language format.  

Example of usage: 

`add i/appple`

`add i/Apple i/banana`

`add i/apple|5 i/banana`


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`

