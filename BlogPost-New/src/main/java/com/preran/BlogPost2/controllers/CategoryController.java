package com.preran.BlogPost2.controllers;


import com.preran.BlogPost2.exceptions.CustomResponse;
import com.preran.BlogPost2.models.CategoryDto;
import com.preran.BlogPost2.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto createCategoryDto = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createCategoryDto, HttpStatus.CREATED);
    }


    @GetMapping("/delete/{categoryId}")
    public ResponseEntity<CustomResponse> deleteCategory(@PathVariable long categoryId) {
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(
                new CustomResponse("Category Deleted successfully", true), HttpStatus.OK
        );
    }

    @GetMapping("/byId/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable long categoryId) {
        return new ResponseEntity<>(this.categoryService.getCategory(categoryId), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryDto>> getCategories() {
        List<CategoryDto> categoryDtoList = this.categoryService.getCategories();
        return ResponseEntity.ok(categoryDtoList);
    }

}
