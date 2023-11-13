package quizhub.parser;

import quizhub.command.Command;
import quizhub.command.CommandDelete;
import quizhub.command.CommandEdit;
import quizhub.command.CommandExit;
import quizhub.command.CommandFind;
import quizhub.command.CommandHelp;
import quizhub.command.CommandInvalid;
import quizhub.command.CommandList;
import quizhub.command.CommandMarkDifficulty;
import quizhub.command.CommandMultipleChoice;
import quizhub.command.CommandShortAnswer;
import quizhub.command.CommandShuffle;
import quizhub.command.CommandStart;
import quizhub.question.Question;
import quizhub.question.Question.QnType;
import quizhub.questionlist.QuestionList;
import quizhub.ui.Ui;
import quizhub.exception.QuizHubExceptions;

/**
 * Represents a parser that converts user inputs into command objects.
 */
public class Parser {
    private static QuestionList questions;

    public Parser(QuestionList questions) {
        Parser.questions = questions;
    }

    /**
     * Analyses and extracts relevant information from user input
     * to create a new Command object of the right type.
     *
     * @param rawUserInput The full user CLI input.
     * @return Command of the successfully parsed command or an InvalidCommand if
     *         unsuccessful
     */
    public static Command parseCommand(String rawUserInput) {
        String userInput = rawUserInput.strip();
        if (userInput.isEmpty()) {
            return new CommandInvalid(Ui.INVALID_COMMAND_MSG + System.lineSeparator() +
                    Ui.INVALID_COMMAND_FEEDBACK);
        }
        String[] commandTokens = userInput.split(" ");
        String commandTitle = commandTokens[0];
        try {
            switch (commandTitle) {
            case CommandExit.COMMAND_WORD:
                return new CommandExit();
            case CommandList.COMMAND_WORD:
                return new CommandList();
            case CommandShortAnswer.COMMAND_WORD:
                return parseShortAnswerCommand(userInput);
            case CommandMultipleChoice.COMMAND_WORD:
                return parseMCQCommand(userInput);
            case CommandStart.COMMAND_WORD:
                return parseStartCommand(userInput);
            case CommandEdit.COMMAND_WORD:
                return parseEditCommand(userInput);
            case CommandDelete.COMMAND_WORD:
                return parseDeleteCommand(userInput);
            case CommandFind.COMMAND_WORD:
                return parseFindCommand(userInput);
            case CommandShuffle.COMMAND_WORD:
                return new CommandShuffle();
            case CommandMarkDifficulty.COMMAND_WORD:
                return parseMarkDiffCommand(userInput);
            case CommandHelp.COMMAND_WORD:
                return new CommandHelp();
            default:
                return new CommandInvalid(Ui.INVALID_COMMAND_MSG + System.lineSeparator() +
                        Ui.INVALID_COMMAND_FEEDBACK);
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException invalidIndex) {
            return new CommandInvalid(Ui.INVALID_INTEGER_INDEX_MSG);
        } catch (Exception error) {
            return new CommandInvalid(error.getMessage() + System.lineSeparator()
                    + Ui.INVALID_COMMAND_FEEDBACK);
        }
    }

    /**
     * Extracts relevant information after a specified keyword
     * from CLI user input.
     *
     * @param userInput The full user CLI input.
     * @param keyWord   The keyword used to partition the user input.
     *
     * @return String after the specified keyword
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
     * @return QnDifficulty enumeration based on string, default as Normal
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
            return Question.QnDifficulty.INVALID;
        }
    }

    /**
     * Extracts the question index from raw user input for commands with arguments.
     *
     * @param userInput Raw command entered by the user
     * @return Integer index of the question
     */
    private static int extractQnIndex(String userInput, String commandType) throws IllegalArgumentException,
            ArrayIndexOutOfBoundsException, QuizHubExceptions {
        String editDetails = userInput.split(commandType)[1];
        String qnIndexString = editDetails.split("/")[0].strip();
        if (qnIndexString.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (qnIndexString.split(" ").length != 1) {
            throw new IllegalArgumentException();
        }
        int qnIndex = Integer.parseInt(qnIndexString);
        if (qnIndex <= 0) {
            throw new NumberFormatException();
        }
        if (questions.getQuestionByIndex(qnIndex) == null) {
            throw new QuizHubExceptions();
        } else {
            return qnIndex;
        }
    }

    /**
     * Attempt to parse user input into a MCQ Command by extracting question
     * description, 4 options,
     * answer index, module the question falls under, and level of difficulty from
     * the user input.
     *
     * @param userInput Raw command entered by the user
     * @return MCQ command or an Invalid Command
     */
    private static Command parseMCQCommand(String userInput) {
        assert userInput != null : "Invalid Null Command!";
        try {
            // Split the input by '/' to separate the parts
            String[] inputTokens = userInput.replace(
                    CommandMultipleChoice.COMMAND_WORD, "").strip().split("/");
            // Check if there are exactly 8 parts (description, 4 options, answer, module, difficulty)
            if (inputTokens.length > CommandMultipleChoice.ARGUMENT_SIZE) {
                return new CommandInvalid(CommandMultipleChoice.TOO_MANY_ARGUMENTS_MSG);
            }
            // Extract the values for description, options, answer, module, and difficulty
            String description = inputTokens[0].strip().replace("|", "");
            String option1 = inputTokens[1].strip().replace("|", "");
            String option2 = inputTokens[2].strip().replace("|", "");
            String option3 = inputTokens[3].strip().replace("|", "");
            String option4 = inputTokens[4].strip().replace("|", "");
            String answer = inputTokens[5].strip().replace("|", "");
            String module = inputTokens[6].strip().replace("|", "");
            String difficulty = inputTokens[7].strip();
            for (int i = 1; i < 5; i++) {
                for (int j = i + 1; j < 5; j++) {
                    if(inputTokens[i].strip().equalsIgnoreCase(inputTokens[j].strip())) {
                        return new CommandInvalid(CommandMultipleChoice.DUPLICATE_OPTION_MSG +
                                "\n" + CommandMultipleChoice.INVALID_FORMAT_MSG);
                    }
                }
            }
            boolean isFieldEmpty = description.isEmpty() || option1.isEmpty() || option2.isEmpty() ||
                    option3.isEmpty() || option4.isEmpty() || answer.isEmpty() || module.isEmpty() ||
                    difficulty.isEmpty();
            if (isFieldEmpty) {
                return new CommandInvalid(CommandMultipleChoice.MISSING_FIELDS_MSG +
                        "\n" + CommandMultipleChoice.INVALID_FORMAT_MSG);
            }
            int answerIndex = Integer.parseInt(answer);
            if (answerIndex < 1 || answerIndex > 4) {
                return new CommandInvalid(CommandMultipleChoice.INVALID_ANSWER_MSG);
            }
            Question.QnDifficulty qnDifficulty = extractQuestionDifficulty(difficulty);
            if (qnDifficulty.equals(Question.QnDifficulty.INVALID)) {
                return new CommandInvalid(CommandMultipleChoice.INVALID_DIFFICULTY_MSG);
            }
            return new CommandMultipleChoice(description, option1, option2, option3, option4,
                    Integer.parseInt(answer), module, extractQuestionDifficulty(difficulty));
        } catch (ArrayIndexOutOfBoundsException exception) {
            return new CommandInvalid(CommandMultipleChoice.INVALID_FORMAT_MSG);
        }
    }

    /**
     * Attempt to parse user input into a Short Answer Command by extracting
     * question description, answer,
     * module the question falls under, and level of difficulty from the user input.
     *
     * @param userInput Raw command entered by the user
     * @return Short Answer command or an Invalid Command
     */
    private static Command parseShortAnswerCommand(String userInput) {
        assert userInput != null : "Invalid Null Command!";
        try {
            String[] tokens = userInput.replace(
                    CommandShortAnswer.COMMAND_WORD, "").strip().split("/");
            if (tokens.length > CommandShortAnswer.ARGUMENT_SIZE) {
                return new CommandInvalid(CommandShortAnswer.TOO_MANY_ARGUMENTS_MSG);
            }
            // Extract the values for description, answer, module, and difficulty
            String description = tokens[0].strip().replace("|", "");
            boolean isFieldEmpty = description.isEmpty();
            String answer = tokens[1].strip().replace("|", "");
            if(answer.equals("\\exitquiz")){
                return new CommandInvalid(CommandShortAnswer.INVALID_EXITQUIZ);
            }
            isFieldEmpty = isFieldEmpty || answer.isEmpty();
            String module = tokens[2].strip().replace("|", "");
            isFieldEmpty = isFieldEmpty || module.isEmpty();
            String difficulty = tokens[3].strip();
            isFieldEmpty = isFieldEmpty || difficulty.isEmpty();
            if (isFieldEmpty) {
                return new CommandInvalid(CommandShortAnswer.MISSING_FIELDS_MSG +
                        "\n" + CommandShortAnswer.INVALID_FORMAT_MSG);
            }
            Question.QnDifficulty qnDifficulty = extractQuestionDifficulty(difficulty);
            if (qnDifficulty.equals(Question.QnDifficulty.INVALID)) {
                return new CommandInvalid(CommandShortAnswer.INVALID_DIFFICULTY_MSG);
            }
            return new CommandShortAnswer(description, answer, module, qnDifficulty);
        } catch (ArrayIndexOutOfBoundsException exception) {
            return new CommandInvalid(CommandShortAnswer.INVALID_FORMAT_MSG);
        }
    }

    /**
     * Attempt to parse user input into a Delete Command by extracting question
     * index
     * of question to be deleted.
     *
     * @param userInput Raw command entered by the user
     * @return Delete command or an Invalid Command
     */
    private static Command parseDeleteCommand(String userInput) {
        assert userInput != null : "Invalid Null Command!";
        int qnIndex;
        String[] editDetails;
        try {
            editDetails = userInput.split(" ");
            if (editDetails.length > 2) {
                throw new QuizHubExceptions();
            }
            qnIndex = Integer.parseInt(editDetails[1].strip());
            return new CommandDelete(qnIndex);
        } catch (NumberFormatException incompleteCommand) {
            return new CommandInvalid(Ui.INVALID_INTEGER_INDEX_MSG + System.lineSeparator() +
                    CommandDelete.INVALID_FORMAT_MSG);
        } catch (ArrayIndexOutOfBoundsException incompleteCommand) {
            return new CommandInvalid(CommandDelete.MISSING_INDEX_MSG + System.lineSeparator() +
                    CommandDelete.INVALID_FORMAT_MSG);
        } catch (QuizHubExceptions invalidCommand) {
            return new CommandInvalid(CommandDelete.EXCESSIVE_INDEX_MSG + System.lineSeparator() +
                    CommandDelete.INVALID_FORMAT_MSG);
        }
    }

    /**
     * Attempt to parse user input into a Find Command by extracting search
     * condition/keyword from user input.
     *
     * @param userInput Raw command entered by the user
     * @return Find command or an Invalid Command
     */
    private static Command parseFindCommand(String userInput) {
        assert userInput != null : "Invalid Null Command!";
        String searchCriteria;
        String searchKeyword;
        try {
            searchCriteria = userInput.split("/")[1].strip().split(" ")[0].strip();
        } catch (ArrayIndexOutOfBoundsException incompleteCommand) {
            return new CommandInvalid(CommandFind.MISSING_CRITERIA_MSG + System.lineSeparator() +
                    CommandFind.INVALID_FORMAT_MSG);
        }
        try {
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
        assert userInput != null : "Invalid Null Command!";
        String[] commandEditTokens = new String[2];
        int qnIndex;
        try {
            qnIndex = extractQnIndex(userInput, "edit");
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException | QuizHubExceptions incorrectQnIndex) {
            return handleEditIndexExceptions(incorrectQnIndex);
        }
        try {
            extractEditCriteria(userInput, qnIndex, commandEditTokens);
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException incorrectEditCriteria) {
            return handleEditCriteriaExceptions(incorrectEditCriteria);
        }
        try {
            extractEditNewValues(userInput, commandEditTokens, qnIndex);
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException | QuizHubExceptions invalidEditCriteria) {
            return handleEditNewValuesExceptions(invalidEditCriteria);
        }
        String editField = commandEditTokens[0];
        String newValue = commandEditTokens[1].replace("|", "").replace("/", "");
        if (newValue.isEmpty()) {
            return new CommandInvalid(CommandEdit.MISSING_KEYWORD_MSG);
        }
        return new CommandEdit(qnIndex, editField, newValue);
    }

    /**
     * Extracts the edit criteria from raw user input for edit commands.
     * Respective information is extracted into commandEditTokens.
     *
     * @param userInput         Raw command entered by the user
     * @param commandEditTokens Critical information chunks of edit command
     *                          commandEditTokens[0] contains edit field
     *                          commandEditTokens[1] contains new value to change to
     * @param qnIndex           Index of question to be edited
     */
    private static void extractEditCriteria(String userInput, int qnIndex, String[] commandEditTokens)
            throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        String[] inputSplitByCriteria = userInput.split("/");
        QnType qnType = questions.getQuestionByIndex(qnIndex).getQuestionType();
        String editDetails = inputSplitByCriteria[1].strip();
        String editField = editDetails.split(" ")[0].strip();
        if (editField.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (inputSplitByCriteria.length != 2) {
            throw new IllegalArgumentException("Too Many Criteria");
        } else if (!editField.equals("description") && !editField.equals("answer")
                && !editField.equals("option1")
                && !editField.equals("option2") && !editField.equals("option3")
                && !editField.equals("option4")) {
            throw new IllegalArgumentException("Invalid Criteria");
        } else if (qnType.equals(QnType.SHORTANSWER) && (editField.equals("option1")
                || editField.equals("option2") || editField.equals("option3")
                || editField.equals("option4"))) {
            throw new IllegalArgumentException("Invalid Edit Short Answer Question Criteria");
        } else {
            commandEditTokens[0] = editField;
        }
    }

    /**
     * Extracts the information to edit the indicated question with from raw user
     * input for edit commands.
     * Respective information is extracted into commandEditTokens.
     *
     * @param userInput         Raw command entered by the user
     * @param commandEditTokens Critical information chunks of edit command
     *                          commandEditTokens[0] contains edit field
     *                          commandEditTokens[1] contains new value to change to
     * @param qnIndex           Index of question to be edited
     */
    private static void extractEditNewValues(String userInput, String[] commandEditTokens, int qnIndex)
            throws IllegalArgumentException, QuizHubExceptions, ArrayIndexOutOfBoundsException {
        Question qn = questions.getQuestionByIndex(qnIndex);
        String newVal;
        switch (commandEditTokens[0]) {
        case "description":
            newVal = Parser.getContentAfterKeyword(userInput, "/description");
            if (newVal.equals(qn.getQuestionBody())) {
                throw new QuizHubExceptions();
            }
            break;
        case "answer":
            newVal = Parser.getContentAfterKeyword(userInput, "/answer");
            if (newVal.equals(qn.getQuestionAnswer())) {
                throw new QuizHubExceptions();
            }
            if (newVal.equals("\\exitquiz")) {
                throw new IllegalArgumentException("    Invalid new value: \\exitquiz");
            }
            if (qn.getQuestionType().equals(QnType.MULTIPLECHOICE)) {
                try {
                    int newAnswer = Integer.parseInt(newVal);
                    if (newAnswer < 1 || newAnswer > 4) {
                        throw new IllegalArgumentException("Invalid Integer Answer");
                    }
                } catch (Exception e) {
                    throw new IllegalArgumentException("Invalid Integer Answer");
                }
            }
            break;
        case "option1":
            newVal = Parser.getContentAfterKeyword(userInput, "/option1");
            if (newVal.equals(qn.getOption(1))) {
                throw new QuizHubExceptions();
            }
            break;
        case "option2":
            newVal = Parser.getContentAfterKeyword(userInput, "/option2");
            if (newVal.equals(qn.getOption(2))) {
                throw new QuizHubExceptions();
            }
            break;
        case "option3":
            newVal = Parser.getContentAfterKeyword(userInput, "/option3");
            if (newVal.equals(qn.getOption(3))) {
                throw new QuizHubExceptions();
            }
            break;
        case "option4":
            newVal = Parser.getContentAfterKeyword(userInput, "/option4");
            if (newVal.equals(qn.getOption(4))) {
                throw new QuizHubExceptions();
            }
            break;
        default:
            throw new IllegalArgumentException();
        }
        commandEditTokens[1] = newVal;
    }
    /**
     * Handles exceptions raised by incorrect edit criteria for edit commands.
     *
     * @param editCriteriaException Exception raised by the program
     * @return Invalid command object with different error messages
     */
    private static Command handleEditCriteriaExceptions(Exception editCriteriaException) {
        if (editCriteriaException instanceof ArrayIndexOutOfBoundsException) {
            return new CommandInvalid(CommandEdit.MISSING_CRITERIA_MSG + System.lineSeparator() +
                    CommandEdit.INVALID_FORMAT_MSG);
        } else if (editCriteriaException instanceof IllegalArgumentException) {
            switch (editCriteriaException.getMessage()) {
            case "Invalid Criteria":
                return new CommandInvalid(CommandEdit.INVALID_CRITERIA_MSG + System.lineSeparator() +
                        CommandEdit.INVALID_FORMAT_MSG);
            case "Too Many Criteria":
                return new CommandInvalid(CommandEdit.TOO_MANY_CRITERIA_MSG + System.lineSeparator() +
                        CommandEdit.INVALID_FORMAT_MSG);
            case "Invalid Edit Short Answer Question Criteria":
                return new CommandInvalid(CommandEdit.INVALID_SHORT_ANSWER_CRITERIA_MSG + System.lineSeparator() +
                        CommandEdit.INVALID_FORMAT_MSG);
            default:
                return new CommandInvalid(CommandEdit.INVALID_FORMAT_MSG);
            }
        } else {
            return new CommandInvalid(CommandEdit.INVALID_FORMAT_MSG);
        }
    }

    /**
     * Handles exceptions raised by incorrect question index for edit commands.
     *
     * @param editIndexException Exception raised by the program
     * @return Invalid command with different error messages
     */
    private static Command handleEditIndexExceptions(Exception editIndexException) {
        if (editIndexException instanceof NumberFormatException) {
            return new CommandInvalid(Ui.INVALID_INTEGER_INDEX_MSG + System.lineSeparator() +
                    CommandEdit.INVALID_FORMAT_MSG);
        } else if (editIndexException instanceof ArrayIndexOutOfBoundsException) {
            return new CommandInvalid(CommandEdit.MISSING_INDEX_MSG + System.lineSeparator() +
                    CommandEdit.INVALID_FORMAT_MSG);
        } else if (editIndexException instanceof IllegalArgumentException) {
            return new CommandInvalid(CommandEdit.TOO_MANY_INDEX_MSG + System.lineSeparator() +
                    CommandEdit.INVALID_FORMAT_MSG);
        } else if (editIndexException instanceof QuizHubExceptions) {
            return new CommandInvalid(CommandEdit.INDEX_NOT_IN_RANGE_MSG + System.lineSeparator() +
                    CommandEdit.INVALID_FORMAT_MSG);
        } else {
            return new CommandInvalid(CommandEdit.INVALID_FORMAT_MSG);
        }
    }

    /**
     * Handles exceptions raised by incorrect edit values for edit commands.
     *
     * @param editValuesException Exception raised by the program
     * @return InvalidCommand with error messages
     */
    private static Command handleEditNewValuesExceptions(Exception editValuesException) {
        if (editValuesException instanceof IllegalArgumentException || 
            editValuesException instanceof ArrayIndexOutOfBoundsException) {
            if(editValuesException.getMessage().equals("    Invalid new value: \\exitquiz")){
                return new CommandInvalid(editValuesException.getMessage() + System.lineSeparator() +
                        CommandEdit.INVALID_FORMAT_MSG);
            }
            if (editValuesException.getMessage().equals("Invalid Integer Answer")) {
                return new CommandInvalid(CommandMultipleChoice.INVALID_ANSWER_MSG + System.lineSeparator() +
                        CommandEdit.INVALID_FORMAT_MSG);
            }else {
                return new CommandInvalid(CommandEdit.MISSING_KEYWORD_MSG + System.lineSeparator() +
                    CommandEdit.INVALID_FORMAT_MSG);
            }
        } else if (editValuesException instanceof QuizHubExceptions) {
            return new CommandInvalid(CommandEdit.NO_CHANGES_MADE_MSG + System.lineSeparator() +
                    CommandEdit.INVALID_FORMAT_MSG);
        } else {
            return new CommandInvalid(CommandEdit.INVALID_FORMAT_MSG);
        }
    }

    /**
     * Attempt to parse user input into a Start Quiz Command by extracting quiz mode
     * and question mode
     * from the user input to initialise question set for the quiz.
     *
     * @param userInput Raw command entered by the user
     * @return Start Quiz command or an Invalid Command
     */
    private static Command parseStartCommand(String userInput) {
        String[] commandStartTokens = new String[CommandStart.NUM_ARGUMENTS];
        try {
            extractQuizMode(userInput, commandStartTokens);
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException incorrectQuizMode) {
            return handleQuizModeExceptions(incorrectQuizMode);
        }
        try {
            extractQuizStartDetails(userInput, commandStartTokens);
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException incorrectQuizDetails) {
            return handleQuizStartDetailsExceptions(incorrectQuizDetails);
        }
        try {
            extractQuizQnMode(userInput, commandStartTokens);
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException incorrectQnMode) {
            return handleQuizQnModeExceptions(incorrectQnMode);
        }
        try{
            extractQuizQnType(userInput, commandStartTokens);
        }catch (ArrayIndexOutOfBoundsException | IllegalArgumentException incorrectQnType){
            return handleQnTypeExceptions(incorrectQnType);
        }

        String startMode = commandStartTokens[0];
        String startDetails = commandStartTokens[1];
        String startQnMode = commandStartTokens[2];
        String startQnType = commandStartTokens[3];
        return new CommandStart(startMode, startDetails, startQnMode, startQnType);
    }

    /**
     * Extracts the quiz mode from raw user input for start commands.
     * Respective information is extracted into commandStartTokens.
     *
     * @param userInput          Raw command entered by the user
     * @param commandStartTokens Critical information chunks of start command
     *                           commandEditTokens[0] contains quiz mode
     *                           commandEditTokens[1] contains question selection
     *                           details
     *                           commandEditTokens[2] contains question mode
     */
    private static void extractQuizMode(String userInput, String[] commandStartTokens)
            throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        String[] inputSplitByArguments = userInput.toLowerCase().split("/");
        String quizStartInfo = inputSplitByArguments[1].strip();
        String quizMode = quizStartInfo.split(" ")[0].strip();
        if (quizMode.equals("")) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (!quizMode.equals("all") && !quizMode.equals("module")) {
            throw new IllegalArgumentException();
        } else {
            commandStartTokens[0] = quizMode;
        }
    }

    /**
     * Handles exceptions raised by incorrect quiz mode for start commands.
     *
     * @param quizModeException Exception raised by the program
     */
    private static Command handleQuizModeExceptions(Exception quizModeException) {
        if (quizModeException instanceof ArrayIndexOutOfBoundsException) {
            return new CommandInvalid(CommandStart.MISSING_QUIZ_MODE_MSG + System.lineSeparator() +
                    CommandStart.INVALID_FORMAT_MSG);
        } else if (quizModeException instanceof IllegalArgumentException) {
            return new CommandInvalid(CommandStart.INVALID_QUIZ_MODE_MSG + System.lineSeparator() +
                    CommandStart.INVALID_FORMAT_MSG);
        } else {
            return new CommandInvalid(CommandEdit.INVALID_FORMAT_MSG);
        }
    }

    /**
     * Extracts the quiz start details from raw user input to select quiz questions
     * for start commands.
     * Respective information is extracted into commandStartTokens.
     *
     * @param userInput          Raw command entered by the user
     * @param commandStartTokens Critical information chunks of start command
     *                           commandEditTokens[0] contains quiz mode
     *                           commandEditTokens[1] contains question selection
     *                           details
     *                           commandEditTokens[2] contains question mode
     */
    private static void extractQuizStartDetails(String userInput, String[] commandStartTokens)
            throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        String quizStartDetails;
        if (commandStartTokens[0] == null || commandStartTokens[0].equals("")) {
            throw new IllegalArgumentException();
        }
        if (!commandStartTokens[0].equals("all")) {
            String[] inputSplitByQuizMode = userInput.toLowerCase().split("/");
            String quizStartInfo = inputSplitByQuizMode[1].strip();
            quizStartDetails = quizStartInfo.split(commandStartTokens[0])[1].strip();
            if (!commandStartTokens[0].equals("all") && quizStartDetails.isEmpty()) {
                throw new ArrayIndexOutOfBoundsException();
            }
        } else {
            quizStartDetails = "";
        }
        commandStartTokens[1] = quizStartDetails;
    }

    /**
     * Handles exceptions raised by incorrect quiz start details for start commands.
     *
     * @param quizStartDetailsException Exception raised by the program
     */
    private static Command handleQuizStartDetailsExceptions(Exception quizStartDetailsException) {
        if (quizStartDetailsException instanceof ArrayIndexOutOfBoundsException) {
            return new CommandInvalid(CommandStart.MISSING_START_DETAILS + System.lineSeparator() +
                    CommandStart.INVALID_FORMAT_MSG);
        } else if (quizStartDetailsException instanceof IllegalArgumentException) {
            return new CommandInvalid(CommandStart.INVALID_QUIZ_MODE_MSG + System.lineSeparator() +
                    CommandStart.INVALID_FORMAT_MSG);
        } else {
            return new CommandInvalid(CommandEdit.INVALID_FORMAT_MSG);
        }
    }

    /**
     * Extracts the quiz question mode from raw user input for start commands.
     * Respective information is extracted into commandStartTokens.
     *
     * @param userInput          Raw command entered by the user
     * @param commandStartTokens Critical information chunks of start command
     *                           commandEditTokens[0] contains quiz mode
     *                           commandEditTokens[1] contains question selection
     *                           details
     *                           commandEditTokens[2] contains question mode
     */
    private static void extractQuizQnMode(String userInput, String[] commandStartTokens)
            throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        String[] inputSplitByArguments = userInput.toLowerCase().split("/");
        String qnMode = inputSplitByArguments[2].strip();
        if (qnMode.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (inputSplitByArguments[2].split(" ").length != 1) {
            throw new IllegalArgumentException("Too Many Modes");
        } else if (!qnMode.equals("random") && !qnMode.equals("normal")) {
            throw new IllegalArgumentException("Invalid Mode");
        } else if (inputSplitByArguments.length > CommandStart.NUM_ARGUMENTS) {
            throw new IllegalArgumentException("Too Many Arguments");
        } else {
            commandStartTokens[2] = qnMode;
        }
    }

    /**
     * Handles exceptions raised by incorrect edit criteria for edit commands.
     *
     * @param editCriteriaException Exception raised by the program
     */
    private static Command handleQuizQnModeExceptions(Exception editCriteriaException) {
        if (editCriteriaException instanceof ArrayIndexOutOfBoundsException) {
            return new CommandInvalid(CommandStart.MISSING_QN_MODE_MSG + System.lineSeparator() +
                    CommandStart.INVALID_FORMAT_MSG);
        } else if (editCriteriaException instanceof IllegalArgumentException) {
            if (editCriteriaException.getMessage().equals("Invalid Mode")) {
                return new CommandInvalid(CommandStart.INVALID_QN_MODE_MSG + System.lineSeparator() +
                        CommandStart.INVALID_FORMAT_MSG);
            } else {
                return new CommandInvalid(CommandStart.TOO_MANY_ARGUMENTS_MSG + System.lineSeparator() +
                        CommandStart.INVALID_FORMAT_MSG);
            }
        } else {
            return new CommandInvalid(CommandEdit.INVALID_FORMAT_MSG);
        }
    }
    /**
     * Extracts the question type from the user input and stores it in the commandStartTokens array.
     * The question type is expected to be the fourth token in the command when split by slashes.
     * This method validates the question type and throws exceptions if it is missing or invalid.
     *
     * @param userInput          The full user input string.
     * @param commandStartTokens The array where extracted command tokens are stored.
     * @throws ArrayIndexOutOfBoundsException if the question type argument is missing.
     * @throws IllegalArgumentException       if the question type argument is not one of the expected values.
     */
    private static void extractQuizQnType(String userInput, String[] commandStartTokens)
            throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        String[] inputSplitBySlash = userInput.toLowerCase().split("/");
        if (inputSplitBySlash.length > CommandStart.NUM_ARGUMENTS) {
            throw new IllegalArgumentException("Invalid command: Extra input detected after question type.");
        }
        if (inputSplitBySlash.length < CommandStart.NUM_ARGUMENTS) {
            throw new ArrayIndexOutOfBoundsException("Missing question type for the quiz.");
        }
        String[] inputSplitBySpaceAfterSlash = inputSplitBySlash[CommandStart.NUM_ARGUMENTS - 1].split(" ");
        if (inputSplitBySpaceAfterSlash.length > 1) {
            throw new IllegalArgumentException("Invalid command: Extra input detected after question type.");
        }
        String qnType = inputSplitBySpaceAfterSlash[0].strip().toLowerCase();
        if (!qnType.equals("short") && !qnType.equals("mcq") && !qnType.equals("mix")) {
            throw new IllegalArgumentException("Invalid question type for the quiz.");
        }
        commandStartTokens[3] = qnType;
    }

