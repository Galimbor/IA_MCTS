import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/***
 * MCTS (Monte Carlo tree search) class represents the algorithm with the same name which figures out
 * the best move out of a set of moves by Selecting -> Expanding -> Simulating -> Updating the nodes
 * in tree to find the best solution.
 * It has three data members:
 *
 * - iAPlayer : a char that represents the player that is playing accordingly to the MCTS solution
 * - treePolicy : the tree policy that is going to be used along with the MCTS, UCT is the most famous
 * one, it is represented by a class object which implements the ITreePolicy Interface
 * - rolloutPolicy : the rollout policy is used to choose a node after the expansion, MCTS uses a policy
 * based on randomness, but other approaches can be used, it is represented by a class object which
 * implements the IRolloutPolicy
 */

class MCTS {

    private char iAPlayer;
    private ITreePolicy treePolicy;
    private IRolloutPolicy rolloutPolicy;


    /***
     * MCTS constructor that receives the policies as arguments
     *
     * @param treePolicy class object that implements the ITreePolicy Interface
     * @param rolloutPolicy class object that implements the IRolloutPolicy Interface
     */
    public MCTS(ITreePolicy treePolicy, IRolloutPolicy rolloutPolicy) {
        setTreePolicy(treePolicy);
        setRolloutPolicy(rolloutPolicy);
    }


    /***
     * Getter for the field iAPlayer
     *
     * @return char iAPlayer
     */
    public char getiAPlayer() {
        return iAPlayer;
    }


    /***
     * Setter for the field iAPlayer
     * @param player a char that represents the player that is playing accordingly to the MCTS solution
     * @post this.iAPlayer = iAPlayer
     */
    public void setiAPlayer(char player) {
        this.iAPlayer = player;
    }


    /***
     * Getter for the treepolicy field
     *
     * @return an object from class that implements ITreePolicy
     */
    private ITreePolicy getTreePolicy() {
        return treePolicy;
    }


    /***
     * Setter for the treepolicy field
     *
     * @param treePolicy he tree policy that is going to be used along with the MCTS, UCT is the most famous
     * one, it is represented by a class object which implements the ITreePolicy Interface
     * @post this.treePolicy = treePolicy
     */
    public void setTreePolicy(ITreePolicy treePolicy) {
        this.treePolicy = treePolicy;
    }


    /***
     * Getter for the rolloutPolicy field
     *
     * @return an object from class that implements IRolloutPolicy
     */
    private IRolloutPolicy getRolloutPolicy() {
        return rolloutPolicy;
    }


    /***
     * Setter for the rolloutPolicy field
     *
     * @param rolloutPolicy the rollout policy is used to choose a node after the expansion
     * @post this.rolloutPolicy = rolloutPolicy
     */
    public void setRolloutPolicy(IRolloutPolicy rolloutPolicy) {
        this.rolloutPolicy = rolloutPolicy;
    }


    /***
     * Sucessors will create and return a list that contains all the possibles layouts given a state n.
     *
     * @param n - state that will be used to get its children.
     * @return list of states.
     * @throws CloneNotSupportedException if the clone method in class of the object has been called to clone an object,
     *  but the object's class does not implement the Cloneable interface.
     * @pre true
     * @post list of states of the successors of the given state
     */
    public List<State> sucessors(State n) throws CloneNotSupportedException {
        List<State> sucs = new ArrayList<>();
        List<IBoardGame> children = n.game.children();
        for (IBoardGame e : children) {
            State nn = new State(e, n);
            sucs.add(nn);
        }
        return sucs;
    }

    /***
     * Sucessors_temp will create and return a list that contains all the possibles games given a state n but without
     * father set to null, making them temporary.
     *
     * @param n - state that will be used to get its children.
     * @return list of states.
     * @throws CloneNotSupportedException if the clone method in class of the object has been called to clone an object,
     *  but the object's class does not implement the Cloneable interface.
     * @pre true
     * @post list of states of the successors of the given state
     */
    public List<State> sucessors_temp(State n) throws CloneNotSupportedException {
        List<State> sucs = new ArrayList<>();
        List<IBoardGame> children = n.game.children();
        for (IBoardGame e : children) {
            State nn = new State(e, null);
            sucs.add(nn);
        }
        return sucs;
    }


