package FluxoMaximo;

class Aresta {
    private int capacidade;
    private int fluxo;

    public Aresta (int capacidade){
        this.fluxo = 0;
        this.capacidade = capacidade;
    }

    public void setFluxo(int val) {
        if (val >= 0 && fluxo + val <= capacidade) {
            this.fluxo += val;
        } else if (val < 0 && fluxo + val >= 0) {
            this.fluxo += val;
        } else {
            System.out.println("Erro: Fluxo inválido");
        }
    }

    public int getFluxo (){
        return this.fluxo;
    }

    public int getCapacity (){
        return this.capacidade;
    }
    
    public String printAresta (){
        return "Fluxo: " + fluxo + ", Capacidade: " + capacidade;
    }

}
