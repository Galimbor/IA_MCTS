/***
 * Interface ITreePolicy. Responsible for declaring the methods that will be needed for a Tree Policy
 * class.
 */
public interface ITreePolicy {

    /**
     * Gets the MCTS State from a given MCTS State depending on the policy implemented
     *
     * @param node MCTS State
     * @return MCTS State
     */
    MCTS.State select(MCTS.State node);

}
