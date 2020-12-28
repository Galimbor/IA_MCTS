import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SpecialHeuristic implements IRolloutPolicy {


    @Override
    public MCTS.State select(List<MCTS.State> nodes) {
        return Collections.max(nodes, Comparator.comparing(c -> c.getGame().getH()));
    }
}
