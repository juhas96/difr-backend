package com.jkbjhs.postgresdemo.repository;

import com.jkbjhs.postgresdemo.model.FeedPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedPostRepository extends JpaRepository<FeedPost, Long> {
    List<FeedPost> findByUserId(Long userId);
}
