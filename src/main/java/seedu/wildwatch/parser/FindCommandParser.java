package seedu.wildwatch.parser;

import seedu.wildwatch.command.FindCommand;
import seedu.wildwatch.exception.InvalidInputException;
import seedu.wildwatch.error.InvalidInputErrorType;

public class FindCommandParser implements Parser<FindCommand>{
    public static final String COMMAND_WORD = "find";
    private static final int NUMBER_OF_ALPHABETS_IN_FIND = 4;

    @Override
    public FindCommand parse(String input) throws InvalidInputException {
        String matchingWord = input.substring(input.indexOf(COMMAND_WORD)
                + NUMBER_OF_ALPHABETS_IN_FIND).trim();
        if (matchingWord.isEmpty()) {
            throw new InvalidInputException(InvalidInputErrorType.EMPTY_FIND_INPUT);
        }
        return new FindCommand(matchingWord); //Note the "find" is removed from the String here
    }
}
