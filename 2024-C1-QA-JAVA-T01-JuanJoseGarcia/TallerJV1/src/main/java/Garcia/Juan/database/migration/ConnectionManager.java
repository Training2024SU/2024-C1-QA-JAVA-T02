package Garcia.Juan.database.util;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String mySqlUrl = "jdbc:mysql://localhost:3306/bibliotecapingu";
    private static final String mySqlUser = "root";
    private static final String mySqlPassword = "#32zvv48dH";
    private static final String mongoConnectionString = "mongodb+srv://dm34mg:sTuFu28myI2b7Bs2@bibliotecapingu.vzsvhqa.mongodb.net/?retryWrites=true&w=majority&appName=Bibliotecapingu";

    public static Connection getMySQLConnection() throws SQLException {
        return DriverManager.getConnection(mySqlUrl, mySqlUser, mySqlPassword);
    }

    public static MongoClient getMongoClient() {
        return MongoClients.create(mongoConnectionString);
    }

    public static MongoDatabase getMongoDatabase(String dbName) {
        MongoClient mongoClient = getMongoClient();
        return mongoClient.getDatabase(dbName);
    }

    public static MongoCollection<Document> getMongoCollection(String dbName, String collectionName) {
        MongoDatabase mongoDatabase = getMongoDatabase(dbName);
        return mongoDatabase.getCollection(collectionName);
    }
}
