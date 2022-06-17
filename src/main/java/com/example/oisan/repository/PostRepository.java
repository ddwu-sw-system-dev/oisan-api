package com.example.oisan.repository;

import com.example.oisan.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByTitleContaining(String title);

    List<Post> findByCustomerCustomerId(int customerId);
    
    Post findPostByPostId(int postId);
}
