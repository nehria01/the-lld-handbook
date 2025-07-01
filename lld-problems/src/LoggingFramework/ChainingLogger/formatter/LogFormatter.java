package LoggingFramework.ChainingLogger.formatter;

import LoggingFramework.ChainingLogger.LogMessage;

public interface LogFormatter {
    String format(LogMessage logMessage);
}
