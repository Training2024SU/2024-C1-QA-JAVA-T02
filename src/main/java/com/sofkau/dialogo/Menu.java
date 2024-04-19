package com.sofkau.dialogo;

import com.sofkau.logica.control.MenuSuperAdmin;
import org.w3c.dom.ls.LSOutput;

import static com.sofkau.dialogo.ConstantesMenu.*;

public class Menu {

    public static void menuPrincipal(){
        System.out.println(ConstantesMenu.MSN_BIENVENIDA);
        System.out.println(ConstantesMenu.MSN_INICIOUSER);
        System.out.println(ConstantesMenu.MSN_INICIOEMP);
        System.out.println(ConstantesMenu.MSN_REGISTROUSUARIO);
        System.out.println(ConstantesMenu.MSN_SALIR);
    }

    public static void menuAdministrador(){
        System.out.println(ConstantesMenu.MSN_BIENVENIDA);
        System.out.println(ConstantesMenu.MSN_CREARPROPIETARIO);
        System.out.println(ConstantesMenu.MSN_CREARASIS);
    }

    public static void ingresoEmpleadoAdmin(){
        System.out.println(ConstantesMenu.MSN_BIENVENIDA);
        System.out.println(ConstantesMenu.MSN_CREARPROPIETARIO);
        System.out.println(ConstantesMenu.MSN_CREARASIS);
    }

    public static void ingresoPropietario(){
        System.out.println(ConstantesMenu.MSN_BIENVENIDA);
        System.out.println(ConstantesMenu.MSN_CREARASISP);
    }

    public static void ingresoAsistente(){
        System.out.println(ConstantesMenu.MSN_BIENVENIDA);
        System.out.println(ConstantesMenu.MSN_CREARLIBRO);
        System.out.println(ConstantesMenu.MSN_CREARNOVELA);
        System.out.println(ConstantesMenu.MSN_ACTUALIZARLIBRO);
        System.out.println(ConstantesMenu.MSN_ACTUALIZARNOVELA);
        System.out.println(MSN_ENTREGARLIBRO);
        System.out.println(MSN_LIBRODEV);
        System.out.println(MSN_LISTARPRESTAMOS);
        System.out.println(MSN_INGRESOAU);
        System.out.println(ConstantesMenu.MSN_CREARCANCION);
        System.out.println(ConstantesMenu.MSN_LISTARCANCIONES);
        System.out.println(ConstantesMenu.MSN_CREARVIDEOGRABACION);
        System.out.println(ConstantesMenu.MSN_LISTARVIDEOGRABACIONES);
        System.out.println(ConstantesMenu.MSN_CREARTESIS);
        System.out.println(ConstantesMenu.MSN_LISTARTESIS);
    }

    public static void prestamo(){
        System.out.println(MSN_PRESTAMO);
    }

    public static void ingresoFechaDevolucion(){
        System.out.println(MSN_FECHADEVOLUCION);
    }

    public static void ingresoLibro(){
        System.out.println(ConstantesMenu.MSN_INGRESILIBRO);
    }

    public static void ingresoNovela(){
        System.out.println(ConstantesMenu.MSN_INGRESNOVELA);
    }

    public static void actualizacionNovela(){
        System.out.println(MSN_ACTUALIZARNOVELA);
    }

    public static void actualizacionLibro(){
        System.out.println(MSN_ACTUALIZARLIBRO);
    }

    public static void ingresoTitulo(){
        System.out.println(MSN_TITULO);
    }

    public static void ingresoAutor(){
        System.out.println(MSN_ID_AUTOR);
    }
    public static void ingresoNumPaginas(){
        System.out.println(MSN_NUM_PAGINAS);
    }

    public static void ingresoCantEjemplar(){
        System.out.println(MSN_CANT_EJEMPLARES);
    }

    public static void ingresoCantPrestado(){
        System.out.println(MSN_CANT_PRESTADOS);
    }

    public static void ingresoAreaConocimiento(){
        System.out.println(MSN_AREACONOCIMIENTO);
    }

    public static void ingresoGenero(){
        System.out.println(MSN_GENERO);
    }

    public static void ingresoEdadSugerida(){
        System.out.println(MSN_EDADSUGERIDA);
    }

    public static void tituloActualizar() {
        System.out.println(MSN_TITULOACTULIZAR);
    }

    public static void nombre(){
        System.out.println(ConstantesMenu.MSN_NOMBREU );
    }

    public static void correo(){
        System.out.println(ConstantesMenu.MSN_CORREOU );
    }

    public static void contrasena(){
        System.out.println(ConstantesMenu.MSN_CONTRASENAU );
    }

    public static void menuUsuario() {
        System.out.println(MSN_REALIZARPRESTAMO);
        System.out.println(MSN_LISTARLIBROSU);
        System.out.println(MSN_LISTARNOVELASU);
        System.out.println(MSN_LISTARAUTORESU);
        System.out.println(MSN_LISTARPUBLICACIONAUTOR);
    }

    public static void entregarLibroUsuario() {
        System.out.println(MSN_ENTREGARLIBRO);
    }

    public static void libroDevuelto() {
        System.out.println(MSN_LIBRODEV);
    }

    public static void listarPrestamos() {
        System.out.println(MSN_LISTARPRESTAMOS);
    }

    public static void IngresoIdPrestamo() {
        System.out.println(MSN_INGRESOIDPRESTAMO);
    }

    public static void menuSuperAdmin() {
        System.out.println(ConstantesMenu.MSN_BIENVENIDA);
        System.out.println(MSN_CREARADMIN);
        System.out.println(MSN_ENTRARADMINISTRADOR);
        System.out.println(MSN_ENTRARPROPIETARIO);
        System.out.println(MSN_ENTRARASISTENTE);
        System.out.println(MSN_ENTRARUSUARIO);
        if ((MenuSuperAdmin.isModoSuperAdmin())) {
            System.out.println(MSN_MODOSUPERADMINDES);
        } else {
            System.out.println(MSN_MODOSUPERADMIN);
        }
    }

    public static void ingresoLetra(){
        System.out.println(MSN_LETRA);
    }

    public static void fechaLanzamiento(){
        System.out.println(MSN_FECHA_LANZAMIENTO);
    }

    public static void ingresoSinopsis() {
        System.out.println(ConstantesMenu.MSN_INGRESOSINOPSIS);
    }

    public static void ingresoTipo() {
        System.out.println(ConstantesMenu.MSN_INGRESOTIPO);
    }

    public static void ingresoCalificacion() {
        System.out.println(ConstantesMenu.MSN_INGRESOCALIFICACION);
    }

    public static void ingresoFecha() {
        System.out.println(ConstantesMenu.MSN_INGRESAR_FECHA);
    }

    public static void ingresoCampoEstudio() {
        System.out.println(ConstantesMenu.MSN_INGRESAR_CAMPO_ESTUDIO);
    }

    public static void ingresoPais() {
        System.out.println(ConstantesMenu.MSN_INGRESAR_PAIS);
    }

}
