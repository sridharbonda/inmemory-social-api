package com.sridhar.socialapi.store;


import com.sridhar.socialapi.dto.Post;

import javax.swing.text.html.parser.Entity;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * In-memory storage for posts in the Social API Server application.
 * This class simulates a database by storing posts in a ConcurrentHashMap.
 * <p>
 * Thread-safe operations are provided for creating, retrieving, deleting, and liking posts.
 */
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
     * @return The saved post with an auto-generated ID
     */
    public static Post savePost(Post post) {
        long id = postIdGenerator.getAndIncrement();
        post.setId(id);
        posts.put(id, post);
        return post;
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
     * @return true if the post was deleted, false otherwise
     */
    public static boolean deletePost(Long id, String username) {
        Post post = posts.get(id);
        if (post != null && post.getAuthor().equals(username)) {
            posts.remove(id);
            return true;
        }
        return false;
    }

    /**
     * Adds a like to a post by the given username.
     * Does nothing if the post does not exist.
     *
     * @param id       The ID of the post
     * @param username The username of the user liking the post
     */
    public static void likePost(Long id, String username) {
        Post post = posts.get(id);
        if (post != null) {
            post.getLikedBy().add(username);
        }
    }

    /**
     * Retrieves all posts created by a specific user.
     *
     * @param username The username of the author
     * @return List of posts authored by the given user
     */
    public static List<Post> getMyPost(String name) {
        List<Post> postList = PostStore.getAllPosts().stream().filter(post -> post.getAuthor().equalsIgnoreCase(name)).toList();
        return postList;
    }
}
