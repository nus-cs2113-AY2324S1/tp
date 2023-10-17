package seedu.financialplanner;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class FinancialPlannerLogger {
    private static Logger logger = Logger.getLogger("Financial Planner Logger");

    public static void initialise() {
        try {
            FileHandler fh = new FileHandler("data/logger.log");
            logger.addHandler(fh);
            fh.setFormatter(new SimpleFormatter());
            logger.log(Level.INFO, "Logger initialised");
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}
