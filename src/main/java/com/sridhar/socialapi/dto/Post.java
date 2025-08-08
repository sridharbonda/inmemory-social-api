package com.sridhar.socialapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {
    private Long id;
    private String author;
    private String content;
    private LocalDateTime localDateTime;
    private Set<String> likedBy = new HashSet<>();
}