    /**
     * Handles exceptions related to question type extraction for the quiz start command.
     * This method generates a CommandInvalid object with an appropriate error message based on the exception.
     *
     * @param qnTypeException The exception thrown during question type extraction.
     * @return CommandInvalid containing the error message for the user.
     */
    private static Command handleQnTypeExceptions(Exception qnTypeException) {

        if (qnTypeException instanceof ArrayIndexOutOfBoundsException) {
            return new CommandInvalid(CommandStart.INVALID_QN_TYPE_MSG);
        } else if (qnTypeException instanceof IllegalArgumentException) {
            return new CommandInvalid("    " + qnTypeException.getMessage() + System.lineSeparator() +
                    CommandStart.INVALID_QN_TYPE_MSG);
        } else {
            return new CommandInvalid(CommandEdit.INVALID_FORMAT_MSG);
        }
    }

    /**
     * Attempt to parse user input into a Mark Difficulty Command by extracting
     * question index
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
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException | QuizHubExceptions incorrectQnIndex) {
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
     * Extracts the question difficulty to be assigned from raw user input for
     * markdiff commands.
     *
     * @param userInput Raw command entered by the user
     * @return QnDifficulty of Question Difficulty of Question
     */
    private static Question.QnDifficulty extractNewDifficulty(String userInput)
            throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        String[] inputSplitByQnDifficulty = userInput.split("/");
        String qnDifficultyString = inputSplitByQnDifficulty[1].strip();
        if (qnDifficultyString.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (qnDifficultyString.split(" ").length != 1 || inputSplitByQnDifficulty.length != 2) {
            throw new IllegalArgumentException();
        } else {
            return extractQuestionDifficulty(qnDifficultyString);
        }
    }

