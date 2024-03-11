package com.kodnest.tunehub.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.Song;

@Service
public interface SongService {

	public String addSong(Song song);


	public boolean linkExists(String link);


	public List<Song> getAllSongs();


	public void updateSong(Song s);

	

}
