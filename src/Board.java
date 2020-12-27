import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board implements Cloneable {

    private int numberRows;
    private int numberColumns;
    private char[][] board;


    /**
     * Constructor without any argument. It creates the board array as an empty array.
     */
    public Board(int dimension, char filler) {

        this.numberRows = dimension;
        this.numberColumns = dimension;

        char[][] b = new char[this.numberRows][this.numberColumns];

        this.board = b;

        fillBoardWithChar(filler);
    }

    /**
     * Create a new board given the rows of the game
     *
     * @param row1Splitted First row of tic tac toe game
     * @param row2Splitted First row of tic tac toe game
     * @param row3Splitted First row of tic tac toe game
     * @throws IllegalStateException in case the Java application is not in an appropriate state for the requested
     *                               operation.
     */
    public Board(int dimension, String[] row1Splitted, String[] row2Splitted, String[] row3Splitted) throws IllegalStateException {


        this.numberRows = dimension;
        this.numberColumns = dimension;

        //New board
        board = new char[this.numberRows][this.numberColumns];
        int result = 0;
        //Fill the first row
        for (int i = result; i < this.numberColumns; i++) {
            board[0][i] = row1Splitted[i].charAt(0);
        }
        //Fill the second row
        for (int i = result; i < this.numberColumns; i++) {
            board[1][i] = row2Splitted[i].charAt(0);
        }
        //Fill the third row
        for (int i = result; i < this.numberColumns; i++) {
            board[2][i] = row3Splitted[i].charAt(0);
        }

    }

    /**
     * Constructor where a bidimensional char array is passed as an argument, creating a Board object.
     */
    public Board(char[][] board) {
        this.numberRows = board.length;
        this.numberColumns = board[0].length;
        this.board = board;
    }


    public int getNumberRows() {
        return numberRows;
    }

    public void setNumberRows(int numberRows) {
        if (numberRows >= 0)
            this.numberRows = numberRows;
        else {
            //throw exception
        }
    }

    public int getNumberColumns() {
        return numberColumns;
    }

    public void setNumberColumns(int numberColumns) {
        if (numberColumns >= 0)
            this.numberColumns = numberColumns;
        else {
            //throw exception
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    //Responsability of Tictactoe
    public void fillBoardWithChar(char filler) {
        for (int i = 0; i < this.numberRows; i++) {
            for (int j = 0; j < this.numberColumns; j++) {
                this.board[i][j] = filler;
            }
        }
    }


    public List<Coordinate> getCharPositions(char piece) {
        int size = this.board.length;
        List<Coordinate> emptyPositions = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (this.board[i][j] == piece)
                    emptyPositions.add(new Coordinate(i, j));
            }
        }
        return emptyPositions;
    }

    public boolean placePiece(Coordinate c, char piece) {
        boolean result = false;
        if (isValidCoordinate(c)) {
            this.board[c.getX()][c.getY()] = piece;
            result = true;
        } else {
            System.out.println("Illegal move, selected position is already occupied or it doesn't exists in the board");
            //Board says no
            //violação é feita aqui, não no tictactoe
//            System.out.println("Illegal move, selected move is already occupied or it doesn't exists in the board");
        }
        return result;
    }

    private boolean isValidCoordinate(Coordinate c) {
        boolean result = false;
        int row = c.getX();
        int column = c.getY();
//        System.out.println("this is row" + row + "and column " + column  );
        if ((row >= 0 && row < this.getNumberRows()) || (column >= 0 && column < this.getNumberColumns()))
            result = true;
        return result;
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
        Board board = (Board) o;
        for (int i = 0; i < this.getNumberRows(); i++) {
            for (int j = 0; j < this.getNumberColumns(); j++) {
                if (board.getBoard()[i][j] != this.getBoard()[i][j])
                    return false;
            }
        }
        return true;
    }

    protected Object clone() throws CloneNotSupportedException {
        Board boardCopy = (Board) super.clone();
        boardCopy.board = new char[this.numberRows][this.getNumberColumns()];
        boardCopy.setNumberColumns(this.getNumberColumns());
        boardCopy.setNumberRows(this.getNumberRows());
        for (int i = 0; i < this.numberRows; i++) {
            for (int j = 0; j < this.numberColumns; j++) {
                boardCopy.board[i][j] = this.board[i][j];
            }
        }

        return boardCopy;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < this.numberRows; i++) {
            for (int j = 0; j < this.numberColumns; j++) {
                if (j == this.numberColumns - 1)
                    result = result.concat(String.valueOf(this.board[i][j]));
                else
                    result = result.concat(String.valueOf(this.board[i][j])) + "|";
            }
            result = result.concat("\n");
        }

        return result;
    }


    public boolean isPositionAvailable(Coordinate c, char openSlotChar) {
        boolean result = false;
        if (this.board[c.getX()][c.getY()] == openSlotChar)
            result = true;
        return result;
    }

}
