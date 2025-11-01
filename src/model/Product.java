package model;

/**
 * Represents a product entity with ID, name, quantity, and price.
 */
public class Product {
    private int id;
    private String name;
    private int stock;
    private double price;

    public Product(int id, String name, int stock, double price) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    public Product(String name, int stock, double price) {
        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

    /**
     * Returns a string representation of the Product object,
     * showing id, name, stock quantity, and price
     */
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                '}';
    }
}
