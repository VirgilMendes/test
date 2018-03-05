package gameoflife.displayer.graph;

import static org.junit.Assert.*;
import gameoflife.displayer.Displayer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestGraphDisplayer {

	private Displayer Test = null;
	@Before
	public void setUp() throws Exception {
		Test = new GraphDisplayer("Test",500,500,500);
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testPause() {
		long debut = System.currentTimeMillis();
		
		((GraphDisplayer) Test).pause();
		 
		double temps = System.currentTimeMillis()-debut;
		//Affiche la durée d'exécution en millisecondes
		
		assertTrue("temps trop grand", temps < 500 + 50);
		assertTrue("temps trop petit", temps > 500 - 50);
	}

}
