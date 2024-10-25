package FluxoMaximo;

import java.util.LinkedList;

public class DFS {

    private LinkedList<Integer> tree = null;
    private int[] td = null;
    private int[] sun = null;
    private LinkedList<Integer>[] grafo = null;

    public DFS (LinkedList<Integer>[] grafo){
        this.grafo = grafo;
        tree = new LinkedList<>();
        
        td = new int[grafo.length];
        sun = new int[grafo.length];
        for (int i = 0; i < td.length; i++) {
            td[i] = -1;
            sun[i] = -1;
        }

        dfs();

        if (td[grafo.length - 1] != -1) {
            tree.addFirst(grafo.length - 1);
            int aux = sun[grafo.length - 1];
            while (aux != -1) {
                tree.addFirst(aux);
                aux = sun[aux];
            }
        }
        
        // System.out.println(tree);
    }

    public LinkedList<Integer> getTree() {
        return this.tree;
    }

    private void dfs() {
        int time = 0;
        time = dfs(0, time);
    }

    private int dfs(int vertice, int time) {
        time++;
        td[vertice] = time;

        for (int w : grafo[vertice]) {
            if (td[w] == -1) {
                sun[w] = vertice;
                time = dfs(w, time);
            }
        }

        return time;
    }
}