import java.util.Arrays;
import java.util.List;

public class Board  implements Ilayout{

    private static final int dim = 3;
    private char[][] board;
    private int status;
    private static final int openSlots = dim * dim;


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
     * Create a new board given the rows of the game
     * @param row1 First row of tic tac toe game
     * @param row2 First row of tic tac toe game
     * @param row3 First row of tic tac toe game
     * @throws IllegalStateException in case the Java application is not in an appropriate state for the requested
     *                               operation.
     */
    public Board(String row1, String row2, String row3) throws IllegalStateException {
        //Split each argument into characters
        String[] row1Splitted = row1.split("");
        String[] row2Splitted = row2.split("");
        String[] row3Splitted = row3.split("");

        //Throw exception in case the board is incomplete
        if (row1Splitted.length != dim || row2Splitted.length != dim || row3Splitted.length != dim)  {
            throw new IllegalStateException("Invalid arg in Board constructor");
        }

        //New board
        board = new char[dim][dim];
        int result = 0;
        //Fill the first row
        for (int i = result; i < dim; i++) {
            board[0][i] = row1Splitted[i].charAt(0);
        }
        //Fill the second row
        for (int i = result; i < dim; i++) {
            board[1][i] = row2Splitted[i].charAt(0);
        }
        //Fill the third row
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


    /**
     * String representation of the tic tac toe board. Where "_" indicates a blank space.
     * @return String
     */
    public String toString() {
        String result = "";
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if(j == dim - 1)
                    result = result.concat(String.valueOf(board[i][j]));
                else
                    result = result.concat(String.valueOf(board[i][j])) + "|";
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



    public int getOpenSlots() {
        return openSlots;
    }


    @Override
    public String getStatus() {
        String result = "in progress";
        if (checkCircles()) {
            result = "circles win";
        } else if (checkCrosses()) {
            result = "crosses win";
        } else if (checkDraw()) {
            result = "draw";
        }
        return result;
    }


    private boolean checkDraw() {
        boolean result = false;
        if (this.getOpenSlots() == 0)
            result = true;
        return result;
    }

    public boolean checkRows(char piece) {
        boolean result = false;
        for (int i = 0; i < dim; i++) {
            int j = 0;
            int counter = 0;
            while (j < dim && this.board[i][j] == piece) {
                counter++;
                j++;
                if (counter == 3)
                    result = true;
            }
        }
        return result;
    }

    public boolean checkColumns(char piece) {
        boolean result = false;
        for (int j = 0; j < dim; j++) {
            int i = 0;
            int counter = 0;
            while (i < dim && this.board[i][j] == piece) {
                counter++;
                i++;
                if (counter == 3)
                    result = true;
            }
        }
        return result;
    }


    public boolean checkDiags(char piece) {
        boolean result = false;
        int j = 0;
        int counter = 0;
        while (j < dim && this.board[j][j] == piece) {
            counter++;
            j++;
            if (counter == 3)
                result = true;
        }
        j = 0;
        int i = dim - 1;
        counter = 0;
        while (j < dim && i >= 0 && this.board[i][j] == piece) {
            counter++;
            j++;
            i--;
            if (counter == 3)
                result = true;
        }

        return result;
    }

    private boolean checkCircles() {
        boolean result = false;
        char piece = '0';
        if (checkRows(piece) || checkDiags(piece) || checkColumns(piece))
            result = true;
        return result;
    }

    private boolean checkCrosses() {
        boolean result = false;
        char piece = 'X';
        if (checkRows(piece) || checkDiags(piece) || checkColumns(piece))
            result = true;
        return result;
    }

}