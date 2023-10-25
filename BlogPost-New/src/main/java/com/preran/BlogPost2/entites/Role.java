package com.preran.BlogPost2.entites;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "role")
@Data
public class Role {
    @Id
    private int id;
    @Column(name = "name")
    private String name;

}
