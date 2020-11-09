package seedu.duke.global;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

//@@author jerroldlam
/**
 * Class representing logging tool to log into text file without standard IO.
 */
public class LoggingTool {
    Logger logger;
    FileHandler fh;

    /**
     * Default constructor for LoggingTool.
     *
     * @param loggerName name of logger
     * @param fh FileHandler for Logger to log to
     */
    public LoggingTool(String loggerName, FileHandler fh) {
        logger = Logger.getLogger(loggerName);
        this.fh = fh;
    }

    /**
     * Sets up logger to log at destination file and disables conventional IO logging.
     * Note: Only logging of INFO and above is recorded.
     *
     * @return logger is configured
     */
    public Logger initialize() {
        formatHandlers();
        formatLogger();
        return logger;
    }

    /**
     * Formats handlers to log to file and not console with Level.INFO and higher.
     */
    private void formatHandlers() {
        logger.addHandler(fh);
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.INFO);
    }

    /**
     * Sets format for logger.
     */
    private void formatLogger() {
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
    }
}
