package quizhub.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomLogger {
    private static final Logger logger = Logger.getLogger(CustomLogger.class.getName());

    // Log a message at the INFO level
    public static void info(String message) {
        logger.log(Level.INFO, message);
    }

    // Log a message at the WARNING level
    public static void warning(String message) {
        logger.log(Level.WARNING, message);
    }

    // Log an error message at the SEVERE level
    public static void error(String message) {
        logger.log(Level.SEVERE, message);
    }
}
