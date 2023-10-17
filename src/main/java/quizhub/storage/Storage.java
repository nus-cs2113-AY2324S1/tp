package quizhub.storage;
import quizhub.question.Question;
import quizhub.questionlist.QuestionList;

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
        } catch(NullPointerException | IOException invalidFilePath){
            System.out.println("    " + invalidFilePath.getMessage());
        }
        int questionIndex = 0;
        try {
            Scanner fileScanner = new Scanner(dataFile);
            if (!fileScanner.hasNext()) {
                return;
            }
            fileScanner.nextLine();
            while (fileScanner.hasNext()) {
                questionIndex++;
                String nextQuestion = fileScanner.nextLine();
                String[] questionSubStrings = nextQuestion.split("\\|");
                String questionType = questionSubStrings[0].strip();
                String questionDoneStatus = questionSubStrings[1].strip();
                String questionDescription = questionSubStrings[2].strip();
                // TODO : change this entire code chunk, right now they're all default
                switch (questionType) {
                case "S":
                    questions.addToQuestionList("short " + questionDescription,
                            Question.qnType.SHORTANSWER, false);
                    if (questionDoneStatus.equals("done")) {
                        questions.markQuestionAsDone(questionIndex, false);
                    }
                    break;
                default:
                    System.out.println(nextQuestion);
                    break;
                }
            }
        }
        catch(NullPointerException | IOException  invalidFilePath){
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
        if (questions.getQuestionListSize() > 0) {
            System.out.println("    You currently have the following questions uWu");
            questions.printQuestionList();
        } else {
            System.out.println("    You currently have no saved questions uWu");
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
                switch (question.getQuestionType()) {
                case SHORTANSWER:
                    if (question.questionIsDone()) {
                        writeToFile(dataFile.getPath(), "S | done |  " + question.getQuestionDescription()
                                + System.lineSeparator(), true);
                    } else {
                        writeToFile(dataFile.getPath(), "S | undone |  " + question.getQuestionDescription()
                                + System.lineSeparator(), true);
                    }
                    break;

                default:
                    break;
                }
            }

        }
        catch(NullPointerException | IOException invalidFilePath){
            System.out.println("    " + invalidFilePath.getMessage());
            System.out.println("    ____________________________________________________________");
        }
    }
}
