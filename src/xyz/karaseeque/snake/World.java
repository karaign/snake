package xyz.karaseeque.snake;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * Represents a two-dimensional world in the Snake game.
 */
public class World {
    /**
     * Represents the walls of the level.
     * False means empty space, true means wall.
     * The convention used is array[row][column].
     */
    private boolean[][] walls;

    /**
     * The snake that exists in the world.
     */
    private Snake snake;

    /**
     * Represents the position of the food cell.
     */
    private Vector food = new Vector(-1, -1);

    /**
     * The Random instance to be used in the class
     */
    private Random rand = new Random();

    /**
     * The player's score, equal to the number of @'s they collected
     */
    private int score = 0;

    /**
     * @return the level's height
     */
    public int getHeight () {
        return walls.length;
    }

    /**
     * @return the level's width
     */
    public int getWidth () {
        return walls[0].length;
    }

    /**
     * Gets the content of a specified cell
     * @param row - the y coordinate of the cell (the row)
     * @param col - the x coordinate of the cell (the column)
     * @return the content of the cell
     */
    public Cell getCellAt (int row, int col) {
        LinkedList segments = snake.getSegments();
        Vector testVec = new Vector(col, row);
        if (segments.getFirst().equals(testVec)) {
            return Cell.HEAD;
        } else if (segments.contains(testVec)) {
            return Cell.TAIL;
        } else if (food.equals(testVec)) {
            return Cell.FOOD;
        } else if (walls[row][col]) {
            return Cell.WALL;
        } else {
            return Cell.EMPTY;
        }
    }

    /**
     * Puts a FOOD in a random empty cell.
     */
    private void placeFood () {
        // the list of all empty cells as vectors
        ArrayList<Vector> empties = new ArrayList<>();

        // go through the cells to find the ones that are empty
        int width = getWidth(), height = getHeight();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (getCellAt(row, col) == Cell.EMPTY) {
                    empties.add(new Vector(col, row));
                }
            }
        }

        // pick a random empty cell and put the food there
        Vector randCell = empties.get(rand.nextInt(empties.size() + 1));
        food.x = randCell.x;
        food.y = randCell.y;
    }

    /**
     * Creates a World using specified array as a wall map.
     * @param m - the array to be used
     */
    public World (boolean[][] m) {
        // initialize walls
        walls = m;
        // initialize snake
        snake = new Snake(3, getHeight() / 2, getWidth() / 2, getHeight(), getWidth());
        placeFood();
    }

    /**
     * Creates an empty (no walls) level.
     * @param height - the height of the resulting level
     * @param width - the width of the resulting level
     */
    public World (int height, int width) {
        this(new boolean[height][width]);
        // fill the walls with empty spaces
        for (boolean[] col : walls) {
            for (int i = 0; i < col.length; i++) {
                col[i] = false;
            }
        }
    }

    /**
     * Moves the game world one step forward.
     * @return false if game over, true otherwise
     */
    public boolean continueGame () {
        snake.move();
        // Get snake's head position
        Vector head = snake.getSegments().getFirst();

        // Check for collisions with walls
        if (walls[head.y][head.x]) {
            return false;
        }

        // Check for collision with snake's tail
        for (Vector segment : snake.getSegments()) {
            if (segment != head && segment.equals(head)) {
                return false;
            }
        }

        // Check for collision with food
        if (head.equals(food)) {
            placeFood();
            snake.grow();
            score++;
        }

        return true;
    }

    /**
     * Changes the snake's direction.
     */
    public void setDirection (Direction dir) {
        Vector newDirection = new Vector(0, 0);
        switch (dir) {
            case UP:
                newDirection.y = -1;
                break;
            case DOWN:
                newDirection.y = +1;
                break;
            case LEFT:
                newDirection.x = -1;
                break;
            case RIGHT:
                newDirection.x = +1;
                break;
        }
        snake.setDirection(newDirection);
    }

    /**
     * @return the player's score
     */
    public int getScore () {
        return score;
    }
}
