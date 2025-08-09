package com.sridhar.socialapi.utils;

import com.sridhar.socialapi.dto.Post;
import com.sridhar.socialapi.dto.PostRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

public class EntityMapper {

    public static Post mapToEntity(PostRequest postRequest, String username) {
        return Post.builder()
                .author(username)
                .content(postRequest.getContent())
                .localDateTime(LocalDateTime.now())
                .build();
    }
}
