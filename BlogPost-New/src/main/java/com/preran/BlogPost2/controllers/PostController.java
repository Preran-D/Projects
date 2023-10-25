package com.preran.BlogPost2.controllers;


import com.preran.BlogPost2.entites.Post;
import com.preran.BlogPost2.entites.User;
import com.preran.BlogPost2.exceptions.CustomResponse;
import com.preran.BlogPost2.models.PostDto;
import com.preran.BlogPost2.services.PostService;
import com.preran.BlogPost2.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @PostMapping("/add/{categoryId}")
    public ResponseEntity<PostDto> createPost(
            @Valid @RequestBody PostDto postDto,
            @PathVariable long categoryId,
            Principal principal
    ) {
        String loggedInUsername = principal.getName();
        User loggedInUser = userService.findByName(loggedInUsername);
        long userId = loggedInUser.getId();
        PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @PostMapping("/update/{postsId}")
    public ResponseEntity<?> updatePost(
            @Valid @RequestBody PostDto postDto,
            @PathVariable long postsId, Principal principal
    ) {
        String loggedInUsername = principal.getName();
        Post postToBeUpdated = postService.findById(postsId);
        String authorUsername = postToBeUpdated.getUser().getUsername();
        if (!loggedInUsername.equals(authorUsername)) {
            return new ResponseEntity<>(
                    "You are not allowed to update another user's post", HttpStatus.FORBIDDEN
            );
        }
        PostDto updatedPost = this.postService.updatePost(postDto, postsId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @GetMapping("/delete/{postsId}")
    public ResponseEntity<?> deletePost(@PathVariable long postsId, Principal principal) {
        String loggedInUsername = principal.getName();
        Post postToBeDeleted = postService.findById(postsId);
        String authorUsername = postToBeDeleted.getUser().getUsername();
        if (!loggedInUsername.equals(authorUsername)) {
            return new ResponseEntity<>(
                    "You are not allowed to delete another user's post", HttpStatus.FORBIDDEN
            );
        }
        this.postService.deletePost(postsId);
        return new ResponseEntity<>(
                new CustomResponse("Post deleted successfully", true),
                HttpStatus.OK);
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<PostDto>> getAllPost() {
        List<PostDto> allPosts = this.postService.getAllPost();
        return ResponseEntity.ok(allPosts);
    }

    @GetMapping("/byID/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable long postId) {
        PostDto post = this.postService.getPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/byCategory/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable long categoryId) {
        List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/byUser/{userId}")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable long userId) {
        List<PostDto> posts = this.postService.getPostsByUser(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/searchByTitle/{keywords}")
    public ResponseEntity<List<PostDto>> getBySearchTitle(@PathVariable String keywords) {
        List<PostDto> posts = this.postService.searchPostsByTitleKeyword(keywords);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/searchByContent/{keywords}")
    public ResponseEntity<List<PostDto>> getBySearchContent(@PathVariable String keywords) {
        List<PostDto> posts = this.postService.searchPostsByContentKeyword(keywords);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


}
