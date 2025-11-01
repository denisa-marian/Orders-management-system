package dataAccess;

import model.Client;
import connection.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides database access methods for inserting, retrieving, updating, and deleting Client records
 */

public class ClientDAO {
    /**
     * Inserts a new client record into the database
     */
    public void insert(Client client) {
        String query = "INSERT INTO client (name, email, address) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, client.getName());
            ps.setString(2, client.getEmail());
            ps.setString(3, client.getAddress());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing client record in the database
     */
    public void update(Client client) {
        String query = "UPDATE client SET name = ?, email = ?, address = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, client.getName());
            ps.setString(2, client.getEmail());
            ps.setString(3, client.getAddress());
            ps.setInt(4, client.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a client record from the database by ID
     */
    public void delete(int id) {
        String query = "DELETE FROM client WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all client records from the database
     */
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM client";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Client c = new Client(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("address")
                );
                clients.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
}
