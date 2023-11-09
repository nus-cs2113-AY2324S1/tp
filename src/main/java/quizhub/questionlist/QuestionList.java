package quizhub.questionlist;

import quizhub.command.CommandShortAnswer;
import quizhub.command.CommandStart;
import quizhub.question.MultipleChoiceQn;
import quizhub.question.Question;
import quizhub.question.ShortAnsQn;
import quizhub.exception.QuizHubExceptions;
import quizhub.ui.Ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;

import static quizhub.question.Question.QnType.MULTIPLECHOICE;  
import static quizhub.question.Question.QnType.SHORTANSWER;

/**
 * Represents the list of questions currently registered in Quizhub.
 * This list is created on program start and disposed on program termination.
 */
public class QuestionList {
    public static final String NO_QN_MSG = "    No questions found! Time to add some OWO";
    public static final String INVALID_QN_MSG = "    Ono! Please enter valid question number *sobs*";
    public static final String QN_MARKED_MSG = "    Roger that! I have marked the following question as done >w< !";
    public static final String QN_NO_CHANGE_MSG = "    Question originally done! No changes made!";
    public static final String EMPTY_LIST_MSG = "    Question list is empty! Time to add some OWO";
    public static final String DELETE_QN_MSG = "    Roger that! I have deleted the following question >w< !";
    public static final String SEARCH_RESULT_MSG = "    Here are questions that matched your search:";
    public static final String NO_SEARCH_RESULTS_MSG = "    No results found :< Check your keyword is correct?";
    public static final String SHUFFLE_QN_MSG = "    Questions are now shuffled!";
    private ArrayList<Question> allQns; //array of inputs
    /**
     * Creates a new empty question list.
     */
    public QuestionList(){
        allQns = new ArrayList<>();
    }

    /**
     * Prints default message on successful message addition.
     */
    private void printQnAddedMsg(){
        Ui.displayMessageStatically("    I have added the following question OwO:");
        Ui.displayMessageStatically("    " + viewQuestionByIndex(getQuestionListSize()));
        Ui.displayMessageStatically("    Now you have " + getQuestionListSize() + " questions in the list! UWU");
    }

    /**
     * Adds a short answer question to the current question list.
     *
     * @param description The Question Description
     * @param answer The answer of the Question
     * @param module The module of the Question
     * @param qnDifficulty The difficulty level of the questions
     * @param showMessage If true, program will print response message on CLI after question is added.
     */
    public boolean addShortAnswerQn(String description, String answer, String module,
                                 Question.QnDifficulty qnDifficulty, boolean showMessage){

        boolean isDuplicate = containsDuplicateQuestion(description, SHORTANSWER, module, showMessage);
        if (isDuplicate){
            return false;
        }
        allQns.add(new ShortAnsQn(description, answer, module, qnDifficulty));
        if (showMessage) {
            printQnAddedMsg();
        }
        return true;
    }

    /**
     * Adds a multiple choice question to the current question list.
     *
     * @param description The Question Description
     * @param option1 First option
     * @param option2 Second option
     * @param option3 Third option
     * @param option4 Fourth option
     * @param answer The answer to the question (1, 2, 3 or 4)
     * @param module The module of the Question
     * @param qnDifficulty The difficulty level of the questions
     * @param showMessage If true, program will print response message on CLI after question is added.
     */
    public boolean addMultipleChoiceQn(String description, String option1, String option2,
                                    String option3, String option4, int answer, String module,
                                    Question.QnDifficulty qnDifficulty, boolean showMessage) {


        boolean isDuplicate = containsDuplicateQuestion(description, MULTIPLECHOICE, module, showMessage);
        if (isDuplicate){
            return false;
        }
        allQns.add(new MultipleChoiceQn(description, option1, option2, option3,
                option4, answer, module, qnDifficulty));
        if (showMessage) {
            printQnAddedMsg();
        }
        return true;
    }

    /**
     * Checks if there is a duplicate question.
     * "Duplicate" (similar description) questions are allowed to exist
     * in different question types (i.e. in MCQ, then as short ans),
     * in different modules (i.e. different contexts),
     * in different difficulties (i.e. different levels),
     *
     * @param description The description of the short answer question.
     * @param qnType The type of question (SHORTANS or MULTIPLECHOICE)
     * @param module The module of the short answer question.
     * @return true if all of the above are true
     */
    public boolean containsDuplicateQuestion (String description, Question.QnType qnType, String module,
                                              boolean showMessage) {
        for (Question question : allQns) {
            if (description.strip().equalsIgnoreCase(question.getQuestionBody()) &&
                qnType.equals(question.getQuestionType()) &&
                module.equalsIgnoreCase(question.getModule())) {
                if (showMessage) {
                    Ui.displayMessageStatically(CommandShortAnswer.DUPLICATED_INPUT);
                }
                return true;
            }
        }
        return false;
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
        int oneIndexed = ++qnIndex;
        String indexString = "    ";
        if(asList) {
            indexString += oneIndexed + ": ";
        } else {
            indexString += "    ";
        }
        Ui.displayMessageStatically(indexString + question);
    }
    
