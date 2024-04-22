package com.davidbonelo.persistance;

import com.davidbonelo.models.User;
import com.davidbonelo.models.UserRole;
import com.davidbonelo.mongodb.MongoDBConnector;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class UserDAOMongo {
    private final MongoDatabase mongoDatabase;

    public UserDAOMongo(MongoDatabase mongoDatabase) {
        this.mongoDatabase = mongoDatabase;
    }

    public MongoCollection<Document> getAllUsersFromMongoDB(){
        List<User> users = new ArrayList<>();
        MongoCollection<Document> collection = mongoDatabase.getCollection("users");
        return collection;
    }
    private User buildUserFromDocument(Document document) {
        int id = document.getInteger("id");
        String name = document.getString("name");
        String email = document.getString("email");
        String roleString = document.getString("role");
        UserRole role = UserRole.valueOf(roleString);
        String biography = document.getString("biography");
        Date birthday = Date.valueOf(String.valueOf(document.getDate("birthday")));
        return new User(id, name, email, role, biography, birthday);
    }
}
