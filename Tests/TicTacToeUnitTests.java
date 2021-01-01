import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeUnitTests {

    @Test
    public void boardConstructor() {
        TicTacToe b1 = new TicTacToe();
        char[][] b1Board = b1.getBoard().getBoard();
        char[][] bEmpty = {
                {'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };
        Assert.assertArrayEquals(b1Board, bEmpty);
    }

    @Test
    public void boardContructorWithValue() throws TicTacToeException {
        String row1 = "X__";
        String row2 = "___";
        String row3 = "___";
        TicTacToe b1 = new TicTacToe(row1, row2, row3);
        char[][] b1Board = b1.getBoard().getBoard();
        char[][] b1Compare = {
                {'X', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };
        Assert.assertArrayEquals(b1Board, b1Compare);

        row1 = "XXX";
        row2 = "X00";
        row3 = "0X0";
        TicTacToe b2 = new TicTacToe(row1, row2, row3);
        char[][] b2Board = b2.getBoard().getBoard();
        char[][] b2Compare = {
                {'X', 'X', 'X'},
                {'X', '0', '0'},
                {'0', 'X', '0'}
        };
        Assert.assertArrayEquals(b2Board, b2Compare);

    }

    @Test(expected = TicTacToeException.class)
    public void boardConstructorWithValue2() throws TicTacToeException {
        String row1 = "X_";
        String row2 = "___";
        String row3 = "___";
        TicTacToe b1 = new TicTacToe(row1, row2, row3);
        char[][] b1Board = b1.getBoard().getBoard();
        char[][] b1Compare = {
                {'X', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };
        Assert.assertArrayEquals(b1Board, b1Compare);

    }


    @Test
    public void testFirstConstructor1() throws BoardException, TicTacToeException {
        TicTacToe obtained = new TicTacToe('X');

        char[][] expected = {
                {'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };

        Assert.assertEquals(expected, obtained.getBoard().getBoard());
        Assert.assertEquals(obtained.getOpeningPiece(), 'X');


    }

    @Test
    public void TestsetCurrentPlayer() throws TicTacToeException {
        String row1 = "X__";
        String row2 = "___";
        String row3 = "___";
        TicTacToe b1 = new TicTacToe(row1, row2, row3);
        b1.setOpeningPiece('X');
        b1.setCurrentPlayer();
        Assert.assertEquals(b1.getCurrentPlayer(), '0');


        String row1_2 = "X0X";
        String row2_2 = "_00";
        String row3_2 = "_X_";
        TicTacToe b2 = new TicTacToe(row1_2, row2_2, row3_2);
        b2.setOpeningPiece('X');
        b2.setCurrentPlayer();
        Assert.assertEquals(b2.getCurrentPlayer(), 'X');


    }


    @Test
    public void TestsetCurrentPlayer2() throws BoardException {
        char[][] board = {
                {'X', '0', '_'},
                {'_', 'X', '0'},
                {'X', '_', '_'}
        };
        TicTacToe bd = new TicTacToe(board);
        char obtained = bd.getCurrentPlayer();
        char expected = '0';
        Assert.assertEquals(obtained, expected);
    }

    @Test
    public void testSetCurrentPlayer2() throws TicTacToeException, BoardException {
        char[][] board = {
                {'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };
        TicTacToe bd = new TicTacToe(board);
        bd.setOpeningPiece('X');
        char obtained = bd.getCurrentPlayer();
        char expected = 'X';
        Assert.assertEquals(obtained, expected);
    }

    @Test
    public void testSetCurrentPlayer3() throws BoardException {
        char[][] board = {
                {'X', '0', '_'},
                {'0', 'X', '0'},
                {'X', '_', '0'}
        };
        TicTacToe bd = new TicTacToe(board);
        char obtained = bd.getCurrentPlayer();
        char expected = 'X';
        Assert.assertEquals(obtained, expected);
    }

    @Test(expected = TicTacToeException.class)
    public void testSetOpeningPiece() throws TicTacToeException, BoardException {
        char[][] board = {
                {'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };
        TicTacToe bd = new TicTacToe(board);
        bd.setOpeningPiece('L');
    }

    @Test()
    public void testSetOpeningPiece2() throws TicTacToeException, BoardException {
        char[][] board = {
                {'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };
        TicTacToe bd = new TicTacToe(board);
        bd.setOpeningPiece('X');
        Assert.assertEquals(bd.getOpeningPiece(), 'X');
    }

    @Test
    public void testChildren1() throws CloneNotSupportedException, TicTacToeException, BoardException {
        char[][] board = {
                {'X', '0', '_'},
                {'_', 'X', '0'},
                {'X', '_', '_'}
        };
        TicTacToe bd = new TicTacToe(board);
        bd.setOpeningPiece('X');


        char[][] child1 = {
                {'X', '0', '0'},
                {'_', 'X', '0'},
                {'X', '_', '_'}
        };
        TicTacToe bdchild1 = new TicTacToe(child1);
        bdchild1.setOpeningPiece('X');

        char[][] child2 = {
                {'X', '0', '_'},
                {'0', 'X', '0'},
                {'X', '_', '_'}
        };
        TicTacToe bdchild2 = new TicTacToe(child2);
        bdchild2.setOpeningPiece('X');

        char[][] child3 = {
                {'X', '0', '_'},
                {'_', 'X', '0'},
                {'X', '0', '_'}
        };
        TicTacToe bdchild3 = new TicTacToe(child3);
        bdchild3.setOpeningPiece('X');

        char[][] child4 = {
                {'X', '0', '_'},
                {'_', 'X', '0'},
                {'X', '_', '0'}
        };
        TicTacToe bdchild4 = new TicTacToe(child4);
        bdchild4.setOpeningPiece('X');

        List<IBoardGame> expected = new ArrayList<>();
        expected.add(bdchild1);
        expected.add(bdchild2);
        expected.add(bdchild3);
        expected.add(bdchild4);


        List<IBoardGame> obtained = bd.children();

        Assert.assertEquals(expected, obtained);
    }

    @Test
    public void testChildren2() throws CloneNotSupportedException, TicTacToeException, BoardException {
        char[][] board = {
                {'X', '0', '_'},
                {'0', 'X', '0'},
                {'X', '_', '0'}
        };
        TicTacToe bd = new TicTacToe(board);
        bd.setOpeningPiece('0');

        char[][] child1 = {
                {'X', '0', 'X'},
                {'0', 'X', '0'},
                {'X', '_', '0'}
        };
        TicTacToe bdchild1 = new TicTacToe(child1);
        bdchild1.setOpeningPiece('0');

        char[][] child2 = {
                {'X', '0', '_'},
                {'0', 'X', '0'},
                {'X', 'X', '0'}
        };
        TicTacToe bdchild2 = new TicTacToe(child2);
        bdchild2.setOpeningPiece('0');

        List<IBoardGame> expected = new ArrayList<>();
        expected.add(bdchild1);
        expected.add(bdchild2);


        List<IBoardGame> obtained = bd.children();

        Assert.assertEquals(expected, obtained);
    }

    @Test
    public void testChildren3() throws CloneNotSupportedException, TicTacToeException, BoardException {
        char[][] board = {
                {'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };
        TicTacToe bd = new TicTacToe(board);
        bd.setOpeningPiece('X');

        char[][] child1 = {
                {'X', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };
        TicTacToe bdchild1 = new TicTacToe(child1);
        bdchild1.setOpeningPiece('X');

        char[][] child2 = {
                {'_', 'X', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };
        TicTacToe bdchild2 = new TicTacToe(child2);
        bdchild2.setOpeningPiece('X');

        char[][] child5 = {
                {'_', '_', '_'},
                {'_', 'X', '_'},
                {'_', '_', '_'}
        };
        TicTacToe bdchild5 = new TicTacToe(child5);
        bdchild5.setOpeningPiece('X');

        List<IBoardGame> expected = new ArrayList<>();
        expected.add(bdchild1);
        expected.add(bdchild2);
        expected.add(bdchild5);

        List<IBoardGame> obtained = bd.children();

        Assert.assertEquals(expected, obtained);
    }

    @Test
    public void testGetEmptyPositions() throws TicTacToeException, BoardException {
        char[][] board = {
                {'X', '0', '_'},
                {'_', 'X', '_'},
                {'0', 'X', '0'}
        };
        TicTacToe bd = new TicTacToe(board);
        bd.setOpeningPiece('X');

        Point cd1 = new Point(0, 2);
        Point cd2 = new Point(1, 0);
        Point cd3 = new Point(1, 2);

        List<Point> expected = new ArrayList<>();
        expected.add(cd1);
        expected.add(cd2);
        expected.add(cd3);

        List<Point> obtained = bd.getEmptyPositions();

        Assert.assertEquals(expected, obtained);

    }

    @Test
    public void testGetEmptyPositions3() throws TicTacToeException, BoardException {
        char[][] board = {
                {'X', '0', 'X'},
                {'0', '0', '0'},
                {'0', 'X', '0'}
        };
        TicTacToe bd = new TicTacToe(board);
        bd.setOpeningPiece('X');


        List<Point> expected = new ArrayList<>();


        List<Point> obtained = bd.getEmptyPositions();

        Assert.assertEquals(expected, obtained);

    }


    @Test
    public void testEquals1() throws BoardException {
        char[][] board = {
                {'X', '0', '_'},
                {'0', 'X', '0'},
                {'X', '_', '0'}
        };

        TicTacToe bd = new TicTacToe(board);
        Assert.assertEquals(bd, bd);
    }

    @Test
    public void testEquals2() throws BoardException {
        char[][] board1 = {
                {'X', '0', '_'},
                {'0', 'X', '0'},
                {'X', '_', '0'}
        };
        char[][] board2 = {
                {'X', '0', 'X'},
                {'0', 'X', '0'},
                {'X', '_', '0'}
        };
        TicTacToe bd = new TicTacToe(board1);
        TicTacToe otherbd = new TicTacToe(board2);
        Assert.assertNotEquals(bd, otherbd);
    }


    @Test
    public void testEquals3() throws BoardException {
        char[][] board1 = {
                {'X', '0', '_'},
                {'0', 'X', '0'},
                {'X', '_', '0'}
        };
        char[][] board2 = {
                {'X', '0', '_'},
                {'0', 'X', '0'},
                {'X', '_', '0'}
        };
        TicTacToe bd = new TicTacToe(board1);
        TicTacToe otherbd = new TicTacToe(board2);
        Assert.assertEquals(bd, otherbd);
    }


    @Test
    public void testClone() throws CloneNotSupportedException, TicTacToeException, BoardException {
        char[][] board = {
                {'X', '0', '_'},
                {'_', 'X', '0'},
                {'X', '_', '_'}
        };
        TicTacToe bd = new TicTacToe(board);
        bd.setOpeningPiece('X');
        TicTacToe clone = (TicTacToe) bd.clone();
        Assert.assertEquals(bd, clone);
    }

    @Test
    public void testClone2() throws CloneNotSupportedException, TicTacToeException, BoardException {
        char[][] board = {
                {'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };
        TicTacToe bd = new TicTacToe(board);
        bd.setOpeningPiece('X');
        TicTacToe clone = (TicTacToe) bd.clone();
        Assert.assertEquals(bd, clone);
    }

    @Test
    public void testClone3() throws CloneNotSupportedException, TicTacToeException, BoardException {
        char[][] board = {
                {'X', '0', '_'},
                {'0', 'X', '0'},
                {'X', '_', '0'}
        };
        TicTacToe bd = new TicTacToe(board);
        bd.setOpeningPiece('0');
        TicTacToe clone = (TicTacToe) bd.clone();
        Assert.assertEquals(bd, clone);
    }

    @Test
    public void testClone4() throws CloneNotSupportedException, TicTacToeException, BoardException {
        char[][] board = {
                {'X', '0', '_'},
                {'0', 'X', '0'},
                {'X', '_', '0'}
        };

        TicTacToe bd = new TicTacToe(board);
        bd.setOpeningPiece('X');
        Assert.assertEquals(bd.clone(), bd.clone());
    }


    @Test
    public void testGetStatus() throws TicTacToeException {
        TicTacToe board1 = new TicTacToe("00_", "XX0", "0X_");
        board1.setOpeningPiece('X');
        Assert.assertEquals("in progress", board1.getStatus());

        TicTacToe board2 = new TicTacToe("000", "XX0", "0XX");
        board2.setOpeningPiece('X');
        Assert.assertEquals(board2.getStatus(), "0");

        TicTacToe board3 = new TicTacToe(
                "_X_",
                "0X0",
                "_0_");
        board3.setOpeningPiece('0');
        Assert.assertEquals(board3.getStatus(), "in progress");

        TicTacToe board4 = new TicTacToe("0XX", "X00", "0XX");
        board4.setOpeningPiece('X');
        Assert.assertEquals(board4.getStatus(), "draw");

        TicTacToe board5 = new TicTacToe("0X_", "0X_", "_X_");
        board5.setOpeningPiece('X');
        Assert.assertEquals(board5.getStatus(), "X");

    }


    @Test
    public void testPlaceMove() throws TicTacToeException {
        TicTacToe board1 = new TicTacToe("00_", "XX0", "0X_");
        board1.setOpeningPiece('X');
        board1.placeMove(new Point(0, 2), 'X');
        TicTacToe expected1 = new TicTacToe("00X", "XX0", "0X_");
        expected1.setOpeningPiece('X');

        Assert.assertEquals(expected1, board1);


        TicTacToe board2 = new TicTacToe("0__", "X__", "___");
        board2.setOpeningPiece('0');
        board2.placeMove(new Point(1, 1), '0');
        TicTacToe expected2 = new TicTacToe("0__", "X0_", "___");
        expected2.setOpeningPiece('0');
        Assert.assertEquals(expected2, board2);

        TicTacToe board3 = new TicTacToe("0_X", "_X_", "___");
        board3.setOpeningPiece('X');
        board3.placeMove(new Point(2, 0), '0');
        TicTacToe expected3 = new TicTacToe("0_X", "_X_", "0__");
        expected3.setOpeningPiece('X');
        Assert.assertEquals(expected3, board3);

    }

    @Test(expected = TicTacToeException.class)
    public void testPlaceMove2() throws TicTacToeException {
        TicTacToe board3 = new TicTacToe("0_X", "_X_", "___");
        board3.setOpeningPiece('X');
        board3.placeMove(new Point(0, 0), '0');
    }

    /*
    The unit tests below are related to the heuristic computation
     */

    @Test
    public void testCheckOpenDiag() throws TicTacToeException {
        TicTacToe board1 = new TicTacToe(
                "_X_",
                "0X0",
                "___");
        board1.setOpeningPiece('0');
        Assert.assertEquals(board1.checkOpenDiag('0'), 0);
        Assert.assertEquals(board1.checkOpenDiag('X'), 2);
    }

    @Test
    public void testCheckOpenDiag2() throws TicTacToeException {
        TicTacToe board1 = new TicTacToe(
                "_X_",
                "0_0",
                "_X_");
        board1.setOpeningPiece('0');
        Assert.assertEquals(board1.checkOpenDiag('0'), 2);
        Assert.assertEquals(board1.checkOpenDiag('X'), 2);
    }

    @Test
    public void testCheckOpenDiag3() throws TicTacToeException {
        TicTacToe board1 = new TicTacToe(
                "_XX",
                "0_0",
                "_X_");
        board1.setOpeningPiece('0');
        Assert.assertEquals(board1.checkOpenDiag('0'), 1);
        Assert.assertEquals(board1.checkOpenDiag('X'), 2);
    }

    @Test
    public void testCheckOpenRow() throws TicTacToeException {
        TicTacToe board1 = new TicTacToe(
                "_X_",
                "0X0",
                "___");
        board1.setOpeningPiece('0');
        Assert.assertEquals(board1.checkOpenRows('0'), 1);
        Assert.assertEquals(board1.checkOpenRows('X'), 2);
    }

    @Test
    public void testCheckOpenRow2() throws TicTacToeException {
        TicTacToe board1 = new TicTacToe(
                "_X_",
                "0_0",
                "_X_");
        board1.setOpeningPiece('0');
        Assert.assertEquals(board1.checkOpenRows('0'), 1);
        Assert.assertEquals(board1.checkOpenRows('X'), 2);
    }

    @Test
    public void testCheckOpenRow3() throws TicTacToeException {
        TicTacToe board1 = new TicTacToe(
                "_0X",
                "0__",
                "0XX");
        board1.setOpeningPiece('0');
        Assert.assertEquals(board1.checkOpenRows('0'), 1);
        Assert.assertEquals(board1.checkOpenRows('X'), 0);
    }

    @Test
    public void testCheckOpenColumn() throws TicTacToeException {
        TicTacToe board1 = new TicTacToe(
                "_X_",
                "0X0",
                "___");
        board1.setOpeningPiece('0');
        Assert.assertEquals(board1.checkOpenColumns('0'), 2);
        Assert.assertEquals(board1.checkOpenColumns('X'), 1);
    }

    @Test
    public void testCheckOpenColumn2() throws TicTacToeException {
        TicTacToe board1 = new TicTacToe(
                "_X_",
                "0_0",
                "X__");
        board1.setOpeningPiece('0');
        Assert.assertEquals(board1.checkOpenColumns('0'), 1);
        Assert.assertEquals(board1.checkOpenColumns('X'), 1);
    }

    @Test
    public void testCheckOpenColumn3() throws TicTacToeException {
        TicTacToe board1 = new TicTacToe(
                "X0X",
                "0__",
                "_X0");
        board1.setOpeningPiece('0');
        Assert.assertEquals(board1.checkOpenColumns('0'), 0);
        Assert.assertEquals(board1.checkOpenColumns('X'), 0);
    }


    @Test
    public void mctsTestH() throws TicTacToeException {
        TicTacToe board1 = new TicTacToe(
                "_X_",
                "0X0",
                "___");
        board1.setOpeningPiece('0');
        Assert.assertEquals(0, board1.getH());

    }

    @Test
    public void mctsTestH2() throws TicTacToeException {
        TicTacToe board1 = new TicTacToe(
                "_X_",
                "0_0",
                "_X_");
        board1.setOpeningPiece('0');
        Assert.assertEquals(-3, board1.getH());

    }

    @Test
    public void mctsTestH3() throws TicTacToeException {
        TicTacToe board1 = new TicTacToe(
                "X0_",
                "_X_",
                "X0_");
        board1.setOpeningPiece('X');
        Assert.assertEquals(4, board1.getH());

    }

    @Test
    public void mctsTestH4() throws TicTacToeException {
        TicTacToe board1 = new TicTacToe(
                "X0_",
                "_X_",
                "_0X");
        board1.setOpeningPiece('X');
        Assert.assertEquals(6, board1.getH());

    }

    @Test
    public void mctsTestH5() throws TicTacToeException {
        TicTacToe board1 = new TicTacToe(
                "0X_",
                "_0_",
                "___");
        TicTacToe board2 = new TicTacToe(
                "0X_",
                "___",
                "__0");
        board1.setOpeningPiece('0');
        board2.setOpeningPiece('0');
        Assert.assertEquals(4, board1.getH());
        Assert.assertEquals(3, board2.getH());

    }


    @Test
    public void mctsTestH6() throws TicTacToeException {
        TicTacToe board1 = new TicTacToe(
                "0X_",
                "_0_",
                "X__");
        TicTacToe board2 = new TicTacToe(
                "0X_",
                "_0_",
                "__X");
        board1.setOpeningPiece('0');
        board2.setOpeningPiece('0');
        Assert.assertEquals(-3, board1.getH());
        Assert.assertEquals(-2, board2.getH());

    }

    @Test
    public void mctsTestH7() throws TicTacToeException {
        TicTacToe board1 = new TicTacToe(
                "0X_",
                "_0X",
                "__0");
        TicTacToe board2 = new TicTacToe(
                "0X0",
                "_0X",
                "___");
        board1.setOpeningPiece('0');
        board2.setOpeningPiece('0');
        Assert.assertEquals(4, board1.getH());
        Assert.assertEquals(3, board2.getH());

    }
}
