package tn.innofab.test_technique.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tn.innofab.test_technique.entity.Category;
import tn.innofab.test_technique.entity.Product;
import tn.innofab.test_technique.repository.ProductRepository;
import tn.innofab.test_technique.response.ResponseProduct;
import tn.innofab.test_technique.response.ResponseProductWithSaleTariff;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
//En tant que gestionnaire, j’ai besoin de pouvoir lister tous les produits de mon
//commerce avec leurs quantités et leur valeur de stock (productId,name,
//quantité,quantité * purchaseTariff) (Priorité 1)
    @Override
    public List<ResponseProduct> listAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ResponseProduct> responseProducts = new ArrayList<ResponseProduct>();
        ResponseProduct responseProduct = new ResponseProduct();
        for (Product p : products){
            responseProduct.setProductId(p.getProductId());
            responseProduct.setName(p.getName());
            responseProduct.setQuantity(p.getQuantity());
            responseProduct.setStockValue(p.getQuantity()*p.getPurchaseTariff());
            responseProducts.add(responseProduct);
        }
        return responseProducts;
    }
//En tant que gestionnaire, j’ai besoin de pouvoir lister tous les produits de mon
//commerce avec leurs tarif de vente (Priorité 1)
    @Override
    public List<ResponseProductWithSaleTariff> listAllProductsWithSaleTariff() {
        List<Product> products = productRepository.findAll();
        List<ResponseProductWithSaleTariff> list = new ArrayList<ResponseProductWithSaleTariff>();
        ResponseProductWithSaleTariff responseWithST = new ResponseProductWithSaleTariff();
        for (Product p: products){
            responseWithST.setName(p.getName());
            responseWithST.setSaleTariff(p.getSaleTariff());
            list.add(responseWithST);
        }
        return list;
    }
//En tant que gestionnaire, j’ai besoin de retirer du stock un produit à partir de son
//code barre (Priorité 1)
    @Override
    public void removeProductFromStock(String productCode) {
        Product product = productRepository.findProductByProductCode(productCode).orElse(null);
        if (product == null){
            /*on n'a rien a faire :)*/
            return;
        }else product.setQuantity(product.getQuantity() - 1);
        productRepository.save(product);
    }
//En tant que gestionnaire, j’ai besoin de pouvoir ajouter un produit (Priorité 1)
    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }
//En tant que gestionnaire, j’ai besoin de connaître le prix d’un produit et les autres
//détails à partir de son code à barre (ProductCode) (Priorité 2)
    @Override
    public Product retrieveProductDetailByProductCode(String productCode) {
        Product product = productRepository.findProductByProductCode(productCode).orElse(null);
        List<Product> list = this.productRepository.findAllByProductCode(productCode);
        if (product == null){
            return null;
        }else  return product;
    }
//En tant que gestionnaire, j’ai besoin de pouvoir chercher un produit manuellement
//par marque, catégorie et/ou name pour en connaître la quantité et pour voir les
//autres détails (Priorité 2)
    @Override
    public List<Product> searchProducts(String brand, long categoryId, String name) {
        Specification<Product> specification = Specification.where(null);
        if (brand != null){
            specification = specification.and(hasBrand(brand));
        }
        if (categoryId != 0){
            specification = specification.and(hasCategory(categoryId));
        }
        if (name != null){
            specification = specification.and(hasName(name));
        }
        return productRepository.findAll(specification);
    }
    private Specification<Product> hasBrand(String brand){
        return (root, query, cb) -> cb.equal(root.get("brand"),brand);
    }
    private Specification<Product> hasCategory(long categoryId){
        return (root, query, cb) -> cb.equal(root.get("category"),categoryId);
    }
    private Specification<Product> hasName(String name){
        return (root, query, cb) -> cb.equal(root.get("name"),name);
    }

}
