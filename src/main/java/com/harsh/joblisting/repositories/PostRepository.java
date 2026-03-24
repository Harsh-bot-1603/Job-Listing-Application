package com.harsh.joblisting.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.harsh.joblisting.model.Post;

public interface PostRepository extends MongoRepository<Post, String> {
	
	

}
