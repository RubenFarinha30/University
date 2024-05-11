import numpy as np
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score, precision_score

class KNeighborsClassUE:
    
    # Inicializar a classe com os parâmetros k e p (com os valores por omissão)
    def __init__(self, k=3, p=2.0):
        self.k = k
        self.p = p
        self.X_train = None
        self.y_train = None
    
    # Método para treinar o modelo
    def fit(self, X, y):
        self.X_train = X
        self.y_train = y
        return self

    # Método para fazer previsões para um conjunto de dados X
    def predict(self, X):
        predictions = [self._predict(x) for x in X]
        return np.array(predictions)

    # Método auxiliar
    def _predict(self, x):
        # Calcular distâncias a todos os pontos no conjunto de treino
        distances = [self._distance(x, x_train) for x_train in self.X_train]

        # Ordenar e guardar na variável os indíces dos k-vizinhos mais próximos
        k_neighbors_indices = np.argsort(distances)[:self.k]

        # Guardar as classes dos k-vizinhos mais próximos
        k_neighbor_labels = [self.y_train[i] for i in k_neighbors_indices]

        # Dar return da class mais comum entre os k-vizinhos mais próximos
        most_common = max(set(k_neighbor_labels), key=k_neighbor_labels.count)
        return most_common

    # Calcula a distância de Minkowski entre dois pontos
    def _distance(self, x1, x2):
        return np.power(np.sum(np.power(np.abs(x1 - x2), self.p)), 1/self.p)

    # Calcular o desempenho do modelo com métricas de exatidão e precisão
    def score(self, X, y):
        predictions = self.predict(X)
        accuracy = accuracy_score(y, predictions)
        precision = precision_score(y, predictions, average='weighted')
        return accuracy, precision

#main
if __name__ == "__main__":

    # Ler o conjunto de dados
    data = pd.read_csv('C:/Users/ruben/Documents/48329_48392/iris.csv')

    # Separar atributos da classe
    X = data.values[:, :-1]
    y = data.values[:, -1]

    # Criar conjuntos de treino e teste
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.25, random_state=3)
    
    # Valores de teste para k e p
    k_values = [1, 3, 5, 9]
    p_values = [1, 2]

    # Testar o modelo para diferentes valores de k e p
    for k in k_values:
        for p in p_values:
            # Criar e treinar o modelo KNeighborsClassUE
            knn_ue = KNeighborsClassUE(k=k, p=p)
            knn_ue.fit(X_train, y_train)

            # Testar o modelo no conjunto de treino
            train_accuracy, train_precision = knn_ue.score(X_train, y_train)
            print(f"Desempenho do modelo no conjunto de treino (k={k}, p={p}):")
            print(f"  - Exatidão: {train_accuracy}")
            print(f"  - Precisão: {train_precision}")

            # Testar o modelo no conjunto de teste
            test_accuracy, test_precision = knn_ue.score(X_test, y_test)
            print(f"Desempenho do modelo no conjunto de teste (k={k}, p={p}):")
            print(f"  - Exatidão: {test_accuracy}")
            print(f"  - Precisão: {test_precision}")
            print()
