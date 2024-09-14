package io.github.tundeadetunji.esphome.model.value;

import io.github.tundeadetunji.esphome.model.domain.Control;
import io.github.tundeadetunji.esphome.model.domain.Unit;

public final class Component {

    private final Control control;
    private final String name;
    private final Unit unit_of_measurement;

    private Component(Control control, String name, Unit unit){
        this.control = control;
        this.name = name;
        this.unit_of_measurement = unit;
    }

    public static Component create(Control control, String name, Unit unit){
        return new Component(control, name, unit);
    }

    @Override
    public String toString() {
        return "    " + control.name() + ":\n" +
                "      name: \"" + name + "\"\n" +
                "      unit_of_measurement: \"" + Unit.to(unit_of_measurement) + "\"";
    }
}
