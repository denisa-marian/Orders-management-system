package model;

/**
 * Represents a client entity with ID, name, and address.
 */
public class Client {
    private int id;
    private String name;
    private String email;
    private String address;

    public Client(int id, String name, String email, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public Client(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
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

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    /**
     * Returns a string representation of the Client object,
     * showing the id, name, email, and address
     */
    @Override
    public String toString() {
        return "Client [id=" + id + ", name=" + name + ", email=" + email + ", address=" + address + "]";
    }
}
