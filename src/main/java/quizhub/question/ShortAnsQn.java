package quizhub.question;
/**
 * Represents todo tasks in the current task list
 */
public class ShortAnsQn extends Question {
    /**
     * Creates a new task of type todo.
     *
     * @param description Task description.
     */
    public ShortAnsQn(String description){
        super(description, qnType.SHORTANSWER);
    }
}
