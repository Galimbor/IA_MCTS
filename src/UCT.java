import java.util.Collections;
import java.util.Comparator;

public class UCT {

    //Tree policy uct ( requires interface)


    public static double computeUCT(int parentVisit, double nodeWinScore, int nodeVisit)
    {
        if(nodeVisit == 0)
        {
            return Double.MAX_VALUE; //Node is unvisited which makes it very valuable
        }
        double c = 1.41; //Random value gotten from the internet
        return (nodeWinScore / (double) nodeVisit) + c * Math.sqrt(Math.log(parentVisit) / (double) nodeVisit);
    }


    protected static MCTS.State findBestNodeUsingUCT(MCTS.State node)
    {
        int parentVisit = node.getVisitCount();//Total number of visits

        return Collections.max(node.getChildArray(), Comparator.comparing(c -> computeUCT(
                parentVisit,c.getWinCount(), c.getVisitCount()
        )));
    }



}
