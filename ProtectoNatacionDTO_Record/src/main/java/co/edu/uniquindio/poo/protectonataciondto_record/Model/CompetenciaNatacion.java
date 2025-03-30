package co.edu.uniquindio.poo.protectonataciondto_record.Model;

/*
public class CompetenciaNatacion {
}

 */


import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedList;

public class CompetenciaNatacion {
    private String nombre;
    private LocalDate fecha;
    private LinkedList<Participante> participantes;
    private Resultado resultado;

    public CompetenciaNatacion(String nombre, LocalDate fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.participantes = new LinkedList<>();
    }

    public void agregarParticipante(Participante participante) {
        participantes.add(participante);
    }

    public void registrarResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    public LinkedList<Participante> obtenerRanking() {
        LinkedList<Participante> ranking = new LinkedList<>(participantes);
        ranking.sort(Comparator.comparingInt(p -> p.getTiempo().getMinutos() * 60000 +
                p.getTiempo().getSegundos() * 1000 +
                p.getTiempo().getMilisegundos()));
        return ranking;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LinkedList<Participante> getParticipantes() {
        return participantes;
    }

    public Resultado getResultado() {
        return resultado;
    }
}

