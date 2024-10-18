package FluxoMaximo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.Buffer;
import java.util.Collections;
import java.util.LinkedList;

public class Grafo {

    private LinkedList<Integer> grafo[];

    @SuppressWarnings("unchecked")
    public Grafo (int tam){
        grafo = new LinkedList[tam];

        for (int i = 0; i < grafo.length; i++) {
            grafo[i] = new LinkedList<>();
        }
    }

    @SuppressWarnings("unchecked")
    public Grafo (String path, int tam){
        grafo = new LinkedList[tam];

        for (int i = 0; i < grafo.length; i++) {
            grafo[i] = new LinkedList<>();
        }

        arqGrafo(path);
    }

    public LinkedList<Integer>[] cloneGrafo (){
        sortGrafo();
        return this.grafo;
    }

    public void setSuce (int v, int w){
        grafo[v].add(w);
    }

    public boolean isEdge (int v, int w){
        return grafo[v].contains(w);
    }

    public void printGraph(){
        for (int i = 0; i < grafo.length; i++) {
            System.out.println("Sucessores (" + i + ") " + grafo[i]);
        }
    }

    public void arqGrafo (String path){
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = "";

            while ((line = br.readLine()) != null) {
                String temp[] = line.split(",");
                setSuce(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
            }

            br.close();
            printGraph();

        } catch (Exception e) {
            System.out.println("Erro arq: " + e.getMessage());
        }

    }

    private void sortGrafo (){
        for (int i = 0; i < grafo.length; i++) {
            Collections.sort(grafo[i]);
        }
    }

}