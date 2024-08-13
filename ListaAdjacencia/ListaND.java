package ListaAdjacencia;

import java.io.RandomAccessFile;
import java.util.LinkedList;
class NodeND {
    private int grau;
    private LinkedList<Integer> list;

    public NodeND (){
        this.grau = 0;
        this.list = new LinkedList<Integer>();
    }

    public void printValues (){
        System.out.println(this.list + " - Grau: " + this.grau);
    }

    public void add (int value){
        if(this.list.indexOf(value) == -1){
            this.list.add(value);
            grau++;
        }
    }
}
public class ListaND {

    static void preencherVetor(NodeND vet[]) {
        for (int i = 0; i < vet.length; i++) {
            vet[i] = new NodeND();
        }
    }
    public static void main(String[] args) {
        NodeND vet[] = null;
        String str[], aux;

        try {
            RandomAccessFile raf = new RandomAccessFile("./graph-test-100-1.txt", "r");
            str = raf.readLine().split(" ");
            vet = new NodeND[Integer.parseInt(str[0])];
            
            preencherVetor(vet);

            while ((aux = raf.readLine()) != null) {
                str = aux.trim().split("\\s+");
                vet[Integer.parseInt(str[0]) - 1].add(Integer.parseInt(str[1]));
                vet[Integer.parseInt(str[1]) - 1].add(Integer.parseInt(str[0]));
            }

            for (NodeND nd : vet) {
                nd.printValues();
            }

            raf.close();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}