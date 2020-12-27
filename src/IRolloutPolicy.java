import java.util.List;

public interface IRolloutPolicy {

    MCTS.State select(List<MCTS.State> nodes);

}
