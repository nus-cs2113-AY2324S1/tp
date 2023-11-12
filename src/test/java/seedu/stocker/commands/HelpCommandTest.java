package seedu.stocker.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelpCommandTest {
    @Test
    public void executeTest() {
        HelpCommand command = new HelpCommand();

        String expectedOutput =
                System.getProperty("line.separator")

                + "login: Login new user into system."
                + System.getProperty("line.separator")
                + "Example: login"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")

                + "register: Register new user into system."
                + System.getProperty("line.separator")
                + "Example: register"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")

                + "saveDrugs: Saves existing druglist that is loaded into inventory when system is booted up."
                + System.getProperty("line.separator")
                + "Example: saveDrugs"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")

                + "help: Shows program usage instructions. "
                + System.getProperty("line.separator")
                + "Example: help"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")

                + "add: Adds a new drug to the drug list. Parameters: NAME, EXPIRY DATE, SERIAL NUMBER, " +
                "QUANTITY, PRICE"
                + System.getProperty("line.separator")
                + "Example: add /n Doliprane /d 12/06/2035 /s ABC123 /q 52 /p 12.90"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")

                + "delete: Removes a drug from drug list. Parameters: Serial Number  "
                + System.getProperty("line.separator")
                + "Example: delete /s <Serial Number>"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")

                + "list: List all drug information that is being tracked by the system. "
                + System.getProperty("line.separator")
                + "Example: list"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")

                + "stockLevel: List all drugs by quantity level tracked by the system in ascending order. "
                + System.getProperty("line.separator")
                + "Example: stockLevel"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")

                + "find /n: Finds drug in inventory using name."
                + System.getProperty("line.separator")
                + "Example: find /n panadol"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")
                + "find /d: Finds drug in inventory using date."
                + System.getProperty("line.separator")
                + "Example: find /d panadol"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")
                + "find /s: Finds drug in inventory using serial number."
                + System.getProperty("line.separator")
                + "Example: find /s ABC123"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")

                + "setThreshold: Set the threshold quantity for a drug. (default 100)"
                + System.getProperty("line.separator")
                + "Example: setThreshold /s TC150 /tq 50"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")

                + "listThreshold: List all drugs and their threshold levels."
                + System.getProperty("line.separator")
                + "Example: listThreshold"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")

                + "addToCart: Adds a new drug to the current cart. Parameters: SERIAL NUMBER, QUANTITY,"
                + System.getProperty("line.separator")
                + "Example: addToCart /s ABC123 /q 2"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")

                + "viewCart: View the current cart items and the total cost."
                + System.getProperty("line.separator")
                + "Example: viewCart"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")

                + "checkout: Checks out current cart. Parameters:"
                + System.getProperty("line.separator")
                + "Example: checkout"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")

                + "saveSales: Saves the current sold items to a file."
                + System.getProperty("line.separator")
                + "Example: saveSales"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")

                + "listSales: Lists all sold items."
                + System.getProperty("line.separator")
                + "Example: listSales"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")

                + "addVendor: Adds a new vendor to the vendors list. Parameter: NAME"
                + System.getProperty("line.separator")
                + "Example: addVendor /v Moderna"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")

                + "deleteVendor: Deletes a vendor from the vendors list. Parameter: NAME"
                + System.getProperty("line.separator")
                + "Example: deleteVendor /v Moderna"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")

                + "listVendors: List all vendors that are being tracked by the system."
                + System.getProperty("line.separator")
                + "Example: listVendors"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")

                + "addVendorSupply: Adds a drug to a vendor's supply list. Parameters: VENDOR_NAME, DRUG_NAME"
                + System.getProperty("line.separator")
                + "Example: addVendorSupply /v Moderna /n Paracetamol"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")

                + "listVendorSupply: Lists the drugs supplied by a specific vendor. Parameters: VENDOR_NAME"
                + System.getProperty("line.separator")
                + "Example: listVendorSupply /v Moderna"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")

                + "findVendorSupply: Lists the vendors that supply a specific drug. Parameters: DRUG_NAME"
                + System.getProperty("line.separator")
                + "Example: findVendorSupply /n Paracetamol"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")

                + "deleteVendorSupply: Deletes a drug from a vendor's supply list. Parameters: VENDOR_NAME, DRUG_NAME"
                + System.getProperty("line.separator")
                + "Example: deleteVendorSupply /v Moderna /n Paracetamol"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")

                + "addDescription: Adds a new description for a specific drug. Parameters: NAME, DESCRIPTION"
                + System.getProperty("line.separator")
                + "Example: addDescription /n Panadol /desc Pain Relief "
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")

                + "getDescription: Gets the description of a specific drug. Parameters: NAME"
                + System.getProperty("line.separator")
                + "Example: getDescription /n Panadol"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")

                + "listDescriptions: Lists all the descriptions for all drugs "
                + System.getProperty("line.separator")
                + "Example: listDescriptions"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")

                + "bye: Exits the program."
                + System.getProperty("line.separator")
                + "Example: bye";


        assertEquals(new CommandResult(expectedOutput).feedbackToUser, command.execute().feedbackToUser);
    }
}
