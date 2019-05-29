package com.jkbjhs.postgresdemo.controller;


import com.jkbjhs.postgresdemo.exception.ResourceNotFoundException;
import com.jkbjhs.postgresdemo.model.FeedPost;
import com.jkbjhs.postgresdemo.repository.FeedPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class FeedPostController {

    @Autowired
    private FeedPostRepository feedPostRepository;

    @GetMapping("/feed_posts")
    public List<FeedPost> getFeedPosts() {
        return feedPostRepository.findAll();
    }

    @GetMapping("/feed_posts/users/{user_id}")
    public List<FeedPost> getFeedPostsByUser(@PathVariable Long user_id) {
        return feedPostRepository.findByUserId(user_id);
    }

    @PostMapping("/feed_posts")
    public FeedPost createFeedPost(@Valid @RequestBody FeedPost feedPost) {
//        feedPostRepository.flush();
        return feedPostRepository.save(feedPost);
    }

    @DeleteMapping("/feed_posts/{post_id}")
    public ResponseEntity<?> deleteFeedPost(@PathVariable Long post_id) {
        if(!feedPostRepository.existsById(post_id)) {
            throw new ResourceNotFoundException("Feed post not found with id: " + post_id);
        }

        return feedPostRepository.findById(post_id)
                .map(post -> {
                    feedPostRepository.delete(post);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Workout not found with id: " + post_id));
    }
}
