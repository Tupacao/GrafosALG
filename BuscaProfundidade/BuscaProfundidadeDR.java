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

    public LinkedList<Integer>[] getSucessores (){
        return this.sucessores;
    }

    private void preencherList() {
        for (int i = 0; i < sucessores.length; i++) {
            sucessores[i] = new LinkedList<Integer>();
        }
    }

    public LinkedList<Integer> getPredecessores(int vertice) {
        predecessores = new LinkedList<>();
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

    private void ordenarVertice() {
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

class BPD extends Grafo{
    private LinkedList<Integer> grafo[];
    private int vidaVertices[][] = null;

    public BPD(String arq, int vertice) {
        super(arq, vertice);
        
        this.grafo = getSucessores();

        vidaVertices = new int[3][getTam()];
        preencher(getTam());

        BuscaProfundidade();
    }

    private void preencher(int tam) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < tam; j++) {
                this.vidaVertices[i][j] = 0;
            }
        }
    }

    private void BuscaProfundidade() {
        int time = 0;
        for (int i = 0; i < grafo.length; i++) {
            if (vidaVertices[0][i] == 0) {
                time = BuscaProfundidade(i + 1, time);
            }
        }
    }

    private int BuscaProfundidade(int vertice, int time) {
        time++;
        vidaVertices[0][vertice - 1] = time;

        for (int w : grafo[vertice - 1]) {
            if (vidaVertices[0][w - 1] == 0) {
                vidaVertices[2][w - 1] = vertice;
                time = BuscaProfundidade(w, time);
            }
        }

        time++;
        vidaVertices[1][vertice - 1] = time;
        return time;
    }

    public void analisaArestas(int vertice) {
        LinkedList<Integer> predecessores = getPredecessores(vertice);

        int inicio = vidaVertices[0][vertice - 1];
        int fim = vidaVertices[1][vertice - 1];
        int pai = vidaVertices[2][vertice - 1];

        System.out.println("");

        for (int value : this.grafo[vertice - 1]) {
            if (inicio < vidaVertices[0][value - 1] && fim > vidaVertices[1][value - 1]
                    && vidaVertices[2][value - 1] == vertice) {
                System.out.println(vertice + " -> " + value + " (aresta de árvore)");
            } else if (inicio < vidaVertices[0][value - 1] && fim > vidaVertices[1][value - 1]
                    && vidaVertices[2][value - 1] != vertice) {
                System.out.println(vertice + " -> " + value + " (aresta de avanço)");
            } else if (inicio > vidaVertices[0][value - 1] && fim < vidaVertices[1][value - 1]) {
                System.out.println(vertice + " -> " + value + " (aresta de retorno)");
            } else if (inicio > vidaVertices[0][value - 1] && fim > vidaVertices[1][value - 1]) {
                System.out.println(vertice + " -> " + value + " (aresta de cruzamento)");
            }
        }

        for (int value : predecessores) {
            if (vidaVertices[0][value - 1] < inicio && vidaVertices[1][value - 1] > fim && value == pai) {
                System.out.println(value + " -> " + vertice + " (aresta de árvore)");
            } else if (vidaVertices[0][value - 1] < inicio && vidaVertices[1][value - 1] > fim && value != pai) {
                System.out.println(value + " -> " + vertice + " (aresta de avanço)");
            } else if (vidaVertices[0][value - 1] > inicio && vidaVertices[1][value - 1] < fim) {
                System.out.println(value + " -> " + vertice + " (aresta de retorno)");
            } else if (vidaVertices[0][value - 1] > inicio && vidaVertices[1][value - 1] > fim) {
                System.out.println(value + " -> " + vertice + " (aresta de cruzamento)");
            }
        }

    }

    public void printVida() {
        for (int i = 0; i < grafo.length; i++) {
            System.out.println(
                    "\nVértice: " + (i + 1)
                            + "\nInicio: " + vidaVertices[0][i]
                            + "\nFim: " + vidaVertices[1][i]
                            + "\nPai: " + vidaVertices[2][i]);
        }
    }

}

public class BuscaProfundidadeDR {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Insira o nome do arquivo: ");
        String arq = sc.nextLine();

        System.out.print("Insira o vértice: ");
        int vertice = sc.nextInt();

        sc.close();

        BPD bpd = new BPD(arq, vertice);

        bpd.analisaArestas(vertice);
    }
}