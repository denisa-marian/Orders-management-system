package businessLogic;

import dataAccess.ClientDAO;
import model.Client;
import java.util.List;

/**
 * Business logic layer for validating and managing clients
 */
public class ClientBLL {
    private final ClientDAO clientDAO = new ClientDAO();

    public void addClient(Client client) {
        clientDAO.insert(client);
    }

    public void updateClient(Client client) {
        clientDAO.update(client);
    }

    public void deleteClient(int id) {
        clientDAO.delete(id);
    }

    public List<Client> getAllClients() {
        return clientDAO.findAll();
    }
}
