package xyz.karaseeque.snake;

import java.util.LinkedList;

/**
 * Represents the playable character of the Snake game.
 */
public class Snake {
    /**
     * Height/width of the level, to make the snake wrap around.
     */
    private int lvlHeight, lvlWidth;

    /**
     * The segments of which the snake's body is composed
     */
    private LinkedList<Vector> segments = new LinkedList<>();

    /**
     * The direction in which the snake moves
     */
    private Vector direction = new Vector(1,0);

    /**
     * Creates a new Snake.
     * @param size the length of the snake
     * @param x the x coordinate of the snake's head
     * @param y the y coordinate of the snake's head
     * @param height - the height of the level
     * @param width - the width of the level
     */
    public Snake (int size, int x, int y, int height, int width) {
        // Initialize head
        Vector head = new Vector(x, y);
        segments.add(head);
        // Initialize tail
        for (int i = 0; i < size; i++) {
            segments.add(segments.getLast().add(-1, 0));
        }
        // Set level width and height
        lvlHeight = height;
        lvlWidth = width;
    }

    /**
     * Changes the direction of the snake
     * @param newDirection - the new direction
     */
    public void setDirection (Vector newDirection) {
        // Make sure the new direction isn't the opposite of the old one
        // (that would make the snake crash into itself immediately)
        if (!direction.add(newDirection).equals(new Vector(0, 0))) {
            direction = newDirection;
        }
    }

    /**
     * @return a list of coordinates of all of the snake's segments
     */
    public LinkedList<Vector> getSegments () {
        return segments;
    }

    /**
     * Makes the snake longer.
     */
    public void grow () {
        Vector last = segments.getLast();
        Vector nextToLast = segments.get(segments.size() - 2);
        segments.addLast(last.add(-nextToLast.x, -nextToLast.y, lvlWidth, lvlHeight));
    }

    /**
     * Moves the snake in the specified direction.
     */
    public void move () {
        segments.addFirst(segments.getFirst().add(direction, lvlWidth, lvlHeight));
        segments.removeLast();
    }
}
