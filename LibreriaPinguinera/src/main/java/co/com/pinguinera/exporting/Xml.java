package co.com.pinguinera.exporting;

import co.com.pinguinera.datos.model.Publicacion;
import co.com.pinguinera.datos.model.publicaciones.Ensayos;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Xml {

    public static void exportarPublicaciones(List<Ensayos> ensayos, String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(Publicacion.class,Ensayos.class,ArrayList.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(ensayos, new File(filePath));
            System.out.println("Publicaciones exportadas correctamente a XML en: " + filePath);
        } catch (JAXBException e) {
            System.err.println("Error al exportar publicaciones a XML: " + e.getMessage());
        }
    }

    public static List<Ensayos> importarPublicaciones(String filePath) {
        List<Ensayos> ensayos = new ArrayList<>();
        try {
            // Crear el contexto JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Ensayos.class);

            // Crear el unmarshaller
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            // Especificar la ubicación del archivo XML
            File xmlFile = new File(filePath);

            // Convertir el archivo XML en un objeto Java
            Ensayos ensayo = (Ensayos) unmarshaller.unmarshal(xmlFile);

            // Agregar el ensayo a la lista
            ensayos.add(ensayo);

            System.out.println("Publicaciones importadas correctamente desde XML: " + filePath);
        } catch (JAXBException e) {
            System.err.println("Error al importar publicaciones desde XML: " + e.getMessage());
        }
        return ensayos;
    }


}
