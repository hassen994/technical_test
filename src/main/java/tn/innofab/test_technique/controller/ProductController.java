package tn.innofab.test_technique.controller;

import org.springframework.web.bind.annotation.*;
import tn.innofab.test_technique.entity.Product;
import tn.innofab.test_technique.repository.ProductRepository;
import tn.innofab.test_technique.response.ResponseProduct;
import tn.innofab.test_technique.response.ResponseProductWithSaleTariff;
import tn.innofab.test_technique.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductController(ProductService productService,
                             ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }
    @GetMapping("/all")
    public List<ResponseProduct> listAllProducts(){
        return this.productService.listAllProducts();
    }
    @GetMapping("/saletariff")
    public List<ResponseProductWithSaleTariff> listAllProductsWithSaleTariff(){
        return this.productService.listAllProductsWithSaleTariff();
    }
    @DeleteMapping("/delete/{productCode}")
    public void removeProductFromStock(@PathVariable String productCode){
        this.productService.removeProductFromStock(productCode);
    }
    @PostMapping("/add")
    public void addProduct(@RequestBody Product product){
        this.productService.addProduct(product);
    }
    @GetMapping("/retrieve/{productCode}")
    public Product retrieveProductDetailByProductCode(@PathVariable String productCode){
        return this.productService.retrieveProductDetailByProductCode(productCode);
    }
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam(required = false) String brand,
                                        @RequestParam(required = false) long categoryId,
                                        @RequestParam(required = false) String name){
        return this.productService.searchProducts(brand,categoryId,name);
    }

}
