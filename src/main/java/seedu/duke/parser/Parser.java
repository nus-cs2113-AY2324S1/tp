package seedu.duke.parser;

import static seedu.duke.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.duke.commands.Command;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.goal.GoalCommand;
import seedu.duke.commands.goal.DeleteGoalCommand;
import seedu.duke.commands.goal.ViewGoalCommand;
import seedu.duke.commands.goal.AchievementCommand;
import seedu.duke.commands.goal.AchieveGoalCommand;
import seedu.duke.commands.IncorrectCommand;
import seedu.duke.commands.logcommands.LogCommand;
import seedu.duke.commands.logcommands.DeleteLogCommand;
import seedu.duke.commands.logcommands.ViewLogCommand;
import seedu.duke.commands.logcommands.UpdateLogCommand;
import seedu.duke.data.exception.IllegalValueException;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.meal.*;

public class Parser {

    public static final Pattern PERSON_INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");

    public static final Pattern KEYWORDS_ARGS_FORMAT = Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)");
    // one or more keywords separated by whitespace

    /**
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws Exception if unexpected exception occurs
     */
    public Command parseCommand(String userInput) throws Exception {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
            case LogCommand.COMMAND_WORD:
                return new LogCommand(Arrays.asList(arguments.trim().split(" ")));

            case DeleteLogCommand.COMMAND_WORD:
                return new DeleteLogCommand(Arrays.asList(arguments.trim().split(" ")));

            case ViewLogCommand.COMMAND_WORD:
                return new ViewLogCommand(Arrays.asList(arguments.trim().split(" ")));

            case UpdateLogCommand.COMMAND_WORD:
                return new UpdateLogCommand(Arrays.asList(arguments.trim().split(" ")));

            case AddCommand.COMMAND_WORD:
                return new AddCommand(Arrays.asList(arguments.trim().split(" ")));

            case DeleteCommand.COMMAND_WORD:
                return new DeleteCommand(Arrays.asList(arguments.trim().split(" ")));

            case ListCommand.COMMAND_WORD:
                return new ListCommand(Arrays.asList(arguments.trim().split(" ")));

            case GoalCommand.COMMAND_WORD:
                return new GoalCommand(userInput);

            case DeleteGoalCommand.COMMAND_WORD:
                return new DeleteGoalCommand(userInput);

            case ViewGoalCommand.COMMAND_WORD:
                return new ViewGoalCommand(userInput);

            case AchieveGoalCommand.COMMAND_WORD:
                return new AchieveGoalCommand(userInput);

            case AchievementCommand.COMMAND_WORD:
                return new AchievementCommand(userInput);

            case HelpCommand.COMMAND_WORD:
                return new HelpCommand();

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();

            default:
                return new IncorrectCommand("The command you inputted does not exist. Run `help` to see a" +
                        "list of available commands.");
        }
    }

    /**
     * Returns true if the private prefix is present for a contact detail in the add
     * command's arguments string.
     */
    private static boolean isPrivatePrefixPresent(String matchedPrefix) {
        return matchedPrefix.equals("p");
    }

    /**
     * Extracts the new person's tags from the add command's tag arguments string.
     * Merges duplicate tag strings.
     */
    private static Set<String> getTagsFromArgs(String tagArguments) throws IllegalValueException {
        // no tags
        if (tagArguments.isEmpty()) {
            return Collections.emptySet();
        }
        // replace first delimiter prefix, then split
        final Collection<String> tagStrings = Arrays.asList(tagArguments.replaceFirst(" t/", "").split(" t/"));
        return new HashSet<>(tagStrings);
    }

    /**
     * Parses the given arguments string as a single index number.
     *
     * @param args arguments string to parse as index number
     * @return the parsed index number
     * @throws ParseException        if no region of the args string could be found
     *                               for the index
     * @throws NumberFormatException the args string region is not a valid number
     */
    private int parseArgsAsDisplayedIndex(String args) throws ParseException, NumberFormatException {
        final Matcher matcher = PERSON_INDEX_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException("Could not find index number to parse");
        }
        return Integer.parseInt(matcher.group("targetIndex"));
    }

    /**
     * Signals that the user input could not be parsed.
     */
    public static class ParseException extends Exception {
        ParseException(String message) {
            super(message);
        }
    }
}
