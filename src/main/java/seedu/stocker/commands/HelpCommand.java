package seedu.stocker.commands;


import seedu.stocker.drugs.StockEntry;

/**
 * Shows help instructions.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions. "
            + System.lineSeparator()
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult<StockEntry> execute() {
        return new CommandResult<>(
                System.lineSeparator() + AddCommand.MESSAGE_USAGE
                + System.lineSeparator()
                + System.lineSeparator() + DeleteCommand.MESSAGE_USAGE
                + System.lineSeparator()
                + System.lineSeparator() + HelpCommand.MESSAGE_USAGE
                + System.lineSeparator()
                + System.lineSeparator() + ListCommand.MESSAGE_USAGE
                + System.lineSeparator()
                + System.lineSeparator() + ShowStockLevelCommand.MESSAGE_USAGE
                + System.lineSeparator()
                + System.lineSeparator() + FindCommand.MESSAGE_USAGE
                + System.lineSeparator()
                + System.lineSeparator() + AddToCartCommand.MESSAGE_USAGE
                + System.lineSeparator()
                + System.lineSeparator() + CheckOutCommand.MESSAGE_USAGE
                + System.lineSeparator()
                + System.lineSeparator() + ViewCartCommand.MESSAGE_USAGE
                + System.lineSeparator()
                + System.lineSeparator() + LoginCommand.MESSAGE_USAGE
                + System.lineSeparator()
                + System.lineSeparator() + RegisterCommand.MESSAGE_USAGE
                + System.lineSeparator()
                + System.lineSeparator() + SaveCommand.MESSAGE_USAGE
                + System.lineSeparator()
                + System.lineSeparator() + ExitCommand.MESSAGE_USAGE

        );
    }
}
