package io.github.tundeadetunji.esphome.model.domain;

import java.util.Arrays;
import java.util.List;

public enum SensorPlatform {
    dht,
    gpio;

    public static List<String> listing(){
        return Arrays.stream(SensorPlatform.values()).map(Enum::name).toList();
    }
}
