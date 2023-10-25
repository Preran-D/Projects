package com.preran.BlogPost2.servicesImpl;

import com.preran.BlogPost2.entites.Category;
import com.preran.BlogPost2.entites.Post;
import com.preran.BlogPost2.entites.User;
import com.preran.BlogPost2.exceptions.ResourceNotFoundException;
import com.preran.BlogPost2.models.PostDto;
import com.preran.BlogPost2.repositories.CategoryRepo;
import com.preran.BlogPost2.repositories.PostRepo;
import com.preran.BlogPost2.repositories.UserRepo;
import com.preran.BlogPost2.services.PostService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {


    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, long userId, long categoryId) {
        User user = this.userRepo
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User id", userId));
        Category category = this.categoryRepo
                .findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setUser(user);
        post.setCategory(category);
        post.setAddedDate(new Date());
        Post newPost = this.postRepo.save(post);
        return this.modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long postId) {
        Post post = this.postRepo
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    @Transactional
    public void deletePost(long postId) {
        Post deletePost = this.postRepo.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Posts", "Post Id", postId));

        deletePost.getUser().getPosts().remove(deletePost);
        deletePost.getCategory().getPosts().remove(deletePost);

        this.postRepo.delete(deletePost);
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> posts = this.postRepo.findAll();
        return posts
                .stream()
                .map((post) -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(long postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()
                -> new ResourceNotFoundException("Posts", "Post Id", postId));
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(long categoryId) {
        Category category = this.categoryRepo
                .findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category Id", categoryId));

        List<Post> allPostByCategory = this.postRepo.findByCategory(category);

        if (allPostByCategory.isEmpty()) {
            throw new ResourceNotFoundException("Post", "category Id", categoryId);
        }
        return allPostByCategory
                .stream()
                .map(categoryPost -> this.modelMapper.map(categoryPost, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostsByUser(long userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException("User", "User Id", userId));
        List<Post> allPostByUser = this.postRepo.findByUser(user);
        if (allPostByUser.isEmpty()) {
            throw new ResourceNotFoundException("Post", "user Id", userId);
        }
        return allPostByUser
                .stream()
                .map(userPost -> this.modelMapper.map(userPost, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPostsByContentKeyword(String keyword) {
        List<Post> posts = this.postRepo.findByContentContaining(keyword);
        if (posts.isEmpty()) {
            throw new ResourceNotFoundException("Post", "Content Keyword", keyword);
        }
        return posts
                .stream()
                .map((post) -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPostsByTitleKeyword(String keyword) {
        List<Post> posts = this.postRepo.findByTitleContaining(keyword);
        if (posts.isEmpty()) {
            throw new ResourceNotFoundException("Post", "Title Keyword", keyword);
        }
        return posts
                .stream()
                .map((post) -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Post findById(long postsId) {
        return postRepo.findById(postsId).orElseThrow(()
                -> new ResourceNotFoundException("Posts", "Post Id", postsId));
    }

}
