package gameoflife.world;

public interface ReadOnlyWorld
{
    /**
     * @return width of world
     */
    int getWidth();
    
    /**
     * @return height of world
     */
    int getHeight();
    
    /**
     * @return if world is toroidal
     */
    boolean isToroidal();
    
    /**
     * Get state of a cell in the world.
     * @param x
     * @param y
     * @return true if cell exist and is alive, false otherwise
     */
    boolean isAlive(int x, int y);
    
    /**
     * Get state of a cell in last generation.
     * @param x
     * @param y
     * @return true if cell exist and was alive, false otherwise
     */
    boolean wasAlive(int x, int y);
    
    /**
     * Computes if a cell will be alive in next generation.
     * @param x
     * @param y
     * @return true or false depending of neighbourhood current state
     */
    boolean willBeAlive(int x, int y);
    
    /**
     * @param x
     * @param y
     * @return number of alive neighbours of cell
     */
    int countNeighbours(int x, int y);
}
