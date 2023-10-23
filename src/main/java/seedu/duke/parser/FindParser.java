package seedu.duke.parser;



import seedu.duke.commands.KaChinnnngException;


public class FindParser {
    /**
     * Parses the find command input.
     * @param fullCommand The full command string input by the user.
     * @return An array of Strings containing the parsed parameters in the order: TYPE, CATEGORY, DESCRIPTION, DATE.
     * @throws KaChinnnngException If the command format is invalid.
     */
    public static String[] parseFindCommand(String fullCommand) throws KaChinnnngException {
        String[] tokens = fullCommand.split(" ");
        String[] parsedParameters = new String[4];  // To store TYPE, CATEGORY, DESCRIPTION, DATE
        String[] validParameters = {"/t", "/cat", "/de", "/date"};  // List of valid parameters

        for (String token : tokens) {
            if (token.startsWith("/")) {
                boolean isValidParam = false;
                for (String validParam : validParameters) {
                    if (token.equals(validParam)) {
                        isValidParam = true;
                        break;
                    }
                }
                if (!isValidParam) {
                    throw new KaChinnnngException("Unrecognized parameter \"" + token + "\". " +
                            "Please refer to the help guide for valid parameters.");
                }
            }
        }

        if (fullCommand.contains("/type")) {
            throw new KaChinnnngException("Please use /t instead of /type for specifying the type.");
        }
        if (fullCommand.contains("/category")) {
            throw new KaChinnnngException("Please use /cat instead of /category for specifying the category.");
        }
        if (fullCommand.contains("/description")) {
            throw new KaChinnnngException("Please use /de instead of /description for specifying the description.");
        }
        if (!fullCommand.contains("/t")) {
            throw new KaChinnnngException("The /t TYPE field is mandatory.");
        }

        int typeIndex = indexOf(tokens, "/t");
        if (typeIndex == tokens.length - 1 || tokens[typeIndex + 1].startsWith("/")) {
            throw new KaChinnnngException("The value for /t TYPE cannot be empty.");
        }

        parsedParameters[0] = tokens[typeIndex + 1];

        if (fullCommand.contains("/cat")) {
            int categoryIndex = indexOf(tokens, "/cat");
            if (categoryIndex == tokens.length - 1 || tokens[categoryIndex + 1].startsWith("/")) {
                throw new KaChinnnngException("The value for /c CATEGORY cannot be empty.");
            }
            parsedParameters[1] = tokens[categoryIndex + 1];
        }

        if (fullCommand.contains("/de")) {
            int descriptionIndex = indexOf(tokens, "/de");
            if (descriptionIndex == tokens.length - 1 || tokens[descriptionIndex + 1].startsWith("/")) {
                throw new KaChinnnngException("The value for /de DESCRIPTION cannot be empty.");
            }
            parsedParameters[2] = tokens[descriptionIndex + 1];
        }

        if (fullCommand.contains("/date")) {
            int dateIndex = indexOf(tokens, "/date");
            if (dateIndex == tokens.length - 1 || tokens[dateIndex + 1].startsWith("/")) {
                throw new KaChinnnngException("The value for /da DATE cannot be empty.");
            }
            parsedParameters[3] = tokens[dateIndex + 1];
        }

        // Check that at least one optional field is provided
        if (parsedParameters[1] == null && parsedParameters[2] == null && parsedParameters[3] == null) {
            throw new KaChinnnngException("At least one of the optional fields [/c CATEGORY], " +
                                            "[/de DESCRIPTION], [/da DATE] must be provided.");
        }
        return parsedParameters;
    }

    /**
     * Helper method to find the index of a specific string in an array.
     * @param array The array to search.
     * @param value The string value to find.
     * @return The index of the value in the array, or -1 if not found.
     */
    private static int indexOf(String[] array, String value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }
}
