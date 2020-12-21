import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board  implements Ilayout, Cloneable{

    private static final int dim = 3;
    private char[][] board;
    private int openSlots;
    private int status;
    private char currentPlayer;

    /**
     * Constructor where a bidimensional char array is passed as an argument, creating a Board object.
     */
    public Board(char[][] board) {
        this.board = board;
        setCurrentPlayer();
    }


    /**
     * Calculates the hashcode of a Board object.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(board);
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
     *
     *
     */
    private void setCurrentPlayer()
    {
        int playerXmoves = 0;
        int player0moves = 0;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if(this.board[i][j] == 'X')
                    playerXmoves++;
                else if(this.board[i][j] == '0')
                    player0moves++;
            }

        }
        if(playerXmoves > player0moves) this.currentPlayer = '0';
        else this.currentPlayer = 'X';

    }


    @Override
    public int getTurn() {
        return 0;
    }

    /** Missing optimization
     * Computes a List containing the boards with all the possible moves for the current player.
     *
     * @return children of this board.
     * @throws CloneNotSupportedException in case it cannot create a new board to hold the new positions.
     */
    @Override
    public List<Ilayout> children() throws CloneNotSupportedException {

        List<Ilayout> children = new ArrayList<>();
        char nextPiece;

        if(this.currentPlayer == '0') nextPiece = '0';
        else nextPiece = 'X';

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if(this.board[i][j] == '-')
                {
                    Board boardCopy =(Board) this.clone();
                    boardCopy.board[i][j] = nextPiece;
                    children.add(boardCopy);
                }
            }
        }

        return children;

    }

    @Override
    public int getStatus() {
        return 0;
    }

    /**
     * Creates a deepcopy of Board object.
     *
     * @return deep copy of this.board.
     * @throws CloneNotSupportedException
     */
    protected Object clone() throws CloneNotSupportedException {
        Board boardCopy =  (Board) super.clone();
        boardCopy.board = new char[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                boardCopy.board[i][j] = this.board[i][j];
            }
        }
        return boardCopy;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }
}
