package gameoflife.world;

import gameoflife.displayer.Displayer;
import gameoflife.displayer.text.TextDisplayer;

import java.io.StringWriter;
import java.util.Random;

public abstract class AbstractWorld implements World
{
    private final int width;
    private final int height;
    private final boolean toroidal;
    private boolean[][] previousWorld;
    private boolean[][] world;
    private boolean[][] nextWorld;
    private Displayer displayer;
    
    /**
     * Build a game of life world.
     * @param width width of world
     * @param height height of world
     * @param toroidal if world is toroidal
     * @param displayer for displaying world state
     * @throws IllegalArgumentException if width or height <= 0
     */
    public AbstractWorld(int width, int height, boolean toroidal) throws IllegalArgumentException
    {
        if (width<=0)
            throw new IllegalArgumentException("Invalid width: "+width);
        if (height<=0)
            throw new IllegalArgumentException("Invalid height: "+height);
        
        this.width = width;
        this.height = height;
        this.toroidal = toroidal;
        this.previousWorld = new boolean[width][height];
        this.world = new boolean[width][height];
        this.nextWorld = new boolean[width][height];
    }

    @Override
    public int getWidth()
    {
        return width;
    }

    @Override
    public int getHeight()
    {
        return height;
    }
    
    @Override
    public boolean isToroidal()
    {
        return toroidal;
    }
    
    @Override
    public void setDisplayer(Displayer displayer)
    {
        this.displayer = displayer;
    }
    
    /**
     * Computes a modulo b even if a is negative.
     * @param a
     * @param b > 0
     * @return a mod b
     * @throws ArithmeticException if b==0
     */
    private static int mod(int a, int b) throws ArithmeticException
    {
        int r = a % b;
        return r < 0 ? r + b : r;
    }
    
    @Override
    public boolean isAlive(int x, int y)
    {
        return isAlive(world, x, y);
    }
    
    @Override
    public boolean wasAlive(int x, int y)
    {
        return isAlive(previousWorld, x, y);
    }
    
    private boolean isAlive(boolean[][] gridWorld, int x, int y)
    {
        if (toroidal)
            return gridWorld[mod(x, width)][mod(y, height)];
        
        if (x < 0 || x >= width || y < 0 || y >= height)
            return false;
        
        return gridWorld[x][y];
    }
    
    @Override
    public void setAlive(int x, int y, boolean alive)
    {
        setAlive(x, y, alive, true);
    }
    
    /**
     * Set state of a cell in current world or in next generation world.
     * @param x
     * @param y
     * @param alive if cell is alive in current world or will be alive in next generation world.
     * @param inCurrentWorld true for current world, false for next generation of world
     */
    private void setAlive(int x, int y, boolean alive, boolean inCurrentWorld)
    {
        setAlive(inCurrentWorld ? world : nextWorld, x, y, alive);
    }
    
    /**
     * Set state of cell in a world.
     * @param gridWorld world to set
     * @param x
     * @param y
     * @param alive if cell is alive in the world to set
     */
    private void setAlive(boolean[][] gridWorld, int x, int y, boolean alive)
    {
        if (toroidal)
            gridWorld[mod(x, width)][mod(y, height)] = alive;
        
        if (x >= 0 && x < width && y >= 0 && y < height)
            gridWorld[x][y] = alive;
    }

    /**
     * Rotates the different world generation.
     */
    private void switchWorlds()
    {
        boolean[][] tmp = previousWorld;
        previousWorld = world;
        world = nextWorld;
        nextWorld = tmp;
    }
    
    @Override
    public int nextGeneration()
    {
        int cpt = 0;
        
        for (int x = 0; x < width; x++ )
        for (int y = 0; y < height; y++ )
        {
            boolean alive = willBeAlive(x, y);
            
            setAlive(x, y, alive, false);
            
            if (alive)
                cpt++ ;
        }
        
        switchWorlds();
        
        return cpt;
    }
    
    @Override
    public int shuffle(double density) throws IllegalArgumentException
    {
        return shuffle(new Random(),density);
    }
    
    @Override
    public int shuffle(Random random, double density) throws IllegalArgumentException
    {        
        if (density<0 || density>1)
            throw new IllegalArgumentException("Density will be between 0 and 1: "+density);
        
        int cpt = 0;
        
        for (int x = 0; x < width; x++ )
        for (int y = 0; y < height; y++ )
        {
            boolean alive = random.nextDouble() < density;
            
            setAlive(x, y, alive);
            
            if (alive)
                cpt++ ;
        }
        
        return cpt;
    }
    
    @Override
    public int run(int nbStep)
    {
        int i = 0;
        
        if (displayer==null)
        {
            for (; i < nbStep && nextGeneration() > 0; i++ );
        }
        else
        {
            displayer.start(this);
            displayer.display(this);
            
            for (; i < nbStep && nextGeneration() > 0; i++ )
                displayer.display(this);
            
            displayer.display(this);
            displayer.finish();
        }
        
        return i;
    }
    
    @Override
    public int hashCode()
    {
        int result = toroidal ? 1231 : 1237;
        
        for (int x = 0; x < width; x++ )
        for (int y = 0; y < height; y++ )
            result = 31 * result + (world[x][y] ? 1231 : 1237);
        
        return result;
    }
    
    @Override
    public boolean equals(Object other)
    {
        if (other == this)
            return true;
        
        if (other == null)
            return false;
        
        if ( ! (other instanceof AbstractWorld))
            return false;
        
        AbstractWorld otherWorld = (AbstractWorld) other;
        
        if (otherWorld.toroidal != toroidal)
            return false;
        
        if (otherWorld.width != width)
            return false;
        
        if (otherWorld.height != height)
            return false;

        for (int x = 0; x < width; x++ )
        for (int y = 0; y < height; y++ )
            if (otherWorld.world[x][y] != world[x][y])
                return false;
        
        return true;
    }
        
    @Override
    public String toString()
    {
        StringWriter sw = new StringWriter();
        Displayer displayer = new TextDisplayer(sw);
        displayer.display(this);
        return sw.toString();
    }
}
