package ListaAdjacencia;

import java.io.RandomAccessFile;
import java.util.LinkedList;

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

public class ListaD {

    static void preencherVetor(NodeD vet[]) {
        for (int i = 0; i < vet.length; i++) {
            vet[i] = new NodeD();
        }
    }

    public static void main(String[] args) {
        NodeD sucessores[] = null, antecessores[] = null;
        String temp[] = null, aux = "";

        try {

            RandomAccessFile raf = new RandomAccessFile("./graph-test-50000-1.txt", "r");

            temp = raf.readLine().split(" ");
            int tam = Integer.parseInt(temp[0]);
            sucessores = new NodeD[tam];
            antecessores = new NodeD[tam];

            preencherVetor(sucessores);
            preencherVetor(antecessores);

            while ((aux = raf.readLine()) != null) {
                temp = aux.trim().split("\\s+");
                sucessores[Integer.parseInt(temp[0]) - 1].add(Integer.parseInt(temp[1]));
                antecessores[Integer.parseInt(temp[1]) - 1].add(Integer.parseInt(temp[0]));
            }

            // for (int i = 0; i < sucessores.length; i++) {
            //     for (int j = 0; j < sucessores.length; j++) {
            //         if (sucessores[i].search(j + 1)) {
            //             sucessores[j].increment();
            //         }
            //     }
            // }

            for (int i = 0; i < tam; i++) {
                System.out.println("Vértice " + (i+1) + " - Sucessores: " + sucessores[i].getGrau() + " - Antecessores: " + antecessores[i].getGrau());
            }

            raf.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}