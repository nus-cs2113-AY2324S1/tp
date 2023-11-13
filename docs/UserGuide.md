# **User Guide**

## **Introduction**

TaskLinker is a CLI-tool for helping university students memorize flashcards 
and track their flashcard progress as well as general academic progress in 
the courses they are taking.

## **Quick Start**

1. Ensure that you have Java 11 or above installed.
2. Download the latest jar from [the latest release on GitHub](https://github.com/AY2324S1-CS2113-F11-3/tp/releases).
3. Run the jar file via `java -jar duke.jar`

## **Features**

### **Notes about the command format and sample output format:**

#### **Command format**

In the command format, words in UPPER_CASE are the parameters to be 
supplied by the user. e.g. in `delete flashcard FLASHCARD_ID`, FLASHCARD_ID 
is a parameter whose value shall be supplied by the user, e.g. as `delete 
flashcard 3`.

#### **Sample output format**

When sample output is provided, it is shown as a code block.

User input within the example is shown in bold, text printed by the program 
is shown in normal thickness.

E.g. in the following example, the user inputted the command "list 
flashcards" and the program printed out a list of the available flashcards.
<pre>
Enter your command: <b>list flashcards</b>
    Here is a list of all your flashcards: 

--------------------------------------------------------------------------------
front text: How long is a meter in cm?
back text: 100
id: 20
difficulty: 4
--------------------------------------------------------------------------------
front text: What does UML stand for?
back text: Unified Modelling Language
id: 21
difficulty: 6
--------------------------------------------------------------------------------

    Those were all your flashcards.
</pre>

### **Flashcard-related features**

#### **General explanation of flashcards**

Flashcards are two-sided cards that have a question on the front and its 
answer on the back. They are used for revising information and helping with 
memorization. As such, they are often used by students; e.g. medicine students often use 
flashcards to memorize the anatomy of the human body.

TaskLinker implements virtual flashcards that you can create, review and 
delete on the go, without being bound to physical copies.

In TaskLinker, every flashcard has a front text (the question) and a back 
text (the answer). Also, every flashcard has a difficulty attribute that shows 
how hard it is for you to remember this particular flashcard (The higher the
difficulty number, the harder). Every flashcard is identified by a unique id 
(Flashcard ids don't necessarily start at zero and are not necessarily
consecutive. Don't worry, this is totally fine and the planned behavior!).

#### **"Dual Commands": Different input options for beginner vs expert users**

We know that not all users want the same. Same want more simplicity, whilst 
others want more sophisticated tools that speed up their working speed.
To cater to both groups of users, TaskLinker's flashcards features offer
so-called "dual commands" that can be invoked in two different ways: an easy,
but more time-consuming way for beginner users who want as much guidance as
possible; or a less time-consuming, but more complicated way for expert users
who don't need additional guidance.

Currently, the following commands are dual commands:

- [`review flashcards`](#reviewing-your-flashcards-review-flashcards)
- [`delete flashcard`](#deleting-a-flashcard-delete-flashcard)

For more information, see the respective sections explaining their usage.

#### **Listing all flashcards: `list flashcards`**

Prints out a list of all flashcards that shows each flashcard's front text 
and back text as well as its id and current difficulty level.

Command Format: `list flashcards`

Example of usage: 
<pre>
Enter your command: <b>list flashcards</b>
    Here is a list of all your flashcards: 

--------------------------------------------------------------------------------
front text: How long is a meter in cm?
back text: 100
id: 20
difficulty: 4
--------------------------------------------------------------------------------
front text: What does UML stand for?
back text: Unified Modelling Language
id: 21
difficulty: 6
--------------------------------------------------------------------------------

    Those were all your flashcards.
</pre>

#### **Creating a new flashcard: `create flashcard`**

Starts the process of creating a new flashcard.

After entering this command, you are prompted to first input the front page
text (once you have typed it out, submit it by pressing ENTER) and then the
back page text (once you have typed it out, submit it by pressing ENTER) of
your new flashcard.

After you've done this, a success message will be printed out. This
indicates that your new flashcard has been successfully created and saved.

Command Format: `create flashcard`

Example of usage:
<pre>
Enter your command: <b>create flashcard</b>
Enter the front page text: <b>What is a banana?</b>
Enter the back page text: <b>A kind of fruit</b>

Success! Flashcard has been added to your collection.
</pre>

#### **Reviewing your flashcards: `review flashcards`**

Starts the process of reviewing your flashcards.

The `review flashcards` command is a so-called "Dual Command" that can be 
invoked in two different ways: an easy, but more time-consuming way for 
beginner users who want as much guidance as possible; or a less 
time-consuming, but more complicated way for expert users who don't need 
additional guidance.

There are two review modes to choose from: 

- **random mode:** The flashcards to be reviewed are randomly chosen.
- **spaced repetition mode:** Which flashcards are chosen to be reviewed 
  depends on 
  how well you previously knew their contents. Flashcards which you couldn't 
  remember well are shown more often, while flashcards which you remembered well
  are shown less often.

How you chose your review mode differs between the beginner mode and expert 
mode:

- **Choosing review mode in beginner mode:** After you've inputted the 
  `review flashcards` command, in a separate step, you are prompted to select 
  your desired review mode by inputting its respective letter: Input `a` to 
  choose random mode, or input `b` to choose spaced repetition mode.
- **Choosing review mode in expert mode:** You have to already choose the 
  review mode when you input the `review flashcards` command. Thus, you have 
  to use the `review flashcards REVIEW_MODE` format, where REVIEW_MODE has 
  to be either an `a` (for random mode) or a `b` (for spaced repetition mode)
  . Unlike in the beginner mode, there is no separate step for you to 
  choose a review mode from a list of available options as you have already 
  supplied which review mode you want to use via the REVIEW_MODE parameter 
  of the `review flashcards REVIEW_MODE` command format; thus saving you time.

Once you've selected a review mode, the actual review begins.

Firstly, the front page of a flashcard is shown to you. You should now try and
think of the answer (the text on the back page of the flashcard); and once 
you're ready, press ENTER to be shown the back page of the flashcard so you can
compare what you expected to be the answer to the actual answer.

In spaced repetition mode, after you have revealed the back page of a 
flashcard, you are prompted to rate how to difficult it was to remember. 
Select `E` if it was easy, `M` if it was moderately hard and `H` if it was 
quite hard. This information is used to adjust the difficulty of the 
flashcard you just reviewed.

(In random mode, no such process of rating the flashcard's difficulty takes 
place.)

Now, the process repeats and the front page of the next flashcard is shown to 
you.

If you ever want to quit the review process, simply input `q` or `quit` instead
of pressing ENTER to reveal the back page.

##### **Command format and example of usage in beginner mode**

Command Format: `review flashcards`

Example of usage:
<pre>
Enter your command: <b>review flashcards</b>
    How do you want to review your flashcards?
        a) random mode
        b) spaced repetition mode
<b>a</b>
    You have started a review session in random review mode

    ----------------------------------------------------------------------------
    The front text is: What is the boiling point of water?

    Think of the answer (the back text) in your head.
    Press ENTER when you are ready to compare it,
    or enter q or quit to end this review session.

    The actual back text is: 100 degrees Celsius

    ----------------------------------------------------------------------------
    The front text is: What is the boiling point of water?

    Think of the answer (the back text) in your head.
    Press ENTER when you are ready to compare it,
    or enter q or quit to end this review session.
<b>quit</b>
    Success! You have ended this review session.
</pre>

##### **Command format and example of usage in expert mode**

Command Format: `review flashcards REVIEW_MODE` (where REVIEW_MODE has to be 
either an `a` or a `b`)

Example of usage:
<pre>
Enter your command: <b>review flashcards b</b>
    You have started a review session in spaced repetition mode

    ----------------------------------------------------------------------------
    The front text is: What is the boiling point of water?

    Think of the answer (the back text) in your head.
    Press ENTER when you are ready to compare it,
    or enter q or quit to end this review session.

    The actual back text is: 100 degrees Celsius

    How hard was it to remember the back page of this flashcard?
    Input E if it was easy, M if it was moderately challenging 
    or H if it was quite hard.
<b>H</b>
    ----------------------------------------------------------------------------
    The front text is: What is the boiling point of water?

    Think of the answer (the back text) in your head.
    Press ENTER when you are ready to compare it,
    or enter q or quit to end this review session.
<b>q</b>
    Success! You have ended this review session.
</pre>

#### **Deleting a flashcard: `delete flashcard`**

Starts the process of deleting a flashcard.

The `delete flashcard` command is a so-called "Dual Command" that can be
invoked in two different ways: an easy, but more time-consuming way for
beginner users who want as much guidance as possible; or a less
time-consuming, but more complicated way for expert users who don't need
additional guidance.

How you chose which flashcard to delete differs between the beginner mode and
expert mode:

- **Choosing review mode in beginner mode:** After you've inputted the
  `delete flashcard` command, in a separate step, you are prompted to select
  the flashcard you want to delete by inputting its id.
- **Choosing review mode in expert mode:** You have to already choose the
  flashcard you want to delete when you input the `delete flashcard` command.
  Thus, you have to use the `delete flashcards FLASHCARD_ID` format, where
  FLASHCARD_ID is the id of the flashcard you want to delete. Unlike in the
  beginner mode, there is no separate step for you to choose which flashcard you
  want to delete as you have already informed TaskLinker about which flashcard
  you want to delete via the FLASHCARD_ID parameter of the
  `delete flashcards FLASHCARD_ID` command format; thus saving you time.


If the id you provided was a valid id, the flashcard with that id is deleted; 
otherwise, an error message is shown, and you are prompted to retry.

##### **Command format and example of usage in beginner mode**

Command format: `delete flashcard` 

Example of usage:
<pre>
Enter your command: <b>delete flashcard</b>
    Enter id of the flashcard you want to delete: <b>2</b>
    Flashcard with id 2 has been successfully deleted.
</pre>

##### **Command format and example of usage in expert mode**

Command format: `delete flashcard FLASHCARD_ID` (where FLASHCARD_ID is the 
id of the flashcard you want to delete)

Example of usage:
<pre>
Enter your command: <b>delete flashcard 34</b>
    Couldn't find a flashcard with id 34
    No deletion has been performed. Please try again with a valid id.
</pre>

#### **Deleting all flashcards: `delete all flashcards`**

Deletes all flashcards currently stored by TaskLinker.

Format: `delete all flashcards`

Example of usage:
<pre>
Enter your command: <b>delete all flashcards</b>
    All your flashcards have been successfully deleted.
</pre>

### **Calendar-related features**

#### **General explanation of flashcards**

TaskLinker's calendar feature allows users to manage their time effectively 
by adding, listing, finding, and deleting events. Events in the calendar have a 
specified start and end time, making it easy for users to plan their schedules. 
This feature is particularly useful for individuals with busy schedules or 
those who want to keep track of their upcoming appointments or tasks.

The TaskLinker is also integrated with the flashcards and can be used for setting
a goal to review flashcards. Add goal event can be used to take user input for 
setting a goal to review flashcards.

Here is your features list:

+ `add event` , `delete event`
+ `list events` , `find event`
+ `delete all events` , `add goal event`

Users can use the above features to handle their events`

#### **Adding an event to the calendar: `add event`**

Adds an event to the calendar with start and end time. 

After entering this command, the user is asked to enter the event name on the calendar.
Once the name is given, the user should press ENTER to continue. Once the name is entered,
the user is prompted to give a start time and an end time for the event. 

The start and the end time should be in an acceptable format (`yyyy-mm-ddThh:mm:ss`.)
If it is not in an acceptable format, (`Invalid date and time format. Please try again.`)
message will appear and prompt the user to enter a new start time.

Furthermore, the end time should be later than the start time as an event cannot
end before it's start time. If an earlier time is given for the end time than the
start time, the TaskLinker displays (`End time is before or equal to the start time. Please enter the correct end time.`)
message and starts over from (`Enter your command:`).

Format:
<pre>
Enter your command: <b>add event</b>
What's the event?: <b>Name [Event Name]</b>
When does it start?: <b>yyyy-mm-ddThh:mm:ss [Start Time]</b>
When does it end?: <b>yyyy-mm-ddThh:mm:ss [End Time]</b>
</pre>

Example of usage:
<pre>
Enter your command: <b>add event</b>
What's the event?: <b>Do HW</b>
When does it start?: <b>2023-12-20T12:30:30</b>
When does it end?: <b>2023-12-20T13:40:30</b>

Event 'Do HW' From: 2023-12-20T12:30:30, To: 2023-12-20T13:40:30 
has been added to your Calendar
</pre>

#### **Adding a goal event to the calendar: `add goal event`**

Adds a goal of reviewing flashcards as an event to the calendar.

After entering (`add goal event`) command, the user is asked to provide a name
for this goal event. After the name, the user is prompted to give an end time 
for the event. The end time serves as a deadline for the goal.

After the deadline has been given, then the goal should be given. Goal of reviewing
some number of flashcards by the given deadline. Same as adding an event, if an unacceptable
format of end time is given, the TaskLinker will display (`Invalid date and time format. Please try again.`)
message. 

Format:
<pre>
Enter your command: <b>add goal event</b>
What's the event?: <b>Name [Goal Name]</b>
When does it end?: <b>yyyy-mm-ddThh:mm:ss [End Time]</b>
How many flashcard to review by then?: <b>Number [# of flashcards to review]</b>
</pre>

Example of usage:
<pre>
Enter your command: <b>add goal event</b>
What's the event?: <b>Do Flashcards</b>
When does it end?: <b>2023-12-20T12:30:30</b>
How many flashcard to review by then?: <b>20</b>

Goal 'Do Flashcards' review 20 flashcards by: 2023-12-20T12:30:30 (Reviewed: 0) 
has been added to your Calendar
</pre>

#### **Delete an event from the calendar: `delete event`**

Deletes an event from the calendar with event name.

After the (`delete event`) command has been given, the user is prompted to give
the name of the event he/she wants to delete from the calendar. If the given
event exist in the calendar, then TaskLinker will display (`[Event Name] has been deleted from your Calendar!`).
Then the event gets deleted from the event list.

However, if the given event name doesn't exist in the calendar. Then the event
doesn't exist in the calendar message will display (`[Event Name] doesn't exist in your Calendar!`).
The user then enter a different command.

Format:
<pre>
Enter your command: <b>delete event</b>
What's the event?: <b>Name [Event Name]</b>
    [Event Name] has been deleted from your Calendar!
</pre>

Example of usage:
<pre>
Enter your command: <b>delete event</b>
What's the event?: <b>hello</b>
    hello has been deleted from your Calendar!
</pre>

#### **Delete all events from the Calendar: `delete all events`**

Deletes all events from the calendar.

Once the (`delete all events`) command has been given by the user,
the TaskLinker will clear all events in the calendar. If the exact 
command is not entered, the feature will not work.

Format & Example:
<pre>
Enter your command: <b>delete all events</b>
    All your events have been successfully deleted from the Calendar.
</pre>

#### **Find an event from the Calendar: `find event`**

Finds an event from the calendar.

Once the (`find event`) command has been entered, the user is prompted to
give the name of the event he/she is looking for. The input user gives can
just be partial name of the event. If so, the feature will list all
events that included the given input.

However, if the given input is not found from the events in the Calendar, 
then the (`No such event found`) message will be displayed.

Format:
<pre>
Enter your command: <b>find event</b>
What's the event?: <b>Name [Event Name]</b>
1. Event 'Event Name' From: yyyy-mm-ddThh:mm:ss, To: yyyy-mm-ddThh:mm:ss
    These events have been found
</pre>

Example of usage:
<pre>
Enter your command: <b>find event</b>
What's the event?: <b>Do HW</b>
1. Event 'Do HW' From: 2023-12-20T12:30:30, To: 2023-12-20T13:30:30
    These events have been found
</pre>

#### **List all events from the Calendar: `list events`**

Lists all events from the calendar

Once the (`list events`) command has been entered, the TaskLinker lists
all the events in the event list. If there is no event in the event list,
the (`The Calendar is empty!`) message will be displayed.

Format & Example:
<pre>
Enter your command: <b>list events</b>
    Here is a list of all your events: 
--------------------------------------------------------------------------------
1. Event 'Do User' From: 2023-12-20T12:30:30, To: 2023-12-20T13:30:30
--------------------------------------------------------------------------------
</pre>

## **FAQ**

**Q**: Where can I find my flashcard and calendar data?

**A**: You can find it in `data/flashcards` and `data/events` folder.

Every event and flashcard are automatically save after each command.

**Q**: How do I transfer my data to another computer? 

**A**: You can transfer your data by copying & pasting the data folder.

**Q**: Why is the calendar features needed?

**A**: The calendar features are used for adding a flashcard review and other goals.

**Q**: Are the flashcard and the calendar use different commands?

**A**: Yes, they have different command based on their features.

**Q**: How to exit the program?

**A**: Enter "exit" for the command.

## **Command Summary**

* [Listing all flashcards](#listing-all-flashcards-list-flashcards): `list 
  flashcards`
* [Creating a new flashcard](#creating-a-new-flashcard-create-flashcard): 
  `create flashcard`
* [Reviewing your flashcards](#reviewing-your-flashcards-review-flashcards): 
  `review flashcards`
* [Deleting a flashcard](#deleting-a-flashcard-delete-flashcard) `delete 
  flashcard`
* [Deleting all flashcards](#deleting-all-flashcards-delete-all-flashcards) 
  `delete all flashcards`
* [Create an event](#adding-an-event-to-the-calendar-add-event): 
  `add event`
* [Add a goal event](#adding-a-goal-event-to-the-calendar-add-goal-event): 
  `add goal event`
* [Delete an event](#delete-an-event-from-the-calendar-delete-event): 
  `delete event`
* [Delete all events](#delete-all-events-from-the-calendar-delete-all-events): 
  `delete all events`
* [Find an event](#find-an-event-from-the-calendar-find-event): 
  `find event`
* [List all events](#list-all-events-from-the-calendar-list-events): 
  `list events` 
