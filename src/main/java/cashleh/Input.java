package cashleh;

import java.util.Scanner;

public class Input {
    private String inputString;
    private Scanner in = new Scanner(System.in);

    public Input() {
        inputString = "";
    }

    public String getInputString() {
        if (in.hasNextLine()) {
            inputString = in.nextLine();
        }
        return inputString;
    }
}
