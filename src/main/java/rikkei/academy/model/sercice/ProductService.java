package rikkei.academy.model.sercice;

import rikkei.academy.model.dao.ProductDAO;
import rikkei.academy.model.entity.Product;

import java.util.List;

public class ProductService {
    private ProductDAO productDAO = new ProductDAO();
    public List<Product> findAll() {
        return productDAO.findAll();
    }
    public Boolean create(Product product){
        return productDAO.create(product);
    }

    public Boolean delete(Integer id){
        return productDAO.delete(id);
    }

    public Product findById(Integer id) {
        return productDAO.findById(id);
    }

    public Boolean update(Product product,Integer id){
        return productDAO.update(product,id);
    }
}
