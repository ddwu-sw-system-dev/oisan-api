package com.example.oisan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.oisan.domain.PostLike;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Integer>{
	
	public PostLike findPostLikeByPostIdAndCustomerId(int postId, int customerId);

}
