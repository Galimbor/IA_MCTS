import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BoardUnitTest {



    @Test
    public void testSetCurrentPlayer1()
    {
        char[][] board = {
                        {'X','0','-'},
                        {'-','X','0'},
                        {'X','-','-'}
        };
        Board bd = new Board(board);
        char obtained = bd.getCurrentPlayer();
        char expected = '0';
        Assert.assertEquals(obtained,expected);
    }

    @Test
    public void testSetCurrentPlayer2()
    {
        char[][] board = {
                {'-','-','-'},
                {'-','-','-'},
                {'-','-','-'}
        };
        Board bd = new Board(board);
        char obtained = bd.getCurrentPlayer();
        char expected = 'X';
        Assert.assertEquals(obtained,expected);
    }

    @Test
    public void testSetCurrentPlayer3()
    {
        char[][] board = {
                {'X','0','-'},
                {'0','X','0'},
                {'X','-','0'}
        };
        Board bd = new Board(board);
        char obtained = bd.getCurrentPlayer();
        char expected = 'X';
        Assert.assertEquals(obtained,expected);
    }



    @Test
    public void testEquals1() throws CloneNotSupportedException {
        char[][] board = {
                {'X','0','-'},
                {'0','X','0'},
                {'X','-','0'}
        };

        Board bd = new Board(board);
        Assert.assertEquals(bd, bd);
    }

    @Test
    public void testEquals2() throws CloneNotSupportedException {
        char[][] board1 = {
                {'X','0','-'},
                {'0','X','0'},
                {'X','-','0'}
        };
        char[][] board2 = {
                {'X','0','X'},
                {'0','X','0'},
                {'X','-','0'}
        };
        Board bd = new Board(board1);
        Board otherbd = new Board(board2);
        Assert.assertNotEquals(bd, otherbd);
    }


    @Test
    public void testEquals3() throws CloneNotSupportedException {
        char[][] board1 = {
                {'X','0','-'},
                {'0','X','0'},
                {'X','-','0'}
        };
        char[][] board2 = {
                {'X','0','-'},
                {'0','X','0'},
                {'X','-','0'}
        };
        Board bd = new Board(board1);
        Board otherbd = new Board(board2);
        Assert.assertEquals(bd, otherbd);
    }


    @Test
    public void testClone() throws CloneNotSupportedException {
        char[][] board = {
                {'X','0','-'},
                {'-','X','0'},
                {'X','-','-'}
        };
        Board bd = new Board(board);
        Board clone = (Board) bd.clone();
        Assert.assertEquals(bd, clone);
    }

    @Test
    public void testClone2() throws CloneNotSupportedException {
        char[][] board = {
                {'-','-','-'},
                {'-','-','-'},
                {'-','-','-'}
        };
        Board bd = new Board(board);
        Board clone = (Board) bd.clone();
        Assert.assertEquals(bd, clone);
    }

    @Test
    public void testClone3() throws CloneNotSupportedException {
        char[][] board = {
                {'X','0','-'},
                {'0','X','0'},
                {'X','-','0'}
        };
        Board bd = new Board(board);
        Board clone = (Board) bd.clone();
        Assert.assertEquals(bd, clone);
    }

    @Test
    public void testClone4() throws CloneNotSupportedException {
        char[][] board = {
                {'X','0','-'},
                {'0','X','0'},
                {'X','-','0'}
        };

        Board bd = new Board(board);
        Assert.assertEquals(bd.clone(), bd.clone());
    }

    @Test
    public void testChildren1() throws CloneNotSupportedException {
        char[][] board = {
                {'X','0','-'},
                {'-','X','0'},
                {'X','-','-'}
        };
        Board bd = new Board(board);


        char[][] child1 = {
                {'X','0','0'},
                {'-','X','0'},
                {'X','-','-'}
        };
        Board bdchild1 = new Board(child1);


        char[][] child2 = {
                {'X','0','-'},
                {'0','X','0'},
                {'X','-','-'}
        };
        Board bdchild2 = new Board(child2);

        char[][] child3 = {
                {'X','0','-'},
                {'-','X','0'},
                {'X','0','-'}
        };
        Board bdchild3 = new Board(child3);


        char[][] child4 = {
                {'X','0','-'},
                {'-','X','0'},
                {'X','-','0'}
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
                {'X','0','-'},
                {'0','X','0'},
                {'X','-','0'}
        };
        Board bd = new Board(board);

        char[][] child1 = {
                {'X','0','X'},
                {'0','X','0'},
                {'X','-','0'}
        };
        Board bdchild1 = new Board(child1);


        char[][] child2 = {
                {'X','0','-'},
                {'0','X','0'},
                {'X','X','0'}
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
                {'-','-','-'},
                {'-','-','-'},
                {'-','-','-'}
        };
        Board bd = new Board(board);

        char[][] child1 = {
                {'X','-','-'},
                {'-','-','-'},
                {'-','-','-'}
        };
        Board bdchild1 = new Board(child1);


        char[][] child2 = {
                {'-','X','-'},
                {'-','-','-'},
                {'-','-','-'}
        };
        Board bdchild2 = new Board(child2);


        char[][] child3 = {
                {'-','-','X'},
                {'-','-','-'},
                {'-','-','-'}
        };
        Board bdchild3 = new Board(child3);


        char[][] child4 = {
                {'-','-','-'},
                {'X','-','-'},
                {'-','-','-'}
        };
        Board bdchild4 = new Board(child4);

        char[][] child5 = {
                {'-','-','-'},
                {'-','X','-'},
                {'-','-','-'}
        };
        Board bdchild5 = new Board(child5);

        char[][] child6 = {
                {'-','-','-'},
                {'-','-','X'},
                {'-','-','-'}
        };
        Board bdchild6 = new Board(child6);

        char[][] child7 = {
                {'-','-','-'},
                {'-','-','-'},
                {'X','-','-'}
        };
        Board bdchild7 = new Board(child7);

        char[][] child8 = {
                {'-','-','-'},
                {'-','-','-'},
                {'-','X','-'}
        };
        Board bdchild8 = new Board(child8);

        char[][] child9 = {
                {'-','-','-'},
                {'-','-','-'},
                {'-','-','X'}
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
