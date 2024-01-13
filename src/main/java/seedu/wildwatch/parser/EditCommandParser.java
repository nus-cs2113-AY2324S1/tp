//@@woodenclock
package seedu.wildwatch.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import seedu.wildwatch.command.Command;
import seedu.wildwatch.command.EditCommand;
import seedu.wildwatch.entry.EntryList;
import seedu.wildwatch.error.DateChecker;
import seedu.wildwatch.error.InvalidInputErrorType;
import seedu.wildwatch.exception.InvalidInputException;

public class EditCommandParser {
    private static final Pattern EDIT_ENTRY_COMMAND_FORMAT_CHECK =
            Pattern.compile("edit"
                    + "\\s*(?<iprefix> I/)?\\s*(?<index>[^/]+)"
                    + "\\s*(?<dprefix> D/)?\\s*(?<date>[^/]+)?"
                    + "\\s*(?<sprefix> S/)?\\s*(?<species>[^/]+)?"
                    + "\\s*(?<nprefix> N/)?\\s*(?<name>[^/]+)?"
                    + "\\s*(?: R/(?<remark>[^/]*))?");

    public Command parse(String input) throws InvalidInputException {
        performChecks(input);

        final Matcher matcher = EditCommand.EDIT_ENTRY_COMMAND_FORMAT.matcher(input);
        boolean isMatch = matcher.matches();
        assert isMatch : "Command should match format.";

        return new EditCommand(input);
    }

    /**
     * Ensures that {@code input} adheres to the command format.
     *
     * @param input
     * @throws InvalidInputException thrown if the input does not adhere to command format.
     */
    private void performChecks(String input) throws InvalidInputException {
        final Matcher matcher = EDIT_ENTRY_COMMAND_FORMAT_CHECK.matcher(input);

        //Checks if the input adheres to the command format
        if (!matcher.matches()) {
            throw new InvalidInputException(InvalidInputErrorType.INVALID_INPUT);
        }

        checkIndex(matcher);
        if (!(matcher.group("date") == null)) {
            checkDate(matcher);
        }

        //Checks if there is any change to edit
        if ((matcher.group("date") == null) && (matcher.group("species") == null) &&
                (matcher.group("name") == null) && (matcher.group("remark") == null)) {
            throw new InvalidInputException(InvalidInputErrorType.EMPTY_EDIT_INPUT);
        }
    }

    private void checkIndex(Matcher matcher) throws InvalidInputException{
        int editIdx = -1;
        try {
            editIdx = Integer.parseInt(matcher.group("index"));
        } catch (NumberFormatException exception) {
            throw new InvalidInputException(InvalidInputErrorType.INVALID_INDEX);
        }
        if ((matcher.group("index") == null) || editIdx < 1 || editIdx > EntryList.getArraySize()) {
            throw new InvalidInputException(InvalidInputErrorType.ENTRY_NOT_FOUND);
        }
    }

    private void checkDate(Matcher matcher) throws InvalidInputException {
        String dateValue = matcher.group("date");
        if (!DateChecker.isDateValid(dateValue)) {
            throw new InvalidInputException(InvalidInputErrorType.INVALID_DATE);
        }
    }
}
