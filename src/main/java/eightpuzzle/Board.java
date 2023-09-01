package eightpuzzle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Board {
    int[][] Tiles;
    public Board(int[][] tiles) {
        Tiles = tiles;
    }

    public String toString() {
        int N = dimension();
        StringBuilder result = new StringBuilder();
        result.append(dimension()).append("\n");
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                result.append(Tiles[i][j]).append("\t");
            }
            result.append("\n");
        }
        return result.toString();
    }

    public int dimension() {
        return Tiles.length;
    }

    // No of tiles out of place
    public int hamming() {
        int N = dimension();
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // Last tile
                if (i == N-1 && j == N-1) {
                    if (Tiles[i][j] != 0) {
                        count++;
                    }
                } else {
                    int value = N*i + j + 1;
                    if (Tiles[i][j] != value) {
                        count++;
                    }
                }
            }
        }
        return count;
    };

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int N = dimension();
        int manhattan = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == N-1 && j == N-1) {
                    break;
                }
                int goalI = ((Tiles[i][j] - 1) / N);
                int goalJ = Tiles[i][j] % N == 0 ? N-1 : (Tiles[i][j] % N) - 1;
                if (i != goalI) {
                    manhattan += Math.abs(i - goalI);
                }
                if (j != goalJ) {
                    manhattan += Math.abs(j - goalJ);
                }
            }
        }
        return manhattan;
    }

    // is this board the goal board?
    // TODO
    public boolean isGoal() {
        return false;
    }

    // does this board equal y?
    // TODO
    public boolean equals(Object y) {
        assert y instanceof int[][];
        int[][] yTiles = (int[][]) y;

        int dimension = dimension();
        if (dimension != yTiles.length) {
            return false;
        }
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (Tiles[i][j] != yTiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // all neighboring boards
    // TODO
    public Iterable<Board> neighbors() {
        int x = 0;
        int y = 0;
        int dimension = dimension();
        int[][] copYTiles = new int[dimension][dimension];
        List<Board> nbs = new ArrayList<>();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (Tiles[i][j] == 0) {
                    x = i;
                    y = j;
                }
                copYTiles[i][j] = Tiles[i][j];
            }
        }


        if (x > 0) {
            // Insert top neighbor
            int tmp = copYTiles[x-1][y];
            copYTiles[x-1][y] = 0;
            copYTiles[x][y] = tmp;

            int[][] copyDedicated = new int[dimension][dimension];
            for (int i= 0; i < dimension; i++)
                for (int j = 0; j < dimension; j++)
                    copyDedicated[i][j] = copYTiles[i][j];

            nbs.add(new Board(copyDedicated));
            copYTiles[x-1][y] = tmp;
            copYTiles[x][y] = 0;
        }
        if (x < (dimension - 1)) {
            // Insert bottom neighbor
            int tmp = copYTiles[x+1][y];
            copYTiles[x+1][y] = 0;
            copYTiles[x][y] = tmp;

            int[][] copyDedicated = new int[dimension][dimension];
            for (int i= 0; i < dimension; i++)
                for (int j = 0; j < dimension; j++)
                    copyDedicated[i][j] = copYTiles[i][j];

            nbs.add(new Board(copyDedicated));
            copYTiles[x+1][y] = tmp;
            copYTiles[x][y] = 0;
        }
        if (y > 0) {
            // Insert left neighbor
            int tmp = copYTiles[x][y-1];
            copYTiles[x][y-1] = 0;
            copYTiles[x][y] = tmp;

            int[][] copyDedicated = new int[dimension][dimension];
            for (int i= 0; i < dimension; i++)
                for (int j = 0; j < dimension; j++)
                    copyDedicated[i][j] = copYTiles[i][j];

            nbs.add(new Board(copyDedicated));
            copYTiles[x][y-1] = tmp;
            copYTiles[x][y] = 0;
        }
        if (y < (dimension - 1)) {
            // Insert right neighbor
            int tmp = copYTiles[x][y+1];
            copYTiles[x][y+1] = 0;
            copYTiles[x][y] = tmp;

            int[][] copyDedicated = new int[dimension][dimension];
            for (int i= 0; i < dimension; i++)
                for (int j = 0; j < dimension; j++)
                    copyDedicated[i][j] = copYTiles[i][j];

            nbs.add(new Board(copyDedicated));
            copYTiles[x][y+1] = tmp;
            copYTiles[x][y] = 0;
        }

        return nbs;
    }

    // a board that is obtained by exchanging any pair of tiles
    // TODO
    public Board twin() {
        return null;
    }

    public static void main(String[] args) {
        int[][] tiles = new int[3][3];
        tiles[0][0] = 1;
        tiles[0][1] = 2;
        tiles[0][2] = 3;
        tiles[1][0] = 4;
        tiles[1][1] = 0;
        tiles[1][2] = 6;
        tiles[2][0] = 8;
        tiles[2][1] = 7;
        tiles[2][2] = 5;
        Board board = new Board(tiles);
        System.out.println(board);
        System.out.println("Hamming distance: " + board.hamming());
        System.out.println("Manhattan distance: " + board.manhattan());
        int[][]yTiles = new int[3][3];
        System.out.println(board.equals(yTiles));
        for (Board nb: board.neighbors()) {
            System.out.println(nb);
        }
    }
}
