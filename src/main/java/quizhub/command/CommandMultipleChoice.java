package quizhub.command;

import quizhub.question.Question;
import quizhub.questionlist.QuestionList;
import quizhub.storage.Storage;
import quizhub.ui.Ui;

public class CommandMultipleChoice extends Command {
    // TODO: Add everything else that is in CommandShortAnswer (or abstract?? idk)
    private final String description;
    private final String module;
    private final Question.QnDifficulty qnDifficulty;
    private final int answer;
    private final String option1;
    private final String option2;
    private final String option3;
    private final String option4;

    /**
     * Creates a new question command to add a MULTIPLECHOICE question
     *
     * @param description Question description
     * @param option1 First option
     * @param option2 Second option
     * @param option3 Third option
     * @param option4 Fourth option
     * @param answer The answer to the question (1, 2, 3 or 4)
     * @param module module of question
     * @param qnDifficulty difficulty of question
     */
    public CommandMultipleChoice(String description, String option1, String option2,
                                 String option3, String option4, int answer,
                                 String module, Question.QnDifficulty qnDifficulty) {
        super(CommandType.ADD);
        this.description = description;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.module = module;
        this.qnDifficulty = qnDifficulty;
    }

    /**
     * Adds the MULTIPLECHOICE question and updates storage data.
     *
     * @param ui User interface for interactions with user through CLI.
     * @param questions Current question list in the program.
     * @param dataStorage Hard disk storage for storing question data.
     */
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, QuestionList questions){
        questions.addMultipleChoiceQn(description, option1, option2, option3,
                option4, answer, module, qnDifficulty, true);
        dataStorage.updateData(questions);
    }
}
