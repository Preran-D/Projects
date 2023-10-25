package com.preran.BlogPost2.services;

import com.preran.BlogPost2.entites.Post;
import com.preran.BlogPost2.models.PostDto;


import java.util.List;

public interface PostService {
    //    Create
    PostDto createPost(PostDto postDto, long userId, long categoryId);

    //    Update
    PostDto updatePost(PostDto postDto, long postId);


    //    Delete
    public void deletePost(long postId);

    //    Get all posts
    List<PostDto> getAllPost();

    //    Get post by Id
    PostDto getPostById(long postId);

    //    get all post by category
    List<PostDto> getPostsByCategory(long categoryId);

    //    get all posts by user
    List<PostDto> getPostsByUser(long userId);

    //    searchPosts by content keyword
    List<PostDto> searchPostsByContentKeyword(String Keyword);

//        searchPosts by title keyword
    List<PostDto> searchPostsByTitleKeyword(String Keyword);

    Post findById(long postsId);
}
