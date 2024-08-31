package BuscaProfundidade;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

class Grafo {

    private LinkedList<Integer> sucessores[] = null;
    private LinkedList<Integer> predecessores = null;
    private int tam;

    public Grafo(String arq, int vertice) {
        criarGrafo(arq, vertice);
    }

    private void preencherList() {
        for (int i = 0; i < sucessores.length; i++) {
            sucessores[i] = new LinkedList<Integer>();
        }
    }

    public LinkedList<Integer> getPredecessores(int vertice) {
        for (int i = 0; i < this.sucessores.length; i++) {
            if (this.sucessores[i].indexOf(vertice) != -1) {
                this.predecessores.add(i + 1);
            }
        }

        return this.predecessores;
    }

    @SuppressWarnings("unchecked")
    private void criarGrafo(String arq, int vertice) {
        try {

            BufferedReader bf = new BufferedReader(new FileReader("./" + arq));

            String linha = bf.readLine();

            String temp[] = linha.split(" ");
            this.tam = Integer.parseInt(temp[0]);

            this.sucessores = new LinkedList[this.tam];
            preencherList();

            while ((linha = bf.readLine()) != null) {
                temp = linha.trim().split("\\s+");
                this.sucessores[Integer.parseInt(temp[0]) - 1].add(Integer.parseInt(temp[1]));
            }
            
            bf.close();

            ordenarVertice();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            e.getStackTrace();
        }
    }

    private void ordenarVertice (){
        for (int i = 0; i < sucessores.length; i++) {
            Collections.sort(sucessores[i]);
        }
    }

    public LinkedList<Integer>[] cloneGrafo() {
        return this.sucessores.clone();
    }

    public int getTam() {
        return this.tam;
    }
}

class BPD {
    private Grafo aux = null;
    private LinkedList<Integer> grafo[];
    private int vidaVertices[][] = null;


    private void preencher(int tam) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < tam; j++) {
                this.vidaVertices[i][j] = 0;
            }
        }
    }

    private void BuscaProfundidade (){
        int time = 0;
        for (int i = 0; i < grafo.length; i++) {
            if(vidaVertices[0][i] == 0){
                time = BuscaProfundidade(i + 1, time);
            }
        }
    }

    private int  BuscaProfundidade(int vertice, int time) {
        time++;
        vidaVertices[0][vertice - 1] = time;

        for (int i = 0; i < grafo[vertice-1].size(); i++) {
            int w = grafo[vertice-1].get(i);
            if(vidaVertices[0][w - 1] == 0){
                vidaVertices[2][w - 1] = vertice;
                time =  BuscaProfundidade(w, time);
            }
        }
        time++;
        vidaVertices[1][vertice - 1] = time;
        return time;
    }

    public void analisaArestas (int vertice){
        LinkedList<Integer> predecessores = aux.getPredecessores(vertice);
        
        int inicio = vidaVertices[0][vertice-1];
        int fim = vidaVertices[1][vertice-1];
        int pai = vidaVertices[2][vertice-1];
        
        for (int value : this.grafo[vertice-1]) {
            if(inicio < vidaVertices[0][vertice-1]){
                
            }
        }

    }

    public BPD(String arq, int vertice) {

        aux = new Grafo(arq, vertice);
        this.grafo = aux.cloneGrafo();

        vidaVertices = new int[3][aux.getTam()];
        preencher(aux.getTam());

        BuscaProfundidade();
    }

    public void printVida (){
        for (int i = 0; i < grafo.length; i++) {
            System.out.println(
                "\nVértice: " + (i+1)
                +"\nInicio: " + vidaVertices[0][i]
                +"\nFim: " + vidaVertices[1][i]
                +"\nPai: " + vidaVertices[2][i]
            );
        }
    }

}


public class InnerBPD {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Insira o nome do arquivo: ");
        String arq = sc.nextLine();

        System.out.print("Insira o vértice: ");
        int vertice = sc.nextInt();

        sc.close();

        BPD bpd = new BPD(arq, vertice);

        bpd.printVida();
    }
}