import numpy as np
from sklearn.metrics import accuracy_score, precision_score
from sklearn.model_selection import train_test_split

class NBayesClassUE:
    
    # Inicializar a classe com o parâmetro alpha do estimador
    def __init__(self, alpha=1):
        self.alpha = alpha
        self.class_probs = {}       # Dicionário para armazenar probabilidades das classes
        self.feature_probs = {}     # Dicionário para armazenar probabilidades condicionais dos atributos
        self.classes = []           # Lista de classes presentes nos dados
        
    # Método para treinar o modelo
    def fit(self, X, y):
        self.classes, class_counts = np.unique(y, return_counts=True)
        total_samples = len(y)
        
        # Loop externo sobre todas as classes
        for label in self.classes:
            label_indices = (y == label)
            # Calcular a probabilidade da classe atual
            self.class_probs[label] = (len(y[label_indices]) + self.alpha) / (total_samples + len(self.classes) * self.alpha)
            
            feature_probs_label = {}
            
            # Loop interno sobre os atributos
            for feature in X.columns:
                feature_values = np.unique(X[feature])
                feature_counts = X.loc[label_indices, feature].value_counts()
                total_feature_values = len(feature_values)
                
                feature_probs = {}
                
                # Loop sobre todos os valores únicos da atributos
                for value in feature_values:
                    # Ignorar instâncias com valores ausentes
                    if pd.notna(value):
                        count = feature_counts.get(value, 0)
                        # Calcular a probabilidade condicional para a classe e atributo
                        feature_probs[value] = (count + self.alpha) / (len(y[label_indices]) + total_feature_values * self.alpha)
                
                feature_probs_label[feature] = feature_probs
            
            self.feature_probs[label] = feature_probs_label
            
        return self

    # Método para fazer previsões
    def predict(self, X):
        predictions = []
        
        # Loop sobre todas as amostras no conjunto de teste
        for _, sample in X.iterrows():
            max_prob = float('-inf')
            predicted_class = None
            
            # Loop sobre todas as classes
            for label in self.classes:
                class_prob = np.log(self.class_probs[label])
                feature_probs = self.feature_probs[label]
                
                # Loop sobre todos os atributos e valores da amostra
                for feature, value in sample.items():
                    if feature in feature_probs and pd.notna(value):
                        # Calcular a probabilidade logarítmica condicional da classe para a amostra
                        feature_prob = feature_probs[feature].get(value, 0)
                        class_prob += np.log(feature_prob)
                
                # Atualizar a classe prevista se a probabilidade atual for maior
                if class_prob > max_prob:
                    max_prob = class_prob
                    predicted_class = label
            
            # Adicionar a classe prevista à lista de previsões
            predictions.append(predicted_class)
        
        return np.array(predictions)

    # Método para calcular o desempenho do modelo com métricas de exatidão e precisão
    def score(self, X, y):
        predictions = self.predict(X)
        accuracy = accuracy_score(y, predictions)
        precision = precision_score(y, predictions, average='weighted')  # Calcula a precisão ponderada
        return accuracy, precision

# Exemplo de uso
if __name__ == "__main__":
    import pandas as pd

    # Ler o conjunto de dados
    data = pd.read_csv('/Users/drricardoantoniogomes/Desktop/48329_48392/bc-nominal.csv')

    # Remover instâncias com valores ausentes
    data = data.dropna()

    # Separar atributos da classe
    X = data.iloc[:, :-1]
    y = data.iloc[:, -1]

    # Criar conjuntos de treino e teste
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.25, random_state=3)

    # Valores de teste para alpha
    alpha_values = [0, 1, 3, 5]

    # Testar o modelo para diferentes valores de alpha
    for alpha in alpha_values:
        # Criar e treinar o modelo NBayesClassUE
        NBayesUE = NBayesClassUE(alpha=alpha)
        NBayesUE.fit(X_train, y_train)

        # Testar o modelo no conjunto de treino
        train_accuracy, train_precision = NBayesUE.score(X_train, y_train)
        print(f"Desempenho do modelo no conjunto de treino (alpha={alpha}):")
        print(f"  - Exatidão: {train_accuracy}")
        print(f"  - Precisão: {train_precision}")

        # Testar o modelo no conjunto de teste
        test_accuracy, test_precision = NBayesUE.score(X_test, y_test)
        print(f"Desempenho do modelo no conjunto de teste (alpha={alpha}):")
        print(f"  - Exatidão: {test_accuracy}")
        print(f"  - Precisão: {test_precision}")
        print()
