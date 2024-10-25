package Implementacoes.Implementação03;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

public class CayleyGraph {
    private int tam; // ordem do grupo
    private Grafo grafo;

    public CayleyGraph(int tam) {
        this.tam = tam;
        
        grafo = new Grafo(tam);

        generateGraph();
    }

    private void generateGraph() {

        int generator = 11; // pode ser qualquer número coprimo com n
        
        for (int i = 0; i < grafo.cloneGrafo().length; i++) {
            int w = (i + generator) % tam;
            grafo.setSuce(i, w, 1);
            grafo.setSuce(w, 1, 1);
        }

        // exportGraphToCSV("grafo.csv");
    }

    public LinkedList<Integer>[] cloneGrafo (){
        return grafo.cloneGrafo();
    }

    public Map<String, Aresta> cloneEdge (){
        return grafo.cloneEdge();
    }

    public void exportGraphToCSV(String filename) {
        try (FileWriter writer = new FileWriter("./FluxoMaximo/" + filename)) {
            for (int i = 0; i < tam; i++) {
                for (int v : grafo.cloneGrafo()[i]) {
                    writer.write(i + "," + v + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}