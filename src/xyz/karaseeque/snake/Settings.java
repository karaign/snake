package xyz.karaseeque.snake;

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
     * @return a Settings object usable for accessing game configuration
     */
    public static Settings load () {
        Settings config = new Settings();
        // TODO: read from disk
        return config;
    }

    /**
     * Saves the provided config to disk.
     * @param settings the config which is to save
     */
    public static void save (Settings settings) {
        // TODO: save to disk
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
    public String levelName = "";
    /**
     * The game's difficulty level/speed, which is another way to define
     * the waiting time between frames in milliseconds.
     */
    public int speed = 200;

}
