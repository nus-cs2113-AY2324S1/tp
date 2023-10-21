package quizhub.command;

import quizhub.storage.Storage;
import quizhub.questionlist.QuestionList;
import quizhub.ui.Ui;

public class CommandHelp extends Command{
    public CommandHelp() {
        super(CommandType.HELP);
    }

    /**
     * Displays all commands available to the user.
     * 
     * @param ui User interface for interactions with user through CLI.
     * @param questions Current question list in the program.
     * @param dataStorage Hard disk storage for storing task data.
     */
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, QuestionList questions) {
        System.out.println("    Here are the list of commands you can use:");
        System.out.println("    1. help - shows the list of commands you can use");
        System.out.println("    2. short [question]/[answer] - adds a short answer question " +
                "and its answer to the list");
        System.out.println("    3. list - shows the list of questions and answers");
        System.out.println("    4. delete [question number] - deletes the question and answer " +
                "at the specified number");
        System.out.println("    5. find /[description] - displays all questions that contains" +
                " the specified description");
        System.out.println("    6. edit [question number] /description - edits the question " +
                "with the specified number");
        System.out.println("    7. edit [question number] /answer - edits the answer to the question " +
                "with the specified number");
        System.out.println("    8. start - starts the quiz");
        System.out.println("    9. bye - exits the program");  
        return;
    }
}
