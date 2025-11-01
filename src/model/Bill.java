package model;

/**
 * Represents a billing entry containing information about an order,
 * including client and product details, quantity, and total price
 */
public record Bill (int orderId, String clientName, String productName, int quantity, double totalPrice) {
}
