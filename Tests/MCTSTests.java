import org.junit.Assert;
import org.junit.Test;

public class MCTSTests {


    @Test
    public void mctsTest() throws CloneNotSupportedException, TicTacToeException, MCTSException {

        ITreePolicy treePolicy = new UCT();
        IRolloutPolicy rolloutPolicy = new SpecialHeuristic();
        MCTS s = new MCTS(treePolicy, rolloutPolicy);

        TicTacToe board1 = new TicTacToe("___", "___", "___");
        board1.setOpeningPiece('X');
        MCTS.State s0 = new MCTS.State(board1, null);

        MCTS.State obtained = s.bestNextMove(s0.getGame());

        TicTacToe board2 = new TicTacToe("___", "_X_", "___");
        board2.setOpeningPiece('X');
        MCTS.State expected = new MCTS.State(board2, null);


        Assert.assertEquals(expected, obtained);

    }


    @Test
    public void mctsTest2() throws TicTacToeException, MCTSException, CloneNotSupportedException {

        ITreePolicy treePolicy = new UCT();
        IRolloutPolicy rolloutPolicy = new SpecialHeuristic();
        MCTS s = new MCTS(treePolicy, rolloutPolicy);

        TicTacToe board1 = new TicTacToe("X__", "___", "___");
        board1.setOpeningPiece('X');
        MCTS.State s0 = new MCTS.State(board1, null);

        MCTS.State obtained = s.bestNextMove(s0.getGame());

        TicTacToe board2 = new TicTacToe("X__", "_0_", "___");
        board2.setOpeningPiece('X');
        MCTS.State expected = new MCTS.State(board2, null);


        Assert.assertEquals(expected, obtained);


    }


    @Test
    public void mctsTest3() throws CloneNotSupportedException, TicTacToeException, MCTSException {

        ITreePolicy treePolicy = new UCT();
        IRolloutPolicy rolloutPolicy = new SpecialHeuristic();
        MCTS s = new MCTS(treePolicy, rolloutPolicy);

        TicTacToe board1 = new TicTacToe("0X_", "___", "___");
        board1.setOpeningPiece('0');
        MCTS.State s0 = new MCTS.State(board1, null);

        MCTS.State obtained = s.bestNextMove(s0.getGame());

        TicTacToe board2 = new TicTacToe("0X_", "_0_", "___");
        board2.setOpeningPiece('0');
        MCTS.State expected = new MCTS.State(board2, null);


        Assert.assertEquals(expected, obtained);

    }

    @Test
    public void mctsTest4() throws CloneNotSupportedException, TicTacToeException, MCTSException {

        ITreePolicy treePolicy = new UCT();
        IRolloutPolicy rolloutPolicy = new SpecialHeuristic();
        MCTS s = new MCTS(treePolicy, rolloutPolicy);

        TicTacToe board1 = new TicTacToe("0X_", "_X_", "___");
        board1.setOpeningPiece('X');
        MCTS.State s0 = new MCTS.State(board1, null);

        MCTS.State obtained = s.bestNextMove(s0.getGame());

        TicTacToe board2 = new TicTacToe("0X_", "_X_", "_0_");
        board2.setOpeningPiece('X');
        MCTS.State expected = new MCTS.State(board2, null);


        Assert.assertEquals(expected, obtained);


    }


    @Test
    public void mctsTest5() throws CloneNotSupportedException, TicTacToeException, MCTSException {

        ITreePolicy treePolicy = new UCT();
//        IRolloutPolicy rolloutPolicy = new RandomUniform();
        IRolloutPolicy rolloutPolicy = new SpecialHeuristic();
        MCTS s = new MCTS(treePolicy, rolloutPolicy);

        TicTacToe board1 = new TicTacToe("X0_", "_X_", "_0_");
        board1.setOpeningPiece('X');
        MCTS.State s0 = new MCTS.State(board1, null);

        MCTS.State obtained = s.bestNextMove(s0.getGame());
        //X0_
        //_X_  5 - 1 = 4
        //x0_

        //X0_
        //_X_  5 - 0 = 5
        //_0X

//        TicTacToe board2 = new TicTacToe("X0_","_X_","X0_");
//        TicTacToe board3 = new TicTacToe("X0_","_X_","_0X");
//        System.out.println(board2.getH());
//        System.out.println(board3.getH());
        //It goes to a child that wins instead of this; Wins anyway
        TicTacToe board2 = new TicTacToe("X0_", "_X_", "_0X");
        board2.setOpeningPiece('X');
        MCTS.State expected = new MCTS.State(board2, null);


        Assert.assertEquals(expected, obtained);


    }


