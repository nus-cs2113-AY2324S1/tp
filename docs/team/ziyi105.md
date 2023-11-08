# Zi Yi - Project Portfolio Page

**Project: CafeCRTL**

-----------------------------------------------------------------------------------------------
## **Overview**
CaféCTRL is a software engineering project for CS2113. The user interacts with the application through CLI. It has around 6 kLoC, and it is done in a team of 5.

## **Value Proposition**
CaféCRTL aims to optimize managing of inventory and cash flow in a restaurant. Our CLI platform empowers Café proprietors to streamline inventory and menu management.

## **Target User**
Café proprietors who prefer typing on CLI than any other interaction method and are seeking for a software solution to optimize the management of their café's operations.

-----------------------------------------------------------------------------------------------

## **Summary of Contributions**
### Code Contribution

[Follow here to see code written by me](https://nus-cs2113-ay2324s1.github.io/tp-dashboard/?search=ziyi105&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos&tabOpen=true&tabType=authorship&tabAuthor=ziyi105&tabRepo=AY2324S1-CS2113-T17-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancements implemented

#### Feature

1.  **Edit Price** <br>
    Function: Allow user to edit price of the dish on the menu
    Command Format: `edit_price dish/DISH_INDEX price/NEW_PRICE`<br>
    Error Handling: This command is able to detect missing argument tag, missing argument, wrong argument type, empty argument type and same price. It will then output specific error message to give user hint.
    <br/><br/>
2.  **Help** <br>
    Function: Print out a list of commands and their usages to the user<br>

### Enhancement
1. **Storage**<br>
   Implemented Encoding.java, Decoding.java and Storage.java with skeleton methods for my teammates to implement. I spent quite some time trying to make sense of the storage system in addressbook level 3 and adopt it in our project. 
    <br><br>
2. **Parser**<br>
   Implemented skeleton class for Parser for other teammates to use. I also implemented ParserUtil interface to reduce coupling of Parser.java and CafeCRTL.java.
   <br><br>
3. **Messages & ErrorMessages**<br>
   Created classes to store all messages to the user as static constant.

### Contributions to UG
[UserGuide](https://ay2324s1-cs2113-t17-2.github.io/tp/UserGuide.html)

#### Individual Task
1. Edit price
2. Help

#### Team-based Task
1. Table of Contents
2. Introduction
3. Known Issues
4. Glossary
5. General formatting

### Contributions to DG
#### Individual Task
1. Parser component
2. Edit Price
3. Help

#### Team-based Task
1. Table of Contents
2. Acknowledgement
3. Setting up, getting started
4. Product Scope
5. Non-Functional Requirements
6. Glossary
7. General formatting

### Other Contributions to Team-based Task
1. Maintaining issue tracker with Dexter by labeling issues
2. Adding theme to UG & DG
3. Approving and merging PRs

### Review/Mentoring Contributions
1. Reviewed and approved 52 PRs in total.
   Some examples of PR reviewed: [#167](https://github.com/AY2324S1-CS2113-T17-2/tp/pull/167), [#106](https://github.com/AY2324S1-CS2113-T17-2/tp/pull/106), [#57](https://github.com/AY2324S1-CS2113-T17-2/tp/pull/57),
   [#96](https://github.com/AY2324S1-CS2113-T17-2/tp/pull/96)
2. Created a tp howto to guide teammates on how to start coding tp 
   ![Telegram screenshot of tp howto](docs/Images_PPP/tp_howto.png)
3. Shared my opinions in Telegram group.
   ![Relevant Telegram screenshot 1](docs/Images_PPP/telegram_chef_text.png)
   ![Relevant Telegram screenshot 2](docs/Images_PPP/relevant_telegram_screenshot_2.png)
   ![Relevant Telegram screenshot 3](docs/Images_PPP/relevant_telegram_screenshot_3.png)
   ![Relevant Telegram screenshot 3](docs/Images_PPP/relevant_telegram_screenshot_4.png)

### Contributions beyond the Project Team
1. Posted 8 posts in the forum.
   Examples of forum posts: [#37](https://github.com/nus-cs2113-AY2324S1/forum/issues/37), [#19](https://github.com/nus-cs2113-AY2324S1/forum/issues/19), [#31](https://github.com/nus-cs2113-AY2324S1/forum/issues/31)
2. Reviews on PR from other teams: [T18-1]https://github.com/nus-cs2113-AY2324S1/tp/pull/19#discussion_r1379823357, {will add more in the future}