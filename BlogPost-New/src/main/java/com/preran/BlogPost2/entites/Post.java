package com.preran.BlogPost2.entites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Table(name = "post")
@Getter
@Setter

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private long postId;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "content", length = 10000)
    private String content;

    @Column(name = "Date")
    private Date addedDate;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();


}
