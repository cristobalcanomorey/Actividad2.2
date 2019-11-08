package control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vista.Main;

public class LogSingleton {
	private static final LogSingleton INSTANCE = new LogSingleton();
	private Logger loggerMain = (Logger) LoggerFactory.getLogger(Main.class);

	private LogSingleton() {
	}

	public static LogSingleton getInstance() {
		return INSTANCE;
	}

	public Logger getLog() {
		return loggerMain;
	}

}