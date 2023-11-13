# Wendelin Wemhoener - Project Portfolio Page

## Overview

TaskLinker is a CLI-tool for helping university students memorize flashcards
and track their flashcard and general academic progress in the courses they are
taking.

## Summary of Contributions

### Code Contribution

See my code contribution [here on the tP Code Dashboard](https://nus-cs2113-ay2324s1.github.io/tp-dashboard/?search=wendelinwemhoener&breakdown=true).

I implemented the `flashcard` package and coded all flashcard-related 
features (except for storing the flashcards in a text file for persistence).

I came up with the architecture of having a separate Parser and Ui component 
that work together to process input and create commands from a `command` 
subpackage that consists of the different commands inheriting from either a 
normal `Command` or `DualCommand` abstract class.

After I had developed this architecture for the `flashcard` package, the 
other team members copied my architecture and reused it for the `calendar` 
package. Also, in many cases, they reused my source code: e.g. the source code
of `DualEventCommand.java` in the `calendar.command` package is basically a 
perfect copy of the `DualFlashcardCommand.java` class I created in my 
`flashcard.command` package.

To make understanding my code and architecture easier, I wrote Javadoc for all 
the classes and methods (excluding getters, setters and constructors) I 
developed.

In total, I implemented 1241 lines of functional code, thus accounting for more 
than 44% of functional code produced by the entire team.

Also, I ensured that the build process runs without errors and that the 
text-ui-test passes.

Additionally, I implemented tests for the `flashcard` package and achieved 
100% class coverage with my tests, see here:
![img.png](img.png)

### Contributions to the UG

I documented all flashcard-related features: I documented each single 
command with a description, command format and a usage example and 
additionally wrote an explanation about what flashcards are and what the 
"dual commands" are. 

Also, I implemented a structure and formatting that the other team members 
reused.

Moreover, I wrote parts of the UG that are not specific to a feature: I 
wrote the `Introduction`, `Quick Start` and `Notes about the command format and 
sample output format` sections and started the `Command Summary` 

In total, I wrote 335 lines of the UG, thus accounting for over 60% of the 
entire user guide.

### Contributions to the DG

did hyperlinks to make navigation easier

product scope



posted on the forum repeatedly: https://nus-cs2113-ay2324s1.github.io/dashboards/contents/forum-activities.html

### Contributions to team-based tasks

- I set up the GitHub team org, created the project board on GitHub and set 
  up the "developers" team on GitHub
- I set up the repo and set up GitHub Pages
- I set up the issue tracker 
- I was responsible for release management (as well as setting up milestones)
- I documented target user profile and value proposition in UG/DG
- I set up the Google Doc we used for coordinating the project in the 
  initial phase and kept it updated with the ongoing changes
- I set up and administrated our Telegram chat group to allow for 
  efficient communication
- I created the majority of user stories for the DG
- I coordinated the weekly meetings

### Review/mentoring contributions: 

Helped my team members with the tasks during the tutorial.

Reviewed team members' code for style guide violations and gave them 
feedback on it (or fixed it myself for them).

### Contributions beyond the project team

I have created multiple forum posts to clarify questions and allow others to 
also profit from the answers. I currently sit at spot 8 on the forum 
activities dashboard, see [link](https://nus-cs2113-ay2324s1.github.io/dashboards/contents/forum-activities.html).

### Contributions to the User Guide (Extracts)

See all of my contributions to the UG [here](wendelinwemhoener-ug.md)

### Contributions to the Developer Guide (Extracts)

See all of my contributions to the DG [here](wendelinwemhoener-dg.md)