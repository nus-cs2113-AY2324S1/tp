package quizhub.questionlist;

import quizhub.parser.Parser;
import quizhub.question.Question;
import quizhub.question.ShortAnsQn;
import quizhub.exception.QuizHubExceptions;
import quizhub.ui.Ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
/**
 * Represents the list of questions currently registered in Quizhub.
 * This list is created on program start and disposed on program termination.
 */
public class QuestionList {
    private ArrayList<Question> allQns; //array of inputs
    /**
     * Creates a new empty question list.
     */
    public QuestionList(){
        allQns = new ArrayList<>();
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
    public void addShortAnswerQn(String description, String answer, String module,
                                 Question.QnDifficulty qnDifficulty, boolean showMessage){
        allQns.add(new ShortAnsQn(description, answer, module, qnDifficulty));
        if (showMessage) {
            System.out.println("    I have added the following question OwO:");
            System.out.printf("      [S] %s\n", viewQuestionByIndex(getQuestionListSize()));
            System.out.println("    Now you have " + getQuestionListSize() + " questions in the list! UWU");
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
                if (asList) {
                    System.out.printf("    %d: [S][X] %s\n", qnIndex + 1, question.getQuestionDescription());
                } else {
                    System.out.printf("        [S][X] %s\n", question.getQuestionDescription());
                }

            } else {
                if (asList) {
                    System.out.printf("    %d: [S][] %s\n", qnIndex + 1, question.getQuestionDescription());
                } else {
                    System.out.printf("        [S][] %s\n", question.getQuestionDescription());
                }
            }
            break;
        default:
            break;
        }
    }
    /**
     * Prints all the questions in the current question list as an indexed list.
     */
    public void printQuestionList(){
        if(allQns.isEmpty()){
            System.out.println("    No questions found! Time to add some OWO");
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
        try{
            Question question = allQns.get(index-1);
            if(!question.questionIsDone()) {
                question.markAsDone();
                if (showMessage) {
                    System.out.println("    Roger that! I have marked the following question as done >w< !");
                    printQuestion(question, false);
                }
            } else {
                System.out.println("    Question originally done! No changes made!");
            }
        } catch (IndexOutOfBoundsException invalidIndex){
            System.out.println("    Ono! Please enter valid question number *sobs*");
        }
    }
    /**
     * Mark a question in the current question list as not done.
     */
    public void markQuestionAsNotDone(int index){
        try{
            Question question = allQns.get(index-1);
            if(question.questionIsDone()){
                question.markAsNotDone();
                System.out.println("    Roger that! I have unmarked the following question as done >w< !");
                printQuestion(question, false);
            } else {
                System.out.println("    Question originally not done! No changes made!");
            }
        } catch (IndexOutOfBoundsException invalidIndex){
            System.out.println("    Ono! Please enter valid question number *sobs*");
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
            if(question.getDifficulty() != qnDifficulty){
                allQns.get(index-1).markDifficulty(qnDifficulty);
                if(showMessage) {
                    System.out.println("    Roger that! I have marked the following question as " +
                            difficulty +
                            " >w< !");
                    printQuestion(question, false);
                }
            } else {
                System.out.println("    Question is already set as " +
                        difficulty +
                        " ! No changes made!");
            }
        } catch (IndexOutOfBoundsException invalidIndex){
            System.out.println("    Ono! Please enter valid question number *sobs*");
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
            System.out.println("    Ono! Please enter valid question number *sobs*");
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
        } catch(InputMismatchException |NullPointerException | IndexOutOfBoundsException invalidIndex){
            return "Question Not Found";
        }
    }

    /**
     * Delete a question from the current question list.
     *
     * @param index The list index of the question to be deleted.
     */
    public void editQuestionByIndex(int index, String newDescription, String newAnswer){
        try{
            Question question = allQns.get(index-1);
            question.editQuestion(newDescription, newAnswer);
            System.out.println("    Roger that! I have edited the following question >w< !");
            printQuestion(question, false);
            System.out.println("    Now you have " + getQuestionListSize() + " questions in the list! UWU");
        } catch (IndexOutOfBoundsException invalidIndex){
            if(index != 0){
                System.out.println("    Ono! Please enter valid question number *sobs*");
            }
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
        } else {
            System.out.println("    Here are questions that matched your search:");
            for (Question question : allQns) {
                if(question.getQuestionDescription().toLowerCase().contains(keyword.toLowerCase())){
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
        } else {
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
     * Search for questions in the current question list using their module.
     *
     * @param module Module used to search for matches.
     */
    public void searchListByModule(String module){
        ArrayList<Question> matchedQuestions = new ArrayList<>();
        if(allQns.isEmpty()){
            System.out.println("    Question list is empty! Time to add some OWO");
        } else {
            System.out.println("    Here are questions that matched your search:");
            for (Question question : allQns) {
                if(question.getModule().toLowerCase().contains(module.toLowerCase())){
                    matchedQuestions.add(question);
                    printQuestion(question, true);
                }
            }
            if(matchedQuestions.isEmpty()){
                System.out.println("    No results found :< Check your module is correct?");
            }
        }
    }
    /**
     * Build a new list of questions based on specified module.
     *
     * @param module Module used to search for matches.
     */
    public ArrayList<Question> categoriseListByModule(String module){
        ArrayList<Question> matchedQuestions = new ArrayList<>();
        try {
            if (allQns.isEmpty()) {
                throw new QuizHubExceptions("    Question list is empty! Time to add some OWO");
            } else {
                for (Question question : allQns) {
                    if (question.getModule().toLowerCase().matches(module.toLowerCase())) {
                        matchedQuestions.add(question);
                    }
                }
            }
        } catch (QuizHubExceptions emptyList){
            System.out.println(emptyList.getMessage());
        }
        return matchedQuestions;
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
    public void shuffleQuestions() {
        Collections.shuffle(allQns);
    }
    /**
     * Retrieves the answer for a question by its index in the question list.
     * @param index The index of the question in the list.
     * @return The answer to the question, or null if the index is invalid or the question is of a different type.
     */
    public String getAnswerByIndex(int index, ArrayList<Question> questions) {
        try {
            Question question = questions.get(index - 1);
            if (question instanceof ShortAnsQn) {
                return ((ShortAnsQn) question).getQuestionAnswer();
            } else {
                System.out.println("    This question is not a Short Answer question.");
                return null;
            }
        } catch (IndexOutOfBoundsException invalidIndex) {
            System.out.println("    Ono! Please enter a valid question number *sobs*");
            return null;
        }
    }
    /**
     * Retrieves the question by its index in the question list.
     *
     * @param index The index of the question in the list.
     * @return The question, or null if the index is invalid or the question is of a different type.
     */
    public String getQuestionTextByIndex(int index) {
        if (index >= 0 && index < allQns.size()) {
            Question question = allQns.get(index);
            return question.toString(); // Use the toString() method to get the text of the question
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
            ui.displayMessage("    No questions found! Add questions before starting the quiz.");
            return;
        }

        ui.displayMessage("    Starting the quiz...");
        int totalQuestions = questions.size();
        int correctAnswers = 0;

        for (int i = 0; i < totalQuestions; i++) {
            Question question = questions.get(i);

            ui.displayQuestion(question, i + 1, totalQuestions);
            String correctAnswer = getAnswerByIndex(i + 1,  questions).strip(); // Get correct answer by index
            String userAnswer = ui.getUserInput().strip();

            if (userAnswer.equalsIgnoreCase(correctAnswer)) {
                System.out.println("    Correct!");
                correctAnswers++;
            } else {
                System.out.println("    Wrong!");
            }

            int questionsLeft = totalQuestions - (i + 1);
            if (questionsLeft > 0) {
                System.out.println("    Questions left: " + questionsLeft);
            } else {
                System.out.println("    Quiz completed!");
            }
        }
        System.out.println("    Your score: " + correctAnswers + "/" + totalQuestions);
    }
}
