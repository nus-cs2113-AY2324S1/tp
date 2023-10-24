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

output

![](C:\Users\puach\Documents\Y2S1\CS2113\tp\docs\images\vis\Screenshot%202023-10-24%20113526.png)

This feature was implemented with the help of three different classes.

Class Diagram



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
