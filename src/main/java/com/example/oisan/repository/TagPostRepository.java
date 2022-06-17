package com.example.oisan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.oisan.domain.TagPost;

public interface TagPostRepository extends JpaRepository<TagPost, Integer> {

	@Query(value="SELECT * FROM TAG_POST T JOIN MOODTAG M ON M.MOODTAG_ID = T.MOODTAG_ID JOIN POST P ON P.POST_ID = T.POST_ID WHERE T.POST_ID = ?1", nativeQuery=true)
	List<TagPost> findByPostId(int postId);
	
	@Query(value="SELECT * FROM TAG_POST T JOIN MOODTAG M ON M.MOODTAG_ID = T.MOODTAG_ID JOIN POST P ON P.POST_ID = T.POST_ID WHERE T.MOODTAG_ID = ?1", nativeQuery=true)
	List<TagPost> findByMoodtagMoodtagId(int moodtagId);
	
	@Query(value="SELECT TAG_POST_ID FROM TAG_POST WHERE MOODTAG_ID = ?1 AND POST_ID = ?2", nativeQuery=true)
	int findByMoodtagIdAndByPostId(int moodtagId, int postId);
	
}
