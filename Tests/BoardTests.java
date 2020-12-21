
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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


    @Test
    public void testSetCurrentPlayer1() {
        char[][] board = {
                {'X', '0', '-'},
                {'-', 'X', '0'},
                {'X', '-', '-'}
        };
        Board bd = new Board(board);
        char obtained = bd.getCurrentPlayer();
        char expected = '0';
        Assert.assertEquals(obtained, expected);
    }

    @Test
    public void testSetCurrentPlayer2() {
        char[][] board = {
                {'-', '-', '-'},
                {'-', '-', '-'},
                {'-', '-', '-'}
        };
        Board bd = new Board(board);
        char obtained = bd.getCurrentPlayer();
        char expected = 'X';
        Assert.assertEquals(obtained, expected);
    }

    @Test
    public void testSetCurrentPlayer3() {
        char[][] board = {
                {'X', '0', '-'},
                {'0', 'X', '0'},
                {'X', '-', '0'}
        };
        Board bd = new Board(board);
        char obtained = bd.getCurrentPlayer();
        char expected = 'X';
        Assert.assertEquals(obtained, expected);
    }


    @Test
    public void testEquals1() throws CloneNotSupportedException {
        char[][] board = {
                {'X', '0', '-'},
                {'0', 'X', '0'},
                {'X', '-', '0'}
        };

        Board bd = new Board(board);
        Assert.assertEquals(bd, bd);
    }

    @Test
    public void testEquals2() throws CloneNotSupportedException {
        char[][] board1 = {
                {'X', '0', '-'},
                {'0', 'X', '0'},
                {'X', '-', '0'}
        };
        char[][] board2 = {
                {'X', '0', 'X'},
                {'0', 'X', '0'},
                {'X', '-', '0'}
        };
        Board bd = new Board(board1);
        Board otherbd = new Board(board2);
        Assert.assertNotEquals(bd, otherbd);
    }


    @Test
    public void testEquals3() throws CloneNotSupportedException {
        char[][] board1 = {
                {'X', '0', '-'},
                {'0', 'X', '0'},
                {'X', '-', '0'}
        };
        char[][] board2 = {
                {'X', '0', '-'},
                {'0', 'X', '0'},
                {'X', '-', '0'}
        };
        Board bd = new Board(board1);
        Board otherbd = new Board(board2);
        Assert.assertEquals(bd, otherbd);
    }


    @Test
    public void testClone() throws CloneNotSupportedException {
        char[][] board = {
                {'X', '0', '-'},
                {'-', 'X', '0'},
                {'X', '-', '-'}
        };
        Board bd = new Board(board);
        Board clone = (Board) bd.clone();
        Assert.assertEquals(bd, clone);
    }

    @Test
    public void testClone2() throws CloneNotSupportedException {
        char[][] board = {
                {'-', '-', '-'},
                {'-', '-', '-'},
                {'-', '-', '-'}
        };
        Board bd = new Board(board);
        Board clone = (Board) bd.clone();
        Assert.assertEquals(bd, clone);
    }

    @Test
    public void testClone3() throws CloneNotSupportedException {
        char[][] board = {
                {'X', '0', '-'},
                {'0', 'X', '0'},
                {'X', '-', '0'}
        };
        Board bd = new Board(board);
        Board clone = (Board) bd.clone();
        Assert.assertEquals(bd, clone);
    }

    @Test
    public void testClone4() throws CloneNotSupportedException {
        char[][] board = {
                {'X', '0', '-'},
                {'0', 'X', '0'},
                {'X', '-', '0'}
        };

        Board bd = new Board(board);
        Assert.assertEquals(bd.clone(), bd.clone());
    }

    @Test
    public void testChildren1() throws CloneNotSupportedException {
        char[][] board = {
                {'X', '0', '-'},
                {'-', 'X', '0'},
                {'X', '-', '-'}
        };
        Board bd = new Board(board);


        char[][] child1 = {
                {'X', '0', '0'},
                {'-', 'X', '0'},
                {'X', '-', '-'}
        };
        Board bdchild1 = new Board(child1);


        char[][] child2 = {
                {'X', '0', '-'},
                {'0', 'X', '0'},
                {'X', '-', '-'}
        };
        Board bdchild2 = new Board(child2);

        char[][] child3 = {
                {'X', '0', '-'},
                {'-', 'X', '0'},
                {'X', '0', '-'}
        };
        Board bdchild3 = new Board(child3);


        char[][] child4 = {
                {'X', '0', '-'},
                {'-', 'X', '0'},
                {'X', '-', '0'}
        };
        Board bdchild4 = new Board(child4);

        List<Ilayout> expected = new ArrayList<>();
        expected.add(bdchild1);
        expected.add(bdchild2);
        expected.add(bdchild3);
        expected.add(bdchild4);


        List<Ilayout> obtained = bd.children();

        Assert.assertEquals(expected, obtained);
    }

    @Test
    public void testChildren2() throws CloneNotSupportedException {
        char[][] board = {
                {'X', '0', '-'},
                {'0', 'X', '0'},
                {'X', '-', '0'}
        };
        Board bd = new Board(board);

        char[][] child1 = {
                {'X', '0', 'X'},
                {'0', 'X', '0'},
                {'X', '-', '0'}
        };
        Board bdchild1 = new Board(child1);


        char[][] child2 = {
                {'X', '0', '-'},
                {'0', 'X', '0'},
                {'X', 'X', '0'}
        };
        Board bdchild2 = new Board(child2);


        List<Ilayout> expected = new ArrayList<>();
        expected.add(bdchild1);
        expected.add(bdchild2);


        List<Ilayout> obtained = bd.children();

        Assert.assertEquals(expected, obtained);
    }

    @Test
    public void testChildren3() throws CloneNotSupportedException {
        char[][] board = {
                {'-', '-', '-'},
                {'-', '-', '-'},
                {'-', '-', '-'}
        };
        Board bd = new Board(board);

        char[][] child1 = {
                {'X', '-', '-'},
                {'-', '-', '-'},
                {'-', '-', '-'}
        };
        Board bdchild1 = new Board(child1);


        char[][] child2 = {
                {'-', 'X', '-'},
                {'-', '-', '-'},
                {'-', '-', '-'}
        };
        Board bdchild2 = new Board(child2);


        char[][] child3 = {
                {'-', '-', 'X'},
                {'-', '-', '-'},
                {'-', '-', '-'}
        };
        Board bdchild3 = new Board(child3);


        char[][] child4 = {
                {'-', '-', '-'},
                {'X', '-', '-'},
                {'-', '-', '-'}
        };
        Board bdchild4 = new Board(child4);

        char[][] child5 = {
                {'-', '-', '-'},
                {'-', 'X', '-'},
                {'-', '-', '-'}
        };
        Board bdchild5 = new Board(child5);

        char[][] child6 = {
                {'-', '-', '-'},
                {'-', '-', 'X'},
                {'-', '-', '-'}
        };
        Board bdchild6 = new Board(child6);

        char[][] child7 = {
                {'-', '-', '-'},
                {'-', '-', '-'},
                {'X', '-', '-'}
        };
        Board bdchild7 = new Board(child7);

        char[][] child8 = {
                {'-', '-', '-'},
                {'-', '-', '-'},
                {'-', 'X', '-'}
        };
        Board bdchild8 = new Board(child8);

        char[][] child9 = {
                {'-', '-', '-'},
                {'-', '-', '-'},
                {'-', '-', 'X'}
        };
        Board bdchild9 = new Board(child9);


        List<Ilayout> expected = new ArrayList<>();
        expected.add(bdchild1);
        expected.add(bdchild2);
        expected.add(bdchild3);
        expected.add(bdchild4);
        expected.add(bdchild5);
        expected.add(bdchild6);
        expected.add(bdchild7);
        expected.add(bdchild8);
        expected.add(bdchild9);


        List<Ilayout> obtained = bd.children();

        Assert.assertEquals(expected, obtained);
    }

}