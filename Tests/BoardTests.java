import org.junit.Assert;
import org.junit.Test;

public class BoardTests {


    @Test
    public void boardConstructor() {
        Board b1 = new Board();
        char[][] b1Board = b1.getBoard();
        char[][] bEmpty = {
                {'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };
        Assert.assertArrayEquals(b1Board, bEmpty);
    }

    @Test
    public void boardContructorWithValue() {
        String row1 = "X__";
        String row2 = "___";
        String row3 = "___";
        Board b1 = new Board(row1, row2, row3);
        char[][] b1Board = b1.getBoard();
        char[][] b1Compare = {
                {'X', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };
        Assert.assertArrayEquals(b1Board, b1Compare);

        row1 = "XXX";
        row2 = "X00";
        row3 = "0X0";
        Board b2 = new Board(row1, row2, row3);
        char[][] b2Board = b2.getBoard();
        char[][] b2Compare = {
                {'X', 'X', 'X'},
                {'X', '0', '0'},
                {'0', 'X', '0'}
        };
        Assert.assertArrayEquals(b2Board, b2Compare);

    }

    @Test
    public void boardToString() {
        String row1 = "X__";
        String row2 = "___";
        String row3 = "___";
        Board b1 = new Board(row1, row2, row3);
        String expected = "X|_|_\n";
        expected = expected.concat("_|_|_\n");
        expected = expected.concat("_|_|_\n");
        String result = b1.toString();
        Assert.assertEquals(expected, result);

        row1 = "X__";
        row2 = "__X";
        row3 = "___";
        Board b2 = new Board(row1, row2, row3);
        String expected2 = "X|_|_\n";
        expected2 = expected2.concat("_|_|_\n");
        expected2 = expected2.concat("_|_|_\n");
        result = b2.toString();
        Assert.assertNotEquals(expected2, result);
    }

}
