import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class MCTS {

    /**
     * Representation of the board in a given time. State is represented by the layout, the link to the
     * previous state and the cost in which it took from the initial state to the current one.
     *
     * @inv g >= 0
     */
    static class State {

        private IGame game;
        private State father;
        private int visitCount;
        private int winCount;

        public void setGame(IGame game) {
            this.game = game;
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

        public IGame getGame() {
            return game;
        }


        public int getWinStatus() {
            return winStatus;
        }

        /**
         * Constructor
         *
         * @param l - instance of a class that implements the Ilayout interface
         * @param n - state that represents the father or null if it doesnt have any.
         */
        public State(IGame l, State n) {
            game = l;
            father = n;
            visitCount = 0;
            winCount = 0;
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



    private char iAPlayer;
    private final ITreePolicy treePolicy;
    private final IRolloutPolicy rolloutPolicy;


    //Constructor using interfaces
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
     *  @post list of states of the successors of the given state
     */
    public List<State> sucessores(State n) throws CloneNotSupportedException {
        List<State> sucs = new ArrayList<>();
        List<IGame> children = n.game.children();
        for (IGame e : children) {
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
//            System.out.println(status + " and player = " + player);
            result = 1;
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
    final
    public State solve(IGame s) throws CloneNotSupportedException {
        State result;
        iAPlayer = s.getCurrentPlayer();
        long start = System.currentTimeMillis();//Since we'll have two minutes for a game i'm thinking about
        long end = start + 1000; // 5 seconds
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
                actual = RandomUniform.pickRandom(actual.getChildArray());
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

}
