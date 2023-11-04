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
     * Get the Question's answerindex in String format
     * @return Integer from 1 to 4 inclusive
     */
    @Override
    public String getQuestionAnswer() {
        return Integer.toString(this.answer);
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

    /**
     * Gets a specific option in String format
     * @param optionNumber Integer from 1 to 4 inclusive
     * @return String corresponding to the correct option's String contents
     */
    @Override
    public String getOption(int optionNumber) {
        switch(optionNumber) {
        case 1: return option1;
        case 2: return option2;
        case 3: return option3;
        case 4: return option4;
        default: return null;
        }
    }

    public void editQuestion(String editField, String newValue) {
        switch (editField) {
        case "description":
            super.editQuestion(editField, newValue);
            break;
        case "option1":
            this.option1 = newValue;
            break;
        case "option2":
            this.option2 = newValue;
            break;
        case "option3":
            this.option3 = newValue;
            break;
        case "option4":
            this.option4 = newValue;
            break;
        case "answer":
            this.answer = Integer.parseInt(newValue);
            break;
        default:
            break;
        }
        System.out.println("    Roger that! I have edited the following question >w< !");
    }

    @Override
    public String getQuestionDescription() {
        return super.getQuestionDescription().strip() + " / " + this.option1.strip() + " / " + this.option2.strip() +
                " / " + this.option3.strip() + " / " + this.option4.strip() + " / " + this.answer + " | " +
                super.getModule() + " | " + super.getDifficulty().toString();
    }
}
