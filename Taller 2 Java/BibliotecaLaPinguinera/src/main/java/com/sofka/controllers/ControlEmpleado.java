package com.sofka.controllers;

import com.sofka.enums.Rol;
import com.sofka.modelo.Empleado;
import net.datafaker.Faker;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static com.sofka.BibliotecaLaPinguinera.mySqlOperation;
import static com.sofka.constants.InsertConstants.INSERT_EMPLEADO;
import static com.sofka.constants.SelectConstants.SELECT_ALL_FROM_EMPLEADO;
import static com.sofka.constants.SelectConstants.SELECT_ALL_FROM_USUARIO;
import static com.sofka.constants.UpdateConstants.UPDATE_EMPLEADO;
import static com.sofka.controllers.ControlMenu.mostrarMensaje;
import static com.sofka.controllers.ControlStament.ejecutarMostrarSQL;
import static com.sofka.controllers.ControlStament.insertIntoBd;

public class ControlEmpleado {

    private static final List<Empleado> empleados = new ArrayList<>();

    public static void logicaCrearEmpleado(Empleado empleado) {
        registrarEmpleado(empleado);
        empleados.add(empleado);
        mostrarMensaje("¡Empleado registrado exitosamente: " + empleado.getNombre() + "!");
    }

    public static void registrarNuevoEmpleado(String rol) {
        String idEmpleado = JOptionPane.showInputDialog(null, "Ingrese la cedula del empleado: ");
        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre de empleado: ");
        String correo = JOptionPane.showInputDialog(null, "Ingrese una nueva direccion de correo: ");
        String contrasena = JOptionPane.showInputDialog(null, "Ingrese una nueva contraseña: ");
        Empleado worker = new Empleado(idEmpleado, nombre, contrasena, correo, rol);
        logicaCrearEmpleado(worker);
    }

    public static void registrarEmpleado(Empleado empleado) {
        String cadena = String.format(INSERT_EMPLEADO, empleado.getIdEmpleado(), empleado.getNombre(), empleado.getContrasena(), empleado.getCorreo(), empleado.getRol());
        insertIntoBd(cadena);
    }

    public static void crearEmpleado() {
        Faker faker = new Faker(new Locale("es"));
        String idEmpleado = faker.passport().valid();
        String nombre = faker.name().name().replace("'", "");
        String correo = faker.internet().emailAddress();
        String contrasena = faker.passport().valid();
        String rol = String.valueOf(Rol.values()[new Random().nextInt(Rol.values().length)]);
        Empleado worker = new Empleado(idEmpleado, nombre, contrasena, correo, rol);
        logicaCrearEmpleado(worker);
    }

    public static void insertarEmpleadoFaker(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            crearEmpleado();
        }
    }

    public static void selectAllFromEmpleado() throws SQLException {
        System.out.println("Lista de Empleados Registrados");
        ejecutarMostrarSQL(SELECT_ALL_FROM_EMPLEADO);
    }
    public static void updateEmpleadoById(String idPEmpleado) {
        mySqlOperation.setSqlStatement(UPDATE_EMPLEADO);
        mySqlOperation.executeSqlStatementVoid();
    }

    public static void actualizarEmpleado (){
        JOptionPane.showInputDialog(null, "Modifica, contrasenha, nombre y correo ");
        String idPrestamo = JOptionPane.showInputDialog(null, "Ingrese el id de empleado: ");
        updateEmpleadoById(idPrestamo);

    }

    public static void modificarEmpleado() throws SQLException {
        int idEmpleado = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID del empleado: "));
        String nuevoNombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre del empleado: ");
        String nuevaContrasenha = JOptionPane.showInputDialog(null, "Ingrese la nueva contraseña del empleado: ");

        mySqlOperation.setSqlStatement(SELECT_ALL_FROM_USUARIO);
        mySqlOperation.executeSqlStatement();
        ResultSet empleado = mySqlOperation.getResulset();

        boolean encontrado = false;

        while (empleado.next()) {
            int id = empleado.getInt("idEmpleado");
            if (id == idEmpleado) {
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            String updateStatement = String.format(UPDATE_EMPLEADO,nuevoNombre, nuevaContrasenha, idEmpleado);
            mySqlOperation.setSqlStatement(updateStatement);
            mySqlOperation.executeSqlStatementVoid();
            System.out.println("Empleado actualizado exitosamente.");
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }



}
