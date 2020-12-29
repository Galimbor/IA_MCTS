import java.util.Objects;

/***
 * Point class that represents a location in (x,y) coordinate space, specified in integer precision.
 *
 * It has the following data members:
 * x : The X coordinate of the Point
 * y : The Y coordinate of the Point
 */
public class Point {

    private int x;
    private int y;

    /***
     * Constructs and initializes a point at the origin (0, 0) of the coordinate space.
     */
    public Point() {
        setX(0);
        setY(0);
    }

    /***
     * Constructs and initializes a point at the specified (x,y) location in the coordinate space.
     *
     * @param x The X coordinate of the Point
     * @param y The Y coordinate of the Point
     */
    public Point(int x, int y) {
        setX(x);
        setY(y);
    }

    /***
     * Getter for the x
     *
     * @return int : x coordinate
     */
    public int getX() {
        return x;
    }

    /***
     * Setter for the x
     *
     * @param x The X coordinate of the Point
     * @pos this.x = x
     */
    public void setX(int x) {
        this.x = x;
    }

    /***
     * Getter for the y
     *
     * @return int : y coordinate
     */
    public int getY() {
        return y;
    }

    /***
     * Setter for the y
     *
     * @param y The X coordinate of the Point
     * @pos this.y = y
     */
    public void setY(int y) {
        this.y = y;
    }

    /***
     * The string representation of the object.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * Verifies if an object is a Point and if so, then verifies if the two coordinates are equal
     *
     * @param o other object to be compared
     * @return boolean : true if equals, false if not equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point that = (Point) o;
        return x == that.x && y == that.y;
    }


    /***
     * Returns a hash code value for the object.
     *
     * @return int : hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
