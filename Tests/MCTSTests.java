import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MCTSTests {

    @Test
    public void simulationTest() throws CloneNotSupportedException {
        MCTS mcts = new MCTS();
        Board board1 = new Board("X__", "___", "___");
        MCTS.State s0 = new MCTS.State(board1, null);
        s0.setMainPiece('X');
        System.out.println(mcts.simulation(s0));


    }
}
