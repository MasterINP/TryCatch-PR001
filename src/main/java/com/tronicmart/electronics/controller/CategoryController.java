package com.tronicmart.electronics.controller;

import com.tronicmart.electronics.Common.ApiResponse;
import com.tronicmart.electronics.model.Category;
import com.tronicmart.electronics.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //create category
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCtegory(@RequestBody Category category) {
        categoryService.createCategory(category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "created the category"), HttpStatus.CREATED);
    }

    //get all categories
    @GetMapping("/list")
    public List<Category> listCategory() {
        return categoryService.listCategory();
    }

    //update category
    @PostMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryID") Integer categoryID, @RequestBody Category category) {
        System.out.println("category id " + categoryID);
        if (!categoryService.findById(categoryID)) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category does not exist"), HttpStatus.NOT_FOUND);
        }
        categoryService.editCategory(categoryID, category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "updated the category"), HttpStatus.OK);
    }

}
