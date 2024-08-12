package Adjacencia;

import java.io.RandomAccessFile;

public class AdjacenciaD {

    static void preencherMatriz(int mat[][], int tam) {
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                mat[i][j] = 0;
            }
        }
    }

    static void printMatriz(int mat[][], int tam) {
        for (int i = 0; i < tam; i++) {
            System.out.print("Linha: " + (i + 1) + " - ");

            int auxS = 0;
            int auxP = 0;

            for (int j = 0; j < tam; j++) {
                System.out.print("[" + mat[i][j] + "] ");
                auxS += mat[i][j];
                auxP += mat[j][i];
            }

            System.out.print("Quant Sucessores: " + auxS + " - " + " Quant Predecessores: " + auxP);

            System.out.println("");
            System.out.println("");
        }
    }

    public static void main(String[] args) {

        int matriz[][] = null, tamM = 0;
        String arq = "", aux[] = null;

        try {

            RandomAccessFile raf = new RandomAccessFile("./entrada100.txt", "r");

            arq = raf.readLine();
            aux = arq.split(" ");
            tamM = Integer.parseInt(aux[0]);
            matriz = new int[tamM][tamM];

            preencherMatriz(matriz, tamM);

            while ((arq = raf.readLine()) != null) {
                aux = arq.split(" ");
                int v1 = Integer.parseInt(aux[0]) - 1;
                int v2 = Integer.parseInt(aux[1]) - 1;

                matriz[v1][v2] = 1;
            }

            printMatriz(matriz, tamM);

            raf.close();

        } catch (Exception e) {

        }
    }
}