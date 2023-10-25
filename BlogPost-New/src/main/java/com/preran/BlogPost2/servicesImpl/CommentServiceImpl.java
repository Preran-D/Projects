package com.preran.BlogPost2.servicesImpl;

import com.preran.BlogPost2.entites.Comment;
import com.preran.BlogPost2.entites.Post;
import com.preran.BlogPost2.exceptions.ResourceNotFoundException;
import com.preran.BlogPost2.models.CommentDto;
import com.preran.BlogPost2.repositories.CommentRepo;
import com.preran.BlogPost2.repositories.PostRepo;
import com.preran.BlogPost2.repositories.UserRepo;
import com.preran.BlogPost2.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;


    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
        Post post = this.postRepo
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        comment.setContent(commentDto.getContent());
        Comment savedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(long commentId) {
        Comment comment = this.commentRepo
                .findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentId", commentId));
        this.commentRepo.delete(comment);
    }

    @Override
    public Comment findById(long commentId) {
        return this.commentRepo
                .findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentId", commentId));
    }
}
