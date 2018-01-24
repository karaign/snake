package xyz.karaseeque.snake;

import java.io.*;
import java.util.Scanner;

/**
 * A data structure class that represents the game's configuration.
 * Also contains static methods for loading and saving the settings.
 */
public class Settings {
    /**
     * The filename used to look for the config.
     */
    public static final String FILENAME = ".snake_config.txt";

    /**
     * Tries to read the config from disk. If not found or invalid,
     * returns the default config.
     * A valid config file should contain 5 values separated by semicolons.
     * Each of the integers controls a setting, in the following order:
     *  useEmptyLevel (0 or 1);
     *  emptyLevelWidth (5-50);
     *  emptyLevelHeight (5-50);
     *  levelName (a string);
     *  frameTime (100-1000);
     * @return a Settings object usable for accessing game configuration
     */
    public static Settings load () {
        Settings config = new Settings();
        File configFile = new File(FILENAME);
        Scanner sc = null;
        try {
            sc = new Scanner(configFile);
            sc.useDelimiter(";");
            config.useEmptyLevel = sc.nextInt() != 0;
            config.emptyLevelWidth = sc.nextInt();
            config.emptyLevelHeight = sc.nextInt();
            config.levelName = sc.next();
            config.frameTime = sc.nextInt();
        } catch (FileNotFoundException e) {
            // Print a message to stderr for debug purposes
            System.err.println("settings file not found, using default settings");
        } finally {
            if (sc != null) sc.close();
        }
        return config;
    }

    /**
     * Saves the provided config to disk.
     * @param config the config which is to save
     */
    public static void save (Settings config) throws IOException {
        FileWriter fw = new FileWriter(FILENAME);
        PrintWriter output = new PrintWriter(fw);
        output.print(config.useEmptyLevel ? "1" : "0" + ";");
        output.print(config.emptyLevelWidth + ";");
        output.print(config.emptyLevelHeight + ";");
        output.print(config.levelName + ";");
        output.print(config.frameTime + ";");
        // close the streams
        output.close();
        fw.close();
    }


    /**
     * Whether to use an empty level instead of one loaded from disk
     */
    public boolean useEmptyLevel = true;
    /**
     * Width of the default level
     */
    public int emptyLevelWidth = 15;
    /**
     * Height of the default level
     */
    public int emptyLevelHeight = 15;
    /**
     * The name of the loaded level to use.
     * Levels should be stored in ./levels
     */
    public String levelName = "walled_garden.txt";
    /**
     * The game's difficulty level/speed, which is defined by
     * the waiting time between frames in milliseconds.
     */
    public int frameTime = 200;

}
