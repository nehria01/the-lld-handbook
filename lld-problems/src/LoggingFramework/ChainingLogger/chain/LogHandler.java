package LoggingFramework.ChainingLogger.chain;

import LoggingFramework.ChainingLogger.LogMessage;
import LoggingFramework.ChainingLogger.formatter.LogFormatter;

public abstract class LogHandler {
    protected LogHandler next;
    public abstract void setNext(LogHandler next);
    public abstract boolean canHandle(LogMessage logMessage);
    public abstract void handle(LogMessage logMessage, LogFormatter logFormatter);
}
