package com.preran.BlogPost.JsonModels;

import com.preran.BlogPost.entities.Tag;
import com.preran.BlogPost.entities.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostModel {
    private long postId;
    private String senderName;
    private String title;
    private String content;
    private Date addedDate;
    private List<Tag> tag;


}
