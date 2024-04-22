package com.sofkau.logica.empleado;

import com.sofkau.dialogo.MensajeOperacionBd;
import com.sofkau.integration.database.ConexionDatabase;
import com.sofkau.integration.database.mysql.MySqlOperation;
import com.sofkau.integration.repositorio.EmpleadoRepositorio;
import com.sofkau.model.Empleado;
import com.sofkau.util.CommonOperacion.GenerateUniqueId;
import com.sofkau.util.enums.Roles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;

public class EmpleadoOperaciones {

    private Empleado empleadoActual;

    private static Empleado empleadoPerfil;

    private static HashMap<String, Empleado> empleados = new HashMap<>();

    public void registrarEmpleado(Empleado empleado, String rol) {
        empleado.setId(GenerateUniqueId.generateID());
        empleado.setRol(rol);
        EmpleadoRepositorio.crearEmpleado(empleado);
        EmpleadoRepositorio.registroCreacion(empleadoActual.getId(),empleado.getId());
            MensajeOperacionBd.crearEmpleado();
            System.out.println(empleado);
        empleados.put(empleado.getId(),empleado);
    }

    public EmpleadoOperaciones() {
        getEmpleados();
    }

    //Se crea usuario administrador
    public void generarEmpleadoAdministrador(Empleado admin){
        Empleado empleadoVal = consultarEmpleado(admin.getId());
        if(empleadoVal != null ){
            return;
        }
        EmpleadoRepositorio.crearEmpleado(admin);
        empleados.put(admin.getId(),admin);
    }

    public void getEmpleados() {
        empleados = EmpleadoRepositorio.consultarEmpleados();
    }

    public boolean inicioSesion(String correo, String contrasena) {
        Optional<Empleado> empleadoVal;
        empleadoVal = empleados.values().stream()
                    .filter(empleado -> empleado.getCorreo().equals(correo) && empleado.getContrasena().equals(contrasena))
                    .findFirst();

        if(!empleadoVal.isEmpty()){
            this.empleadoActual = empleadoVal.get();
            return true;
        }

       return false;
    }

    public Empleado buscarEmpleadoPorCorreo(String correo) {
        Optional<Empleado> empleado = empleados.values().stream()
                .filter(e -> e.getCorreo().equals(correo))
                .findFirst();

        empleadoPerfil = empleado.get();
        return empleado.get();
    }

    private Empleado consultarEmpleado(String Id){
       return EmpleadoRepositorio.consultarEmpleadoPorId(Id);
    }

    public Empleado getEmpleadoActual(){
        return empleadoActual;
    }

    public void actualizarEmpleado(Empleado empleado) {
        // Verificamos si el empleado a actualizar existe en la base de datos
        Empleado empleadoExistente = buscarEmpleadoPorCorreo(empleado.getCorreo());
        if (empleadoExistente != null) {
            // Actualizamos el empleado en la base de datos
            EmpleadoRepositorio.actualizarEmpleado(empleado);
            // Actualizamos el empleado en la lista de empleados
            empleados.put(empleadoExistente.getId(), empleado);
            // Asignamos el empleado actualizado como el empleado perfil
            empleadoPerfil = empleado;
            System.out.println("Empleado actualizado correctamente: " + empleado);
        } else {
            System.out.println("El empleado con correo " + empleado.getCorreo() + " no existe en la base de datos.");
        }
    }

}
