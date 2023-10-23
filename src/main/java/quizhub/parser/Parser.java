package quizhub.parser;

import quizhub.command.*;
import quizhub.exception.QuizHubExceptions;
import quizhub.question.Question;

/**
 * Represents a parser that converts user inputs into command objects.
 */
public class Parser {

    public static final String INVALID_COMMAND_FEEDBACK = "Here are the list of commands you can use:" +
            System.lineSeparator() +
            "    1. help - shows the list of commands you can use" +
            System.lineSeparator() +
            "    2. short [question]/[answer]/[category]/[difficulty] - adds a short answer question and its answer to the list," +
            System.lineSeparator() +
            "     3. list - shows the list of questions and answers," +
            System.lineSeparator() +
            "     4. delete [question number] - deletes the question and answer at the specified number," +
            System.lineSeparator() +
            "     5. find /[description] - displays all questions that contains the the specified description," +
            System.lineSeparator() +
            "     6. edit [question number] /description [description] - edits the description of the question " +
            "with the specified number," +
            System.lineSeparator() +
            "     7. edit [question number] /answer [answer] - edits the answer to the question with " +
            "the specified number," +
            System.lineSeparator() +
            "     8. start /[quiz mode] [start details] /[qn mode] - starts the quiz," +
            System.lineSeparator() +
            "     9. shuffle - shuffle quiz questions to a random order PERMANENTLY," +
            System.lineSeparator() +
            "     10. markdiff [question number] [question difficulty] - sets the difficulty of question " +
            "with the specified number," +
            System.lineSeparator() +
            "     11. bye - exits the program";
    public static final String INVALID_INTEGER_INDEX = "    Please enter valid integer index!";
    /**
     * Analyses and extracts relevant information from user input
     * to create a new Command object of the right type.
     *
     * @param userInput The full user CLI input.
     */
    public Command parseCommand(String userInput) {
        String[] commandTokens = userInput.split(" ");
        if (commandTokens.length == 0) {
            return new CommandInvalid(INVALID_COMMAND_FEEDBACK);
        }
        String commandTitle = commandTokens[0];

        try {
            switch (commandTitle) {
            case "bye":
                return new CommandExit();
            case "list":
                return new CommandList();
            case "short":
                return new CommandShortAnswer(userInput);
            case "start":
                return new CommandStart(userInput);
            case "edit":
                return new CommandEdit(userInput);
            case "delete":
                return new CommandDelete(userInput);
            case "find":
                return new CommandFind(userInput);
            case "shuffle":
                return new CommandShuffle();
            case "markdiff":
                return new CommandMarkDifficulty(userInput);
            case "help":
                return new CommandHelp();
            default:
                return new CommandInvalid(INVALID_COMMAND_FEEDBACK);
            }
        }
        catch (NumberFormatException | ArrayIndexOutOfBoundsException invalidIndex) {
            return new CommandInvalid(INVALID_INTEGER_INDEX);
        }
        catch (Exception error) {
            return new CommandInvalid(INVALID_COMMAND_FEEDBACK);
        }
    }
    /**
     * Extracts relevant information after a specified keyword
     * from CLI user input.
     *
     * @param userInput The full user CLI input.
     * @param keyWord The keyword used to partition the user input.
     */
    public static String getContentAfterKeyword(String userInput, String keyWord) throws ArrayIndexOutOfBoundsException {
        String content;
        content = userInput.split(keyWord)[1].strip();
        if (content.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return content;
    }
    /**
     * Extracts difficulty from user command to assign to a question.
     * Default invalid difficulty is assigned if invalid difficulty given.
     *
     * @param difficulty The difficulty level defined by user in CLI.
     */
    public static Question.QnDifficulty extractQuestionDifficulty(String difficulty){
        Question.QnDifficulty qnDifficulty = Question.QnDifficulty.DEFAULT;
        try {
            switch (difficulty.toLowerCase()) {
                case "easy":
                    qnDifficulty = Question.QnDifficulty.EASY;
                    break;
                case "hard":
                    qnDifficulty = Question.QnDifficulty.HARD;
                    break;
                case "normal":
                    qnDifficulty = Question.QnDifficulty.NORMAL;
                    break;
                default:
                    throw new QuizHubExceptions("    Ono! We only support easy, normal and hard difficulty levels" +
                            System.lineSeparator() +
                            "    Please only use 'easy', 'normal' or 'hard' for difficulty levels!");
            }
        } catch (QuizHubExceptions incorrectDifficulty){
            System.out.println(incorrectDifficulty.getMessage());
        }
        return qnDifficulty;
    }

}
