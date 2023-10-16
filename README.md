# Blog Platform

This README provides instructions on how to use the Blog Platform's API, including various endpoints for user registration, changing passwords, adding roles, publishing blogs, managing tags, commenting on posts, and removing content. Please note that this API includes security measures to ensure proper authorization.

- **Find the file in Git_Hub repo PostMan_JSON_Preran-D.postman_collection and import into postman.**

# Add User

You can use the following endpoint to add a new user. No security measures are applied here, so use it with caution.

### Endpoint

- **POST** http://localhost:8080/api/users/create

### JSON Formats

1. Example

> Mathew as ADMIN
> 

```
{
  "name": "Mathew",
  "email": "mathew@gmail.com",
  "password":"pwd123",
  "enable": "true",
  "role": "ADMIN"
}
```

1. Example

> Linus as USER
> 

```
{
  "name": "Linus",
  "email": "linus@gmail.com",
  "password":"pwd1234",
  "enable": "true",
  "role": "USER"
}
```

## Update or Change Password

To change a user's password, you need to be either a USER or ADMIN, and **basic authentication is required.**

### Endpoint

- **POST** http://localhost:8080/api/users/update

### [JSON Format Example](https://github.com/roopeshbabubh/projects#json-format-example)

```
{
    "userName": "Mathew",
    "password": "pwd12345"
}
```

Here, the new password is provided in the JSON body, and the old password should be provided in basic authentication.

## Adding Roles to users

To add roles to existing users, you should be an ADMIN. Basic authentication is required.

### EndPoint

- **POST** http://localhost:8080/api/users/add-roles

### JSON Format

```
{
  "userName": "Linus",
  "roleName":"EMPLOYEE"
}
```

### EndPoint

- **POST** http://localhost:8080/api/users/get-all

## Add Post

To post a blog, you should be either a USER or ADMIN. Security measures are in place, and basic authentication is required.

### EndPoint

- **POST** http://localhost:8080/api/blogs/posts

### JSON Format

```
{
    "publisherName": "Mathew",
    "title": "Sample Blog Post",
    "content": "This is the content of the blog post.",
    "tags": [
        {
            "category": "Devops"
        },
        {
            "category": "Full Stack Development"
        }
    ]
}
```

## Add tags

To add tags (categories) to existing blogs, you should be either a USER or ADMIN. Basic authentication is required.

### Endpoint

Here at the end of link add the existing **POSTID** as path variable

- **POST**  [http://localhost:8080/api/blogs/add-tags/{postId}](https://www.notion.so/d7208d036af84db4b39847891f3a4bf5?pvs=21)

### JSON Format

```
[
    {
        "category": "Java"
    },
    {
        "category": "AWS"
    }
]
```

## Comment on Post

To comment on an existing blog post, you should be either a USER or ADMIN. Basic authentication is required.

### Endpoint

- **POST** http://localhost:8080/api/blogs/comment-post

### JSON Format

```
{
    "postId":1,
    "commenterName":"Mathew",
    "comment":"This is first comment on my BLOG POST"
}
```

## Remove Logs

To remove existing blogs, comments you can be USER or ADMIN, but for or users you should be an ADMIN. Security measures are in place, and basic authentication is required.

### Remove Comment

- **DELETE** [http://localhost:8080/api/blogs/remove-comment/{commentID}](http://localhost:8080/api/blogs/remove-comment/1)

Use it in Postman, where [{commentID}](http://localhost:8080/api/blogs/remove-comment/{commentID}) is the ID of the blog you want to remove.

### Remove Post

- **DELETE** [http://localhost:8080/api/blogs/posts/{postId}](http://localhost:8080/api/blogs/posts/1)

Use it in Postman, where [{postId}](http://localhost:8080/api/blogs/posts/{postId}) is the ID of the comment you want to remove.

### Remove User

- **DELETE** [http://localhost:8080/api/users/remove/{userName}](http://localhost:8080/api/users/remove/Linus)

# Get All Users

To view all USERs, you should be either a USER or ADMIN. Basic authentication is required.3

### Endpoint

http://localhost:8080/api/users/get-all

Remember to replace `<username>` and `<password>` with your actual authentication details when using the API.
