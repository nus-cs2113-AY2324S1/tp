---
layout: default
title: User Guide
---

EssenMakanan is a **desktop app for managing recipes and ingredients in your inventory, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). 
If you can type fast, EssenMakanan can get your recipes and ingredients management tasks done faster than traditional GUI apps.

--------------------------------------------------------------------------------------------------------------------
# User Guide

## Introduction

EssenMakanan is an app that keeps track of ingredients that a user has in the kitchen, stores recipes and provides steps on how to cook a specific recipe. This app will include a command line interface to use the available commands in the app.

## Quick Start

1. The app requires Java 11 to be installed into your computer or laptop.
2. Download the latest `Essenmakanan.jar` from [here](https://github.com/AY2324S1-CS2113-F11-2/tp/releases)
3. Copy and move the file into the selected folder you want to put the app in.
4. Open your command line and input the command below to run the app:
`java -jar Essenmakanan.jar`

## Features
#### Notes about the command format:<br>
* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add r/RECIPE_TITLE`, `RECIPE_TITLE` is a parameter which can be used as `add r/bread`.


* Items in square brackets are optional.<br>
  e.g `edit i/INGREDIENT_NAME [n/NEW_NAME] [q/NEW_QUANTITY] `. 
        The commands`edit i/bread n/toast q/10`, `edit i/bread n/toast` or `edit i/bread q/10` are all valid.


* tags with `...` after them can be used multiple times including zero times.<br>
  e.g. `add i/INGREDIENT_NAME,QUANTITY,UNIT [i/...]` can be used as `i/egg,2,pc`, `i/egg,2,pc i/flour,1,kg i/oil,2,l`. 
    Any number of ingredients after `i/` is valid as long as the format is correct.

<!-- 
// To be implemented
* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.
-->
* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit`)
  will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.


