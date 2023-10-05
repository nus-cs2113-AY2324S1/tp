package quizhub.quiz;
/**
 * Represents todo tasks in the current task list
 */
public class ShortAnsQn extends Quiz {
    /**
     * Creates a new task of type todo.
     *
     * @param description Task description.
     */
    public ShortAnsQn(String description){
        super(description, TaskType.TODO);
    }
}
