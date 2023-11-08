# Martin Schneider - Project Portfolio Page

## Project : Stocker

Stocker is a Command Line Interface(CLI) app for drug inventory management purposes.
If you can type fast, it can get your inventory management tasks done faster than traditional
GUI apps.

### Summary of Contributions

-**Code contributed** [Link to reposense contribution graph](https://nus-cs2113-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=martinschnder&tabRepo=AY2324S1-CS2113-T17-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

-**Features implemented**

- Command result system
  - What it does : Provide a basic command interface to display various messages and lists to the user.
- Add command
  - What it does : Adds a particular drugs with all parameters to the inventry
- Cart handling
  - What it does : Allow a user (a vendor for example) to add various drugs in a temporary cart before checking it out and process a sale.

-**Classes implemented**

- `Command`
- `Parser`
- `CommandResult`
- `AddCommand`
- `AddToCartCommad`
- `ViewCartCommand`
- `CheckoutCommand`

-**Contributions to the UG**

- [Add Command](https://ay2324s1-cs2113-t17-3.github.io/tp/UserGuide.html#add---adds-drug-into-inventory-list)
- [Add to cart Command](https://ay2324s1-cs2113-t17-3.github.io/tp/UserGuide.html#addtocart---adds-drug-into-current-cart)

-**Contributions to the DG**

- [Main data structures](https://ay2324s1-cs2113-t17-3.github.io/tp/DeveloperGuide.html#main-data-structures),[Login System Component](https://ay2324s1-cs2113-t17-3.github.io/tp/DeveloperGuide.html#login-system-component),[Help Command](https://ay2324s1-cs2113-t17-3.github.io/tp/DeveloperGuide.html#4-help-command), [Save Command](https://ay2324s1-cs2113-t17-3.github.io/tp/DeveloperGuide.html#5-save-command), [addVendor Command](https://ay2324s1-cs2113-t17-3.github.io/tp/DeveloperGuide.html#6-addvendor-command),

-**Contributions to team-based tasks**

**Mentoring**

- Clarified misconceptions on workflow and application architecture in the initial few weeks
- Provided some advices and reviews on other members implementation.