    /**
     * Prints all the questions in the current question list as an indexed list.
     */
    public void printQuestionList(){
        if(allQns.isEmpty()){
            Ui.displayMessageStatically(NO_QN_MSG);
            return;
        }
        for (Question question : allQns) {
            printQuestion(question, true);
        }
    }
    /**
     * Mark a question in the current question list as done.
     *
     * @param index The list index of the question to be marked as done.
     * @param showMessage If true, program will print response message on CLI
     *                    after question is marked as done.
     */
    public void markQuestionAsDone (int index, boolean showMessage){
        Question question;
        try{
            int oneIndexed = index-1;
            question = allQns.get(oneIndexed);
        } catch (IndexOutOfBoundsException invalidIndex){
            Ui.displayMessageStatically(INVALID_QN_MSG);
            return;
        }
        assert(question != null);
        if(!question.questionIsDone()) {
            question.markAsDone();
            if (showMessage) {
                Ui.displayMessageStatically(QN_MARKED_MSG);
                printQuestion(question, false);
            }
        } else {
            Ui.displayMessageStatically(QN_NO_CHANGE_MSG);
        }
    }
    /**
     * Mark the difficulty of a question in the current question list.
     *
     * @param index The list index of the question to be marked.
     * @param qnDifficulty Difficulty to be assigned to the question.
     * @param showMessage If true, program will print response message on CLI
     *                    after question difficulty is marked.
     */
    public void markQuestionDifficulty(int index, Question.QnDifficulty qnDifficulty,  boolean showMessage){
        String difficulty = null;
        switch (qnDifficulty){
        case EASY:
            difficulty = "easy";
            break;
        case HARD:
            difficulty = "hard";
            break;
        case NORMAL:
            difficulty = "normal";
            break;
        default:
            break;
        }
        try{
            Question question = allQns.get(index-1);
            if(question.getDifficulty() == qnDifficulty){
                Ui.displayMessageStatically("    Question is already set as " +
                        difficulty + " ! No changes made!");
                return;
            }
            allQns.get(index-1).markDifficulty(qnDifficulty);
            if(!showMessage) {
                return;
            }
            Ui.displayMessageStatically("    Roger that! I have marked the following question as " +
                    difficulty + " >w< !");
            printQuestion(question, false);
        } catch (IndexOutOfBoundsException invalidIndex){
            Ui.displayMessageStatically(INVALID_QN_MSG);
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
            Ui.displayMessageStatically(DELETE_QN_MSG);
            printQuestion(question, false);
            Ui.displayMessageStatically("    Now you have " + getQuestionListSize() + " questions in the list! UWU");
        } catch (IndexOutOfBoundsException invalidIndex){
            Ui.displayMessageStatically(INVALID_QN_MSG);
        }
    }
    /**
     * Returns the description and all other details of a question in one String object.
     * Used to display question details in CLI.
     *
     * @param index The list index of the question to be viewed.
     * @return String value of the question
     */
    public String viewQuestionByIndex(int index){
        try{
            return allQns.get(index-1).toString();
        } catch(InputMismatchException |NullPointerException | IndexOutOfBoundsException invalidIndex){
            return "Question Not Found";
        }
    }

    /**
     * Delete a question from the current question list.
     *
     * @param index The list index of the question to be deleted.
     */
    public void editQuestionByIndex(int index, String editField, String newValue){
        Question question = allQns.get(index - 1);
        question.editQuestion(editField, newValue);
        printQuestion(question, false);
    }
    /**
     * Search for questions in the current question list using their description.
     *
     * @param keyword Description keyword(s) used to search for matches.
     */
    public void searchListByDescription(String keyword){
        ArrayList<Question> matchedQuestions = new ArrayList<>();
        if(allQns.isEmpty()) {
            Ui.displayMessageStatically(EMPTY_LIST_MSG);
            return;
        }
        Ui.displayMessageStatically(SEARCH_RESULT_MSG);
        for (Question question : allQns) {
            if (!question.getQuestionBody().toLowerCase().contains(keyword.toLowerCase())) {
                continue;
            }
            matchedQuestions.add(question);
            printQuestion(question, true);
        }
        if (matchedQuestions.isEmpty()) {
            Ui.displayMessageStatically(NO_SEARCH_RESULTS_MSG);
        }
    }

