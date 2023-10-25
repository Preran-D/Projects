    package com.preran.BlogPost2.models;


    import com.preran.BlogPost2.entites.Post;
    import lombok.Data;
    import lombok.Getter;
    import lombok.Setter;

    @Getter
    @Setter
    @Data

    public class CommentDto {
        private long id;
        private String commenterName;
        private String content;
     }
