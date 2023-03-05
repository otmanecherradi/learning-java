package me.otmane.feb11th;

public class Product {
    private String name;
    private ElectronicType electronicType;

    public Product(String name, ElectronicType electronicType) {
        this.name = name;
        this.electronicType = electronicType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ElectronicType getElectronicType() {
        return electronicType;
    }

    public void setElectronicType(ElectronicType electronicType) {
        this.electronicType = electronicType;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", electronicType=" + electronicType +
                '}';
    }
}
