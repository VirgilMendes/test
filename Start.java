package gameoflife;

import javax.swing.*;

import gameoflife.conway.ConwayWorld;
import gameoflife.displayer.Displayer;
import gameoflife.displayer.graph.GraphDisplayer;
import gameoflife.world.WorldManipulator;

public class Start
{
    public static void main(String...args)
    {
        // Settings of displayer
        
        String title = "Conway Game of life";
        int pause = 0;
        int size = 800;
        int dim = 0;
        int circonference = 0;
        int resteVivant1 = -1 ,resteVivant2 = -1,devientVivant = -1 ;
        fenetre init = new fenetre();
        while( dim == 0 || pause == 0 || circonference == 0 || resteVivant1 == -1 || resteVivant2 == -1 || devientVivant == -1)
        {
        	pause = init.getTempsPause();
        	dim = init.getTaille();
        	circonference = init.getCirconference();
        	resteVivant1 = init.getCaseResteVivant1();
        	resteVivant2 = init.getCaseResteVivant2();
        	devientVivant = init.getCaseDevientVivant();
        	System.out.println(" taille = "+dim + " / pause = "+pause+" / circonference = "+circonference+" / reste vivant 1 = "+resteVivant1+" / reste vivant 2 = "+resteVivant2+ " / devient vivant = "+devientVivant);
        	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        init.dispose();
         
        
        Displayer displayer = new GraphDisplayer(title, size, size, pause);
        
        // Settings of game of life
        
        
        boolean toroidal = true;
        
        WorldManipulator world = new ConwayWorld(dim, dim, toroidal, circonference,resteVivant1,resteVivant2,devientVivant);
        world.setDisplayer(displayer);
        
        // Initialization
                
        for (int i=0; i<dim; i++)
        {
            world.setAlive(0, i, true);
            world.setAlive(i, 0, true);
        }
        
        // Execution
        
        int nbStep = 256;
        
        world.run(nbStep);
    }
}
