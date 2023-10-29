package quizhub.parser;

import quizhub.command.Command;
import quizhub.command.CommandExit;
import quizhub.command.CommandInvalid;
import quizhub.command.CommandList;
import quizhub.command.CommandShortAnswer;
import quizhub.command.CommandStart;
import quizhub.command.CommandEdit;
import quizhub.command.CommandDelete;
import quizhub.command.CommandFind;
import quizhub.command.CommandShuffle;
import quizhub.command.CommandMarkDifficulty;
import quizhub.command.CommandHelp;
import quizhub.question.Question;
import quizhub.questionlist.QuestionList;
import quizhub.ui.Ui;

/**
 * Represents a parser that converts user inputs into command objects.
 */
public class Parser {
    /**
     * Analyses and extracts relevant information from user input
     * to create a new Command object of the right type.
     *
     * @param userInput The full user CLI input.
     */
    public static Command parseCommand(String userInput) {
        String[] commandTokens = userInput.split(" ");
        if (commandTokens.length == 0) {
            return new CommandInvalid(Ui.INVALID_COMMAND_MSG + System.lineSeparator() +
                    Ui.INVALID_COMMAND_FEEDBACK);
        }
        String commandTitle = commandTokens[0];

        try {
            switch (commandTitle) {
            case "bye":
                return new CommandExit();
            case "list":
                return new CommandList();
            case "short":
                return parseShortAnswerCommand(userInput);
            case "start":
                return parseStartCommand(userInput);
            case "edit":
                return parseEditCommand(userInput);
            case "delete":
                return parseDeleteCommand(userInput);
            case "find":
                return parseFindCommand(userInput);
            case "shuffle":
                return new CommandShuffle();
            case "markdiff":
                return parseMarkDiffCommand(userInput);
            case "help":
                return new CommandHelp();
            default:
                return new CommandInvalid(Ui.INVALID_COMMAND_MSG + System.lineSeparator() +
                        Ui.INVALID_COMMAND_FEEDBACK);
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException invalidIndex) {
            return new CommandInvalid(Ui.INVALID_INTEGER_INDEX_MSG);
        } catch (Exception error) {
            return new CommandInvalid(Ui.INVALID_COMMAND_FEEDBACK);
        }
    }
    /**
     * Extracts relevant information after a specified keyword
     * from CLI user input.
     *
     * @param userInput The full user CLI input.
     * @param keyWord The keyword used to partition the user input.
     */
    public static String getContentAfterKeyword(String userInput, String keyWord)
            throws ArrayIndexOutOfBoundsException {
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
    public static Question.QnDifficulty extractQuestionDifficulty(String difficulty) {
        switch (difficulty.toLowerCase()) {
        case "easy":
            return Question.QnDifficulty.EASY;
        case "hard":
            return Question.QnDifficulty.HARD;
        case "normal":
            return Question.QnDifficulty.NORMAL;
        default:
            System.out.println(Ui.INVALID_QUESTION_DIFFICULTY_MSG);
            return Question.QnDifficulty.INVALID;
        }
    }

    /**
     * Attempt to parse user input into a Short Answer Command
     *
     * @param userInput Raw command entered by the user
     * @return Short Answer command or an Invalid Command
     */
    private static Command parseShortAnswerCommand(String userInput) {
        String description;
        String answer;
        String module;
        String difficulty;

        try {
            // Split the input by 'short' and then by '/' to separate the parts
            String[] inputTokens = userInput.split("short")[1].strip().split("/");

            // Check if there are exactly 4 parts (description, answer, module, difficulty)
            if (inputTokens.length > 4) {
                return new CommandInvalid(CommandShortAnswer.TOO_MANY_ARGUMENTS_MSG);
            }

            // Extract the values for description, answer, module, and difficulty
            description = inputTokens[0].strip();
            answer = inputTokens[1].strip();
            module = inputTokens[2].strip();
            difficulty = inputTokens[3].strip();

            if (description.isEmpty() || answer.isEmpty() || module.isEmpty() || difficulty.isEmpty()) {
                return new CommandInvalid(CommandShortAnswer.MISSING_FIELDS_MSG +
                        System.lineSeparator() + CommandShortAnswer.INVALID_FORMAT_MSG);
            }

            Question.QnDifficulty qnDifficulty = extractQuestionDifficulty(difficulty);
            return new CommandShortAnswer(description, answer, module, qnDifficulty);
        } catch (ArrayIndexOutOfBoundsException exception) {
            return new CommandInvalid(CommandShortAnswer.INVALID_FORMAT_MSG);
        }
    }

    /**
     * Attempt to parse user input into a Delete Command
     *
     * @param userInput Raw command entered by the user
     * @return Delete command or an Invalid Command
     */
    private static Command parseDeleteCommand(String userInput) {
        int qnIndex;
        String[] editDetails;
        try {
            editDetails = userInput.split(" ");
            qnIndex = Integer.parseInt(editDetails[1].strip());
            return new CommandDelete(qnIndex);
        } catch (NumberFormatException incompleteCommand) {
            return new CommandInvalid(Ui.INVALID_INTEGER_INDEX_MSG + System.lineSeparator() +
                    CommandDelete.INVALID_FORMAT_MSG);
        } catch (ArrayIndexOutOfBoundsException incompleteCommand) {
            return new CommandInvalid(CommandDelete.MISSING_INDEX_MSG + System.lineSeparator() +
                    CommandDelete.INVALID_FORMAT_MSG);
        }
    }

    /**
     * Attempt to parse user input into a Find Command
     *
     * @param userInput Raw command entered by the user
     * @return Find command or an Invalid Command
     */
    private static Command parseFindCommand(String userInput) {
        String searchCriteria;
        String searchKeyword;
        try {
            searchCriteria = userInput.split("/")[1].strip().split(" ")[0].strip();
        } catch (ArrayIndexOutOfBoundsException incompleteCommand) {
            return new CommandInvalid(CommandFind.MISSING_CRITERIA_MSG + System.lineSeparator() +
                    CommandFind.INVALID_FORMAT_MSG);
        }
        try{
            searchKeyword = userInput.split("/" + searchCriteria)[1].strip();
        } catch (ArrayIndexOutOfBoundsException incompleteCommand) {
            return new CommandInvalid(CommandFind.MISSING_KEYWORD_MSG + System.lineSeparator() +
                    CommandFind.INVALID_FORMAT_MSG);
        }
        return new CommandFind(searchCriteria, searchKeyword);
    }

    /**
     * Attempt to parse user input into an Edit Command
     *
     * @param userInput Raw command entered by the user
     * @return Edit command or an Invalid Command
     */
    private static Command parseEditCommand(String userInput) {
        String[] editDetails;
        String editCriteria;
        int qnIndex;
        String newDescription = "";
        String newAnswer = "";
        try {
            editDetails = userInput.split(" ");
            qnIndex = Integer.parseInt(editDetails[1].strip());
        } catch (NumberFormatException incompleteCommand) {
            return new CommandInvalid(Ui.INVALID_INTEGER_INDEX_MSG + System.lineSeparator() +
                    CommandEdit.INVALID_FORMAT_MSG);
        } catch (ArrayIndexOutOfBoundsException incompleteCommand) {
            return new CommandInvalid(CommandEdit.MISSING_INDEX_MSG + System.lineSeparator() +
                    CommandEdit.INVALID_FORMAT_MSG);
        }
        try {
            editCriteria = editDetails[2].strip();
        } catch (ArrayIndexOutOfBoundsException incompleteCommand) {
            return new CommandInvalid(CommandEdit.MISSING_CRITERIA_MSG + System.lineSeparator() +
                    CommandEdit.INVALID_FORMAT_MSG);
        }
        try{
            switch (editCriteria){
            case "/description":
                newDescription = Parser.getContentAfterKeyword(userInput, "/description");
                break;
            case "/answer":
                newAnswer = Parser.getContentAfterKeyword(userInput, "/answer");
                break;
            default:
                throw new ArrayIndexOutOfBoundsException();
            }
        } catch (ArrayIndexOutOfBoundsException incompleteCommand) {
            return new CommandInvalid(CommandEdit.MISSING_KEYWORD_MSG + System.lineSeparator() +
                    CommandEdit.INVALID_FORMAT_MSG);
        }
        return new CommandEdit(qnIndex, newDescription, newAnswer);
    }

    /**
     * Attempt to parse user input into a Start Quiz Command
     *
     * @param userInput Raw command entered by the user
     * @return Start Quiz command or an Invalid Command
     */
    private static Command parseStartCommand(String userInput) {
        String startMode;
        String startDetails = "";
        String startQnMode;
        String[] commandDetails = userInput.split("/");
        String startInfo;
        try {
            startInfo = commandDetails[1];
            startMode = startInfo.split(" ")[0].strip();
        } catch (ArrayIndexOutOfBoundsException incompleteCommand) {
            return new CommandInvalid(CommandStart.MISSING_QUIZ_MODE_MSG + System.lineSeparator() +
                    CommandStart.INVALID_FORMAT_MSG);
        }
        try {
            if(!startMode.equalsIgnoreCase("all")){
                startDetails = startInfo.split(startMode)[1].strip();
                if(startDetails.equals("")){
                    return new CommandInvalid(CommandStart.MISSING_START_DETAILS + System.lineSeparator() +
                            CommandStart.INVALID_FORMAT_MSG);
                }
            }
        }  catch (ArrayIndexOutOfBoundsException incompleteCommand) {
            return new CommandInvalid(CommandStart.MISSING_START_DETAILS + System.lineSeparator() +
                    CommandStart.INVALID_FORMAT_MSG);
        }
        try {
            // Reads in /random or /normal
            startQnMode = commandDetails[2].split(" ")[0].strip();
            if (startQnMode.isEmpty()) {
                return new CommandInvalid(CommandStart.MISSING_QN_MODE_MSG + System.lineSeparator() +
                        CommandStart.INVALID_FORMAT_MSG);
            }
            if (!startQnMode.equals("random") && !startQnMode.equals("normal")) {
                throw new IllegalArgumentException(CommandStart.INVALID_MODE_MSG);
            }
            if(commandDetails[2].split(" ").length != 1){
                return new CommandInvalid(CommandStart.TOO_MANY_ARGUMENTS_MSG + System.lineSeparator() +
                        CommandStart.INVALID_FORMAT_MSG);
            }
        } catch (IllegalArgumentException e) {
            return new CommandInvalid(e.getMessage() + System.lineSeparator() +
                    CommandStart.INVALID_FORMAT_MSG);
        } catch (ArrayIndexOutOfBoundsException invalidIndex) {
            return new CommandInvalid(CommandStart.MISSING_QN_MODE_MSG + System.lineSeparator() +
                    CommandStart.INVALID_FORMAT_MSG);
        }
        return new CommandStart(startMode, startDetails, startQnMode);
    }

    /**
     * Attempt to parse user input into a Mark Difficulty Command
     *
     * @param userInput Raw command entered by the user
     * @return Mark Difficulty command or an Invalid Command
     */
    private static Command parseMarkDiffCommand(String userInput) {
        String[] commandDetails = userInput.split(" ");
        int qnIndex;
        Question.QnDifficulty qnDifficulty;
        String qnIndexString;
        String qnDifficultyString;
        try {
            qnIndexString  = commandDetails[1].strip();
        } catch (ArrayIndexOutOfBoundsException incompleteCommand) {
            return new CommandInvalid(CommandMarkDifficulty.MISSING_INDEX_MSG + System.lineSeparator() +
                    CommandMarkDifficulty.INVALID_FORMAT_MSG);
        }
        try {
            qnIndex = Integer.parseInt(qnIndexString);
            if(qnIndex < 0){
                return new CommandInvalid(Ui.INVALID_INTEGER_INDEX_MSG);
            }
        }  catch (NumberFormatException incompleteCommand) {
            return new CommandInvalid(Ui.INVALID_INTEGER_INDEX_MSG);
        }
        try {
            qnDifficultyString = commandDetails[2].strip();
        } catch (ArrayIndexOutOfBoundsException incompleteCommand) {
            return new CommandInvalid(CommandMarkDifficulty.MISSING_DIFFICULTY_MSG);
        }
        if(commandDetails.length != 3){
            return new CommandInvalid(CommandMarkDifficulty.TOO_MANY_ARGUMENTS_MSG + System.lineSeparator() +
                    CommandMarkDifficulty.INVALID_FORMAT_MSG);
        }
        qnDifficulty = Parser.extractQuestionDifficulty(qnDifficultyString);
        return new CommandMarkDifficulty(qnIndex, qnDifficulty);
    }
}
