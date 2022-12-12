package tn.innofab.test_technique.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import tn.innofab.test_technique.entity.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>,JpaSpecificationExecutor<Product> {

    Optional<Product> findProductByProductCode(String productCode);

    List<Product> findAllByProductCode(String productCode);
}
