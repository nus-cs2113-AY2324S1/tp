package seedu.wildwatch.exception;

import seedu.wildwatch.error.InvalidInputErrorType;

public class InvalidInputException extends Exception {
    private InvalidInputErrorType errorType;
    private String customMessage;

    public InvalidInputException(InvalidInputErrorType error, String message) {
        errorType = error;
        customMessage = message;
    }

    public InvalidInputException(InvalidInputErrorType error) {
        this(error, "Command is invalid.");
    }

    public InvalidInputException(String message) {
        this(InvalidInputErrorType.CUSTOM, message);
    }

    public InvalidInputErrorType getError() {
        return errorType;
    }

    public String getCustomMessage() {
        return customMessage;
    }
}
