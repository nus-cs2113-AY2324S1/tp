package quizhub.question;
/**
 * Represents Short Answer Questions. This means answers are a simple string.
 */
public class ShortAnsQn extends Question {
    private String answer;
    /**
     * Creates a new question of type SHORTANSWER.
     *
     * @param description question description.
     * @param answer question answer.
     */
    public ShortAnsQn(String description, String answer, String module){
        super(description, QnType.SHORTANSWER, module);
        this.answer = answer;
    }
    /**
     * Returns question answer.
     */
    public String getQuestionAnswer() {
        return this.answer;
    }

    @Override
    public void editQuestion(String newDescription, String newAnswer) {
        super.editQuestion(newDescription, newAnswer);
        if(null != newAnswer){
            this.answer = newAnswer;
        }
    }
    /**
     * Returns question-answer pair, separated by a vertical bar.
     */
    @Override
    public String getQuestionDescription() {
        return super.getQuestionDescription().strip() + " / " + this.answer.strip();
    }
}
