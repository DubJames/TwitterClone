package com.wcj.TwitterClone.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wcj.TwitterClone.model.Tweet;
import com.wcj.TwitterClone.model.User;

@SpringBootApplication
@Repository
public interface TweetRepository extends CrudRepository<Tweet, Long> {
	List<Tweet> findAllByUserOrderByCreatedAtDesc(User user);
	List<Tweet> findByTags_PhraseOrderByCreatedAtDesc(String phrase);
	List<Tweet> findAllByUserInOrderByCreatedAtDesc(List<User> users);
	List<Tweet> findAllByOrderByCreatedAtDesc();
}