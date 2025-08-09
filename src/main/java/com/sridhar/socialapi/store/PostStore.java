package com.sridhar.socialapi.store;


import com.sridhar.socialapi.dto.Post;
import com.sridhar.socialapi.exception.PostNotFoundException;
import com.sridhar.socialapi.exception.PostNotOwnedException;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In-memory storage for posts in the Social API Server application.
 * This class simulates a database by storing posts in a ConcurrentHashMap.
 * Thread-safe operations are provided for creating, retrieving, deleting, and liking posts.
 */
@Slf4j
public class PostStore {

    /**
     * In-memory store for posts.
     * Key: Post ID
     * Value: Post object
     */
    private static final Map<Long, Post> posts = new ConcurrentHashMap<>();

    /**
     * Atomic counter for generating unique post IDs.
     */
    private static final AtomicLong postIdGenerator = new AtomicLong(1);


    /**
     * Saves a new post to the in-memory store.
     *
     * @param post The post object to save
     */
    public static void savePost(Post post) {
        long id = postIdGenerator.getAndIncrement();
        post.setId(id);
        posts.put(id, post);
    }

    /**
     * Retrieves a post by its ID.
     *
     * @param id The ID of the post
     * @return The post object if found, otherwise null
     */
    public static Post getPost(Long id) {
        return posts.get(id);
    }

    /**
     * Retrieves all posts from the store.
     *
     * @return List of all posts
     */
    public static List<Post> getAllPosts() {
        return new ArrayList<>(posts.values());
    }

    /**
     * Deletes a post if it exists and belongs to the given username.
     *
     * @param id       The ID of the post
     * @param username The username of the post's author
     */
    public static void deletePost(Long id, String username) {
        Post post = posts.get(id);
        if (post == null) {
            throw new PostNotFoundException("Post not found for id: "+id);
        }
        if (!post.getAuthor().equals(username)) {
            throw new PostNotOwnedException("User doesn't own the post with id: "+id);
        }
        posts.remove(id);
        log.info("The post with id: {} has been deleted", id);
    }

    /**
     * Adds a like to a post by the given username.
     * Supports Toggle Behaviour
     * Post can be unliked with invoked same request again
     * Does nothing if the post does not exist.
     *
     * @param id       The ID of the post
     * @param username The username of the user liking the post
     */
    public static void likePost(Long id, String username) {
        Post post = posts.get(id);
        if (post == null) {
            throw new PostNotFoundException("Post not found for id: "+id);
        }
        Set<String> likedBy = post.getLikedBy();

        if (likedBy.contains(username)) {
            // Toggle: Remove like if already liked
            likedBy.remove(username);
            log.info("User {} unliked post {}", username, id);
        } else {
            // Add like if not already liked
            likedBy.add(username);
            log.info("User {} liked post {}", username, id);
        }
        log.info("the post updated: {}", post);
    }

    /**
     * Retrieves all posts created by a specific user.
     *
     * @param username, The username of the author
     * @return List of posts authored by the given user
     */
    public static List<Post> getMyPost(String name) {
        List<Post> postList = PostStore.getAllPosts().stream().filter(post -> post.getAuthor().equalsIgnoreCase(name)).toList();
        return postList;
    }
}
