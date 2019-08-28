package com.wcj.TwitterClone.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wcj.TwitterClone.model.Tag;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long>  {
	Tag findByPhrase(String phrase);
}
