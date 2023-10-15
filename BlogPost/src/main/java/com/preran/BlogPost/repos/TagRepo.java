package com.preran.BlogPost.repos;


import com.preran.BlogPost.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepo extends JpaRepository<Tag, Long> {
    List<Tag> findAllByBlogId(long blogPostId);

    Tag findByBlogIdAndCategory(long blogId, String category);
}
