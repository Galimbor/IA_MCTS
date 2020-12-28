import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/***
 * SpecialHeuristic is a class that stands for a custom rollout policy, so as needed it implements the IRolloutPolicy
 * Interface. It uses the method getH() from the game where the MCTS is being used
 */
public class SpecialHeuristic implements IRolloutPolicy {


    /***
     *
     * @param nodes stands for the List of MCTS State objects
     * @return MCTS State : object that has the highest score with the heuristic
     */
    @Override
    public MCTS.State select(List<MCTS.State> nodes) {
        return Collections.max(nodes, Comparator.comparing(c -> c.getGame().getH()));
    }
}
