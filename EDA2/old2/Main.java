import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //get input
        String[] rcT = br.readLine().split(" ");
        int rows = Integer.parseInt(rcT[0]) + 1;
        int cols = Integer.parseInt(rcT[1]) + 1;
        int numTests = Integer.parseInt(rcT[2]);

        char[][] map = new char[rows][cols];
        for (int i = 1; i < rows; i++) {
            String line = br.readLine();
            for (int j = 1; j < cols; j++) {
                map[i][j] = line.charAt(j - 1);
            }
        }

        int[][] tests = new int[numTests][2];
        for (int i = 0; i < numTests; i++) {
            String[] testCoords = br.readLine().split(" ");
            tests[i][0] = Integer.parseInt(testCoords[0]);
            tests[i][1] = Integer.parseInt(testCoords[1]);
        }
        
        //dar output
        for (int i = 0; i < numTests; i++) {
        	  int result = minimumMoves(map, tests[i][0], tests[i][1]);
              if (result == -1) {
                  System.out.println("Stuck");
              } else {
                  System.out.println(result);
              }
        }
        
        br.close();
    }

    // FunÃ§Ã£o de resolver o "labirinto"
    private static int minimumMoves(char[][] map, int row, int col) {

        //preencher o mapa com Maxvalues para posterior uso
        int[][] moves = new int[map.length][map[0].length];
        for (int[] rowMoves : moves) {
            Arrays.fill(rowMoves, Integer.MAX_VALUE);
        }

        //definir direÃ§oes
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        //Criar lista para procura em largura
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        moves[row][col] = 0;

        //Ciclo while, onde entra a posiÃ§Ã£o inicial e as posiÃ§Ãµes que dÃ£o bump
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currRow = curr[0];
            int currCol = curr[1];

            //iterar as direÃ§Ãµes da posiÃ§Ã£o
            for (int[] direction : directions) {
                int nextRow = currRow + direction[0];
                int nextCol = currCol + direction[1];
                
                //atualizar # steps para chegar a H
                int steps = moves[currRow][currCol] + 1;

                //ciclo while que limita o mapa
                while (nextRow > 0 && nextRow < map.length && nextCol > 0 && nextCol < map[0].length && map[nextRow][nextCol] != 'O') {
                    //verificar se H estÃ¡ na linha/coluna
                    if (map[nextRow][nextCol] == 'H') {
                        return steps;
                    }
                    //verificar se ja passamos na posiÃ§Ã£o para quebrar possiveis loops infinitos
                    if (moves[nextRow][nextCol] == Integer.MAX_VALUE) {

                        //atualizar a posiÃ§Ã£o com #steps, para indicar que passamos la
                        moves[nextRow][nextCol] = steps;

                        int next_row_step = nextRow + direction[0];
                        int next_col_step = nextCol + direction[1];
                       	
                        //verifcar se a posiÃ§Ã£o a frente nÃ£o Ã© fora do mapa
                       	if(next_row_step > map.length - 1 || next_col_step > map[0].length - 1) {
                       		break;
                       	}
                       	
                        //verificar se existe obstaculo a frente, se existe entra na queue
                        if (map[next_row_step][next_col_step] == 'O') {
                            queue.add(new int[]{nextRow, nextCol});
                        }
                    }

                    //andar na direÃ§Ã£o
                    nextRow += direction[0];
                    nextCol += direction[1];
                }
            }
        }
        return -1;
    }
}