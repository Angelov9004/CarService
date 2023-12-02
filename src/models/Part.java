package models;

public class Part {
    private String name;
    private double price;
    private int quantity;

    public Part(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public String getName() {
        return name;
    }

}
