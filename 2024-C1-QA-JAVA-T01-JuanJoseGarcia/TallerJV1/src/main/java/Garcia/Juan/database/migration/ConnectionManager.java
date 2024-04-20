package Garcia.Juan.database.migration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String mySqlUrl = System.getenv("MYSQL_URL");
    private static final String mySqlUser = System.getenv("MYSQL_USER");
    private static final String mySqlPassword = System.getenv("MYSQL_PASSWORD");
    private static final String mongoConnectionString = System.getenv("MONGO_CONNECTION_STRING");

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
