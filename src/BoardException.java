/**
 * Exception for the BoardException class in case violates the contract made by SentinelLL.
 */

public class BoardException extends Exception {

    private final String message;

    /**
     * Inherit the methods of the super class Exception and set the message field.
     *
     * @param message message to be printed along with toString method, for readability's sake.
     */
    public BoardException(String message) {
        super();
        this.message = message;
    }

    /**
     * Converts the BoardException to a String representation.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "BoardException: " + message;
    }
}
