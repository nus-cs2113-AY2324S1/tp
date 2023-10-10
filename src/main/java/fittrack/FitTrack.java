package fittrack;

import fittrack.command.Command;
import fittrack.command.CommandResult;
import fittrack.command.ExitCommand;
import fittrack.parser.CommandParser;

/**
 * Represents the main part of FitTrack.
 */
public class FitTrack {
    private final UserProfile userProfile;
    private final MealList meals;
    private final WorkList works;
    private final Ui ui;

    private FitTrack() {
        ui = new Ui();

        userProfile = new UserProfile();
        meals = new MealList();
        works = new WorkList();
    }

    /**
     * Main entry-point for the FitTrack application.
     */
    public static void main(String[] args) {
        new FitTrack().run();
    }

    private void run() {
        start();
        loopCommandExecution();
        end();
    }

    private void start() {
        ui.printWelcome();
    }

    private void loopCommandExecution() {
        Command command;
        do {
            String userCommandLine = ui.scanCommandLine();
            command = new CommandParser().parseCommand(userCommandLine);
            CommandResult commandResult = executeCommand(command);
            ui.printCommandResult(commandResult);
        } while (!ExitCommand.isExit(command));
    }

    private CommandResult executeCommand(Command command) {
        command.setData(userProfile, meals, works);
        return command.execute();
    }

    private void end() {
    }
}
