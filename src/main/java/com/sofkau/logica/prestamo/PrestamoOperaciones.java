package com.sofkau.logica.prestamo;

import com.sofkau.dialogo.MensajeOperacionBd;
import com.sofkau.integration.repositorio.EmpleadoRepositorio;
import com.sofkau.integration.repositorio.PrestamoRepositorio;
import com.sofkau.logica.cancion.CancionOperaciones;
import com.sofkau.logica.publicacion.PublicacionOperaciones;
import com.sofkau.logica.tesis.TesisOperaciones;
import com.sofkau.logica.videograbacion.VideoGrabacionOperaciones;
import com.sofkau.model.Empleado;
import com.sofkau.model.Prestamo;
import com.sofkau.model.Publicacion;
import com.sofkau.model.VideoGrabacion;
import com.sofkau.util.CommonOperacion.GenerateUniqueId;
import com.sofkau.util.enums.EstadoPrestamo;
import com.sofkau.util.enums.Material;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class PrestamoOperaciones {

    private static HashMap<String, Prestamo> prestamos = new HashMap<>();

    private PublicacionOperaciones publicacionOp;

    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    public PrestamoOperaciones() {
        this.publicacionOp = new PublicacionOperaciones();
        getPrestamos();
    }

    //Registra un nuevo prestamo
    public void RegistrarPrestamo (String titulo, String dateDevolucion, String correoUsuario){
        Prestamo prestamo = new Prestamo();
        prestamo.setId(GenerateUniqueId.generateID());
        prestamo.setCorreoUsuario(correoUsuario);
        prestamo.setTituloPublicacion(titulo);
        Date fechaPrestamo = new Date();
        Date fechaDevolucion = null;

        fechaDevolucion = obtenerFechaDevolucionValida(dateDevolucion,fechaPrestamo);

        if(fechaDevolucion == null){
            System.out.println("Por favor ingrese una fecha de devolucion valida");
            return;
        }

        prestamo.setFechaPrestamo(fechaPrestamo);
        prestamo.setFechaDevolucion(fechaDevolucion);
        prestamo.setEstadoPrestamo(EstadoPrestamo.SOLICITADO.toString());

        PrestamoRepositorio.crearPrestamo(prestamo);

        MensajeOperacionBd.crearPrestamo();

        // Se actualiza el prestamo en la lista
        prestamos.put(prestamo.getId(), prestamo);

        //Se suma 1 la cantidad de ejemplares prestados
        publicacionOp.actualizarCantidadPrestadaPublicacion(prestamo.getTituloPublicacion(),1,false);
    }

    public static String registrarPrestamoOtroMaterial (String dateDevolucion, String correoUsuario){
        Prestamo prestamo = new Prestamo();
        prestamo.setId(GenerateUniqueId.generateID());
        prestamo.setCorreoUsuario(correoUsuario);
        Date fechaPrestamo = new Date();
        Date fechaDevolucion = null;

        fechaDevolucion = obtenerFechaDevolucionValida(dateDevolucion,fechaPrestamo);

        if(fechaDevolucion == null){
            System.out.println("Por favor ingrese una fecha de devolucion valida");
            return null;
        }

        prestamo.setFechaPrestamo(fechaPrestamo);
        prestamo.setFechaDevolucion(fechaDevolucion);
        prestamo.setEstadoPrestamo(EstadoPrestamo.SOLICITADO.toString());

        PrestamoRepositorio.crearPrestamoOtroMaterial(prestamo);

        // Se actualiza el prestamo en la lista
        prestamos.put(prestamo.getId(), prestamo);

        MensajeOperacionBd.crearPrestamo();

        //Se retorna el id del prestamo
        return prestamo.getId();

    }

    // Actualiza el estado de un prestamo
    public void actualizarEstadoPrestamo(EstadoPrestamo estado, String idPrestamo, Material material){
       Prestamo prestamo = getPrestamo(idPrestamo);
       Date fechaActual = new Date();
        if(!prestamos.isEmpty()){
            prestamo.setEstadoPrestamo(estado.toString());
            PrestamoRepositorio.actualizarPrestamo(prestamo);
            if(estado == EstadoPrestamo.FINALIZADO){
                // Se devuelve publicacion del stock
                devolverMaterialStock(idPrestamo,material);
            }
            if(estado == EstadoPrestamo.FINALIZADO && fechaActual.after(prestamo.getFechaDevolucion()) ){
                System.out.println("El usuario no cumplio con la fecha de devolución");
            }
            //Actualiza el prestamo en la lista
            prestamos.put(idPrestamo,prestamo);
            MensajeOperacionBd.prestamoActualizado();
        }else{
            MensajeOperacionBd.errorActualizacionPrestamo();
        }
    }

    private void devolverMaterialStock(String idPrestamo, Material material){

        switch (material){
            case PUBLICACION -> {
                publicacionOp.actualizarCantidadPrestadaPublicacion(prestamos.get(idPrestamo).getTituloPublicacion(),
                        1,true);
            }case CANCION -> {
                String  tituloCancion =  PrestamoCancionOperaciones.getPrestamoCancion(idPrestamo);
                CancionOperaciones.actualizarStock(true,tituloCancion);
            }case VIDEOGRABACION -> {
                String tituloVideo = PrestamoVideoGrabacionOperaciones.getPrestamoVideograbacion(idPrestamo);
                VideoGrabacionOperaciones.actualizarStockVideograbacion(true,tituloVideo);
            }case TESIS -> {
                String tituloTesis = PrestamoTesisOperaciones.getPrestamoTesis(idPrestamo);
                TesisOperaciones.actualizarStockTesis(true,tituloTesis);
            }
        }
    }

    //Consulta todos los prestamos en base de datos
    public void getPrestamos() {
        prestamos = PrestamoRepositorio.consultarPrestamos();
    }

    // Obtiene un prestamo
    public Prestamo getPrestamo(String idPrestamo){
       return prestamos.get(idPrestamo);
    }

    public void listarPrestamosPorCorreo(String correoUsuario) {
        // Iterar sobre todos los préstamos
        for (Prestamo prestamo : prestamos.values()) {
            // Verificar si el préstamo pertenece al usuario con el correo especificado
            if (prestamo.getCorreoUsuario().equals(correoUsuario)) {
                String  tituloCancion =  PrestamoCancionOperaciones.getPrestamoCancion(prestamo.getId());
                String tituloVideo = PrestamoVideoGrabacionOperaciones.getPrestamoVideograbacion(prestamo.getId());
                String tituloTesis = PrestamoTesisOperaciones.getPrestamoTesis(prestamo.getId());

                if(tituloCancion != null){
                    System.out.println(prestamo+" Título canción: "+ tituloCancion);
                    System.out.println(CancionOperaciones.getCancionPorTitulo(tituloCancion));
                }else if(tituloVideo != null){
                    System.out.println(prestamo+" Título video grabación: "+ tituloVideo);
                    System.out.println(VideoGrabacionOperaciones.getVideograbacionPorTitulo(tituloVideo));
                }else if(tituloTesis != null){
                    System.out.println(prestamo+" Título tesis: "+ tituloTesis);
                    System.out.println(TesisOperaciones.getTesisPorTitulo(tituloTesis));
                }else{
                    System.out.println(prestamo+" Título: "+ prestamo.getTituloPublicacion());
                    System.out.println( publicacionOp.buscarPublicacionTitulo(prestamo.getTituloPublicacion()));
                }
            }
        }
    }

    public static Date obtenerFechaDevolucionValida(String dateDevolucion, Date fechaPrestamo) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDevolucion = null;

        try {
            fechaDevolucion = formato.parse(dateDevolucion);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // Calcula la diferencia en milisegundos y lo convierte a días
        long diferenciaEnDias = (fechaDevolucion.getTime() - fechaPrestamo.getTime()) / (1000 * 60 * 60 * 24);

        // Valida que la fecha de devolución no sea menor que la fecha actual y sea máximo 15 días después de la fecha de préstamo
        if (fechaDevolucion.after(fechaPrestamo) && diferenciaEnDias <= 15) {
            return fechaDevolucion;
        } else {
            return null;
        }
    }


}
