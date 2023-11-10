package essenmakanan.shortcut;

import essenmakanan.exception.EssenOutOfRangeException;
import essenmakanan.ui.Ui;

import java.util.ArrayList;

public class ShortcutList {

    private ArrayList<Shortcut> shortcuts;

    public ShortcutList() {
        shortcuts = new ArrayList<>();
    }

    public ArrayList<Shortcut> getShortcuts() {
        return shortcuts;
    }

    public void addShortcut(Shortcut shortcut) {
        shortcuts.add(shortcut);
    }

    public Shortcut getShortcut(int index) throws EssenOutOfRangeException {
        if (index == -1) {
            throw new EssenOutOfRangeException();
        }

        Shortcut shortcut;

        try {
            shortcut = shortcuts.get(index);
        } catch (IndexOutOfBoundsException exception) {
            throw new EssenOutOfRangeException();
        }

        return shortcut;
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

    public int getIndex(String ingredientName) {
        int index = 0;

        for (Shortcut shortcut : shortcuts) {
            if (shortcut.getIngredientName().equals(ingredientName)) {
                return index;
            }
            index++;
        }

        return -1;
    }

    public boolean findShortcutInList(int index) {

        return false;
    }
}
