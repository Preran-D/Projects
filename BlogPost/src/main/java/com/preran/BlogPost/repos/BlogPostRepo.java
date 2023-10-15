package com.preran.BlogPost.repos;


import com.preran.BlogPost.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepo extends JpaRepository<Post, Long> {

    Post findByBlogId(long blogId);

    boolean existsByBlogId(long blogId);

    void deleteByBlogId(long blogId);

}
