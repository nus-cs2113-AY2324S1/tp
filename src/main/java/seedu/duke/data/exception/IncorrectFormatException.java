package seedu.duke.data.exception;

public class IncorrectFormatException extends Exception {

    public IncorrectFormatException(String message) {
        super(message);
    }

    public void handleException(){
        System.out.println("Please try again.");
    }

}
