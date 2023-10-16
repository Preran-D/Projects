package com.preran.BlogPost.JsonModels;

import com.preran.BlogPost.entities.Tags;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostModel {

    private long postId;
    private String publisherName;
    private String title;
    private String content;
    private LocalDate addedDate;
    private List<Tags> tags;
}
