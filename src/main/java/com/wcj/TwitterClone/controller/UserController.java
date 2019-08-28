package com.wcj.TwitterClone.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wcj.TwitterClone.model.TweetDisplay;
import com.wcj.TwitterClone.model.User;
import com.wcj.TwitterClone.service.TweetService;
import com.wcj.TwitterClone.service.UserService;

@Controller
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private TweetService tweetService;
    
    @GetMapping(value = "/users/{username}")
   
    public String getUser(@PathVariable(value="username") String username, Model model) {	
    	
    	//following
    	User loggedInUser = userService.getLoggedInUser();
    	List<User> following = loggedInUser.getFollowing();
    	boolean isFollowing = false;
    	for (User followedUser : following) {
    	    if (followedUser.getUsername().equals(username)) {
    	        isFollowing = true;
    	    }
    	}
    	model.addAttribute("following", isFollowing);
    	//does the page we're looking at belong to the logged in user? can't follow self
    	boolean isSelfPage = loggedInUser.getUsername().equals(username);
    	model.addAttribute("isSelfPage", isSelfPage);
    	
    	User user = userService.findByUsername(username);
        List<TweetDisplay> tweets = tweetService.findAllByUser(user);
        model.addAttribute("tweetList", tweets);
        model.addAttribute("user", user);
        return "user";
        
    }
    
    private void SetTweetCounts(List<User> users, Model model) {
        HashMap<String,Integer> tweetCounts = new HashMap<>();
        for (User user : users) {
            List<TweetDisplay> tweets = tweetService.findAllByUser(user);
            tweetCounts.put(user.getUsername(), tweets.size());
        }
        model.addAttribute("tweetCounts", tweetCounts);
    }
    
    private void SetFollowingStatus(List<User> users, List<User> usersFollowing, Model model) {
        HashMap<String,Boolean> followingStatus = new HashMap<>();
        String username = userService.getLoggedInUser().getUsername();
        for (User user : users) {
            if(usersFollowing.contains(user)) {
                followingStatus.put(user.getUsername(), true);
            }else if (!user.getUsername().equals(username)) {
              followingStatus.put(user.getUsername(), false);
        	}
        }
        model.addAttribute("followingStatus", followingStatus);
    }
    
    @GetMapping(value = "/users")
    public String getUsers(Model model) {
    	List<User> users = userService.findAll();
        model.addAttribute("users", users);
        SetTweetCounts(users, model);
        User loggedInUser = userService.getLoggedInUser();
        List<User> usersFollowing = loggedInUser.getFollowing();
        SetFollowingStatus(users, usersFollowing, model);        
        return "users";          
    	
        }

    
}