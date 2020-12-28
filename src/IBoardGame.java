import java.util.List;

/***
 * Interface IGame. Declared methods that will be needed for a board game
 */
public interface IBoardGame {


    /**
     * Gets a list of class objects that are children of the given object.
     *
     * @return List<IBoardGame> : the children of the receiver.
     */
    List<IBoardGame> children() throws CloneNotSupportedException;


    /***
     * Gets a list of Coordinate objects that represents the empty positions
     * in the board game.
     *
     * @return List<Coordinate> : the empty positions
     */
    List<Point> getEmptyPositions();

    /***
     * Gets the status of the board game
     *
     * @return String: the game state
     */
    String getStatus();

    /***
     * Gets the current player, the player that will make the next move
     *
     * @return char : the player
     */
    char getCurrentPlayer();


    /***
     * Gets a value of an heuristic used to perform better in the game
     *
     * @return int : the value of the calculated heuristic
     */
    int getH();

}

