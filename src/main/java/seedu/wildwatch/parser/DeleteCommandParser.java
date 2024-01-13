//@@lctxct
package seedu.wildwatch.parser;

import seedu.wildwatch.command.DeleteCommand;
import seedu.wildwatch.entry.EntryList;
import seedu.wildwatch.exception.InvalidInputException;
import seedu.wildwatch.error.InvalidInputErrorType;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class DeleteCommandParser implements Parser<DeleteCommand> {

    private static final Pattern DELETE_ENTRY_COMMAND_FORMAT_CHECK =
            Pattern.compile("delete\\s+(?<index>\\S+)\\s*");

    @Override
    public DeleteCommand parse(String input) throws InvalidInputException {
        int getIdx = getIndexFromInput(input);

        return new DeleteCommand(getIdx);
    }

    /**
     * Gets index of entry to delete from input.
     * Checks that input adheres to required delete command format.
     * 1. Checks that it is in the form {@code delete<whitespace(s)>INDEX}
     * 2. Checks that INDEX > 1 and INDEX <= length of entry list
     *
     * @param input
     * @return index of entry to delete
     * @throws InvalidInputException thrown when command format is incorrect.
     */
    private int getIndexFromInput(String input) throws InvalidInputException {

        final Matcher matcher = DELETE_ENTRY_COMMAND_FORMAT_CHECK.matcher(input);
        if (!matcher.matches()) {
            throw new InvalidInputException(InvalidInputErrorType.INVALID_INPUT);
        }

        // Check that index is a valid number
        int deleteIdx = -1;
        try {
            deleteIdx = Integer.parseInt(matcher.group("index"));
        } catch (NumberFormatException e) {
            throw new InvalidInputException(InvalidInputErrorType.INVALID_INDEX);
        }

        // Check that index is within bounds
        if (deleteIdx < 1 || deleteIdx > EntryList.getArraySize()) {
            throw new InvalidInputException(InvalidInputErrorType.ENTRY_NOT_FOUND);
        }

        return deleteIdx;
    }
}
