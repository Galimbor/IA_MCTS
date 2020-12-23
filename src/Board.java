import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board implements Ilayout, Cloneable {

    private static final int dim = 3;
    private char[][] board;
    private int openSlots = dim * dim;
    private char currentPlayer;
    private static char openingPiece;

    /**
     * Constructor where a bidimensional char array is passed as an argument, creating a Board object.
     */
    public Board(char[][] board) {
        this.board = board;
        setCurrentPlayer();
    }

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
     *
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
        if (row1Splitted.length != dim || row2Splitted.length != dim || row3Splitted.length != dim) {
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

        //Set current player
        setCurrentPlayer();
    }


    public void placeMove(int x, int y,char move) {
        this.board[x][y] = move;
        setCurrentPlayer();
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
     *
     * @return String
     */
    public String toString() {
        String result = "";
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (j == dim - 1)
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



    public char getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Verifies if an object is a Board and if so, then verifies if two Board objects are equal,
     * that's if all their positions hold the same values.
     *
     * @param o other object to be compared
     * @return boolean
     */
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

    /**
     * Determines who's the next player to make a move, according to the current Board, and sets the object variable.
     */
    private void setCurrentPlayer() {
        int playerXmoves = 0;
        int player0moves = 0;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (this.board[i][j] == 'X')
                    playerXmoves++;
                else if (this.board[i][j] == '0')
                    player0moves++;
            }

        }
//        System.out.println(playerXmoves + " xmoves");
//        System.out.println(player0moves + " 0moves");
        if (playerXmoves == player0moves) this.currentPlayer = openingPiece;
        else if (playerXmoves > player0moves)
        {
            this.currentPlayer = '0';
        }
        else {
            this.currentPlayer = 'X';
        }

        setOpenSlots(dim * dim - (playerXmoves + player0moves));
    }

    public void setOpeningPiece(char openingPiece) {
        openingPiece = openingPiece;
    }


    @Override
    public int getTurn() {
        return 1; //this is still not great
    }

    /**
     * Missing optimization
     * Computes a List containing the boards with all the possible moves for the current player.
     *
     * @return children of this board.
     * @throws CloneNotSupportedException in case it cannot create a new board to hold the new positions.
     */
    @Override
    public List<Ilayout> children() throws CloneNotSupportedException {

        List<Ilayout> children = new ArrayList<>();
        char nextPiece;

        if (this.currentPlayer == 'X') nextPiece = 'X';
        else nextPiece = '0';

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (this.board[i][j] == '_') {
                    Board boardCopy = (Board) this.clone();
                    boardCopy.board[i][j] = nextPiece;
                    boardCopy.setCurrentPlayer();
                    children.add(boardCopy);
                }
            }
        }

        return children;

    }

    public int getOpenSlots() {
        return openSlots;
    }

    public void setOpenSlots(int openSlots) {
        this.openSlots = openSlots;
    }

    /**
     * Creates a deepcopy of Board object.
     *
     * @return deep copy of this.board.
     * @throws CloneNotSupportedException
     */
    protected Object clone() throws CloneNotSupportedException {
        Board boardCopy = (Board) super.clone();
        boardCopy.board = new char[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                boardCopy.board[i][j] = this.board[i][j];
            }
        }
        return boardCopy;
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