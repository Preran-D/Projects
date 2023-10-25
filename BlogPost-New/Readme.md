# BLOG-POST-API

The "BLOG-POST-API" is a comprehensive backend API for a blogging platform. It provides functionality for user, post, comment, and category management. Users can register, update their profiles, create and manage posts, comment on posts, and manage categories. The API enforces access permissions, with user roles like ADMIN and USER dictating who can perform specific actions. Basic HTTP Authentication is used for user authorization, ensuring secure access to the API.

# Postman Collection Import

A Postman collection file has been included in this project. This file contains pre-configured API endpoints for testing and development purposes.

## Instructions for Importing the Collection

1. Download the Postman collection file from the project’s Git repository.
2. Open Postman.
3. Click on the “Import” button located at the top left corner of the application.
4. In the opened dialog box, select “Upload Files”.
5. Browse to the location where you downloaded the collection file, select it, and click on “Open”.
6. The collection will now be imported into your Postman application and will be available for use.

Please note that these endpoints are configured based on the initial setup values (roles, user, categories) inserted at runtime.
# Project Initialization

This project includes several initial setup steps:

## 1. Role Insertion

Two roles are inserted into the system:

- ‘ROLE_ADMIN’ with an ID of 100
- ‘ROLE_USER’ with an ID of 101

## 2. User Creation

A user is created with the following details:

- ID: 1
- Email: admin@example.com
- Name: ‘admin’
- Password: ‘admin’ (hashed for security) This user is associated with the ‘ROLE_ADMIN’ role.

## 3. Category Addition

Several categories are added to the system, each with a unique title and ID:

- ‘Technology’ (ID: 1)
- ‘Travel’ (ID: 2)
- ‘Food’ (ID: 3)
- ‘Lifestyle’ (ID: 4)
- ‘Fashion’ (ID: 5)

Please note that these values are inserted at runtime and are necessary for using the endpoints.

# Create a Database
DROP SCHEMA `blog_post_2`;
CREATE DATABASE  IF NOT EXISTS `blog_post_2`;
USE `blog_post_2`;

## 1. **Register User**

- **Endpoint:** **(POST /api/users/register)**

**Description:** This endpoint allows anyone to register a new user by providing the necessary user information.

**Access Permission:** Open to all users.

**Sample JSON Request:**

```json
{
    "name": "johndoe",
    "email": "johndoe@example.com",
    "password": "passw23"
}
```

## 2. **Update User**

- **Endpoint:(POST /api/users/update)**

**Description:** This endpoint is used to update the profile of a user. It restricts users from updating other users’ profiles. 

**Access Permission:** Restricted to users with the USER or ADMIN role.

**Sample JSON Request:**

```json
{
    "name": "johndoe",
    "password": "pwd123"
}
```

## 3. **Get User by ID**

- **Endpoint:(GET /api/users/get/{userId})**

**Description:** Retrieve user information by specifying the user's ID. Only ADMIN can access all the users while users can access only there ID.

**Access Permission:** Restricted to users with the USER or ADMIN role.

## 4. **Get All Users**

- **Endpoint: (GET /api/users/all)**

**Description:** Retrieve a list of all users in the system.

**Access Permission:**  Restricted to users with the ADMIN role.

## 5. **Update User Role**

- **Endpoint: (POST /api/users/{userId}/roles/{roleId})**

**Description:** Users with the ADMIN role can update the role of a specific user.

**Access Permission:** Restricted to users with the ADMIN role.

## 6. **Add Role to User**

- **Endpoint: (POST /api/users/{userId}/addRole/{roleId})**

**Description:** Users with the ADMIN role can add a new role to a specific user.

**Access Permission:** Restricted to users with the ADMIN role.

## 7. **Delete User**

- **Endpoint: (GET /api/users/delete/{userId})**

**Description:** Users with the ADMIN role can delete a user from the system.

**Access Permission:** Restricted to users with the ADMIN role.

Please replace **`{userId}`** and **`{roleId}`** in the URLs with the actual user IDs and role IDs when making requests to these endpoints. The access permissions ensure that only authorized users can perform certain actions, helping to maintain security and control in the system.

## 1. **Create a New Post**

- **Endpoint:** **POST `/api/post/add/{categoryId}`**
- **Description:** Create a new blog post with the specified  **`categoryId`**.
- **Sample JSON Request:**

```json
{
    "title": "My First Blog",
    "content": "First Post"
}
```

## 2. **Update a Post**

- **Endpoint: POST**  **`/api/post/update/{postId}`**
- **Description:** Update an existing blog post with the specified **`postId`**. It restricts users from updating other users’ posts.
- **Sample JSON Request:**

