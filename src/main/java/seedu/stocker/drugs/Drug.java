package seedu.stocker.drugs;

public class Drug {

    public String name;
    String expiryDate;
    Long quantity;

    public Drug(String name, String expiryDate, Long quantity) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
    }


}
