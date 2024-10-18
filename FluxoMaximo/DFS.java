package FluxoMaximo;

import java.util.LinkedList;

public class DFS {
    
    private LinkedList<Integer> tree = null; 
    private int[] td = null;
    private LinkedList<Integer>[] grafo = null;

    public DFS (LinkedList<Integer>[] grafo){
        this.grafo = grafo;
        tree = new LinkedList<>();
        
        td = new int[grafo.length];
        for (int i = 0; i < td.length; i++) {
            td[i] = -1;
        }

        dfs();

        System.out.println(tree);
    }

    public LinkedList<Integer> getTree (){
        return this.tree;
    }

    private void dfs (){
        int time = 0;
        time = dfs(1, time);
    }

    private int dfs (int vertice, int time){
        time++;
        td[vertice - 1] = time;
        tree.add(vertice - 1);

        for (int w : grafo[vertice - 1]) {
            if(td[w] == - 1){
                time = dfs(w + 1, time);
            }
        }
        
        return time;
    }
}