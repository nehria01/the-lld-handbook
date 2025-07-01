package LoggingFramework.ChainingLogger.chain;

import LoggingFramework.ChainingLogger.LogLevel;
import LoggingFramework.ChainingLogger.LogMessage;
import LoggingFramework.ChainingLogger.formatter.LogFormatter;

import java.util.Objects;

public class ErrorLeveLogHandler extends LogHandler{

    @Override
    public void setNext(LogHandler next) {
        this.next = next;
    }

    @Override
    public boolean canHandle(LogMessage logMessage) {
        return logMessage.getLogLevel().getSeverity() == LogLevel.ERROR.getSeverity();
    }

    @Override
    public void handle(LogMessage logMessage, LogFormatter logFormatter) {
        if(canHandle(logMessage)) {
            System.out.println(logFormatter.format(logMessage));
        } else if(Objects.nonNull(next))
            next.handle(logMessage, logFormatter);
    }
}
