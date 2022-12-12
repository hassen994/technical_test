package tn.innofab.test_technique.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.innofab.test_technique.entity.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory,Long> {
}
