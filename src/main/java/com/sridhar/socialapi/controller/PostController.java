package com.sridhar.socialapi.controller;

import com.sridhar.socialapi.dto.Post;
import com.sridhar.socialapi.dto.PostRequest;
import com.sridhar.socialapi.service.PostService;
import com.sridhar.socialapi.store.PostStore;
import com.sridhar.socialapi.utils.EntityMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * REST Controller for managing social media posts.
 * Provides APIs for creating, listing, liking, and deleting posts.
 */
@RestController
@Slf4j
@RequestMapping("/social")
@Tag(name = "Post Management", description = "APIs for creating, liking, deleting and listing posts")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/create")
    @Operation(summary = "Create a new post", description = "Creates a post with the given content for the authenticated user.")
    public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest, Principal principal) throws Exception {
        log.info("Received Request for new post creation.");
        postService.createPost(postRequest, principal.getName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/allPosts")
    @Operation(summary = "List all posts", description = "Returns a list of all posts with author details.")
    public ResponseEntity<List<Post>> getAllPosts(){
        log.info("Request received for returning all posts stored.");
        List<Post> postResponseList = postService.listAllPosts();
        return ResponseEntity.ok(postResponseList);
    }

    @GetMapping("/myPosts")
    @Operation(summary = "List the user posts", description = "Returns a list of all posts the user.")
    public ResponseEntity<List<Post>> getMyPosts(Principal principal) throws Exception {
        log.info("Request received to return the user's posts");
        List<Post> postResponseList = postService.getUserPosts(principal.getName());
        return ResponseEntity.ok(postResponseList);
    }

    @PutMapping("/like/post/{id}")
    @Operation(summary = "Like a post", description = "Adds a like to the given post ID.")
    public ResponseEntity<?> likePost(@PathVariable Long id, Principal principal) throws Exception {
        log.info("Received request to like the post with id: {}", id);
        postService.likePost(id, principal.getName());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a post", description = "Deletes a post by ID if it belongs to the authenticated user.")
    public ResponseEntity<?> deleteMyPost(@PathVariable Long id, Principal principal) throws Exception {
        log.info("received request to delete the post id : {}", id);
        postService.deletePost(id, principal.getName());
        return ResponseEntity.noContent().build();
    }
}
