package AdjacenciaVetores;

import java.io.RandomAccessFile;

public class ADV {
    public static void main(String[] args) {
        String str = "", aux[] = null;
        int pointer[] = null, data[] = null;

        try {

            RandomAccessFile raf = new RandomAccessFile("graph-test-50000-1.txt", "r");
            
            str = raf.readLine();
            aux = str.split("\\s+");
            
            pointer = new int[Integer.parseInt(aux[0]) + 1];
            data = new int[Integer.parseInt(aux[1])];
            
            int count = 0, temp = 1, position = 0;

            long start = System.nanoTime();

            while ((str = raf.readLine()) != null) {
                aux = str.trim().split("\\s+");
                if(temp == Integer.parseInt(aux[0])){
                    data[count] = Integer.parseInt(aux[1]);
                    count++;
                }else{
                    position++;
                    pointer[position] = count;
                    data[count] = Integer.parseInt(aux[1]);
                    count++;
                    temp = Integer.parseInt(aux[0]);
                }
            }

            pointer[++position] = count;

            long fim = System.nanoTime() - start;
            System.out.println(fim/1000000000);

            // for (int i = pointer[99]; i <pointer[100] ; i++) {
            //     System.out.println(data[i]);
            // }

            // for (int i = 0; i < pointer.length - 1; i++) {
            //     System.out.print((pointer[i + 1] - pointer[i]) + " ");
            // }

            // System.out.println(" ");
            
            // for (int i : pointer) {
            //     System.out.print(i + " - ");
            // }
            
            raf.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}