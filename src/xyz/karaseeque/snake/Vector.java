package xyz.karaseeque.snake;

/**
 * A vector defined by two integer coordinates.
 * Can be used to represent direction or position.
 */
public class Vector {
    /**
     * The horizontal component of the vector
     */
    public int x;

    /**
     * The vertical component of the vector
     */
    public int y;

    /**
     * Creates a new Vector.
     * @param xComponent - the x component of the vector
     * @param yComponent - the y component of the vector
     */
    public Vector (int xComponent, int yComponent) {
        x = xComponent;
        y = yComponent;
    }

    @Override
    public boolean equals (Object other) {
        Vector that = (Vector) other;
        return (this.x == that.x) && (this.y == that.y);
    }

    /**
     * Adds the vector to another vector
     * @param that - the vector to add
     * @return the sum of the two vectors
     */
    public Vector add (Vector that) {
        return new Vector(
                this.x + that.x,
                this.y + that.y
        );
    }

    /**
     * Adds the vector to another vector without having to create it first
     * @param x - the x component of the vector to add
     * @param y - the y component of the vector to add
     * @return the sum of the two vectors
     */
    public Vector add (int x, int y) {
        return new Vector(
          x + this.x,
          y + this.y
        );
    }
}