    /**
     * Search for questions in the current question list using their module.
     *
     * @param module Module used to search for matches.
     */
    public void searchListByModule(String module){
        ArrayList<Question> matchedQuestions = new ArrayList<>();
        if(allQns.isEmpty()){
            Ui.displayMessageStatically(EMPTY_LIST_MSG);
        } else {
            Ui.displayMessageStatically(SEARCH_RESULT_MSG);
            for (Question question : allQns) {
                if(question.getModule().toLowerCase().contains(module.toLowerCase())){
                    matchedQuestions.add(question);
                    printQuestion(question, true);
                }
            }
            if(matchedQuestions.isEmpty()){
                Ui.displayMessageStatically(NO_SEARCH_RESULTS_MSG);
            }
        }
    }
    /**
     * Build a new list of questions based on a list of specified modules.
     *
     * @param modules Modules used to search for matches.
     * @return array list of questions by module
     */
    public ArrayList<Question> assembleListByModules(String[] modules) throws QuizHubExceptions{
        ArrayList<Question> matchedQuestions = new ArrayList<>();
        if (allQns.isEmpty()) {
            throw new QuizHubExceptions(EMPTY_LIST_MSG);
        }
        for (String module : modules) {
            assembleListByModule(module, matchedQuestions);
        }
        return matchedQuestions;
    }
    /**
     * Build a new list of questions based on a specified module.
     *
     * @param module The module used to search for matches.
     * @param matchedQuestions Question list to append matching questions to.
     */
    public void assembleListByModule(String module, ArrayList<Question> matchedQuestions){
        for (Question question : allQns) {
            if (!question.getModule().toLowerCase().matches(module.toLowerCase())) {
                continue;
            }
            matchedQuestions.add(question);
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
     * Shuffles the order of questions in the deck
     */
    public void shuffleQuestions(Ui ui) {
        Collections.shuffle(allQns);
        ui.displayMessage(SHUFFLE_QN_MSG);
        printQuestionList();
    }
    /**
     * Retrieves the question by its index in the question list.
     *
     * @param index The index of the question in the list.
     * @return The question, or null if the index is invalid or the question is of a different type.
     */
    public Question getQuestionByIndex(int index) {
        if (index > 0 && index <= allQns.size()) {
            return allQns.get(index - 1); // Use the toString() method to get the text of the question
        }
        return null; // Handle invalid index
    }

    /**
     * Starts a quiz session using the provided user interface (UI).
     *
     * @param ui The user interface to interact with the user.
     */

    public void startQuiz(Ui ui, ArrayList<Question> questions) {
        if (questions.isEmpty()) {
            ui.displayMessage(CommandStart.NO_QN_FOUND_MSG);
            return;
        }

        ui.displayMessage("    Starting the quiz...");
        int totalQuestions = questions.size();
        int correctAnswersCount = 0;

        for (int i = 0; i < totalQuestions; i++) {
            Question question = questions.get(i);
            ui.displayQuestion(question, i + 1, totalQuestions);
            
            String validatedAnswer = getAndValidateUserAnswer(ui, question);
            if (validatedAnswer.equals(CommandStart.EXIT_QUIZ_KEYWORD)) {
                ui.displayMessage("    Exiting the quiz...");
                ui.displayFinalScore(correctAnswersCount, totalQuestions);
                return; // Exit the quiz if the user types "\\exitquiz"
            }

            if (question.checkAnswerCorrectness(validatedAnswer)) {
                ui.displayMessage("    Correct!");
                correctAnswersCount++;
            } else {
                ui.displayMessage("    Wrong!");
                ui.displayCorrectAnswer(question);
            }
        }

        ui.displayFinalScore(correctAnswersCount, totalQuestions);
    }

    /**
     * Fetches and validates the user answer in a loop
     * @author yeo-menghan
     *
     * @param ui The ui object for displaying messages
     * @param question The question object related to the answer
     *
     * @return The validated answer, or "\exitquiz"
     * */
    private String getAndValidateUserAnswer(Ui ui, Question question) {
        String userAnswer;
        String isValidAnswer;
        do {
            ui.displayMessageSameLine("  Your Answer: ");
            userAnswer = ui.getUserInput().strip();
            if (userAnswer.equalsIgnoreCase(CommandStart.EXIT_QUIZ_KEYWORD)) {
                return userAnswer;
            }
            isValidAnswer = question.checkAnswerValidity(userAnswer);
            if (!isValidAnswer.equals(Question.VALID_ANSWER_KEYWORD)) {
                ui.displayMessage(isValidAnswer);
            }
        } while (!isValidAnswer.equals(Question.VALID_ANSWER_KEYWORD));

        return userAnswer;
    }
}
