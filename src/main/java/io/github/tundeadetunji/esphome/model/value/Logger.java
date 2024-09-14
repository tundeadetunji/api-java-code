package io.github.tundeadetunji.esphome.model.value;

import io.github.tundeadetunji.esphome.model.domain.LogLevel;

public final class Logger {
    private final LogLevel level;

    private Logger(LogLevel level){
        this.level = level;
    }

    public static Logger create(LogLevel level){
        return new Logger(level);
    }

    @Override
    public String toString() {
        return "  level: " + level.name();
    }
}
