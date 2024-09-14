package io.github.tundeadetunji.esphome.model.domain;

import java.util.Arrays;
import java.util.List;

public enum Control {
    temperature,
    humidity;

    public static List<String> listing(){
        return Arrays.stream(Control.values()).map(Enum::name).toList();
    }
}
