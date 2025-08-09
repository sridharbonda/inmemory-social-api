package com.sridhar.socialapi.service;

import com.sridhar.socialapi.dto.Post;
import com.sridhar.socialapi.dto.PostRequest;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Service interface for managing posts in the Social API.
 */
@Component
public interface PostService {

    /**
     * Retrieves all posts created by a specific user.
     *
     * @param username the username of the user whose posts are to be retrieved
     * @return a list of Post objects created by the specified user
     */
    List<Post> getUserPosts(String username);

    /**
     * Creates a new post for the given user.
     *
     * @param postRequest the content and data of the post to be created
     * @param username the username of the user creating the post
     */
    void createPost(PostRequest postRequest, String username);

    /**
     * Lists all posts in the system, regardless of the author.
     *
     * @return a list of all Post objects
     */
    List<Post> listAllPosts();

    /**
     * Deletes a post if it belongs to the specified user.
     *
     * @param id the ID of the post to delete
     * @param username the username of the post's owner
     */
    void deletePost(Long id, String username);

    /**
     * Adds a like to the specified post from the given user.
     * Supports Toggle behaviour
     *
     * @param id the ID of the post to like
     * @param username the username of the user liking the post
     */
    void likePost(Long id, String username);
}
