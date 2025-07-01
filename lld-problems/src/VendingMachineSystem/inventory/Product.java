package VendingMachineSystem.inventory;

public class Product {
    private java.lang.String code;
    private java.lang.String name;
    private double price;
    private int quantity;

    public Product(java.lang.String code, java.lang.String name, double price, int quantity) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public java.lang.String getCode() {
        return code;
    }

    public void setCode(java.lang.String code) {
        this.code = code;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}