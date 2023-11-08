# Zhong Heng - Project Portfolio Page
**Project: CafeCRTL**

-----------------------------------------------------------------------------------------------
## **Overview**
CaféCTRL is a software engineering project for CS2113. The user interacts with the application through CLI. It has around 6 kLoC, and it is done in a team of 5.

## **Value Proposition**
CaféCRTL aims to optimize managing of inventory and cash flow in a restaurant. Our CLI platform empowers Café proprietors to streamline inventory and menu management.

## **Target User**
Café proprietors who prefer typing on CLI than any other interaction method and are seeking for a software solution to optimize the management of their café's operations.

-----------------------------------------------------------------------------------------------


### Summary of Contributions

### Code Contribution
[Follow here to see code written by me](https://nus-cs2113-ay2324s1.github.io/tp-dashboard/?search=cazh1&breakdown=true)

### Enhancements implemented

#### Feature

1.  **List Menu** <br>
    Function: Allow user to view all dishes added to menu <br>
    Command Format: `list_menu`
    <br/><br/>
2.  **Add Order** <br>
    Function: Adds an order consisting of dishes off the menu to an order list <br>
    Command Format: `add_order name/DISH_NAME qty/DISH_QTY`<br>
    Error Handling: This command is able to detect missing argument tag, missing argument, wrong argument type, empty argument type. It will then output specific error message to prompt the user to enter the correct Command format.
    <br/><br/>
3.  **Day Change Commands** <br>
    Added the ability to traverse through different days. <br>
    Function: Next Day and Previous Day commands allow the user to traverse through different days to capture sales, simulating a sales system used in a restaurant. <br>
    Command Format: `next_day` and `previous_day`<br>
    Error Handling: Next Day command ensures that the intended traversed day has a proper orderList, preventing a NullPointerException. Previous Day command will prevent the user from traversing to days before Day 1.
    <br/><br/>
4.  **Hashing text save files** <br>
    Implemented Hashing for text files used to save app user input data. <br>
    What it does: Hashes the text files generated from user actions (such as Menu, Sales and PantryStock), that will be accessed to load relevant data back into the application. <br>
    Justification: This was done to detect tampering of these save files which may potentially crash or induce unexpected behaviour from the application when the data is read and loaded into the application. <br>
    Highlights: This feature relied on knowledge learnt in CS2040C, Data Structures and Algorithms, to understand how hashing works and how to implement this feature.<br>
    Credits: https://www.geeksforgeeks.org/java-string-hashcode-method-with-examples/
    <br/><br/>

### Enhancement
1. **Main**<br>
   Implemented skeleton code for Main for other teammates to use.
   <br><br>
2. **Parser**<br>
   Implemented skeleton class for Parser for other teammates to use. I refactored Parser such that only relevant parameters are passed into commands when executing. This removes bloat from the Command class and leaves the work to the Parser class.
   <br><br>
3. **Command**<br>
   Implemented skeleton class for Command for other teammates to use. I refactored most Command classes such that only relevant parameters are passed into commands when executing.
   <br><br>
4. **Order**<br>
   Implemented class for Order, OrderList, Sales and Chef. These classes are accessed by Pantry and Menu for features relating to Orders and Sales, accounting for 1/3 of the application features.
   <br><br>
5. **Printing**<br>
   Implemented the printing style of auto formatting box used by `list_menu`, `view_stock` and `show_sales`.
   <br><br>
6. **Order**<br>
   Implemented class for CurrentDate. This class is used to facilitate the day changes and sales.
   <br><br>

### Contributions to UG
[UserGuide](https://ay2324s1-cs2113-t17-2.github.io/tp/UserGuide.html)

1. List Menu
2. Add Order
3. Next Day
4. Previous Day

### Contributions to DG
1. List Menu
2. Add Order
3. Next Day
4. Previous Day

### Other Contributions to Team-based Task
1. Maintain issue tracker
2. Bug testing for the application and providing detailed steps to recreate found bugs. <br>
   ![Bugs Found](../images_PPP/zhongheng/Bug_reporting.png)
   ![Bugs Found](../images_PPP/zhongheng/Pantry_load_from_file_bug.png)
   ![Bugs Found](../images_PPP/zhongheng/Pantry_restock_bug.png)


