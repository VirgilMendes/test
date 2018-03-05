package gameoflife.displayer.graph;

import gameoflife.displayer.Displayer;
import gameoflife.world.ReadOnlyWorld;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GraphDisplayer implements Displayer
{
    private final JFrame frame;
    private Component[][] comps;
    
    private long pause;
    
    /**
     * 
     * @param name the title for the frame
     * @param width width of frame
     * @param height height of frame
     * @param pause time for animation in milliseconds
     */
    public GraphDisplayer(String title, int width, int height, long pause)
    {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.pause = pause; 
    }
    
    public void pause()
    {
        try
        {
            Thread.sleep(pause);
        }
        catch (InterruptedException e)
        {
        }
    }

    @Override
    public void start(ReadOnlyWorld world)
    {
        int w = world.getWidth();
        int h = world.getHeight();
        
        comps = new Component[w][h];
        
        for (int y=0; y<h; y++)
        for (int x=0; x<w; x++)
            frame.add(comps[x][y] = new JButton());
        
        frame.setLayout(new GridLayout(w,h));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    @Override
    public void display(ReadOnlyWorld world)
    {
        int w = world.getWidth();
        int h = world.getHeight();
        
        for (int y=0; y<h; y++)
        for (int x=0; x<w; x++)
            comps[x][y].setBackground(ColorDisplayer.getColor(world, x, y));
        
        frame.repaint();
        
        pause();
    }
    
    @Override
    public void finish()
    {
        //frame.setVisible(false);
    }
}
