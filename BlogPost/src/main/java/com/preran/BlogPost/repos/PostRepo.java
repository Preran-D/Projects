package com.preran.BlogPost.repos;


import com.preran.BlogPost.entities.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Posts, Long> {

    Posts findByPostId(long postId);

    boolean existsByPostId(long postId);

    void deleteByPostId(long postId);

}
