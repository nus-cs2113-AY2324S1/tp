import quizhub.command.Command;
import quizhub.parser.Parser;
import quizhub.questionlist.QuestionList;
import quizhub.ui.Ui;
import quizhub.storage.Storage;

/**
 * The main program running for the QuizHub Application.
 * The program bridges the user interface (Ui),
 * hard disk data (dataStorage) and supports
 * dynamic data manipulation.
 */
public class Quizhub {
    private static final String FILE_PATH = "questionlist.txt";
    private final Ui ui;
    private Storage dataStorage;
    private QuestionList questions;
    private final Parser parser;
    /**
     * Initiates the program with the necessary components.
     *
     * @param filePath File location where data is
     *                 written to and read from by the program.
     */
    public Quizhub(String filePath){
        dataStorage = new Storage(filePath);
        questions = new QuestionList();
        ui = new Ui(dataStorage, questions);
        parser = new Parser(questions);
    }
    /**
     * Starts the QuizHub application by showing opening message.
     * QuizHub application repeatedly handles user commands
     * until it is requested to terminate.
     * QuizHub application closes by showing closing message.
     */
    public void run(){
        ui.displayOpeningMessage();
        boolean toExit = false;
        while(!toExit){
            String fullCommand = ui.getUserInput();
            Ui.showLine();
            Command command = Parser.parseCommand(fullCommand);
            toExit = command.toExit();
            command.executeCommand(ui, dataStorage, questions);
            if(!toExit){
                Ui.showLine();
            }
        }
        ui.displayClosingMessage();
    }

    public static void main(String[] args) {
        new Quizhub(FILE_PATH).run();
    }
}
