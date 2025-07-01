package LoggingFramework.ChainingLogger;

public class LogMessage {
    private String logMessage;
    private LogLevel logLevel;
    private long timestamp;
    private String threadName;

    public LogMessage(String logMessage, LogLevel logLevel) {
        this.logMessage = logMessage;
        this.logLevel = logLevel;
        this.timestamp = System.currentTimeMillis();
        this.threadName = Thread.currentThread().getName();
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }
}
