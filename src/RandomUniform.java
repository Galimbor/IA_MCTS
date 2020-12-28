import java.util.List;
import java.util.Random;


/***
 *  RandomUniform class represents a rollout policy that is based on selecting
 *  a MCTS based on randomness
 */
public class RandomUniform implements IRolloutPolicy {


    /***
     * Selects a random MCTS State object from a List of MCTS State objects
     *
     * @param nodes stands for the List of MCTS State objects
     * @return MCTS State : State object chosen randomly from the list
     */
    public MCTS.State select(List<MCTS.State> nodes) {
        int size = nodes.size();
        Random rand = new Random();
        int randomIndex = rand.nextInt(size);
        return nodes.get(randomIndex);
    }

}
