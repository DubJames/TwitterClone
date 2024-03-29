package com.wcj.TwitterClone.model;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SpringBootApplication
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TweetDisplay {
    private User user;
	
    private String message;
	
	private String date;
	
    private List<Tag> tags;
}
