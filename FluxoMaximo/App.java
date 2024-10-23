package FluxoMaximo;

public class App {
    public static void main(String[] args) {
        GrafoGenerator gf = new GrafoGenerator(20);
        // Grafo gf = new Grafo("./FluxoMaximo/grafo.csv", 10);
        FordFulkerson fd = new FordFulkerson(gf.cloneGrafo(), gf.cloneEdge(), 19);
    }
}