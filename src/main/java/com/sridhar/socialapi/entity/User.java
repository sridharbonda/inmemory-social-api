package com.sridhar.socialapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object (DTO) representing a user in the social API.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    /**
     * The unique username for the user.
     */
    @Id
    private String username;

    /**
     * The password for the user.
     * This will be securely stored in hashed form.
     */
    @JsonIgnore
    @ToString.Exclude
    private String password;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private List<PostLikes> postLikes = new ArrayList<>();

}
