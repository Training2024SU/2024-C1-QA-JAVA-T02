package Garcia.Juan.database.migration;

import Garcia.Juan.database.util.ConnectionManager;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLToMongoDBMigrationAreaGenero {
    public static void main(String[] args) {
        try (Connection mySqlConnection = ConnectionManager.getMySQLConnection();
             MongoClient mongoClient = ConnectionManager.getMongoClient()) {

            MongoCollection<Document> collection = ConnectionManager.getMongoCollection("bibliotecapingu", "area_genero");

            // Consulta para obtener datos de MySQL
            Statement statement = mySqlConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM area_genero");

            // Lista para guardar documentos
            List<Document> documents = new ArrayList<>();

            // Convertir registros a documentos
            while (resultSet.next()) {
                Document document = new Document()
                        .append("titulo", resultSet.getString("titulo"))
                        .append("area_genero", resultSet.getString("area_genero"));

                documents.add(document);
            }

            // Insertar documentos en MongoDB
            collection.insertMany(documents);
            System.out.println("Migración completa para la tabla 'area_genero'");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
