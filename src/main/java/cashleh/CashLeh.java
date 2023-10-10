package cashleh;

public class CashLeh {
    private final Ui ui = new Ui();
    private final Input input = new Input();
    /**
     * Main entry-point for the application.
     */
    public void run() {
        
        String logo = "    ______           __    __         __  ___  \n"
                    + "   / ____/___ ______/ /_  / /   ___  / /_/__ \\ \n"
                    + "  / /   / __ `/ ___/ __ \\/ /   / _ \\/ __ \\/ _/ \n"
                    + " / /___/ /_/ (__  ) / / / /___/  __/ / / /_/   \n"
                    + " \\____/\\__,_/____/_/ /_/_____/\\___/_/ /_(_)    \n";
        System.out.println("Here is the link to the user guide:"
                + "https://docs.google.com/document/d/"
                + "15h45BB5kMkTZ6bkwUHujpYwxVVl80tNEyNUsEVyk5AQ/edit?usp=drive_link");
        System.out.println(logo);
        System.out.println("Welcome to 'CashLeh?'! Your one-stop app for managing your finances!");
        String[] greetingLines = {"What is your name?"};

        ui.printMultipleText(greetingLines);
        String inputString = input.getInputString();
        ui.printText("Hello " + inputString);
        do {
            inputString = input.getInputString();
            if (inputString.equals("bye")) {
                ui.printText("Bye. Hope to see you again soon!");
            } else {
                ui.printText("Sorry, I don't understand what you mean.");
            } 
        } while (!inputString.equals("bye"));
    }

    public static void main(String[] args) {
        new CashLeh().run();
    }
}
