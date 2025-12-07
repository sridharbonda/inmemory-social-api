package com.sridhar.socialapi.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String username;

    /**
     * The password for the user.
     * This will be securely stored in hashed form.
     */
    private String password;
}
