package com.wcj.TwitterClone.repository;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wcj.TwitterClone.model.User;

@SpringBootApplication
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}