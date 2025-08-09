package com.sridhar.socialapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing a user in the social API.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    /**
     * The unique username for the user.
     */
    private String username;

    /**
     * The password for the user.
     * This will be securely stored in hashed form.
     */
    private String password;
}
