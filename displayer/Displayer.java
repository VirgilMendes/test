package gameoflife.displayer;

import gameoflife.world.ReadOnlyWorld;

public interface Displayer
{
    /**
     * Method for initialisation, will display nothing.
     * @param world will be display
     */
    void start(ReadOnlyWorld world);
    
    /**
     * Method for displaying a world.
     * @param world to display
     */
    void display(ReadOnlyWorld world);
    
    /**
     * Method for termination, will display nothing.
     */
    void finish();
}