    @Test
    public void mctsTest6() throws CloneNotSupportedException, TicTacToeException, MCTSException {

        ITreePolicy treePolicy = new UCT();
//        IRolloutPolicy rolloutPolicy = new RandomUniform();
        IRolloutPolicy rolloutPolicy = new SpecialHeuristic();
        MCTS s = new MCTS(treePolicy, rolloutPolicy);

        TicTacToe board1 = new TicTacToe("0X0", "___", "___");
        board1.setOpeningPiece('0');
        MCTS.State s0 = new MCTS.State(board1, null);

        MCTS.State obtained = s.bestNextMove(s0.getGame());


        TicTacToe board2 = new TicTacToe("0X0", "_X_", "___");
        board2.setOpeningPiece('0');
        MCTS.State expected = new MCTS.State(board2, null);


        Assert.assertEquals(expected, obtained);

    }

    @Test
    public void mctsTest7() throws CloneNotSupportedException, TicTacToeException, MCTSException {

        ITreePolicy treePolicy = new UCT();
//        IRolloutPolicy rolloutPolicy = new RandomUniform();
        IRolloutPolicy rolloutPolicy = new SpecialHeuristic();
        MCTS s = new MCTS(treePolicy, rolloutPolicy);

        TicTacToe board1 = new TicTacToe("X_0", "_0_", "X__");
        board1.setOpeningPiece('X');
        MCTS.State s0 = new MCTS.State(board1, null);

        MCTS.State obtained = s.bestNextMove(s0.getGame());


        TicTacToe board2 = new TicTacToe("X_0", "X0_", "X__");
        board2.setOpeningPiece('X');
        MCTS.State expected = new MCTS.State(board2, null);


        Assert.assertEquals(expected, obtained);


    }


    @Test
    public void mctsTest8() throws CloneNotSupportedException, TicTacToeException, MCTSException {

        ITreePolicy treePolicy = new UCT();
//        IRolloutPolicy rolloutPolicy = new RandomUniform();
        IRolloutPolicy rolloutPolicy = new SpecialHeuristic();
        MCTS s = new MCTS(treePolicy, rolloutPolicy);

        TicTacToe board1 = new TicTacToe("0X_", "_0_", "_X_");
        board1.setOpeningPiece('X');
        MCTS.State s0 = new MCTS.State(board1, null);

        MCTS.State obtained = s.bestNextMove(s0.getGame());


        TicTacToe board2 = new TicTacToe("0X_", "_0_", "_XX");
        board2.setOpeningPiece('X');
        MCTS.State expected = new MCTS.State(board2, null);


        Assert.assertEquals(expected, obtained);

    }


    @Test
    public void mctsTest9() throws CloneNotSupportedException, TicTacToeException, MCTSException {
        ITreePolicy treePolicy = new UCT();
//        IRolloutPolicy rolloutPolicy = new RandomUniform();
        IRolloutPolicy rolloutPolicy = new SpecialHeuristic();
        MCTS s = new MCTS(treePolicy, rolloutPolicy);

        TicTacToe board1 = new TicTacToe("0X_", "_0_", "_XX");
        board1.setOpeningPiece('0');
        MCTS.State s0 = new MCTS.State(board1, null);

        MCTS.State obtained = s.bestNextMove(s0.getGame());


        TicTacToe board2 = new TicTacToe("0X0", "_0_", "_XX");
        board2.setOpeningPiece('0');
        MCTS.State expected = new MCTS.State(board2, null);


        Assert.assertEquals(expected, obtained);

    }

    @Test
    public void mctsTest10() throws CloneNotSupportedException, TicTacToeException, MCTSException {

        ITreePolicy treePolicy = new UCT();
//        IRolloutPolicy rolloutPolicy = new RandomUniform();
        IRolloutPolicy rolloutPolicy = new SpecialHeuristic();
        MCTS s = new MCTS(treePolicy, rolloutPolicy);

        TicTacToe board1 = new TicTacToe("0X_", "_0_", "0XX");
        board1.setOpeningPiece('X');
        MCTS.State s0 = new MCTS.State(board1, null);

        MCTS.State obtained = s.bestNextMove(s0.getGame());


        TicTacToe board2 = new TicTacToe("0XX", "_0_", "0XX");
        board2.setOpeningPiece('X');
        MCTS.State expected = new MCTS.State(board2, null);


        Assert.assertEquals(expected, obtained);


    }

    @Test
    public void mctsTest11() throws CloneNotSupportedException, TicTacToeException, MCTSException {

        ITreePolicy treePolicy = new UCT();
//        IRolloutPolicy rolloutPolicy = new RandomUniform();
        IRolloutPolicy rolloutPolicy = new SpecialHeuristic();
        MCTS s = new MCTS(treePolicy, rolloutPolicy);

        TicTacToe board1 = new TicTacToe("0X_", "_0_", "0XX");
        board1.setOpeningPiece('X');
        MCTS.State s0 = new MCTS.State(board1, null);

        MCTS.State obtained = s.bestNextMove(s0.getGame());


        TicTacToe board2 = new TicTacToe("0XX", "_0_", "0XX");
        board2.setOpeningPiece('X');
        MCTS.State expected = new MCTS.State(board2, null);


        Assert.assertEquals(expected, obtained);


    }


