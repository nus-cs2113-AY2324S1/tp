import quizhub.command.Command;
import quizhub.parser.Parser;
import quizhub.questionlist.QuestionList;
import quizhub.ui.Ui;
import quizhub.storage.Storage;

/**
 * The main program running for the Duke chatbot.
 * The program bridges the user interface (Ui),
 * hard disk data (dataStorage) and supports
 * dynamic data manipulation.
 */
public class Quizhub {
    private Ui ui;
    private Storage dataStorage;
    private QuestionList questions;
    private Parser parser;
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
        parser = new Parser();
    }
    /**
     * Starts the chatbot by showing opening message.
     * Chatbot repeatedly handles user commands
     * until it is requested to terminate.
     * Chatbot closes by showing closing message.
     */
    public void run(){
        ui.displayOpeningMessage();
        boolean toExit = false;
        while(!toExit){
            String fullCommand = ui.readCommand();
            ui.showLine();
            Command command = parser.parseCommand(fullCommand);
            toExit = command.toExit();
            command.executeCommand(ui, dataStorage, questions);
            if(!toExit){
                ui.showLine();
            }
        }
        ui.displayClosingMessage();
    }

    public static void main(String[] args) {
        new Quizhub("tasklist.txt").run();
    }
}
