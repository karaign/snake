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
     * Adds the vector to another vector,
     * wrapping around if the sum is over a certain value (modulus)
     * @param that - the vector to add
     * @param nx - the modulus for x coordinate
     * @param ny - the modulus for y coordinate
     * @return the sum of the two vectors in mod-n
     */
    public Vector add (Vector that, int nx, int ny) {
        return new Vector(
                modulus(this.x + that.x, nx),
                modulus(this.y + that.y, ny)
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

    /**
     * Adds the vector to another vector without having to create it first
     * AND using modular arithmetic
     * @param x - the x component of the vector to add
     * @param y - the y component of the vector to add
     * @param nx - the modulus for x coordinate
     * @param ny - the modulus for y coordinate
     * @return the sum of the two vectors
     */
    public Vector add (int x, int y, int nx, int ny) {
        return new Vector(
                modulus(this.x + x, nx),
                modulus(this.y + y, ny)
        );
    }

    /**
     * Modulus function that always returns a positive value
     * e. g. modulus(-1, 100) == 99 and not -1
     */
    private int modulus (int x, int n) {
        return (((x % n) + n) % n);
    }

}
