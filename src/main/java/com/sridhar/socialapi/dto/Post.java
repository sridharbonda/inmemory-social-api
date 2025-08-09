package com.sridhar.socialapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * DTO representing a social media post.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {
    /**
     * Unique identifier for the post.
     */
    private Long id;

    /**
     * Username of the post author.
     */
    private String author;

    /**
     * Main text content of the post.
     */
    private String content;

    /**
     * Date and time when the post was created.
     */
    private LocalDateTime localDateTime;

    /**
     * Set of usernames who have liked this post.
     * Defaults to an empty set to avoid NullPointerException.
     */
    private Set<String> likedBy = new HashSet<>();
}
