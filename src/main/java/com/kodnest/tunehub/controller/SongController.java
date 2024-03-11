package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.service.SongService;

@Controller
public class SongController {

	@Autowired
	SongService songService;
	
	//To add a new song on  to the DB
	@PostMapping("/addsong")
	public String addsong(@ModelAttribute Song song) {
		//fetching the link from song
		String link = song.getLink();
		//This method will check if the link of that song is already present in the DB or not
		boolean isPresent = songService.linkExists(link);
		
		//if the link is not present it will be added to the DB 
		if(!isPresent) songService.addSong(song);
		//And if it is present it will be redirected back to the newsong.html page
		else return "newsong";
		
		return "adminhome";
	}
	
	
	//It will fetch all the song objects present in the db and print them on the console.
	//This is for the admin
	@GetMapping("/viewsongs")
	public String viewsongs(Model model){
		List<Song> songList = songService.getAllSongs();
		model.addAttribute("songs", songList);
		return "displaysongs";
	}
	
	//It will fetch all the song objects present in the db and print them on the console.
	//This is for the user
	@GetMapping("/playsongs")
	public String playsongs(Model model){
		boolean premium = false;
		if(premium) {
			List<Song> songList = songService.getAllSongs();
			model.addAttribute("songs", songList);
			return "displaysongs";
		}else {
			return "subscriptionform";
		}
		
	}
}
