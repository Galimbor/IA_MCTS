import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicTacToe implements Ilayout, Cloneable {

    private static final int dim = 3;
    private Board board;
    private char currentPlayer;
    private static char openingPiece;

    /**
     * Constructor where a bidimensional char array is passed as an argument, creating a TicTacToe object.
     */
    public TicTacToe(char[][] board) {
        this.board = new Board(board);
        setCurrentPlayer();
    }

    /**
     * Constructor without any argument. It creates the board array as an empty array.
     */
    public TicTacToe() {
        this.board = new Board(dim, '_');
    }

//    public TicTacToe(String board) {
//        char[][] b = new char[dim][dim];
//        int j = 0;
//        int k = 0;
//        while (j < dim && k < dim) {
//            for (int i = 0; i < board.length(); i++) {
//                char letter = board.charAt(i);
//                if (letter == '0' || letter == 'X') {
//                    b[j][k++] = letter;
//                } else if (letter == '|') {
//                } else if (letter == '\n') {
//                    j++;
//                    k = 0;
//                } else {
//                    b[j][k++] = '_';
//                }
//            }
//        }
//        this.board = b;
//        setCurrentPlayer();
//    }


    public Board getBoard() {
        return this.board;
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
    public TicTacToe(String row1, String row2, String row3) throws IllegalStateException, TicTacToeException {

        //Split each argument into characters
        String[] row1Splitted = row1.split("");
        String[] row2Splitted = row2.split("");
        String[] row3Splitted = row3.split("");

        //Throw exception in case the board is incomplete
        if (row1Splitted.length != dim || row2Splitted.length != dim || row3Splitted.length != dim) {
            throw new TicTacToeException("Invalid arg in TicTacToe constructor");
        }

        this.board = new Board(dim, row1Splitted, row2Splitted, row3Splitted);

        //Set current player
        setCurrentPlayer();
    }


    /**
     * String representation of the tic tac toe board. Where "_" indicates a blank space.
     *
     * @return String
     */
    public String toString() {
        return board.toString();
    }


    @Override
    public int hashCode() {
        return Arrays.hashCode(board.getBoard());
    }


    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public char getOpeningPiece() {
        return openingPiece;
    }

    /**
     * Verifies if an object is a TicTacToe and if so, then verifies if two TicTacToe objects are equal,
     * that's if all their positions hold the same values.
     *
     * @param o other object to be compared
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicTacToe board = (TicTacToe) o;
        if (!this.getBoard().equals(board.getBoard()))
            return false;
        if (this.getCurrentPlayer() != board.getCurrentPlayer() || this.getOpeningPiece() != board.getOpeningPiece())
            return false;
        return true;
    }

    /**
     * Determines who's the next player to make a move, according to the current TicTacToe, and sets the object variable.
     */
    //Melhorar um bcd esta
    private void setCurrentPlayer() {
        int playerXmoves = 0;
        int player0moves = 0;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (this.board.getBoard()[i][j] == 'X')
                    playerXmoves++;
                else if (this.board.getBoard()[i][j] == '0')
                    player0moves++;
            }
        }
        if (playerXmoves == player0moves) {
            this.currentPlayer = openingPiece;
        } else if (playerXmoves > player0moves) {
            this.currentPlayer = '0';
        } else {
            this.currentPlayer = 'X';
        }
    }

    public void setOpeningPiece(char openingPiece) throws TicTacToeException {
        if (openingPiece == 'X' || openingPiece == '0') {
            TicTacToe.openingPiece = openingPiece;
            setCurrentPlayer();
        } else {
            throw new TicTacToeException("Wrong piece selected, select either 0 or X.");

        }
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

        List<Coordinate> availablePositions = this.getEmptyPositions();
        for (Coordinate c : availablePositions) {
            TicTacToe b = (TicTacToe) this.clone();
            try {
                b.placeMove(c, nextPiece);
            } catch (TicTacToeException e) {
                System.out.println(e.toString());
                System.exit(0);
            }
            children.add(b);


        }
        return children;
    }

    @Override
    public List<Coordinate> getEmptyPositions() {
        return this.board.getCharPositions('_');
    }


    /**
     * Creates a deepcopy of TicTacToe object.
     *
     * @return deep copy of this.board.
     * @throws CloneNotSupportedException
     */
    protected Object clone() throws CloneNotSupportedException {

        TicTacToe ticTacToeCopy = (TicTacToe) super.clone();

        ticTacToeCopy.board = (Board) this.board.clone();


//        System.out.println("Original is"+ this);
//        System.out.println("Clone is" + ticTacToeCopy);
//        ticTacToeCopy.getBoard().getBoard()[0][2] = '0';
//        System.out.println("Original is"+ this);
//        System.exit(0);


        ticTacToeCopy.setCurrentPlayer();
        try {
            ticTacToeCopy.setOpeningPiece(this.getOpeningPiece());
        } catch (TicTacToeException e) {
            System.out.println(e.toString());
        }

        return ticTacToeCopy;
    }


    @Override
    public String getStatus() {
        String result = "in progress";
        if (checkCircles()) {
            result = "0";
        } else if (checkCrosses()) {
            result = "X";
        } else if (checkDraw()) {
            result = "draw";
        }
        return result;
    }


    private boolean checkDraw() {
        boolean result = false;
        if (this.getEmptyPositions().size() == 0)
            result = true;
        return result;
    }

    public boolean checkRows(char piece) {
        boolean result = false;
        for (int i = 0; i < dim; i++) {
            int j = 0;
            int counter = 0;
            while (j < dim && this.board.getBoard()[i][j] == piece) {
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
            while (i < dim && this.board.getBoard()[i][j] == piece) {
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
        while (j < dim && this.board.getBoard()[j][j] == piece) {
            counter++;
            j++;
            if (counter == 3)
                result = true;
        }
        j = 0;
        int i = dim - 1;
        counter = 0;
        while (j < dim && i >= 0 && this.board.getBoard()[i][j] == piece) {
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

    public boolean placeMove(Coordinate c, char piece) throws TicTacToeException {
        boolean result = false;
        try {
            if (this.getBoard().isPositionAvailable(c, '_')) {

                this.getBoard().placePiece(c, piece);

                this.setCurrentPlayer();
                result = true;
            } else {

                throw new TicTacToeException("The selected position is already occupied.");

            }
        } catch (BoardException e) {
            System.out.println(e.toString());
            System.exit(0);
        }

        return result;
    }


}