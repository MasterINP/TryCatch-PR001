package com.tronicmart.electronics.services;

import com.tronicmart.electronics.model.Category;
import com.tronicmart.electronics.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryrepository;
    public void createCategory(Category category) {
        categoryrepository.save(category);
    }

    public List<Category> listCategory(){
        return categoryrepository.findAll();
    }

    public void updateCategory(Integer categoryID, Category newCategory) {
        Category category = categoryrepository.findById(categoryID).get();
        category.setName(newCategory.getName());
        category.setDescription(newCategory.getDescription());
//        category.setProducts(newCategory.getProducts());

        categoryrepository.save(category);
    }

    public void editCategory(Integer categoryID, Category updatecategory) {
        Category category = categoryrepository.getById(categoryID);
        category.setName(updatecategory.getName());
        category.setDescription(updatecategory.getDescription());
//        category.setProducts(newCategory.getProducts());

        categoryrepository.save(category);
    }

    public boolean findById(int categoryID) {
        return categoryrepository.findById(categoryID).isPresent();
    }
}
