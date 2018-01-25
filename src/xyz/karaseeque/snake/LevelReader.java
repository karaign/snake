package xyz.karaseeque.snake;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LevelReader {
    /**
     * The file extension for level files
     */
    private static final String EXTENSION = "txt";

    /**
     * The directory where to look for levels
     */
    private static final String DIRECTORY = "levels";

    /**
     * The maximum dimension allowed for the level.
     */
    private static final int MAX_SIZE = 50;

    /**
     * Reads a file from the levels directory and converts
     * it to a 2D array of booleans representing walls.
     * # means wall, space (or any other character) means empty space
     * @param name - the name of the needed level
     * @return a 2D boolean array ([row][column]), where true represents a wall
     * and false represents an empty space.
     */
    public static boolean[][] readLevel (String name) throws FileNotFoundException {
        File file = new File(System.getProperty("user.dir") // get the root directory of the project
                + "/" + DIRECTORY + "/" + name + "." + EXTENSION);
        Scanner sc = new Scanner(file);
        // Read the level as an array of strings first
        String[] map = new String[MAX_SIZE];
        int currentRow = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            map[currentRow++] = line;
        }
        sc.close();
        // Create the level with necessary width and height
        int lvlHeight = currentRow;
        int lvlWidth = map[0].trim().length();
        boolean[][] level = new boolean[lvlHeight][lvlWidth];
        // Construct the level
        for (int row = 0; row < lvlHeight; row++) {
            for (int col = 0; col < lvlWidth; col++) {
                if (map[row].charAt(col) == '#') {
                    level[row][col] = true;
                }
            }
        }
        return level;
    }
}
