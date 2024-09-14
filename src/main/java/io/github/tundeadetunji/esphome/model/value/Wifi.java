package io.github.tundeadetunji.esphome.model.value;

public final class Wifi {
    private final String ssid;
    private final String password;

    public Wifi (String ssid, String password) {
        this.ssid = ssid;
        this.password = password;
    }
    public static Wifi create(String ssid, String password) {
        return new Wifi(ssid, password);
    }

    @Override
    public String toString() {
        return "wifi:\n" +
                "  ssid: !secret " + this.ssid + "\n" +
                "  password: !secret " + this.password;
    }
}
