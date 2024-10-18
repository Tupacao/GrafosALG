package FluxoMaximo;

public class App {
    public static void main(String[] args) {
        GrafoGenerator gfr = new GrafoGenerator(20);
        DFS dfs = new DFS(gfr.cloneGrafo());
        Grafo gf = new Grafo("./FluxoMaximo/grafo.csv", 20);
    }
}