import csv

def print_grafo_residual(filename):
    with open(filename, mode='r') as file:
        reader = csv.reader(file)
        next(reader)  # Pular o cabeçalho

        print("Vértice_Origem | Vértice_Destino | Fluxo/Capacidade")
        print("-" * 50)
        for row in reader:
            vertice_origem, vertice_destino, fluxo_capacidade = row
            print(f"{vertice_origem:^15} | {vertice_destino:^16} | {fluxo_capacidade:^14}")

# Chame a função passando o nome do arquivo CSV gerado
print_grafo_residual("./FluxoMaximo/residual.csv")
