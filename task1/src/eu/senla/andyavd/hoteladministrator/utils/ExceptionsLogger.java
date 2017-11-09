package eu.senla.andyavd.hoteladministrator.utils;

import org.apache.log4j.Logger;

public class ExceptionsLogger {

	final static Logger logger = Logger.getLogger(ExceptionsLogger.class);

	public void callMeInAppInfo(String parameter) {
		if (logger.isInfoEnabled()) {
			logger.info("This is info : " + parameter);
		}
	}

	public void callMeInAppDebug(String parameter) {
		if (logger.isDebugEnabled()) {
			logger.debug("This is Debug : " + parameter);
		}
	}
}