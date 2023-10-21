package quizhub.parser;

import quizhub.command.Command;
import quizhub.command.CommandList;
import quizhub.command.CommandEdit;
import quizhub.command.CommandShortAnswer;
import quizhub.command.CommandExit;
import quizhub.command.CommandDelete;
import quizhub.command.CommandFind;
import quizhub.command.CommandInvalid;
import quizhub.command.CommandStart;
import quizhub.command.CommandHelp;

/**
 * Represents a parser that converts user inputs into command objects.
 */
public class Parser {

    public static final String INVALID_COMMAND_FEEDBACK = "    Valid commands are: short [question]/[answer],\n" +
            "                        list,\n" +
            "                        start,\n" +
            "                        edit [question number] /question,\n" +
            "                        edit [question number] /answer,\n" +
            "                        delete [question number],\n" +
            "                        find /description [question description]\n" +
            "                        shuffle,\n" +
            "                        help,\n" +
            "                        bye";
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
        String commandDetails;
        int taskIndex;

        try {
            switch (commandTitle) {
            case "bye":
                return new CommandExit();
            case "list":
                return new CommandList();
            case "short":
                return new CommandShortAnswer(userInput);
            case "start":
                return new CommandStart();
            case "edit":
                return new CommandEdit(userInput);
            case "delete":
                commandDetails = commandTokens[1].strip();
                taskIndex = Integer.parseInt(commandDetails.strip());
                return new CommandDelete(taskIndex);
            case "find":
                return new CommandFind(userInput);
            case "shuffle":
                return new CommandShuffle();
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

}
