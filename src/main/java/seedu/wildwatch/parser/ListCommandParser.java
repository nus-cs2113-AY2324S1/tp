package seedu.wildwatch.parser;

import seedu.wildwatch.command.ListCommand;
import seedu.wildwatch.error.InvalidInputErrorType;
import seedu.wildwatch.exception.InvalidInputException;

public class ListCommandParser implements Parser<ListCommand>{
    public static final String COMMAND_WORD = "list";
    private static final int NUMBER_OF_ALPHABETS_IN_LIST = 4;


    public ListCommand parse(String input) throws InvalidInputException {
        String matchingWord = input.substring(input.indexOf(COMMAND_WORD)
                + NUMBER_OF_ALPHABETS_IN_LIST).trim();
        if (matchingWord.isEmpty()) {
            return new ListCommand();
        }
        throw new InvalidInputException(InvalidInputErrorType.INVALID_INPUT);
    }
}
