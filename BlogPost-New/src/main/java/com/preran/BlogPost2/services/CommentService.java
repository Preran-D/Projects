package com.preran.BlogPost2.services;

import com.preran.BlogPost2.entites.Comment;
import com.preran.BlogPost2.models.CommentDto;
import com.preran.BlogPost2.models.PostDto;
import com.preran.BlogPost2.models.UserDto;


public interface CommentService {
    CommentDto createComment(CommentDto commentDto, long postId);
    void deleteComment(long commentId);

    Comment findById(long commentId);
}
