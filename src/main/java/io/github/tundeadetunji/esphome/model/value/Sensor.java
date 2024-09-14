package io.github.tundeadetunji.esphome.model.value;

import io.github.tundeadetunji.esphome.model.domain.Mode;
import io.github.tundeadetunji.esphome.model.domain.SensorPlatform;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Sensor {

    private final SensorPlatform platform;
    private final int pin;
    private List<Component> components = new ArrayList<>();
    private Mode mode;
    private String name;
    private Filter filter;

    private Sensor(SensorPlatform platform, int pin, List<Component> components, Mode mode, String name, Filter filter){
        this.platform = platform;
        this.pin = pin;
        this.components = components;
        this.mode = mode;
        this.name = name;
        this.filter = filter;
    }
    public static Sensor create(SensorPlatform platform, int pin, List<Component> components, Mode mode, String name, Filter filter){
        return new Sensor(platform, pin, components, mode, name, filter);
    }

    @Override
    public String toString() {
        String br = "\n";
        StringBuilder builder = new StringBuilder();
        builder.append("  - platform: " + this.platform.name() + "\n" +
                "    pin: " + this.pin + "\n");

        builder.append(!this.components.isEmpty() ? this.components.stream().map(Component::toString).collect(Collectors.joining("\n")) : "");
        builder.append(br);

        builder.append("    mode: " + this.mode.name() + "\n" +
                "    name: \"" + this.name + "\"");

        builder.append(br);
        builder.append(this.filter != null ? "    filters:" : "");
        builder.append(this.filter != null ? br : "");
        builder.append(this.filter != null ? this.filter.toString() : "");
        builder.append(this.filter != null ? br : "");

        return builder.toString();
    }
}
