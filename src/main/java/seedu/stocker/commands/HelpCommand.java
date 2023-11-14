package seedu.stocker.commands;
//@@author TeoHaoZhi

/**
 * Shows help instructions.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions. "
            + System.lineSeparator()
            + "Example: " + COMMAND_WORD;

    @Override
    public  <T> CommandResult<T> execute() {
        return new CommandResult<>(
                System.lineSeparator() + LoginCommand.MESSAGE_USAGE + System.lineSeparator()
                        + System.lineSeparator() + RegisterCommand.MESSAGE_USAGE + System.lineSeparator()
                        + System.lineSeparator() + SaveCommand.MESSAGE_USAGE + System.lineSeparator()
                        + System.lineSeparator() + HelpCommand.MESSAGE_USAGE + System.lineSeparator()

                        + System.lineSeparator() + AddCommand.MESSAGE_USAGE + System.lineSeparator()
                        + System.lineSeparator() + DeleteCommand.MESSAGE_USAGE + System.lineSeparator()

                        + System.lineSeparator() + ListCommand.MESSAGE_USAGE + System.lineSeparator()
                        + System.lineSeparator() + ShowStockLevelCommand.MESSAGE_USAGE + System.lineSeparator()

                        + System.lineSeparator() + FindCommand.MESSAGE_USAGE + System.lineSeparator()

                        + System.lineSeparator() + SetThresholdCommand.MESSAGE_USAGE + System.lineSeparator()
                        + System.lineSeparator() + ListThresholdCommand.MESSAGE_USAGE + System.lineSeparator()

                        + System.lineSeparator() + AddToCartCommand.MESSAGE_USAGE + System.lineSeparator()
                        + System.lineSeparator() + ViewCartCommand.MESSAGE_USAGE + System.lineSeparator()
                        + System.lineSeparator() + CheckOutCommand.MESSAGE_USAGE + System.lineSeparator()

                        + System.lineSeparator() + SaveSalesCommand.MESSAGE_USAGE + System.lineSeparator()
                        + System.lineSeparator() + ListSalesCommand.MESSAGE_USAGE + System.lineSeparator()

                        + System.lineSeparator() + AddVendorCommand.MESSAGE_USAGE + System.lineSeparator()
                        + System.lineSeparator() + DeleteVendorCommand.MESSAGE_USAGE + System.lineSeparator()
                        + System.lineSeparator() + ListVendorCommand.MESSAGE_USAGE + System.lineSeparator()

                        + System.lineSeparator() + AddVendorSupplyCommand.MESSAGE_USAGE + System.lineSeparator()
                        + System.lineSeparator() + ListVendorSupplyCommand.MESSAGE_USAGE + System.lineSeparator()
                        + System.lineSeparator() + FindVendorSupplyCommand.MESSAGE_USAGE + System.lineSeparator()
                        + System.lineSeparator() + DeleteVendorSupplyCommand.MESSAGE_USAGE + System.lineSeparator()

                        + System.lineSeparator() + AddDescriptionCommand.MESSAGE_USAGE + System.lineSeparator()
                        + System.lineSeparator() + GetDescriptionCommand.MESSAGE_USAGE + System.lineSeparator()
                        + System.lineSeparator() + ListDescriptionsCommand.MESSAGE_USAGE + System.lineSeparator()

                        + System.lineSeparator() + ExitCommand.MESSAGE_USAGE

        );
    }
}
