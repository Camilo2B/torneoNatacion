package co.edu.uniquindio.poo;

import java.util.LinkedList;

public record Resultado(Participante ganador, LinkedList<Participante> ranking) {

    public static LinkedList<Participante> calcularRanking(LinkedList<Participante> participantes) {
        participantes.sort((p1, p2) -> Integer.compare(
                p1.getTiempo().getMinutos() * 60000 + p1.getTiempo().getSegundos() * 1000 + p1.getTiempo().getMilisegundos(),
                p2.getTiempo().getMinutos() * 60000 + p2.getTiempo().getSegundos() * 1000 + p2.getTiempo().getMilisegundos()
        ));
        return participantes;
    }
}
