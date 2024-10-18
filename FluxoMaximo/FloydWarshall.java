package FluxoMaximo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FloydWarshall {

    int[][] dist = null;
    List<Set<Integer>> predecessors = null;

    public FloydWarshall (int tam) {
        dist = new int[tam][tam];
        
    }

}