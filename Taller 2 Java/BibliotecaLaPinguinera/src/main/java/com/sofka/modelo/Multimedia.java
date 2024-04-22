package com.sofka.modelo;

import lombok.*;
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Multimedia {

        private String titulomulti;
        private String autor;
        private String tipo;
        private int minutos;
        private int cantEjemplares;
        private int cantPrestados;
        private int cantiDisponibles;

    public Multimedia(String titulomulti, String autor, int minutos, int cantEjemplares, int cantPrestados, int cantiDisponibles) {
        this.titulomulti = titulomulti;
        this.autor = autor;
        this.minutos = minutos;
        this.cantEjemplares = cantEjemplares;
        this.cantPrestados = cantPrestados;
        this.cantiDisponibles = cantiDisponibles;
    }



        public void prestar() {
            cantPrestados++;
            cantiDisponibles--;
        }

        public void devolver() {
            cantPrestados--;
            cantiDisponibles++;
        }

}
