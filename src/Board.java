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

    /** 1 = X
     *  0 = O
     *  -1 = Nothing?
     * @param str string format ("1")
     * @throws IllegalStateException in case the Java application is not in an appropriate state for the requested
     *                               operation.
     */
    public Board(String row1, String row2, String row3) throws IllegalStateException {
//        if (str.length() != dim * dim) throw new IllegalStateException("Invalid arg in Board constructor");
        board = new char[dim][dim];
        int si = 0;
        for (int i = 0; i < dim; i++)
            for (int j = 0; j < dim; j++) {
                if(str.charAt(si) == ' ' )
                    board[i][j] = -1;
                else{
                    board[i][j] = Character.getNumericValue(str.charAt(si++));
                }
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
