package com.preran.BlogPost2.entites;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "comments")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "commenterName")
    private String commenterName;
    @Column(name = "content")
    private String content;
    @ManyToOne
    private Post post;
}
