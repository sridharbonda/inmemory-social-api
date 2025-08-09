package com.sridhar.socialapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO representing a social media post request.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    /**
     * Post Content to be stored
     */
    private String content;
}
