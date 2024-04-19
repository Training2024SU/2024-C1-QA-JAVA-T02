package Garcia.Juan.database.migration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLToMongoDBMigrationPrestamo {
    public static void main(String[] args) {
        // Conexión a MySQL
        String mySqlUrl = "jdbc:mysql://localhost:3306/bibliotecapingu";
        String mySqlUser = "root";
        String mySqlPassword = "#32zvv48dH";

        try (Connection mySqlConnection = DriverManager.getConnection(mySqlUrl, mySqlUser, mySqlPassword)) {
            // Conexión a MongoDB
            String connectionString = "mongodb+srv://dm34mg:sTuFu28myI2b7Bs2@bibliotecapingu.vzsvhqa.mongodb.net/?retryWrites=true&w=majority&appName=Bibliotecapingu";
            MongoClient mongoClient = MongoClients.create(connectionString);
            MongoDatabase mongoDatabase = mongoClient.getDatabase("bibliotecapingu");
            MongoCollection<Document> collection = mongoDatabase.getCollection("prestamo");

            // Crear índice único en el campo 'id' si no existe
            collection.createIndex(new Document("id", 1), new IndexOptions().unique(true));

            // Consulta para obtener datos de MySQL
            Statement statement = mySqlConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM prestamo");

            // Convertir registros a documentos e insertarlos en MongoDB
            while (resultSet.next()) {
                // Crear un documento para el registro
                Document document = new Document()
                        .append("id", resultSet.getString("id"))
                        .append("estado", resultSet.getString("estado"))
                        .append("fecha_salida", resultSet.getDate("fecha_salida"))
                        .append("fecha_devolucion", resultSet.getDate("fecha_devolucion"))
                        .append("correo_usuario", resultSet.getString("correo_usuario"));

                // Verificar si el documento ya existe en la colección
                Bson filter = Filters.eq("id", document.getString("id"));
                if (collection.countDocuments(filter) == 0) {
                    // Si el documento no existe, insertarlo en la colección
                    collection.insertOne(document);
                } else {
                    // El documento ya existe, omitir la inserción
                    System.out.println("Documento con id '" + document.getString("id") + "' ya existe. Omite la inserción.");
                }
            }

            System.out.println("Migración completa para la tabla 'prestamo'");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
