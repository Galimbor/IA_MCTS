import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BoardUnitTests {


    @Test
    public void testFirstContructor1() throws BoardException {
        Board obtained = new Board(3, '_');

        char[][] board = {
                {'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };

        Board expected = new Board(board);

        Assert.assertEquals(expected, obtained);

    }

    @Test
    public void testFirstContructor2() throws BoardException {
        Board obtained = new Board(2, '0');

        char[][] board = {
                {'0', '0'},
                {'0', '0'}
        };

        Board expected = new Board(board);

        Assert.assertEquals(expected, obtained);

    }


    @Test
    public void testSecondConstructor() throws BoardException {
        String[] row1 = {"X", "_", "_"};
        String[] row2 = {"_", "_", "_"};
        String[] row3 = {"_", "_", "_"};
        Board obtained = new Board(3, row1, row2, row3);

        char[][] char2 = {
                {'X', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };
        Board expected = new Board(char2);


        Assert.assertEquals(expected, obtained);

        String[] row1_1 = {"X", "X", "X"};
        String[] row2_1 = {"X", "0", "0"};
        String[] row3_1 = {"0", "X", "0"};
        Board obtained_1 = new Board(3, row1_1, row2_1, row3_1);

        char[][] b2Compare = {
                {'X', 'X', 'X'},
                {'X', '0', '0'},
                {'0', 'X', '0'}
        };
        Board expected_1 = new Board(b2Compare);

        Assert.assertEquals(expected_1, obtained_1);

    }

    @Test(expected = BoardException.class)
    public void testSetRows1() throws BoardException {
        Board b1 = new Board(3, '_');
        b1.setNumberRows(-3);

    }

    @Test()
    public void testSetRows2() throws BoardException {
        Board b1 = new Board(3, '_');
        b1.setNumberRows(2);
        Assert.assertEquals(2, b1.getNumberRows());

    }

    @Test(expected = BoardException.class)
    public void testSetColumns1() throws BoardException {
        Board b1 = new Board(3, '_');
        b1.setNumberColumns(-3);

    }

    @Test()
    public void testSetColumns2() throws BoardException {
        Board b1 = new Board(3, '_');
        b1.setNumberColumns(2);
        Assert.assertEquals(2, b1.getNumberColumns());

    }

    @Test
    public void testFillBoardWithChar() throws BoardException {
        Board obtained1 = new Board(3, '_');
        obtained1.fillBoardWithChar('X');

        char[][] char1 = {
                {'X', 'X', 'X'},
                {'X', 'X', 'X'},
                {'X', 'X', 'X'}
        };
        Board expected1 = new Board(char1);

        Assert.assertEquals(expected1, obtained1);


        Board obtained2 = new Board(2, '_');
        obtained2.fillBoardWithChar('0');

        char[][] char2 = {
                {'0', '0'},
                {'0', '0'}
        };
        Board expected2 = new Board(char2);

        Assert.assertEquals(expected2, obtained2);

    }


    @Test
    public void testGetCharPositions() throws BoardException {

        String[] row1_1 = {"X", "X", "X"};
        String[] row2_1 = {"X", "0", "0"};
        String[] row3_1 = {"0", "X", "0"};
        Board board1 = new Board(3, row1_1, row2_1, row3_1);

        List<Point> obtained = board1.getElementPositions('0');

        List<Point> expected = new ArrayList<>();
        expected.add(new Point(1, 1));
        expected.add(new Point(1, 2));
        expected.add(new Point(2, 0));
        expected.add(new Point(2, 2));

        Assert.assertEquals(expected, obtained);

    }

    @Test
    public void testGetCharPositions2() throws BoardException {

        String[] row1_1 = {"X", "_", "X"};
        String[] row2_1 = {"_", "0", "0"};
        String[] row3_1 = {"0", "X", "_"};
        Board board1 = new Board(3, row1_1, row2_1, row3_1);

        List<Point> obtained = board1.getElementPositions('X');

        List<Point> expected = new ArrayList<>();
        expected.add(new Point(0, 0));
        expected.add(new Point(0, 2));
        expected.add(new Point(2, 1));

        Assert.assertEquals(expected, obtained);

    }

    @Test
    public void testIsValidCoordinate() throws BoardException {
        Board bd1 = new Board(3, '_');
        List<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(0, 2));
        points.add(new Point(2, 1));

        for (Point c : points
        ) {
            Assert.assertTrue(bd1.isValidPoint(c));
        }
    }

    @Test(expected = BoardException.class)
    public void testIsValidCoordinate2() throws BoardException {
        Board bd1 = new Board(3, '_');

        List<Point> points = new ArrayList<>();
        points.add(new Point(-1, 0));
        points.add(new Point(3, 2));

        for (Point c : points
        ) {
            bd1.isValidPoint(c);
        }
    }


    @Test
    public void testEquals1() throws BoardException {
        char[][] board = {
                {'X', '0', '-'},
                {'0', 'X', '0'},
                {'X', '-', '0'}
        };

        Board bd = new Board(board);
        Assert.assertEquals(bd, bd);
    }

    @Test
    public void testEquals2() throws BoardException {
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
    public void testEquals3() throws BoardException {
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
    public void testClone() throws CloneNotSupportedException, BoardException {
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
    public void testClone2() throws CloneNotSupportedException, BoardException {
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
    public void testClone3() throws CloneNotSupportedException, BoardException {
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
    public void testClone4() throws CloneNotSupportedException, BoardException {
        char[][] board = {
                {'X', '0', '-'},
                {'0', 'X', '0'},
                {'X', '-', '0'}
        };

        Board bd = new Board(board);
        Assert.assertEquals(bd.clone(), bd.clone());
    }


    @Test
    public void boardToString() throws BoardException {
        String[] row1_1 = {"X", "_", "_"};
        String[] row2_1 = {"_", "_", "_"};
        String[] row3_1 = {"_", "_", "_"};
        Board b1 = new Board(3, row1_1, row2_1, row3_1);
        String expected = "X|_|_\n";
        expected = expected.concat("_|_|_\n");
        expected = expected.concat("_|_|_\n");
        String obtained = b1.toString();
        Assert.assertEquals(expected, obtained);

        String[] row1_2 = {"X", "_", "_"};
        String[] row2_2 = {"_", "_", "X"};
        String[] row3_2 = {"_", "_", "_"};
        Board b2 = new Board(3, row1_2, row2_2, row3_2);
        String expected2 = "X|_|_\n";
        expected2 = expected2.concat("_|_|_\n");
        expected2 = expected2.concat("_|_|_\n");
        String obtained2 = b2.toString();
        Assert.assertNotEquals(expected2, obtained2);
    }


    @Test
    public void testIsPositionAvailable() throws BoardException {
        String[] row1_2 = {"X", "_", "_"};
        String[] row2_2 = {"_", "_", "X"};
        String[] row3_2 = {"_", "_", "_"};
        Board b2 = new Board(3, row1_2, row2_2, row3_2);

        Point cd1 = new Point(0, 0);
        Point cd2 = new Point(0, 1);
        Assert.assertTrue(b2.isPositionAvailable(cd2, '_'));
        Assert.assertFalse(b2.isPositionAvailable(cd1, '_'));

        char[][] board3 = {
                {'X', '0', 'X'},
                {'0', 'X', '0'},
                {'X', '_', '0'}
        };
        Board bd3 = new Board(board3);

        Point cd3 = new Point(0, 0);
        Point cd4 = new Point(0, 1);
        Point cd5 = new Point(2, 1);
        Assert.assertTrue(bd3.isPositionAvailable(cd5, '_'));
        Assert.assertFalse(bd3.isPositionAvailable(cd3, '_'));
        Assert.assertFalse(bd3.isPositionAvailable(cd4, '_'));


    }


}