### [System Summary of Commands](#feature-system)
| Action                                         | Format                                                      | Example                                                                            |
|------------------------------------------------|-------------------------------------------------------------|------------------------------------------------------------------------------------|
| [Help](#help)<br>(list all commands available) | help                                                        | help                                                                               |
---

### [Recipes Summary of Commands](#feature-recipe)

| Action                                                                     | Format                                                      | Example                                                                            |
|----------------------------------------------------------------------------|-------------------------------------------------------------|------------------------------------------------------------------------------------|
| [View all recipes](#view-recipes)                                          | view r                                                      | view r                                                                             |
| [View specific recipe](#view-recipe)                                       | view r/RECIPE_ID                                            | view r/1                                                                           |
| [Add recipe](#add-recipe)                                                  | add r/RECIPE_TITLE t/TAG_NAME s/STEP_DESCRIPTION d/DURATION | add r/scramble egg t/1 s/buy ingredients t/2 s/wash ingredients s/bake ingredients |
| [Edit recipe](#edit-recipe)                                                | edit r/RECIPE_TITLE n/NEW_NAME s/STEP_NUM, NEW_STEP         | edit r/scramble egg n/toast s/1,buy ingredients                                    |
| [Delete Recipe](#delete-recipe)                                            | delete r/RECIPE_TITLE OR delete r/RECIPE_INDEX              | delete r/meatball noodles OR delete r/2                                            |
| [Duplicate Recipe](#duplicate-recipe)                                      | duplicate RECIPE_NAME or duplicate RECIPE_INDEX             | duplicate sandwich or duplicate 1                                                  |                                                              |                                                                                    |
| [Start recipe](#start-recipe) <br>(view missing ingredients from a recipe) | start RECIPE_TITLE <br><br>start RECIPE_ID                  | start dumpling noodles <br><br> start 1                                            |
| [Filter recipe by ingredients](#filter-recipe)                             | filter recipe i/INGREDIENT_NAME [i/...]                     | filter recipe i/chicken i/noodles                                                  |
|[Plan recipes for the week](#plan-recipe)|plan NUMBER_OF_RECIPES r/RECIPE_ID [r/...]|filter 2 r/1 r/3|

---

### [Ingredient Summary of Commands](#feature-ingredient)

| Action                                             | Format                                                      | Example                       |
|----------------------------------------------------|-------------------------------------------------------------|-------------------------------|
| [View all ingredients](#view-ingredients)          | view i                                                      | view i                        |
| [View specific ingredient](#view-ingredient)       | view i/INGREDIENT_NAME <br><br>view i/INGREDIENT_ID         | view i/bread <br><br>view i/1 |
| [Add ingredient](#add-ingredient)                  | add i/INGREDIENT_NAME                                       | add i/bread                   |
| [Edit ingredient](#edit-ingredient)                | edit i/INGREDIENT_NAME n/NEW_NAME q/NEW_QUANTITY u/NEW_UNIT | edit i/bread n/toast q/3 u/pc |
| [Delete Ingredient](#delete-ingredient)            | delete r/INGREDIENT_NAME OR delete r/INGREDIENT_ID          | delete i/EGG OR delete i/1    |


----
<a id="tags"></a>
### Tags
| Tag Index | Tag Description    |
|-----------|--------------------|
| 1         | NIGHT_BEFORE       |
| 2         | MORNING_OF_COOKING |
| 3         | MORE_THAN_ONE_DAY  |
| 4         | ACTUAL_COOKING     |

----
<a id="units"></a>
### Units
| Unit (to be used) | Full Unit Description |
|-------------------|-----------------------|
| g                 | gram                  |
| kg                | kilogram              |
| ml                | mililitre             |
| l                 | litres                |
| tsp               | teaspoon              |
| tbsp              | tablespoon            |
| cup               | cup                   |
| pc                | piece                 |

----
<a id="feature-system"></a>
### System Commands


<a id="help"></a>
#### Viewing help – `help`

List all commands and the format and brief description of each command.

---

<a id="feature-recipe"></a>
### Recipe Commands

<a id="view-recipes"></a>
#### View all recipes -  `view r`

List all recipes available inside the app.

---

<a id="view-recipe"></a>
#### View specific recipe - `view r/RECIPE_ID`

View the steps of a specified recipe with RECIPE_ID
  
Examples :
  
* `view r/1` to show a recipe at index ‘1’ of the list.
---

<a id="add-recipe"></a>
#### Add new recipe - `add r/RECIPE_TITLE s/STEP_DESCRIPTION i/INGREDIENT_NAME,QUANTITY,UNIT`

Add a new recipe to the list of recipes. A user is able to add more than one
step and ingredient to a recipe in 1 line.

Note 
- Multiple duration, steps and ingredients can be added
- `DURATION` and `TAGS` is optional. [Registered tags in our app](#tags)
- `RECIPE_TITLE`, `STEPS` and `INGREDIENT` are compulsory fields and at least 1 field must be entered

Examples :

- Format to add tags: `add r/RECIPE_TITLE t/TAG_NAME s/STEP_DESCRIPTION d/DURATION i/INGREDIENT`
  - `add r/meatball pasta t/1 s/defrost meatballs d/30mins t/2 s/Boil pasta s/fry meatballs i/pasta,100,g i/meatball,3,pc`
    
    This helps create a recipe in the format [TO BE ADDED]
  
<br>

- `add r/RECIPE_TITLE s/STEP_DESCRIPTION i/INGREDIENT`
  - `add r/tomato omelette s/crack egg into a bowl s/chop tomato and add into egg bowl s/fry everything on a pan i/tomato,1,pc i/egg,2,pc`

    This helps create a recipe in the format [TO BE ADDED]

---

<a id="delete-recipe"></a>
#### Delete a specific recipe - `delete r/RECIPE_ID` or `delete r/RECIPE_TITLE`

Delete the recipe from the recipe list.


Example :
   
* `delete r/1` 

  to delete a recipe at index ‘1’


* `delete r/meatball pasta`

    to delete recipe titled "meatball pasta"

---
<a id="edit-recipe"></a>
#### Edit a recipe - `edit r/RECIPE_TITLE n/NEW_RECIPE_NAME s/STEP_ID,NEW_STEP i/INRGEDIENT_ID,NEW_INGREDIENT_NAME`

Edit a recipe to change the name or steps. A user is able to edit name or step of a recipe.

Note:
- `RECIPE_TITLE` must be specified
- Choose 1 or more field from the following to edit: `n/NEW_NAME`, `s/STEP_ID,NEW_STEP` and `i/INRGEDIENT_ID,NEW_INGREDIENT_NAME`

Example:

   * `edit r/bread n/toast` to change `bread` to `toast`

[Output of "View r/bread" after the edit command]
![img_3.png](images/edit_recipe_eg_1.png)


   * `edit r/bread s/1,buy ingredients` to change the first step to `buy ingredients`


[Output of "View r/bread" after the edit command]
![img_4.png](images/edit_recipe_eg_2.png.png)

---

<a id="duplicate-recipe"></a> 
#### Duplicate a recipe - `duplicate RECIPE_NAME` or `duplicate RECIPE_INDEX`

Duplicate a recipe from the recipe list. The duplicated recipe with have `(copy)` within its name.

Example :

* `duplicate sandwich` to duplicate a recipe with the title `sandwich`
* `duplicate 1` to duplicate the first recipe on the list.

![img_3.png](images/DuplicateRecipe.png)
---    
<a id="start-recipe"></a>
#### Start a recipe - `start RECIPE_TITLE` or `start RECIPE_ID`

Use the start command to check if you are all set to start on the recipe.This command will list all missing ingredients from the recipe you want to start on.

<br>

Example:

* `start bread`
      
    To check if you have all ingredients needed for the recipe named "bread".
   
![img.png](images/StartRecipeCommand1.png)
   

* `start 1`

    To check if you have all ingredients needed for the recipe with id 1.

![img_1.png](images/StartRecipeCommand2.png)


---
<a id="filter-recipe"></a>
#### Filter recipe based by ingredients - `filter recipe i/INGREDIENT_NAME` or `filter recipe i/INGREDIENT_ID`

Filter your recipes by ingredients you are craving for that meal.

<br>

Example:
* `filter recipe i/egg`
   
    All recipes containing the ingredient egg will be printed
   
* `filter recipe i/egg i/vegetable`
   
![img_1.png](images/FilterRecipseByIngredientCommand.png)

---

<a id="plan-recipe"></a>
#### Plan recipes for the week `plan NUMBER_OF_RECIPES r/RECIPE_ID [r/...]`

Schedule recipes for a week or for as many days you want by using our plan command!
This allows you to plan for your grocery trip in advance.

Upon using the command, we will show you all the ingredients needed for all your planned recipes and ingredients that you are missing.

Note:
- `NUMBER_OF_RECIPES` must correspond to the total number of recipes ("r/...") you enter. This is a compulsory field.
- You need to input at least 1 recipe to plan
- To view your recipe ids, use the `view r` command

Example:
- `plan 2 r/1 r/2` 

  

---

<a id="feature-ingredient"></a>
### Ingredient Commands

<a id="view-ingredients"></a>
#### View all ingredients - `view i`
    
   List all ingredients available inside the app.

---
<a id="view-ingredient"></a>
#### View a specific ingredient - `view i/INGREDIENT_NAME` or `view i/INGREDIENT_ID`

Check the quantity of an ingredient you have available in your kitchen/inventory.

Example:
    
* `view i/flour`

    ![img.png](images/ViewSpecificIngredientCommand.png)

* `view i/1` to view the quantity of your ingredient with id 1

---
<br>

<a id="add-ingredient"></a>
#### Add ingredients - `add i/INGREDIENT_NAME,QUANTITY,UNIT`

Adds a new item to the list of todo items. [Registered units in our app](#units)
   
When entering your unit, a user is required to use any one of the units above.
    
Example :

* `add i/bread,2,pc` to add `2 pieces of bread` into the list
* `add i/cooking oil,5,l` to add `5 liters of cooking oil` into the list
   
---

<a id="edit-ingredient"></a>
#### Edit ingredient - `edit i/INGREDIENT_NAME edit i/INGREDIENT_NAME n/NEW_NAME q/NEW_QUANTITY u/NEW_UNIT`

Edit an ingredient to change the name, quantity or unit. A user is able to edit more than one property of an 
ingredient.

Example :

* `edit i/bread n/toast` to change `bread` to `toast`
* `edit i/egg q/10 u/kg` to change the quantity to `10` and the unit to `kg`

<br>

---
<a id="delete-ingredient"></a>
#### Delete ingredient - `delete i/INGREDIENT_INDEX` OR `[delete i/INGREDIENT_NAME]`

Delete an ingredient based on the selected index in the list or the ingredient's name.

Example :

* `delete i/2` to delete the `second` ingredient on the list
* `delete i/egg` to delete `egg` ingredient


---
## FAQ

**Q**: Why are there 2 methods to add a recipe? 

**A**: We are exploring the best way to add a recipe, for now users can 
choose to add a recipe with steps and ingredients or add a recipe with steps duration.
