package me.otmane.feb11th;

public enum ElectronicType {
    Laptop("LAPTOP"),
    DISPLAY("DISPLAY");

    private final String value;

    ElectronicType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
