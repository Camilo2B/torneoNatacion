package co.edu.uniquindio.poo.protectonataciondto_record.Model;


public class Tiempo implements Comparable<Tiempo> {
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


    @Override
    public int compareTo(Tiempo otroTiempo) {
        if (this.minutos != otroTiempo.minutos) {
            return Integer.compare(this.minutos, otroTiempo.minutos);
        }
        if (this.segundos != otroTiempo.segundos) {
            return Integer.compare(this.segundos, otroTiempo.segundos);
        }
        return Integer.compare(this.milisegundos, otroTiempo.milisegundos);
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%03d", minutos, segundos, milisegundos);
    }


}
