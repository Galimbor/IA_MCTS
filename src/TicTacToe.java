import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * TicTacToe class represents the board game of TicTacToe, it is represented by an object Board and some more data
 * members that are relevant for the game, such as:
 *  - DIM : dimension that the board (object of class Board) has to have to play this game, this value is a global
 *  constant set to 3
 *  - EMPTY_FILLER : char used to fill the empty positions of the Board
 *  - board : object of class Board
 *  - currentPlayer : a char that represents the player to make the next move
 *  - openingPiece : a char that represents the piece that will make the first move
 */
public class TicTacToe implements IBoardGame, Cloneable {

    private static final int DIM = 3;
    private static final char EMPTY_FILLER = '_';
    private Board board;
    private char currentPlayer;
    private char openingPiece;


    /***
     * Constructor without any argument. It creates the board array filled with a char that represents an empty positions
     */
    public TicTacToe() {
        try {
            this.board = new Board(DIM, EMPTY_FILLER);
        } catch (BoardException e) {
            System.out.println(e.toString());
            System.exit(0);
        }
    }


    /**
     * Constructor with the opening piece as arguments. It creates a Board object filled with empty positions
     *
     * @param openingPiece Piece selected to start the game
     * @throws TicTacToeException in case the give opening piece is not valid for the TicTacToe game.
     */
    public TicTacToe(char openingPiece) throws TicTacToeException, BoardException {
        this.board = new Board(DIM, EMPTY_FILLER);
        setOpeningPiece(openingPiece);
    }


    /***
     * TESTS-ONLY
     * Constructor where a non empty bidimensional char array is passed as an argument, creating a TicTacToe object.
     *
     * @param board char[][] that has already some pieces on it
     * @throws BoardException if pieces on the board are invalid
     */
    public TicTacToe(char[][] board) throws BoardException {
        this.board = new Board(board);
        setCurrentPlayer();
    }


    /**
     * TESTS-ONLY
     * Create a new board given the rows of the game
     *
     * @param row1 First row of tic tac toe game
     * @param row2 First row of tic tac toe game
     * @param row3 First row of tic tac toe game
     * @throws IllegalStateException in case the Java application is not in an appropriate state for the requested
     *                               operation.
     * @throws TicTacToeException    if there is a valid piece in the the parameters given
     */
    public TicTacToe(String row1, String row2, String row3) throws IllegalStateException, TicTacToeException {

        String[] row1Splitted = row1.split("");
        String[] row2Splitted = row2.split("");
        String[] row3Splitted = row3.split("");

        if (row1Splitted.length != DIM || row2Splitted.length != DIM || row3Splitted.length != DIM) {
            throw new TicTacToeException("Invalid arg in TicTacToe constructor");
        }
        try {
            this.board = new Board(DIM, row1Splitted, row2Splitted, row3Splitted);
        } catch (BoardException e) {
            System.out.println(e.toString());
            System.exit(0);
        }
        setCurrentPlayer();
    }


    /***
     * Getter for the DIM field
     *
     * @return int : dimension that the board (object of class Board) has to have to play this game
     */
    public static int getDim() {
        return DIM;
    }


    /***
     * Getter for the board field
     *
     * @return Board object
     */
    public Board getBoard() {
        return this.board;
    }


    /***
     * Getter for currentPlayer field
     *
     * @return char currentPlayer : the player to make the next move
     */
    public char getCurrentPlayer() {
        return currentPlayer;
    }


    /***
     * Getter for the openingPiece field
     *
     * @return char openingPiece : the piece that will make the first move
     */
    public char getOpeningPiece() {
        return openingPiece;
    }


    /***
     * Setter for the openingPiece field
     * @param openingPiece char : piece that will make the first move
     * @pre openingPiece == 'X' || openingPiece == '0'
     * @post sets the openingPiece field if no exceptions are thrown
     * @throws TicTacToeException if the parameter is not 'X' or '0'
     */
    public void setOpeningPiece(char openingPiece) throws TicTacToeException {
        if (openingPiece == 'X' || openingPiece == '0') {
            this.openingPiece = openingPiece;
            setCurrentPlayer();
        } else {
            throw new TicTacToeException("Wrong piece selected, select either 0 or X.");

        }
    }


