---
layout: page
title: Developer Guide
---
* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

References
1. Developer Guide: https://se-education.org/addressbook-level3/DeveloperGuide.html 
2. User Guide: https://se-education.org/addressbook-level3/UserGuide.html
3. 

## Design & implementation

## Design

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

### UI component

### Logic component

### Model component

### Storage component


## Implementation
### Help Feature
The help feature is facilitated by the `HelpCommand` class. By calling `executeCommand` on the class, 
it will invoke the `Ui` class to print the user help commands.

<img src="images/HelpFunctionSequenceDiagram.png" width="963" />

### Add Recipe feature

The add recipe feature is facilitated by the `AddRecipeCommand` class. By calling `executeCommand` on the class, 
the steps will be executed as follows:
- **Step1**
  
  `AddRecipeCommand` will parse the recipe title using `RecipeParser`. Then, it will return the recipe title.


- **Step2**

  `AddRecipeCommand` will create a new `Recipe` with the obtained title.


- **Step3**

  `AddRecipeCommand` will add newly created `Recipe` into `RecipeList`. Then, the recipe will be added into an
  `ArrayList` inside `RecipeList`.


- **Step4**

  `AddRecipeCommand` will call `Ui` class to print out the title of the recently added recipe.

<img src="images/AddNewRecipeSequenceDiagram.png" width="963" />

### Add Ingredient feature
The add ingredient feature is facilitated by the `AddIngredientCommand` class. 
Multiple ingredients can be added at the same time using the syntax
`add i/NAME,QUANTITY,UNIT i/INGREDIENT2,.. i/INGREDIENT3...` 

By calling `executeCommand` on the class, the steps will be executed as follows:
- **Step 1**
  
  A for loop will interate through all the ingredients that are to be added. They are detected with the 
  `i/` flags in the user input.

- **Step 2** 

  The details of an ingredient (after the `i/` flag) will be parsed `IngredientParser` 
  using the `parseIngredient` method. 

- **Step 3**
  
  To standardise units of ingredients, the `IngredientUnit` enum will be used. `mapIngredientUnit`
  will be used to convert "layman" user inputs to the standardised units.

- **Step 4**
  
  The `Ingredient` object will be created using the `Ingredient` constructor, after the `ingredientName`, 
  `ingredientQuanity` and `ingredientUnit` of an ingredient is obtained.

- **Step 5**
  
  The `Ingredient` object will be added to the `IngredientList` using the `addIngredient` method.

- **Step 6**

  The `Ui` class will be called to print out the newly added ingredient.

<img src="images/AddNewIngredientSequenceDiagram.png" width="963" />




{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}


## Product scope
### Target user profile

{Describe the target user profile}

This product is for people who share kitchen space and ingredients with other cooks.


### Value proposition

{Describe the value proposition: what problem does it solve?}

Easy and intuitive way to keep track of ingredients you have in your kitchen. This helps avoid buying duplicated ingredients, reminds you of the ingredients you need, and gives a visualisation of the recipe timeline to ensure that advance preparation is done in time, eg marinating.


## User Stories

| Version | As a ...      | I want to ...                     | So that I can ...                        |
|---------|---------------|-----------------------------------|------------------------------------------|
| v1.0    | beginner user | see all recipes                   | learn and try all recipes                |
| v1.0    | beginner user | add new recipes into the list     | learn and try out new recipes            |
| v1.0    | beginner user | see all ingredient I have         |                                          |
| v1.0    | beginner user | add ingredients to the empty list | add an item to my kitchen inventory      |
| v2.0a   | amateur       | delete some ingredients           | remove a ingredient that I no longer own |

## Non-Functional Requirements

{Give non-functional requirements}
1. Should work on any mainstream OS as long as it has Java 11 or above installed.
2. Should be able to hold up to 1000 recipes and ingredients without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

## Glossary
* *glossary item* - Definition
- Mainstream OS: Windows, Linux, Unix, OS-X


## Instructions for manual testing
{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
