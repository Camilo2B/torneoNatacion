package co.edu.uniquindio.poo.protectonataciondto_record.Model;


public class ParticipanteDTO {
    private String nombre;
    private String apellido;
    private int edad;
    private Categoria categoria;


    public ParticipanteDTO(String nombre, String apellido, int edad, Categoria categoria) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.categoria = categoria;
    }

    public void registrarTiempo(Tiempo tiempo) {
        System.out.println("El tiempo registrado para " + nombre + " es: " + tiempo.obtenerFormatoTiempo());
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
