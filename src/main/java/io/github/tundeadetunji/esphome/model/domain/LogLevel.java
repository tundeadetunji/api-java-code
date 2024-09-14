package io.github.tundeadetunji.esphome.model.domain;

import java.util.Arrays;
import java.util.List;

public enum LogLevel {
    DEBUG;

    public static List<String> listing(){
        return Arrays.stream(LogLevel.values()).map(Enum::name).toList();
    }
}
