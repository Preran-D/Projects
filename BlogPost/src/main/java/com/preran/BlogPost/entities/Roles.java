package com.preran.BlogPost.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "role_name", nullable = false)
    private String roleName;
}

