package com.example.oisan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.oisan.domain.Moodtag;

public interface MoodtagRepository  extends JpaRepository<Moodtag, Integer> {

	Moodtag findByName(String name);
	
	Moodtag findByMoodtagId(int moodtagId);
}
