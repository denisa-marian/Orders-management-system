package businessLogic;

import dataAccess.ProductDAO;
import model.Product;
import java.util.List;

/**
 * Business logic layer for validating and managing products
 */

public class ProductBLL {
    private final ProductDAO productDAO = new ProductDAO();

    public void addProduct(Product product) {
        productDAO.insertProduct(product);
    }

    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    public void deleteProduct(int id) {
        productDAO.deleteProduct(id);
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public Product getProductById(int id) {
        return productDAO.findProductById(id);
    }
}
