package seedu.stocker.commands;

import seedu.stocker.drugs.Inventory;
import seedu.stocker.drugs.SalesList;
import seedu.stocker.drugs.Cart;
import seedu.stocker.vendors.VendorsList;

import java.io.IOException;

public abstract class Command {

    protected Inventory inventory;
    protected SalesList salesList;
    protected Cart currentCart;
    protected VendorsList vendorsList;

    protected Command() {

    }

    public void setData(Inventory inventory, SalesList salesList, Cart currentCart, VendorsList vendorsList) {
        this.inventory = inventory;
        this.salesList = salesList;
        this.currentCart = currentCart;
        this.vendorsList = vendorsList;
    }

    public abstract <T> CommandResult<T> execute() throws IOException;


}
