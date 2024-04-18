package Garcia.Juan.logica;

import Garcia.Juan.model.Producto;
import Garcia.Juan.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrestamosPrueba {

    // Lista para almacenar los préstamos de prueba
    private static List<PrestamoPrueba> prestamosPrueba = new ArrayList<>();

    // Clase anidada para representar un préstamo de prueba
    private static class PrestamoPrueba {
        private Usuario usuario;
        private Producto producto;

        public PrestamoPrueba(Usuario usuario, Producto producto) {
            this.usuario = usuario;
            this.producto = producto;
        }

        @Override
        public String toString() {
            return "PrestamoPrueba{usuario=" + usuario.getNombre() + ", producto=" + producto.toStringPrestamo() + "}";
        }
    }

    // Método para realizar un préstamo de prueba
    public static void realizarPrestamoPrueba() {
        Scanner scanner = new Scanner(System.in);

        // Solicita información del usuario
        System.out.println("Ingrese el nombre del usuario que realizará el préstamo de prueba:");
        String nombreUsuario = scanner.nextLine();
        System.out.println("Ingrese el correo electrónico del usuario:");
        String correoUsuario = scanner.nextLine();
        System.out.println("Ingrese la contraseña del usuario:");
        String contrasena = scanner.nextLine();

        // Crear un objeto Usuario con la información proporcionada
        Usuario usuario = new Usuario(correoUsuario, nombreUsuario, contrasena);

        // Solicita información del producto a prestar
        System.out.println("Ingrese el título del producto a prestar:");
        String tituloProducto = scanner.nextLine();
        System.out.println("Ingrese el tipo de producto (por ejemplo, Libro, Revista, etc.):");
        String tipoProducto = scanner.nextLine();
        System.out.println("Ingrese el autor del producto:");
        String autorProducto = scanner.nextLine();

        // Crear un objeto Producto con la información proporcionada
        Producto producto = new Producto();
        producto.setTitulo(tituloProducto);
        producto.setTipo(tipoProducto);
        producto.setAutor(autorProducto);
        producto.setCantidadDisponibles(1); // Asumimos que hay al menos un ejemplar disponible

        // Crear un nuevo préstamo de prueba
        PrestamoPrueba prestamoPrueba = new PrestamoPrueba(usuario, producto);

        // Agregar el préstamo de prueba a la lista
        prestamosPrueba.add(prestamoPrueba);
        System.out.println("Préstamo de prueba realizado exitosamente.");
    }

    // Método para consultar los préstamos de prueba
    public static void consultarPrestamosPrueba() {
        System.out.println("Lista de préstamos de prueba:");
        for (PrestamoPrueba prestamo : prestamosPrueba) {
            System.out.println(prestamo);
        }
    }

    // Método para eliminar todos los préstamos de prueba
    public static void eliminarPrestamoPrueba() {
        // Verifica si la lista de préstamos de prueba no está vacía
        if (!prestamosPrueba.isEmpty()) {
            // Vacía la lista de préstamos de prueba
            prestamosPrueba.clear();
            System.out.println("Todos los préstamos de prueba han sido eliminados exitosamente.");
        } else {
            // Si la lista ya está vacía, muestra un mensaje
            System.out.println("No hay préstamos de prueba para eliminar.");
        }
    }

    // Método para manejar el menú de los préstamos de prueba
    public static void menuPrestamosPrueba() {
        Scanner scanner = new Scanner(System.in);
        boolean ciclo = true;

        while (ciclo) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Realizar préstamo de prueba");
            System.out.println("2. Consultar préstamos de prueba");
            System.out.println("3. Eliminar préstamo de prueba");
            System.out.println("4. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            switch (opcion) {
                case 1:
                    realizarPrestamoPrueba();
                    break;
                case 2:
                    consultarPrestamosPrueba();
                    break;
                case 3:
                    eliminarPrestamoPrueba();
                    break;
                case 4:
                    ciclo = false;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }
        }
    }
}
