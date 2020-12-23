import java.util.Collections;
import java.util.Comparator;

public class UCT {

    //Tree policy uct ( requires interface)


    public static double computeUCT(int parentVisit, double nodeWinScore, int nodeVisit) {
        double result = 0;
        if (nodeVisit == 0) {
            result = Double.MAX_VALUE; //Node is unvisited which makes it very valuable
        } else {
            double c = Math.sqrt(2); //Random value gotten from the internet
            result = ((nodeWinScore / (double) nodeVisit) + c * Math.sqrt(Math.log(parentVisit) / (double) nodeVisit));
        }
        return result;
    }


    protected static MCTS.State findBestNodeUsingUCT(MCTS.State node, char playerTurn, char player) {
        MCTS.State result;
        int parentVisit = node.getVisitCount();//Total number of visits
        result = Collections.max(node.getChildArray(), Comparator.comparing(c -> computeUCT(
                parentVisit, c.getWinCount(), c.getVisitCount())));
//        System.out.println("player " + playerTurn + " played \n"+ result + "uct = " + computeUCT(parentVisit,result.getWinCount(), result.getVisitCount()));
        return result;
    }


}
