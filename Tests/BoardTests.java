//
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class BoardTests {
//
//
//    @Test
//    public void boardConstructor() {
//        TicTacToe b1 = new TicTacToe();
//        char[][] b1Board = b1.getBoard();
//        char[][] bEmpty = {
//                {'_', '_', '_'},
//                {'_', '_', '_'},
//                {'_', '_', '_'}
//        };
//        Assert.assertArrayEquals(b1Board, bEmpty);
//    }
//
//    @Test
//    public void boardContructorWithValue() {
//        String row1 = "X__";
//        String row2 = "___";
//        String row3 = "___";
//        TicTacToe b1 = new TicTacToe(row1, row2, row3);
//        char[][] b1Board = b1.getBoard();
//        char[][] b1Compare = {
//                {'X', '_', '_'},
//                {'_', '_', '_'},
//                {'_', '_', '_'}
//        };
//        Assert.assertArrayEquals(b1Board, b1Compare);
//
//        row1 = "XXX";
//        row2 = "X00";
//        row3 = "0X0";
//        TicTacToe b2 = new TicTacToe(row1, row2, row3);
//        char[][] b2Board = b2.getBoard();
//        char[][] b2Compare = {
//                {'X', 'X', 'X'},
//                {'X', '0', '0'},
//                {'0', 'X', '0'}
//        };
//        Assert.assertArrayEquals(b2Board, b2Compare);
//
//    }
//
//    @Test
//    public void boardToString() {
//        String row1 = "X__";
//        String row2 = "___";
//        String row3 = "___";
//        TicTacToe b1 = new TicTacToe(row1, row2, row3);
//        String expected = "X|_|_\n";
//        expected = expected.concat("_|_|_\n");
//        expected = expected.concat("_|_|_\n");
//        String result = b1.toString();
//        Assert.assertEquals(expected, result);
//
//        row1 = "X__";
//        row2 = "__X";
//        row3 = "___";
//        TicTacToe b2 = new TicTacToe(row1, row2, row3);
//        String expected2 = "X|_|_\n";
//        expected2 = expected2.concat("_|_|_\n");
//        expected2 = expected2.concat("_|_|_\n");
//        result = b2.toString();
//        Assert.assertNotEquals(expected2, result);
//    }
//
//    @Test
//    public void checkRowsTest() {
//        TicTacToe b1 = new TicTacToe("0-X",
//                "0--",
//                "0-X");
//        TicTacToe b2 = new TicTacToe("000", "0X-", "0-X");
//        TicTacToe b3 = new TicTacToe("0X0", "0X-", "000");
//        Assert.assertFalse(b1.checkRows('0'));
//        Assert.assertTrue(b2.checkRows('0'));
//        Assert.assertTrue(b3.checkRows('0'));
//    }
//
//    @Test
//    public void checkColumnsTest() {
//        TicTacToe b1 = new TicTacToe("0-X", "---", "0-X");
//        TicTacToe b2 = new TicTacToe("000", "0X-", "0-X");
//        TicTacToe b3 = new TicTacToe("0X0", "0X-", "000");
//        Assert.assertFalse(b1.checkColumns('0'));
//        Assert.assertTrue(b2.checkColumns('0'));
//        Assert.assertTrue(b3.checkColumns('0'));
//    }
//
//    @Test
//    public void checkDiagsTest() {
//        TicTacToe b1 = new TicTacToe("0-X", "---", "0-X");
//        TicTacToe b2 = new TicTacToe("000", "0X-", "0-X");
//        TicTacToe b3 = new TicTacToe("0X0", "0X-", "000");
//        Assert.assertFalse(b1.checkDiags('0'));
//        Assert.assertFalse(b2.checkDiags('0'));
//        Assert.assertFalse(b3.checkDiags('0'));
//    }
//
//    @Test
//    public void checkDiagsTest2() {
//        TicTacToe b1 = new TicTacToe("0-X", "---", "0-X");
//        TicTacToe b2 = new TicTacToe("000", "0--", "0-X");
//        TicTacToe b3 = new TicTacToe("0X0", "00-", "000");
//        Assert.assertFalse(b1.checkDiags('0'));
//        Assert.assertFalse(b2.checkDiags('0'));
//        Assert.assertTrue(b3.checkDiags('0'));
//    }
//
//    @Test
//    public void checkDiagsTest3() {
//        TicTacToe b1 = new TicTacToe("0-X", "---", "0-X");
//        TicTacToe b2 = new TicTacToe("--0", "00-", "0-X");
//        TicTacToe b3 = new TicTacToe("0X0", "00-", "000");
//        Assert.assertFalse(b1.checkDiags('0'));
//        Assert.assertTrue(b2.checkDiags('0'));
//        Assert.assertTrue(b3.checkDiags('0'));
//    }
//
//
//    @Test
//    public void testSetCurrentPlayer1() {
//        char[][] board = {
//                {'X', '0', '-'},
//                {'-', 'X', '0'},
//                {'X', '-', '-'}
//        };
//        TicTacToe bd = new TicTacToe(board);
//        char obtained = bd.getCurrentPlayer();
//        char expected = '0';
//        Assert.assertEquals(obtained, expected);
//    }
//
//    @Test
//    public void testSetCurrentPlayer2() {
//        char[][] board = {
//                {'-', '-', '-'},
//                {'-', '-', '-'},
//                {'-', '-', '-'}
//        };
//        TicTacToe bd = new TicTacToe(board);
//        char obtained = bd.getCurrentPlayer();
//        char expected = 'X';
//        Assert.assertEquals(obtained, expected);
//    }
//
//    @Test
//    public void testSetCurrentPlayer3() {
//        char[][] board = {
//                {'X', '0', '-'},
//                {'0', 'X', '0'},
//                {'X', '-', '0'}
//        };
//        TicTacToe bd = new TicTacToe(board);
//        char obtained = bd.getCurrentPlayer();
//        char expected = 'X';
//        Assert.assertEquals(obtained, expected);
//    }
//
//
//    @Test
//    public void testEquals1() throws CloneNotSupportedException {
//        char[][] board = {
//                {'X', '0', '-'},
//                {'0', 'X', '0'},
//                {'X', '-', '0'}
//        };
//
//        TicTacToe bd = new TicTacToe(board);
//        Assert.assertEquals(bd, bd);
//    }
//
//    @Test
//    public void testEquals2() throws CloneNotSupportedException {
//        char[][] board1 = {
//                {'X', '0', '-'},
//                {'0', 'X', '0'},
//                {'X', '-', '0'}
//        };
//        char[][] board2 = {
//                {'X', '0', 'X'},
//                {'0', 'X', '0'},
//                {'X', '-', '0'}
//        };
//        TicTacToe bd = new TicTacToe(board1);
//        TicTacToe otherbd = new TicTacToe(board2);
//        Assert.assertNotEquals(bd, otherbd);
//    }
//
//
//    @Test
//    public void testEquals3() throws CloneNotSupportedException {
//        char[][] board1 = {
//                {'X', '0', '-'},
//                {'0', 'X', '0'},
//                {'X', '-', '0'}
//        };
//        char[][] board2 = {
//                {'X', '0', '-'},
//                {'0', 'X', '0'},
//                {'X', '-', '0'}
//        };
//        TicTacToe bd = new TicTacToe(board1);
//        TicTacToe otherbd = new TicTacToe(board2);
//        Assert.assertEquals(bd, otherbd);
//    }
//
//
//    @Test
//    public void testClone() throws CloneNotSupportedException {
//        char[][] board = {
//                {'X', '0', '-'},
//                {'-', 'X', '0'},
//                {'X', '-', '-'}
//        };
//        TicTacToe bd = new TicTacToe(board);
//        TicTacToe clone = (TicTacToe) bd.clone();
//        Assert.assertEquals(bd, clone);
//    }
//
//    @Test
//    public void testClone2() throws CloneNotSupportedException {
//        char[][] board = {
//                {'-', '-', '-'},
//                {'-', '-', '-'},
//                {'-', '-', '-'}
//        };
//        TicTacToe bd = new TicTacToe(board);
//        TicTacToe clone = (TicTacToe) bd.clone();
//        Assert.assertEquals(bd, clone);
//    }
//
//    @Test
//    public void testClone3() throws CloneNotSupportedException {
//        char[][] board = {
//                {'X', '0', '-'},
//                {'0', 'X', '0'},
//                {'X', '-', '0'}
//        };
//        TicTacToe bd = new TicTacToe(board);
//        TicTacToe clone = (TicTacToe) bd.clone();
//        Assert.assertEquals(bd, clone);
//    }
//
//    @Test
//    public void testClone4() throws CloneNotSupportedException {
//        char[][] board = {
//                {'X', '0', '-'},
//                {'0', 'X', '0'},
//                {'X', '-', '0'}
//        };
//
//        TicTacToe bd = new TicTacToe(board);
//        Assert.assertEquals(bd.clone(), bd.clone());
//    }
//
//    @Test
//    public void testChildren1() throws CloneNotSupportedException {
//        char[][] board = {
//                {'X', '0', '-'},
//                {'-', 'X', '0'},
//                {'X', '-', '-'}
//        };
//        TicTacToe bd = new TicTacToe(board);
//
//
//        char[][] child1 = {
//                {'X', '0', '0'},
//                {'-', 'X', '0'},
//                {'X', '-', '-'}
//        };
//        TicTacToe bdchild1 = new TicTacToe(child1);
//
//
//        char[][] child2 = {
//                {'X', '0', '-'},
//                {'0', 'X', '0'},
//                {'X', '-', '-'}
//        };
//        TicTacToe bdchild2 = new TicTacToe(child2);
//
//        char[][] child3 = {
//                {'X', '0', '-'},
//                {'-', 'X', '0'},
//                {'X', '0', '-'}
//        };
//        TicTacToe bdchild3 = new TicTacToe(child3);
//
//
//        char[][] child4 = {
//                {'X', '0', '-'},
//                {'-', 'X', '0'},
//                {'X', '-', '0'}
//        };
//        TicTacToe bdchild4 = new TicTacToe(child4);
//
//        List<Ilayout> expected = new ArrayList<>();
//        expected.add(bdchild1);
//        expected.add(bdchild2);
//        expected.add(bdchild3);
//        expected.add(bdchild4);
//
//
//        List<Ilayout> obtained = bd.children();
//
//        Assert.assertEquals(expected, obtained);
//    }
//
//    @Test
//    public void testChildren2() throws CloneNotSupportedException {
//        char[][] board = {
//                {'X', '0', '-'},
//                {'0', 'X', '0'},
//                {'X', '-', '0'}
//        };
//        TicTacToe bd = new TicTacToe(board);
//
//        char[][] child1 = {
//                {'X', '0', 'X'},
//                {'0', 'X', '0'},
//                {'X', '-', '0'}
//        };
//        TicTacToe bdchild1 = new TicTacToe(child1);
//
//
//        char[][] child2 = {
//                {'X', '0', '-'},
//                {'0', 'X', '0'},
//                {'X', 'X', '0'}
//        };
//        TicTacToe bdchild2 = new TicTacToe(child2);
//
//
//        List<Ilayout> expected = new ArrayList<>();
//        expected.add(bdchild1);
//        expected.add(bdchild2);
//
//
//        List<Ilayout> obtained = bd.children();
//
//        Assert.assertEquals(expected, obtained);
//    }
//
//    @Test
//    public void testChildren3() throws CloneNotSupportedException {
//        char[][] board = {
//                {'-', '-', '-'},
//                {'-', '-', '-'},
//                {'-', '-', '-'}
//        };
//        TicTacToe bd = new TicTacToe(board);
//
//        char[][] child1 = {
//                {'X', '-', '-'},
//                {'-', '-', '-'},
//                {'-', '-', '-'}
//        };
//        TicTacToe bdchild1 = new TicTacToe(child1);
//
//
//        char[][] child2 = {
//                {'-', 'X', '-'},
//                {'-', '-', '-'},
//                {'-', '-', '-'}
//        };
//        TicTacToe bdchild2 = new TicTacToe(child2);
//
//
//        char[][] child3 = {
//                {'-', '-', 'X'},
//                {'-', '-', '-'},
//                {'-', '-', '-'}
//        };
//        TicTacToe bdchild3 = new TicTacToe(child3);
//
//
//        char[][] child4 = {
//                {'-', '-', '-'},
//                {'X', '-', '-'},
//                {'-', '-', '-'}
//        };
//        TicTacToe bdchild4 = new TicTacToe(child4);
//
//        char[][] child5 = {
//                {'-', '-', '-'},
//                {'-', 'X', '-'},
//                {'-', '-', '-'}
//        };
//        TicTacToe bdchild5 = new TicTacToe(child5);
//
//        char[][] child6 = {
//                {'-', '-', '-'},
//                {'-', '-', 'X'},
//                {'-', '-', '-'}
//        };
//        TicTacToe bdchild6 = new TicTacToe(child6);
//
//        char[][] child7 = {
//                {'-', '-', '-'},
//                {'-', '-', '-'},
//                {'X', '-', '-'}
//        };
//        TicTacToe bdchild7 = new TicTacToe(child7);
//
//        char[][] child8 = {
//                {'-', '-', '-'},
//                {'-', '-', '-'},
//                {'-', 'X', '-'}
//        };
//        TicTacToe bdchild8 = new TicTacToe(child8);
//
//        char[][] child9 = {
//                {'-', '-', '-'},
//                {'-', '-', '-'},
//                {'-', '-', 'X'}
//        };
//        TicTacToe bdchild9 = new TicTacToe(child9);
//
//
//        List<Ilayout> expected = new ArrayList<>();
//        expected.add(bdchild1);
//        expected.add(bdchild2);
//        expected.add(bdchild3);
//        expected.add(bdchild4);
//        expected.add(bdchild5);
//        expected.add(bdchild6);
//        expected.add(bdchild7);
//        expected.add(bdchild8);
//        expected.add(bdchild9);
//
//
//        List<Ilayout> obtained = bd.children();
//
//        Assert.assertEquals(expected, obtained);
//    }
//
//}