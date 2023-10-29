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
     * Extracts the question index from raw user input for commands with arguments.
     *
     * @param userInput Raw command entered by the user
     */
    private static int extractQnIndex(String userInput, String commandType) throws IllegalArgumentException,
            ArrayIndexOutOfBoundsException {
        String editDetails = userInput.split(commandType)[1];
        String qnIndexString  = editDetails.split("/")[0].strip();
        if(qnIndexString.equals("")){
            throw new ArrayIndexOutOfBoundsException();
        }
        if(qnIndexString.split(" ").length != 1) {
            throw new IllegalArgumentException();
        }
        int qnIndex  = Integer.parseInt(qnIndexString);
        if(qnIndex <= 0){
            throw new NumberFormatException();
        } else {
            return qnIndex;
        }
    }
    /**
     * Attempt to parse user input into a Short Answer Command by extracting question description, answer,
     * module the question falls under, and level of difficulty from the user input.
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
                        "\n" + CommandShortAnswer.INVALID_FORMAT_MSG);
            }

            Question.QnDifficulty qnDifficulty = extractQuestionDifficulty(difficulty);
            if(qnDifficulty.equals(Question.QnDifficulty.INVALID)) {
                return new CommandInvalid(CommandShortAnswer.INVALID_DIFFICULTY_MSG);
            }
            return new CommandShortAnswer(description, answer, module, qnDifficulty);

        } catch (ArrayIndexOutOfBoundsException exception) {
            return new CommandInvalid(CommandShortAnswer.INVALID_FORMAT_MSG);
        }
    }

    /**
     * Attempt to parse user input into a Delete Command by extracting question index
     * of question to be deleted.
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
     * Attempt to parse user input into a Find Command by extracting search condition/keyword from user input.
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
     * Attempt to parse user input into an Edit Command by extracting question index
     * and question details to be edited from the user input.
     *
     * @param userInput Raw command entered by the user
     * @return Edit command or an Invalid Command
     */
    private static Command parseEditCommand(String userInput) {
        String[] commandEditTokens = new String[3];
        int qnIndex;
        try {
            qnIndex = extractQnIndex(userInput, "edit");
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException incorrectQnIndex) {
            return handleEditIndexExceptions(incorrectQnIndex);
        }
        try{
            extractEditCriteria(userInput, commandEditTokens);
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException incorrectEditCriteria) {
            return handleEditCriteriaExceptions(incorrectEditCriteria);
        }
        try{
            extractEditNewValues(userInput, commandEditTokens);
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException invalidEditCriteria) {
            return handleEditNewValuesExceptions(invalidEditCriteria);
        }
        String newDescription = commandEditTokens[1];
        String newAnswer = commandEditTokens[2];
        return new CommandEdit(qnIndex, newDescription, newAnswer);
    }
    /**
     * Extracts the edit criteria from raw user input for edit commands.
     * Respective information is extracted into commandEditTokens.
     *
     * @param userInput Raw command entered by the user
     * @param commandEditTokens Critical information chunks of edit command
     *                          commandEditTokens[0] contains edit criteria
     *                          commandEditTokens[1] contains new question description to change to (if any)
     *                          commandEditTokens[2] contains new question answer to change to (if any)
     */
    private static void extractEditCriteria(String userInput, String[] commandEditTokens)
            throws ArrayIndexOutOfBoundsException, IllegalArgumentException{
        String[] inputSplitByCriteria = userInput.split("/");
        String editDetails = inputSplitByCriteria[1].strip();
        String editCriteria = editDetails.split(" ")[0].strip();
        if(editCriteria.equals("")){
            throw new ArrayIndexOutOfBoundsException();
        } else if (inputSplitByCriteria.length != 2) {
            throw new IllegalArgumentException();
        } else {
            commandEditTokens[0] = editCriteria;
        }
    }
    /**
     * Extracts the information to edit the indicated question with from raw user input for edit commands.
     * Respective information is extracted into commandEditTokens.
     *
     * @param userInput Raw command entered by the user
     * @param commandEditTokens Critical information chunks of edit command
     *                          commandEditTokens[0] contains edit criteria
     *                          commandEditTokens[1] contains new question description to change to (if any)
     *                          commandEditTokens[2] contains new question answer to change to (if any)
     */
    private static void extractEditNewValues(String userInput, String[] commandEditTokens)
            throws IllegalArgumentException{
        switch (commandEditTokens[0]){
        case "description":
            commandEditTokens[1] = Parser.getContentAfterKeyword(userInput, "/description");
            break;
        case "answer":
            commandEditTokens[2] = Parser.getContentAfterKeyword(userInput, "/answer");
            break;
        default:
            throw new IllegalArgumentException();
        }
    }
    /**
     * Handles exceptions raised by incorrect edit criteria for edit commands.
     *
     * @param editCriteriaException Exception raised by the program
     */
    private static Command handleEditCriteriaExceptions(Exception editCriteriaException){
        if(editCriteriaException instanceof ArrayIndexOutOfBoundsException) {
            return new CommandInvalid(CommandEdit.MISSING_CRITERIA_MSG + System.lineSeparator() +
                    CommandEdit.INVALID_FORMAT_MSG);
        } else if(editCriteriaException instanceof IllegalArgumentException) {
            return new CommandInvalid(CommandEdit.TOO_MANY_CRITERIA_MSG + System.lineSeparator() +
                    CommandEdit.INVALID_FORMAT_MSG);
        } else {
            return new CommandInvalid(CommandEdit.INVALID_FORMAT_MSG);
        }
    }
    /**
     * Handles exceptions raised by incorrect question index for edit commands.
     *
     * @param editIndexException Exception raised by the program
     */
    private static Command handleEditIndexExceptions(Exception editIndexException){
        if(editIndexException instanceof NumberFormatException) {
            return new CommandInvalid(Ui.INVALID_INTEGER_INDEX_MSG + System.lineSeparator() +
                    CommandEdit.INVALID_FORMAT_MSG);
        } else if(editIndexException instanceof ArrayIndexOutOfBoundsException) {
            return new CommandInvalid(CommandEdit.MISSING_INDEX_MSG + System.lineSeparator() +
                    CommandEdit.INVALID_FORMAT_MSG);
        } else if(editIndexException instanceof IllegalArgumentException) {
            return new CommandInvalid(CommandEdit.TOO_MANY_INDEX_MSG + System.lineSeparator() +
                    CommandEdit.INVALID_FORMAT_MSG);
        } else {
            return new CommandInvalid(CommandEdit.INVALID_FORMAT_MSG);
        }
    }
    /**
     * Handles exceptions raised by incorrect edit values for edit commands.
     *
     * @param editValuesException Exception raised by the program
     */
    private static Command handleEditNewValuesExceptions(Exception editValuesException){
        if(editValuesException instanceof IllegalArgumentException ||
                editValuesException instanceof ArrayIndexOutOfBoundsException) {
            return new CommandInvalid(CommandEdit.MISSING_KEYWORD_MSG + System.lineSeparator() +
                    CommandEdit.INVALID_FORMAT_MSG);
        } else {
            return new CommandInvalid(CommandEdit.INVALID_FORMAT_MSG);
        }
    }

    /**
     * Attempt to parse user input into a Start Quiz Command by extracting quiz mode and question mode
     * from the user input to initialise question set for the quiz.
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
     * Attempt to parse user input into a Mark Difficulty Command by extracting question index
     * and question difficulty to be assigned to the question from the user input.
     *
     * @param userInput Raw command entered by the user
     * @return Mark Difficulty command or an Invalid Command
     */
    private static Command parseMarkDiffCommand(String userInput) {
        int qnIndex;
        Question.QnDifficulty qnDifficulty;
        try {
            qnIndex = extractQnIndex(userInput, "markdiff");
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException incorrectQnIndex) {
            return handleMarkDiffIndexExceptions(incorrectQnIndex);
        }
        try {
            qnDifficulty = extractNewDifficulty(userInput);
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException incorrectQnDifficulty) {
            return handleQnDifficultyExceptions(incorrectQnDifficulty);
        }
        return new CommandMarkDifficulty(qnIndex, qnDifficulty);
    }
    /**
     * Extracts the question difficulty to be assigned from raw user input for markdiff commands.
     *
     * @param userInput Raw command entered by the user
     */
    private static Question.QnDifficulty extractNewDifficulty(String userInput)
            throws ArrayIndexOutOfBoundsException, IllegalArgumentException{
        String[] inputSplitByQnDifficulty = userInput.split("/");
        String qnDifficultyString = inputSplitByQnDifficulty[1].strip();
        if(qnDifficultyString.equals("")){
            throw new ArrayIndexOutOfBoundsException();
        } else if (qnDifficultyString.split(" ").length != 1 ||inputSplitByQnDifficulty.length != 2) {
            throw new IllegalArgumentException();
        } else {
            return extractQuestionDifficulty(qnDifficultyString);
        }
    }
    /**
     * Handles exceptions raised by incorrect question difficulty for markdiff commands.
     *
     * @param qnDifficultyException Exception raised by the program
     */
    private static Command handleQnDifficultyExceptions(Exception qnDifficultyException){
        if(qnDifficultyException instanceof ArrayIndexOutOfBoundsException) {
            return new CommandInvalid(CommandMarkDifficulty.MISSING_DIFFICULTY_MSG + System.lineSeparator() +
                    CommandMarkDifficulty.INVALID_FORMAT_MSG);
        } else if(qnDifficultyException instanceof IllegalArgumentException) {
            return new CommandInvalid(CommandMarkDifficulty.TOO_MANY_DIFFICULTY_MSG + System.lineSeparator() +
                    CommandMarkDifficulty.INVALID_FORMAT_MSG);
        } else {
            return new CommandInvalid(CommandEdit.INVALID_FORMAT_MSG);
        }
    }
    /**
     * Handles exceptions raised by incorrect question index for markdiff commands.
     *
     * @param markDiffIndexException Exception raised by the program
     */
    private static Command handleMarkDiffIndexExceptions(Exception markDiffIndexException){
        if(markDiffIndexException instanceof NumberFormatException) {
            return new CommandInvalid(Ui.INVALID_INTEGER_INDEX_MSG + System.lineSeparator() +
                    CommandMarkDifficulty.INVALID_FORMAT_MSG);
        } else if(markDiffIndexException instanceof ArrayIndexOutOfBoundsException) {
            return new CommandInvalid(CommandMarkDifficulty.MISSING_INDEX_MSG + System.lineSeparator() +
                    CommandMarkDifficulty.INVALID_FORMAT_MSG);
        } else if(markDiffIndexException instanceof IllegalArgumentException) {
            return new CommandInvalid(CommandMarkDifficulty.TOO_MANY_INDEX_MSG + System.lineSeparator() +
                    CommandMarkDifficulty.INVALID_FORMAT_MSG);
        } else {
            return new CommandInvalid(CommandEdit.INVALID_FORMAT_MSG);
        }
    }
}
