package seedu.stocker.vendors;

import java.util.ArrayList;
import java.util.List;

public class VendorsList {

    public ArrayList<Vendor> vendorArrayList;

    public VendorsList(){
        vendorArrayList = new ArrayList<>();
    }

    /**
     * Adds new vendor to vendor list
     *
     * @param vendor
     */
    public void addNewVendor(Vendor vendor){
        vendorArrayList.add(vendor);
    }

    public void deleteVendor(Vendor vendor){
        vendorArrayList.remove(vendor);
    }

    /**
     * Gets details of all vendors
     * @return List vendor
     */
    public List<Vendor> getVendorEntries() {
        return new ArrayList<>(this.vendorArrayList);
    }
}
