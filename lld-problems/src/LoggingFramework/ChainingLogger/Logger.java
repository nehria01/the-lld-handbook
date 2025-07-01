package LoggingFramework.ChainingLogger;

import LoggingFramework.ChainingLogger.chain.*;
import LoggingFramework.ChainingLogger.formatter.LogFormatter;
import LoggingFramework.ChainingLogger.formatter.SimpleFormatter;

public class Logger {
    private LogHandler logHandlerChain;
    private LogFormatter logFormatter;

    private Logger() {
        LogHandler debugLevelHandler = new DebugLeveLogHandler();
        LogHandler infoLevelHandler = new InfoLeveLogHandler();
        LogHandler warnLevelHandler = new WarnLeveLogHandler();
        LogHandler errorLevelHandler = new ErrorLeveLogHandler();
        debugLevelHandler.setNext(infoLevelHandler);
        infoLevelHandler.setNext(warnLevelHandler);
        warnLevelHandler.setNext(errorLevelHandler);

        logHandlerChain = debugLevelHandler;
        logFormatter = new SimpleFormatter();
    }

    private static Logger instance = null;

    public static Logger getInstance() {
        if(instance == null) {
            synchronized (Logger.class) {
                if(instance == null)
                    instance = new Logger();
            }
        }
        return instance;
    }

    public void setLogHandlerChain(LogHandler logHandlerChain) {
        this.logHandlerChain = logHandlerChain;
    }

    public void setLogFormatter(LogFormatter logFormatter) {
        this.logFormatter = logFormatter;
    }

    public void log(LogLevel logLevel, String message) {
        LogMessage logMessage = new LogMessage(message, logLevel);
        logHandlerChain.handle(logMessage, logFormatter);
    }
}
