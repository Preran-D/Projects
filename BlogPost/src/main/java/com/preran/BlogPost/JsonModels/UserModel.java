package com.preran.BlogPost.JsonModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserModel {

    private Long UMid;
    private String name;
    private String email;
    private String password;
    private boolean enable;
    private String role;
}
