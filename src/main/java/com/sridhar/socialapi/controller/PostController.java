package com.sridhar.socialapi.controller;

import com.sridhar.socialapi.dto.Post;
import com.sridhar.socialapi.store.PostStore;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/social")
public class PostController {



    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody Post post, Principal principal) throws Exception {
        log.info("Received Request for new post creation.");
        String username =  principal.getName();
        post.setAuthor(username);
        PostStore.savePost(post);
        log.info("Post Created for user: {}", username);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/allPosts")
    public ResponseEntity<List<Post>> getAllPosts(){
        log.info("Request received for returning all posts stored.");
        List<Post> postResponseList = PostStore.getAllPosts();
        return ResponseEntity.ok(postResponseList);
    }

    @GetMapping("/myPosts")
    public ResponseEntity<List<Post>> getMyPosts(Principal principal) throws Exception {
        List<Post> postResponseList = PostStore.getMyPost(principal.getName());
        return ResponseEntity.ok(postResponseList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMyPost(@PathVariable Long id, Principal principal) throws Exception {
        PostStore.deletePost(id, principal.getName());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/like/post/{id}")
    public ResponseEntity<?> likePost(@PathVariable Long id, Principal principal) throws Exception {
        PostStore.likePost(id, principal.getName());
        return ResponseEntity.ok().build();
    }
}
