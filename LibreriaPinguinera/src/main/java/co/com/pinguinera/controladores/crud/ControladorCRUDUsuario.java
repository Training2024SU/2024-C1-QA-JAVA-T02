package co.com.pinguinera.controladores.crud;

import co.com.pinguinera.datos.DAO.UsuarioDAO;
import co.com.pinguinera.datos.crud_local.CRUDUsuariosLocales;
import co.com.pinguinera.datos.model.Usuario;
import co.com.pinguinera.servicios.integracion.SincronizadorUsuario;
import co.com.pinguinera.vistas.VistaUtil;
import co.com.pinguinera.vistas.vista_usuario.InformacionUsuarioVista;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class ControladorCRUDUsuario {

    private final InformacionUsuarioVista vista;
    private final CRUDUsuariosLocales crudUsuariosLocales;
    private final UsuarioDAO usuarioDAO;
    private final SincronizadorUsuario sincronizadorUsuario;

    public ControladorCRUDUsuario(InformacionUsuarioVista vista, CRUDUsuariosLocales crudUsuariosLocales,
                                  UsuarioDAO usuarioDAO, SincronizadorUsuario sincronizadorUsuario) {
        this.vista = vista;
        this.crudUsuariosLocales = crudUsuariosLocales;
        this.usuarioDAO = usuarioDAO;
        this.sincronizadorUsuario = sincronizadorUsuario;
    }

    public void registrarUsuario() {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(vista.pedirNombreUsuario());
        nuevoUsuario.setCorreo(vista.pedirCorreoUsuario());
        nuevoUsuario.setContrasena(vista.pedirContrasenaUsuario());

        crudUsuariosLocales.agregar(nuevoUsuario);
        try {
            usuarioDAO.insertar(nuevoUsuario);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
        }
        List<String> infoAdicional= vista.preguntaInfoAdicional();
        try{
            if (!infoAdicional.get(0).equals("NO")){
                nuevoUsuario.setEdad(infoAdicional.get(0));
                nuevoUsuario.setTelefono(infoAdicional.get(1));
                usuarioDAO.insertarInfoAdicional(nuevoUsuario);
            }
        } catch (SQLException e){
            System.out.println("Error info add");
        }
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    public void actualizarUsuario() {
        Usuario usuarioActualizado = new Usuario();
        Scanner scanner =new Scanner(System.in);
        System.out.println("Ingrese el Id del usuario que desea actualizar");
        usuarioActualizado.setIdUsuario(Integer.parseInt(scanner.nextLine()));
        usuarioActualizado.setNombre(vista.pedirNombreUsuario());
        usuarioActualizado.setCorreo(vista.pedirCorreoUsuario());
        usuarioActualizado.setContrasena(vista.pedirContrasenaUsuario());
        crudUsuariosLocales.actualizar(usuarioActualizado);
        try {
            usuarioDAO.actualizar(usuarioActualizado);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
            return;
        }
        List<String> infoAdicional= vista.preguntaInfoAdicional();
        try{
            if (!infoAdicional.get(0).equals("NO")){
                usuarioActualizado.setEdad(infoAdicional.get(0));
                usuarioActualizado.setTelefono(infoAdicional.get(1));
                usuarioDAO.actualizarInfoAdicional(usuarioActualizado);
            }
        } catch (SQLException e){
            System.out.println("Error info update");
        }
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    public void eliminarUsuario() {
        String correo = vista.pedirCorreoUsuario();
        Usuario usuarioAEliminar = new Usuario();
        usuarioAEliminar.setCorreo(correo);
        crudUsuariosLocales.eliminar(usuarioAEliminar);
        try {
            usuarioDAO.eliminar(usuarioAEliminar);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
            return;
        }
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    public void obtenerTodosUsuarios() {
        try {
            List<Usuario> usuarios = usuarioDAO.obtenerTodos();
            usuarios.forEach(System.out::println);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeSolicitudFallida();
        }
    }

    private void sincronizarDatos() {
        try {
            sincronizadorUsuario.sincronizarUsuarios();
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
        }
    }
}
