package com.preran.BlogPost.services;

import com.preran.BlogPost.JsonModels.CommentRequestModel;
import com.preran.BlogPost.entities.Post;
import com.preran.BlogPost.entities.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    public Post createPost(Post post, long userId, long categoryId) {

        return null;
    }

    public void deletePost(long postsId) {
    }

    public List<Post> getAllPost() {
        return null;
    }


    public List<Post> getPostsByCategory(long categoryId) {
        return null;
    }

    public List<Post> getPostsByUser(long userId) {
        return null;
    }

    public Object applyTags(long blogId, List<Tag> tags) {
        return null;
    }

    public Object addComment(CommentRequestModel commentRequestModel) {
        return null;

    }

    public Object removeBlog(long blogId) {
        return null;

    }

    public Object removeComment(long commentId) {
        return null;

    }

    public Post getPostById(long postId) {
        return null;
    }
}
