package com.preran.BlogPost.controllers;

import com.preran.BlogPost.JsonModels.CommentRequestModel;
import com.preran.BlogPost.JsonModels.PostModel;
import com.preran.BlogPost.entities.Post;
import com.preran.BlogPost.entities.Tag;
import com.preran.BlogPost.exceptions.CustomResponse;
import com.preran.BlogPost.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/")
public class BlogController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<?> createPost(
            @RequestBody Post post,
            @PathVariable long userId,
            @PathVariable long categoryId
    ) {
        Post createdPost = this.postService.createPost(post, userId, categoryId);
        return new ResponseEntity<Post>(createdPost, HttpStatus.CREATED);
    }


    @DeleteMapping("/posts/{postsId}")
    public ResponseEntity<?> deletePost(@PathVariable long postsId) {
        this.postService.deletePost(postsId);
        return new ResponseEntity<>(new CustomResponse("Post deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getAllPost() {
        List<Post> allPosts = this.postService.getAllPost();
        return ResponseEntity.ok(allPosts);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<?> getPostById(@PathVariable long postId) {
        Post post = this.postService.getPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<?>> getPostByCategory(@PathVariable long categoryId) {
        List<Post> posts = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<?> getPostByUser(@PathVariable long userId) {
        List<Post> posts = this.postService.getPostsByUser(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping("/add-tags")
    public ResponseEntity<?> assignTagsToBlog(@RequestBody PostModel postModel ) throws CustomResponse {
        long blogId = postModel.getPostId();
        List<Tag> tags = postModel.getTag();
        if (!tags.isEmpty()) {
            var updatedBlog = postService.applyTags(blogId, tags);
            return new ResponseEntity<>(updatedBlog, HttpStatus.OK);
        } else {
            throw new CustomResponse("invalided.data",false);
        }
    }

    @PostMapping("/comment-post")
    public ResponseEntity<?> commentOnPost(@RequestBody CommentRequestModel commentRequestModel) throws CustomResponse {
        if (commentRequestModel != null) {
            var commentDetail = postService.addComment(commentRequestModel);
            return new ResponseEntity<>(commentDetail, HttpStatus.OK);
        } else {
            throw new CustomResponse("invalided.data",false);
        }
    }

    @DeleteMapping("/remove-blog/{blogId}")
    public ResponseEntity<?> deleteBlogPost(@PathVariable("blogId") long blogId) {
        var removedBlog = postService.removeBlog(blogId);
        return new ResponseEntity<>(removedBlog, HttpStatus.OK);
    }

    @DeleteMapping("/remove-comment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") long commentId) {
        var removedComment = postService.removeComment(commentId);
        return new ResponseEntity<>(removedComment, HttpStatus.OK);
    }


}

