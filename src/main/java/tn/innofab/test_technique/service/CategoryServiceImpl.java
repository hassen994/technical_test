package tn.innofab.test_technique.service;

import org.springframework.stereotype.Service;
import tn.innofab.test_technique.entity.Category;
import tn.innofab.test_technique.repository.CategoryRepository;
@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
//En tant que gestionnaire, j’ai besoin de pouvoir ajouter une catégorie (Priorité 1)
    @Override
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }
}
