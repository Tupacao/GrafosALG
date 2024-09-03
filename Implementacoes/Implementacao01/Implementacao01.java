package Implementacoes.Implementacao01;

// Implementação da lista de adjacência vista nos slides da disciplina.
// A escolha foi devido a maior facilidade de implementação e pela menor utilização de memória se comparado com os outros algoritmos estudados

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

public class Implementacao01 {

    static void predecessores (int vertice, LinkedList<Integer> ll[], LinkedList<Integer> predecessores){
        for (int i = 0; i < ll.length; i++) {
            if(ll[i].indexOf(vertice) != -1){
                predecessores.add(i+1);
            }
        }
    }

    static void preencherList (LinkedList<Integer> sucessores[]){
        for (int i = 0; i < sucessores.length; i++) {
            sucessores[i] = new LinkedList<Integer>();
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        System.out.print("Insira o nome do arquivo: ");
        String arq = sc.nextLine();
        
        System.out.print("Insira o vértice: ");
        int verticeEntrada = sc.nextInt();
        
        LinkedList<Integer> sucessores[] = null;

        sc.close();

        try {
            
            BufferedReader bf = new BufferedReader(new FileReader("./" + arq));

            String linha = bf.readLine();

            String temp[] = linha.split(" ");
            sucessores = new LinkedList[Integer.parseInt(temp[0])];
            preencherList(sucessores);

            while ((linha = bf.readLine()) != null) {
                temp = linha.trim().split("\\s+");
                sucessores[Integer.parseInt(temp[0]) - 1].add(Integer.parseInt(temp[1]));
            }

            LinkedList<Integer> predecessores = new LinkedList<>();
            predecessores(verticeEntrada, sucessores, predecessores);

            System.out.println(
                "\nVértice: " + verticeEntrada
                + "\nGrau saída: " + sucessores[verticeEntrada - 1].size()
                + "\nGrau entrada: " + predecessores.size()
                + "\nSucessores: " + sucessores[verticeEntrada - 1]
                + "\nPredecessores" + predecessores
            );

            bf.close();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            e.getStackTrace();
        }
    }
}