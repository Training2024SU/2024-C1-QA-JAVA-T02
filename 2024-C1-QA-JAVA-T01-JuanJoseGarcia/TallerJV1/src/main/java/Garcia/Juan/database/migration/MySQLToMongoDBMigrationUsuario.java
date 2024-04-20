package Garcia.Juan.database.migration;

import Garcia.Juan.database.util.ConnectionManager;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLToMongoDBMigrationUsuario {
    public static void main(String[] args) {
        try (Connection mySqlConnection = ConnectionManager.getMySQLConnection();
             MongoClient mongoClient = ConnectionManager.getMongoClient()) {

            MongoDatabase mongoDatabase = mongoClient.getDatabase("bibliotecapingu");
            MongoCollection<Document> collection = mongoDatabase.getCollection("usuario");

            // Consulta para obtener datos de MySQL
            Statement statement = mySqlConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM usuario");

            // Lista para guardar documentos
            List<Document> documents = new ArrayList<>();

            // Convertir registros a documentos
            while (resultSet.next()) {
                Document document = new Document()
                        .append("correo", resultSet.getString("correo"))
                        .append("contrasenha", resultSet.getString("contrasenha"))
                        .append("nombre", resultSet.getString("nombre"))
                        .append("rol", resultSet.getString("rol"));

                documents.add(document);
            }

            // Insertar documentos en MongoDB
            collection.insertMany(documents);
            System.out.println("Migración completa para la tabla 'usuario'");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
