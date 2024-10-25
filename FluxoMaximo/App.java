package FluxoMaximo;

public class App {
    public static void main(String[] args) {
        int tam = 10;
        // GrafoGenerator gf = new GrafoGenerator(tam);
        CayleyGraph gf = new CayleyGraph(tam);
        // Grafo gf = new Grafo("./FluxoMaximo/grafo.csv", tam);
        FordFulkerson fd = new FordFulkerson(gf.cloneGrafo(), gf.cloneEdge(), tam - 1);
        
    }
}