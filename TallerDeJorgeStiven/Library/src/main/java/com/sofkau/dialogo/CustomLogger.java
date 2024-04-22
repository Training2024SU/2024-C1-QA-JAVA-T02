package com.sofkau.dialogo;

import java.util.logging.Logger;

public class CustomLogger {
    private static final Logger LOGGER = Logger.getLogger(CustomLogger.class.getName());

    public static void info(String message){
        LOGGER.info(message);
    }
}
