package essenmakanan.shortcut;

import java.util.ArrayList;

public class ShortcutList {

    private ArrayList<Shortcut> shortcuts;

    public ShortcutList() {
        shortcuts = new ArrayList<>();
    }

    public void addShortcut(Shortcut shortcut) {
        shortcuts.add(shortcut);
    }
}
