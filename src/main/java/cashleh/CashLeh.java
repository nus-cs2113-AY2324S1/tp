package cashleh;

import java.util.Scanner;

public class CashLeh {
    private final Ui ui = new Ui();
    /**
     * Main entry-point for the application.
     */
    public void run() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String[] greetingLines = {"Hello from\n" + logo, "What is your name?"};

        ui.printMultipleText(greetingLines);

        Scanner in = new Scanner(System.in);
        ui.printText("Hello " + in.nextLine());
    }

    public static void main(String[] args) {
        new CashLeh().run();
    }
}
