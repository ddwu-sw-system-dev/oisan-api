package com.example.oisan.repository;

import com.example.oisan.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByTitleContaining(String title);

    List<Post> findByCustomerCustomerId(int customerId);
    
    Post findByPostId(int postId);
    
    @Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="UPDATE POST SET STATUS = ?1 WHERE POST_ID = ?2", nativeQuery=true)
	int updateStatusByPostId(int status, int postId);

}
