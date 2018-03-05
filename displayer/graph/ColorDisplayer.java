package gameoflife.displayer.graph;

import gameoflife.world.ReadOnlyWorld;

import java.awt.Color;

public enum ColorDisplayer
{
    /** Cell is not alive, was not alive on previous generation, and will not be alive on next generation. */
    NONE(Color.WHITE),
    
    /** Cell is not alive, but will be alive on next generation. */
    INCUBATING(Color.WHITE),
    
    /** Cell is alive, but was not alive on previous generation and will not be alive on next generation. */
    TRANSIANT(Color.YELLOW),
    
    /** Cell is alive, will be alive on next generation, but was not alive on previous generation. */
    GROWING(Color.GREEN),
    
    /** Cell is alive, was alive on previous generation, and will be alive on next generation. */
    ALIVE(Color.BLUE),
    
    /** Cell is alive, was alive on previous generation, but will not be alive on next generation. */
    DYING(Color.RED),
    
    /** Cell is not alive, but was alive on previous generation. */
    DEAD(Color.WHITE);
    
    private Color color;
    
    ColorDisplayer(Color color)
    {
        this.color = color;
    }
    
    public Color getColor()
    {
        return color;
    }
    
    public void setColor(Color color)
    {
        this.color = color;
    }
    
    /**
     * @param world where cells live
     * @param x
     * @param y
     * @return color of cell
     */
    public static Color getColor(ReadOnlyWorld world, int x, int y)
    {
        int a = world.wasAlive(x, y)    ? 0100 : 0;
        int b = world.isAlive(x, y)     ? 0010 : 0;
        int c = world.willBeAlive(x, y) ? 0001 : 0;
        int n = a+b+c;
        
        switch (n)
        {
            case 0000 :
                return ColorDisplayer.NONE.getColor();
            case 0001 :
                return ColorDisplayer.INCUBATING.getColor();
            case 0010 :
                return ColorDisplayer.TRANSIANT.getColor();
            case 0011 :
                return ColorDisplayer.GROWING.getColor();
            case 0100 :
                return ColorDisplayer.DEAD.getColor();
            case 0101 :
                return ColorDisplayer.INCUBATING.getColor();
            case 0110 :
                return ColorDisplayer.DYING.getColor();
            case 0111 :
                return ColorDisplayer.ALIVE.getColor();
            default :
                throw new IllegalStateException("Unknown state "+Integer.toOctalString(n));
        }
    }
}
