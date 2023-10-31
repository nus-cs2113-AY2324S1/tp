package quizhub.storage;
import quizhub.question.Question;
import quizhub.questionlist.QuestionList;
import quizhub.parser.Parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Represents the hard disk storage where
 * question data is stored, read and updated.
 */
public class Storage {
    private File dataFile;
    /**
     * Creates a new storage for storing question data.
     *
     * @param filePath The file location in hard disk where data is stored and read from.
     */
    public Storage(String filePath) {
        dataFile = new File(filePath);
    }
    /**
     * Write in new data to storage.
     *
     * @param filePath The file location in hard disk where data is written to.
     * @param textToAdd Data to be written.
     * @param toAppend If true, new data is added to the back of existing data instead of overwriting them.
     */
    private void writeToFile(String filePath, String textToAdd, boolean toAppend) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, toAppend);
        fileWriter.write(textToAdd);
        fileWriter.close();
    }
    /**
     * Adds a question from storage into question list being built.
     *
     * @param questions The question list to be built.
     * @param qnType Type of current question.
     * @param qnDescription Description of current question.
     * @param qnDoneStatus Done status of current question.
     * @param difficulty Difficulty of current question.
     * @param qnModule Module of current question.
     */
    private int addQuestionFromFile(QuestionList questions, String qnType, String qnDescription,
                                    String qnDoneStatus, Question.QnDifficulty difficulty,
                                    String qnModule) {
        switch (qnType) {
        case "S":
            try {
                // Split the description by "/" and check for empty fields
                String[] qnTokens = qnDescription.split("/");
                if (qnTokens[0].isEmpty() || qnTokens[1].isEmpty() || qnModule.isEmpty()) {
                    return 1;
                }
                questions.addShortAnswerQn(qnTokens[0], qnTokens[1], qnModule, difficulty, false);
                if (qnDoneStatus.equals("done")) {
                    questions.markQuestionAsDone(questions.getQuestionListSize(), false);
                }
                return 0;
            } catch (ArrayIndexOutOfBoundsException exception) {
                return 1;
            }
        default:
            return 1;
        }
    }
    /**
     * Parse raw questions read from the question file and store them in the questionList
     * Used at start of program to load all questions from the file
     *
     * @param rawQuestions the arrayList of question strings to be parsed
     * @param questions the questionList object for string parsed questions
     */
    private void parseQuestionsFromStrings(ArrayList<String> rawQuestions, QuestionList questions) {
        int failedQuestions = 0;
        for (int i = 1; i < rawQuestions.size(); i++) {
            try {
                String currentQuestion = rawQuestions.get(i);
                String[] questionSubStrings = currentQuestion.split("\\|");
                String questionType = questionSubStrings[0].strip();
                String questionDoneStatus = questionSubStrings[1].strip();
                String questionDescription = questionSubStrings[2].strip();
                String questionModule = questionSubStrings[3].strip();
                String questionDifficulty = questionSubStrings[4].strip();
                Question.QnDifficulty difficulty = Parser.extractQuestionDifficulty(questionDifficulty);
                failedQuestions += addQuestionFromFile(questions, questionType, questionDescription,
                        questionDoneStatus, difficulty, questionModule);
            } catch (ArrayIndexOutOfBoundsException e) {
                failedQuestions++;
            }
        }
        System.out.println("    " + failedQuestions + " questions parsed unsuccessfully from storage file\n");
    }
    /**
     * Build a new question list from data stored in hard disk.
     * Used at program start to build the current question list.
     *
     * @param questions The question list to be built.
     */
    public void buildCurrentListFromFile(QuestionList questions){
        try {
            if (dataFile.createNewFile()) {
                System.out.println("    Question-list created: " + dataFile.getName());
            }
        } catch(NullPointerException | IOException invalidFilePath) {
            System.out.println("    " + invalidFilePath.getMessage());
        }
        try {
            Scanner fileScanner = new Scanner(dataFile);
            // Pipe all lines into string arrayList for processing
            ArrayList<String> rawQuestions = new ArrayList<String>();
            while (fileScanner.hasNext()) {
                String rawQuestion = fileScanner.nextLine();
                rawQuestions.add(rawQuestion);
            }
            if (rawQuestions.size() <= 1) {
                fileScanner.close();
                return;
            }
            parseQuestionsFromStrings(rawQuestions, questions);
            fileScanner.close();
        } catch(NullPointerException | IOException  invalidFilePath) {
            System.out.println("    " + invalidFilePath.getMessage());
        }
    }
    /**
     * Build a new question list from data stored in hard disk.
     * Prints out the questions in the list in CLI.
     *
     * @param questions The question list that has been built.
     */
    public void loadData(QuestionList questions) {
        buildCurrentListFromFile(questions);
        if (questions.getQuestionListSize() == 0) {
            System.out.println("    You currently have no saved questions uWu");
            return;
        }
        System.out.println("    You currently have the following questions uWu");
        questions.printQuestionList();
    }
    private void storeQuestionToFile(Question question) throws IOException {
        String isDoneString = "undone";
        if (question.questionIsDone()) {
            isDoneString = "done";
        }
        switch (question.getQuestionType()) {
        case SHORTANSWER:
            writeToFile(dataFile.getPath(), "S | " + isDoneString + " | " + question.getQuestionDescription()
                    + System.lineSeparator(), true);
            break;
        default:
            break;
        }
    }
    /**
     * Overwrites all existing data in storage with
     * the current questions in the question list.
     * Used after every question change and on program termination.
     *
     * @param questions The question list to overwrite current data with.
     */
    public void updateData(QuestionList questions){
        try{
            //flush all current records
            writeToFile(dataFile.getPath(), "Latest Questions" + System.lineSeparator(), false);
            ArrayList<Question> allQuestions = questions.getAllQns();
            for (Question question : allQuestions) {
                storeQuestionToFile(question);
            }
        } catch(NullPointerException | IOException invalidFilePath) {
            System.out.println("    " + invalidFilePath.getMessage());
            System.out.println("    ____________________________________________________________");
        }
    }
}
