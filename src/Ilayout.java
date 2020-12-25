import java.util.List;

public interface Ilayout {





    /**
     * @return the children of the receiver.
     */
    List<Ilayout> children() throws CloneNotSupportedException;


    List<Coordinate> getEmptyPositions();

    /**
     * @return the cost for moving from the input config to the receiver.
     */

    String getStatus();//keeping track of ths game state (Progress, win or loss)

    char getCurrentPlayer();

    void placeMove(Coordinate c,char move);


}

