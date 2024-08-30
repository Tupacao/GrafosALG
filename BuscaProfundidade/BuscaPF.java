package BuscaProfundidade;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

class NodeDirecional {
    private int grau;
    private LinkedList<Integer> list;

    public NodeDirecional() {
        this.grau = 0;
        this.list = new LinkedList<Integer>();
    }

    public LinkedList<Integer> getList() {
        return this.list;
    }

    public int getGrau() {
        return grau;
    }

    public void add(int value) {
        this.list.add(value);
        grau++;
    }

    public void ordenaLista(){
        Collections.sort(list);
    }
}

class GrafoLista {
    private NodeDirecional sucessores[] = null;
    private int vetDados [][] = null;
    private int tam;

    public GrafoLista(int vertice) {

        this.sucessores = new NodeDirecional[vertice];
        this.tam = vertice;

        for (int i = 0; i < sucessores.length; i++) {
            sucessores[i] = new NodeDirecional();
        }

        preencherVetor();
    }

    public void criarGrafo(String arquivo) {

        String temp[] = null, aux = "";

        try {
            BufferedReader bf = new BufferedReader(new FileReader("./" + arquivo));

            bf.readLine();
            temp = bf.readLine().split(" ");
            while ((aux = bf.readLine()) != null) {
                temp = aux.trim().split("\\s+");
                sucessores[Integer.parseInt(temp[0]) - 1].add(Integer.parseInt(temp[1]));
            }

            bf.close();
            ordenarGrafo();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }

    }

    private void ordenarGrafo (){
        for (int i = 0; i < sucessores.length; i++) {
            sucessores[i].ordenaLista();
            System.out.println((i+1) + " - " + sucessores[i].getList());
        }
    }

    private void preencherVetor (){
        this.vetDados = new int[tam][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < tam; j++) {
                vetDados[i][j] = 0;
            }
        }

    }

    public void BuscaProfundidade(int verticeEntrada){
        int tempo = 0;
        BuscaPF(1, tempo);
    }

    public void BuscaPF (int vertice, int tempo){
        tempo++;
        vetDados[0][vertice - 1] = tempo;
        
    }
}


public class BuscaPF {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        GrafoLista gf = null;

        System.out.print("Nome do arquivo: ");
        String arquivo = sc.nextLine();

        System.out.print("Vértice que deseja procurar: ");
        int vertice = sc.nextInt();
        sc.close();
        
        try {
            BufferedReader bf = new BufferedReader(new FileReader("./" + arquivo));
            gf = new GrafoLista(Integer.parseInt(bf.readLine().split(" ")[0]));
            bf.close();
        } catch (Exception e) {
            System.out.println("Erro:" + e.getMessage());
            e.printStackTrace();
        }

        gf.criarGrafo(arquivo);
    }
}