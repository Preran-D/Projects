package com.preran.BlogPost2.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data

public class CategoryDto {

    private long categoryId;
    @NotEmpty
    @Size(min = 3, max = 50,message = "Length should be min 3 and max 30")
    private String categoryTitle;

}
