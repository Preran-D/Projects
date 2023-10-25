package com.preran.BlogPost2.repositories;


import com.preran.BlogPost2.entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