    /***
     * Setter for the field currentPlayer, determines who's the next player to make a move, according to the
     * current TicTacToe, and sets the object variable.
     * @post currentPlayer is not null
     */
    public void setCurrentPlayer() {
        int playerXmoves = 0;
        int player0moves = 0;
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
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


    /**
     * Computes and returns a List containing the TicTacToe games with the respective boards with all the possible moves
     * for the current player. It has a tweak which consists of when the TicTacToe has the board empty it only creates
     * 3 children, one with the piece in a corner, one with the piece in the midle of the board and another with the piece
     * in the middle of an outer row/column.
     *
     * @return List of children of this TicTacToe
     * @throws CloneNotSupportedException if TicTacToe does not implement the Cloneable interface
     */
    @Override
    public List<IBoardGame> children() throws CloneNotSupportedException {
        List<IBoardGame> children = new ArrayList<>();

        char nextPiece;

        if (this.currentPlayer == 'X') nextPiece = 'X';
        else nextPiece = '0';

        List<Point> availablePositions = this.getEmptyPositions();
        if (this.getEmptyPositions().size() == 9) {
            try {
                TicTacToe b1 = (TicTacToe) this.clone();
                // Point(0,0) -> top left corner
                b1.placeMove(availablePositions.get(0), nextPiece);
                children.add(b1);
                b1 = (TicTacToe) this.clone();
                // Point(0,1) -> middle of top row
                b1.placeMove(availablePositions.get(1), nextPiece);
                children.add(b1);
                b1 = (TicTacToe) this.clone();
                // Point(1,1) -> middle of the board
                b1.placeMove(availablePositions.get(4), nextPiece);
                children.add(b1);
            } catch (TicTacToeException e) {
                e.printStackTrace();
            }
        } else {
            for (Point c : availablePositions) {
                TicTacToe b = (TicTacToe) this.clone();
                try {
                    b.placeMove(c, nextPiece);

                } catch (TicTacToeException e) {
                    System.out.println(e.toString());
                    System.exit(0);
                }
                children.add(b);
            }
        }
        return children;
    }


    /***
     * Gets a list of Point objects that represents the empty positions
     * in the board game by calling the method from Board getElementPositions() with the char EMPTY_FILLER, which
     * represents an empty position
     *
     * @return List<Point> : the empty positions of the board
     */
    @Override
    public List<Point> getEmptyPositions() {
        return this.board.getElementPositions(EMPTY_FILLER);
    }


    /***
     * Gets the status of the board game : 'in progress' if the game is not over yet, 'draw' if there's a draw, '0' if
     * circles win and 'X' if crosses win
     *
     * @return String: the game state
     */
    @Override
    public String getStatus() {
        String result = "in progress";
        if (checkWin('0')) {
            result = "0";
        } else if (checkWin('X')) {
            result = "X";
        } else if (checkDraw()) {
            result = "draw";
        }
        return result;
    }


    /***
     * checkDraw checks if there is any empty position available in the game, if there is not, it means its a draw
     *
     * @return boolean : true if draw, false if not a draw
     */
    private boolean checkDraw() {
        boolean result = false;
        if (this.getEmptyPositions().size() == 0)
            result = true;
        return result;
    }


    /***
     * Checks if the given piece has won
     *
     * @param piece char
     * @return boolean : true if it is a win, false if it is not a win
     */
    private boolean checkWin(char piece) {
        boolean result = false;
        if (this.board.checkRows(piece) || this.board.checkDiagonals(piece) || this.board.checkColumns(piece))
            result = true;
        return result;
    }


    /***
     * Places a given char piece on the board of the game in the position given as parameter
     *
     * @param p Point object that represents the position on the board
     * @param piece char to be place on the board
     * @pre p exists on the board, piece == 'X' || piece == '0'
     * @post piece is placed on the board
     * @throws TicTacToeException if the selected position is already occupied or the piece entered is not valid
     */
    public void placeMove(Point p, char piece) throws TicTacToeException {
        try {
            if ((piece == 'X' || piece == '0')) {
                if (this.getBoard().isPositionAvailable(p, EMPTY_FILLER)) {
                    this.getBoard().placeElement(p, piece);
                    this.setCurrentPlayer();
                } else {
                    throw new TicTacToeException("The selected position is already occupied.");
                }
            } else {
                throw new TicTacToeException("The inserted piece is not valid, please choose X or 0");
            }
        } catch (BoardException e) {
            System.out.println(e.toString());
            System.exit(0);
        }
    }


    /**
     * Creates a 'deep' copy of TicTacToe object.
     *
     * @return deep copy of the object
     * @throws CloneNotSupportedException if
     */
    protected Object clone() throws CloneNotSupportedException {

        TicTacToe ticTacToeCopy = (TicTacToe) super.clone();

        ticTacToeCopy.board = (Board) this.board.clone();
        ticTacToeCopy.setCurrentPlayer();
        try {
            ticTacToeCopy.setOpeningPiece(this.getOpeningPiece());
        } catch (TicTacToeException e) {
            System.out.println(e.toString());
        }
        return ticTacToeCopy;
    }

    /**
     * Verifies if an object is a TicTacToe and if so, then verifies if two TicTacToe objects are equal, and their
     * boards are equal, if so checks if the others fields are all equal
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
        return this.getCurrentPlayer() == board.getCurrentPlayer() && this.getOpeningPiece() == board.getOpeningPiece();
    }


    /***
     * Returns a hash code value for the object.
     *
     * @return int : hash code
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(board.getBoard());
    }

    /**
     * String representation of the tic tac toe board. Where "_" indicates a blank space.
     *
     * @return String
     */
    public String toString() {
        return board.toString();
    }


    /*********************** | Heuristic | *************************




     /***
     * Checks both diagonals of the game board if it is possible to win from that diagonal
     * @param piece char
     * @return the number of diagonals still open to win with the piece given
     */
    public int checkOpenDiag(char piece) {
        int result = 0;
        int j = 0;
        int counter = 0;
        int pieceCounter = 0;
        while (j < DIM && (this.board.getBoard()[j][j] == piece || this.board.getBoard()[j][j] == EMPTY_FILLER)) {
            if (this.board.getBoard()[j][j] == piece) {
                pieceCounter++;
            }
            counter++;
            j++;
            if (counter == 3)
                result++;
        }
        if (pieceCounter == 3)
            result++;
        j = 0;
        int i = DIM - 1;
        counter = 0;
        pieceCounter = 0;
        while (j < DIM && i >= 0 && (this.board.getBoard()[i][j] == piece || this.board.getBoard()[i][j] == EMPTY_FILLER)) {
            if (this.board.getBoard()[i][j] == piece) {
                pieceCounter++;
            }
            counter++;
            j++;
            i--;
            if (counter == 3)
                result++;
        }

        if (pieceCounter == 3)
            result++;
        return result;
    }


    /***
     * Checks all columns of the board if it is possible to win from a certain column
     * @param piece char
     * @return the number of columns still open to win with the piece given
     */
    public int checkOpenColumns(char piece) {
        int result = 0;
        for (int j = 0; j < DIM; j++) {
            int i = 0;
            int counter = 0;
            int pieceCounter = 0;
            while (i < DIM && (this.board.getBoard()[i][j] == piece || this.board.getBoard()[i][j] == EMPTY_FILLER)) {
                if (this.board.getBoard()[i][j] == piece)
                    pieceCounter++;
                counter++;
                i++;
                if (counter == 3)
                    result++;
            }
            if (pieceCounter == 3)
                result++;
        }
        return result;
    }


    /***
     * Checks all rows of the board if it is possible to win from a certain row
     * @param piece char
     * @return the number of rows still open to win with the piece given
     */
    public int checkOpenRows(char piece) {
        int result = 0;
        for (int i = 0; i < DIM; i++) {
            int j = 0;
            int counter = 0;
            int pieceCounter = 0;
            while (j < DIM && (this.board.getBoard()[i][j] == piece || this.board.getBoard()[i][j] == EMPTY_FILLER)) {
                if (this.board.getBoard()[i][j] == piece) {
                    pieceCounter++;
                }
                counter++;
                j++;
                if (counter == 3)
                    result++;
            }
            if (pieceCounter == 3)
                result++;
        }
        return result;
    }

    /***
     * Checks all rows of the board if it is possible to win from a certain row and increments the result
     * if there is a row open with 2 pieces, meaning that a victory for this player will happen in the next
     * move
     * @param piece char
     * @return the number of rows still open to win with the piece given
     */
    public int checkOpenRowsOpponent(char piece) {
        int result = 0;
        int pieceCounter = 0;
        for (int i = 0; i < DIM; i++) {
            int j = 0;
            int counter = 0;
            while (j < DIM && (this.board.getBoard()[i][j] == piece || this.board.getBoard()[i][j] == EMPTY_FILLER)) {
                if (this.board.getBoard()[i][j] == piece)
                    pieceCounter++;
                counter++;
                j++;
                if (counter == 3) {
                    if (pieceCounter == 2)
                        result++;
                    result++;
                }
            }
        }
        return result;
    }


    /***
     * Checks all columns of the board if it is possible to win from a certain column and increments the result
     * if there is a column open with 2 pieces, meaning that a victory for this player will happen in the next
     * move
     * @param piece char
     * @return the number of columns still open to win with the piece given
     */
    public int checkOpenColumnsOpponent(char piece) {
        int result = 0;
        int pieceCounter = 0;
        for (int j = 0; j < DIM; j++) {
            int i = 0;
            int counter = 0;
            while (i < DIM && (this.board.getBoard()[i][j] == piece || this.board.getBoard()[i][j] == EMPTY_FILLER)) {
                if (this.board.getBoard()[i][j] == piece)
                    pieceCounter++;
                counter++;
                i++;
                if (counter == 3)
                    result++;
                if (pieceCounter == 2)
                    result++;
            }
        }
        return result;
    }


    /***
     * Checks both diagonals of the game board if it is possible to win from that diagonal and increments the
     * result if there is a diagonal open with 2 pieces, meaning that a victory for this player will happen in the next
     * move
     * @param piece char
     * @return the number of diagonals still open to win with the piece given
     */
    public int checkOpenDiagOpponent(char piece) {
        int result = 0;
        int j = 0;
        int counter = 0;
        int pieceCounter = 0;
        while (j < DIM && (this.board.getBoard()[j][j] == piece || this.board.getBoard()[j][j] == EMPTY_FILLER)) {
            if (this.board.getBoard()[j][j] == piece)
                pieceCounter++;
            counter++;
            j++;
            if (counter == 3) {
                result++;
                if (pieceCounter == 2)
                    result++;
            }
        }

        j = 0;
        int i = DIM - 1;
        counter = 0;
        pieceCounter = 0;
        while (j < DIM && i >= 0 && (this.board.getBoard()[i][j] == piece || this.board.getBoard()[i][j] == EMPTY_FILLER)) {
            if (this.board.getBoard()[i][j] == piece) {
                pieceCounter++;
            }
            counter++;
            j++;
            i--;
            if (counter == 3) {
                result++;
                if (pieceCounter == 2)
                    result++;
            }
        }

        return result;
    }

    /***
     * Sums the total number of open rows, columns and diagonals and returns the sum.
     *
     * @param piece char
     * @return int which represents the number of open rows, columns and diagonals of the TicTacToe board.
     */
    public int checkOpenings(char piece) {
        int result = 0;
        result += checkOpenRows(piece);
        result += checkOpenColumns(piece);
        result += checkOpenDiag(piece);
        return result;
    }


    /***
     * Sums the total number of open rows, columns and diagonals and returns the sum.
     *
     * @param piece char
     * @return int which represents the number of open rows, columns and diagonals of the TicTacToe board.
     */
    public int checkOpeningsOpponent(char piece) {
        int result = 0;
        result += checkOpenRowsOpponent(piece);
        result += checkOpenColumnsOpponent(piece);
        result += checkOpenDiagOpponent(piece);
        return result;
    }


    /***
     * Gets a value of an heuristic used to perform better in the game. This heuristic specifically
     * consists on evaluating the game on the following formulae:
     * (Total number of open rows, columns and diagonals for the current player) -
     * (Total number of open rows, columns and diagonals for the opponent)
     *
     * @return int : the value of the calculated heuristic
     */
    @Override
    public int getH() {
        int result;
        char lastPlayer = getCurrentPlayer() == 'X' ? 'Y' : 'X';

        if (lastPlayer == 'X') {
            result = checkOpenings('X') - checkOpeningsOpponent('0');
        } else {
            result = checkOpenings('0') - checkOpeningsOpponent('X');
        }

        return result;
    }
}