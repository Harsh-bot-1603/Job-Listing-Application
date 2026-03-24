package com.harsh.joblisting.repositories;

import java.util.List;

import com.harsh.joblisting.model.Post;

public interface SearchRepository {
	List<Post> findByText(String text);

}
