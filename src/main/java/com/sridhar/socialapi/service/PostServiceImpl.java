package com.sridhar.socialapi.service;

import com.sridhar.socialapi.dto.PostDetails;
import com.sridhar.socialapi.entity.Post;
import com.sridhar.socialapi.dto.PostRequest;
import com.sridhar.socialapi.entity.PostLikes;
import com.sridhar.socialapi.entity.User;
import com.sridhar.socialapi.repository.PostLikesRepository;
import com.sridhar.socialapi.repository.PostRepository;
import com.sridhar.socialapi.repository.UserRepository;
import com.sridhar.socialapi.utils.EntityMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostLikesRepository postLikesRepository;

    @Override
    public void createPost(PostRequest postRequest, String username) {
        User user = userRepository.findByUsername(username);
        postRepository.save(EntityMapper.mapToEntity(postRequest, user));
        log.info("Post Created for user: {}", username);
    }

    @Override
    public List<PostDetails> listAllPosts() {
        List<Post> postList = postRepository.findAll();
        List<PostDetails> postDetailsList = postList.stream()
                .map(EntityMapper::mapToResponse)
                .toList();
        log.info("List of all posts: {}", postList);
        return postDetailsList;
    }

    @Override
    public List<PostDetails> getUserPosts(String username) {
        User user = userRepository.findByUsername(username);
        List<Post> postList = postRepository.findByAuthor(user);
        List<PostDetails> postDetailsList = postList.stream()
                .map(EntityMapper::mapToResponse)
                .toList();
        log.info("List of user's posts: {}", postDetailsList);
        return postDetailsList;
    }

    @Override
    public void deletePost(Long id, String username) {
        postRepository.deleteById(id);
        log.info("Deleted the post with id: {}", id);
    }

    @Override
    public void deleteAllMyPost(String username) {
        User user = userRepository.findByUsername(username);
        user.getPosts().clear();
        log.info("Deleted the all post of user: {}", user);
    }

    @Override
    public void likePost(Long id, String username) {
        User user = userRepository.findByUsername(username);
        Post post = postRepository.findById(id).get();

        // toggle effect
        if (postLikesRepository.existsByUserAndPost(user, post)) {
            post.getPostLikes().removeIf(x -> x.equals(postLikesRepository.findByUserAndPost(user, post)));
            log.info("undoing the like");
            return;
        }

        PostLikes postLikes = new PostLikes();
        postLikes.setUser(user);
        postLikes.setPost(post);

        postLikesRepository.save(postLikes);

        log.info("Liked the post id : {} successfully", id);
    }
}
