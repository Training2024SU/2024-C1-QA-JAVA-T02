package co.com.pinguinera.datos.conexionBD.mongo;

import co.com.pinguinera.datos.model.Publicacion;
import co.com.pinguinera.datos.model.enums.TipoPublicacion;
import co.com.pinguinera.datos.model.publicaciones.Ensayos;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.List;

public class MongoDBExample {
    public static void agregarPublis(List<Ensayos> publicaciones) {

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("pinguinera");
            MongoCollection<Document> collection = database.getCollection("publicaciones");

            // Convierte el objeto Persona a un documento BSON
            for (Publicacion publicacion:publicaciones){
                Document document = new Document("_id",publicacion.getIdPublicacion()).append(
                        "Titulo", publicacion.getTitulo())
                        .append("Tipo", publicacion.getTipoPublicacion().toString())
                        .append("Autor",publicacion.getAutor())
                        .append("Formato",publicacion.getFormato())
                        .append("CantEjemplares",publicacion.getCantEjemplares())
                        .append("CantPrestados",publicacion.getCantPrestados())
                        .append("CantDisponible",publicacion.getCantDisponible());
                // Inserta el documento en la colección
                collection.insertOne(document);
            }


            System.out.println("Persona agregada correctamente a la base de datos.");
        } catch (Exception e) {
            System.err.println("Error al conectar con MongoDB: " + e.getMessage());
        }
    }
    public static Publicacion obtenerPublicacionPorId(int id) {
        Publicacion publicacion = null;
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("pinguinera");
            MongoCollection<Document> collection = database.getCollection("publicaciones");

            // Realiza la búsqueda por el ID especificado
            Document query = new Document("_id", id);
            Document result = collection.find(query).first();

            // Si se encuentra la publicación, convierte el documento a objeto Publicacion
            if (result != null) {
                TipoPublicacion tipo = TipoPublicacion.valueOf(result.getString("Tipo"));
                publicacion = new Publicacion(
                        result.getInteger("_id"),
                        result.getString("Titulo"),
                        tipo,
                        result.getString("Autor"),
                        result.getString("Formato"),
                        result.getInteger("CantEjemplares"),
                        result.getInteger("CantPrestados"),
                        result.getInteger("CantDisponible")
                );
            }
        } catch (Exception e) {
            System.err.println("Error al conectar con MongoDB: " + e.getMessage());
        }
        System.out.println(publicacion);
        return publicacion;
    }
}