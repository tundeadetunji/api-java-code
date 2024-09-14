package io.github.tundeadetunji.esphome.model.domain;

import java.util.Arrays;
import java.util.List;

public enum TimingValue {
    One,
    Two,
    Three;

    public static String to(TimingValue value) {
        return value == One ?
                "1s" :
                value == Two ?
                        "2s" :
                        "3s";
    }

    public static List<String> listing(){
        return Arrays.stream(TimingValue.values()).map(Enum::name).toList();
    }

}
