import matplotlib.pyplot as plt
import pandas as pd

# Dados
data = {
    "Tamanho": [10, 100, 1000, 5000],
    "GeradorGrafo_Min": [2.463E-4, 0.0237702, 25.6400181, 33.0541786],
    "GeradorGrafo_Max": [0.0039397, 0.0421293, 27.7953302, 36.656578],
    "GeradorGrafo_Media": [6.9262E-4, 0.03048771, 26.43922867, 34.98630337777778],
    "CayleyGrafo_Min": [3.65E-5, 1.249E-4, 2.286E-4, 9.223372036854776E9],
    "CayleyGrafo_Max": [1.852E-4, 6.223E-4, 0.0015441, -9.223372036854776E9],
    "CayleyGrafo_Media": [6.248E-5, 1.9852E-4, 3.6461E-4, 0.0],
}

df = pd.DataFrame(data)

# Gráfico para GeradorGrafo
plt.figure(figsize=(10, 5))
plt.plot(df["Tamanho"], df["GeradorGrafo_Min"], label="Tempo Mínimo", marker="o")
plt.plot(df["Tamanho"], df["GeradorGrafo_Max"], label="Tempo Máximo", marker="o")
plt.plot(df["Tamanho"], df["GeradorGrafo_Media"], label="Tempo Médio", marker="o")
plt.xlabel("Tamanho do Grafo")
plt.ylabel("Tempo (s)")
plt.title("Tempos de Execução - GeradorGrafo")
plt.legend()
plt.yscale("log")  # Escala logarítmica para visualizar grandes variações
plt.grid(True)

# Exibir o gráfico
plt.show()

# Gráfico para CayleyGrafo
plt.figure(figsize=(10, 5))
plt.plot(df["Tamanho"], df["CayleyGrafo_Min"], label="Tempo Mínimo", marker="o")
plt.plot(df["Tamanho"], df["CayleyGrafo_Max"], label="Tempo Máximo", marker="o")
plt.plot(df["Tamanho"], df["CayleyGrafo_Media"], label="Tempo Médio", marker="o")
plt.xlabel("Tamanho do Grafo")
plt.ylabel("Tempo (s)")
plt.title("Tempos de Execução - CayleyGrafo")
plt.legend()
plt.yscale("log")  # Escala logarítmica para visualizar grandes variações
plt.grid(True)

# Exibir o gráfico
plt.show()
