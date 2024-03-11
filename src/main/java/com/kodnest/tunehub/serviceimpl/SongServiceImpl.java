package com.kodnest.tunehub.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.repository.SongRepository;
import com.kodnest.tunehub.service.SongService;

@Service
public class SongServiceImpl implements SongService{

	@Autowired
	SongRepository songRepository;

	//To add a new song on to the DB
	@Override
	public String addSong(Song song) {
		songRepository.save(song);
		return "Song Added";
	}

	//To check if the link of that song already exists in the DB. 
	//If it is present it will return True and if it is not present it will return False.
	@Override
	public boolean linkExists(String link) {
		boolean byLink = songRepository.findByLink(link);
		return byLink;
	}

	//It will return every song object present in the db.
	@Override
	public List<Song> getAllSongs() {
		return songRepository.findAll();
	}

	@Override
	public void updateSong(Song s) {

		songRepository.save(s);
	}

	

	
}
