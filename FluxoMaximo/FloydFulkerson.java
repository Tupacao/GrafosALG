package FluxoMaximo;

import java.util.LinkedList;
import java.util.Map;

public class FloydFulkerson {

    LinkedList<Integer>[] residual;
    LinkedList<Integer>[] graph;
    Map<String, Aresta> edges;
    

    public FloydFulkerson (LinkedList<Integer>[] graph, Map<String, Aresta> edges) {
        this.graph = graph;
        this.edges = edges;
        fluxoMaximo();
    }

    @SuppressWarnings("unchecked")
    private void fluxoMaximo (){

        // residual = new LinkedList[graph.length];
        // for (int i = 0; i < graph.length; i++) {
        //     for (int w : graph[i]) {
        //         if(edges.get(i+"-"+w).getFluxo() == 0){
        //             residual[i].add(w);
        //         } else {
        //             residual[i].add(w);
        //             residual[w].add(i);
        //         }
        //     }
        // }

        residual = graph;

        int maxflox = -1;

        while (DFS) {
            int pathFlow = Integer.MAX_VALUE;
            for (int i = 0; i < tree.length-2; i++) {
                pathflow = Math.min(edges.get(tree[i]+"-"+tree[i+1]).getFluxo(), edges.get(tree[i+1]+"-"+tree[i+2]).getFluxo());                
            }


            for (int i = 0; i < tree.length-1; i++) {
                edges.get(tree[i]+"-"+tree[i+1]).setCapacidade(pathFlow);                
            }

            maxflox = pathFlow;
        }

        return maxflox;

    }

}