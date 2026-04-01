package ru.anoddyne.temperaturesensorserver.exception;

public class SensorNotFoundException extends RuntimeException {
    public SensorNotFoundException(String message) {
        super(message);
    }
    public SensorNotFoundException() {
        super("That sensor was not found!");
    }
}
