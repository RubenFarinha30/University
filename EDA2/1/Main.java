import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // LÃª o espaÃ§o e o nÃºmero de jogos
        int space = Integer.parseInt(reader.readLine());
        int nGames = Integer.parseInt(reader.readLine());
        Games[] games = new Games[nGames]; // Criar um array para armazenar os jogos

        // LÃª os detalhes de cada jogo e armazena-os num array Games
        for (int i = 0; i < nGames; i++) {
            String[] input = reader.readLine().split(" ");
            String name = input[0];
            int players = Integer.parseInt(input[1]);
            int enthusiasm = Integer.parseInt(input[2]);
            games[i] = new Games(name, players, enthusiasm);
        }

        // Cria uma matriz para armazenar os resultados dos cÃ¡lculos
        int[][] matrix = new int[nGames + 1][space + 1];

        // Algoritmo de programaÃ§Ã£o dinÃ¢mica, knapsack, para calcular os jogos selecionados e o entusiasmo total
        for (int i = 1; i <= nGames; i++) {
            Games currentGame = games[i - 1];
            for (int j = 1; j <= space; j++) {
                if (currentGame.players <= j) {
                    matrix[i][j] = greater(matrix[i - 1][j], matrix[i - 1][j - currentGame.players] + currentGame.enthusiasm);
                } else {
                    matrix[i][j] = matrix[i - 1][j];
                }
            }
        }

        // Encontra os jogos selecionados e imprime os resultados
        int[] selectedGames = new int[nGames];
        int remainingSpace = space;
        int selectedCount = 0;

        for (int i = nGames; i > 0 && remainingSpace > 0; i--) {
            if (matrix[i][remainingSpace] != matrix[i - 1][remainingSpace]) {
                selectedGames[selectedCount++] = i - 1;
                remainingSpace -= games[i - 1].players;
            }
        }

        // Imprime o nÃºmero de jogos selecionados, o espaÃ§o ocupado e o entusiasmo total
        System.out.println(selectedCount + " " + (space - remainingSpace) + " " + matrix[nGames][space]);

        // Imprime os nomes dos jogos selecionados por ordem de input
        for (int i = selectedCount - 1; i >= 0; i--) {
            System.out.println(games[selectedGames[i]].name);
        }
    }

    // Retorna o maior entre dois nÃºmeros (Math.max)
    static int greater(int a, int b) {
        return a > b ? a : b;
    }
}

// Classe que define jogo
class Games {
    String name;
    int players;
    int enthusiasm;

    public Games(String name, int players, int enthusiasm) {
        this.name = name;
        this.players = players;
        this.enthusiasm = enthusiasm;
    }
}