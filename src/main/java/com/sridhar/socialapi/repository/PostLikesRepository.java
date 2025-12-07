package com.sridhar.socialapi.repository;

import com.sridhar.socialapi.entity.Post;
import com.sridhar.socialapi.entity.PostLikes;
import com.sridhar.socialapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikesRepository extends JpaRepository<PostLikes, Long> {
//    Boolean is();?
    boolean existsByUserAndPost(User user, Post post);

    PostLikes findByUserAndPost(User user, Post post);
}
