package Implementacoes.tetse;

public class Main {
    
    public static void main(String[] args) {
        int m = 3; // Número de fontes
        int n = 3; // Número de destinos

        // Criar o problema de transporte
        TransportationProblem tp = new TransportationProblem(m, n);

        // Definir a matriz de custos
        double[][] cost = {
            {2, 3, 1},
            {5, 4, 8},
            {7, 6, 3}
        };
        tp.setCost(cost);

        // Definir as ofertas e demandas
        double[] supply = {10, 15, 20};
        double[] demand = {15, 10, 20};
        tp.setSupply(supply);
        tp.setDemand(demand);

        // Exibir os dados do problema
        System.out.println("Matriz de Custos:");
        tp.printCost();

        // Resolver o problema usando o método dual
        double[][] solution = DualMethod.solve(tp);

        // Exibir a solução
        tp.printSolution(solution);
    }
}
