# Naychi Min - Project Portfolio Page
**Project: CafeCRTL**

-----------------------------------------------------------------------------------------------
## **Overview**
CaféCTRL is a software engineering project for CS2113. The user interacts with the application through CLI. It has around 6 kLoC, and it is done in a team of 5.

## **Value Proposition**
CaféCRTL aims to optimize managing of inventory and cash flow in a restaurant. Our CLI platform empowers Café proprietors to streamline inventory and menu management.

## **Target User**
Café proprietors who prefer typing on CLI than any other interaction method and are seeking for a software solution to optimize the management of their café's operations.

-----------------------------------------------------------------------------------------------
## Summary of Contributions

### Code Contribution
[Follow here to see code written by me](https://nus-cs2113-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=functional-code~test-code~docs&since=2023-09-22&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=NaychiMin&tabRepo=AY2324S1-CS2113-T17-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=functional-code~test-code~docs&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancements implemented

#### Feature

1. **List Ingredient**
    - Function: Allow the user to view the ingredients of the desired dish from the menu.
    - Command Format: `list_ingredient INDEX_OF_DISH_TO_LIST`
    - Error Handling:
        - If the specified index is out of range, to prevent index out of bounds error.
        - If the specified index is of a wrong argument type to prevent number format exception.
        - If the specified index is empty.
        - Specific error messages will then be output to user along with recommended command format.
2. **List Total Sales**
    - Function: Allow the user to view the sale for each day across every day since the cafe has operated.
    - Command Format: `list_total_sales`
3. **Show Sale By Day** 
    - Function: Allow the user to view the sale for the desired day. <br>
    - Command Format: `list_sale day/DAY_TO_LIST` <br>
    - Error Handling:
      - If the specified index is out of range, to prevent index out of bounds error.
      - If the specified index is of a wrong argument type to prevent number format exception.
      - If the specified index is empty or the argument tag is missing.
      - Specific error messages will then be output to user along with recommended command format.
4. **Data processing of 'add_order'**
   - My group mate (Cazh1) did the parsing of the command, along with the implementation of needed classes such as Order, OrderList and Chef.
   - My role was to handle the logic of the data being processed after an order was added to an orderList for the following purposes:
     - Determine if the order can be completed
     - Determine which ingredients need to be restocked
   - This will be elaborated on in the section below.

#### Enhancements
1. **Pantry Class**
    - Collaborated with my group mate (ShaniceTang) to develop the Pantry class.
    - ShaniceTang focused on the buying and restocking of pantry ingredients, as detailed in her PPP.
    - My role, outlined in point 4 above, involved implementing key functions, including:
        - `isDishCooked`:
            - Returns a boolean, indicating the success of the order.
            - Manages the decrease and update of ingredient quantities in the Pantry Stock.
        - `calculateDishAvailability`:
            - Informs the user of the available quantity for each dish in the menu after each order.
        - `calculateMaxDishes`:
            - Handles the logic for calculating the number of dishes made.
            - Manages the logic for determining restocked ingredients and their required quantities.
            - Presents the information neatly in a table for the user.
    - Initially unfamiliar with OOP concepts, this class's creation, which interacts with various classes (menu, order, chef, dish, and ingredients), presented a challenging learning opportunity.
    - Explored the use of Java stream filter, a concept introduced in lectures.

2. **Encoding of Sales**
    - Implemented encoding for the Sales object, involving:
        - Parsing through various attributes of the Sales object.
        - Converting the data to a string with the delimiter '|'.
        - Storing the data in a text file.

3. **Decoding of Sales**
    - Executed decoding for the Sales object, encompassing:
        - Parsing through the text file and separating contents using the delimiter '|'.
        - Using parsed attributes to instantiate the Sales object for use in other command classes.
    - Implemented error handling during decoding:
        - Nonexistent dishes are filtered out.
        - Lines with missing delimiters or incorrect formatting in the text file are filtered out (collaboration with Cazh1).

4. **Parser**
    - Implemented parsing and error handling for the commands listed in the section above.

### Contributions to UG
[UserGuide](https://ay2324s1-cs2113-t17-2.github.io/tp/UserGuide.html)

1. List Ingredient
2. List Total Sales
3. List Sale by day

### Contributions to DG
1. Worked on ShaniceTang with the following:
   - Architecture diagram and overall description of the architecture
   - Sequence diagram of interactions of various components shown in the architecture diagram
   - Data Component class diagram and description
2. List Ingredient

### Other Contributions to Team-based Task
1. Maintain issue tracker
