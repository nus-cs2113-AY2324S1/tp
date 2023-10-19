package essenmakanan.parser;

import essenmakanan.command.*;
import essenmakanan.exception.EssenMakananCommandException;

public class Parser {

    public Command parseCommand(String input) throws EssenMakananCommandException {
        Command command;

        String[] parsedInput = input.split(" ", 2);
        String commandType = parsedInput[0];
        String inputDetail = parsedInput.length == 1 ? "" : parsedInput[1].trim();

        switch (commandType) {
        case "add":
            if (inputDetail.substring(0,2).equals("r/")) {
                command = new AddRecipeCommand(inputDetail);
            } else if (inputDetail.substring(0,2).equals("i/")) {
                command = new AddIngredientCommand(inputDetail);
            } else {
                System.out.println("Invalid add command");
                throw new EssenMakananCommandException();
            }
            break;
        case "view":
            if (inputDetail.equals("r")) {
                command = new ViewRecipesCommand();
            } else if (inputDetail.equals("i")) {
                command = new ViewIngredientsCommand();
            } else {
                throw new EssenMakananCommandException();
            }
            break;
        case "help":
            command = new HelpCommand();
            break;
        case "exit":
            command = new ExitCommand();
            break;
        default:
            throw new EssenMakananCommandException();
        }

        return command;
    }
}
