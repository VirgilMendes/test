package gameoflife.world;

import java.util.Random;

import gameoflife.displayer.Displayer;

public interface WorldManipulator
{
    /**
     * Set randomly alive cells on the world.<br>
     * A cell will be alive if random number between 0 and 1 is less than specified density.
     * @param density between 0 and 1
     * @return number of cells alive in the world (will be around width*height*density)
     * @throws IllegalArgumentException if density is not between 0 and 1
     */
    int shuffle(double density) throws IllegalArgumentException;
    
    /**
     * Set randomly alive cells on the world.<br>
     * A cell will be alive if random number between 0 and 1 is less than specified density.
     * @param randomizator
     * @param density between 0 and 1
     * @return number of cells alive in the world (will be around width*height*density)
     * @throws IllegalArgumentException if density is not between 0 and 1
     */
    int shuffle(Random random, double density) throws IllegalArgumentException;
    
    /**
     * Set state of a cell in the world.
     * @param x
     * @param y
     * @param alive if cell is alive
     */
    void setAlive(int x, int y, boolean isAlive);
    
    /**
     * Computes a step on evolution of the world.
     * @return number of cells alive in the new generation
     */
    int nextGeneration();
    
    /**
     * Will run game of life during many generations.<br>
     * Run will be stop if all cells are died or if max  number of step is reached.<br>
     * If a displayer is set, all generations will be display.
     * @param nbStep max number of generations to run
     * @return number of generations that effectively had run (<= nbStep)
     */
    int run(int nbStep);
    
    /**
     * Set how display each state world when run.
     * @param displayer how display (can be null)
     */
    void setDisplayer(Displayer displayer);
}
