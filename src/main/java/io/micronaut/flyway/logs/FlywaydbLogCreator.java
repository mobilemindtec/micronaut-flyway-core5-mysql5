package io.micronaut.flyway.logs;

import org.flywaydb.core.api.logging.Log;
import org.flywaydb.core.api.logging.LogCreator;

public class FlywaydbLogCreator implements LogCreator {

    @Override
    public Log createLogger(Class<?> aClass) {
        return new FlywaydbLog(aClass);
    }

}
