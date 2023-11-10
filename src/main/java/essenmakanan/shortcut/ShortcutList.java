package essenmakanan.shortcut;

import essenmakanan.ui.Ui;

import java.util.ArrayList;

public class ShortcutList {

    private ArrayList<Shortcut> shortcuts;

    public ShortcutList() {
        shortcuts = new ArrayList<>();
    }

    public void addShortcut(Shortcut shortcut) {
        shortcuts.add(shortcut);
    }

    public Shortcut getShortcut(int index) {
        return shortcuts.get(index);
    }

    public void listShortcuts() {
        Ui.drawDivider();

        if (shortcuts.isEmpty()) {
            System.out.println("No shortcuts found in session.");
        }

        int count = 1;

        for (Shortcut shortcut : shortcuts) {
            System.out.print(count + ". ");
            System.out.println(shortcut);
            count++;
        }
    }
}
