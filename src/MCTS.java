import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/***
 * MCTS (Monte Carlo tree search) class represents the algorithm with the same name which figures out
 * the best move out of a set of moves by Selecting -> Expanding -> Simulating -> Updating the nodes
 * in tree to find the best solution.
 * It has three data members:
 * - iAPlayer : a char that represents the player that is playing accordingly to the MCTS solution
 * - treePolicy : the tree policy that is going to be used along with the MCTS, UCT is the most famous
 * one, it is represented by a class object which implements the ITreePolicy Interface
 * - rolloutPolicy : the rollout policy is used to choose a node after the expansion, MCTS uses a policy
 * based on randomness, but other approaches can be used, it is represented by a class object which
 * implements the IRolloutPolicy
 */

class MCTS {

    private char iAPlayer;
    private final ITreePolicy treePolicy;
    private final IRolloutPolicy rolloutPolicy;

    /***
     * MCTS constructor that receives the policies as arguments
     *
     * @param treePolicy class object that implements the ITreePolicy Interface
     * @param rolloutPolicy class object that implements the IRolloutPolicy Interface
     */
    public MCTS(ITreePolicy treePolicy, IRolloutPolicy rolloutPolicy) {
        this.treePolicy = treePolicy;
        this.rolloutPolicy = rolloutPolicy;
    }


    /***
     * Sucessores will create and return a list that contains all the possibles layouts given a state n.
     *
     * @param n - state that will be used to get its children.
     * @return list of states.
     *  @throws CloneNotSupportedException if the clone method in class TicTacToe has been called to clone an object,
     *  but the object's class does not implement the Cloneable interface.
     *  @pre true
     *  @pos list of states of the successors of the given state
     */
    public List<State> sucessores(State n) throws CloneNotSupportedException {
        List<State> sucs = new ArrayList<>();
        List<IBoardGame> children = n.game.children();
        for (IBoardGame e : children) {
            State nn = new State(e, n);
            sucs.add(nn);
        }
        return sucs;
    }


    /*** Selection
     *
     * @param root
     * @return
     * */
    public State selection(State root) {
        State node = root;
        while (node.getChildArray().size() != 0) //It has to have children
        {
            node = this.treePolicy.select(node);//We should use an interface for this
        }
        return node;
    }

    /***
     *
     * @param node
     * @throws CloneNotSupportedException
     */
    public void expansion(State node) throws CloneNotSupportedException {
        node.setChildArray(sucessores(node));
        //Need to find a way to say who's playing -- I think it's done ?
        //The state should save that information
    }


    /***
     *
     * @param node from where the simulation will start
     * @return the score depending if node reached is a win, loss or a draw
     * @throws CloneNotSupportedException
     */
    public int simulation(State node) throws CloneNotSupportedException {
        //End result
        int result = 0;

        //Node randomly choosen
        State tempNode = node;
        String status = tempNode.getGame().getStatus();
        if ((!status.equals("in progress") && !status.equals("draw")) && iAPlayer == tempNode.getGame().getCurrentPlayer()) {
            node.father.setWinCount(-9999);
//            System.out.println(node.father.getWinCount());
//            System.out.println(node.father + " defeat");
        }
        while (tempNode.getGame().getStatus().equals("in progress")) {
//            One of the big reasons i'll be creating separate interfaces has to do with this..
//            Random uniform receives List<State> instead of a State. I can't set their childArray otherwise it
//            will stay saved in memory..
            tempNode = this.rolloutPolicy.select(sucessores(tempNode));
            tempNode.father = null;
        }
        status = tempNode.getGame().getStatus();
        if (status.equals(String.valueOf(iAPlayer))) {
            int counter = 1;
            while (tempNode.father != null) {
                counter++;
                tempNode = tempNode.father;
            }
//            System.out.println(status + " and player = " + player);

            result = 1 + 1 / counter;
        } else if (status.equals("draw")) {
            //do nothing
        } else {
            result = -1;
        }
        return result;
    }


