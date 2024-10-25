package Implementacoes.Implementação03;
public class App {
    public static void main(String[] args) {
        int tam = 5;
        GrafoGenerator gf = new GrafoGenerator(tam);
        CayleyGraph cg = new CayleyGraph(tam);
        
        new FordFulkerson(gf.cloneGrafo(), gf.cloneEdge(), tam - 1);
        new FordFulkerson(cg.cloneGrafo(), cg.cloneEdge(), tam - 1);
    }
}