package com.sridhar.socialapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventModel {
    private String author;
    private Long postId;
    private String likedBy;
}
