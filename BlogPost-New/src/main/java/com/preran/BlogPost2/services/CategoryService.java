package com.preran.BlogPost2.services;



import com.preran.BlogPost2.models.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);


    void deleteCategory(long categoryId);

    CategoryDto getCategory(long categoryId);

    List<CategoryDto> getCategories();
}
