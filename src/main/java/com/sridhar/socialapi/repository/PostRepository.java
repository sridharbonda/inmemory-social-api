package com.sridhar.socialapi.repository;

import com.sridhar.socialapi.entity.Post;
import com.sridhar.socialapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthor(User user);
}
