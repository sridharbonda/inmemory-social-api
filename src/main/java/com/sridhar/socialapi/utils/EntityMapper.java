package com.sridhar.socialapi.utils;

import com.sridhar.socialapi.dto.Likes;
import com.sridhar.socialapi.dto.PostDetails;
import com.sridhar.socialapi.entity.Post;
import com.sridhar.socialapi.dto.PostRequest;
import com.sridhar.socialapi.entity.PostLikes;
import com.sridhar.socialapi.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EntityMapper {

    public static Post mapToEntity(PostRequest postRequest, User user) {
        return Post.builder()
                .author(user)
                .content(postRequest.getContent())
                .localDateTime(LocalDateTime.now())
                .build();
    }

    public static PostDetails mapToResponse(Post post) {
        PostDetails postDetails = new PostDetails();

        postDetails.setId(post.getId());
        postDetails.setAuthor(post.getAuthor().getUsername());
        postDetails.setContent(post.getContent());
        postDetails.setCreatedDate(post.getLocalDateTime());

        List<Likes> likeList = new ArrayList<>();
        for (PostLikes postLikes: post.getPostLikes()) {
            Likes like = new Likes();
            like.setUser(postLikes.getUser().getUsername());
            likeList.add(like);
        }

        postDetails.setLikes(likeList);

        return postDetails;
    }
}
