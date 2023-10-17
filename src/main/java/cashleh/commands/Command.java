package cashleh.commands;

import exceptions.CashLehException;

public class Command {
    private int index;

    public Command(int index) {
        this.index = index;
    }

    public Command() {
    }

    public int getIndex() {
        return this.index;
    }

    public void execute() throws CashLehException {
    }

}