    /*** Selection is a process that from root State and select successive child States according to the tree policy,
     * until a leaf node is reached.
     *
     * @param root State from where the MCTS is starting
     * @return State : A leaf node that was chosen by the tree policy
     * */
    public State selection(State root) {
        State node = root;
        while (node.getChildArray().size() != 0) {
            node = this.treePolicy.select(node);
        }
        return node;
    }


    /***
     * Expansion creates a list of children of the given State and sets the list as his children
     *
     * @param node State given to be expanded
     * @throws CloneNotSupportedException if the clone method in object class has been called to clone an object,
     * but the object's class does not implement the Cloneable interface.
     */
    public void expansion(State node) throws CloneNotSupportedException {
        node.setChildArray(sucessors(node));
    }


    /***
     * Simulation class completes a match of the game from the given State.
     * It uses a rollout policy to choose the next State until it reaches a final State.
     *
     * @param node from where the simulation will start
     * @pre board game getStatus() method must return "in progress", "draw" and in case of victory a String that indicates
     * which player as won
     * @post returns a double that holds the score depending if node reached is a win, loss or a draw
     * @throws CloneNotSupportedException if the clone method in object class has been called to clone an object,
     * but the object's class does not implement the Cloneable interface.
     */
    public double simulation(State node) throws CloneNotSupportedException {
        double result = 0;

        int depth = 1;
        State tempNode = node;
        while (tempNode.father != null) {
            depth++;
            tempNode = tempNode.father;
        }

        tempNode = node;
        String status = tempNode.getGame().getStatus();
        if ((!status.equals("in progress") && !status.equals("draw")) && iAPlayer == tempNode.getGame().getCurrentPlayer()) {
            node.father.setWinCount(-9999);
        }
        while (tempNode.getGame().getStatus().equals("in progress")) {
            depth++;
            tempNode = this.rolloutPolicy.select(sucessors_temp(tempNode));
            tempNode.setFather(null);
        }

        status = tempNode.getGame().getStatus();
        if (status.equals(String.valueOf(iAPlayer))) {
            result = 1 + 1.0 / depth;
        } else if ((!status.equals("draw") && !status.equals("in progress")) && !status.equals(String.valueOf(iAPlayer))) {
            result = -1;
        }

        return result;
    }


    /***
     * Backpropagate class is the responsible for updating the values visitCount and winCount,
     * it uses the result of the simulation to update the nodes on the path to the root State
     *
     * @param node State where the backpropagation will start
     * @param score double that holds the value given by simulation
     */
    public void backPropagate(State node, double score) {

        while (node.father != null) {
            if (node.getGame().getCurrentPlayer() != iAPlayer) {
                node.setWinCount(node.getWinCount() + score);
            }
            node.setVisitCount(node.getVisitCount() + 1);
            node = node.father;
        }
        if (node.getGame().getCurrentPlayer() != iAPlayer) {
            node.setWinCount(node.getWinCount() + score);
        }
        node.setVisitCount(node.getVisitCount() + 1);
    }


    /***
     * Chooses the child of the given State that has most visits, this means the best promising State
     *
     * @param root State that represents the actual State of the game
     * @return State that is the child of root State that has the highest visitCount
     */
    private State bestChild(State root) {
        return Collections.max(root.getChildArray(), Comparator.comparing(State::getVisitCount));
    }


