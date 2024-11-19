package Implementacoes.tetse;

public class DualMethod {

    public static double[][] solve(TransportationProblem tp) {
        int m = tp.m;
        int n = tp.n;
        double[][] cost = tp.cost;
        double[] supply = tp.supply;
        double[] demand = tp.demand;

        // Inicializar os preços das fontes (u) e destinos (v)
        double[] u = new double[m];
        double[] v = new double[n];

        // Inicializar u[0] = 0, arbitrariamente
        u[0] = 0;
        for (int j = 0; j < n; j++) {
            v[j] = 0; // Inicializa todos os valores de v como 0
        }

        boolean change = true;
        while (change) {
            change = false;
            // Atualiza os valores de u e v conforme a regra de dualidade
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // A relação de dualidade: u[i] - v[j] = c[i][j]
                    if (cost[i][j] != Double.MAX_VALUE) {
                        if (u[i] - v[j] != cost[i][j]) {
                            // Ajusta os valores de u e v de acordo com os custos
                            u[i] = cost[i][j] + v[j];
                            change = true;
                        }
                    }
                }
            }
        }

        // Solução: A matriz de transporte baseada nos preços (u, v)
        double[][] solution = new double[m][n];

        // Calcular a quantidade de unidades a ser transportada
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                solution[i][j] = Math.min(supply[i], demand[j]);
                supply[i] -= solution[i][j];
                demand[j] -= solution[i][j];
            }
        }

        return solution;
    }
}
