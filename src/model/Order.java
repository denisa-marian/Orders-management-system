package model;

public class Order {
    private int id;
    private int clientId;
    private int productId;
    private int quantity;

    public Order(int id, int clientId, int productId, int quantity) {
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Order(int clientId, int productId, int quantity) {
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    /**
     * Returns a string representation of the Order object,
     * including id, clientId, productId, and quantity
     */
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
