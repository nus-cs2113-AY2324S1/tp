package seedu.stocker.parser;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.stocker.commands.AddCommand;
import seedu.stocker.commands.Command;
import seedu.stocker.commands.DeleteCommand;
import seedu.stocker.commands.ExitCommand;
import seedu.stocker.commands.FindCommand;
import seedu.stocker.commands.HelpCommand;
import seedu.stocker.commands.IncorrectCommand;
import seedu.stocker.commands.ListCommand;
import seedu.stocker.commands.LoginCommand;
import seedu.stocker.commands.RegisterCommand;
import seedu.stocker.commands.SaveCommand;
import seedu.stocker.commands.AddVendorCommand;
import seedu.stocker.commands.ShowStockLevelCommand;
import seedu.stocker.commands.ViewCartCommand;
import seedu.stocker.commands.AddToCartCommand;
import seedu.stocker.commands.CheckOutCommand;
import seedu.stocker.commands.ListVendorCommand;
import seedu.stocker.commands.SetThresholdCommand;
import seedu.stocker.commands.ListThresholdCommand;

import static seedu.stocker.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class Parser {
    public Parser() {
    }

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput) throws IOException {
        String[] words = userInput.trim().split(" ", 2);  // split the input into command and arguments
        if (words.length == 0) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = words[0];
        final String arguments = userInput.replaceFirst(commandWord, "").trim();

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return prepareAddCommand(arguments);

        case FindCommand.COMMAND_WORD:
            return prepareFindCommand(arguments);

        case AddToCartCommand.COMMAND_WORD:
            return prepareAddToCartCommand(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommand(arguments);

        case CheckOutCommand.COMMAND_WORD:
            return new CheckOutCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ListCommand.COMMAND_WORD:
            return new ListCommand();
            
        case ViewCartCommand.COMMAND_WORD:
            return new ViewCartCommand();

        case RegisterCommand.COMMAND_WORD:
            return new RegisterCommand();

        case LoginCommand.COMMAND_WORD:
            return new LoginCommand();

        case SaveCommand.COMMAND_WORD:
            return new SaveCommand();
            
        case AddVendorCommand.COMMAND_WORD:
            return prepareAddVendorCommand(arguments);

        case ShowStockLevelCommand.COMMAND_WORD:
            return new ShowStockLevelCommand();

        case ListVendorCommand.COMMAND_WORD:
            return new ListVendorCommand();

        case SetThresholdCommand.COMMAND_WORD:
            return prepareSetThresholdCommand(arguments);

        case ListThresholdCommand.COMMAND_WORD:
            return new ListThresholdCommand();

        default:
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses arguments in the context of the add drug command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareAddCommand(String args) {
        try {
            Pattern pattern = Pattern.compile("/n (.*) /d (.*) /q (.*)");
            Matcher matcher = pattern.matcher(args);
            if (matcher.matches() && matcher.groupCount() == 3) {
                String name = matcher.group(1);
                String expiryDate = matcher.group(2);
                Long quantity = Long.parseLong(matcher.group(3));
                return new AddCommand(name, expiryDate, quantity);
            } else {
                return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
            }
        } catch (NumberFormatException e) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses arguments in the context of the add drug to cart command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareAddToCartCommand(String args) {
        Pattern pattern = Pattern.compile("/n (.*) /q (.*)");
        Matcher matcher = pattern.matcher(args);
        if (matcher.matches() && matcher.groupCount() == 2) {
            String name = matcher.group(1);
            long quantity = Long.parseLong(matcher.group(2));
            return new AddToCartCommand(name, quantity);
        } else {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddToCartCommand.MESSAGE_USAGE));
        }
    }

    private Command prepareFindCommand(String args) {
        String[] findArgs = args.split(" ", 2);
        if (findArgs.length == 2) {
            String criterion = findArgs[0];
            String keyword = findArgs[1];
            if (criterion.equals("/n") || criterion.equals("/d")) {
                return new FindCommand(keyword, criterion);
            } else {
                return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
        } else {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }
    private Command prepareAddVendorCommand (String args) throws IOException {
        String[] vendorArgs = args.split(" ",1);
        String vendorName = vendorArgs[0];
        try {
            if (vendorName.equals(null) || vendorName.equals("") || vendorName.equals(" ")) {
                throw new IOException();
            }
        } catch(Exception invalidInput){
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT,AddVendorCommand.MESSAGE_USAGE));
        }
        return new AddVendorCommand(vendorName);
    }

    private Command prepareSetThresholdCommand(String args) {
        try {
            Pattern pattern = Pattern.compile("/n (.*) /tq (.*)");
            Matcher matcher = pattern.matcher(args);
            if (matcher.matches() && matcher.groupCount() == 2) {
                String name = matcher.group(1);
                Long threshold = Long.parseLong(matcher.group(2));
                return new SetThresholdCommand(name, threshold);
            } else {
                return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        SetThresholdCommand.MESSAGE_USAGE));
            }
        } catch (NumberFormatException e) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SetThresholdCommand.MESSAGE_USAGE));
        }
    }

}
