package com.preran.BlogPost2.exceptions;

public class ResourceFoundException extends RuntimeException {
    String resourceName;

    String fieldValue;

    public ResourceFoundException(String resourceName, String fieldValue) {
        super(String.format("%s with %s already exists", resourceName, fieldValue));
        this.resourceName = resourceName;
        this.fieldValue = fieldValue;
    }
}
