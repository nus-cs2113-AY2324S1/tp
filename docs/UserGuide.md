# User Guide

## Introduction

CafeCTRL aims to optimize managing of inventory and cash flow in a restaurant. Our CLI platform empowers Café proprietors to streamline inventory and menu management.


## Quick Start
1. Ensure that you have Java `11` or above installed. 
2. Down the latest version of `CafeCtrl` from [here](https://github.com/AY2324S1-CS2113-T17-2/tp/releases).
3. Copy the file to the folder you want to use as the home folder for your Cafe Manager CLI Application.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar tp.jar` command to run the application.
5. If the setup is correct, you should see something like the below as the output:
```
..Downloading data...
Hello! Welcome to CafeCTRL!
-----------------------------------------------------
> 
```

## Features 
> **Notes about command format:**
> - Words in `UPPER_CASE` are the arguments to be supplied by user. <br>
    e.g. in add name/NAME, NAME is a parameter that can be used as add name/Chicken.
> - Parameters need to be in the exact format as specified. <br>
    e.g. `add name/DISH_NAME price/PRICE ingredient/INGREDIENT1_NAME qty/INGREDIENT1_QTY`,<br> `name/` must come before `price/`.
> - Items in square brackets with …​ can be used multiple times including zero times. <br>
    e.g. `add name/DISH_NAME price/PRICE ingredient/INGREDIENT1_NAME qty/INGREDIENT1_QTY [, ingredient/INGREDIENT2_NAME qty/INGREDIENT2_QTY, ...]` <br>
    can be used as <br>
    `add name/Christmas Ham price/50.00 ingredient/Ham qty/1000g`<br>
    or as <br>
    `add name/chicken rice price/2.00 ingredient/rice qty/100g, ingredient/chicken qty/200g, ingredient/garlic qty/100g`
> - Extraneous parameters for commands that do not take in parameters (such as `help`, `list_menu`, `bye`) will be ignored. <br>
    e.g. if the command specifies `help 123`, it will be interpreted as `help`.

### Viewing help : `help`
Shows a message explaining how to use all the commands

Format: `help`
Output:
```
These are all the commands I recognise: 

(- Words in UPPER_CASE are the parameters to be supplied by the user.
e.g. in add name/NAME, NAME is a parameter that can be used as add name/Chicken.
- Parameters in [] are optional.)

Command Format:
add name/DISH_NAME price/DISH_PRICE ingredient/INGREDIENT1_NAME qty/INGREDIENT1_QTY[, ingredient/INGREDIENT2_NAME, qty/INGREDIENT2_QTY...]
(Items in square brackets [] are optional)
Example:
add name/chicken rice price/3.00 ingredient/rice qty/200g, ingredient/chicken qty/100g

list_ingredients: Lists out the ingredients needed along with the quantity for a specific dish.
Parameters: INDEX
Example: list_ingredients 1

To list out all dishes on the menu: list_menu

To delete a menu item: delete DISH_INDEX
Example: delete 1

edit_priceTo edit price of a menu item: edit_price index/DISH_INDEX price/NEW_PRICE
Example: edit_price index/1 price/4.50
```

### Adding a dish : `add`
Adds a dish consisting of its ingredients to the menu

Format: `add name/DISH_NAME price/PRICE ingredient/INGREDIENT1_NAME qty/INGREDIENT1_QTY[, ingredient/INGREDIENT2_NAME qty/INGREDIENT2_QTY, ...]`


* The `PRICE` must be a positive 2 decimal place number.
* The `IMGREDIENT_QTY` must contain the unit ml or g specifically.
  * e.g. `qty/50g` or `qty/1000ml`

Example:


### Listing all menu items : `list_menu`
Shows a list of all dishes on the menu

Format: list_menu

Example:

### Listing ingredients needed for the selected dish : `list_ingredients`
Lists out the ingredients needed along with the quantity for a specific dish

Format: `list_ingredients DISH_INDEX`

Example:

list followed by list_ingredients 1 lists the ingredients of the 1st dish on the menu



### Deleting a menu item : `delete`
Deletes a specific dish from the menu

Format: `delete DISH_INDEX`

* Deletes the dish at the specified DISH_INDEX
* The index refers to the index number shown in the menu list 
* The index must be a positive integer

Example:
* list followed by delete 4 deletes the 4th dish in the menu


### Editing price of menu item : `edit_price`
Edits the price of an existing dish on the menu

Format: `edit_price index/DISH_INDEX price/NEW_PRICE`

Example: `edit_price index/1 price/4.50`

Output: 
```
Price modified for the following dish: 
Chicken rice - $4.50
```

### Viewing the total stock of ingredients : `view_stock`
Displays the available stock of all the ingredients found in the pantry.

Format: `view_stock`

Example:


### Buying an ingredient : `buy_ingredient`


### Adding an order : `add_order`


### Exiting the program : `bye`
Exits the program.

Format: `bye`

## Command Summary
| Action                    | Format, Examples                                                                                                                                                                                                                                       |
|---------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**                   | `add name/DISH_NAME price/PRICE ingredient/INGREDIENT1_NAME qty/INGREDIENT1_QTY [, ingredient/INGREDIENT2_NAME qty/INGREDIENT2_QTY, ...]`<br><br/>Example:<br/>`add name/chicken rice price/3.00 ingredient/rice qty/50g, ingredient/chicken qty/100g` |
| **List Menu**             | `list_menu`                                                                                                                                                                                                                                            |
| **List Ingredients**      | `list_ingredients DISH_INDEX`<br/><br/>Example:<br/>`list_ingredients 1`                                                                                                                                                                               |
| **Delete**                | `delete DISH_INDEX`<br/><br/>Example:<br/>`delete 1`                                                                                                                                                                                                   |
| **Edit Price**            | `edit_price index/DISH_INDEX price/NEW_PRICE`<br/><br/>Example:<br/>`edit_price index/1 price/4.50`                                                                                                                                                    |
| **View Ingredient Stock** |                                                                                                                                                                                                                                                        |
| **Buy Ingredients**       |                                                                                                                                                                                                                                                        |
| **Add Order**             |                                                                                                                                                                                                                                                        |
| **Help**                  | `help`                                                                                                                                                                                                                                                 |
| **Exit Program**          | `bye`                                                                                                                                                                                                                                                  |

