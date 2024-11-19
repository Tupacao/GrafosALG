package Implementacoes.Implementação04;

public class Cell {
    private int x;
    private int c;

    public Cell (){
        this.x = 0;
        this.c = 0;
    }

    public Cell (int x, int c){
        this.x = x;
        this.c = c;
    }

    public void setX (int x){
        this.x = x;
    }

    public int getX (){
        return this.x;
    }

    public void setC (int c){
        this.c = c;
    }

    public int getC (){
        return this.c;
    }

    @Override
    public String toString() {
        return "C - X -> " + c + " - " + x;
    }

}