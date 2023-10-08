package quizhub.question;
/**
 * Represents all questions in the question list in general.
 */
public class Question {
    public enum qnType {SHORTANSWER, DEFAULT};
    private qnType qnType;
    private String description;
    private boolean done;
    /**
     * Mark a question as done.
     */
    public void markAsDone(){
        done = true;
    }
    /**
     * Mark a question as not done.
     */
    public void markAsNotDone(){
        done = false;
    }
    /**
     * Creates a new question with a blank description,
     * not done status and default question type.
     */
    public Question(){
        description = "";
        done = false;
        qnType = qnType.DEFAULT;
    }
    /**
     * Creates a new question with a given description,
     * not done status and default task type.
     *
     * @param questionBody Description given to the question.
     */
    public Question(String questionBody){
        this.description = questionBody;
        done = false;
        qnType = qnType.DEFAULT;
    }
    /**
     * Creates a new question with a given description,
     * not done status and given question type.
     *
     * @param questionBody Description given to the question.
     * @param qnType Type given to the question.
     */
    public Question(String questionBody, qnType qnType){
        this.description = questionBody;
        done = false;
        this.qnType = qnType;
    }
    /**
     * Checks if a question has been done.
     */
    public boolean questionIsDone(){
        return done;
    }
    /**
     * Returns question description.
     */
    public String getQuestionDescription(){
        return description;
    }
    /**
     * Returns question timing details.
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
    public void editQuestion(String newDescription, String newAnswer){
        if(!newDescription.equals("")){
            this.description = newDescription;
        }
    }
}
