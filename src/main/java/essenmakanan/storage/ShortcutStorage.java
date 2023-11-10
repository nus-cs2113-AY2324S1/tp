package essenmakanan.storage;

import essenmakanan.exception.EssenFileNotFoundException;
import essenmakanan.exception.EssenInvalidEnumException;
import essenmakanan.exception.EssenStorageDuplicateException;
import essenmakanan.exception.EssenStorageFormatException;
import essenmakanan.logger.EssenLogger;
import essenmakanan.parser.ShortcutParser;
import essenmakanan.shortcut.Shortcut;
import essenmakanan.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShortcutStorage {
    private String dataPath;

    private ArrayList<Shortcut> shortcutListPlaceholder;

    public ShortcutStorage(String path) {
        shortcutListPlaceholder = new ArrayList<>();
        dataPath = path;
    }

    public void saveData(ArrayList<Shortcut> shortcuts) {
        try {
            FileWriter writer = new FileWriter(dataPath, false);
            String dataString;

            EssenLogger.logInfo("Transferring shortcut data");
            for (Shortcut shortcut : shortcuts) {
                dataString = ShortcutParser.convertToString(shortcut);
                writer.write(dataString);
                writer.write(System.lineSeparator());
            }

            writer.close();
            EssenLogger.logInfo("Shortcut data has been successfully saved");
        } catch (IOException exception) {
            Ui.handleIOException(exception);
            EssenLogger.logSevere("Unable to save shortcut data", exception);
        }
    }

    private boolean searchDuplicate(String shortcutName) {
        for (Shortcut shortcut : shortcutListPlaceholder) {
            if (shortcut.getIngredientName().equals(shortcutName)) {
                return true;
            }
        }

        return false;
    }

    private void createNewData(Scanner scan) {
        String dataString = scan.nextLine();
        String[] parsedShortcut = dataString.trim().split(" \\| ");

        EssenLogger.logInfo("Retrieving shortcut data");
        try {
            if (parsedShortcut.length != 2) {
                throw new EssenStorageFormatException();
            }

            String shortcutName = parsedShortcut[0];
            double shortcutQuantity = Double.parseDouble(parsedShortcut[1]);

            if (searchDuplicate(shortcutName)) {
                throw new EssenStorageDuplicateException();
            }

            shortcutListPlaceholder.add(new Shortcut(shortcutName, shortcutQuantity));
        } catch (EssenStorageFormatException exception) {
            exception.handleException(dataString);
            String message = "Data: " + dataString + " has an invalid format";
            EssenLogger.logWarning(message, exception);
        } catch (IllegalArgumentException exception) {
            EssenInvalidEnumException.handleException(dataString);
            String message = "Data: " + dataString + " has an invalid enum";
            EssenLogger.logWarning(message, exception);
        } catch (EssenStorageDuplicateException exception) {
            exception.handleException(dataString);
            String message = "Data: " + dataString + " cannot be created due to duplicates";
            EssenLogger.logWarning(message, exception);
        }
        EssenLogger.logInfo("Saved shortcut data has been received");
    }

    public ArrayList<Shortcut> restoreSavedData() throws EssenFileNotFoundException {
        try {
            File file = new File(dataPath);
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                createNewData(scan);
            }
        } catch (FileNotFoundException exception) {
            EssenLogger.logWarning("Text file not found", exception);
            throw new EssenFileNotFoundException();
        }

        return shortcutListPlaceholder;
    }
}

