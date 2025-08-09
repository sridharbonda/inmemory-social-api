package com.sridhar.socialapi.service;

import com.sridhar.socialapi.dto.Post;
import com.sridhar.socialapi.dto.PostRequest;
import com.sridhar.socialapi.store.PostStore;
import com.sridhar.socialapi.utils.EntityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PostServiceImpl implements PostService{

    @Override
    public void createPost(PostRequest postRequest, String username) {
        PostStore.savePost(EntityMapper.mapToEntity(postRequest, username));
        log.info("Post Created for user: {}", username);
    }

    @Override
    public List<Post> listAllPosts() {
        List<Post> postList = PostStore.getAllPosts();
        log.info("List of all posts: {}", postList);
        return postList;
    }

    @Override
    public List<Post> getUserPosts(String username) {
        List<Post> postList = PostStore.getMyPost(username);
        log.info("List of user's posts: {}", postList);
        return postList;
    }

    @Override
    public void deletePost(Long id, String username) {
        PostStore.deletePost(id, username);
        log.info("Deleted the post with id: {}", id);
    }

    @Override
    public void likePost(Long id, String username) {
        PostStore.deletePost(id, username);
        log.info("Liked the post id : {} successfully", id);
    }
}
