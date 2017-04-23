package org.engdream.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
	
	public static final Logger ERROR_LOG = LoggerFactory.getLogger("error");
	
	public static void e(String message, Throwable e) {
        ERROR_LOG.error(message, e);
    }

    public static void w(String s) {
        System.out.println(s);
    }
}
