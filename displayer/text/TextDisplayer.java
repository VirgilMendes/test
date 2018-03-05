package gameoflife.displayer.text;

import gameoflife.displayer.Displayer;
import gameoflife.world.ReadOnlyWorld;

import java.io.IOException;

public class TextDisplayer implements Displayer
{
    private final Appendable out;

    public TextDisplayer(Appendable out)
    {
        this.out = out;
    }
    
    @Override
    public void start(ReadOnlyWorld world)
    {
    }
    
    @Override
    public void display(ReadOnlyWorld world)
    {
        int width = world.getWidth();
        int height = world.getHeight();
        
        try
        {
            out.append(CharDisplayer.NORTH_WEST_CORNER.getChar());
            for (int x = 0; x < width; x++ )
                out.append(CharDisplayer.NORTH_BORDER.getChar());
            out.append(CharDisplayer.NORTH_EAST_CORNER.getChar()).append(CharDisplayer.NEW_LINE.getChar());
            
            for (int y = 0; y < height; y++ )
            {
                out.append(CharDisplayer.WEST_BORDER.getChar());
                for (int x = 0; x < width; x++ )
                    out.append(world.isAlive(x, y) ? CharDisplayer.ALIVE_CELL.getChar() : CharDisplayer.DEAD_CELL.getChar());
                out.append(CharDisplayer.EAST_BORDER.getChar()).append(CharDisplayer.NEW_LINE.getChar());
            }
            
            out.append(CharDisplayer.SOUTH_WEST_CORNER.getChar());
            for (int x = 0; x < width; x++ )
                out.append(CharDisplayer.SOUTH_BORDER.getChar());
            out.append(CharDisplayer.SOUTH_EAST_CORNER.getChar()).append(CharDisplayer.NEW_LINE.getChar());
        }
        catch (IOException e)
        {
            throw new IllegalStateException("Appendable is not usable", e);
        }
    }
    
    @Override
    public void finish()
    {
    }
}
