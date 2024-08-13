//@@lctxct
package seedu.wildwatch.parser;

import seedu.wildwatch.entry.Entry;
import seedu.wildwatch.command.AddFileStringCommand;
import seedu.wildwatch.entry.EntryList;
import seedu.wildwatch.error.InvalidInputErrorType;
import seedu.wildwatch.exception.InvalidInputException;
import seedu.wildwatch.error.DateChecker;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class FileStringParser implements Parser<AddFileStringCommand> {

    private static final Pattern ADD_FILE_ENTRY_COMMAND_FORMAT_CHECK =
            Pattern.compile(
                    "\\s*(?<date>[^/]+)?\\s*/" +
                    "\\s*(?<species>[^/]+)?\\s*/" +
                    "\\s*(?<name>[^/]+)?\\s*/" +
                    "\\s*(?<remark>[^/]+)?");

    @Override
    public AddFileStringCommand parse(String input) throws InvalidInputException {
        performChecks(input);

        final Matcher matcher = AddFileStringCommand.FILE_STRING_FORMAT.matcher(input);
        boolean isMatch = matcher.matches();

        assert isMatch : "Input line should match format.";

        final String date = matcher.group("date").trim();
        final String species = matcher.group("species").trim();
        final String name = matcher.group("name").trim();
        final String remark = matcher.group("remark").trim();

        Entry newEntry = new Entry(date, species, name, remark);

        int existingEntryIdx = EntryList.checkEntryExists(newEntry);
        if (existingEntryIdx != -1) {
            throw new InvalidInputException(InvalidInputErrorType.INVALID_INPUT);
        }

        return new AddFileStringCommand(newEntry);
    }

    private void performChecks(String input) throws InvalidInputException {

        final Matcher matcher = ADD_FILE_ENTRY_COMMAND_FORMAT_CHECK.matcher(input);
        if (!matcher.matches()) {
            throw new InvalidInputException(InvalidInputErrorType.INVALID_INPUT);
        }

        // Check that all groups present
        if (matcher.group("date") == null ||
                matcher.group("species") == null ||
                matcher.group("name") == null) {
            throw new InvalidInputException(InvalidInputErrorType.INVALID_INPUT);
        }

        // Check that date is valid
        if (!DateChecker.isDateValid(matcher.group("date"))) {
            throw new InvalidInputException(InvalidInputErrorType.INVALID_INPUT);
        }
    }
}
