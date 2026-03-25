package com.harsh.joblisting.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import com.harsh.joblisting.model.Post;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
@Component
public class SearchRepositoryImplementation implements SearchRepository  {
	
	@Autowired
	MongoClient client;
	
	@Autowired
	MongoConverter convertor;
	@Override
	public List<Post> findByText(String text){
		final List<Post> posts = new ArrayList<>();
		MongoDatabase database = client.getDatabase("<DataBaseName>");
		MongoCollection<Document> collection = database.getCollection("<CollectionName>");
		AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search", 
		    new Document("index", "default")
		            .append("text", 
		    new Document("query", text)
		                .append("path", Arrays.asList("techs", "desc", "profile"))
		                .append("matchCriteria", "any"))), 
		    new Document("$sort", 
		    new Document("exp", 1L)), 
		    new Document("$limit", 5L)));
		result.forEach(doc -> posts.add(convertor.read(Post.class,doc)));
		return posts;
	}

}
