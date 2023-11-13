package quizhub.question;
/**
 * Represents Short Answer Questions. This means answers are a simple string.
 */
public class ShortAnsQn extends Question {
    public static final String IDENTIFIER = "S";
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
    @Override
    public String getQuestionAnswer() {
        return this.answer;
    }

    @Override
    public String getCorrectAnswer() {
        return this.getQuestionAnswer();
    }

    @Override
    public void editQuestion(String editField, String newValue) {
        switch (editField) {
        case "description":
            super.editQuestion(newValue, "");
            break;
        case "answer":
            this.answer = newValue;
            break;
        default:
            break;
        }
    }

    /**
     * Returns question-answer pair, separated by a vertical bar.
     */
    @Override
    public String getQuestionDescription() {
        return super.getQuestionDescription().strip() + " / " + this.answer.strip() + " | " + super.getModule()
                + " | " + super.getDifficulty().toString();
    }

    @Override
    public String toString() {
        String questionType = "[" + IDENTIFIER + "]";
        String isDone;
        if (super.questionIsDone()) {
            isDone = "[X]";
        } else {
            isDone = "[ ]";
        }
        String assembledQuestion = questionType + isDone + " " + this.getQuestionDescription();
        return assembledQuestion.replace("\\slash", "/");
    }

    @Override
    public String toSerializedString() {
        String isDone;
        if (super.questionIsDone()) {
            isDone = "done";
        } else {
            isDone = "undone";
        }
        return IDENTIFIER + " | " + isDone + " | " + this.getQuestionDescription() + System.lineSeparator();
    }

    @Override
    public String checkAnswerValidity(String userAnswer) {
        if (userAnswer.isEmpty()) {
            return Question.ANSWER_BLANK_MSG;
        }
        return "valid";
    }

    @Override
    public boolean checkAnswerCorrectness(String validatedAnswer) {
        validatedAnswer = validatedAnswer.replace("/", "\\slash");
        return validatedAnswer.equalsIgnoreCase(this.answer);
    }
}
