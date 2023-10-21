package quizhub.question;
/**
 * Represents all questions in the question list in general.
 */
public class Question {
    public enum QnType {SHORTANSWER, DEFAULT};
    private QnType qnType;
    private String description;
    private boolean done;
    private String module;

    /**
     * Creates a new question with a blank description,
     * not done status and default question type.
     */
    public Question(){
        description = "";
        done = false;
        qnType = QnType.DEFAULT;
        module = "";
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
        qnType = QnType.DEFAULT;
        module = "";
    }
    /**
     * Creates a new question with a given description,
     * not done status and given question type.
     *
     * @param questionBody Description given to the question.
     * @param qnType Type given to the question.
     */
    public Question(String questionBody, QnType qnType){
        this.description = questionBody;
        done = false;
        this.qnType = qnType;
        module = "";
    }

    /**
     * Creates a new question with a given description,
     * not done status and given question type.
     *
     * @param questionBody Description given to the question.
     * @param qnType Type given to the question.
     * @param module Module given to the question.
     */
    public Question(String questionBody, QnType qnType, String module){
        this.description = questionBody;
        done = false;
        this.qnType = qnType;
        this.module = module;
    }

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
    public QnType getQuestionType(){
        return qnType;
    }

    /**
     * Returns module.
     */
    public String getModule(){
        return module;
    }

    /**
     * Returns question details in a string.
     * @param newDescription New description of the question.
     * @param newAnswer New answer of the question.
     */
    public void editQuestion(String newDescription, String newAnswer){
        if(null != newDescription){
            this.description = newDescription;
        }
    }
}
