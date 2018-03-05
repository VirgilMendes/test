package gameoflife.conway;

import gameoflife.world.AbstractWorld;

public class ConwayWorld extends AbstractWorld
{
	private int circonference;
	private int resteVivante1;
	private int resteVivante2;
	private int deviensVivante;
	
    /**
     * Build a game of life world with Conway's rules.
     * @param width width of world
     * @param height height of world
     * @param toroidal if world is toroidal
     * @throws IllegalArgumentException if width or height <= 0
     */
    public ConwayWorld(int width, int height, boolean toroidal, int circonference, int resteVivante1,int resteVivante2,int deviensVivante) throws IllegalArgumentException
    {
        super(width, height, toroidal);
        this.circonference = circonference;
        this.resteVivante1 = resteVivante1;
        this.resteVivante2 = resteVivante2;
        this.deviensVivante = deviensVivante;
    }
    
    /**
     * The neighbourhood of a cell for Conway is the height cells around of this cell.
     * @param x
     * @param y
     * @return number of alive neighbours of cell (between 0 and 8)
     */
    @Override
    public int countNeighbours(int x, int y)
    {
        int cpt = isAlive(x, y) ? -1 : 0;
        
        for (int i = x - circonference; i <= x + circonference; i++ )
        for (int j = y - circonference; j <= y + circonference; j++ )
            if (isAlive(i, j))
                cpt++ ;
        
        return cpt;
    }
    
    public int getCirconference() {
		return circonference;
	}

	/**
     * Computes if a cell will be alive in next generation.<ul>
     * <li>An alive cell will be alive if have 2 or 3 alive neighbours.
     * <li>A dead cell will be alive if have 3 neighbours.</ul>
     * @param x
     * @param y
     * @return true or false depending of neighbourhood current state
     */
    @Override
    public boolean willBeAlive(int x, int y)
    {
        int n = countNeighbours(x, y);
        return isAlive(x, y) ? n >= resteVivante1 && n <= resteVivante2 : n == deviensVivante;
    }
}
