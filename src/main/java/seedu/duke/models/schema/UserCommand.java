package seedu.duke.models.schema;

import seedu.duke.models.logic.Api;
import seedu.duke.utils.Parser;
import seedu.duke.utils.errors.UserError;

import static seedu.duke.utils.Parser.parseArguments;
import static seedu.duke.utils.Parser.parseCommand;
import static seedu.duke.views.CommandLineView.displayCommands;

import static seedu.duke.controllers.ModuleMethodsController.executePaceCommand;
import static seedu.duke.controllers.ModuleMethodsController.showModulesLeft;
import static seedu.duke.controllers.ModuleMethodsController.determinePrereq;
import static seedu.duke.controllers.ModuleMethodsController.executeRecommendCommand;
import static seedu.duke.controllers.ModuleMethodsController.executeAddModuleCommand;
import static seedu.duke.controllers.ModuleMethodsController.executeDeleteModuleCommand;
import static seedu.duke.controllers.ModuleMethodsController.executeShiftModuleCommand;
import static seedu.duke.controllers.ModuleMethodsController.executeClearScheduleCommand;
import static seedu.duke.controllers.ModuleMethodsController.completeModule;
import static seedu.duke.controllers.ModuleMethodsController.executeGetRequiredModulesForStudent;

/**
 * The UserCommand class represents a command entered by the user and provides methods to process and execute the
 * command.
 */
public class UserCommand implements UserCommandWord {

    //@@author janelleenqi
    private final String userInput;
    private final String commandWord;
    private final String[] arguments;
    private final boolean isValid;

    private CommandManager commandManager;

    /**
     * Constructs a UserCommand object with the specified user input and validates it.
     *
     * @param userInput The user input representing the command.
     */
    public UserCommand(String userInput) {
        this.userInput = userInput.trim();
        this.commandManager = new CommandManager();

        commandWord = parseCommand(this.userInput);
        arguments = parseArguments(this.userInput);


        if (!commandManager.getListOfCommandNames().contains(commandWord)){
            UserError.displayInvalidInputCommand(commandWord);
            isValid = false;
            return;
        }

        boolean validArgument = Parser.isValidInputForCommand(commandWord, arguments);

        if (!validArgument) {
            UserError.displayInvalidMethodCommand(commandWord);
            isValid = false;
            return;
        }

        isValid = true;
    }

    public UserCommand() {
        userInput = null;
        commandWord = null;
        arguments = null;
        isValid = false;
    }

    /**
     * Checks if the UserCommand is valid.
     *
     * @return true if the command is valid, false otherwise.
     */
    public boolean isValid() {
        return isValid;
    }

    public String getUserInput() {
        return userInput;
    }

    public String getCommandWord() {
        return commandWord;
    }

    public String[] getArguments() {
        return arguments;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Checks if the command is an close program command.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    public boolean isBye() {
        if (commandWord == null) {
            return false;
        }

        if (!isValid) {
            return false;
        }

        return this.commandWord.equalsIgnoreCase(UserCommandWord.EXIT_COMMAND);
    }

    //@@author

    /**
     * Processes and executes the user command based on the command word.
     *
     * @param student The student object to perform operations on.
     */
    public void processCommand(Student student) {
        switch (commandWord) {
        case UserCommandWord.LEFT_COMMAND: {
            showModulesLeft(student.getModuleCodesLeft());
            break;
        }
        case UserCommandWord.PACE_COMMAND: {
            executePaceCommand(arguments, student.getCurrentModuleCredits(), student.getYear());
            break;
        }
        case UserCommandWord.PREREQUISITE_COMMAND: {
            String module = arguments[0];
            determinePrereq(module.toUpperCase(), student.getMajor()); //to convert "CEG" to dynamic course
            break;
        }
        case UserCommandWord.RECOMMEND_COMMAND: {
            executeRecommendCommand(student);
            break;
        }
        case UserCommandWord.ADD_MODULE_COMMAND: {
            String module = arguments[0].toUpperCase();
            int targetSem = Integer.parseInt(arguments[1]);

            executeAddModuleCommand(module, targetSem, student);
            break;
        }
        case UserCommandWord.DELETE_MODULE_COMMAND: {
            String module = arguments[0].toUpperCase();

            executeDeleteModuleCommand(module,student);
            break;
        }
        case UserCommandWord.SHIFT_MODULE_COMMAND: {
            String module = arguments[0].toUpperCase();
            int targetSem = Integer.parseInt(arguments[1]);
            executeShiftModuleCommand(module, targetSem, student);
            break;
        }
        case UserCommandWord.VIEW_SCHEDULE_COMMAND: {
            student.printSchedule();
            break;
        }
        case UserCommandWord.CLEAR_SCHEDULE_COMMAND: {
            executeClearScheduleCommand(student);
            break;
        }
        case UserCommandWord.COMPLETE_MODULE_COMMAND: {
            String module = arguments[0].toUpperCase();
            //to add to user completed module
            completeModule(student, module);

            break;
        }
        case UserCommandWord.REQUIRED_MODULES_COMMAND: {
            executeGetRequiredModulesForStudent(student.getMajor());
            break;
        }
        case UserCommandWord.INFO_COMMAND: {
            Api.infoCommands(arguments[0], userInput);
            break;
        }
        case UserCommandWord.SEARCH_MODULE_COMMAND: {
            Api.searchCommand(userInput);
            break;
        }
        case UserCommandWord.HELP_COMMAND: {
            displayCommands(commandManager);
            break;
        }
        case UserCommandWord.TIMETABLE_COMMAND: {
            student.timetableShowOrModify(arguments[0]);
            break;
        }
        default: {
            break;
        }
        }

    }

}
