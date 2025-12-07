package com.sridhar.socialapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDetails {

    private Long id;
    private String author;
    private String content;
    private LocalDateTime createdDate;
    private List<Likes> likes;
}
