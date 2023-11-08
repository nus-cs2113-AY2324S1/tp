package quizhub.ui;

import quizhub.question.MultipleChoiceQn;
import quizhub.storage.Storage;
import quizhub.questionlist.QuestionList;
import quizhub.question.Question;

import java.util.Scanner;
/**
 * Represents the user interface for CLI displays.
 * Supports CLI interactions with users.
 */
public class Ui {
    public static final String LOGO =
                    "    _______          _________ _______                    ______  \n" +
                    "   (  ___  )|\\     /|\\__   __// ___   )|\\     /||\\     /|(  ___ \\ \n" +
                    "   | (   ) || )   ( |   ) (   \\/   )  || )   ( || )   ( || (   ) )\n" +
                    "   | |   | || |   | |   | |       /   )| (___) || |   | || (__/ / \n" +
                    "   | |   | || |   | |   | |      /   / |  ___  || |   | ||  __ (  \n" +
                    "   | | /\\| || |   | |   | |     /   /  | (   ) || |   | || (  \\ \\ \n" +
                    "   | (_\\ \\ || (___) |___) (___ /   (_/\\| )   ( || (___) || )___) )\n" +
                    "   (____\\/_)(_______)\\_______/(_______/|/     \\|(_______)|/ \\___/";
    public static final String INVALID_COMMAND_MSG =  "    Please enter a valid command :0";
    public static final String INVALID_COMMAND_FEEDBACK = "    Here are the list of commands you can use:" +
            System.lineSeparator() +
            "    1. help - shows the list of commands you can use," +
            System.lineSeparator() +
            "    2. short [question]/[answer]/[module]/[difficulty] - adds a short answer question and " +
            "its answer to the list," +
            System.lineSeparator() +
            "    3. mcq [question]/[option 1]/[option 2]/[option 3]/[option 4]/[answer index]/[module]/[difficulty]" +
            " - adds a multiple-choice question and its answer to the list" +
            System.lineSeparator() +
            "    4. list - shows the list of questions and answers," +
            System.lineSeparator() +
            "    5. delete [question number] - deletes the question and answer at the specified number," +
            System.lineSeparator() +
            "    6. find /description [description] - displays all questions containing the specified description," +
            System.lineSeparator() +
            "    7. find /description [module] - displays all questions that belong to the specified module," +
            System.lineSeparator() +
            "    8. edit [question number] /description [description] - edits the description of the question " +
            "with the specified number," +
            System.lineSeparator() +
            "    9. edit [question number] /answer [answer] - edits the answer to the question with " +
            "the specified number," +
            System.lineSeparator() +
            "    10. edit [question number] /option[number] [new value] - edits the option of the question with " +
            "the specified number (MCQ only)," +
            System.lineSeparator() +
            "    11. start /[quiz mode] [start details] /[qn mode] - " +
            "starts the quiz with option for /module or /all and /random or /normal," +
            System.lineSeparator() +
            "    12. shuffle - shuffle quiz questions to a random order," +
            System.lineSeparator() +
            "    13. markdiff [question number] /[question difficulty] - sets the difficulty of question " +
            "with the specified number," +
            System.lineSeparator() +
            "    14. bye - exits the program";
    public static final String INVALID_INTEGER_INDEX_MSG = "    Please enter valid integer question index!";

    public static final String INVALID_QUESTION_DIFFICULTY_MSG = "    Ono! We only support easy, normal and hard " +
            "difficulty levels" + System.lineSeparator() + "    No changes will be made to your difficulty level";
    private final Scanner input = new Scanner(System.in);  // Create a Scanner object
    private Storage dataStorage;
    private QuestionList questions;
    /**
     * Sets up the bridging between the UI and question data.
     *
     * @param questions A record of all questions documented that is built on program start and disposed on exit.
     * @param dataStorage The hard disk record of all questions documented that persists even on program exit.
     */
    public Ui(Storage dataStorage, QuestionList questions){
        this.dataStorage = dataStorage;
        this.questions = questions;
    }
    /**
     * Print out separating line in CLI to mark
     * start and end of QuizHub output.
     */
    public void showLine(){
        System.out.println("    ____________________________________________________________\n");
    }
    /**
     * Displays app logo and opening message to welcome users
     * on the launch of QuizHub application.
     */
    public void displayOpeningMessage(){
        System.out.println(LOGO);
        showLine();
        System.out.println("    Welcome to Quizhub!!!\n");
        System.out.println("    Let the quizzing begin XDD");
        System.out.println();
        dataStorage.loadData(questions);
        assert questions != null : "Invalid null questions";
        showLine();
    }

    /**
     * Retrieves the CLI input from the user
     * and documents it as a String object.
     */
    public String getUserInput() {
        if(input.hasNextLine()){
            return input.nextLine();
        } else {
            return "";
        }

    }
    /**
     * Displays closing message on exiting the QuizHub application.
     */
    public void displayClosingMessage(){
        dataStorage.updateData(questions);
        System.out.println("    Are you sure you want to stop quizzing?");
        System.out.println("    Well... hope you had fun quizzing :D");
        System.out.println("    See you again soon!");
        showLine();
    }
    /**
     * Displays a question along with its index in a set of questions and the total count of questions.
     * Extracts and displays the question part from the question description, which is in the "question/answer" format.
     * If the format is invalid or missing, it prompts the user to edit the question via the edit function.
     *
     * @param question           The Question object containing the question description.
     * @param currentQuestionIndex The index of the current question in the set of questions.
     * @param totalQuestions     The total count of questions in the set.
     */

    public void displayQuestion(Question question, int currentQuestionIndex, int totalQuestions) {
        showLine();
        System.out.println("    Question " + currentQuestionIndex + " / " + totalQuestions + ":");

        if (question instanceof MultipleChoiceQn) {
            MultipleChoiceQn mcq = (MultipleChoiceQn) question;
            String questionDescription = mcq.getQuestionDescription();
            String[] parts = questionDescription.split("/");

            if (parts.length >= 6) {
                System.out.println("    " + parts[0]);  // part[0] returns the question part
                System.out.println("    1. " + parts[1]); // option 1
                System.out.println("    2. " + parts[2]); // option 2
                System.out.println("    3. " + parts[3]); // option 3
                System.out.println("    4. " + parts[4]); // option 4
            } else {
                System.out.println("    Invalid question format, please edit this question via the edit function");
            }
        } else {
            String questionDescription = question.getQuestionDescription();
            String[] parts = questionDescription.split("/");

            if (parts.length >= 1) {
                System.out.println("    " + parts[0]);  // part[0] returns the question part
            } else {
                System.out.println("    Invalid question format, please edit this question via the edit function");
            }
        }
    }

    public void showInvalidCommandHelp(String feedback) {
        System.out.println(feedback);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayMessageSameLine(String message){
        System.out.print(message);
    }

    public void displayNumberOfQuestions() {
        System.out.println("    Now you have " + questions.getQuestionListSize() + " questions in the list.");
    }
}

