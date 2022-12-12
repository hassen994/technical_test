package tn.innofab.test_technique.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class SubCategory extends Category{
    @ManyToOne
    private Category parentCategory;
}
