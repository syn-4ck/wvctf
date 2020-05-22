package com.julianfm.wvctf.model.repository.mongo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.julianfm.wvctf.model.entity.mongo.Contact;
import com.julianfm.wvctf.model.exception.UserNotFoundException;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public interface ContactRepository extends MongoRepository<Contact,String> {
	
	public default Contact findByName (String name) {
		
		ServerAddress address = new ServerAddress("localhost", 27017);
		
		MongoCredential credential = MongoCredential.createCredential("root", "admin", 
           "toor".toCharArray()); 
		List<MongoCredential> credentials = new ArrayList<MongoCredential>();
		credentials.add(credential);
		
		MongoClient mongoClient = new MongoClient(address,credentials);
        
		DB database = mongoClient.getDB("WVCTF");
		DBCollection collection = database.getCollection("Contact");
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("name", name);
		DBCursor cursor = collection.find(searchQuery);
		
		Contact contact = new Contact();
		
		while (cursor.hasNext()) {
		    DBObject theObj = cursor.next();
		    
		    contact.setId(null);
		    contact.setName((String)theObj.get("name"));
		    contact.setPhoneNumber((String)theObj.get("phoneNumber"));
		    contact.setMail((String)theObj.get("mail"));
		    
		}
		
		if (contact.getName()==null) {
			throw new UserNotFoundException();
		}
		
		mongoClient.close();
		
		return contact;
		
	}

}
