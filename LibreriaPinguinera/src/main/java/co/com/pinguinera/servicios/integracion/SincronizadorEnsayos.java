package co.com.pinguinera.servicios.integracion;

import co.com.pinguinera.datos.DAO.EnsayoDAO;
import co.com.pinguinera.datos.crud_local.CRUDEnsayosLocales;
import co.com.pinguinera.datos.model.publicaciones.Ensayos;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SincronizadorEnsayos {
    private final EnsayoDAO ensayoDAO;
    private final CRUDEnsayosLocales crudEnsayosLocales;

    public SincronizadorEnsayos(EnsayoDAO ensayoDAO, CRUDEnsayosLocales crudEnsayosLocales) {
        this.ensayoDAO = ensayoDAO;
        this.crudEnsayosLocales = crudEnsayosLocales;
    }

    public void sincronizarEnsayos() throws SQLException {
        List<Ensayos> ensayosBD = ensayoDAO.obtenerTodos();
        List<Ensayos> ensayosLocales = crudEnsayosLocales.obtenerTodos();

        Map<Integer, Ensayos> mapaEnsayosLocales = new HashMap<>();
        for (Ensayos ensayoLocal : ensayosLocales) {
            mapaEnsayosLocales.put(ensayoLocal.getIdPublicacion(), ensayoLocal);
        }

        for (Ensayos ensayoBD : ensayosBD) {
            Ensayos ensayoLocal = mapaEnsayosLocales.get(ensayoBD.getIdPublicacion());
            if (ensayoLocal != null) {
                if (!ensayoLocal.equals(ensayoBD)) {
                    ensayoDAO.actualizar(ensayoLocal);
                }
                mapaEnsayosLocales.remove(ensayoLocal.getIdPublicacion());
            }
        }

        // Inserta libros locales que no están en la base de datos
        for (Ensayos ensayoLocal : mapaEnsayosLocales.values()) {
            ensayoDAO.insertar(ensayoLocal);
        }
    }

}