    /**
     * Handles exceptions raised by incorrect question difficulty for markdiff
     * commands.
     *
     * @param qnDifficultyException Exception raised by the program
     * @return InvalidCommand with error messages
     */
    private static Command handleQnDifficultyExceptions(Exception qnDifficultyException) {
        if (qnDifficultyException instanceof ArrayIndexOutOfBoundsException) {
            return new CommandInvalid(CommandMarkDifficulty.MISSING_DIFFICULTY_MSG + System.lineSeparator() +
                    CommandMarkDifficulty.INVALID_FORMAT_MSG);
        } else if (qnDifficultyException instanceof IllegalArgumentException) {
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
     * @return InvalidCommand with error messages
     */
    private static Command handleMarkDiffIndexExceptions(Exception markDiffIndexException) {
        if (markDiffIndexException instanceof NumberFormatException) {
            return new CommandInvalid(Ui.INVALID_INTEGER_INDEX_MSG + System.lineSeparator() +
                    CommandMarkDifficulty.INVALID_FORMAT_MSG);
        } else if (markDiffIndexException instanceof ArrayIndexOutOfBoundsException) {
            return new CommandInvalid(CommandMarkDifficulty.MISSING_INDEX_MSG + System.lineSeparator() +
                    CommandMarkDifficulty.INVALID_FORMAT_MSG);
        } else if (markDiffIndexException instanceof IllegalArgumentException) {
            return new CommandInvalid(CommandMarkDifficulty.TOO_MANY_INDEX_MSG + System.lineSeparator() +
                    CommandMarkDifficulty.INVALID_FORMAT_MSG);
        } else if (markDiffIndexException instanceof QuizHubExceptions) {
            return new CommandInvalid(CommandMarkDifficulty.INDEX_NOT_IN_RANGE_MSG + System.lineSeparator() +
                    CommandMarkDifficulty.INVALID_FORMAT_MSG);
        } else {
            return new CommandInvalid(CommandEdit.INVALID_FORMAT_MSG);
        }
    }
}
