package essenmakanan.command;

public abstract class Command {

    boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }
}
