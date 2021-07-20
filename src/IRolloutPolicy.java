import java.util.List;

/***
 * Interface IRolloutPolicy. Responsible for declaring the methods that will be needed for a Rollout Policy
 * class.
 */
public interface IRolloutPolicy {

    /***
     * Selects MCTS State object from a List of MCTS State objects
     *
     * @param nodes stands for the List of MCTS State objects
     * @return MCTS State : State object chosen from the list
     */
    MCTS.State select(List<MCTS.State> nodes);

}
