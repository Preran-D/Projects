package com.preran.BlogPost2.models;

import com.preran.BlogPost2.entites.Comment;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Data
public class UserDto {

    private Long id;

    @Size(min = 5, max = 15, message = "Username must be min of 5 and max 15 characters")
    private String name;

    @Email(message = "Your Email address is not Valid")
    private String email;

    @NotEmpty
    @Size(min = 5, max = 16, message = "Password must be min of 5 and max 16 characters")
    private String password;

    private Set<RoleDto> roles = new HashSet<>();

}

