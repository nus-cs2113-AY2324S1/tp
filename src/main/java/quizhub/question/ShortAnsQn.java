package quizhub.question;
/**
 * Represents Short Answer Questions. This means answers are a simple string
 */
public class ShortAnsQn extends Question {
    private String answer;
    /**
     * Creates a new task of type SHORTANSWER.
     *
     * @param description Task description.
     */
    public ShortAnsQn(String description, String answer){
        super(description, qnType.SHORTANSWER);
        this.answer = answer;
    }

    public String getQuestionAnswer() {
        return this.answer;
    }
}
