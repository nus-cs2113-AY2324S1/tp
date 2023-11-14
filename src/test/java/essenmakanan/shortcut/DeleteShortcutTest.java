package essenmakanan.shortcut;

import essenmakanan.command.DeleteShortcutCommand;
import essenmakanan.exception.EssenOutOfRangeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Executes tests related to deleting shortcuts.
 */
public class DeleteShortcutTest {

    private ShortcutList shortcuts;
    private DeleteShortcutCommand command;

    /**
     * Sets up attributes before each test.
     */
    @BeforeEach
    public void setup() {
        shortcuts = new ShortcutList();
        shortcuts.addShortcut(new Shortcut("bread", 2.0));
        shortcuts.addShortcut(new Shortcut("egg", 1.0));
        shortcuts.addShortcut(new Shortcut("cheese", 11.0));
    }

    /**
     * Executes a test related to deleting a shortcut.
     */
    @Test
    public void deleteShortcut_existingShortcut_expectLesserShortcutInList() {
        String userInput = "sc/cheese";
        command = new DeleteShortcutCommand(shortcuts, userInput);
        command.executeCommand();

        assertFalse(shortcuts.exist("cheese"));

        userInput = "sc/2";
        command = new DeleteShortcutCommand(shortcuts, userInput);
        command.executeCommand();

        assertFalse(shortcuts.exist("egg"));
    }

    /**
     * Execute a test related to deleting shortcut that is out of bounds.
     */
    @Test
    public void deleteShortcut_outOfBoundsIndex_expectEssenOutOfRangeException() {
        assertThrows(EssenOutOfRangeException.class, () -> {
            shortcuts.deleteShortcut(100);
        });
    }
}
