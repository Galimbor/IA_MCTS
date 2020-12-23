import org.junit.Test;

public class MCTSTests {

//    @Test
//    public void simulationTest() throws CloneNotSupportedException {
//        MCTS mcts = new MCTS();
//        Board board1 = new Board("X__", "___", "___");
//        MCTS.State s0 = new MCTS.State(board1, null);
//        System.out.println(mcts.simulation(s0));
//
//    }
//
//    @Test
//    public void selectionTest() throws CloneNotSupportedException {
//        MCTS mcts = new MCTS();
//        Board board1 = new Board("X__", "___", "___");
//        MCTS.State s0 = new MCTS.State(board1, null);
//        mcts.expansion(s0);
//        s0.setVisitCount(2);
//    }
//
//    @Test
//    public void backPropagationTest() throws CloneNotSupportedException {
//        MCTS mcts = new MCTS();
//        Board board1 = new Board("___", "___", "___");
//        MCTS.State s0 = new MCTS.State(board1, null);
//        mcts.expansion(s0);
//        MCTS.State s1 = s0.getChildArray().get(0);
//        mcts.expansion(s1);
//        s1.getChildArray().get(0).setVisitCount(1);
//        s1.getChildArray().get(1).setVisitCount(1);
//        mcts.backpropagate(s1.getChildArray().get(0), 1);
//        mcts.backpropagate(s1.getChildArray().get(1), 0);
//        System.out.println(s0.getChildArray().get(0).getVisitCount());
//        System.out.println(s0.getChildArray().get(0).getWinCount());
//    }
//

    @Test
    public void mctsTest() throws CloneNotSupportedException {
        MCTS mcts = new MCTS();
        Board board1 = new Board("___", "___", "___");
        MCTS.State s0 = new MCTS.State(board1, null);
        mcts.setPlayer('X');
        System.out.println(mcts.solve(s0.getLayout()));
    }


    @Test
    public void mctsTest1() throws CloneNotSupportedException {
        MCTS mcts = new MCTS();
        Board board1 = new Board("X__", "___", "___");
        MCTS.State s0 = new MCTS.State(board1, null);
        mcts.setPlayer('0');
        System.out.println(mcts.solve(s0.getLayout()));
    }

    @Test
    public void mctsTest2() throws CloneNotSupportedException {
        MCTS mcts = new MCTS();
        Board board1 = new Board("X_0", "___", "___");
        MCTS.State s0 = new MCTS.State(board1, null);
        mcts.setPlayer('X');
        System.out.println(mcts.solve(s0.getLayout()));
    }

    @Test
    public void mctsTest3() throws CloneNotSupportedException {
        MCTS mcts = new MCTS();
        Board board1 = new Board("X_0", "___", "X__");
        mcts.setPlayer('0');
        MCTS.State s0 = new MCTS.State(board1, null);
        System.out.println(mcts.solve(s0.getLayout()));
    }

    @Test
    public void mctsTest3_1() throws CloneNotSupportedException {
        MCTS mcts = new MCTS();
        Board board1 = new Board("X_0", "_0_", "X__");
        mcts.setPlayer('X');
        MCTS.State s0 = new MCTS.State(board1, null);
        System.out.println(mcts.solve(s0.getLayout()));
    }



    @Test
    public void mctsTest4() throws CloneNotSupportedException {
        MCTS mcts = new MCTS();
        Board board1 = new Board("0X_", "_0_", "_X_");
        mcts.setPlayer('X');
        MCTS.State s0 = new MCTS.State(board1, null);
        System.out.println(mcts.solve(s0.getLayout()));
    }

    @Test
    public void mctsTest4_1() throws CloneNotSupportedException {
        MCTS mcts = new MCTS();
        Board board1 = new Board("0X_", "X0_", "_X_");
        mcts.setPlayer('0');
        MCTS.State s0 = new MCTS.State(board1, null);
        System.out.println(mcts.solve(s0.getLayout()));
    }


    @Test
    public void mctsTest5() throws CloneNotSupportedException {
        MCTS mcts = new MCTS();
        Board board1 = new Board("0X_", "_0_", "_XX");
        mcts.setPlayer('0');
        MCTS.State s0 = new MCTS.State(board1, null);
        System.out.println(mcts.solve(s0.getLayout()));
    }


    @Test
    public void mctsTest6() throws CloneNotSupportedException {
        MCTS mcts = new MCTS();
        Board board1 = new Board("0X_", "00_", "_XX");
        mcts.setPlayer('X');
        MCTS.State s0 = new MCTS.State(board1, null);
        System.out.println(mcts.solve(s0.getLayout()));
    }




}


