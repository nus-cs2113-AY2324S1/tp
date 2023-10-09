package seedu.stocker.commands;

public abstract class Command {
    protected DrugList drugList;

    protected Command() {

    }

    public void setData(DrugList drugList) {
        this.drugList = drugList;
    }

}
