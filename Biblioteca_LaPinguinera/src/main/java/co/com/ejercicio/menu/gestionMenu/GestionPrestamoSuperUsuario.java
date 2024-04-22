package co.com.ejercicio.menu.gestionMenu;

import co.com.ejercicio.conexionBd.Conexion;
import co.com.ejercicio.modelo.Prestamo;
import co.com.ejercicio.modeloAccesoBD.PrestamoAccesoBD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static co.com.ejercicio.menu.dialogos.MenuGestionPrestamo.menuPrestamos;
import static co.com.ejercicio.menu.dialogos.MenuGestionPrestamoSuperUsuario.menuPrestamosSuperUsuario;

public class GestionPrestamoSuperUsuario {

    public static Connection conexion;
    public static PrestamoAccesoBD prestamoAccesoBD;
    public static List<Prestamo> prestamos;
    static {
        try {
            conexion = Conexion.obtenerConexion();
            prestamoAccesoBD = new PrestamoAccesoBD(conexion);
            prestamos = prestamoAccesoBD.obtenerTodosLosPrestamos();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void mostrarMenuGestionPrestamoSuperUsuario(){
        menuPrestamosSuperUsuario();
    }

    public static void insertarPrestamoSuperUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insertando préstamo...");
        System.out.println("Ingrese id ");
        int idPrestamo = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Fecha prestamo");
        Date fechaPrestamo = Date.valueOf(scanner.nextLine());
        System.out.println("Fecha devolucion");
        Date fechaDevolucion = Date.valueOf(scanner.nextLine());
        System.out.println("Estado");
        String estado = scanner.nextLine();
        System.out.println("Correo usuario");
        String correoUsuario = scanner.nextLine();
        System.out.println("Titulo publicacion");
        String tituloPublicacion = scanner.nextLine();

        Prestamo prestamo = new Prestamo(idPrestamo, fechaPrestamo, fechaDevolucion, estado, correoUsuario, tituloPublicacion);
        prestamos.add(prestamo);
    }

    public static void obtenerPrestamoSuperUsuario() {
        System.out.println("Obteniendo préstamo...");

        for (Prestamo prestamo : prestamos) {
            System.out.println(prestamo.toString());
        }
    }

    public static void eliminarPrestamoSuperUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Eliminando préstamo...");
        System.out.println("Ingresa el id a eliminar");
        int idPrestamo = scanner.nextInt();
        int posicionParaBorrar = -1;

        for(int i = 0; i < prestamos.size(); i++){
            if(prestamos.get(i).getIdPrestamo() == idPrestamo){
                posicionParaBorrar = i;
            }
        }

        if(posicionParaBorrar == -1){
            System.out.println("No se encontro prestamo con id: " + idPrestamo );
        } else {
            prestamos.remove(posicionParaBorrar);
        }
    }

    public static void actualizarPrestamoSuperUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Actualizando préstamo...");
        System.out.println("Ingrese id de prestamo ha actualizar");

        int idPrestamo = scanner.nextInt();
        scanner.nextLine();

        try {
            Prestamo prestamoAEditar = prestamos.stream()
                    .filter(prestamo -> prestamo.getIdPrestamo() == idPrestamo).findFirst().orElseThrow();

            System.out.println("Editando prestamo: " + prestamoAEditar);

            System.out.println("Editando fecha prestamo");
            Date fechaPrestamo = Date.valueOf(scanner.nextLine());
            System.out.println("Editando fecha devolucion");
            Date fechaDevolucion = Date.valueOf(scanner.nextLine());
            System.out.println("Editando estado");
            String estado = scanner.nextLine();
            System.out.println("Editando correo usuario");
            String correoUsuario = scanner.nextLine();
            System.out.println("Editando titulo publicacion");
            String tituloPublicacion = scanner.nextLine();

            prestamoAEditar.setFechaPrestamo(fechaPrestamo);
            prestamoAEditar.setFechaDevolucion(fechaDevolucion);
            prestamoAEditar.setEstado(estado);
            prestamoAEditar.setUsuarioCorreo(correoUsuario);
            prestamoAEditar.setPublicacionTitulo(tituloPublicacion);

            System.out.println("Prestamo editado exitosamente");
        } catch (NoSuchElementException e){
            System.out.println("Pestamo con id " + idPrestamo + " no existe");
        }


    }
}
