package essenmakanan.shortcut;

import essenmakanan.exception.EssenOutOfRangeException;
import essenmakanan.ui.Ui;

import java.util.ArrayList;

public class ShortcutList {

    private ArrayList<Shortcut> shortcuts;

    public ShortcutList() {
        shortcuts = new ArrayList<>();
    }

    public ShortcutList(ArrayList<Shortcut> shortcuts) {
        this.shortcuts = shortcuts;
    }

    public ArrayList<Shortcut> getShortcuts() {
        return shortcuts;
    }

    public void addShortcut(Shortcut shortcut) {
        shortcuts.add(shortcut);
    }

    public Shortcut getShortcut(int index) throws EssenOutOfRangeException {
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

    public void deleteShortcut(int index) throws EssenOutOfRangeException {
        try {
            Ui.printDeletedShortcut(getShortcut(index).getIngredientName());
            shortcuts.remove(index);
        } catch (IndexOutOfBoundsException exception) {
            throw new EssenOutOfRangeException();
        }
    }

    public boolean exist(String ingredientName) {
        for (Shortcut shortcut : shortcuts) {
            if (shortcut.getIngredientName().equals(ingredientName)) {
                return true;
            }
        }

        return false;
    }
}
