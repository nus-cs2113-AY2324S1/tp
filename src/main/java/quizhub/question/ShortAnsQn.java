package quizhub.question;
/**
 * Represents Short Answer Questions. This means answers are a simple string.
 */
public class ShortAnsQn extends Question {
    private String answer;
    /**
     * Creates a new question of type SHORTANSWER
     *
     * @param description Question description.
     * @param answer Question answer.
     * @param module Question module.
     */
    public ShortAnsQn(String description, String answer, String module){
        super(description, QnType.SHORTANSWER, module);
        this.answer = answer;
    }
    /**
     * Creates a new question of type SHORTANSWER
     *
     * @param description Question description.
     * @param answer Question answer.
     * @param module Question module.
     * @param qnDifficulty Question difficulty.
     */
    public ShortAnsQn(String description, String answer, String module, QnDifficulty qnDifficulty){
        super(description, QnType.SHORTANSWER, module, qnDifficulty);
        this.answer = answer;
    }

    /**
     * Gets question answer.
     * @return The answer
     */
    public String getQuestionAnswer() {
        return this.answer;
    }

    @Override
    public void editQuestion(String editField, String newValue) {
        switch (editField) {
        case "description":
            if (super.getQuestionDescription().equals(newValue)) {
                displayEditErrorMessage(editField);
                break;
            }
            super.editQuestion(newValue, "");
            break;
        case "answer":
            if (this.answer.equals(newValue)) {
                displayEditErrorMessage(editField);
                break;
            }
            this.answer = newValue;
            break;
        default:
            break;
        }
    }

    @Override
    public QnType getQuestionType(){
        return QnType.SHORTANSWER;
    }

    /**
     * Returns question-answer pair, separated by a vertical bar.
     */
    @Override
    public String getQuestionDescription() {
        return super.getQuestionDescription().strip() + " / " + this.answer.strip() + " | " + super.getModule()
                + " | " + super.getDifficulty().toString();
    }
}
