package Implementacao01;

// Implementação da lista de adjacência vista nos slides da disciplina.
// A escolha foi devido a maior facilidade de implementação e pela menor utilização de memória se comparado com os outros algoritmos estudados
// Optei por velocidade e portanto criei dois vetores, um com os sucessores e outro com os predecessores

import java.io.RandomAccessFile;
import java.util.LinkedList;
import java.util.Scanner;

class NodeD {
    private int grau;
    private LinkedList<Integer> list;

    public NodeD() {
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

    public boolean isAntecessor (int vertice){
        boolean resp = true;
        if(list.indexOf(vertice) == -1){
            resp = false;
        }

        return resp;
    }

}

public class ListaImplementacao {

    static void preencherVetor(NodeD vet[]) {
        for (int i = 0; i < vet.length; i++) {
            vet[i] = new NodeD();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        NodeD sucessores[] = null, antecessores[] = null;
        String temp[] = null, aux = "";

        System.out.print("Insira o nome do arquivo: ");
        String arq = sc.nextLine();
        System.out.print("Vértice a ser analisado: ");
        int usefull = sc.nextInt();

        try {

            RandomAccessFile raf = new RandomAccessFile("./" + arq, "r");

            temp = raf.readLine().split(" ");
            int tam = Integer.parseInt(temp[0]);
            sucessores = new NodeD[tam];
            antecessores = new NodeD[tam];

            preencherVetor(sucessores);
            preencherVetor(antecessores);

            long start = System.nanoTime();

            System.out.println("\nprocessando ...\n");

            while ((aux = raf.readLine()) != null) {
                temp = aux.trim().split("\\s+");
                sucessores[Integer.parseInt(temp[0]) - 1].add(Integer.parseInt(temp[1]));
                antecessores[Integer.parseInt(temp[1]) - 1].add(Integer.parseInt(temp[0]));
            }

            // LinkedList<Integer> numbers = new LinkedList<Integer>();

            // for (int i = 0; i < sucessores.length; i++) {
            //     if (sucessores[i].isAntecessor(usefull)) {
            //         numbers.add(i+1);
            //     }
            // }


            System.out.println("Vértice " + usefull + " - Sucessores: " + sucessores[usefull - 1].getGrau() + " "
                    + sucessores[usefull - 1].getList() + " - Antecessores: " + antecessores[usefull - 1].getGrau()
                    + " " +  antecessores[usefull - 1].getList()
                     + "\n");

                     long fim = System.nanoTime() - start;
                     System.out.println(fim/1000000000);

            raf.close();
            sc.close();


        } catch (

        Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}