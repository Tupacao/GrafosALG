package Implementacoes.Implementação04;

import java.io.*;

public class App {    
    public static void main(String[] args) {

        Model md = null;
        int m, n;

        try {
            BufferedReader bf = new BufferedReader(new FileReader("./input.txt"));        
            String aux = bf.readLine();

            String temp[] = aux.trim().split(",");
            m = Integer.parseInt(temp[0]);
            n = Integer.parseInt(temp[1]);

            md = new Model(m, n);
            
            int j = 0;

            while ((aux = bf.readLine()) != null) {

                temp = aux.trim().split(",");
                
                if (m > j) {
                    md.addDemand(Integer.parseInt(temp[0]));
                } else if (m+n > j){
                    md.addOffer(Integer.parseInt(temp[0]));
                } else {
                    md.setMat(temp);
                }

                j++;
            }

            bf.close();

            md.printMat();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.getStackTrace();
        }

    }
}
