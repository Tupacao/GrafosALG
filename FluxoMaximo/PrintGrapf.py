import csv
import networkx as nx
import matplotlib.pyplot as plt

# Função para ler o arquivo CSV e construir o grafo
def load_graph_from_csv(filename):
    G = nx.DiGraph()  # Cria um grafo direcionado
    with open(filename, 'r') as file:
        reader = csv.reader(file)
        for row in reader:
            u, v = int(row[0]), int(row[1])
            G.add_edge(u, v)  # Adiciona a aresta (u -> v)
    return G

# Função para plotar o grafo em formato de árvore
def plot_graph_as_tree(G, base_node):
    # Usa um layout hierárquico
    pos = nx.spring_layout(G, k=0.5, iterations=100)  # spring layout como base
    
    # Layout de árvore direcionada
    pos = nx.circular_layout(G)  # shell layout organiza o grafo em camadas
    
    # Plotando o grafo
    nx.draw(G, pos, with_labels=True, node_color='lightblue', node_size=500, font_size=10, font_weight='bold', arrows=True)
    plt.show()

# Carregar o grafo do arquivo CSV
graph = load_graph_from_csv('./FluxoMaximo/grafo.csv')

# Escolher o nó base, por exemplo, o nó 0 como a raiz da árvore
base_node = 0

# Plotar o grafo como uma árvore
plot_graph_as_tree(graph, base_node)
