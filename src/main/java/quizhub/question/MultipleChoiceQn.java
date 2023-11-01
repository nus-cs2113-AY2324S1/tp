package quizhub.question;

public class MultipleChoiceQn extends Question {
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int answer;

    /**
     * Creates a new question of type MULTIPLECHOICE
     * @param option1
     * @param option2
     * @param option3
     * @param option4
     * @param answer
     */
    public MultipleChoiceQn (String description, String option1, String option2,
                             String option3, String option4, int answer,
                             String module, QnDifficulty qnDifficulty) {
        super(description, QnType.MULTIPLECHOICE, module, qnDifficulty);
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
    }

    /**
     * Get the Question's answer in integer format
     * @return Integer from 1 to 4 inclusive
     */
    public int getAnswerNumber() {
        return this.answer;
    }

    /**
     * Gets the Question's answer in String format
     * @return String corresponding to the correct option's String contents
     */
    public String getAnswerString() {
        switch(this.answer) {
        case 1: return option1;
        case 2: return option2;
        case 3: return option3;
        case 4: return option4;
        default: return null;
        }
    }

    // TODO: Make functionality to edit the question

    // TODO: Add getQuestionDescription to present in
}
