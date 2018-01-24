package xyz.karaseeque.snake;

public class Renderer {
    /**
     * Draws the world using pseudographics.
     * @param world - the world to draw
     * @return A pseudographical representation of the world in its current state.
     */
    public String render (World world) {
        StringBuilder result = new StringBuilder();
        int height = world.getHeight(), width = world.getWidth();

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                String cell;
                switch (world.getCellAt(row, col)) {
                    case FOOD:
                        cell = "@";
                        break;
                    case WALL:
                        cell = "#";
                        break;
                    case HEAD:
                        cell = "O";
                        break;
                    case TAIL:
                        cell = "o";
                        break;
                    case EMPTY: default:
                        cell = " ";
                        break;
                }
                result.append(cell);
            }

            result.append("\n");
        }

        return result.toString();
    }
}
