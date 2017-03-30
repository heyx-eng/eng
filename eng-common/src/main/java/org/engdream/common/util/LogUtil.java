package org.engdream.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
	
	public static final Logger ERROR_LOG = LoggerFactory.getLogger("error-log");
	
	public static void e(String message, Throwable e) {
        ERROR_LOG.error(message, e);
    }
}
