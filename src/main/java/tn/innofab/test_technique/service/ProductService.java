package tn.innofab.test_technique.service;

import tn.innofab.test_technique.entity.Category;
import tn.innofab.test_technique.entity.Product;
import tn.innofab.test_technique.response.ResponseProduct;
import tn.innofab.test_technique.response.ResponseProductWithSaleTariff;

import java.util.List;

public interface ProductService {

    List<ResponseProduct> listAllProducts();
    List<ResponseProductWithSaleTariff> listAllProductsWithSaleTariff();
    void removeProductFromStock(String productCode);
    void addProduct(Product product);
    Product retrieveProductDetailByProductCode(String productCode);
    List<Product> searchProducts(String brand, long category, String name);

}
