package io.github.tundeadetunji.esphome.model;

import io.github.tundeadetunji.esphome.model.domain.BasePlatform;
import io.github.tundeadetunji.esphome.model.domain.Board;
import io.github.tundeadetunji.esphome.model.value.Api;
import io.github.tundeadetunji.esphome.model.value.Logger;
import io.github.tundeadetunji.esphome.model.value.Sensor;
import io.github.tundeadetunji.esphome.model.value.Wifi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Configuration {
    private final String name;
    private final BasePlatform platform;
    private final Board board;

    private final Wifi wifi;
    private final Api api;
    private List<Sensor> sensors = new ArrayList<>();
    private final Logger logger;

    public Configuration(String name, BasePlatform platform, Board board, Wifi wifi, Api api, List<Sensor> sensors, Logger logger){
        this.name = name;
        this.platform = platform;
        this.board = board;
        this.wifi = wifi;
        this.api = api;
        this.sensors = sensors;
        this.logger = logger;
    }
    public static Configuration create(String name, BasePlatform platform, Board board, Wifi wifi, Api api, List<Sensor> sensors, Logger logger){
        return new Configuration(name, platform, board, wifi, api, sensors, logger);
    }

    @Override
    public String toString() {

        String br = "\n" +
                "\n";

        StringBuilder builder = new StringBuilder();

        builder.append("esphome:\n" +
                "  name: " + this.name + "\n" +
                "  platform: " + this.platform.name() + "\n" +
                "  board: " + this.board.name() + "\n" +
                "\n");

        builder.append(this.wifi.toString());
        builder.append(br);
        builder.append(this.api.toString());
        builder.append(br);

        builder.append("sensor:");
        builder.append("\n");

        builder.append(this.sensors.stream().map(Sensor::toString).collect(Collectors.joining("\n")));
        builder.append("\n");

        builder.append("logger:");
        builder.append("\n");
        builder.append(this.logger.toString());

        return builder.toString();
    }
}
