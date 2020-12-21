import java.util.Arrays;
import java.util.List;

public class Board  implements Ilayout{

    private static final int dim = 3;
    private char[][] board;
    private int status;

    /**
     * Constructor without any argument. It creates the board array as an empty array.
     */
    public Board() {
        char[][] b = new char[dim][dim];
        this.board = initializeBoard(b);
    }


    /**
     * Initialize a new board without any values.
     */
    public char[][] initializeBoard(char[][] b) {
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                b[i][j] = '_';
            }
        }
        return b;
    }

    public char[][] getBoard() {
        return board;
    }

    /**
     * @throws IllegalStateException in case the Java application is not in an appropriate state for the requested
     *                               operation.
     */
    public Board(String row1, String row2, String row3) throws IllegalStateException {
        String[] row1Splitted = row1.split("");
        String[] row2Splitted = row2.split("");
        String[] row3Splitted = row3.split("");

        if (row1Splitted.length != dim || row2Splitted.length != dim || row3Splitted.length != dim)  {
            throw new IllegalStateException("Invalid arg in Board constructor");
        }


        board = new char[dim][dim];
        int result = 0;
        for (int i = result; i < dim; i++) {
            board[0][i] = row1Splitted[i].charAt(0);
        }
        for (int i = result; i < dim; i++) {
            board[1][i] = row2Splitted[i].charAt(0);
        }
        for (int i = result; i < dim; i++) {
            board[2][i] = row3Splitted[i].charAt(0);
        }
    }


    /**
     * The possibility to create a deep copy of the Board.
     *
     * @return deep copy of this.board.
     * @throws CloneNotSupportedException
     */
//    protected Object clone() throws CloneNotSupportedException {
//        Board boardCopy = new Board();
//        boardCopy.board = new int[dim][dim];
//        for (int i = 0; i < dim; i++) {
//            for (int j = 0; j < dim; j++) {
//                boardCopy.board[i][j] = this.board[i][j];
//            }
//        }
//        return boardCopy;
//    }








    public String toString() {
        String result = "";
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                result = result.concat(String.valueOf(board[i][j]));
            }
            result = result.concat("\n");
        }

        return result;
    }


    @Override
    public int hashCode() {
        return Arrays.hashCode(board);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (board.board[i][j] != this.board[i][j])
                    return false;
            }
        }
        return true;
    }


    @Override
    public int getTurn() {
        return 1; //this is still not great
    }

    @Override
    public List<Ilayout> children() throws CloneNotSupportedException {
        return null;//Fazemos a children utilizando aquela forma inteliegente para reduzir o
        //número de children
    }

    @Override
    public int getStatus() {
        return 0; //Temos de verificar linhas, colunas e diagonais e consoante
        // o resultado atribuir um valor ao status do board
        //Se o jogador 1 ganhou, se o jogador 2 ganhou, se empataram
        // ou se o jogo continua em execução
    }
}
