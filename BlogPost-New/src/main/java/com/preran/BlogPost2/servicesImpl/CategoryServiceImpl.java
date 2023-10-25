package com.preran.BlogPost2.servicesImpl;

import com.preran.BlogPost2.entites.Category;
import com.preran.BlogPost2.exceptions.ResourceNotFoundException;
import com.preran.BlogPost2.models.CategoryDto;
import com.preran.BlogPost2.repositories.CategoryRepo;
import com.preran.BlogPost2.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;


    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);
        Category addCategory = this.categoryRepo.save(category);
        return this.modelMapper.map(addCategory, CategoryDto.class);
    }


    @Override
    public void deleteCategory(long categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
        this.categoryRepo.deleteById(categoryId);
    }

    @Override
    public CategoryDto getCategory(long categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Category", "CategoryId", categoryId)
                );
        return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = this.categoryRepo.findAll();
        return categories
                .stream()
                .map((cat) -> this.modelMapper.map(cat, CategoryDto.class))
                .collect(Collectors.toList());
    }
}
