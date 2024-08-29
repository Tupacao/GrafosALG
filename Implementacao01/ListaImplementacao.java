package Implementacao01;

// Implementação da lista de adjacência vista nos slides da disciplina.
// A escolha foi devido a maior facilidade de implementação e pela menor utilização de memória se comparado com os outros algoritmos estudados

import java.io.BufferedReader;
import java.io.FileReader;
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
        int verticeEntrada = sc.nextInt();

        try {
            
            BufferedReader bf = new BufferedReader(new FileReader("./" + arq));

            temp = bf.readLine().split(" ");
            int tam = Integer.parseInt(temp[0]);
            sucessores = new NodeD[tam];
            antecessores = new NodeD[tam];


            preencherVetor(sucessores);
            preencherVetor(antecessores);


            while ((aux = bf.readLine()) != null) {
                temp = aux.trim().split("\\s+");
                sucessores[Integer.parseInt(temp[0]) - 1].add(Integer.parseInt(temp[1]));
                antecessores[Integer.parseInt(temp[1]) - 1].add(Integer.parseInt(temp[0]));
            }

            System.out.println("\nVértice " + verticeEntrada
            + "\nGrau de saída: "
            + sucessores[verticeEntrada - 1].getGrau()
            + " - Sucessores " + sucessores[verticeEntrada - 1].getList()
            + "\nGrau de entrada: "
            + antecessores[verticeEntrada - 1].getGrau()
            + " - Antecessores " +  antecessores[verticeEntrada - 1].getList() + "\n");

            bf.close();
            sc.close();

        } catch (

        Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}