package co.edu.uniquindio.poo;

public class Tiempo {
    private int minutos;
    private int segundos;
    private int milisegundos;

    public Tiempo(int minutos, int segundos, int milisegundos) {
        this.minutos = minutos;
        this.segundos = segundos;
        this.milisegundos = milisegundos;
    }

    public String obtenerFormatoTiempo() {
        return minutos + " min " + segundos + " seg " + milisegundos + " ms";
    }

    public int getMinutos() {
        return minutos;
    }

    public int getSegundos() {
        return segundos;
    }

    public int getMilisegundos() {
        return milisegundos;
    }
}
