package com.preran.BlogPost2;

import com.preran.BlogPost2.repositories.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class BlogPostNewApplication {

    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(BlogPostNewApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}
