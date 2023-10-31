package seedu.financialplanner;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.LogManager;

/**
 * Represents the logger for the Financial Planner.
 */
public class FinancialPlannerLogger {
    private static Logger logger = Logger.getLogger("Financial Planner Logger");

    /**
     * Initialises the logger and saves logging info to a file.
     */
    public static void initialise() {
        try {
            FileHandler fh = new FileHandler("data/logger.log");
            LogManager.getLogManager().reset();
            logger.addHandler(fh);
            fh.setFormatter(new SimpleFormatter());
            logger.log(Level.INFO, "Logger initialised");
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}
