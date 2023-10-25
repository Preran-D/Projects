package com.preran.BlogPost2.repositories;


import com.preran.BlogPost2.entites.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Long> {
}
