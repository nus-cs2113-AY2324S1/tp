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
        String command[] = userInput.split(" ");
        switch (command[0].toLowerCase()) {
            case ListMenuCommand.COMMAND_WORD:
                return new ListMenuCommand();
            /*case ListIngredientCommand.COMMAND_WORD:
                return prepareListIngredient(menu,userInput);
            case DeleteDishCommand.COMMAND_WORD:
                return prepareDelete(menu,userInput);*/
            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();
            case AddDishCommand.COMMAND_WORD:
                return new AddDishCommand();
            default:
                return new IncorrectCommand("Whoa there, tiger! Your command has left me scratching my virtual head. Let's try that again, shall we?");
        }
    }

    /**
     *
     * @param menu
     * @param userInput
     * @return the prepared command
     */
    private static Command prepareListIngredient(Menu menu, String userInput) {
        try {
            final int listIndex = parseArgsAsDisplayedIndex(menu ,userInput, ListIngredientCommand.COMMAND_WORD);
            //return new ListIngredientCommand(Menu menu, listIndex);
            return new IncorrectCommand("NAYCHI DO YOUR WORK");
        } catch (ParseException e) {
            return new IncorrectCommand("MESSAGE_INVALID_COMMAND_FORMAT" + ListIngredientCommand.MESSAGE_USAGE);
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand("MESSAGE_INVALID_TASK_DISPLAYED_INDEX");
        }
    }

    private static Command prepareDelete(Menu menu, String userInput) {
        try {
            final int listIndex = parseArgsAsDisplayedIndex(menu, userInput, DeleteDishCommand.COMMAND_WORD);
            //return new DeleteDishCommand(listIndex);
            return new IncorrectCommand("SHANICE DO YOUR WORK");
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
     * @param menu The arraylist object created that stores current dishes
     * @return the parsed index number
     * @throws ParseException if no region of the args string could be found for the index
     * @throws NumberFormatException the args string region is not a valid number
     */
    private static int parseArgsAsDisplayedIndex(Menu menu, String userInput, String command) throws ParseException, NumberFormatException {
        String formattedString = userInput.replace(command, "").trim();
        int listIndex = Integer.parseInt(formattedString);
        return listIndex;
    }
}
