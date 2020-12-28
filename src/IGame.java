import java.util.List;

public interface IGame {


    /**
     * @return the children of the receiver.
     */
    List<IGame> children() throws CloneNotSupportedException;


    /***
     *
     * @return the empty positions
     */
    List<Coordinate> getEmptyPositions();

    /***
     *
     * @return the game state (in progress, circles win, crosses win, draw)
     */
    String getStatus();

    /***
     *
     * @return the player that will make the next move
     */
    char getCurrentPlayer();


    //Returns the value following a certain heuristic
    int getH();

}

