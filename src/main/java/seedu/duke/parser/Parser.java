package seedu.duke.parser;

import seedu.duke.command.AddDishCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteDishCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.ListIngredientCommand;
import seedu.duke.command.ListMenuCommand;
import seedu.duke.data.Menu;

import java.text.ParseException;

/**
 * Parse everything received from the users on terminal
 * into a format that can be interpreted by other core classes
 */
public class Parser {
    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @param menu The arraylist object created that stores current tasks
     * @return the command based on the user input
     */
    public static Command parseCommand(Menu menu, String userInput) {
        String[] command = userInput.split(" ");
        switch (command[0].toLowerCase()) {
        case ListMenuCommand.COMMAND_WORD:
            return new ListMenuCommand();
        case ListIngredientCommand.COMMAND_WORD:
            return prepareListIngredient(userInput);
        case DeleteDishCommand.COMMAND_WORD:
            return prepareDelete(userInput);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case AddDishCommand.COMMAND_WORD:
            //return new AddDishCommand();
            return new IncorrectCommand("DEXTER DO WORK");
        default:
            return new IncorrectCommand("Your command has left me scratching my virtual head. " +
                    "Let's try that again, shall we?");
        }
    }

    /**
     * Parses arguments in the context of the ListIngredient command.
     * @param userInput arguments string to parse as index number
     * @return the prepared command
     */
    private static Command prepareListIngredient(String userInput) {
        try {
            final int listIndex = parseArgsAsDisplayedIndex(userInput, ListIngredientCommand.COMMAND_WORD);
            return new ListIngredientCommand(listIndex);
        } catch (ParseException e) {
            return new IncorrectCommand("MESSAGE_INVALID_COMMAND_FORMAT" + ListIngredientCommand.MESSAGE_USAGE);
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand("MESSAGE_INVALID_TASK_DISPLAYED_INDEX");
        }
    }

    /**
     * Parses arguments in the context of the Delete command.
     *
     * @param userInput Input from the user
     * @return Command to be executed
     */
    private static Command prepareDelete(String userInput) {
        try {
            final int listIndex = parseArgsAsDisplayedIndex(userInput, DeleteDishCommand.COMMAND_WORD);
            return new DeleteDishCommand(listIndex);
        } catch (ParseException e) {
            return new IncorrectCommand("MESSAGE_INVALID_COMMAND_FORMAT" + DeleteDishCommand.MESSAGE_USAGE);
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand("MESSAGE_INVALID_TASK_DISPLAYED_INDEX");
        }
    }

    /**
     * Parses the given arguments string to identify task index number.
     *
     * @param userInput arguments string to parse as index number
     * @param command expected String name of the command called
     * @return the parsed index number
     * @throws ParseException if no region of the args string could be found for the index
     * @throws NumberFormatException the args string region is not a valid number
     */
    private static int parseArgsAsDisplayedIndex(String userInput, String command)
            throws ParseException, NumberFormatException {
        String formattedString = userInput.replace(command, "").trim();
        return Integer.parseInt(formattedString);
    }
}
