package xyz.karaseeque.snake;

import xyz.karaseeque.snake.borrowedCode.RawConsoleInput;

import java.io.IOException;

public class Main {
    private static final int DEFAULT_WIDTH = 15;
    private static final int DEFAULT_HEIGHT = 15;
    private static final int DEFAULT_DIFFICULTY = 200;
    private static final int SCR_HEIGHT = 70;

    public static void main(String[] args) throws InterruptedException, IOException {
        //TODO: add ability to read levels from disk
        //TODO: add option to change speed
        //TODO: store options in a text file

        // Initialize screen cleaner
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < SCR_HEIGHT; i++) {
            builder.append("\n");
        }
        String newlines = builder.toString();

        System.out.println("Welcome to Snake\n" +
                           "Controls: W/A/S/D or arrow keys to move\n" +
                           "Press Ctrl+C to exit\n" +
                           "Press any key to continue\n");

        RawConsoleInput.read(true); // wait for key press

        // main menu

        // initialize world
        World world = new World(DEFAULT_HEIGHT, DEFAULT_WIDTH);
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
            Thread.sleep(DEFAULT_DIFFICULTY);
            // read input
            int input = RawConsoleInput.read(false);
            // check input and forward it to World
            if (input == 3) { // Ctrl+C to exit
                break;
            } else if (input == 119 || input == 57416) { // W or up-arrow to move up
                world.setDirection(Direction.UP);
            } else if (input == 115 || input == 57424) { // S or down-arrow to move down
                world.setDirection(Direction.DOWN);
            } else if (input == 97 || input == 57419) { // A or left-arrow to move left
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
