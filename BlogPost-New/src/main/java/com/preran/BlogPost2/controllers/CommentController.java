package com.preran.BlogPost2.controllers;


import com.preran.BlogPost2.entites.Comment;
import com.preran.BlogPost2.exceptions.CustomResponse;
import com.preran.BlogPost2.models.CommentDto;
import com.preran.BlogPost2.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    public CommentService commentService;

    @PostMapping("/add/{postId}")
    public ResponseEntity<CommentDto> createComment(
            @RequestBody CommentDto commentDto,
            @PathVariable long postId, Principal principal
    ) {
        String loggedInUsername = principal.getName();
        commentDto.setCommenterName(loggedInUsername);
        CommentDto createdComment = this.commentService.createComment(commentDto, postId);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @GetMapping("/delete/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable long commentId, Principal principal) {
        String loggedInUsername = principal.getName();

        Comment commentToBeDeleted = commentService.findById(commentId);
        String authorUsername = commentToBeDeleted.getCommenterName();

        if (!loggedInUsername.equals(authorUsername)) {
            return new ResponseEntity<>(
                    "You are not allowed to delete another user's comment", HttpStatus.FORBIDDEN
            );
        }

        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(
                new CustomResponse("Deleted Successfully", true), HttpStatus.OK
        );
    }
}
