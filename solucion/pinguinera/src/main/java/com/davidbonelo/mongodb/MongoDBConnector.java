package com.davidbonelo.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import static com.davidbonelo.mongodb.MongoDbConnectionConstant.CONNECTION_STRING;
import static com.davidbonelo.mongodb.MongoDbConnectionConstant.DATABASE_NAME;

public class MongoDBConnector {
    private static MongoClient mongoClient;
    private static MongoDatabase mongoDatabase;

    public MongoDBConnector(){
        try{
            mongoClient = MongoClients.create(CONNECTION_STRING);
            mongoDatabase = mongoClient.getDatabase(DATABASE_NAME);
            System.out.println("Successfully connected");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }

    public void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Closed connection");
        }
    }
}
