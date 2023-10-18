package seedu.cafectrl.ui;

public class Messages {

    // Error messages
    public static final String MISSING_ARGUMENT_FOR_EDIT_PRICE = "Error: Missing arguments "
                                                                 + "for edit price command.";

    public static final String MISSING_ARGUMENT_FOR_DELETE = "Error: Missing arguments "
                                                                 + "for delete command.";

    public static final String INVALID_DISH_INDEX = "Hmmm, can you double the your dish index, " 
                                                    + "I can't seem to find the dish you're referring to";
    public static final String WRONG_ARGUMENT_TYPE_FOR_EDIT_PRICE = "Oops, seems like you gave me " 
                                                                    + "the wrong type for dish index "
                                                                    + "or price. Make sure dish index is "
                                                                    + "of type int and price is of type float!";

    // Ui messages
    public static final String PRICE_MODIFIED_MESSAGE = "Price modified for the following dish: ";
}
