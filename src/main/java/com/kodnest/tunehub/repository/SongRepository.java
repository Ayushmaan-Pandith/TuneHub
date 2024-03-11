package com.kodnest.tunehub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kodnest.tunehub.entity.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer>{

	 @Query("SELECT count(s) > 0 FROM Song s WHERE s.link = :link")
	boolean findByLink(@Param("link") String link);

	

	

}
