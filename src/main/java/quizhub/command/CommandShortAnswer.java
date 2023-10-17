package quizhub.command;

import quizhub.storage.Storage;
import quizhub.question.Question;
import quizhub.questionlist.QuestionList;
import quizhub.ui.Ui;
/**
 * Represents a command to add a quiz question.
 */
public class CommandShortAnswer extends Command {

    private final String userInput;
    /**
     * Creates a new question command to add a SHORTANSWER question
     *
     * @param userInput User input containing details of the SHORTANSWER question
     */
    public CommandShortAnswer(String userInput){
        super(CommandType.ADD);
        this.userInput = userInput;
    }

    /**
     * Get userInput item (still in the short Question / Answer format)
     * @return String userInput
     */
    public String getUserInput() {
        return userInput;
    }

    /**
     * Adds the SHORTANSWER question and updates storage data.
     *
     * @param ui User interface for interactions with user through CLI.
     * @param questions Current question list in the program.
     * @param dataStorage Hard disk storage for storing task data.
     */
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, QuestionList questions){
        questions.addToQuestionList(userInput, Question.qnType.SHORTANSWER, true);
        dataStorage.updateData(questions);
    }

}
