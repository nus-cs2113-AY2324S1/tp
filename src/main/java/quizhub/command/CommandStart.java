package quizhub.command;

import quizhub.storage.Storage;
import quizhub.questionlist.QuestionList;
import quizhub.ui.Ui;

/**
 * Command to Start the Quiz
 */
public class CommandStart extends Command{
    private String startMode;
    private String startDetails = "";
    /**
     * Creates a new start command
     *
     * @param userInput User input from CLI.
     */
    public CommandStart(String userInput) {
        super(CommandType.START);
        String[] commandDetails = userInput.split("/");
        try {
            startMode = commandDetails[1].split(" ")[0].strip();
        } catch (ArrayIndexOutOfBoundsException incompleteCommand) {
            System.out.println("    Ono! You did not indicate mode of the quiz :<");
            System.out.println("    Please format your input as start /[quiz mode] [start details]!");
            return;
        }
        try {
            if(!startMode.equalsIgnoreCase("all")){
                startDetails = commandDetails[1].split(" ")[1].strip();
            }
        }  catch (ArrayIndexOutOfBoundsException incompleteCommand) {
            System.out.println("    Ono! You did not indicate start details :<");
            System.out.println("    Please format your input as start /[quiz mode] [start details]!");
        }
    }

    /**
     * Loop through the array list of questions & allow the user to answer them.
     * If the input given matches EXACTLY (v1.0), then the answer is correct
     * returns "Correct" or "Wrong"
     *
     * @param ui User interface for interactions with user through CLI.
     * @param questions Current question list in the program.
     * @param dataStorage Hard disk storage for storing question data.
     */
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, QuestionList questions) {
        assert questions != null && ui != null && dataStorage != null;
        if(startMode != null) {
            switch (startMode.toLowerCase()) {
            case "module":
                assert startDetails != null;
                questions.startQuiz(ui, questions.categoriseListByModule(startDetails));
                break;
            case "all":
                questions.startQuiz(ui, questions.getAllQns());
                break;
            default:
                System.out.println("    Please enter valid quiz mode :<");
                break;
            }
        }
    }
}
