package quizhub.parser;

import quizhub.command.*;
import quizhub.exception.QuizHubExceptions;
/**
 * Represents a parser that converts user inputs into command objects.
 */
public class Parser {
    /**
     * Analyses and extracts relevant information from user input
     * to create a new Command object of the right type.
     *
     * @param userInput The full user CLI input.
     */
    public Command parseCommand(String userInput) {
        String commandTitle = userInput.split(" ")[0];
        String commandDetails;
        int taskIndex;
        try {
            switch (commandTitle) {
                case "bye":
                    return new CommandExit();
                case "list":
                    return new CommandList();
                    /*
                case "mark":
                    commandDetails = userInput.split(" ")[1];
                    taskIndex = Integer.parseInt(commandDetails.strip());
                    return new CommandMark(taskIndex);
                case "unmark":
                    commandDetails = userInput.split(" ")[1];
                    taskIndex = Integer.parseInt(commandDetails.strip());
                    return new CommandUnmark(taskIndex);
                     */
                case "short":
                    return new CommandShortAnswer(userInput);
                case "delete":
                    commandDetails = userInput.split(" ")[1];
                    taskIndex = Integer.parseInt(commandDetails.strip());
                    return new CommandDelete(taskIndex);
                case "find":
                    return new CommandFind(userInput);
                default:
                    throw new QuizHubExceptions("Invalid Input");
            }
        }
        catch(NumberFormatException | ArrayIndexOutOfBoundsException invalidIndex){
            System.out.println("    Pwease enter valid integer index!");
        }
        catch (QuizHubExceptions exception){
            String exceptionMessage = exception.getMessage();
            if (exceptionMessage.equals("Invalid Input")) {
                System.out.println("    Pwease enter a valid command :0");
                // TODO : CHANGE THIS
                System.out.println("    Valid commands are: short [question]/[answer],\n" +
                        "                        list,\n" +
                        "                        delete [task number],\n" +
                        "                        find /description [description]\n" +
                        "                        bye");
            }
        }
        return new Command();
    }

}
