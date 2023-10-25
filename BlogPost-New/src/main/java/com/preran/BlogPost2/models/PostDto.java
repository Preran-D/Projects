package com.preran.BlogPost2.models;


import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Data

public class PostDto {
    private long postId;
    @Size(min = 5, max = 30, message = "Title must be min of 5 and max 30 characters")
    private String title;
    @Size(min = 5, message = "Content must be min of 5 characters")
    private String content;
    private Date addedDate;
    private CategoryDto category;
    private UserDto user;
    private Set<CommentDto> comments = new HashSet<>();

}
