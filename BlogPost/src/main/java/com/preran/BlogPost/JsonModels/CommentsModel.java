package com.preran.BlogPost.JsonModels;


import com.preran.BlogPost.entities.Comment;
import com.preran.BlogPost.entities.Posts;
import com.preran.BlogPost.entities.Users;
import lombok.Getter;

import java.util.List;


@Getter
public class CommentsModel {

    private List<Users> commenterDetails;
    private Posts blogDetails;
    private List<Comment> commentDetails;

    public CommentsModel() {
    }

    public CommentsModel(Posts blogDetails, List<Comment> commentDetails) {
        this.blogDetails = blogDetails;
        this.commentDetails = commentDetails;
    }

    public CommentsModel(List<Users> commenterDetails, Posts blogDetails, List<Comment> commentDetails) {
        this.commenterDetails = commenterDetails;
        this.blogDetails = blogDetails;
        this.commentDetails = commentDetails;
    }


    public void setCommenterDetails(List<Users> commenterDetails) {
        this.commenterDetails = commenterDetails;
    }

    public void setBlogDetails(Posts blogDetails) {
        this.blogDetails = blogDetails;
    }

    public void setCommentDetails(List<Comment> commentDetails) {
        this.commentDetails = commentDetails;
    }
}