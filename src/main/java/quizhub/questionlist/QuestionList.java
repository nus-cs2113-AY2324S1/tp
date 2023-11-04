package quizhub.questionlist;

import quizhub.command.CommandShortAnswer;
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

        if(containsDuplicateQuestion(description, SHORTANSWER, module, qnDifficulty)){
            System.out.println(CommandShortAnswer.DUPLICATED_INPUT + System.lineSeparator());
        } else{
            allQns.add(new ShortAnsQn(description, answer, module, qnDifficulty));
            if (showMessage) {
                System.out.println("    I have added the following question OwO:");
                System.out.printf("      [S] %s\n", viewQuestionByIndex(getQuestionListSize()));
                System.out.println("    Now you have " + getQuestionListSize() + " questions in the list! UWU");
            }
        }
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
    public void addMultipleChoiceQn(String description, String option1, String option2,
                                    String option3, String option4, int answer, String module,
                                    Question.QnDifficulty qnDifficulty, boolean showMessage) {

        if(containsDuplicateQuestion(description, Question.QnType.MULTIPLECHOICE, module, qnDifficulty)){
            System.out.println(CommandShortAnswer.DUPLICATED_INPUT + System.lineSeparator());
        } else {
            allQns.add(new MultipleChoiceQn(description, option1, option2, option3,
                    option4, answer, module, qnDifficulty));
            if (showMessage) {
                System.out.println("    I have added the following question OwO:");
                System.out.printf("      [M] %s\n", viewQuestionByIndex(getQuestionListSize()));
                System.out.println("    Now you have " + getQuestionListSize() + " questions in the list! UWU");
            }
        }

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
     * @param qnDifficulty The difficulty level of the short answer question.
     * @return true if all of the above are true
     */
    public boolean containsDuplicateQuestion (String description, Question.QnType qnType,
                                              String module, Question.QnDifficulty qnDifficulty) {
        for (Question question : allQns) {
            if(description.strip().equalsIgnoreCase(question.getQuestionDescription()) &&
                qnType.equals(question.getQuestionType()) &&
                module.equalsIgnoreCase(question.getModule()) &&
                qnDifficulty.equals(question.getDifficulty()) ) {
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
        String isDone = question.questionIsDone() ? "X" : " ";
        String questionTypeIdentifier = "";
        if(question.getQuestionType().equals(SHORTANSWER)){
            questionTypeIdentifier = "S";
        }
        else if (question.getQuestionType().equals(MULTIPLECHOICE)){
            questionTypeIdentifier = "M";
        }
        if(asList) {
            System.out.printf("    %d: [%s][%s] %s\n", oneIndexed, isDone, questionTypeIdentifier, question.getQuestionDescription());
        } else {
            System.out.printf("        [%s][%s] %s\n", isDone, questionTypeIdentifier, question.getQuestionDescription());
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
        Question question = null;
        try{
            int oneIndexed = index-1;
            question = allQns.get(oneIndexed);
        } catch (IndexOutOfBoundsException invalidIndex){
            System.out.println("    Ono! Please enter valid question number *sobs*");
            return;
        }
        assert(question != null);
        if(!question.questionIsDone()) {
            question.markAsDone();
            if (showMessage) {
                System.out.println("    Roger that! I have marked the following question as done >w< !");
                printQuestion(question, false);
            }
        } else {
            System.out.println("    Question originally done! No changes made!");
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
     * @return String value of the question
     */
    public String viewQuestionByIndex(int index){
        try{
            switch(allQns.get(index-1).getQuestionType()) {
            case SHORTANSWER:
                return allQns.get(index-1).getQuestionDescription();
            case MULTIPLECHOICE:
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
    public void editQuestionByIndex(int index, String editField, String newValue){
        try{
            Question question = allQns.get(index-1);
            question.editQuestion(editField, newValue);
            printQuestion(question, false);
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
     * Build a new list of questions based on a list of specified modules.
     *
     * @param modules Modules used to search for matches.
     * @return array list of questions by module
     */
    public ArrayList<Question> assembleListByModules(String[] modules) throws QuizHubExceptions{
        ArrayList<Question> matchedQuestions = new ArrayList<>();
        if (allQns.isEmpty()) {
            throw new QuizHubExceptions("    Question list is empty! Time to add some OWO");
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
            if (question.getModule().toLowerCase().matches(module.toLowerCase())) {
                matchedQuestions.add(question);
            }
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
        ui.displayMessage("    Questions are now shuffled!");
        printQuestionList();
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
     * Retrieves the question by its index in the question list.
     *
     * @param index The index of the question in the list.
     * @return The question, or null if the index is invalid or the question is of a different type.
     */
    public Question getQuestionByIndex(int index) {
        if (index > 0 && index <= allQns.size()) {
            Question question = allQns.get(index - 1);
            return question; // Use the toString() method to get the text of the question
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
            String userAnswer = ui.getUserInput().strip();
            String correctAnswer = "";
            if (question instanceof MultipleChoiceQn) {
                correctAnswer = ((MultipleChoiceQn) question).getAnswerString();
            } else {
                correctAnswer = ((ShortAnsQn) question).getQuestionAnswer();
            }

            correctAnswer = correctAnswer.strip();

            if (userAnswer.equalsIgnoreCase(correctAnswer)) {
                ui.displayMessage("    Correct!");
                correctAnswers++;
            } else {
                ui.displayMessage("    Wrong!");
                ui.displayMessage("    The answer is: " + correctAnswer);
            }

            int questionsLeft = totalQuestions - (i + 1);
            if (questionsLeft > 0) {
                ui.displayMessage("    Questions left: " + questionsLeft);
            } else {
                ui.displayMessage("    Quiz completed!");
            }
        }
        ui.displayMessage("    Your score: " + correctAnswers + "/" + totalQuestions);
    }
}
