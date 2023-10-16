package com.preran.BlogPost.JsonModels;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CommentRequestModel {
    private long postId;
    private String commenterName;
    private String comment;
}
