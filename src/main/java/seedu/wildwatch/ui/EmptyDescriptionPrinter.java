package seedu.wildwatch.ui;

import seedu.wildwatch.command.AddCommand;
import seedu.wildwatch.command.DeleteCommand;
import seedu.wildwatch.command.EditCommand;
import seedu.wildwatch.command.FindCommand;

public class EmptyDescriptionPrinter {
    public static void emptyDescriptionMessagePrinter(String description) {
        if (description == null) {
            System.out.println("OOPS!!! The description cannot be empty. :-(");
        } else if (description.equals(AddCommand.COMMAND_WORD)) {
            System.out.println("OOPS!!! The description of an add command cannot be empty. :-(");
        } else if (description.equals(DeleteCommand.COMMAND_WORD)) {
            System.out.println("OOPS!!! The description of a delete command cannot be empty. :-(");
        } else if (description.equals(EditCommand.COMMAND_WORD)) {
            System.out.println("OOPS!!! The description of an edit command cannot be empty. :-(");
        } else if (description.equals(FindCommand.COMMAND_WORD)) {
            System.out.println("OOPS!!! The description of a find command cannot be empty. :-(");
        } else {
            System.out.println("OOPS!!! The description cannot be empty. :-(");
        }
    }
}
