package com.sridhar.socialapi.store;


import com.sridhar.socialapi.dto.User;
import com.sridhar.socialapi.exception.UserNotFoundException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In-memory store for user data in the Social API application.
 * This simulates a database by storing users in a thread-safe ConcurrentHashMap.
 * Provides methods to save, retrieve, and check user existence.
 */
public class UserStore {
    /**
     * In-memory map to store users.
     * Key: Username (String)
     * Value: User object
     */
    private static final Map<String, User> users = new ConcurrentHashMap<>();

    /**
     * Saves a new user in the in-memory store.
     * If a user with the same username already exists, it will be overwritten.
     *
     * @param user The User object to be saved
     */
    public static void saveUser(User user) {
        users.put(user.getUsername(), user);
    }

    /**
     * Finds a user by username.
     *
     * @param username The username to search for
     * @return The User object if found, otherwise null
     */
    public static User findByUsername(String username) {
        User user = users.get(username);
        if (user == null){
            throw new UserNotFoundException("User with username : "+username+" is not registered yet.");
        }
        return user;
    }


    /**
     * Checks if a user with the given username exists in the store.
     *
     * @param username The username to check
     * @return true if the user exists, false otherwise
     */
    public static boolean exists(String username) {
        return users.containsKey(username);
    }
}
