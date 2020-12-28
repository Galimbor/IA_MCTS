import org.junit.Assert;
import org.junit.Test;

public class MCTSTests {

////    @Test
////    public void simulationTest() throws CloneNotSupportedException {
////        MCTS mcts = new MCTS();
////        TicTacToe board1 = new TicTacToe("X__", "___", "___");
////        MCTS.State s0 = new MCTS.State(board1, null);
////        System.out.println(mcts.simulation(s0));
////
////    }
////
////    @Test
////    public void selectionTest() throws CloneNotSupportedException {
////        MCTS mcts = new MCTS();
////        TicTacToe board1 = new TicTacToe("X__", "___", "___");
////        MCTS.State s0 = new MCTS.State(board1, null);
////        mcts.expansion(s0);
////        s0.setVisitCount(2);
////    }
////
////    @Test
////    public void backPropagationTest() throws CloneNotSupportedException {
////        MCTS mcts = new MCTS();
////        TicTacToe board1 = new TicTacToe("___", "___", "___");
////        MCTS.State s0 = new MCTS.State(board1, null);
////        mcts.expansion(s0);
////        MCTS.State s1 = s0.getChildArray().get(0);
////        mcts.expansion(s1);
////        s1.getChildArray().get(0).setVisitCount(1);
////        s1.getChildArray().get(1).setVisitCount(1);
////        mcts.backpropagate(s1.getChildArray().get(0), 1);
////        mcts.backpropagate(s1.getChildArray().get(1), 0);
////        System.out.println(s0.getChildArray().get(0).getVisitCount());
////        System.out.println(s0.getChildArray().get(0).getWinCount());
////    }
////
//
//    @Test
//    public void mctsTest() throws CloneNotSupportedException {
//        MCTS mcts = new MCTS();
//        TicTacToe board1 = new TicTacToe("___", "___", "___");
//        board1.setOpeningPiece('X');
//        MCTS.State s0 = new MCTS.State(board1, null);
//        mcts.setPlayer('X');
//        System.out.println(mcts.solve(s0.getLayout()));
//    }
//
//
//    @Test
//    public void mctsTest1() throws CloneNotSupportedException {
//        MCTS mcts = new MCTS();
//        TicTacToe board1 = new TicTacToe("X__", "___", "___");
//        MCTS.State s0 = new MCTS.State(board1, null);
//        mcts.setPlayer('0');
//        System.out.println(mcts.solve(s0.getLayout()));
//    }
//
//    @Test
//    public void mctsTestSuc2() throws CloneNotSupportedException {
//        MCTS mcts = new MCTS();
//        TicTacToe board1 = new TicTacToe("X__", "___", "___");
//        MCTS.State s0 = new MCTS.State(board1, null);
//        System.out.println(mcts.sucessores(s0));
////        System.out.println(mcts.solve(s0.getLayout()));
//    }
//
//    @Test
//    public void mctsTest2() throws CloneNotSupportedException {
//        MCTS mcts = new MCTS();
//        TicTacToe board1 = new TicTacToe("0X_", "___", "___");
//        board1.setOpeningPiece('0');
//        MCTS.State s0 = new MCTS.State(board1, null);
//        mcts.setPlayer('0');
//        System.out.println(mcts.solve(s0.getLayout()));
//    }
//
//    @Test
//    public void mctsTest2_1() throws CloneNotSupportedException {
//        MCTS mcts = new MCTS();
//        TicTacToe board1 = new TicTacToe("0X0", "___", "___");
//        board1.setOpeningPiece('0');
//        MCTS.State s0 = new MCTS.State(board1, null);
//        mcts.setPlayer('X');
//        System.out.println(mcts.solve(s0.getLayout()));
//    }
//
//    @Test
//    public void mctsTest3() throws CloneNotSupportedException {
//        MCTS mcts = new MCTS();
//        TicTacToe board1 = new TicTacToe("X0X", "___", "___");
//        mcts.setPlayer('0');
//        MCTS.State s0 = new MCTS.State(board1, null);
//        System.out.println(mcts.solve(s0.getLayout()));
//    }
//
//    @Test
//    public void mctsTest3_1() throws CloneNotSupportedException {
//        MCTS mcts = new MCTS();
//        TicTacToe board1 = new TicTacToe("X_0", "_0_", "X__");
//        mcts.setPlayer('X');
//        MCTS.State s0 = new MCTS.State(board1, null);
//        System.out.println(mcts.solve(s0.getLayout()));
//    }
//
//
//
//    @Test
//    public void mctsTest4_X() throws CloneNotSupportedException {
//        MCTS mcts = new MCTS();
//        TicTacToe board1 = new TicTacToe("0X_", "_0_", "_X_");
//        board1.setOpeningPiece('X');
//        mcts.setPlayer('X');
//        MCTS.State s0 = new MCTS.State(board1, null);
//        System.out.println(mcts.solve(s0.getLayout()));
//    }
//
//    @Test
//    public void mctsTest4_0() throws CloneNotSupportedException {
//        MCTS mcts = new MCTS();
//        TicTacToe board1 = new TicTacToe("X0_", "_X_", "_0_");
//        mcts.setPlayer('0');
//        board1.setOpeningPiece('0');
//        MCTS.State s0 = new MCTS.State(board1, null);
//        System.out.println(mcts.solve(s0.getLayout()));
//    }
//
//    @Test
//    public void mctsTest4_X_1() throws CloneNotSupportedException {
//        MCTS mcts = new MCTS();
//        TicTacToe board1 = new TicTacToe("0X_", "_0_", "_XX");
//        mcts.setPlayer('0');
//        MCTS.State s0 = new MCTS.State(board1, null);
//        System.out.println(mcts.solve(s0.getLayout()));
//    }
//
//    @Test
//    public void mctsTest4_X_1_1() throws CloneNotSupportedException {
//        MCTS mcts = new MCTS();
//        TicTacToe board1 = new TicTacToe("0X_", "_0_", "0XX");
//        mcts.setPlayer('X');
//        MCTS.State s0 = new MCTS.State(board1, null);
//        System.out.println(mcts.solve(s0.getLayout()));
//    }
//
//    @Test
//    public void mctsTest4_X_1_1_1() throws CloneNotSupportedException {
//        MCTS mcts = new MCTS();
//        TicTacToe board1 = new TicTacToe("0XX", "_0_", "0XX");
//        mcts.setPlayer('0');
//        MCTS.State s0 = new MCTS.State(board1, null);
//        System.out.println(mcts.solve(s0.getLayout()));
//    }
//
//
//
//    @Test
//    public void mctsTest4_1() throws CloneNotSupportedException {
//        MCTS mcts = new MCTS();
//        TicTacToe board1 = new TicTacToe("0X_", "X0_", "_X_");
//        mcts.setPlayer('0');
//        MCTS.State s0 = new MCTS.State(board1, null);
//        System.out.println(mcts.solve(s0.getLayout()));
//    }
//
//
//    @Test
//    public void mctsTest5() throws CloneNotSupportedException {
//        MCTS mcts = new MCTS();
//        TicTacToe board1 = new TicTacToe("0X_", "_0_", "_XX");
//        mcts.setPlayer('0');
//        MCTS.State s0 = new MCTS.State(board1, null);
//        System.out.println(mcts.solve(s0.getLayout()));
//    }
//
//
//    @Test
//    public void mctsTest6() throws CloneNotSupportedException {
//        MCTS mcts = new MCTS();
//        TicTacToe board1 = new TicTacToe("0X_", "00_", "_XX");
//        mcts.setPlayer('X');
//        MCTS.State s0 = new MCTS.State(board1, null);
//        System.out.println(mcts.solve(s0.getLayout()));
//    }
//
//
//    @Test
//    public void mctsTestPorra() throws CloneNotSupportedException {
//        MCTS mcts = new MCTS();
//        TicTacToe board1 = new TicTacToe("00_", "XX0", "0X_");
//        mcts.setPlayer('X');
//        MCTS.State s0 = new MCTS.State(board1, null);
//        System.out.println(mcts.solve(s0.getLayout()));
//    }
//
//    @Test
//    public void mctsTestPorra2() throws CloneNotSupportedException {
//        MCTS mcts = new MCTS();
//        TicTacToe board1 = new TicTacToe(
//                "_X_",
//                "0X0",
//                "___");
//        board1.setOpeningPiece('0'); //TODO opening piece faz tomar decisao errada?
//        mcts.setPlayer('0'); //TODO se trocar player para 0 da resultado certo
//        MCTS.State s0 = new MCTS.State(board1, null);
//        System.out.println(mcts.solve(s0.getLayout()));
//    }


