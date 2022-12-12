package tn.innofab.test_technique.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.innofab.test_technique.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
