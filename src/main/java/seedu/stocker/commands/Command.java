package seedu.stocker.commands;

import seedu.stocker.drugs.Inventory;
import seedu.stocker.drugs.SalesList;
import seedu.stocker.drugs.Cart;
import seedu.stocker.exceptions.StockerException;

import java.io.IOException;

public abstract class Command {

    protected Inventory inventory;
    protected SalesList salesList;
    protected Cart currentCart;

    protected Command() {

    }

    public void setData(Inventory inventory, SalesList salesList, Cart currentCart) {
        this.inventory = inventory;
        this.salesList = salesList;
        this.currentCart = currentCart;
    }

    public abstract CommandResult execute() throws IOException, StockerException;


}
