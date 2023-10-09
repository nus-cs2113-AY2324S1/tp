package fittrack.command;

public class CommandResult {
    private String feedback;

    public CommandResult(String feedback) {
        setFeedback(feedback);
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
