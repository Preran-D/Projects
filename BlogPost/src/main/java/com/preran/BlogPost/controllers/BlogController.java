package com.preran.BlogPost.controllers;

import com.preran.BlogPost.JsonModels.CommentRequestModel;
import com.preran.BlogPost.JsonModels.PostModel;
import com.preran.BlogPost.entities.Tags;
import com.preran.BlogPost.exceptions.CustomException;
import com.preran.BlogPost.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    @Autowired
    private PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<?> createPost(@RequestBody PostModel postModel) {
        if (postModel != null) {
            return new ResponseEntity<>(postService.publishPost(postModel), HttpStatus.OK);
        } else {
            throw new CustomException("Invalid data");
        }
    }

    @DeleteMapping("/posts/{postsId}")
    public ResponseEntity<?> deletePost(@PathVariable long postsId) {
        if (postsId > 0) {
            return new ResponseEntity<>(postService.removePost(postsId), HttpStatus.OK);
        } else {
            throw new CustomException("Invalid data");
        }
    }

    @PostMapping("/add-tags/{postsId}")
    public ResponseEntity<?> assignTags(@PathVariable("postsId") long postsId, @RequestBody List<Tags> tags) {
        if (postsId > 0 && !tags.isEmpty()) {
            var updatedBlog = postService.addTags(postsId, tags);
            return new ResponseEntity<>(updatedBlog, HttpStatus.OK);
        } else {
            throw new CustomException("invalided.data");
        }
    }

    @PostMapping("/comment-post")
    public ResponseEntity<?> commentOnPost(@RequestBody CommentRequestModel commentRequestModel) {
        if (commentRequestModel != null) {
            var commentDetail = postService.addComment(commentRequestModel);
            return new ResponseEntity<>(commentDetail, HttpStatus.OK);
        } else {
            throw new CustomException("invalided.data");
        }
    }

    @DeleteMapping("/remove-comment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") long commentId) {
        var removedComment = postService.removeComment(commentId);
        return new ResponseEntity<>(removedComment, HttpStatus.OK);
    }

}