    @Test
    public void mctsTest12() throws CloneNotSupportedException, TicTacToeException, MCTSException {

        ITreePolicy treePolicy = new UCT();
//        IRolloutPolicy rolloutPolicy = new RandomUniform();
        IRolloutPolicy rolloutPolicy = new SpecialHeuristic();
        MCTS s = new MCTS(treePolicy, rolloutPolicy);

        TicTacToe board1 = new TicTacToe("0X_", "X0_", "_X_");
        board1.setOpeningPiece('X');
        MCTS.State s0 = new MCTS.State(board1, null);

        MCTS.State obtained = s.bestNextMove(s0.getGame());


        TicTacToe board2 = new TicTacToe("0X_", "X0_", "_X0");
        board2.setOpeningPiece('X');
        MCTS.State expected = new MCTS.State(board2, null);


        Assert.assertEquals(expected, obtained);

    }


    @Test
    public void mctsTest14() throws CloneNotSupportedException, TicTacToeException, MCTSException {

        ITreePolicy treePolicy = new UCT();
//        IRolloutPolicy rolloutPolicy = new RandomUniform();
        IRolloutPolicy rolloutPolicy = new SpecialHeuristic();
        MCTS s = new MCTS(treePolicy, rolloutPolicy);

        TicTacToe board1 = new TicTacToe("0X_", "_0_", "_XX");
        board1.setOpeningPiece('X');
        MCTS.State s0 = new MCTS.State(board1, null);

        MCTS.State obtained = s.bestNextMove(s0.getGame());


        TicTacToe board2 = new TicTacToe("0X_", "_0_", "0XX");
        board2.setOpeningPiece('X');
        MCTS.State expected = new MCTS.State(board2, null);


        Assert.assertEquals(expected, obtained);


    }


    @Test
    public void mctsTest15() throws CloneNotSupportedException, TicTacToeException, MCTSException {

        ITreePolicy treePolicy = new UCT();
//        IRolloutPolicy rolloutPolicy = new RandomUniform();
        IRolloutPolicy rolloutPolicy = new SpecialHeuristic();
        MCTS s = new MCTS(treePolicy, rolloutPolicy);

        TicTacToe board1 = new TicTacToe("0X_", "00_", "_XX");
        board1.setOpeningPiece('X');
        MCTS.State s0 = new MCTS.State(board1, null);

        MCTS.State obtained = s.bestNextMove(s0.getGame());


        TicTacToe board2 = new TicTacToe("0X_", "00_", "XXX");
        board2.setOpeningPiece('X');
        MCTS.State expected = new MCTS.State(board2, null);


        Assert.assertEquals(expected, obtained);
    }


    @Test
    public void mctsTest16() throws CloneNotSupportedException, TicTacToeException, MCTSException {

        ITreePolicy treePolicy = new UCT();
//        IRolloutPolicy rolloutPolicy = new RandomUniform();
        IRolloutPolicy rolloutPolicy = new SpecialHeuristic();
        MCTS s = new MCTS(treePolicy, rolloutPolicy);

        TicTacToe board1 = new TicTacToe("00_", "XX0", "0X_");
        board1.setOpeningPiece('X');
        MCTS.State s0 = new MCTS.State(board1, null);

        MCTS.State obtained = s.bestNextMove(s0.getGame());


        TicTacToe board2 = new TicTacToe("00X", "XX0", "0X_");
        board2.setOpeningPiece('X');
        MCTS.State expected = new MCTS.State(board2, null);


        Assert.assertEquals(expected, obtained);

    }

    @Test
    public void mctsTest17() throws CloneNotSupportedException, TicTacToeException, MCTSException {

        ITreePolicy treePolicy = new UCT();
//        IRolloutPolicy rolloutPolicy = new RandomUniform();
        IRolloutPolicy rolloutPolicy = new SpecialHeuristic();
        MCTS s = new MCTS(treePolicy, rolloutPolicy);

        TicTacToe board1 = new TicTacToe(
                "_X_",
                "0X0",
                "___");
        board1.setOpeningPiece('0');
        MCTS.State s0 = new MCTS.State(board1, null);

        MCTS.State obtained = s.bestNextMove(s0.getGame());


        TicTacToe board2 = new TicTacToe(
                "_X_",
                "0X0",
                "_0_");
        board2.setOpeningPiece('0');
        MCTS.State expected = new MCTS.State(board2, null);


        Assert.assertEquals(expected, obtained);

    }
}