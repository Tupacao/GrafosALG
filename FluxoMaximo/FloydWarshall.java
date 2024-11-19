package FluxoMaximo;

import java.util.List;

public class FloydWarshall {

    int[][] dist = null;
    int[][] residual_dist = null;
    List<Integer>[] residual_graph;
    Grafo graph;

    private void copyGraph (){
        residual_graph = graph.cloneGrafo();
    }

    public FloydWarshall (int tam) {
        dist = new int[tam][tam];
        
    }

}