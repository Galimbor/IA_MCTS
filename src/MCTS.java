import java.util.*;

class MCTS {
    /**
     * Representation of the board in a given time. State is represented by the layout, the link to the
     * previous state and the cost in which it took from the initial state to the current one.
     *
     * @inv g >= 0
     */
    static class State {

        private Ilayout layout;
        private State father;

        private int visitCount;
        private int winCount;

        private int playerTurn;

        private int winStatus;// thils will the in the board

        private List<State> childArray; //This will be holding the state's childs
        //We'll need it for several iterations of MCTS

        public List<State> getChildArray() {
            return childArray;
        }

        public Ilayout getLayout() {
            return layout;
        }

        /**
         * Constructor
         *
         * @param l - instance of a class that implements the Ilayout interface
         * @param n - state that represents the father or null if it doesnt have any.
         */
        public State(Ilayout l, State n) {
            layout = l;
            father = n;
            if (father != null) {
                playerTurn = (father.playerTurn + l.getTurn()) % 2; //Second player is 1
            } else playerTurn = 0; //First player is 0
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
            return layout.toString();
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
            return layout.equals(state.layout);
        }
    }


    protected Queue<State> abertos;//This might hold the nodes that still need to be seen
    private List<State> fechados; //This might hold the depleted notes(have been completely visited)
    private State actual; // The current node about to me explored?


    /**
     * Sucessores will create and return a list that contains all the possibles layouts given a state n.
     *
     * @param n - state that will be used to get its children.
     * @return list of states.
     * @throws CloneNotSupportedException
     * @pre true.
     * @post list of states that has at least 1 element and a maximum of 4.
     */
    final private List<State> sucessores(State n) throws CloneNotSupportedException {
        List<State> sucs = new ArrayList<>();
        List<Ilayout> children = n.layout.children();
        for (Ilayout e : children) {
            if (n.father == null || !e.equals(n.father.layout)) {
                State nn = new State(e, n);
                sucs.add(nn);
            }
        }
        return sucs;
    }


    //Selection part of MCTS(using UCT)
    private State selection(State root) {
        State node = root;
        while (node.getChildArray().size() != 0) //It has to have children
        {
            node = UCT.findBestNodeUsingUCT(node); //We should use an interface for this
        }
        return node;
    }


    //Expansion
    private void expansion(State node) throws CloneNotSupportedException {

        node.setChildArray(sucessores(node));
        //Need to find a way to say who's playing -- I think it's done ?
        //The state should save that information
    }


    //Simulation using a rollout policy random uniform
    private int simulation(State node) {
        State tempNode = node;
        while (!tempNode.getChildArray().isEmpty()) //Não é isto mas sim END OF THE GAME
        {
            tempNode = RandomUniform.pickRandom(tempNode.getChildArray());
        }
        //TODO return tempNode.getLayout().getStatus(); //This tell me if the leaf is a win or loss
        // or a draw
        return 0;
    }


    //backpropagation
    private void backpropagate(State node, int result) {
        int win = 0;
        if (result == 1) {
            win = 1; //porra feia
        }
        while (node.father != null) {
            node.setWinCount(node.getWinCount() + win);
            node.setVisitCount(node.getVisitCount() + 1);
        }
    }


    //best_child
    private State bestChild(State root) {
        //Returning the root child who has the most visits
        return Collections.max(root.getChildArray(), Comparator.comparing(c -> c.getVisitCount()
        ));
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
    public State solve(Ilayout s) throws CloneNotSupportedException {
        long start = System.currentTimeMillis();//Since we'll have two minutes for a game i'm thinking about
        long end = start + 10000; // 10 seconds

        //Root of our tree
        State root = new State(s, null);

        while (System.currentTimeMillis() < end) {
            actual = selection(root);
            expansion(actual);
            int simulation_result = simulation(actual);
            backpropagate(actual, simulation_result);
        }
        return bestChild(root);

    }
}
