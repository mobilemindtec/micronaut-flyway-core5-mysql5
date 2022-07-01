package io.micronaut.flyway.logs;

import org.flywaydb.core.api.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlywaydbLog implements Log {


    private final Logger logger;

    public FlywaydbLog(Class<?> aClass){
        this.logger = LoggerFactory.getLogger(aClass);
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    public void debug(String s) {
        logger.debug(s);
    }

    @Override
    public void info(String s) {
        logger.info(s);
    }

    @Override
    public void warn(String s) {
        logger.warn(s);
    }

    @Override
    public void error(String s) {
        logger.error(s);
    }

    @Override
    public void error(String s, Exception e) {
        logger.error(s, e);
    }
}
