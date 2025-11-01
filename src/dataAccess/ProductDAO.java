package dataAccess;

import connection.ConnectionFactory;
import model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles database operations for Product entities such as adding, finding, modifying, and removing entries
 */

public class ProductDAO {

    /**
     * Adds a new product record to the database
     */
    public void insertProduct(Product product) {
        String query = "INSERT INTO product (name, price, stock) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getStock());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the details of an existing product record in the database
     */
    public void updateProduct(Product product) {
        String query = "UPDATE product SET name = ?, price = ?, stock = ? WHERE id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getStock());
            statement.setInt(4, product.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes a product record from the database based on its identifier
     */
    public void deleteProduct(int id) {
        String query = "DELETE FROM product WHERE id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all product records from the database
     */
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM product";
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("stock"),
                        resultSet.getDouble("price")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    /**
     * Finds and returns a product record by its identifier
     */
    public Product findProductById(int id) {
        String query = "SELECT * FROM product WHERE id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("stock"),
                        resultSet.getDouble("price")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
