package essenmakanan.parser;

import essenmakanan.command.AddIngredientCommand;
import essenmakanan.command.AddRecipeCommand;
import essenmakanan.command.Command;
import essenmakanan.command.ExitCommand;
import essenmakanan.command.HelpCommand;
import essenmakanan.command.ViewIngredientsCommand;
import essenmakanan.command.ViewRecipesCommand;
import essenmakanan.exception.EssenMakananCommandException;
import essenmakanan.exception.EssenMakananFormatException;

public class Parser {

    public Command parseCommand(String input) throws EssenMakananCommandException, EssenMakananFormatException {
        Command command;

        String[] parsedInput = input.split(" ", 2);
        String commandType = parsedInput[0];
        String inputDetail = parsedInput.length == 1 ? "" : parsedInput[1].trim();

        switch (commandType) {
        case "add":
            if (!inputDetail.isEmpty() && inputDetail.startsWith("r/")) {
                command = new AddRecipeCommand(inputDetail);
            } else if (!inputDetail.isEmpty() && inputDetail.startsWith("i/")) {
                command = new AddIngredientCommand(inputDetail);
            } else {
                throw new EssenMakananFormatException();
            }
            break;
        case "view":
            if (inputDetail.equals("r")) {
                command = new ViewRecipesCommand();
            } else if (inputDetail.equals("i")) {
                command = new ViewIngredientsCommand();
            } else {
                throw new EssenMakananFormatException();
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
