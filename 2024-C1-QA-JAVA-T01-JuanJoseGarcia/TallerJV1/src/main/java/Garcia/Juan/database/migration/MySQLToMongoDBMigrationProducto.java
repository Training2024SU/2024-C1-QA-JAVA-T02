package Garcia.Juan.database.migration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLToMongoDBMigrationProducto {
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
            MongoCollection<Document> collection = mongoDatabase.getCollection("producto");

            // Consulta para obtener datos de MySQL
            Statement statement = mySqlConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM producto");

            // Convertir registros a documentos e insertarlos en MongoDB
            while (resultSet.next()) {
                // Crear un documento para el registro
                Document document = new Document()
                        .append("titulo", resultSet.getString("titulo"))
                        .append("tipo", resultSet.getString("tipo"))
                        .append("autor", resultSet.getString("autor"))
                        .append("magnitud", resultSet.getString("magnitud"))
                        .append("cant_ejemplares", resultSet.getInt("cant_ejemplares"))
                        .append("cant_prestados", resultSet.getInt("cant_prestados"))
                        .append("cant_disponibles", resultSet.getInt("cant_disponibles"));

                // Insertar el documento en la colección
                collection.insertOne(document);
            }

            System.out.println("Migración completa para la tabla 'producto'");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
