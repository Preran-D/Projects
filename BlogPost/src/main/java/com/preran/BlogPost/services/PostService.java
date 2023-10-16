package com.preran.BlogPost.services;

import com.preran.BlogPost.JsonModels.CommentRequestModel;
import com.preran.BlogPost.JsonModels.CommentsModel;
import com.preran.BlogPost.JsonModels.PostModel;
import com.preran.BlogPost.entities.Comment;
import com.preran.BlogPost.entities.Posts;
import com.preran.BlogPost.entities.Tags;
import com.preran.BlogPost.entities.Users;
import com.preran.BlogPost.exceptions.CustomException;
import com.preran.BlogPost.repos.CommentRepo;
import com.preran.BlogPost.repos.PostRepo;
import com.preran.BlogPost.repos.TagRepo;
import com.preran.BlogPost.repos.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private TagRepo tagRepo;

    public PostModel publishPost(PostModel postModel) {

        LocalDate localDate = LocalDate.now();
        Posts newPosts = new Posts();
        newPosts.setBlogTitle(postModel.getTitle());
        newPosts.setBlogContent(postModel.getContent());
        newPosts.setPublishedDate(localDate);
        newPosts.setUserName(postModel.getPublisherName());
        newPosts = postRepo.save(newPosts);

        long postId = newPosts.getPostId();
        List<Tags> tags = new ArrayList<>();
        postModel.getTags().forEach(tag -> {
            Tags newTag = new Tags();
            newTag.setPostId(postId);
            newTag.setCategory(tag.getCategory());
            tags.add(tagRepo.save(newTag));
        });

        postModel.setPostId(newPosts.getPostId());
        postModel.setTags(tags);
        return postModel;
    }

    @Transactional
    public String removePost(long postsId) {
        if (postsId > 0) {
            postRepo.deleteByPostId(postsId);
            tagRepo.deleteByPostId(postsId);
            return "Deleted successfully";
        }
        return "Invalid data";
    }

    public PostModel addTags(long postsId, List<Tags> tags) {
        Posts post = postRepo.findByPostId(postsId);
        PostModel postModel = new PostModel();
        List<Tags> tagList = new ArrayList<>();
        if (post != null) {
            postModel.setPostId(postsId);
            tags.forEach(tag -> {
                tag.setPostId(postsId);
                tagList.add(tagRepo.save(tag));
            });
            postModel.setPostId(postsId);
            postModel.setTitle(post.getBlogTitle());
            postModel.setContent(post.getBlogContent());
            postModel.setAddedDate(post.getPublishedDate());
            postModel.setPublisherName(post.getUserName());
            postModel.setTags(tagList);
            return postModel;
        } else {
            throw new CustomException("Invalid data");
        }
    }

    public Object addComment(CommentRequestModel commentRequestModel) {
        long postId = commentRequestModel.getPostId();
        String commenterUserName = commentRequestModel.getCommenterName();
        String comment = commentRequestModel.getComment();
        if (userRepo.existsByUserName(commenterUserName)) {

            if (postRepo.existsByPostId(postId)) {

                List<Users> commentingUserList = new ArrayList<>();
                Posts existingBlog = postRepo.findByPostId(postId);
                Users user = userRepo.findByUserName(commenterUserName);
                Users commentingUser = new Users();
                commentingUser.setUserId(user.getUserId());
                commentingUser.setUserName(user.getUserName());
                commentingUser.setUserPassword("* * * * *");
                commentingUser.setEmail(user.getEmail());
                commentingUser.setEnable(user.isEnable());
                OffsetDateTime currentDate = OffsetDateTime.now();

                Comment newComment = new Comment();
                newComment.setComment(comment);
                newComment.setCommentDate(currentDate);
                newComment.setCommentingUserId(commentingUser.getUserId());
                newComment.setBlogId(postId);

                List<Comment> comments = new ArrayList<>();
                comments.add(commentRepo.save(newComment));
                commentingUserList.add(commentingUser);

                return new CommentsModel(commentingUserList, existingBlog, comments);
            } else {
                throw new CustomException("Invalid data");
            }
        } else {
            throw new CustomException("No Blog");
        }
    }



    public Object removeComment(long commentId) {
        if (commentRepo.existsById(commentId)) {
            var comment = commentRepo.findByCommentId(commentId);
            if (comment != null) {
                commentRepo.delete(comment);
            }
            return comment;
        } else {
            throw new CustomException("NO Comment");
        }
    }
}
