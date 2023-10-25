package com.preran.BlogPost2.repositories;

import com.preran.BlogPost2.entites.Category;
import com.preran.BlogPost2.entites.Post;
import com.preran.BlogPost2.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Long> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByContentContaining(String content);

    List<Post> findByTitleContaining(String title);




}
