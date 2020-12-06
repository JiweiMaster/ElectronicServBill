package main.log4j;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.logging.Logger;

public class MyLog {
    public static void info(String message){
        Log log = LogFactory.getLog(MyLog.class);
        log.info(message);
    }
}
