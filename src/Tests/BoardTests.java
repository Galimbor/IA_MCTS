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

    }



}
