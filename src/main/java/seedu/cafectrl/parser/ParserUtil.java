package seedu.cafectrl.parser;

import seedu.cafectrl.command.Command;
import seedu.cafectrl.data.CurrentDate;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.Sales;
import seedu.cafectrl.ui.Ui;

/**
 * Parser interface for external class to use Parser
 */
public interface ParserUtil {
    Command parseCommand(Menu menu, String userInput, Ui ui,
            Pantry pantry, Sales sales, CurrentDate currentDate);
}
