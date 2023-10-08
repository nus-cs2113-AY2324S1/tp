package quizhub.questionlist;

import quizhub.question.Question;
import quizhub.question.ShortAnsQn;
import quizhub.exception.QuizHubExceptions;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
/**
 * Represents the list of questions currently registered in the chatbot.
 * This list is created on program start and disposed on program termination.
 */
public class QuestionList {
    private ArrayList<Question> allQns; //array of inputs
    DateTimeFormatter inputDateTimeFormatter = DateTimeFormatter.ofPattern( "dd-MM-yyyy HH:mm" );
    /**
     * Adds a user-requested question to the current question list.
     * Depending on the type of question to add to the list,
     * the program extracts the relevant information from
     * the user input and builds a Question object to be added.
     *
     * @param input The full user input from CLI.
     * @param qnType The type of question to be added (TODO, DEADLINE, EVENT).
     * @param showMessage If true, program will print response message on CLI after question is added.
     */
    public void addToQuestionList(String input, Question.qnType qnType, boolean showMessage){
        switch (qnType) {
            case SHORTANSWER:
                try {
                    String toDoDescription = input.split("todo")[1].strip();
                    if (toDoDescription.equals("")) {
                        throw new QuizHubExceptions("Incomplete Command");
                    }
                    allQns.add(new ShortAnsQn(toDoDescription));
                    if (showMessage) {
                        System.out.println("    I have added the following question OwO:");
                        System.out.printf("      [T][] %s\n", viewQuestionByIndex(getQuestionListSize()));
                        System.out.println("    Now you have " + getQuestionListSize() + " questions in the list! UWU");
                    }
                    break;
                } catch (ArrayIndexOutOfBoundsException | QuizHubExceptions incompleteCommand) {
                    System.out.println("    Ohnus! You did not use give todo a name!");
                    System.out.println("    Pwease format your input as todo [question name]!");
                    return;
                }
        }
    }
    /**
     * Prints the details of a question in CLI.
     *
     * @param question The question in which details are to be printed.
     * @param asList If true, prints out the index of the question in the question list
     *               in addition to the details of the question.
     */
    public void printQuestion(Question question, boolean asList){
        int qnIndex = allQns.indexOf(question);
        switch(question.getQuestionType()) {
            case SHORTANSWER:
                if (question.questionIsDone()) {
                    if(asList){
                        System.out.printf("    %d: [T][X] %s\n", qnIndex+1, question.getQuestionDescription());
                    } else{
                        System.out.printf("        [T][X] %s\n", question.getQuestionDescription());
                    }

                } else {
                    if(asList){
                        System.out.printf("    %d: [T][] %s\n", qnIndex+1, question.getQuestionDescription());
                    } else{
                        System.out.printf("        [T][] %s\n", question.getQuestionDescription());
                    }
                }
                break;
        }
    }
    /**
     * Prints all the questions in the current question list as an indexed list.
     */
    public void printQuestionList(){
        if(allQns.isEmpty()){
            System.out.println("    No questions found! Time to add some OWO");
        }
        else {
            for (Question question : allQns) {
                printQuestion(question, true);
            }
        }
    }
    /**
     * Mark a question in the current question list as done.
     *
     * @param index The list index of the question to be marked as done.
     * @param showMessage If true, program will print response message on CLI
     *                    after question is marked as done.
     */
    public void markQuestionAsDone(int index, boolean showMessage){
        try{
            allQns.get(index-1).markAsDone();
            if(showMessage) {
                Question question = allQns.get(index - 1);
                System.out.println("    Roger that! I have marked the following question as done >w< !");
                printQuestion(question, false);
            }
        } catch (IndexOutOfBoundsException invalidIndex){
            System.out.println("    Ohnuuu! Please enter valid question number *sobs*");
        }
    }
    /**
     * Mark a question in the current question list as not done.
     */
    public void markQuestionAsNotDone(int index){
        try{
            allQns.get(index-1).markAsNotDone();
            Question question = allQns.get(index-1);
            System.out.println("    Roger that! I have unmarked the following question as done >w< !");
            printQuestion(question, false);
        } catch (IndexOutOfBoundsException invalidIndex){
            System.out.println("    Ohnuuu! Please enter valid question number *sobs*");
        }
    }
    /**
     * Delete a question from the current question list.
     *
     * @param index The list index of the question to be deleted.
     */
    public void deleteQuestionByIndex(int index){
        try{
            Question question = allQns.get(index-1);
            allQns.remove(index - 1);
            System.out.println("    Roger that! I have deleted the following question >w< !");
            printQuestion(question, false);
            System.out.println("    Now you have " + getQuestionListSize() + " questions in the list! UWU");
        } catch (IndexOutOfBoundsException invalidIndex){
            System.out.println("    Ohnuuu! Please enter valid question number *sobs*");
        }
    }
    /**
     * Returns the description and all other details of a question in one String object.
     * Used to display question details in CLI.
     *
     * @param index The list index of the question to be viewed.
     */
    public String viewQuestionByIndex(int index){
        try{
            switch(allQns.get(index-1).getQuestionType()) {
                case SHORTANSWER:
                    return allQns.get(index-1).getQuestionDescription();
                default:
                    return "Question Not Found";
            }
        } catch(NullPointerException | IndexOutOfBoundsException invalidIndex){
            System.out.println("    Ohnuuu! Please enter valid question number *sobs*");
            return "Question Not Found";
        }
    }
    /**
     * Search for questions in the current question list using their description.
     *
     * @param keyword Description keyword(s) used to search for matches.
     */
    public void searchListByDescription(String keyword){
        ArrayList<Question> matchedQuestions = new ArrayList<>();
        if(allQns.isEmpty()){
            System.out.println("    Question list is empty! Time to add some OWO");
        }
        else {
            System.out.println("    Here are questions that matched your search:");
            for (Question question : allQns) {
                if(question.getQuestionDescription().contains(keyword)){
                    matchedQuestions.add(question);
                    printQuestion(question, true);
                }
            }
            if(matchedQuestions.isEmpty()){
                System.out.println("    No results found :< Check your keyword is correct?");
            }
        }
    }
    /**
     * Search for questions in the current question list using their date and time.
     *
     * @param dateTime Date and time used to search for matches.
     */
    public void searchListByTime(String dateTime){
        ArrayList<Question> matchedQuestions = new ArrayList<>();
        if(allQns.isEmpty()){
            System.out.println("    Question list is empty! Time to add some OWO");
        }
        else {
            System.out.println("    Here are questions that matched your search:");
            for (Question question : allQns) {
                if(question.getQuestionTiming(true).contains(dateTime)){
                    matchedQuestions.add(question);
                    printQuestion(question, true);
                }
            }
            if(matchedQuestions.isEmpty()){
                System.out.println("    No results found :< Check your time format is in dd-MM-yyyy HH:mm?");
            }
        }
    }
    /**
     * Search for a question in the current question list.
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
     * Returns the size of current question list.
     */
    public int getQuestionListSize(){
        return allQns.size();
    }
    /**
     * Returns a list of all questions in the current question list.
     */
    public ArrayList<Question> getAllQns(){
        return allQns;
    }
    /**
     * Creates a new empty question list.
     */
    public QuestionList(){
        allQns = new ArrayList<Question>();
    }
}