import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board  implements Ilayout, Cloneable{

    private static final int dim = 3;
    private char[][] board;
    private int openSlots;
    private int status;
    private char currentPlayer;


    public Board(char[][] board) {
        this.board = board;
        setCurrentPlayer();
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

    //Falta a otimização na children
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
     * The possibility to create a deep copy of the Board.
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
