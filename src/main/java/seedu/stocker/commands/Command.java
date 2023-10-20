package seedu.stocker.commands;

import seedu.stocker.drugs.Inventory;

import java.io.IOException;

public abstract class Command {

    protected Inventory inventory;

    protected Command() {

    }

    public void setData(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Executes the command and returns the result.
     */
    public abstract CommandResult execute() throws IOException;

}
