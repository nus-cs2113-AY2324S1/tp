# User Guide
* Table of Contents
<!-- TOC -->
* [User Guide](#user-guide)
  * [Introduction](#introduction)
  * [Quick Start](#quick-start)
  * [Features](#features)
    * [Viewing help : `help`](#viewing-help--help)
    * [Adding a dish : `add`](#adding-a-dish--add)
    * [Listing all menu items : `list_menu`](#listing-all-menu-items--listmenu)
    * [Listing ingredients needed for the selected dish : `list_ingredients`](#listing-ingredients-needed-for-the-selected-dish--listingredients)
    * [Deleting a menu item : `delete`](#deleting-a-menu-item--delete)
    * [Editing price of menu item : `edit_price`](#editing-price-of-menu-item--editprice)
    * [Viewing the total stock of ingredients : `view_stock`](#viewing-the-total-stock-of-ingredients--viewstock)
    * [Buying an ingredient : `buy_ingredient`](#buying-an-ingredient--buyingredient)
    * [Showing all sales : `show_sales`](#showing-all-sales--showsales)
    * [Showing sales for a chosen day : `show_sale`](#showing-sales-for-a-chosen-day--showsale)
    * [Adding an order : `add_order`](#adding-an-order--addorder)
    * [Returning to the previous day: `previous_day`](#returning-to-the-previous-day-previousday)
    * [Advancing to the next day: `next_day`](#advancing-to-the-next-day-nextday)
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
Hello! Welcome to CafeCTRL!
-----------------------------------------------------
> 
```

---------------------------------------------------
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

edit_priceTo edit price of a menu item: edit_price dish/DISH_INDEX price/NEW_PRICE
Example: edit_price dish/1 price/4.50
```
<!---@@author DextheChik3n--->
### Adding a dish : `add`
Adds a dish consisting of its ingredients to the menu

Format: `add name/DISH_NAME price/PRICE ingredient/INGREDIENT1_NAME qty/INGREDIENT1_QTY[, ingredient/INGREDIENT2_NAME qty/INGREDIENT2_QTY, ...]`

* `DISH_NAME` can contain up to 35 alphanumeric characters with whitespaces
* `PRICE` must be a positive number and can be up to 2 decimal places.
* `IMGREDIENT_QTY` must be a positive integer and contain the unit **ml** or **g** specifically.
  * e.g. `qty/50g` or `qty/1000ml`

Example:
```
> add name/chicken rice price/2.00 ingredient/rice qty/100g, ingredient/chicken qty/200g, ingredient/soup qty/50ml
You have added the following dish...
Dish Name: chicken rice
Dish Price: $2.00
chicken rice Ingredients: 
rice - 100g
chicken - 200g
soup - 50ml
```

<!---@@author Cazh1--->
### Listing all menu items : `list_menu`
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

Format: `list_ingredients DISH_INDEX`

Example:
- list followed by list_ingredients 1 lists the ingredients of the 1st dish on the menu
```
chicken salad Ingredients: 
pasta - 100g
chicken - 200g
lettuce - 200g
tomatoes - 100g
feta cheese - 20g
-----------------------------------------------------
```


list followed by list_ingredients 1 lists the ingredients of the 1st dish on the menu

<!---@@author ShaniceTang--->
### Deleting a menu item : `delete`
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
### Editing price of menu item : `edit_price`
Edits the price of an existing dish on the menu

Format: `edit_price index/DISH_INDEX price/NEW_PRICE`

* `NEW_PRICE` must be a positive number and can be up to 2 decimal places.
* The index refers to the index number shown in the menu list
* The index must be a positive integer

Example: `edit_price index/1 price/4.50`

Output: 
```
Price modified for the following dish: 
Chicken rice - $4.50
```

<!---@@author ShaniceTang--->
### Viewing the total stock of ingredients : `view_stock`
Displays the available stock of all the ingredients found in the pantry

Format: `view_stock`

Output:
```
You have the following ingredients in pantry:
Ingredients		Qty
chicken			500g
milk			1000ml
```


### Buying an ingredient : `buy_ingredient`
Adds one or more ingredients to the pantry

Format: `buy_ingredient ingredient/INGREDIENT1_NAME qty/INGREDIENT1_QTY[, ingredient/INGREDIENT2_NAME qty/INGREDIENT2_QTY, ...]`

Example: `buy_ingredient ingredient/chicken qty/500g, ingredient/milk qty/1000ml`

Output:
```
Added to stock:
Ingredient: chicken		Qty: 500g
Ingredient: milk		Qty: 1000ml
```



### Showing all sales : `show_sales`
Displays the dishes sold and total sales for each from Day 1 to the current day that 
the cafe is operating on.

Format: `show_sales`

Example: `show_sales`

Output:
- show_sales lists the dishes sold along with the total sales for every operating day of the cafe.
```
Day 1:
Dish Name            Dish Qty   Total Cost Price    

chicken rice         4          12.0                 

Total for day: $12.00

Day 2:
Dish Name            Dish Qty   Total Cost Price    

chicken chop         5          21.0    

chicken rice         3          9.0                

Total for day: $30.00

```


### Showing sales for a chosen day : `show_sale`
Displays the dishes sold along with the total sales for any chosen day.

Format: `show_sale day/DAY_TO_DISPLAY`

Example: `show_sale day/1`

Output:
- show_sale day/1 lists the dishes sold along with the total sales for day 1.
```
Day 1:
Dish Name            Dish Qty   Total Cost Price    

chicken rice         4          12.0                 

Total for day: $12.00
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


### Returning to the previous day: `previous_day`

Goes back to the previous business day

Format: `previous_day`

Output:
```
Sure thing! Let's rev up the virtual DeLorean and take a spin to the previous day. Buckle up, it's time to hit that rewind button!
Today is Day 1
```

### Advancing to the next day: `next_day`

Proceeds to the next business day

Format: `next_day`

Output:
```
Prepare for liftoff! We're about to fast-forward to the next day. Hold onto your hats; here we go!
Today is Day 2
```
<!---@@author--->

### Exiting the program : `bye`
Exits the program.

Format: `bye`

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
## Known Issues
- The application is unable to read the data text files if they have been edited directly in the wrong decoding format.
- The application is unable to detect wrong argument tag, a general incorrect command format will be printed out for wrong argument tag.
- The application is unable to support unit conversion, hence only ml ang g are accepted as ingredient unit.

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
## Command Summary

| Action                    | Format, Examples                                                                                                                                                                                                                                       |
|---------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**                   | `add name/DISH_NAME price/PRICE ingredient/INGREDIENT1_NAME qty/INGREDIENT1_QTY [, ingredient/INGREDIENT2_NAME qty/INGREDIENT2_QTY, ...]`<br><br/>Example:<br/>`add name/chicken rice price/3.00 ingredient/rice qty/50g, ingredient/chicken qty/100g` |
| **List Menu**             | `list_menu`                                                                                                                                                                                                                                            |
| **List Ingredients**      | `list_ingredients DISH_INDEX`<br><br/>Example:<br>`list_ingredients 1`                                                                                                                                                                               |
| **Delete**                | `delete DISH_INDEX`<br><br/>Example:<br>`delete 1`                                                                                                                                                                                                   |
| **Edit Price**            | `edit_price index/DISH_INDEX price/NEW_PRICE`<br><br/>Example:<br>`edit_price index/1 price/4.50`                                                                                                                                                    |
| **Show Sale**             | `show_sales`                                                                                                                                                                                                                                           |
| **Show Sale by Day**      | `show_sale day/DAY_TO_DISPLAY` <br><br/>Example:<br>`show_sale day/ 1`                                                                                                                                                                               |
| **View Ingredient Stock** | `view_stock`                                                                                                                                                                                                                                           |
| **Buy Ingredients**       | `buy_ingredient ingredient/INGREDIENT1_NAME qty/INGREDIENT1_QTY[, ingredient/INGREDIENT2_NAME qty/INGREDIENT2_QTY, ...]`<br><br/>Example<br>`buy_ingredient ingredient/chicken qty/500g, ingredient/milk qty/1000ml`                                 |
| **Add Order**             | `add_order name/DISH_NAME qty/QUANTITY`<br><br/>Example:<br>`add_order name/chicken rice qty/2`                                                                                                                                                      |
| **Previous Day**          | `previous_day`                                                                                                                                                                                                                                         |
| **Next Day**              | `next_day`                                                                                                                                                                                                                                             |
| **Help**                  | `help`                                                                                                                                                                                                                                                 |
| **Exit Program**          | `bye`                                                                                                                                                                                                                                                  |

---------------------------------------------------
## Glossary
- **Dish index**: Index of the dish according to `list_menu`.
- **stock**: The quantity of ingredient available in the pantry of the cafe.