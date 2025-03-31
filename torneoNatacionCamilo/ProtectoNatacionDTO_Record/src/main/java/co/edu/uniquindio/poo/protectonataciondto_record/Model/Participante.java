package co.edu.uniquindio.poo.protectonataciondto_record.Model;

import co.edu.uniquindio.poo.protectonataciondto_record.Model.Categoria;

public class Participante {
    private String nombre;
    private String apellido;
    private int edad;
    private Categoria categoria;
    private Tiempo tiempo;

    public Participante(String nombre, String apellido, int edad, Categoria categoria) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.categoria = categoria;
    }

    public void registrarTiempo(Tiempo tiempo) {
        this.tiempo = tiempo;
    }

    public Tiempo getTiempo() {
        return tiempo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public Categoria getCategoria() {
        return categoria;
    }
}
