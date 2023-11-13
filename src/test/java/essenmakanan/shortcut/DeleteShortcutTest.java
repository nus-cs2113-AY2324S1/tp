package essenmakanan.shortcut;

import essenmakanan.command.DeleteShortcutCommand;
import essenmakanan.exception.EssenOutOfRangeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteShortcutTest {

    private ShortcutList shortcuts;
    private DeleteShortcutCommand command;

    @BeforeEach
    public void setup() {
        shortcuts = new ShortcutList();
        shortcuts.addShortcut(new Shortcut("bread", 2.0));
        shortcuts.addShortcut(new Shortcut("egg", 1.0));
        shortcuts.addShortcut(new Shortcut("cheese", 11.0));
    }

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

    @Test
    public void deleteShortcut_outOfBoundsIndex_expectEssenOutOfRangeException() {
        assertThrows(EssenOutOfRangeException.class, () -> {
            shortcuts.deleteShortcut(100);
        });
    }
}
