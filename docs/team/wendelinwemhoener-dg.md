
### **`flashcard` package**

#### **Package structure overview** 

The API of the `flashcard` package is defined in [`FlashcardComponent.java`](https://github.com/AY2324S1-CS2113-F11-3/tp/blob/master/src/main/java/seedu/duke/flashcard/FlashcardComponent.java).

The flashcard package is structured into multiple parts:

- [`Flashcard.java`](https://github.com/AY2324S1-CS2113-F11-3/tp/blob/master/src/main/java/seedu/duke/flashcard/Flashcard.java):
  Represents a single flashcard with front text, back text as well as 
  its unique id and current difficulty level.
- [`FlashcardCommandParser.java`](https://github.com/AY2324S1-CS2113-F11-3/tp/blob/master/src/main/java/seedu/duke/flashcard/FlashcardCommandParser.java):
  Parses user inputs into the corresponding commands.
- [`FlashcardComponent.java`](https://github.com/AY2324S1-CS2113-F11-3/tp/blob/master/src/main/java/seedu/duke/flashcard/FlashcardComponent.java):
  Encapsulates all functionality of the `flashcard` package and exposes it 
  in one single, unified API.
- [`FlashcardDirectory.java`](https://github.com/AY2324S1-CS2113-F11-3/tp/blob/master/src/main/java/seedu/duke/flashcard/FlashcardDirectory.java):
  Helper class needed for storing flashcards after TaskLinker has been 
  exited; see [the DG section about storage components](#storage-components).
- [`FlashcardList.java`](https://github.com/AY2324S1-CS2113-F11-3/tp/blob/master/src/main/java/seedu/duke/flashcard/FlashcardList.java):
  Container class for a list of flashcards. Exposes a simple, unified API 
  for dealing with a list of flashcards.
- [`FlashcardUi.java`](https://github.com/AY2324S1-CS2113-F11-3/tp/blob/master/src/main/java/seedu/duke/flashcard/FlashcardUi.java):
  Responsible for interfacing with the user: dispatches commands to be 
  executed and shows their output to the user.
- [`flashcard.command` package](https://github.com/AY2324S1-CS2113-F11-3/tp/tree/master/src/main/java/seedu/duke/flashcard/command):
  Contains classes representing the different kinds of commands (`list 
  flashcards`, `create flashcard` etc.).
- [`flashcard.exceptions` package](https://github.com/AY2324S1-CS2113-F11-3/tp/tree/master/src/main/java/seedu/duke/flashcard/exceptions):
  Contains classes representing custom exceptions that are specific to the 
  `flashcards` package.
- [`flashcard.review` package](https://github.com/AY2324S1-CS2113-F11-3/tp/tree/master/src/main/java/seedu/duke/flashcard/review):
  Contains classes representing the different flashcard review modes (random 
  mode and spaced repetition mode).

This class diagram provides a high-level overview of how the classes in the 
top-level `flashcard` package integrate with each other:

![class diagram of classes providing flashcard functionality](Diagrams/flashcard-diagrams/overview_classes.svg)

#### **Rough control flow overview**

The process of processing the initial user input and figuring out which 
command to  execute based on this user input is handled by the 
`FlashcardComponent`, `FlashcardCommandParser` and `FlashcardUi` classes.

During their operation, they create an instance of the appropriate 
`FlashcardCommand` (from the `flashcard.command` package) and then execute 
it; thereby performing the action the user wanted.

Put into a sequence diagram flow, the above-mentioned workflow looks like this:

![sequence diagram of processing the list flashcards input](Diagrams/flashcard-diagrams/overview_sequence.svg)

This workflow is divided into multiple parts:

1. When the user enters text ("list flashcards" in this specific case), `Duke` 
   first tries to figure out whether the flashcard package is responsible for
   handling it. To this end, `Duke` calls the `isResponsible` method of 
   `FlashcardComponent`.
2. The `isResponsible` method works by passing the input on to 
   `FlashcardCommandParser` and checking whether it returns an 
   UnknownCommand or not. In this specific case, `FlashcardCommandParser` 
   recognizes the "list flashcards" input and returns a matching 
   `ListFlashcardsCommand`. Thus, the `FlashcardComponent` knows that it 
   can process the inputted text and thus is responsible for handling it. 
   Thus, true is returned.
3. Because `FlashcardComponent` has returned `true` as response to the call 
   of its `isResponsbile` method, `Duke` passes the user input on to 
   `FlashcardComponent` via the `processInput` method.
4. Just like when executing the `isResponsible` method, `FlashcardComponent` 
   passes the input on to `FlashcardCommandParser` and in this specific case 
   gets back a `ListFlashcardsCommand`.
5. `FlashcardComponent` now passes this `ListFlashcardsCommand` on to the 
   `FlashcardUi` by invoking the latter's `executeCommand` method.
6. The `FlashcardUi` in turn calls the `execute` method of the 
   `ListFlashcardsCommand` it has just been passed.
7. The `ListFlashcardsCommand` is executed, showing a list of all flashcards 
   to the user, before control is returned to `Duke`.

#### **`flashcard.command` package**

The `flashcard.command` package contains the classes representing the 
different flashcard commands.

These are the classes representing the different commands:
- [`CreateFlashcardCommand.java`](https://github.com/AY2324S1-CS2113-F11-3/tp/blob/master/src/main/java/seedu/duke/flashcard/command/CreateFlashcardCommand.java)
  corresponds to the `create flashcards` command
- [`DeleteAllFlashcardsCommand.java`](https://github.com/AY2324S1-CS2113-F11-3/tp/blob/master/src/main/java/seedu/duke/flashcard/command/DeleteAllFlashcardsCommand.java)
  corresponds to the `delete all flashcards` command
- [`DeleteFlashcardCommand.java`](https://github.com/AY2324S1-CS2113-F11-3/tp/blob/master/src/main/java/seedu/duke/flashcard/command/ListFlashcardsCommand.java)
  corresponds to the `delete flashcard` command
- [`ListFlashcardsCommand.java`](https://github.com/AY2324S1-CS2113-F11-3/tp/blob/master/src/main/java/seedu/duke/flashcard/command/ListFlashcardsCommand.java)
  corresponds to the `list flashcards` command
- [`StartReviewCommand.java`](https://github.com/AY2324S1-CS2113-F11-3/tp/blob/master/src/main/java/seedu/duke/flashcard/command/StartReviewCommand.java)
  corresponds to the `review flashcards` command

All these classes inherit from the abstract class `FlashcardCommand` and 
define its `execute` method. This method serves as the entire API of a 
`FlashcardCommand`: The `execute` method is passed a scanner and an instance 
of `FlashcardList` that represents the currently used flashcards and then 
performs any actions necessary to execute the respective command.

Subclasses of `FlashcardCommand` are free to implement any additional 
private or protected fields and/or methods that are required for their 
internal operation. In this regard, because the commands all achieve very 
different goals (listing flashcards vs creating new flashcards), the different 
subclasses of `FlashcardCommand` can vary quite heavily. Therefore, in the 
interest of brevity, the individual subclasses are not explained in further 
detail and the reader is instead referred to their respective source code in 
the `src/main/java/seedu.duke/flashcard/command` directory.

##### "Dual Commands": Offering different input options for beginner vs expert users

As already explained in the user guide, there are so-called "Dual Commands" 
that can be invoked in two different ways: an easy, but more time-consuming way
for  beginner users who want as much guidance as possible; or a less
time-consuming, but more complicated way for expert users who don't need
additional guidance. Such "dual commands" are implemented as subclasses of 
the abstract class `DualFlashcardCommand`.

Currently, the following commands are dual commands (and as such inherit 
from `DualFlashcardCommand`):

- [`DeleteFlashcardCommand.java`](https://github.com/AY2324S1-CS2113-F11-3/tp/blob/master/src/main/java/seedu/duke/flashcard/command/ListFlashcardsCommand.java)
- [`StartReviewCommand.java`](https://github.com/AY2324S1-CS2113-F11-3/tp/blob/master/src/main/java/seedu/duke/flashcard/command/StartReviewCommand.java)

The abstract `DualFlashcardCommand` mode contains two abstract methods that 
need to be implemented by its subclasses:

- `executeBeginnerMode`: Implementing this abstract method allows implementing 
  the beginner mode (with interactive input) that allows for easier, but 
  more time-consuming usage.
- `executeExpertMode`: Implementing this abstract method allows implementing
  the expert mode (with one-line input) that allows for less time-consuming, 
  but more complicated usage.

This class diagram depicts the internal structure of the `flashcard.command` 
package and especially highlights how `CreateFlashcardCommand`, 
`DeleteAllFlashcardsCommand` and `ListFlashcardsCommand` directly inherit 
from `FlashcardCommand`; whereas `DeleteFlashcardCommand` and 
`StartReviewCommand` inherit from `DualFlashcardCommand` which in itself 
inherits from `FlashcardCommand`:

![class diagram of flashcard.command package](Diagrams/flashcard-diagrams/command_package_classes.svg)

#### **`flashcard.exceptions` package**

This package contains the `FlashcardException` base class from which all
flashcard-specific exceptions are derived.

Individual, flashcard-specific exceptions are implemented as subclasses of
the `FlashcardException` class. For further details, you can see the Javadoc
comments in their source code.

Currently, the flashcard-specific exceptions are:

- [`InvalidFlashcardIdException`](https://github.com/AY2324S1-CS2113-F11-3/tp/blob/master/src/main/java/seedu/duke/flashcard/exceptions/InvalidFlashcardIdException.java):
  Signifies that a given flashcardId is not valid, i.e. that no flashcard with
  that id appears in the list of flashcards which are currently being worked on.
- [`InvalidReviewModeException`](https://github.com/AY2324S1-CS2113-F11-3/tp/blob/master/src/main/java/seedu/duke/flashcard/exceptions/InvalidReviewModeException.java):
  Signifies that an invalid, i.e. non-existent review mode has been chosen.

#### **`flashcard.review` package**

This package contains all classes related to review modes for flashcards.

It contains the `ReviewMode` base class that provides functionalities to 
easily review flashcards that can be used by subclasses as building blocks to
implement specific kinds of reviews (e.g. random or spaced repetition reviews). 

There are two subclasses which inherit from `ReviewMode`, namely 
`RandomReviewMode` and `SpacedRepetitionReviewMode`.

In this regard, because the different review modes all use very
different revision strategies (random picking vs difficulty-based picking of 
flashcards), the different subclasses of `ReviewMode` can vary quite heavily. 
Therefore, in the interest of brevity, the individual subclasses are not
explained in further detail and the reader is instead referred to their
respective source code in the `src/main/java/seedu.duke/flashcard/review`
directory.
