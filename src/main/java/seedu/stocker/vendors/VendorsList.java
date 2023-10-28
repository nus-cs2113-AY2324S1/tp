package seedu.stocker.vendors;

import java.util.ArrayList;

public class VendorsList {

    public static ArrayList<Vendor> vendorArrayList;

    public VendorsList(){
        vendorArrayList = new ArrayList<>();
    }

    public static void addNewVendor(Vendor vendor){
        vendorArrayList.add(vendor);
    }
}