```json
{
  "title": "Updated Post Title",
  "content": "This is the updated content of the blog post."
}
```

## 3. **Delete a Post**

- **Endpoint: GET** **`/api/post/delete/{postId}`**
- **Description:** Delete a blog post with the specified **`postId`**.

## 4. **Get All Posts**

- **Endpoint:** **GET `/api/post/getAll`**
- **Description:** Retrieve a list of all blog posts.

## 5. **Get Post by ID**

- **Endpoint: GET** **`/api/post/byID/{postId}`**
- **Description:** Retrieve a blog post by its **`postId`**.

## 6. **Get Posts by Category**

- **Endpoint: GET** **`/api/post/byCategory/{categoryId}`**
- **Description:** Retrieve a list of blog posts by the specified **`categoryId`**.

## 7. **Get Posts by User**

- **Endpoint: GET** **`/api/post/byUser/{userId}`**
- **Description:** Retrieve a list of blog posts by the specified **`userId`**.

## 8. **Search Posts by Title Keyword**

- **Endpoint: GET** **`/api/post/searchByTitle/{keywords}`**
- **Description:** Search for blog posts by title keywords.

## 9. **Search Posts by Content Keyword**

- **Endpoint:** **GET `/api/post/searchByContent/{keywords}`**
- **Description:** Search for blog posts by content keywords.

1. **`categoryId`**: This is the unique identifier for a category. It is used in the endpoints for creating a new post and retrieving posts by a specific category.
2. **`postId`**: This is the unique identifier for a post. It is used in the endpoints for updating a post, deleting a post, and retrieving a post by its ID.
3. **`keywords`**: These are the search terms used to find posts by title or content. They are used in the endpoints for searching posts by title or content.

Remember, these variables need to be replaced with actual values when making requests to these endpoints. For example, if you want to create a new post for a user with an ID of 1 and a category ID of 2, you would use the endpoint **`POST /api/post/addPost/1`**. Similarly, if you want to search for posts with the keyword “blog”,  you would use the endpoint **`GET /api/post/searchByTitle/blog`**. 

## 1. **Create a New Comment**

- **Endpoint:** **POST `/api/comment/add/{postId}`**
- **Description:** Create a new comment on a blog post with the specified **`postId`**.
- **Sample JSON Request:**

**JSON**

```json
{
  "commentText": "This is a sample comment."
}

```

## 2. **Delete a Comment**

- **Endpoint:** **GET `/api/comment/delete/{commentId}`**
- **Description:** Delete a comment with the specified **`commentId`**. It restricts users from deleting other users’ comments.

Remember, these variables need to be replaced with actual values when making requests to these endpoints. For example, if you want to create a new comment for a post with an ID of 1, you would use the endpoint **`POST /api/comment/add/1`**. Similarly, if you want to delete a comment with an ID of 2, you would use the endpoint **`GET /api/comment/delete/2`**.

## 1. **Create a New Category**

- **Endpoint:** **POST `/api/category/create`**
- **Description:** Create a new category.
- **Sample JSON Request:**

**JSON**

```json
{
  "categoryName": "Sample Category"
}
```

## 2. **Update a Category**

- **Endpoint:** **POST `/api/category/update/{categoryId}`**
- **Description:** Update an existing category with the specified **`categoryId`**.
- **Sample JSON Request:**

**JSON**

```json
{
  "categoryName": "Updated Category Name"
}
```

## 3. **Delete a Category**

- **Endpoint:** **GET `/api/category/delete/{categoryId}`**
- **Description:** Delete a category with the specified **`categoryId`**.

## 4. **Get Category by ID**

- **Endpoint:** **GET `/api/category/byId/{categoryId}`**
- **Description:** Retrieve a category by its **`categoryId`**.

## 5. **Get All Categories**

- **Endpoint:** **GET `/api/category/getAll`**
- **Description:** Retrieve a list of all categories.

Remember, these variables need to be replaced with actual values when making requests to these endpoints. For example, if you want to create a new category, you would use the endpoint **`POST /api/category/create`**. Similarly, if you want to delete a category with an ID of 2, you would use the endpoint **`GET /api/category/delete/2`**.

## Authorization

This application uses Basic HTTP Authentication. To authorize your requests, include an **`Authorization`** header with the value **`Basic {credentials}`**, where **`{credentials}`** is your username and password encoded in Base64.

For example, if your username is **`johndoe`** and your password is **`pwd123`**, must insert in the basic auth in post man.
