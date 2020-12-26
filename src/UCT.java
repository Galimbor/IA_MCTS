import java.util.Collections;
import java.util.Comparator;

public class UCT {

    //Tree policy uct ( requires interface)


    public static double computeUCT(int parentVisit, double nodeWinScore, int nodeVisit) {
        double result;
        if (nodeVisit == 0) {
            result = Integer.MAX_VALUE; //Node is unvisited which makes it very valuable
        } else {
            double c = 1.41; //Random value gotten from the internet
            result = ((nodeWinScore / (double) nodeVisit) + c * Math.sqrt(Math.log(parentVisit) / (double) nodeVisit));
        }
        return result;
    }


    protected static MCTS.State findBestNodeUsingUCT(MCTS.State node) {
        MCTS.State result;
        int parentVisit = node.getVisitCount();//Total number of visits
        result = Collections.max(node.getChildArray(), Comparator.comparing(c -> computeUCT(
                parentVisit, c.getWinCount(), c.getVisitCount())));
        return result;
    }


}
