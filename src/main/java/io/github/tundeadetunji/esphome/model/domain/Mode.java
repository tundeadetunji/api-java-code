package io.github.tundeadetunji.esphome.model.domain;

import java.util.Arrays;
import java.util.List;

public enum Mode {
    INPUT_PULLUP;

    public static List<String> listing(){
        return Arrays.stream(Mode.values()).map(Enum::name).toList();
    }

}
