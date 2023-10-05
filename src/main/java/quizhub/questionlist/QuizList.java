package quizhub.questionlist;

import quizhub.question.Question;
import quizhub.question.ShortAnsQn;
import quizhub.exception.QuizHubExceptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
/**
 * Represents the list of tasks currently registered in the chatbot.
 * This list is created on program start and disposed on program termination.
 */
public class QuizList {
    private ArrayList<Question> allTasks; //array of inputs
    DateTimeFormatter inputDateTimeFormatter = DateTimeFormatter.ofPattern( "dd-MM-yyyy HH:mm" );
    /**
     * Adds a user-requested task to the current task list.
     * Depending on the type of task to add to the list,
     * the program extracts the relevant information from
     * the user input and builds a Task object to be added.
     *
     * @param input The full user input from CLI.
     * @param taskType The type of task to be added (TODO, DEADLINE, EVENT).
     * @param showMessage If true, program will print response message on CLI after task is added.
     */
    public void addToTaskList(String input, Question.TaskType taskType, boolean showMessage){
        switch (taskType) {
            case SHORTANSWER:
                try {
                    String toDoDescription = input.split("todo")[1].strip();
                    if (toDoDescription.equals("")) {
                        throw new QuizHubExceptions("Incomplete Command");
                    }
                    allTasks.add(new ShortAnsQn(toDoDescription));
                    if (showMessage) {
                        System.out.println("    I have added the following task OwO:");
                        System.out.printf("      [T][] %s\n", viewTaskByIndex(getTaskListSize()));
                        System.out.println("    Now you have " + getTaskListSize() + " tasks in the list! UWU");
                    }
                    break;
                } catch (ArrayIndexOutOfBoundsException | QuizHubExceptions incompleteCommand) {
                    System.out.println("    Ohnus! You did not use give todo a name!");
                    System.out.println("    Pwease format your input as todo [task name]!");
                    return;
                }
        }
    }
    /**
     * Prints the details of a task in CLI.
     *
     * @param task The task which details are to be printed.
     * @param asList If true, prints out the index of the task in the task list
     *               in addition to the details of the task.
     */
    public void printTask(Question task, boolean asList){
        int taskIndex = allTasks.indexOf(task);
        switch(task.getTaskType()) {
            case SHORTANSWER:
                if (task.taskIsDone()) {
                    if(asList){
                        System.out.printf("    %d: [T][X] %s\n", taskIndex+1, task.getTaskDescription());
                    } else{
                        System.out.printf("        [T][X] %s\n", task.getTaskDescription());
                    }

                } else {
                    if(asList){
                        System.out.printf("    %d: [T][] %s\n", taskIndex+1, task.getTaskDescription());
                    } else{
                        System.out.printf("        [T][] %s\n", task.getTaskDescription());
                    }
                }
                break;
        }
    }
    /**
     * Prints all the tasks in the current task list as an indexed list.
     */
    public void printTaskList(){
        if(allTasks.isEmpty()){
            System.out.println("    No tasks found! Time to add some OWO");
        }
        else {
            for (Question task : allTasks) {
                printTask(task, true);
            }
        }
    }
    /**
     * Mark a task in the current task list as done.
     *
     * @param index The list index of the task to be marked as done.
     * @param showMessage If true, program will print response message on CLI
     *                    after task is marked as done.
     */
    public void markTaskAsDone(int index, boolean showMessage){
        try{
            allTasks.get(index-1).markAsDone();
            if(showMessage) {
                Question task = allTasks.get(index - 1);
                System.out.println("    Roger that! I have marked the following task as done >w< !");
                printTask(task, false);
            }
        } catch (IndexOutOfBoundsException invalidIndex){
            System.out.println("    Ohnuuu! Please enter valid task number *sobs*");
        }
    }
    /**
     * Mark a task in the current task list as not done.
     */
    public void markTaskAsNotDone(int index){
        try{
            allTasks.get(index-1).markAsNotDone();
            Question task = allTasks.get(index-1);
            System.out.println("    Roger that! I have unmarked the following task as done >w< !");
            printTask(task, false);
        } catch (IndexOutOfBoundsException invalidIndex){
            System.out.println("    Ohnuuu! Please enter valid task number *sobs*");
        }
    }
    /**
     * Delete a task from the current task list.
     *
     * @param index The list index of the task to be deleted.
     */
    public void deleteTaskByIndex(int index){
        try{
            Question task = allTasks.get(index-1);
            allTasks.remove(index - 1);
            System.out.println("    Roger that! I have deleted the following task >w< !");
            printTask(task, false);
            System.out.println("    Now you have " + getTaskListSize() + " tasks in the list! UWU");
        } catch (IndexOutOfBoundsException invalidIndex){
            System.out.println("    Ohnuuu! Please enter valid task number *sobs*");
        }
    }
    /**
     * Returns the description and all other details of a task in one String object.
     * Used to display task details in CLI.
     *
     * @param index The list index of the task to be viewed.
     */
    public String viewTaskByIndex(int index){
        try{
            switch(allTasks.get(index-1).getTaskType()) {
                case SHORTANSWER:
                    return allTasks.get(index-1).getTaskDescription();
                default:
                    return "Task Not Found";
            }
        } catch(NullPointerException | IndexOutOfBoundsException invalidIndex){
            System.out.println("    Ohnuuu! Please enter valid task number *sobs*");
            return "Task Not Found";
        }
    }
    /**
     * Search for tasks in the current task list using their description.
     *
     * @param keyword Description keyword(s) used to search for matches.
     */
    public void searchListByDescription(String keyword){
        ArrayList<Question> matchedTasks = new ArrayList<>();
        if(allTasks.isEmpty()){
            System.out.println("    Task list is empty! Time to add some OWO");
        }
        else {
            System.out.println("    Here are tasks that matched your search:");
            for (Question task : allTasks) {
                if(task.getTaskDescription().contains(keyword)){
                    matchedTasks.add(task);
                    printTask(task, true);
                }
            }
            if(matchedTasks.isEmpty()){
                System.out.println("    No results found :< Check your keyword is correct?");
            }
        }
    }
    /**
     * Search for tasks in the current task list using their date and time.
     *
     * @param dateTime Date and time used to search for matches.
     */
    public void searchListByTime(String dateTime){
        ArrayList<Question> matchedTasks = new ArrayList<>();
        if(allTasks.isEmpty()){
            System.out.println("    Task list is empty! Time to add some OWO");
        }
        else {
            System.out.println("    Here are tasks that matched your search:");
            for (Question task : allTasks) {
                if(task.getTaskTiming(true).contains(dateTime)){
                    matchedTasks.add(task);
                    printTask(task, true);
                }
            }
            if(matchedTasks.isEmpty()){
                System.out.println("    No results found :< Check your time format is in dd-MM-yyyy HH:mm?");
            }
        }
    }
    /**
     * Search for a task in the current task list.
     * Depending on user command, this method will search by
     * either description matches or time matches.
     *
     * @param input Full user command input.
     */
    public void searchList(String input){
        String[] searchDetails;
        String[] searchInfo;
        try {
            searchDetails = input.split("find")[1].strip().split("/");
            searchInfo = searchDetails[1].strip().split(" ");
        } catch (ArrayIndexOutOfBoundsException incompleteCommand) {
            System.out.println("    Ohnus! You did not indicate if you are searching by description or time :<");
            System.out.println("    Pwease format your input as find /description [description] " +
                                    "or find /time [time]!");
            return;
        }
        try{
            String searchCriteria = searchInfo[0].strip();
            String searchKeyword = searchInfo[1].strip();
            switch (searchCriteria){
                case "description":
                    searchListByDescription(searchKeyword);
                    break;
                case "time":
                    searchListByTime(searchKeyword);
                    break;
                default:
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException incompleteCommand) {
            System.out.println("    Ohnus! You did not indicate the keywords you are searching by :<");
            System.out.println("    Pwease format your input as find /description [description] " +
                                    "or find /time [time]!");
        }

    }
    /**
     * Returns the size of current task list.
     */
    public int getTaskListSize(){
        return allTasks.size();
    }
    /**
     * Returns a list of all tasks in the current task list.
     */
    public ArrayList<Question> getAllTasks(){
        return allTasks;
    }
    /**
     * Creates a new empty task list.
     */
    public QuizList(){
        allTasks = new ArrayList<Question>();
    }
}