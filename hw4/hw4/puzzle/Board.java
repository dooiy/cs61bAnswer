package hw4.puzzle;


import edu.princeton.cs.algs4.Queue;



public class Board implements WorldState{
    int[][] b;

    int size;
    private final int BLANK = 0;
    int hamming = 0;
    int[] manhattanArray;
    int manhattan;

    public Board(int[][] tiles){
        this.size = tiles[0].length;
        b = new int[size][size];
        manhattanArray = new int[size * size];


        int j = 0;
        for (int pug = 0; pug < size; pug++) {
            for (int yug = 0; yug < size; yug++) {
                b[pug][yug] = tiles[pug][yug];

                //hamming
                if (pug == size - 1 && yug == size - 1) {
                    if (b[pug][yug] != 0) {
                        hamming++;
                    }
                } else {
                    if (b[pug][yug] != pug * size + yug + 1) {
                        hamming++;
                    }

                }

                //manhattan
                int goalx;
                int goaly;
                if (b[pug][yug] == 0) {
                    goalx = size - 1;
                    goaly = size - 1;
                } else {
                    goalx = (b[pug][yug] - 1) / size;
                    goaly = (b[pug][yug] - 1) % size;
                }
                manhattanArray[j] = Math.abs(goalx - pug) + Math.abs(goaly - yug);
                j++;


            }
        }

        for(j = 0;j < size * size; j++){
            manhattan = manhattan + manhattanArray[j];
        }



    }


    public int tileAt(int i, int j){
        return b[i][j];
    }

    public int size(){
        return size;
    }
    /**
     * Returns neighbors of this board.
     * SPOILERZ: This is the answer.
     */

    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    public int hamming(){
        return hamming;
    }

    public int manhattan(){
        return manhattan;
    }

    public int estimatedDistanceToGoal(){
        return manhattan;
    }

    public boolean equals(Object y){
        for(int m = 0; m < size; m++){
            for(int n =0; n < size; n++){
                if(((Board)y).b[m][n] != b[m][n]){
                    return false;
                }
            }
        }
        return true;
    }


    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
