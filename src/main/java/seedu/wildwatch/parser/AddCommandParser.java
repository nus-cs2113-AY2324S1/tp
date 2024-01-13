//@@lctxct
package seedu.wildwatch.parser;

import seedu.wildwatch.entry.Entry;
import seedu.wildwatch.command.AddCommand;
import seedu.wildwatch.entry.EntryList;
import seedu.wildwatch.exception.InvalidInputException;
import seedu.wildwatch.error.DateChecker;
import seedu.wildwatch.error.InvalidInputErrorType;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class AddCommandParser implements Parser<AddCommand> {


    private static final String[] ENTRY_ITEMS = new String[] { "date", "species", "name", "remark" };

    private static final Pattern ADD_DEFAULT_COMMAND_FORMAT_CHECK =
            Pattern.compile("add"
                    + "\\s*(?<dprefix> D/)?\\s*(?<date>[^/]+)?"
                    + "\\s*(?<sprefix> S/)?\\s*(?<species>[^/]+)?"
                    + "\\s*(?<nprefix> N/)?\\s*(?<name>[^/]+)?"
                    + "\\s*(?: R/(?<remark>.*))?");

    private String date;
    private String species;
    private String name;
    private String remark = null;

    @Override
    public AddCommand parse(String input) throws InvalidInputException {

        final Matcher matcherInteractive = AddCommand.ADD_INTERACTIVE_COMMAND_FORMAT.matcher(input);
        if (matcherInteractive.matches()) {
            handleInteractive();
        } else {
            // Default case
            performChecks(input);

            final Matcher matcherDefault = AddCommand.ADD_DEFAULT_COMMAND_FORMAT.matcher(input);
            boolean isMatch = matcherDefault.matches();

            assert isMatch : "Command should match format.";

            date = matcherDefault.group("date").trim();
            species = matcherDefault.group("species").trim();
            name = matcherDefault.group("name").trim();
            remark = matcherDefault.group("remark");

            if (remark == null) {
                remark = "";
            } else {
                remark = remark.trim();
            }
        }

        Entry newEntry = new Entry(date, species, name, remark);

        // Check that entry is not a duplicate
        int duplicateEntryIdx = EntryList.checkEntryExists(newEntry);
        if (duplicateEntryIdx != -1) {
            throw new InvalidInputException("Entry already exists! "
                    + "Note that you can only add one entry per animal in a day.\n"
                    + "Use the edit command or delete the existing entry first.");
        }

        return new AddCommand(newEntry);
    }

    /**
     * Handle interactive add command, which prompts user for each item required
     * in an entry.
     */
    private void handleInteractive() throws InvalidInputException {
        Scanner scanner = new Scanner(System.in);
        String input;

        for (String item : ENTRY_ITEMS) {
            String promptMessage = String.format("Please input the %s you would like to set, or q/ to quit.", item);
            if (item.equals("remark")) {
                promptMessage += "\n(Hit Enter to leave this field blank)";
            }

            input = getItem(scanner, promptMessage, item);

            switch (item) {
            case "date":
                date = input;
                break;
            case "species":
                species = input;
                break;
            case "name":
                name = input;
                break;
            case "remark":
                remark = input;
                break;
            default:
            }
        }
    }

    private String getItem(Scanner scanner, String promptMessage, String item) throws InvalidInputException {
        String inputBuffer;
        boolean isValid = false;

        do {
            System.out.print(promptMessage);
            System.out.println(": ");


            inputBuffer = scanner.nextLine().trim();
            isValid = true;

            // check if user q/uits early
            if (inputBuffer.equals("q/")) {
                throw new InvalidInputException("Exited add command");
            }

            // check that fields are not empty
            if (!item.equals("remark")) {
                if (inputBuffer.isEmpty()) {
                    isValid = false;
                    System.out.println("Field cannot be left blank.");
                }
            }

            // if date, check validity of date
            if (item.equals("date")) {
                if (!DateChecker.isDateValid(inputBuffer)) {
                    isValid = false;
                    System.out.println("Please key in a valid date.");
                }
            }

            // check that no /
            if (inputBuffer.contains("/")) {
                isValid = false;
                System.out.println("Your input should not contain /.");
            }

        } while (!isValid);

        return inputBuffer;
    }

    /**
     * Ensures that {@code input} adheres to the command format.
     *
     * @param input
     * @throws InvalidInputException thrown if the input does not adhere to command format.
     */
    private void performChecks(String input) throws InvalidInputException {
        final Matcher matcher = ADD_DEFAULT_COMMAND_FORMAT_CHECK.matcher(input);
        if (!matcher.matches()) {
            throw new InvalidInputException(InvalidInputErrorType.INVALID_INPUT);
        }

        checkDate(matcher);
        checkSpecies(matcher);
        checkName(matcher);
    }

    /**
     * Checks that date is correctly defined in input. Checks the following:
     * 1. D/ is present in user input
     * 2. A date value is defined
     * 3. Date provided is correctly formatted
     *
     * @param matcher Input matched against command pattern
     * @throws InvalidInputException thrown when date is not correctly defined
     */
    private void checkDate(Matcher matcher) throws InvalidInputException {
        if (matcher.group("dprefix") == null) {
            throw new InvalidInputException("D/ is not defined");
        }
        if (matcher.group("date") == null) {
            throw new InvalidInputException("Date value cannot be empty.");
        }
        String date = matcher.group("date").trim();
        if (!DateChecker.isDateValid(date)) {
            throw new InvalidInputException(InvalidInputErrorType.INVALID_DATE);
        }
    }

    /**
     * Checks that species is correctly defined in input. Checks the following:
     * 1. S/ is present in user input
     * 2. Species is defined
     *
     * @param matcher Input matched against command pattern
     * @throws InvalidInputException thrown when species is not correctly defined
     */
    private void checkSpecies(Matcher matcher) throws InvalidInputException {
        if (matcher.group("sprefix") == null) {
            throw new InvalidInputException("S/ is not defined");
        }
        if (matcher.group("species") == null) {
            throw new InvalidInputException("Species is not defined.");
        }
    }

    /**
     * Checks that name is correctly defined in input. Checks the following:
     * 1. N/ is present in user input
     * 2. Name is defined
     *
     * @param matcher Input matched against command pattern
     * @throws InvalidInputException thrown when species is not correctly defined
     */
    private void checkName(Matcher matcher) throws InvalidInputException {
        if (matcher.group("nprefix") == null) {
            throw new InvalidInputException("N/ is not defined");
        }
        if (matcher.group("name") == null) {
            throw new InvalidInputException("Name is not defined");
        }
    }
}