    @Test
    public void mctsTestPorra2() throws CloneNotSupportedException, TicTacToeException {
        ITreePolicy treePolicy = new UCT();
        IRolloutPolicy rolloutPolicy = new RandomUniform();
        MCTS s = new MCTS(treePolicy, rolloutPolicy);
        TicTacToe board1 = new TicTacToe(
                "_X_",
                "0X0",
                "___");
        board1.setOpeningPiece('0'); //TODO opening piece faz tomar decisao errada?
        //TODO se trocar player para 0 da resultado certo
        MCTS.State s0 = new MCTS.State(board1, null);
        Assert.assertEquals(2, board1.getH());

    }

    @Test
    public void mctsTestPorra3() throws CloneNotSupportedException, TicTacToeException {
        ITreePolicy treePolicy = new UCT();
        IRolloutPolicy rolloutPolicy = new RandomUniform();
        MCTS s = new MCTS(treePolicy, rolloutPolicy);
        TicTacToe board1 = new TicTacToe(
                "_X_",
                "0_0",
                "_X_");
        board1.setOpeningPiece('0'); //TODO opening piece faz tomar decisao errada?
        //TODO se trocar player para 0 da resultado certo
        MCTS.State s0 = new MCTS.State(board1, null);
        Assert.assertEquals(0, board1.getH());

    }


}


