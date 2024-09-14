package io.github.tundeadetunji.esphome.model.domain;

import java.util.Arrays;
import java.util.List;

public enum Unit {
    Celsius,
    Percentage;

    public static String to(Unit unit){
        return unit == Celsius ? "°C" : "%";
    }

    public static List<String> listing(){
        return Arrays.stream(Unit.values()).map(Enum::name).toList();
    }

}
