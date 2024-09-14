package io.github.tundeadetunji.esphome.model.domain;

import java.util.Arrays;
import java.util.List;

public enum Board {
    esp32dev;

    public static List<String> listing(){
        return Arrays.stream(Board.values()).map(Enum::name).toList();
    }
}
