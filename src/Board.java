import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/***
 * Board is the class that represents a board that is divided by rows and columns, resulting in squares.
 * It has the following data members:
 *  - board, a bidimensional char array to represent the board and its squares
 *  - numberRows, an int that represents the number of rows of the board
 *  - numberColumns, an int that represents the number of columns of the board
 */
public class Board implements Cloneable {

    private char[][] board;
    private int numberRows;
    private int numberColumns;


    /***
     * Constructor that it will take the dimension and the filler and create an empty square
     * board with the filler representing the empty squares of the board.
     *
     * @pre dimension must be > 0
     * @param dimension int dimension of the board (dimension x dimension)
     * @param filler char to be inserted on empty squares
     * @pos new Object of Board, this.numberRows and this.numberColumns > 0
     * @throws BoardException if there dimension is <=0
     */
    public Board(int dimension, char filler) throws BoardException {

        setNumberRows(dimension);
        setNumberColumns(dimension);
        setBoard(new char[this.numberRows][this.numberColumns]);

        fillBoardWithChar(filler);
    }


    /**
     * Constructor where a bidimensional char array is passed as an argument, creating a Board object.
     *
     * @param board bidimensional char array
     * @throws BoardException if setNumberRows or setNumberColumns get parameters <= 0
     * @pos new Object of Board, this.numberRows = board.length and this.numberColumns = board[0].length and
     * board equals to the board given as argument
     */
    public Board(char[][] board) throws BoardException {
        setNumberRows(board.length);
        setNumberColumns(board[0].length);
        setBoard(this.board = board);
    }


    /**
     * TESTS-ONLY
     * Create a new board given the rows
     *
     * @param dimension    int dimension of the board (dimension x dimension)
     * @param row1Splitted First row, array of Strings with only the Strings wanted without the separators
     * @param row2Splitted First row, array of Strings with only the Strings wanted without the separators
     * @param row3Splitted First row, array of Strings with only the Strings wanted without the separators
     * @throws BoardException if there dimension is <=0 or if all the arrays of Strings don't have the
     *                        same length
     * @pre dimension must be > 0 row1Splitted, row2Splitted and row3Splitted must have the same size
     * @pos new Object of Board, this.numberRows and this.numberColumns > 0
     */
    public Board(int dimension, String[] row1Splitted, String[] row2Splitted, String[] row3Splitted) throws BoardException {


        setNumberRows(dimension);
        setNumberColumns(dimension);

        if (row1Splitted.length == row2Splitted.length && row2Splitted.length == row3Splitted.length) {
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
        } else {
            throw new BoardException("Arrays of Strings given have different lengths");
        }

    }



    /***
     * Getter for numberRows field
     *
     * @return numberRows
     */
    public int getNumberRows() {
        return numberRows;
    }


    /***
     * Setter for the numberRows
     *
     * @pre nRows > 0
     * @param nRows represents the number of rows of the Board
     * @throws BoardException if nRows <= 0
     * @pos this.numberRows = nRows
     */
    public void setNumberRows(int nRows) throws BoardException {
        if (nRows > 0)
            this.numberRows = nRows;
        else {
            throw new BoardException("Invalid number of rows. Please use a value >= 0.");
        }
    }

    /***
     * Getter for the numberColumns field
     *
     * @return numberColumns
     */
    public int getNumberColumns() {
        return numberColumns;
    }


    /***
     * Setter for the numberColumns
     *
     * @pre nColumns > 0
     * @param nColumns represents the number of columns of the Board
     * @throws BoardException if nColumns <= 0
     * @pos this.numberColumns = nColumns
     */
    public void setNumberColumns(int nColumns) throws BoardException {
        if (nColumns > 0)
            this.numberColumns = nColumns;
        else {
            throw new BoardException("Invalid number of columns. Please use a value >= 0.");
        }
    }

    /***
     * Getter for the numberColumns field
     *
     * @return numberColumns
     */
    public char[][] getBoard() {
        return board;
    }

    /***
     * Setter for the board field
     *
     * @param board char bidimensional array
     * @pos this.board = board
     */
    public void setBoard(char[][] board) {
        this.board = board;
    }

    /***
     * Fills the whole board with the char passed in the argument
     *
     * @param filler char to be inserted in every square of the board
     * @pos this.board filled with filler
     */
    public void fillBoardWithChar(char filler) {
        for (int i = 0; i < this.numberRows; i++) {
            for (int j = 0; j < this.numberColumns; j++) {
                this.board[i][j] = filler;
            }
        }
    }


