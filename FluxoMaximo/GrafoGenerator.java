package FluxoMaximo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class GrafoGenerator {

    private int vertices;
    private int minArestas, maxArestas;
    private Grafo grafo;

    public GrafoGenerator(int vertices) {
        this.vertices = vertices;
        minArestas = vertices - 1;
        maxArestas = (vertices - 1)*(vertices);
        grafo = new Grafo(vertices);
        generateGraph();
    }

    public void exportGraphToCSV(String filename) {
        try (FileWriter writer = new FileWriter("./FluxoMaximo/" + filename)) {
            for (int i = 0; i < vertices; i++) {
                for (int v : grafo.cloneGrafo()[i]) {
                    writer.write(i + "," + v + "\n");
                }
            }
            System.out.println("Grafo exportado para " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<Integer>[] cloneGrafo (){
        return grafo.cloneGrafo();
    }

    private void generateGraph() {
        Random rand = new Random();
        int medArestas = rand.nextInt(maxArestas - minArestas) + minArestas;
        int arestaCount = 0, arestasGrafo = 0;

        System.out.println("Media de arestas: " + medArestas);

        while (arestaCount < vertices - 1) {
            grafo.setSuce(arestaCount, arestaCount+1);
            arestaCount++;
            arestasGrafo++;
        }

        while (arestaCount < medArestas) {
            int v = rand.nextInt(vertices);
            int w = rand.nextInt(vertices - 1) + 1;

            if(v != w && !grafo.isEdge(v, w) && !grafo.isEdge(w, v)){
                if(v != vertices - 1){
                    grafo.setSuce(v, w);
                    arestasGrafo++;
                }
            }
            
            arestaCount++;
        }
        
        System.out.println("Arestas de verdade: " + arestasGrafo + " - Arestas que era pra ter: " + medArestas);

        exportGraphToCSV("grafo.csv");
        grafo.printGraph();
    }
}