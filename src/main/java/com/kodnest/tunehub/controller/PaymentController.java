package com.kodnest.tunehub.controller;


import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.service.SongService;
import com.kodnest.tunehub.service.UserService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import jakarta.servlet.http.HttpSession;

@Controller
public class PaymentController {
	@Autowired
	UserService userService;
	
	@Autowired
	SongService songService;

	@GetMapping("/pay")
	public String pay() {
		return "pay";
	}
	//It will fetch all the song objects present in the db and print them on the console.
		//This is for the admin
		@GetMapping("/songs")
		public String viewsongs(Model model){
			List<Song> songList = songService.getAllSongs();
			model.addAttribute("songs", songList);
			return "displaysongs";
		}
	
	

	@SuppressWarnings("finally")
	@PostMapping("/createOrder")
	@ResponseBody
	public String createOrder(HttpSession session) {

		int  amount  = 999;
		Order order=null;
		try {
			RazorpayClient razorpay=new RazorpayClient("rzp_test_Gu7f80clD9PXsl", "EK6ZpBsNgyfb1c24b0AsVls4");

			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount", amount*100); // amount in the smallest currency unit
			orderRequest.put("currency", "INR");
			orderRequest.put("receipt", "order_rcptid_11");

			order = razorpay.orders.create(orderRequest);

			String mail =  (String) session.getAttribute("email");

			User user = userService.getUser(mail);
			user.setIspremium(true);
			userService.updateUser(user);
			
			return "displaysongs";
			

		} catch (RazorpayException e) {
			e.printStackTrace();
		}
		finally {
			return order.toString();
		}
	}	
}