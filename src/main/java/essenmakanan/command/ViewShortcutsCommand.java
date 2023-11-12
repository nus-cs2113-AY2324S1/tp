package essenmakanan.command;

import essenmakanan.shortcut.ShortcutList;
import essenmakanan.ui.Ui;

/**
 * Represents a view shortcuts command.
 */
public class ViewShortcutsCommand extends Command {

    private ShortcutList shortcuts;

    /**
     * Creates a view shortcuts command.
     *
     * @param shortcuts The shortcut list.
     */
    public ViewShortcutsCommand(ShortcutList shortcuts) {
        this.shortcuts = shortcuts;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void executeCommand() {
        Ui.printAllShortcuts(shortcuts);
    }
}
