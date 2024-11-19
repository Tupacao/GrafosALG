package Implementacoes.tetse;

public class TransportationProblem {
    public int m; // Número de fontes
    public int n; // Número de destinos
    public double[][] cost; // Matriz de custos
    public double[] supply; // Oferta de cada fonte
    public double[] demand; // Demanda de cada destino

    public TransportationProblem(int m, int n) {
        this.m = m;
        this.n = n;
        this.cost = new double[m][n];
        this.supply = new double[m];
        this.demand = new double[n];
    }

    public void setCost(double[][] cost) {
        this.cost = cost;
    }

    public void setSupply(double[] supply) {
        this.supply = supply;
    }

    public void setDemand(double[] demand) {
        this.demand = demand;
    }

    public void printCost() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(cost[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printSolution(double[][] solution) {
        System.out.println("Solução de Transporte:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(solution[i][j] + " ");
            }
            System.out.println();
        }
    }
}