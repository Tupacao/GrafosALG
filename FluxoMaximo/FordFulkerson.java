package FluxoMaximo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

public class FordFulkerson {

    LinkedList<Integer>[] residual;
    LinkedList<Integer>[] graph;
    Map<String, Aresta> edges;

    public FordFulkerson(LinkedList<Integer>[] graph, Map<String, Aresta> edges, int antibase) {
        this.graph = graph;
        this.edges = edges;
        System.out.println("Valor fluxo máximo: " + fluxoMaximo(antibase));
    }

    @SuppressWarnings("unchecked")
    private int fluxoMaximo(int antibase) {

        buildResidual();

        LinkedList<Integer> tree = new DFS(residual).getTree();

        int maxflox = 0;

        while (!tree.isEmpty() && tree.getLast() == antibase) {

            int pathFlow = Integer.MAX_VALUE;

            for (int i = 0; i < tree.size() - 1; i++) {
                int v = tree.get(i);
                int w = tree.get(i + 1);

                if (edges.get(v + "-" + w) != null) {
                    pathFlow = Math.min(pathFlow,
                            edges.get(v + "-" + w).getCapacity() - edges.get(v + "-" + w).getFluxo());
                } else {
                    pathFlow = Math.min(pathFlow, edges.get(w + "-" + v).getFluxo());
                }
            }

            for (int i = 0; i < tree.size() - 1; i++) {
                int v = tree.get(i);
                int w = tree.get(i + 1);

                if (edges.get(v + "-" + w) != null) {
                    edges.get(v + "-" + w).setFluxo(pathFlow);
                } else {
                    edges.get(w + "-" + v).setFluxo(pathFlow);
                }

            }

            maxflox += pathFlow;

            buildResidual();

            tree = new DFS(residual).getTree();
        }

        gerarCSVGrafoResidual("./FluxoMaximo/residual.csv");

        return maxflox;
    }

    @SuppressWarnings("unchecked")
    private void buildResidual() {
        residual = new LinkedList[graph.length];

        for (int i = 0; i < residual.length; i++) {
            residual[i] = new LinkedList<>();
        }

        for (int v = 0; v < graph.length; v++) {
            for (int w : graph[v]) {
                if (edges.get(v + "-" + w).getFluxo() == edges.get(v + "-" + w).getCapacity()) {
                    residual[w].add(v);
                } else if (edges.get(v + "-" + w).getFluxo() > 0) {
                    residual[v].add(w);
                    residual[w].add(v);
                } else if (edges.get(v + "-" + w).getFluxo() == 0) {
                    residual[v].add(w);
                }
            }
        }
    }

    private void gerarCSVGrafoResidual(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.append("Vértice_Origem,Vértice_Destino,Fluxo/Capacidade\n");
            for (int i = 0; i < residual.length; i++) {
                for (int w : residual[i]) {
                    // Se houver aresta no grafo residual
                    if (edges.get(i + "-" + w) != null) {
                        int fluxo = edges.get(i + "-" + w).getFluxo();
                        int capacidade = edges.get(i + "-" + w).getCapacity();
                        writer.append(i + "," + w + "," + fluxo + "/" + capacidade + "\n");
                    }
                }
            }
            System.out.println("Arquivo CSV gerado com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao gerar o arquivo CSV: " + e.getMessage());
        }
    }
}