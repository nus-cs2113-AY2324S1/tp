# User Guide
* Table of Contents
<!-- TOC -->
* [User Guide](#user-guide)
  * [Introduction](#introduction)
  * [Quick Start](#quick-start)
  * [Summary](#summary)
  * [Features](#features)
    * [Viewing help : `help`](#viewing-help--help)
    * [Adding a dish : `add`](#adding-a-dish--add)
    * [Deleting a dish : `delete`](#deleting-a-dish--delete)
    * [Editing price of a dish : `edit_price`](#editing-price-of-a-dish--editprice)
    * [Listing all dishes : `list_menu`](#listing-all-dishes--listmenu)
    * [Listing ingredients needed for the selected dish : `list_ingredients`](#listing-ingredients-needed-for-the-selected-dish--listingredients)
    * [Buying an ingredient : `buy_ingredient`](#buying-an-ingredient--buyingredient)
    * [Viewing the total stock of ingredients : `view_stock`](#viewing-the-total-stock-of-ingredients--viewstock)
    * [Adding an order : `add_order`](#adding-an-order--addorder)
    * [Showing total sales : `list_total_sales`](#showing-total-sales--listtotalsales)
    * [Showing sales for a chosen day : `list_sale`](#showing-sales-for-a-chosen-day--listsale)
    * [Advancing to the next day: `next_day`](#advancing-to-the-next-day-nextday)
    * [Returning to the previous day: `previous_day`](#returning-to-the-previous-day-previousday)
    * [Exiting the program : `bye`](#exiting-the-program--bye)
  * [Known Issues](#known-issues)
  * [Command Summary](#command-summary)
  * [Glossary](#glossary)
<!-- TOC -->

---------------------------------------------------
## Introduction

CaféCTRL aims to optimize managing of inventory and cash flow in a restaurant. Our CLI platform empowers users to streamline stock inventory, menu and orders. Users will also briefly be able to gain valuable insights through comprehensive sales reporting, enabling them to analyze sales trends and calculate revenue/profit margins, eliminating the need for cross-platform management.

---------------------------------------------------
## Quick Start
1. Ensure that you have Java `11` installed. 
2. Down the latest version of `CafeCtrl` from [here](https://github.com/AY2324S1-CS2113-T17-2/tp/releases).
3. Copy the file to the folder you want to use as the home folder for your Cafe Manager CLI Application.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar tp.jar` command to run the application.
5. If the setup is correct, you should see something like the below as the output:
```
Hello! Welcome to 
     _/_/_/                _/_/              _/_/_/    _/                _/   
  _/          _/_/_/    _/        _/_/    _/        _/_/_/_/  _/  _/_/  _/    
 _/        _/    _/  _/_/_/_/  _/_/_/_/  _/          _/      _/_/      _/     
_/        _/    _/    _/      _/        _/          _/      _/        _/      
 _/_/_/    _/_/_/    _/        _/_/_/    _/_/_/      _/_/  _/        _/       
------------------------------------------------------------------------
> 
```
---------------------------------------------------
<!---@@author ziyi105--->
## Summary
In CaféCTRL, the user is able to craft and **add dish** to the menu. If needed, he/she can **delete** a dish or *edit* the price of the dish that is already in the menu.
When there is a new order, the user can **add the order** and prepare it. If there is insufficient stock of ingredients, the user can **buy ingredients**. At the end of the day, the user can check the **sales of the day** or the **total sales** since day one. The user can advance to the **next day** or go back to the **previous day** to take in orders of the day.

---------------------------------------------------
<!---@@author DextheChik3n--->
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
> - Items in angle brackets shows the different arguments that must be used at least once.<br>
    e.g. `qty/INGREDIENT2_QTY<g/ml>`<br>
    can be used as<br>
    `qty/100g` or `qty/100ml`
> - Extraneous parameters for commands that do not take in parameters (such as `help`, `list_menu`, `bye`) will be ignored. <br>
    e.g. if the command specifies `help 123`, it will be interpreted as `help`.

<!---@@author ziyi105--->
### Viewing help : `help`
Shows a message explaining how to use all the commands

Format: `help`
Output:
```
------------------------------------------------------------------------
These are all the commands I recognise: 

- Words in UPPER_CASE are the parameters to be supplied by the user.
 e.g. in add name/NAME, NAME is a parameter that can be used as add name/Chicken.
- Parameters in [] are optional.

------------------------------------------------------------------------
To add a new dish to the menu: 
add name/DISH_NAME price/DISH_PRICE ingredient/INGREDIENT1_NAME qty/INGREDIENT1_QTY[, ingredient/INGREDIENT2_NAME, qty/INGREDIENT2_QTY...]
Example:add name/chicken rice price/3.00 ingredient/rice qty/200g, ingredient/chicken qty/100g
------------------------------------------------------------------------
To delete a menu item:
deleteParameters: INDEX
Example: delete 1
------------------------------------------------------------------------
To edit price of a menu item: 
edit_price dish/DISH_INDEX price/NEW_PRICE
Example: edit_price dish/1 price/4.50
------------------------------------------------------------------------
To view menu:
list_menu
------------------------------------------------------------------------
To list out the ingredients needed along with the quantity for a specific dish:
Parameters: index/DISH_INDEX
Example: list_ingredients index/1
------------------------------------------------------------------------
To buy ingredient:
buy_ingredient ingredient/INGREDIENT1_NAME qty/INGREDIENT1_QTY[, ingredient/INGREDIENT2_NAME, qty/INGREDIENT2_QTY...]
Example:buy_ingredient ingredient/milk qty/200ml, ingredient/chicken qty/100g
------------------------------------------------------------------------
To view pantry stock:
view_stock
------------------------------------------------------------------------
To add a new order: 
add_order name/DISH_NAME qty/QUANTITY
Example: add_ordername/chicken rice qty/2
------------------------------------------------------------------------
To show sales for all days:
list_total_sales
------------------------------------------------------------------------
To show sales for a chosen day:
list_sale day/DAY_TO_DISPLAY
Example: list_sale day/1
------------------------------------------------------------------------
To travel to next day:
next_day
------------------------------------------------------------------------
To go back to previous day:
previous_day
------------------------------------------------------------------------
To exit:
bye
------------------------------------------------------------------------
To view all commands:
help
------------------------------------------------------------------------
------------------------------------------------------------------------
```
<!---@@author DextheChik3n--->
### Adding a dish : `add`
Adds a dish consisting of its ingredients to the menu

Format: `add name/DISH_NAME price/PRICE ingredient/INGREDIENT1_NAME qty/INGREDIENT1_QTY<g/ml>[, ingredient/INGREDIENT2_NAME qty/INGREDIENT2_QTY<g/ml>, ...]`

* `DISH_NAME` can contain up to 35 alphanumeric characters with whitespaces
* `PRICE` must be a positive number and can be up to 2 decimal places.
* `INGREDIENT_QTY` must be a positive integer and contain the unit **ml** or **g** specifically.
  * e.g. `qty/50g` or `qty/1000ml`
  * Ingredients that are solid should use the `g` unit while ingredients that are liquid should use the `ml` unit

Example:
```
> add name/chicken rice price/2.00 ingredient/rice qty/100g, ingredient/chicken qty/200g, ingredient/soup qty/50ml
You have added the following dish...
+-------------------------------------------------------+
| Dish: chicken rice                                    |
+----------------------------------------+--------------+
| Price: $2.00                                          |
+----------------------------------------+--------------+
| Ingredient                             + Quantity     |
+----------------------------------------+--------------+
| rice                                   | 100g         |
| chicken                                | 200g         |
| soup                                   | 50ml         |
+-------------------------------------------------------+
```

<!---@@author ShaniceTang--->
### Deleting a dish : `delete`
Deletes a specific dish from the menu

Format: `delete DISH_INDEX`

* Deletes the dish at the specified DISH_INDEX
* The index refers to the index number shown in the menu list
* The index must be a positive integer

Example: `delete 1`

Output:
```
Okay! chicken rice is deleted! :)
```
<!---@@author ziyi105--->
### Editing price of a dish : `edit_price`
Edits the price of an existing dish on the menu

Format: `edit_price index/DISH_INDEX price/NEW_PRICE`

* `NEW_PRICE` must be a positive number and can have up to 2 decimal places.
* The index refers to the index number shown in the menu list
* The index must be a positive integer

Example: `edit_price index/1 price/4.50`

Output:
```
Price modified for the following dish: 
chicken rice $4.50
```

<!---@@author Cazh1--->
### Listing all dishes : `list_menu`
Shows a list of all dishes on the menu

Format: `list_menu`

Example:
```
+-----------------------------------------+
| Ah, behold, the grand menu of delights! |
+--------------------------+--------------+
| Dish Name                |  Price       |
+--------------------------+--------------+
| 1. chicken rice          |  $2.50       |
| 2. chicken curry         |  $4.30       |
+-----------------------------------------+
```

<!---@@author NaychiMin--->
### Listing ingredients needed for the selected dish : `list_ingredients`
Lists out the ingredients needed along with the quantity for a specific dish

Format: `list_ingredients dish/DISH_INDEX`

Example:
- list followed by list_ingredients dish/1 lists the ingredients of the 1st dish on the menu
```
+-------------------------------------------------------+
|Dish: chicken rice                                     |
+----------------------------------------+--------------+
| Ingredient                             + Quantity     +
+----------------------------------------+--------------+
| rice                                   | 100g         |
| chicken                                | 200g         |
| soup                                   | 50ml         |
+-------------------------------------------------------+
```


### Buying an ingredient : `buy_ingredient`
Adds one or more ingredients to the pantry

Format: `buy_ingredient ingredient/INGREDIENT1_NAME qty/INGREDIENT1_QTY<g/ml>[, ingredient/INGREDIENT2_NAME qty/INGREDIENT2_QTY<g/ml>, ...]`

* `INGREDIENT_QTY` must be a positive integer and contain the unit ml or g specifically
    * e.g. `qty/50g` or `qty/1000ml`

Example: `buy_ingredient ingredient/chicken qty/500g, ingredient/milk qty/1000ml`

Output:
```
Added to stock:
Ingredient: milk
Total Qty: 1000ml

Ingredient: chicken
Total Qty: 500g
```


<!---@@author ShaniceTang--->
### Viewing the total stock of ingredients : `view_stock`
Displays the available stock of all the ingredients found in the pantry

Format: `view_stock`

Output:
```
+-------------------------------------------------------+
| You have the following ingredients in pantry:         |
+----------------------------------------+--------------+
| Ingredients                            |  Qty         |
+----------------------------------------+--------------+
| chicken                                | 300g         |
| noodles                                | 2100g        |
| rice                                   | 2900g        |
| bread                                  | 500g         |
+-------------------------------------------------------+
```

<!---@@author Cazh1--->
### Adding an order : `add_order`
Adds an order consisting of dishes off the menu to an order list

Format: `add_order name/DISH_NAME qty/DISH_QTY`

Example:
```
> add_order name/chicken rice qty/2
I'm busy crafting your selected dish in the virtual kitchen of your dreams. Bon appétit!
-----------------------------------------------------
Order is ready!
Total order cost: $5.00
-----------------------------------------------------
Listed below are the availability of the dishes for the next order!
Dish: chicken rice
Available Dishes: 8
-----------------------------------------------------
Dish: chicken curry
Available Dishes: 4
```
* The `DISH_QTY` must be a positive integer number.

Adds an order to the current business day

Format: `add_order name/DISH_NAME qty/QUANTITY`

Example: `add_order name/chicken rice qty/2`

Output:
```
I'm busy crafting your selected dish in the virtual kitchen of your dreams. Bon appétit!
Is order completed?: true
Total orderList cost: $4.00
```


### Showing total sales : `list_total_sales`
Displays the dishes sold and total sales for each from Day 1 to the current day that 
the cafe is operating on.

Format: `list_total_sales`

Example: `list_total_sales`

Output:
- list_total_sales lists the dishes sold along with the total sales for every operating day of the cafe.
- in this case, the cafe has operated for two days.

```
+---------------------------------------------------------------------------+
| Day 1:                                                                    |
+----------------------------------------+--------------+-------------------+
| Dish Name                              |  Dish Qty    |  Total Cost Price |
+----------------------------------------+--------------+-------------------+
| Chicken Rice                           | 2            | 5.00              |
| Chicken Nuggets                        | 4            | 6.00              |
+---------------------------------------------------------------------------+
| Total for day:                                        | $11.00            |
+---------------------------------------------------------------------------+
+---------------------------------------------------------------------------+
| Day 2:                                                                    |
+----------------------------------------+--------------+-------------------+
| Dish Name                              |  Dish Qty    |  Total Cost Price |
+----------------------------------------+--------------+-------------------+
| Chicken Rice                           | 2            | 5.00              |
| Chicken Nuggets                        | 3            | 4.50              |
+---------------------------------------------------------------------------+
| Total for day:                                        | $9.50             |
+---------------------------------------------------------------------------+
```

### Showing sales for a chosen day : `list_sale`
Displays the dishes sold along with the total sales for any chosen day.

Format: `list_sale day/DAY_TO_DISPLAY`

Example: `list_sale day/2`

Output:
- list_sale day/2 lists the dishes sold along with the total sales for day 2.
```
+---------------------------------------------------------------------------+
| Day 2:                                                                    |
+----------------------------------------+--------------+-------------------+
| Dish Name                              |  Dish Qty    |  Total Cost Price |
+----------------------------------------+--------------+-------------------+
| chicken chop                           | 2            | 4.00              |
| chicken sandwich                       | 2            | 6.00              |
| chicken noodles                        | 1            | 2.00              |
+---------------------------------------------------------------------------+
| Total for day:                                        | $12.00            |
+---------------------------------------------------------------------------+
```
### Advancing to the next day: `next_day`

Proceeds to the next business day

Format: `next_day`

Output:
```
Prepare for liftoff! We're about to fast-forward to the next day. Hold onto your hats; here we go!
Today is Day 2
```

### Returning to the previous day: `previous_day`

Goes back to the previous business day

Format: `previous_day`

Output:
```
Sure thing! Let's rev up the virtual DeLorean and take a spin to the previous day. Buckle up, it's time to hit that rewind button!
Today is Day 1
```

<!---@@author--->
### Exiting the program : `bye`
Exits the program.

Format: `bye`

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
<!---@@author ziyi105--->
## Known Issues
- The application is unable to decode the data text files if they have been edited in the wrong decoding format.
- The application is unable to detect wrong argument tag, a general incorrect command format will be printed out for wrong argument tag.
- The application is unable to support unit conversion, hence only ml and g are accepted as ingredient unit and the use of unit must be constant for the same ingredient.
- The application is unable to save data for `Menu`, `Pantry` and `OrderList` if it is force exited using Ctrl+C command.
- The application currently accepts any valid dish name from orders.txt as a valid dish made on a given day, regardless of its presence in the current menu. 
  - This decision ensures that orders remain intact even if dishes are removed from the menu, allowing for a comprehensive record of all transactions.
  - Restricting orders to only those present in the current menu would lead to unintentional deletion of orders containing dishes no longer available.
  - Consideration was given to storing the names of dishes in the menu at the time the order was made in orders.txt. 
  - However, concerns about orders.txt becoming bloated with data were acknowledged.
  - As this does not affect the functionality of any aspects of our application nor the logic of functions related to the content stored in orders.txt, we have decided that this can be considered in future improvements. 

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
## Command Summary

| Action                    | Format, Examples                                                                                                                                                                                                                                                   |
|---------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**                   | `add name/DISH_NAME price/PRICE ingredient/INGREDIENT1_NAME qty/INGREDIENT1_QTY<g/ml> [, ingredient/INGREDIENT2_NAME qty/INGREDIENT2_QTY<g/ml>, ...]`<br><br/>Example:<br/>`add name/chicken rice price/3.00 ingredient/rice qty/50g, ingredient/chicken qty/100g` |
| **List Menu**             | `list_menu`                                                                                                                                                                                                                                                        |
| **List Ingredients**      | `list_ingredients index/DISH_INDEX`<br><br/>Example:<br>`list_ingredients index/1`                                                                                                                                                                                 |
| **Delete**                | `delete DISH_INDEX`<br><br/>Example:<br>`delete 1`                                                                                                                                                                                                                 |
| **Edit Price**            | `edit_price dish/DISH_INDEX price/NEW_PRICE`<br><br/>Example:<br>`edit_price dish/1 price/4.50`                                                                                                                                                                    |
| **List Sale**             | `list_total_sales`                                                                                                                                                                                                                                                 |
| **List Sale by Day**      | `list_sale day/DAY_TO_DISPLAY` <br><br/>Example:<br>`list_sale day/1`                                                                                                                                                                                              |
| **View Ingredient Stock** | `view_stock`                                                                                                                                                                                                                                                       |
| **Buy Ingredients**       | `buy_ingredient ingredient/INGREDIENT1_NAME<g/ml> qty/INGREDIENT1_QTY<g/ml>[, ingredient/INGREDIENT2_NAME qty/INGREDIENT2_QTY<g/ml>, ...]`<br><br/>Example<br>`buy_ingredient ingredient/chicken qty/500g, ingredient/milk qty/1000ml`                             |
| **Add Order**             | `add_order name/DISH_NAME qty/QUANTITY`<br><br/>Example:<br>`add_order name/chicken rice qty/2`                                                                                                                                                                    |
| **Previous Day**          | `previous_day`                                                                                                                                                                                                                                                     |
| **Next Day**              | `next_day`                                                                                                                                                                                                                                                         |
| **Help**                  | `help`                                                                                                                                                                                                                                                             |
| **Exit Program**          | `bye`                                                                                                                                                                                                                                                              |

---------------------------------------------------
<!---@@author ziyi105--->
## Glossary
- **Dish index**: Index of the dish according to `list_menu`.
- **Stock**: The quantity of ingredient available in the pantry of the cafe.