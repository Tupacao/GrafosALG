package Incidencia;

import java.io.BufferedReader;
import java.io.FileReader;

public class IncidenciaD {

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

            int auxS = 0;
            int auxP = 0;

            for (int j = 0; j < coluna; j++) {
                System.out.print("[" + mat[i][j] + "] ");
                if (mat[i][j] == 1) {
                    auxP++;
                } else if (mat[i][j] == -1) {
                    auxS++;
                }
            }

            System.out.print("Quant Sucessores: " + auxS + " - " + " Quant Predecessores: " + auxP);

            System.out.println("");
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        
        int matriz[][] = null;
        int linha = 0, coluna = 0;

        try {
            
            BufferedReader buf = new BufferedReader(new FileReader("./entrada100.txt"));

            String line = buf.readLine();

            String aux[] = line.split(" ");

            linha = Integer.parseInt(aux[0]);
            coluna = Integer.parseInt(aux[1]);

            matriz = new int[linha][coluna];

            preencherMatriz(matriz, linha, coluna);
            int count = 0;
            while ((line = buf.readLine()) != null) {
                aux = line.split(" ");
                int v1 = Integer.parseInt(aux[0]) - 1;
                int v2 = Integer.parseInt(aux[1]) - 1;
                matriz[v1][count] = -1;
                matriz[v2][count] = 1;
                count++;
            }
            
            buf.close();

        } catch (Exception e) {

            System.out.println("Erro: " + e.getMessage());
            
        }

        printMatriz(matriz, linha, coluna);
    }
}