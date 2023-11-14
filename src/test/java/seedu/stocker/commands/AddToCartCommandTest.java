package seedu.stocker.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import seedu.stocker.drugs.Inventory;
import seedu.stocker.drugs.SalesList;
import seedu.stocker.drugs.Cart;
import seedu.stocker.vendors.VendorsList;

class AddToCartCommandTest {
    
    @Test
    public void executeTest() {
        AddCommand command = new AddCommand("Paracetamol", "12/05/2024", "PARA123", 12L, 21.90);
        Inventory inventory = new Inventory();
        Cart cart = new Cart();
        assertEquals(0, cart.getCurrentCart().size());
        SalesList salesList = new SalesList();

        command.setData(inventory, salesList, cart, new VendorsList());
        command.execute();
        AddToCartCommand command2 = new AddToCartCommand("PARA123", 2L);
        command2.setData(inventory, salesList, cart, new VendorsList());
        CommandResult<Object> result = command2.execute();
        assertEquals("New drug added in the current cart: Paracetamol", result.feedbackToUser);
        assertEquals(1, cart.getCurrentCart().size());
    }
}
