package essenmakanan.command;

import essenmakanan.exception.EssenOutOfRangeException;
import essenmakanan.parser.ShortcutParser;
import essenmakanan.shortcut.ShortcutList;
import essenmakanan.ui.Ui;

public class DeleteShortcutCommand extends Command {

    private ShortcutList shortcuts;
    private String input;

    public DeleteShortcutCommand(ShortcutList shortcuts, String input) {
        this.shortcuts = shortcuts;
        this.input = input;
    }

    @Override
    public void executeCommand() {
        try {
            input = input.replace("sc/", "");
            int shortcutIndex = ShortcutParser.getShortcutIndex(shortcuts, input);
            shortcuts.deleteShortcut(shortcutIndex);
        } catch (EssenOutOfRangeException exception) {
            Ui.drawDivider();
            exception.handleException();
            Ui.drawDivider();
        }
    }
}
