import java.util.List;
import java.util.Random;

public class RandomUniform implements IRolloutPolicy {

    //Rollout policy RandomUniform(Requires interface)

    public static MCTS.State pickRandom(List<MCTS.State> nodes) {
        int size = nodes.size();
        Random rand = new Random();
        int randomIndex = rand.nextInt(size);
        return nodes.get(randomIndex);
    }


    @Override
    public MCTS.State select(List<MCTS.State> nodes) {
        return pickRandom(nodes);
    }
}
