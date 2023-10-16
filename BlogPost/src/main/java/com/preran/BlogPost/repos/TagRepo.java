package com.preran.BlogPost.repos;

import com.preran.BlogPost.entities.Tags;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepo extends JpaRepository<Tags, Long> {
    List<Tags> findAllByPostId(long postId);

    void deleteByPostId(long postId);

}
