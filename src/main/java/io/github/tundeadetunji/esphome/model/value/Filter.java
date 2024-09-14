package io.github.tundeadetunji.esphome.model.value;

import io.github.tundeadetunji.esphome.model.domain.TimingValue;

public final class Filter {

    private final TimingValue delayed_on;
    private final TimingValue delayed_off;

    private Filter(TimingValue delayed_on, TimingValue delayed_off){
        this.delayed_on = delayed_on;
        this.delayed_off = delayed_off;
    }

    public static Filter create(TimingValue delayedOn, TimingValue delayedOff){
        return new Filter(delayedOn, delayedOff);
    }

    @Override
    public String toString() {
        return "      - delayed_on: " + TimingValue.to(delayed_on) + "\n" +
                "      - delayed_off: " + TimingValue.to(delayed_off);
    }
}
