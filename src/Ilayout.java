import java.util.List;

public interface Ilayout {

     int getTurn();

    /**
     * @return the children of the receiver.
     */
    List<Ilayout> children() throws CloneNotSupportedException;


    /**
     * @return the cost for moving from the input config to the receiver.
     */

    int getStatus();//keeping track of ths game state (Progress, win or loss)


}

