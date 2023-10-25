package quizhub.command;

import quizhub.exception.QuizHubExceptions;
import quizhub.parser.Parser;
import quizhub.question.Question;
import quizhub.questionlist.QuestionList;
import quizhub.storage.Storage;
import quizhub.ui.Ui;
/**
 * Represents a command to mark the difficulty of a question.
 */
public class CommandMarkDifficulty extends Command{

    private int qnIndex;
    private Question.QnDifficulty qnDifficulty = Question.QnDifficulty.DEFAULT;
    /**
     * Creates a new command to mark the difficulty of a question of specified index
     *
     * @param userInput User input from CLI.
     */
    public CommandMarkDifficulty(String userInput){
        super(CommandType.MARKDIFFICULTY);
        String[] commandDetails = userInput.split(" ");
        try {
            qnIndex = Integer.parseInt(commandDetails[1].strip());
            if(qnIndex < 0){
                throw new QuizHubExceptions("    Ono! Please enter valid question number *sobs*");
            }
        } catch (NumberFormatException incompleteCommand) {
            System.out.println(Parser.INVALID_INTEGER_INDEX_MSG);
            return;
        } catch (ArrayIndexOutOfBoundsException incompleteCommand) {
            System.out.println("    Ono! You did not indicate index of question to be marked :<");
            System.out.println("    Please format your input as markdiff [question number] [question difficulty]!");
            return;
        } catch (QuizHubExceptions invalidIndex){
            System.out.println(invalidIndex.getMessage());
            return;
        }
        try {
            qnDifficulty = Parser.extractQuestionDifficulty(commandDetails[2].strip());
            if(qnDifficulty == Question.QnDifficulty.DEFAULT){
                System.out.println("    No changes made to original question difficulty!");
            }
        } catch (ArrayIndexOutOfBoundsException incompleteCommand) {
            System.out.println("    Ono! You did not indicate difficulty to be assigned the question :<");
        }
    }

    @Override
    public void executeCommand(Ui ui, Storage dataStorage, QuestionList questions) {
        assert qnDifficulty != null;
        if(qnDifficulty != Question.QnDifficulty.DEFAULT) {
            questions.markQuestionDifficulty(qnIndex, qnDifficulty, true);
            dataStorage.updateData(questions);
        }
    }
}
