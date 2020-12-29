import java.util.Collections;
import java.util.Comparator;

/***
 * UCT is the class that holds the algorithm Upper Confidence bounds applied to Trees
 * applied to the Monte-Carlo Tree Search. Its used to know that if the given arm will
 * be optimal.
 * It has the following data member:
 * -c the exploration weight parameter
 */
public class UCT implements ITreePolicy {


    private final static double c = 0.6;

    /***
     * Calculate the UCT value for the given MCTS state
     *
     * @param parentVisit stands for the total number of simulations that the parent stat
     *                    has gone through
     * @param nodeWinScore win score, directly related to the number of wins of the state
     * @param nodeVisit number of simulations that the current state has gone through
     * @return double : UCT score
     */
    public static double calculateUCT(int parentVisit, double nodeWinScore, int nodeVisit) {
        double result;
        if (nodeVisit == 0) {
            result = Integer.MAX_VALUE; //Node is unvisited which makes it very valuable
        } else {
            result = ((nodeWinScore / (double) nodeVisit) + c * Math.sqrt(Math.log(parentVisit) / (double) nodeVisit));
        }
        return result;
    }


    /***
     * Select the child of the given state that has the maximum UCT score
     *
     * @param node MCTS state
     * @return MCTS State : the child state that has the higher UCT score
     */
    @Override
    public MCTS.State select(MCTS.State node) {
        MCTS.State result;
        int parentVisit = node.getVisitCount();//Total number of visits
        result = Collections.max(node.getChildArray(), Comparator.comparing(c -> calculateUCT(
                parentVisit, c.getWinCount(), c.getVisitCount())));
        return result;
    }

}
