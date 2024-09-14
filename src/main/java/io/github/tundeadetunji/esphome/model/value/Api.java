package io.github.tundeadetunji.esphome.model.value;

public final class Api {
    private final String password;

    private Api(String password){
        this.password = password;
    }

    public static Api create(String password){
        return new Api(password);
    }

    @Override
    public String toString() {
        return "api:\n" +
                "  password: !secret " + this.password;
    }
}
