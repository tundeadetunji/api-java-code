package io.github.tundeadetunji.cycles.model.domain;

import java.util.Arrays;

public enum DayPeriod {
    A,
    B,
    C,
    D,
    E,
    F,
    G;

    public static String[] listing(){
        return Arrays.stream(DayPeriod.values()).map(Enum::name).toArray(String[]::new);
    }

    public static String createRefreshHeadline(DayPeriod period){
        return "Currently in the " + period.name() + " period.";
    }

    public static String createSearchHeadline(DayPeriod period){
        return "The " + period.name() + " period.";
    }

    public static String to(DayPeriod period){
        return period.name() + " period";
    }
}
