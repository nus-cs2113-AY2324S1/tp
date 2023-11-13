package essenmakanan.command;

import essenmakanan.shortcut.ShortcutList;
import essenmakanan.ui.Ui;

public class ViewShortcutsCommand extends Command {

    private ShortcutList shortcuts;

    public ViewShortcutsCommand(ShortcutList shortcuts) {
        this.shortcuts = shortcuts;
    }

    @Override
    public void executeCommand() {
        Ui.printAllShortcuts(shortcuts);
    }
}
