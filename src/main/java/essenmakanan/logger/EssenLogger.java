package essenmakanan.logger;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * The class that handles logging of the application.
 */
public class EssenLogger {
    private static final String LOG_PATH = "data/essenmakanan.log";
    private static final String LOG_DIRECTORY = "data";

    private static Logger logger = Logger.getLogger("Storage");

    /**
     * Sets up the application with the selected configurations.
     */
    public static void setup() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.FINE);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.SEVERE);
        logger.addHandler(consoleHandler);

        File newLogger = new File(LOG_PATH);
        File directory = new File(LOG_DIRECTORY);

        try {
            if (!directory.isDirectory() && directory.mkdir()) {
                System.out.println("Directory successfully created");
            }

            if (!newLogger.isFile() && newLogger.createNewFile()) {
                System.out.println("Log file successfully created");
            }

            FileHandler logFile = new FileHandler("data/essenmakanan.log", false);
            logFile.setFormatter(new SimpleFormatter());
            logFile.setLevel(Level.FINE);
            logger.addHandler(logFile);
        } catch (IOException exception) {
            logger.log(Level.SEVERE, "Can't create logger.", exception);
        }
    }

    /**
     * Logs out a log with level info.
     *
     * @param message The given message.
     */
    public static void logInfo(String message) {
        logger.log(Level.INFO, message);
    }

    /**
     * Logs out a log with level warning.
     *
     * @param message The given message.
     * @param exception The triggered exception that relates to the message.
     */
    public static void logWarning(String message, Exception exception) {
        logger.log(Level.WARNING, message, exception);
    }

    /**
     * Logs out a log with level severe.
     *
     * @param message The given message.
     * @param exception The triggered exception that relates to the message.
     */
    public static void logSevere(String message, Exception exception) {
        logger.log(Level.SEVERE, message, exception);
    }
}