    /**
     * @param s initial layout.
     * @return the iterator of the list that contains the states from the initial to the final layout.
     * @throws CloneNotSupportedException if the clone method in object class has been called to clone an object,
     *                                    but the object's class does not implement the Cloneable interface.
     * @throws MCTSException              if there is no possible solution, which will happen if the given State has a completed match
     *                                    of the game
     * @pre game is still in progress
     * @post returns the best next move if no Exceptions are thrown
     */
    final public State bestNextMove(IBoardGame s) throws CloneNotSupportedException, MCTSException {
        this.setiAPlayer(s.getCurrentPlayer());
        long start = System.currentTimeMillis();
        long end = start + 2000;
        State root = new State(s, null);

        while (System.currentTimeMillis() < end) {
            State actual = selection(root);
            double simulation_result;
            if (actual.getGame().getStatus().equals("in progress")) {
                expansion(actual);
            }
            if (actual.getChildArray().size() > 0) {
                actual = this.rolloutPolicy.select(actual.getChildArray());
            }
            simulation_result = simulation(actual);
            backPropagate(actual, simulation_result);
        }
//        for (State node : root.getChildArray()
//        ) {
//            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
//            System.out.println(node.getVisitCount());
//            System.out.println(node.getWinCount());
//            System.out.println(node + "with player " + node.getGame().getCurrentPlayer() + " playing, has value of " + UCT.calculateUCT(1, node.getWinCount(), node.getVisitCount()) + " and win count = " + node.getWinCount());
//            System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
//        }
        if (root.getChildArray().size() > 0)
            return bestChild(root);
        else {
            throw new MCTSException("It is not possible to make a next move");
        }
    }


    /************************ | State Class | *************************



     /**
     * Representation of the board game in a given time. State is represented by the game, the link to the
     * previous state, the number of visits and the number of wins.
     * Those data members are:
     * - game : An object of a class that implements the Interface IBoardGame that represents a board game
     * - father : A State object that is its previous State
     * - visitCount : An int that holds the number of times that this node was visited
     * - winCount : A double that holds the score of this node, this depends of how many times it belonged to
     * a winning path
     * - childArray : A list of the children of the State, by other words, the next possible moves
     */
    static class State {

        private IBoardGame game;
        private State father;
        private int visitCount;
        private double winCount;
        private List<State> childArray = new ArrayList<>();


        /**
         * Constructor
         *
         * @param l - instance of a class that implements the Ilayout interface
         * @param n - state that represents the father or null if it doesnt have any.
         */
        public State(IBoardGame l, State n) {
            setGame(l);
            setFather(n);
            setVisitCount(0);
            setWinCount(0);
        }


        /***
         * Getter for the game field
         *
         * @return IBoardGame game
         */
        public IBoardGame getGame() {
            return game;
        }


        /***
         * Setter for the game field
         *
         * @param game An object of a class that implements the Interface IBoardGame that represents a board game
         * @post this.game = game
         */
        public void setGame(IBoardGame game) {
            this.game = game;
        }


        /***
         * Getter for the field father
         *
         * @return State father
         */
        public State getFather() {
            return father;
        }


        /***
         * Setter for the field father
         *
         * @param father A State object that is its previous State
         * @post this.father = father
         */
        public void setFather(State father) {
            this.father = father;
        }


        /***
         * Getter for the field visitCount
         *
         * @return int visitCount
         */
        public int getVisitCount() {
            return visitCount;
        }


        /***
         * Setter for the field visitCount
         *
         * @param visitCount An int that holds the number of times that this node was visited
         * @post this.visitCount = visitCount
         */
        public void setVisitCount(int visitCount) {
            this.visitCount = visitCount;
        }


        /***
         * Getter for the field winCount
         *
         * @return double winCount
         */
        public double getWinCount() {
            return winCount;
        }


        /***
         * Setter for the field winCount
         *
         * @param winCount A double that holds the score of this node, this depends of how many times it belonged to
         * a winning path
         */
        public void setWinCount(double winCount) {
            this.winCount = winCount;
        }


        /***
         * Getter for the childArray field
         *
         * @return List childArray
         */
        public List<State> getChildArray() {
            return childArray;
        }


        /***
         * Setter for the childArray field
         *
         * @param childArray A list of the children of the State, by other words, the next possible moves
         * @post this.childArray = childArray
         */
        public void setChildArray(List<State> childArray) {
            this.childArray = childArray;
        }


        /***
         * The string representation of the object, we found that is more useful to pass the toString() method of the game
         * since it shows the actual state of the game
         *
         * @return String
         */
        public String toString() {
            return game.toString();
        }


        /***
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
