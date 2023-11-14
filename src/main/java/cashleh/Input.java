package cashleh;

import java.util.Scanner;

/**
 * Represents the input from the user
 */
public class Input {
    private String inputString;
    private Scanner in = new Scanner(System.in);

    public Input() {
        inputString = "";
    }

    public boolean hasNextLine() {
        return in.hasNextLine();
    }

    /**
     * Returns the next line of input from the user
     * @return Next line of input from the user
     */
    public String getInputString() {
        if (in.hasNextLine()) {
            inputString = in.nextLine();
        }
        return inputString;
    }
}
