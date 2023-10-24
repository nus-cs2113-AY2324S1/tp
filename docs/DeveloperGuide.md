# Developer Guide

## Acknowledgements
**Xchart (A Simple Charting Library for Java)**
- author: KNOWN
- source: https://knowm.org/open-source/xchart/

**JSON Simple (simple Java toolkit for encoding and decoding JSON)**
- author: Yidong Fang (Google Code)
- source: https://code.google.com/archive/p/json-simple/

**Apache Common Langs 3**
- author: Apache Commons
- source: https://commons.apache.org/proper/commons-lang/

**Alpha Vantage Stock Market API**
- author: Alpha Vantage
- source: https://www.alphavantage.co/

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Visualization Feature 

This feature is implemented with the help of [XChart](https://knowm.org/open-source/xchart/), a simple charting library for Java by Knowm.

By typing in the vis command with the appropriate arguments (/s and /t), users will be able to visualize their income or expense 
using visualization tools (Piechart, Bar Chart)

Demo: 

`vis /t expense /c pie`

Output

`Displaying piechart for expense`
A message will be shown telling you that the chart is being displayed

![](C:\Users\puach\Documents\Y2S1\CS2113\tp\docs\images\vis\visOutput.png)

This feature was implemented with the help of three different classes.
They are namely: Visualizer, Categorizer, VisCommand (Inherits from abstract Command Class)

VisCommand's Role: 
1) Read the parameters of the vis command entered by the user
- `/t` Reads the type of cashflow that the user wants to visualize (income/expense)
- `/c` Reads the type of visualization tools the user wants (piechart/barchart)

2) Calls the Cateorgizer to sort cashflow (Income/Expense) according to type

3) Calls the Visualizer to display the chart to the user

Categorizer's Role: 

According to the cashflow type (Income/Expense) arugment passed in, the Categorizer sorts the 
specified cashflow entry according to type using a Hashmap which is returned and used by the Visualizer

Visualizer's Role: 

According to the chart type (Pie/Bar) argument and the Hashmap obtained from the categorizer passed in, 
the visualizer displays the specified visualization chart by calling the charting library Xchart.

### Class Diagram

![](C:\Users\puach\Documents\Y2S1\CS2113\tp\docs\images\vis\visualisationClass.png)

### Sequence Diagram 

Overall 

![](C:\Users\puach\Documents\Y2S1\CS2113\tp\docs\images\vis\visualisationSequence.png)

Categorizer

![](C:\Users\puach\Documents\Y2S1\CS2113\tp\docs\images\vis\categorizerSequence.png)

Visualizer

![](C:\Users\puach\Documents\Y2S1\CS2113\tp\docs\images\vis\visualizerSequence.png)

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
