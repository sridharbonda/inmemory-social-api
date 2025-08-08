package com.sridhar.socialapi.store;


import com.sridhar.socialapi.dto.Post;

import javax.swing.text.html.parser.Entity;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class PostStore {
    private static final Map<Long, Post> posts = new ConcurrentHashMap<>();
    private static final AtomicLong postIdGenerator = new AtomicLong(1);

    public static Post savePost(Post post) {
        long id = postIdGenerator.getAndIncrement();
        post.setId(id);
        posts.put(id, post);
        return post;
    }

    public static Post getPost(Long id) {
        return posts.get(id);
    }

    public static List<Post> getAllPosts() {
        return new ArrayList<>(posts.values());
    }

    public static boolean deletePost(Long id, String username) {
        Post post = posts.get(id);
        if (post != null && post.getAuthor().equals(username)) {
            posts.remove(id);
            return true;
        }
        return false;
    }

    public static void likePost(Long id, String username) {
        Post post = posts.get(id);
        if (post != null) {
            post.getLikedBy().add(username);
        }
    }

    public static List<Post> getMyPost(String name) {
        List<Post> postList = PostStore.getAllPosts().stream().filter(post -> post.getAuthor().equalsIgnoreCase(name)).toList();
        return postList;
    }
}
