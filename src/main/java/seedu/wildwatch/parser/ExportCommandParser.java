//@@lctxct
package seedu.wildwatch.parser;

import seedu.wildwatch.command.ExportCommand;
import seedu.wildwatch.error.FilenameChecker;
import seedu.wildwatch.error.InvalidInputErrorType;
import seedu.wildwatch.exception.InvalidInputException;

import java.util.Scanner;
import java.util.regex.Matcher;

/**
 * Parses export command. Accepts optional {@code filename} argument
 * proceeding {@code export} keyword.
 */
public class ExportCommandParser implements Parser<ExportCommand> {

    @Override
    public ExportCommand parse(String input) throws InvalidInputException {
        final Matcher matcher = ExportCommand.EXPORT_COMMAND_FORMAT.matcher(input);

        if (!matcher.matches()) {
            throw new InvalidInputException(InvalidInputErrorType.INVALID_INPUT);
        }

        String filename = matcher.group("filename");
        if (filename == null) {
            return new ExportCommand();
        }

        Scanner scanner = new Scanner(System.in);

        // Keep prompting until user provides a valid filename or decides to exit
        while (!FilenameChecker.isValidCsvFilenameChecker(filename)) {
            System.out.println("Filename is invalid!");
            System.out.println("Please provide a valid filename, or leave input blank to default to WildWatch.csv.");
            System.out.print("You may also type q/ to exit command: ");
            filename = scanner.nextLine().trim();

            if (filename.equals("q/")) {
                throw new InvalidInputException("Exiting export command...");
            }

            if (filename.isEmpty()) {
                return new ExportCommand();
            }
        }

        return new ExportCommand(filename);
    }
}