    /***
     *
     * @param node
     * @param result
     */
    public void backpropagate(State node, int result) {

        while (node.father != null) {
            if (node.getGame().getCurrentPlayer() != iAPlayer) {
                node.setWinCount(node.getWinCount() + result);
            }
            node.setVisitCount(node.getVisitCount() + 1);
            node = node.father;
        }
        if (node.getGame().getCurrentPlayer() != iAPlayer) {
            node.setWinCount(node.getWinCount() + result);
        }
        node.setVisitCount(node.getVisitCount() + 1);
    }


    /***
     *
     * @param root
     * @return
     */
    private State bestChild(State root) {
        //Returning the root child who has the most visits
        return Collections.max(root.getChildArray(), Comparator.comparing(State::getVisitCount));
    }


    /**
     * Given an initial and final configuration, the algorithm will find the shortest path between those two
     * configuration. It uses the Best-First Algorithm.
     *
     * @param s initial layout.
     * @return the iterator of the list that contains the states from the initial to the final layout.
     * @throws CloneNotSupportedException
     * @pre true.
     * @post if no exception has been thrown it should solve the problem and an iterator or null will be returned.
     */
    final public State solve(IBoardGame s) throws CloneNotSupportedException {
        State result;
        this.iAPlayer = s.getCurrentPlayer();
        long start = System.currentTimeMillis();
        long end = start + 25; // 5 seconds
        //Root of our tree
        State root = new State(s, null);
        while (System.currentTimeMillis() < end) {
            // The current node about to me explored?
            State actual = selection(root);
            int simulation_result;
            if (actual.getGame().getStatus().equals("in progress")) {
                expansion(actual);
            }
            if (actual.getChildArray().size() > 0) {
                actual = this.rolloutPolicy.select(actual.getChildArray());
            }
            simulation_result = simulation(actual);
            backpropagate(actual, simulation_result);

        }
//        for (State node : root.getChildArray()
//        ) {
//            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
//            System.out.println(node.getVisitCount());
//            System.out.println(node.getWinCount());
//            System.out.println(node + "with player " + node.getLayout().getCurrentPlayer() + " playing, has value of " + UCT.computeUCT(1, node.getWinCount(), node.getVisitCount()) + " and win count = " + node.getWinCount());
//            System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
//        }

        result = bestChild(root);
        return result;

    }

    /************************ | State Class | *************************

    /**
     * Representation of the board game in a given time. State is represented by the game, the link to the
     * previous state, the number of visits and the number of wins.
     * Those data members are:
     * - game : An object of a class that implements the Interface IBoardGame that represents a board game
     * - father : A State object that is the previous State before it
     * - visitCount : An int that holds the number of times that this node was visited
     * - winCount : An int that holds the score of this node, this depends of how many times it belonged to
     * a winning path
     */
    static class State {

        private IBoardGame game;
        private State father;
        private int visitCount;
        private int winCount;


        /**
         * Constructor
         *
         * @param l - instance of a class that implements the Ilayout interface
         * @param n - state that represents the father or null if it doesnt have any.
         */
        public State(IBoardGame l, State n) {
            game = l;
            father = n;
            visitCount = 0;
            winCount = 0;
        }


        public void setFather(State father) {
            this.father = father;
        }


        public void setWinStatus(int winStatus) {
            this.winStatus = winStatus;
        }

        private int winStatus;// thils will the in the board

        private List<State> childArray = new ArrayList<>();//This will be holding the state's childs
        //We'll need it for several iterations of MCTS

        public List<State> getChildArray() {
            return childArray;
        }

        public IBoardGame getGame() {
            return game;
        }

        public void setGame(IBoardGame game) {
            this.game = game;
        }

        public int getWinStatus() {
            return winStatus;
        }

        public void setChildArray(List<State> childArray) {
            this.childArray = childArray;
        }

        public void setVisitCount(int visitCount) {
            this.visitCount = visitCount;
        }

        public void setWinCount(int winCount) {
            this.winCount = winCount;
        }

        public int getVisitCount() {
            return visitCount;
        }

        public int getWinCount() {
            return winCount;
        }

        /**
         * Return a string the "represents" the state class. It will be represented by the layout.
         *
         * @return string
         */
        public String toString() {
            return game.toString();
        }


        /**
         * Equals override to compare the configurations of a given state layout.
         *
         * @param o - the reference object with which to compare.
         * @return true if this object is the same as the obj argument; false otherwise.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return game.equals(state.game);
        }
    }

}
