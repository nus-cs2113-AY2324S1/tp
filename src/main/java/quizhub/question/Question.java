package quizhub.question;
/**
 * Represents all tasks in the task list in general.
 */
public class Question {
    public enum qnType {SHORTANSWER, DEFAULT};
    private qnType qnType;
    private String description;
    private boolean done;
    /**
     * Mark a task as done.
     */
    public void markAsDone(){
        done = true;
    }
    /**
     * Mark a task as not done.
     */
    public void markAsNotDone(){
        done = false;
    }
    /**
     * Creates a new task with a blank description,
     * not done status and default task type.
     */
    public Question(){
        description = "";
        done = false;
        qnType = qnType.DEFAULT;
    }
    /**
     * Creates a new task with a given description,
     * not done status and default task type.
     *
     * @param description Description given to the task.
     */
    public Question(String description){
        this.description = description;
        done = false;
        qnType = qnType.DEFAULT;
    }
    /**
     * Creates a new task with a given description,
     * not done status and given task type.
     *
     * @param description Description given to the task.
     * @param qnType Type given to the task.
     */
    public Question(String description, qnType qnType){
        this.description = description;
        done = false;
        this.qnType = qnType;
    }
    /**
     * Checks if a task has been done.
     */
    public boolean questionIsDone(){
        return done;
    }
    /**
     * Returns task description.
     */
    public String getQuestionDescription(){
        return description;
    }
    /**
     * Returns task timing details.
     */
    public String getQuestionTiming(boolean useDefaultTiming){
        return "";
    }
    /**
     * Returns task type.
     */
    public qnType getQuestionType(){
        return qnType;
    }
}
