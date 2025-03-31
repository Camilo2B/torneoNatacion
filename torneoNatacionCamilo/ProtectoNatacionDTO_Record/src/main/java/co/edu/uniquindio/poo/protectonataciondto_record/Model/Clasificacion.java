package co.edu.uniquindio.poo.protectonataciondto_record.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Clasificacion {

    public static List<Participante> clasificarParticipantes(List<Participante> participantes) {
        // Crear una copia de la lista para no modificar la original
        List<Participante> participantesOrdenados = new ArrayList<>(participantes);

        // Ordenar la lista por tiempo utilizando Collections.sort()
        Collections.sort(participantesOrdenados, (p1, p2) -> p1.getTiempo().compareTo(p2.getTiempo()));

        return participantesOrdenados;
    }

    public static Participante determinarGanador(List<Participante> participantes) {
        if (participantes == null || participantes.isEmpty()) {
            return null; // No hay participantes, no hay ganador
        }

        Participante ganador = participantes.get(0); // Suponemos que el primero es el ganador inicial
        for (Participante participante : participantes) {
            if (participante.getTiempo().compareTo(ganador.getTiempo()) < 0) {
                ganador = participante; // Encontramos un participante con un tiempo menor
            }
        }
        return ganador;
    }

}