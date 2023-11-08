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
    public boolean addShortAnswerQn(String description, String answer, String module,
                                 Question.QnDifficulty qnDifficulty, boolean showMessage){

        boolean isDuplicate = containsDuplicateQuestion(description, SHORTANSWER, module, showMessage);
        if (isDuplicate){
            return false;
        }
        allQns.add(new ShortAnsQn(description, answer, module, qnDifficulty));
        if (showMessage) {
            System.out.println("    I have added the following question OwO:");
            System.out.println("    " + viewQuestionByIndex(getQuestionListSize()));
            System.out.println("    Now you have " + getQuestionListSize() + " questions in the list! UWU");
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
            System.out.println("    I have added the following question OwO:");
            System.out.println("    " + viewQuestionByIndex(getQuestionListSize()));
            System.out.println("    Now you have " + getQuestionListSize() + " questions in the list! UWU");
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
                    System.out.println(CommandShortAnswer.DUPLICATED_INPUT + System.lineSeparator());
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
        System.out.println(indexString + question);
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
        Question question;
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
                System.out.println("    Question is already set as " +
                        difficulty + " ! No changes made!");
                return;
            }
            allQns.get(index-1).markDifficulty(qnDifficulty);
            if(!showMessage) {
                return;
            }
            System.out.println("    Roger that! I have marked the following question as " +
                    difficulty + " >w< !");
            printQuestion(question, false);
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
            System.out.println("    Question list is empty! Time to add some OWO");
            return;
        }
        System.out.println("    Here are questions that matched your search:");
        for (Question question : allQns) {
            if (!question.getQuestionBody().toLowerCase().contains(keyword.toLowerCase())) {
                continue;
            }
            matchedQuestions.add(question);
            printQuestion(question, true);
        }
        if (matchedQuestions.isEmpty()) {
            System.out.println("    No results found :< Check your keyword is correct?");
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
        ui.displayMessage("    Questions are now shuffled!");
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
//    public void startQuiz(Ui ui, ArrayList<Question> questions) {
//        if (questions.isEmpty()) {
//            ui.displayMessage("    No question found in list / no question found pertaining to module. " +
//                    "Add questions before starting the quiz");
//            return;
//        }
//
//        ui.displayMessage("    Starting the quiz...");
//        int totalQuestions = questions.size();
//        int correctAnswers = 0;
//
//        for (int i = 0; i < totalQuestions; i++) {
//            Question question = questions.get(i);
//            ui.displayQuestion(question, i + 1, totalQuestions);
//            String userAnswer;
//            boolean isValidAnswer;
//
//            do {
//                ui.displayMessageSameLine("  Your Answer: ");
//                userAnswer = ui.getUserInput().strip();
//                isValidAnswer = true; // Assume the answer is valid initially
//
//                if (userAnswer.isEmpty()) {
//                    isValidAnswer = false;
//                    ui.displayMessage("    The question cannot be left blank.");
//                    continue; // Skip the remaining checks and prompt for input again
//                }
//
//                if ("\\exitquiz".equalsIgnoreCase(userAnswer)) {
//                    ui.displayMessage("    Exiting the quiz...");
//                    return; // Exit the startQuiz method
//                }
//
//                if (question instanceof MultipleChoiceQn) {
//                    try {
//                        int answerNumber = Integer.parseInt(userAnswer);
//                        // Check for numbers not within range 1-4
//                        if (answerNumber < 1 || answerNumber > 4){
//                            isValidAnswer = false;
//                            ui.displayMessage("    Please enter a valid choice between 1 and 4.");
//                        }
//                    } catch (NumberFormatException e) {
//                        isValidAnswer = false;
//                        ui.displayMessage("    That's not a valid response. Please enter a number between 1 and 4.");
//                    }
//                }
//
//            } while (!isValidAnswer);
//
//            String correctAnswer;
//            if (question instanceof MultipleChoiceQn) {
//                correctAnswer = ((MultipleChoiceQn) question).getAnswerString();
//            } else {
//                correctAnswer = ((ShortAnsQn) question).getQuestionAnswer();
//            }
//
//            correctAnswer = correctAnswer.strip();
//
//            if (userAnswer.equalsIgnoreCase(correctAnswer)) {
//                ui.displayMessage("    Correct!");
//                correctAnswers++;
//            } else {
//                ui.displayMessage("    Wrong!");
//                ui.displayMessage("    The correct answer is: " + correctAnswer);
//            }
//        }
//
//        ui.displayMessage("    Your score: " + correctAnswers + "/" + totalQuestions);
//        ui.displayMessage("    Quiz completed!");
//    }
    public void startQuiz(Ui ui, ArrayList<Question> questions) {
        if (questions.isEmpty()) {
            ui.displayMessage("    No question found in list / no question found pertaining to module. " +
                    "Add questions before starting the quiz");
            return;
        }

        ui.displayMessage("    Starting the quiz...");
        int totalQuestions = questions.size();
        int correctAnswersCount = 0;

        for (int i = 0; i < totalQuestions; i++) {
            Question question = questions.get(i);
            ui.displayQuestion(question, i + 1, totalQuestions);
            String userAnswer = getUserAnswer(ui, question);

            if (userAnswer == null) return; // Exit the quiz if the user types "\\exitquiz"

            if (checkAnswer(question, userAnswer)) {
                ui.displayMessage("    Correct!");
                correctAnswersCount++;
            } else {
                ui.displayMessage("    Wrong!");
                ui.displayCorrectAnswer(question);
            }
        }

        ui.displayFinalScore(correctAnswersCount, totalQuestions);
    }

    private String getUserAnswer(Ui ui, Question question) {
        String userAnswer;
        boolean isValidAnswer;
        do {
            ui.displayMessageSameLine("  Your Answer: ");
            userAnswer = ui.getUserInput().strip();
            isValidAnswer = validateAnswer(ui, userAnswer, question);
        } while (!isValidAnswer);

        return userAnswer;
    }

    private boolean validateAnswer(Ui ui, String userAnswer, Question question) {
        if (userAnswer.isEmpty()) {
            ui.displayMessage("    The question cannot be left blank.");
            return false;
        }

        if ("\\exitquiz".equalsIgnoreCase(userAnswer)) {
            ui.displayMessage("    Exiting the quiz...");
            return false;
        }

        return validateMCQAnswer(ui, userAnswer, question);
    }

    private boolean validateMCQAnswer(Ui ui, String userAnswer, Question question) {
        if (question instanceof MultipleChoiceQn) {
            try {
                int answerNumber = Integer.parseInt(userAnswer);
                if (answerNumber < 1 || answerNumber > 4) {
                    ui.displayMessage("    Please enter a valid choice between 1 and 4.");
                    return false;
                }
            } catch (NumberFormatException e) {
                ui.displayMessage("    That's not a valid response. Please enter a number between 1 and 4.");
                return false;
            }
        }
        return true;
    }

    private boolean checkAnswer(Question question, String userAnswer) {
        String correctAnswer = question.getCorrectAnswer();
        return userAnswer.equalsIgnoreCase(correctAnswer);
    }
}