    /***
     * Get a list of all the positions (as Point objects) of the squares that hold the given element
     * passed as argument
     *
     * @param element char that represents the element
     * @return List of Point objects representing the position of the squares that hold the given element
     */
    public List<Point> getElementPositions(char element) {
        int size = this.board.length;
        List<Point> emptyPositions = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (this.board[i][j] == element)
                    emptyPositions.add(new Point(i, j));
            }
        }
        return emptyPositions;
    }

    /***
     * Places a char on the given Point
     *
     * @pre Point exists in the board
     * @param p Point where the element is going to be placed
     * @param element char to be placed in the board
     * @pos element is placed in
     * @throws BoardException if p does not exist on the board
     */
    public void placeElement(Point p, char element) throws BoardException {
        if (isValidPoint(p)) {
            this.board[p.getX()][p.getY()] = element;
        }
    }

    /***
     * Checks whether the position, represented by a Point object exists on the board
     *
     * @param p Point representing the position on the board
     * @return boolean : true if p exists on the board
     * @throws BoardException if p does not exist on the board
     */
    public boolean isValidPoint(Point p) throws BoardException {
        int row = p.getX();
        int column = p.getY();
        if ((row >= 0 && row < this.getNumberRows()) && (column >= 0 && column < this.getNumberColumns()))
            return true;
        else {
            throw new BoardException("Selected position does not exist on the board.");
        }
    }

    /***
     * Checks if the position, represented by a Point object is available in the board, this means
     * if the the position holds a char equals to char that was defined to represent an empty square
     *
     * @param p Point that represents the position on the board
     * @param openSquareChar char that represents an empty square
     * @return boolean : true if position is available, false if position is not empty
     */
    public boolean isPositionAvailable(Point p, char openSquareChar) throws BoardException {
        boolean result = false;
        if (isValidPoint(p) && this.board[p.getX()][p.getY()] == openSquareChar)
            result = true;
        return result;
    }


    /***
     * Checks if there is a row of the board that is filled with a given piece
     * @param piece char
     * @return boolean : true if there is a row in the board filled with the given piece, false if there is not
     */
    public boolean checkRows(char piece) {
        boolean result = false;
        for (int i = 0; i < numberRows; i++) {
            int j = 0;
            int counter = 0;
            while (j < numberColumns && this.board[i][j] == piece) {
                counter++;
                j++;
                if (counter == numberRows)
                    result = true;
            }
        }
        return result;
    }

    /***
     * Checks if there is a column of the board that is filled with a given piece
     * @param piece char
     * @return boolean : true if there is a column in the board filled with the given piece, false if there is not
     */
    public boolean checkColumns(char piece) {
        boolean result = false;
        for (int j = 0; j < numberColumns; j++) {
            int i = 0;
            int counter = 0;
            while (i < numberRows && this.board[i][j] == piece) {
                counter++;
                i++;
                if (counter == numberColumns)
                    result = true;
            }
        }
        return result;
    }

    /***
     * Checks if any of both diagonals of the board is filled with a given piece
     * @param piece char
     * @return boolean : true if there is a diagonal in the board filled with the given piece, false if there is not
     */
    public boolean checkDiagonals(char piece) {
        boolean result = false;
        int j = 0;
        int counter = 0;

        // diagonal \
        while (j < numberColumns && this.board[j][j] == piece) {
            counter++;
            j++;
            if (counter == numberRows)
                result = true;
        }
        j = 0;
        int i = numberRows - 1;

        counter = 0;
        // diagonal /
        while (j < numberColumns && i >= 0 && this.board[i][j] == piece) {
            counter++;
            j++;
            i--;
            if (counter == numberColumns)
                result = true;
        }

        return result;
    }


    /**
     * Verifies if an object is a Board and if so, then verifies if two Boards objects are equal,
     * that's if all their positions hold the same values.
     *
     * @param o other object to be compared
     * @return boolean : true if equals, false if not equals
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

    /**
     * Creates a 'deep' copy of Board object.
     *
     * @return deep copy of this.board.
     * @throws CloneNotSupportedException if Board does not implement the Cloneable interface
     */
    protected Object clone() throws CloneNotSupportedException {
        Board boardCopy = (Board) super.clone();
        boardCopy.board = new char[this.numberRows][this.getNumberColumns()];
        try {
            boardCopy.setNumberColumns(this.getNumberColumns());
            boardCopy.setNumberRows(this.getNumberRows());
        } catch (BoardException e) {
            System.out.println(e.toString());
            System.exit(0);
        }

        for (int i = 0; i < this.numberRows; i++) {
            for (int j = 0; j < this.numberColumns; j++) {
                boardCopy.board[i][j] = this.board[i][j];
            }
        }

        return boardCopy;
    }


    /***
     * Returns a hash code value for the object.
     *
     * @return int : hash code
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(numberRows, numberColumns);
        result = 31 * result + Arrays.hashCode(board);
        return result;
    }


    /***
     * The string representation of the object.
     *
     * @return String
     */
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

}
