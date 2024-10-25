package FluxoMaximo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Grafo {

    private LinkedList<Integer> grafo[];
    private Map<String, Aresta> edges;

    @SuppressWarnings("unchecked")
    public Grafo (int tam){
        grafo = new LinkedList[tam];
        edges = new HashMap<>();

        for (int i = 0; i < grafo.length; i++) {
            grafo[i] = new LinkedList<>();
        }
    }

    @SuppressWarnings("unchecked")
    public Grafo (String path, int tam){
        grafo = new LinkedList[tam];
        edges = new HashMap<>();

        for (int i = 0; i < grafo.length; i++) {
            grafo[i] = new LinkedList<>();
        }

        arqGrafo(path);
    }

    public LinkedList<Integer>[] cloneGrafo (){
        sortGrafo();
        return this.grafo;
    }

    public Map<String, Aresta> cloneEdge (){
        return edges;
    }

    public void setSuce (int v, int w, int capacity){
        grafo[v].add(w);
        addEdge(capacity, v+"-"+w);
    }

    public boolean isEdge (int v, int w){
        return grafo[v].contains(w);
    }

    private void arqGrafo (String path){

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = "";

            while ((line = br.readLine()) != null) {
                String temp[] = line.split(",");
                setSuce(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), 1);
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Erro arq: " + e.getMessage());
        }

    }

    public Aresta findEdge (String str){
        return edges.get(str);
    }

    public void printGraph(){
        for (int i = 0; i < grafo.length; i++) {
            System.out.println("Sucessores (" + i + ") " + grafo[i]);
        }
    }

    public void printEdge (){
        for (Map.Entry<String, Aresta> entry : edges.entrySet()) {
            System.out.println("Aresta " + entry.getKey() + ": " + entry.getValue().printAresta());
        }
    }

    private void sortGrafo (){
        for (int i = 0; i < grafo.length; i++) {
            Collections.sort(grafo[i]);
        }
    }

    private void addEdge (int capacity, String str){
        edges.put(str, new Aresta(capacity));
    }

    public int getArestaCount() {
        return edges.size();
    }
}