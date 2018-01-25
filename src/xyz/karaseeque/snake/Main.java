package xyz.karaseeque.snake;

import xyz.karaseeque.snake.borrowedCode.RawConsoleInput;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final int SCR_HEIGHT = 70;

    public static void main(String[] args) throws InterruptedException, IOException {
        // Initialize scanner
        Scanner sc = new Scanner(System.in);

        // Initialize screen cleaner
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < SCR_HEIGHT; i++) {
            builder.append("\n");
        }
        String newlines = builder.toString();

        // Load settings
        Settings settings = Settings.load();

        // Print welcome message
        System.out.println("Welcome to Snake\n" +
                           "Controls: W/A/S/D or arrow keys to move\n" +
                           "Press Ctrl+C to exit\n" +
                           "Press any key to start, or spacebar to change settings\n");

        int mainMenuInput = RawConsoleInput.read(true); // wait for key press

        // settings menu
        if (mainMenuInput == 32) { // spacebar
            // Set whether an empty level will be used, or a level loaded from the disk
            System.out.println("Should an empty level be used? (y/n) \n" +
                               "Current setting: " + settings.useEmptyLevel);
            String next;
            do {
                next = sc.next();
            } while (!next.equals("y") && !next.equals("n"));
            settings.useEmptyLevel = next.equals("y");

            // If an empty level is selected, we can set the width and height
            if (settings.useEmptyLevel) {
                System.out.println("Enter the width of the level\n" +
                                   "Current setting: " + settings.emptyLevelWidth);
                settings.emptyLevelWidth = sc.nextInt();
                System.out.println("Enter the height of the level\n" +
                        "Current setting: " + settings.emptyLevelHeight);
                settings.emptyLevelHeight = sc.nextInt();

            } else { // If we load a level from disk, we need to know its filename
                System.out.println("Enter level name. Go to the 'levels' folder to see available levels!\n" +
                                   "Current setting: " + settings.levelName);
                settings.levelName = sc.next();
            }

            // Finally, configure the difficulty of the game
            System.out.println("Enter how fast the game will move, in milliseconds\n" +
                               "The smaller the number, the harder the game!\n" +
                               "Current setting: " + settings.frameTime);
            settings.frameTime = sc.nextInt();

            // Save the settings
            Settings.save(settings);
        }

        // initialize world
        World world;
        if (settings.useEmptyLevel) {
            world = new World(settings.emptyLevelHeight, settings.emptyLevelWidth);
        } else {
            boolean[][] level = LevelReader.readLevel(settings.levelName);
            world = new World(level);
        }
        // initialize renderer
        Renderer renderer = new Renderer();

        // main game loop
        while (world.continueGame()) {
            // clear screen
            System.out.print(newlines);
            // render world
            System.out.print(renderer.render(world));
            // print score
            System.out.print("SCORE: " + world.getScore());

            // wait some time to receive input
            Thread.sleep(settings.frameTime);
            // read input
            int input = RawConsoleInput.read(false);
            // check input and forward it to World
            if (input == 3) { // Ctrl+C to exit
                break;
            } else if (input == 119 || input == 57416) { // W or up-arrow to move up
                world.setDirection(Direction.UP);
            } else if (input == 115 || input == 57424) { // S or down-arrow to move down
                world.setDirection(Direction.DOWN);
            } else if (input == 97  || input == 57419) { // A or left-arrow to move left
                world.setDirection(Direction.LEFT);
            } else if (input == 100 || input == 57421) { // D or right-arrow to move right
                world.setDirection(Direction.RIGHT);
            }


            // this part is for testing
//            if (input != -2) {
//                System.out.println("Input received: " + input);
//            }
        }

        System.out.println("\nGame over!\n" +
                           "Press any key to exit");
        RawConsoleInput.read(true); // wait for key press
    }
}
