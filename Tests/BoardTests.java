
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

    @Test
    public void checkRowsTest() {
        Board b1 = new Board("0-X",
                "0--",
                "0-X");
        Board b2 = new Board("000", "0X-", "0-X");
        Board b3 = new Board("0X0", "0X-", "000");
        Assert.assertFalse(b1.checkRows('0'));
        Assert.assertTrue(b2.checkRows('0'));
        Assert.assertTrue(b3.checkRows('0'));
    }

    @Test
    public void checkColumnsTest() {
        Board b1 = new Board("0-X", "---", "0-X");
        Board b2 = new Board("000", "0X-", "0-X");
        Board b3 = new Board("0X0", "0X-", "000");
        Assert.assertFalse(b1.checkColumns('0'));
        Assert.assertTrue(b2.checkColumns('0'));
        Assert.assertTrue(b3.checkColumns('0'));
    }

    @Test
    public void checkDiagsTest() {
        Board b1 = new Board("0-X", "---", "0-X");
        Board b2 = new Board("000", "0X-", "0-X");
        Board b3 = new Board("0X0", "0X-", "000");
        Assert.assertFalse(b1.checkDiags('0'));
        Assert.assertFalse(b2.checkDiags('0'));
        Assert.assertFalse(b3.checkDiags('0'));
    }

    @Test
    public void checkDiagsTest2() {
        Board b1 = new Board("0-X", "---", "0-X");
        Board b2 = new Board("000", "0--", "0-X");
        Board b3 = new Board("0X0", "00-", "000");
        Assert.assertFalse(b1.checkDiags('0'));
        Assert.assertFalse(b2.checkDiags('0'));
        Assert.assertTrue(b3.checkDiags('0'));
    }

    @Test
    public void checkDiagsTest3() {
        Board b1 = new Board("0-X", "---", "0-X");
        Board b2 = new Board("--0", "00-", "0-X");
        Board b3 = new Board("0X0", "00-", "000");
        Assert.assertFalse(b1.checkDiags('0'));
        Assert.assertTrue(b2.checkDiags('0'));
        Assert.assertTrue(b3.checkDiags('0'));
    }

}
