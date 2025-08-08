package com.sridhar.socialapi.store;


import com.sridhar.socialapi.dto.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserStore {
    private static final Map<String, User> users = new ConcurrentHashMap<>();

    public static void saveUser(User user) {
        users.put(user.getUsername(), user);
    }

    public static User findByUsername(String username) {
        return users.get(username);
    }

    public static boolean exists(String username) {
        return users.containsKey(username);
    }
}
