package FluxoMaximo;

class Aresta {
    private int capacidade;
    private int fluxo;

    public Aresta (int capacidade){
        this.fluxo = 0;
        this.capacidade = capacidade;
    }

    public void setCapacidade (int val){
        if(fluxo + val <= capacidade){
            this.fluxo+=val;
        } else {
            System.out.println("Erro: Valor de capacidade excedida");
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
