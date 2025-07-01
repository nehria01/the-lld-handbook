package LoggingFramework.ChainingLogger.formatter;

import LoggingFramework.ChainingLogger.LogMessage;

public class SimpleFormatter implements LogFormatter{
    @Override
    public String format(LogMessage logMessage) {
        return String.format("[%s] [%s] [%s] [%s]: %s",
                logMessage.getLogMessage(),
                logMessage.getTimestamp(),
                logMessage.getLogLevel(),
                logMessage.getThreadName(),
                logMessage.getTimestamp());
    }
}
