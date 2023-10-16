package com.preran.BlogPost.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "posts")
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "blog_title", nullable = false)
    private String blogTitle;

    @Column(name = "blog_content", nullable = false)
    private String blogContent;

    @Column(name = "published_date", nullable = false)
    private LocalDate publishedDate;

    @Column(name = "user_name", nullable = false)
    private String userName;
}
