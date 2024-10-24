package Incidencia;

import java.io.RandomAccessFile;

public class IncidenciaND {

    static void preencherMatriz(int mat[][], int linha, int coluna) {
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                mat[i][j] = 0;
            }
        }
    }

    static void printMatriz(int mat[][], int linha, int coluna) {
        for (int i = 0; i < linha; i++) {
            System.out.print("Linha: " + (i + 1) + " - ");

            int auxG = 0;

            for (int j = 0; j < coluna; j++) {
                System.out.print("[" + mat[i][j] + "] ");
                auxG+= mat[i][j];
            }

            System.out.print("Grau: " + auxG + " ");

            System.out.println("");
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        
        int matriz[][] = null;
        int linha = 0, coluna = 0;

        try {
            RandomAccessFile raf = new RandomAccessFile("./entrada100.txt", "r");
            String line = raf.readLine();

            String aux[] = line.split(" ");

            linha = Integer.parseInt(aux[0]);
            coluna = Integer.parseInt(aux[1]);

            matriz = new int[linha][coluna];

            preencherMatriz(matriz, linha, coluna);
            int count = 0;
            while ((line = raf.readLine()) != null) {
                aux = line.split(" ");
                int v1 = Integer.parseInt(aux[0]) - 1;
                int v2 = Integer.parseInt(aux[1]) - 1;
                matriz[v1][count] = 1;
                matriz[v2][count] = 1;
                count++;
            }
            
            raf.close();

        } catch (Exception e) {

            System.out.println("Erro: " + e.getMessage());
            
        }

        printMatriz(matriz, linha, coluna);
    }
}