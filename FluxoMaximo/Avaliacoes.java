package FluxoMaximo;

import java.io.FileWriter;
import java.io.IOException;

public class Avaliacoes {
    public static void main(String[] args) {
        int[] tamanhos = {10, 100, 1000, 2000};
        long limiteTempo = 300 * 1_000_000_000L;  // 20 segundos em nanossegundos
        long inicioPrograma = System.nanoTime();

        try (FileWriter writer = new FileWriter("resultados_tempo.csv")) {
            writer.append("Tamanho,Grafo,Menor Tempo(s),Maior Tempo(s),Tempo Médio(s)\n");

            for (int i = 0; i < tamanhos.length; i++) {
                if (System.nanoTime() - inicioPrograma > limiteTempo) {
                    System.out.println("Tempo limite de 20 segundos atingido. Finalizando...");
                    break;
                }

                new GrafoGenerator(tamanhos[i]);

                // Teste com GrafoGenerator
                long tempoTotalTeste1 = 0;
                long menorTempoTeste1 = Long.MAX_VALUE;
                long maiorTempoTeste1 = Long.MIN_VALUE;
                int execucoesTeste1 = 0;

                while (execucoesTeste1 < 10 && System.nanoTime() - inicioPrograma < limiteTempo) {
                    Grafo gfr = new Grafo("./FluxoMaximo/grafo.csv", tamanhos[i]);
                    long inicio = System.nanoTime();
                    new FordFulkerson(gfr.cloneGrafo(), gfr.cloneEdge(), tamanhos[i] - 1);
                    long fim = System.nanoTime();

                    long duracao = fim - inicio;
                    tempoTotalTeste1 += duracao;
                    menorTempoTeste1 = Math.min(menorTempoTeste1, duracao);
                    maiorTempoTeste1 = Math.max(maiorTempoTeste1, duracao);
                    execucoesTeste1++;
                }

                double mediaTempoTeste1 = execucoesTeste1 > 0 ? (double) tempoTotalTeste1 / execucoesTeste1 : 0;
                writer.append(tamanhos[i] + ",GrafoGenerator," + (menorTempoTeste1 / 1_000_000_000.0) + ","
                              + (maiorTempoTeste1 / 1_000_000_000.0) + "," + (mediaTempoTeste1 / 1_000_000_000.0) + "\n");

                CayleyGraph cal = new CayleyGraph(tamanhos[i]);
                // Teste com CayleyGraph
                long tempoTotalTeste2 = 0;
                long menorTempoTeste2 = Long.MAX_VALUE;
                long maiorTempoTeste2 = Long.MIN_VALUE;
                int execucoesTeste2 = 0;

                while (execucoesTeste2 < 10 && System.nanoTime() - inicioPrograma < limiteTempo) {
                    long inicio = System.nanoTime();
                    new FordFulkerson(cal.cloneGrafo(), cal.cloneEdge(), tamanhos[i] - 1);
                    long fim = System.nanoTime();

                    long duracao = fim - inicio;
                    tempoTotalTeste2 += duracao;
                    menorTempoTeste2 = Math.min(menorTempoTeste2, duracao);
                    maiorTempoTeste2 = Math.max(maiorTempoTeste2, duracao);
                    execucoesTeste2++;
                }

                double mediaTempoTeste2 = execucoesTeste2 > 0 ? (double) tempoTotalTeste2 / execucoesTeste2 : 0;
                writer.append(tamanhos[i] + ",CayleyGraph," + (menorTempoTeste2 / 1_000_000_000.0) + ","
                              + (maiorTempoTeste2 / 1_000_000_000.0) + "," + (mediaTempoTeste2 / 1_000_000_000.0) + "\n");
            }

            System.out.println("Resultados salvos no arquivo resultados_tempo.csv.");
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }
}