package com.sridhar.socialapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * DTO representing a social media post.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Posts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Post {
    /**
     * Unique identifier for the post.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Username of the post author.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false)
    private User author;

    /**
     * Main text content of the post.
     */
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnore
    private List<PostLikes> postLikes = new ArrayList<>();

    /**
     * Date and time when the post was created.
     */
    private LocalDateTime localDateTime;

//    /**
//     * Set of usernames who have liked this post.
//     * Defaults to an empty set to avoid NullPointerException.
//     */
//    @Builder.Default
//    private Set<String> likedBy = new HashSet<>();
}
