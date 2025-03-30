package co.edu.uniquindio.poo.protectonataciondto_record.Model;

import java.time.LocalDate;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        // Crear competencia
        CompetenciaNatacion competencia = new CompetenciaNatacion("Torneo Regional", LocalDate.of(2025, 5, 20));

        // Crear participantes
        Participante p1 = new Participante("Juan", "Perez", 15, Categoria.JUVENIL);
        Participante p2 = new Participante("Maria", "Lopez", 18, Categoria.JUVENIL);
        Participante p3 = new Participante("Carlos", "Gomez", 20, Categoria.ADULTO);

        // Registrar tiempos
        p1.registrarTiempo(new Tiempo(2, 35, 450));
        p2.registrarTiempo(new Tiempo(2, 30, 300));
        p3.registrarTiempo(new Tiempo(2, 40, 200));

        // Agregar participantes a la competencia
        competencia.agregarParticipante(p1);
        competencia.agregarParticipante(p2);
        competencia.agregarParticipante(p3);

        // Calcular ranking
        LinkedList<Participante> ranking = Resultado.calcularRanking(competencia.getParticipantes());

        // Registrar resultado
        Resultado resultado = new Resultado(ranking.getFirst(), ranking);
        competencia.registrarResultado(resultado);

        // Mostrar resultados
        System.out.println("Competencia: " + competencia.getNombre());
        System.out.println("Fecha: " + competencia.getFecha());

        System.out.println("Ganador: " + resultado.ganador().getNombre());

        System.out.println("\nRanking:");
        for (int i = 0; i < ranking.size(); i++) {
            System.out.println((i + 1) + ". " + ranking.get(i).getNombre() + " - " + ranking.get(i).getTiempo().obtenerFormatoTiempo());
        }
    }
}
