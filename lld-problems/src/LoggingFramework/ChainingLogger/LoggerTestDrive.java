package LoggingFramework.ChainingLogger;

import LoggingFramework.ChainingLogger.formatter.LogFormatter;
//import LoggingFramework.ChainingLogger.formatter.SimpleFormatter;

public class LoggerTestDrive {
    public static void main(String args[]) {
        Logger logger = Logger.getInstance();

        logger.log(LogLevel.WARN, "WARNING !!");

    }
}
