package io.github.tundeadetunji.esphome.model.domain;

import java.util.Arrays;
import java.util.List;

public enum BasePlatform {
    ESP32;

    public static List<String> listing(){
        return Arrays.stream(BasePlatform.values()).map(Enum::name).toList();
    }

}