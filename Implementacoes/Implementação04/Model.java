package Implementacoes.Implementação04;

public class Model {
    
    private Cell mat[][] = null;
    private int  k, l;
    private int demand[] = null, i;
    private int offer[] = null, j;

    public Model (){
        this.mat = null;
        this.offer = null;
        this.demand = null;
    }

    public Model (int axis, int cross){
        this.mat = new Cell[axis][cross];
        this.offer = new int[axis];
        this.demand = new int[cross];
        i = 0;
        j = 0;
        k = 0;
        l = 0;
    }

    public void addDemand (int demand){
        this.demand[i++] = demand;
    }

    public void addOffer (int offer){
        this.offer[j++] = offer;
    }

    public void setMat (String vet[]){
        for (String str : vet) {
            mat[k][l++] = new Cell(0, Integer.parseInt(str));
        }
        k++;
        l = 0;
    }

    public void printMat (){
        for (Cell[] cell : mat) {
            for (int i = 0; i < cell.length; i++) {
                System.out.print(cell[i] + " ");
            }
            System.out.println("");
        }
    }

}
