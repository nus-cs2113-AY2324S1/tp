package seedu.duke;

/**
 * Any excption will be throw in this type, which contains information about
 * this exception and the possible solution.
 * It's suggested to use toString() to show the content of this exception so
 * that the tester can have a clear view of the error reason and the possible
 * solution to solve this error.
 */
public class TipsException extends Exception {
    public String error, tips;

    /**
     * Creating a new Tips Exception.
     */
    public TipsException(String error, String tips) {
        this.error = error;
        this.tips = tips;
    }

    @Override
    public String toString() {
        return "Error: " + error
                + "\nTips: " + tips;
    }
}
