package seedu.stocker.drugs;

import java.util.ArrayList;
import java.util.List;

public class SalesList {

    private ArrayList<Cart> validatedCarts;
    
    public SalesList() {
        this.validatedCarts = new ArrayList<>();
    }

    public void addSale(Cart cart) {
        this.validatedCarts.add(cart);
    }

    public List<Cart> getAllSales() {
        return this.validatedCarts;
    }

}
