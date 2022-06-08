package util;

import framework.LogLevels;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil {
    private static Logger logger;

    private LoggerUtil() {

    }

    public static void log(String message, LogLevels logLevel) {
        logger = LogManager.getLogger();
        switch (logLevel) {
            case WARN:
                logger.warn(message);
                break;
            case INFO:
                logger.info(message);
                break;
            case ERROR:
                logger.error(message);
                break;
        }
    }
}


