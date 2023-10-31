package quizhub.exception;
/**
 * Represents non-standard exceptions thrown by this program specifically.
 */
public class QuizHubExceptions extends Exception{
    public QuizHubExceptions(String message) {
        super(message);
    }

    public QuizHubExceptions() {
        super();
    }
}

