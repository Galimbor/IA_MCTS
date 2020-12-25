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
        private int player;

        public void setLayout(Ilayout layout) {
            this.layout = layout;
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

        public Ilayout getLayout() {
            return layout;
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
        public State(Ilayout l, State n) {
            layout = l;
            father = n;
            visitCount = 0;
            winCount = 0;
        }

        public State(char[][] board, State n) {
            layout = new Board(board);
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


    public void setPlayer(char player) {
        this.player = player;
    }

    protected Queue<State> abertos;//This might hold the nodes that still need to be seen
    private List<State> fechados; //This might hold the depleted notes(have been completely visited)
    private State actual; // The current node about to me explored?
    private char player;


    /**
     * Sucessores will create and return a list that contains all the possibles layouts given a state n.
     *
     * @param n - state that will be used to get its children.
     * @return list of states.
     * @throws CloneNotSupportedException
     * @pre true.
     * @post list of states that has at least 1 element and a maximum of 4.
     */
    private List<State> sucessores(State n) throws CloneNotSupportedException {
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

    public List<State> sucessores_v2(State n) throws CloneNotSupportedException {
        List<State> sucs = new ArrayList<>();
        List<Coordinate> availablePositions = n.getLayout().getEmptyPositions();
        for (Coordinate c : availablePositions) {
            Board b = new Board(n.getLayout().toString());
            State nn = new State(b, n);
            nn.getLayout().placeMove(c, n.getLayout().getCurrentPlayer());
            sucs.add(nn);
        }
        return sucs;
    }


    //Selection part of MCTS(using UCT)
    public State selection(State root) {
        State node = root;
        while (node.getChildArray().size() != 0) //It has to have children
        {
            node = UCT.findBestNodeUsingUCT(node);//We should use an interface for this
        }
        return node;
    }


    //Expansion
    public void expansion(State node) throws CloneNotSupportedException {
        node.setChildArray(sucessores_v2(node));
        //Need to find a way to say who's playing -- I think it's done ?
        //The state should save that information
    }

    /**
     * Sucessores will create and return a list that contains all the possibles layouts given a state n.
     *
     * @param n - state that will be used to get its children.
     * @return list of states.
     * @throws CloneNotSupportedException
     * @pre true.
     * @post list of states that has at least 1 element and a maximum of 4.
     */
    final private List<State> remove_sucessores(State n) throws CloneNotSupportedException {
        List<State> sucs = new ArrayList<>();
        List<Ilayout> children = n.layout.children();
        for (Ilayout e : children) {
            if (n.father == null || !e.equals(n.father.layout)) {
                State nn = new State(e, null);
                sucs.add(nn);
            }
        }
        return sucs;
    }


    //Simulation using a rollout policy random uniform
    public int simulation(State node) throws CloneNotSupportedException {
        //End result
        int result = 0;

        //Node randomly choosen
        State tempNode = node;

        if (tempNode.getLayout().getStatus().equals("circles win") && player == 'X' || tempNode.getLayout().getStatus().equals("crosses win") && player == '0') {
            tempNode.father.setWinCount(Integer.MIN_VALUE);
            return -1;
        }
        while (tempNode.getLayout().getStatus().equals("in progress")) {
            tempNode = RandomUniform.pickRandom(sucessores_v2(tempNode));
            tempNode.father = null;
        }
        String status = tempNode.getLayout().getStatus();
        if ((status.equals("circles win") && player == '0') || (status.equals("crosses win") && player == 'X')) {
            result = 1;
        } else if (status.equals("draw")) {
        } else {
            result = -1;
        }
        return result;
    }


    //backpropagation
    public void backpropagate(State node, int result) {

        while (node.father != null) {
            if (node.getLayout().getCurrentPlayer() != player) {
                node.setWinCount(node.getWinCount() + result);
            }
            node.setVisitCount(node.getVisitCount() + 1);
            node = node.father;
        }
        if (node.getLayout().getCurrentPlayer() != player) {
            node.setWinCount(node.getWinCount() + result);
        }
        node.setVisitCount(node.getVisitCount() + 1);
    }


    //best_child
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
    public State solve(Ilayout s) throws CloneNotSupportedException {
        State result;
        long start = System.currentTimeMillis();//Since we'll have two minutes for a game i'm thinking about
        long end = start + 1000; // 5 seconds

        //Root of our tree
        State root = new State(s, null);
        while (System.currentTimeMillis() < end) {
            actual = selection(root);
            int simulation_result;
            if (actual.getLayout().getStatus().equals("in progress")) {
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
//            System.out.println(node + "with player " + s.getCurrentPlayer() + " playing, has value of " + UCT.computeUCT(1, node.getWinCount(), node.getVisitCount()));
//            System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
//        }


        result = bestChild(root);
        return result;


    }

}
