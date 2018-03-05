package gameoflife;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class fenetre extends JFrame implements ActionListener{
	
	private JPanel TempsEtPause = new JPanel();
	private JPanel parametres = new JPanel();
	private JPanel boutons = new JPanel();
		  
	private JFormattedTextField tPause = new JFormattedTextField(createFormatter("###"));
	private JFormattedTextField nbCases = new JFormattedTextField(createFormatter("##"));
	private JFormattedTextField circonference = new JFormattedTextField(createFormatter("#"));
		  
	private JFormattedTextField resteVivant1 = new JFormattedTextField(createFormatter("#"));
	private JFormattedTextField resteVivant2 = new JFormattedTextField(createFormatter("#"));
	private JFormattedTextField devientVivant = new JFormattedTextField(createFormatter("#"));
		  
	private JLabel labelErrorPause = new JLabel("    100 < Temps Pause < 999 ");
	private JLabel labelErrorNbC = new JLabel("    10 < Nombres Cases < 99 ");
	private JLabel labelErrorCirconference = new JLabel("      1 < circonference < 9  ");
	private JLabel labelConway = new JLabel("<html> Conway World : pause = 100, Cases = 50,<br> circonference = 1, paramètres = 2, 3, 3 </html");   
		  
	private JLabel labelp = new JLabel("    Temps Pause : ");
	private JLabel labelnbC = new JLabel("    Nombres Cases : ");
	private JLabel labelCirconference = new JLabel(" circonference du voisinage : ");
	
	private JLabel labelResteVivant1 = new JLabel("Cellule reste vivante si il y a entre "); 
	private JLabel labelResteVivant2 = new JLabel(" et ");
	private JLabel labelResteVivant3 = new JLabel(" cellules vivante dans son voisinage."); 
	private JLabel labelvide = new JLabel("");
	private JLabel labelDevientVivant1 = new JLabel("Cellule morte devient vivante avec "); 
	private JLabel labelDevientVivant2 = new JLabel(" vivante dans son voisinage."); 
		  
		  
	private JButton OK = new JButton ("OK");
	  
	private int tempsPause;
	private int taille;
	private int tailleCirconference;
	private int caseResteVivant1;
	private int caseResteVivant2;
	private int caseDevientVivant;
	  
	private String textNbCases;
	private String textTPause;
	private String textCirconference;
	private String textCaseResteVivant1;
	private String textCaseResteVivant2;
	private String textCaseDevientVivant;

	
	public int getCaseResteVivant1() {
			return caseResteVivant1;
	}


	public int getCaseResteVivant2() {
			return caseResteVivant2;
	}


	public int getCaseDevientVivant() {
			return caseDevientVivant;
	}
	  
	public int getTempsPause() {
		return tempsPause;
	}


	public int getTaille() {
		return taille;
	}

	public int getCirconference() {
		return tailleCirconference;
	}
	

	public fenetre()
	{
     this.setTitle("configuration");
     this.setSize(400, 400);
     this.setLocationRelativeTo(null);
     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
     
     tPause.setPreferredSize(new Dimension(60, 30));
     nbCases.setPreferredSize(new Dimension(60, 30));
     circonference.setPreferredSize(new Dimension(60, 30));
     resteVivant1.setPreferredSize(new Dimension(30, 30));
     resteVivant2.setPreferredSize(new Dimension(30, 30));
     devientVivant.setPreferredSize(new Dimension(30, 30));
     
     OK.addActionListener(this);
     
     TempsEtPause.setLayout(new GridLayout(3,2));
     
     TempsEtPause.add(labelErrorPause);
     TempsEtPause.add(labelErrorNbC);
     
     TempsEtPause.add(labelp);
     TempsEtPause.add(labelnbC);
     
     TempsEtPause.add(tPause);
     TempsEtPause.add(nbCases);
    
 //    parametres.setLayout(new GridLayout(4,4));
     
     parametres.add(labelErrorCirconference);
     parametres.add(labelCirconference);
     parametres.add(circonference);
     parametres.add(labelvide);
     parametres.add(labelvide);
     
     parametres.add(labelResteVivant1);
     parametres.add(resteVivant1);
     parametres.add(labelResteVivant2);
     parametres.add(resteVivant2);
     parametres.add(labelResteVivant3);
     
     parametres.add(labelDevientVivant1);
     parametres.add(devientVivant);
     parametres.add(labelDevientVivant2);
     
     parametres.add(labelConway);
     
     boutons.add(OK);
     
     this.add(parametres, BorderLayout.CENTER);
     this.add(TempsEtPause, BorderLayout.NORTH);
     this.add(boutons, BorderLayout.SOUTH);
     
     this.setVisible(true);
     
     taille = 0;
     tempsPause = 0;
	} 
	
	public void actionPerformed( ActionEvent a)
	{
		System.out.println(" debut action performed : ");
		this.taille = 0;
		this.tempsPause = 0;
		this.caseResteVivant1 = 0;
		this.caseResteVivant2 = 0;
		this.caseDevientVivant = 0;
		
		textTPause = tPause.getText();
		textNbCases = nbCases.getText();
		textCirconference = circonference.getText();
		textCaseResteVivant1 = resteVivant1.getText();
		textCaseResteVivant2 = resteVivant2.getText();
		textCaseDevientVivant = devientVivant.getText();
		    
		// conversion tempsPause
			
		try{
			this.tempsPause = Integer.parseInt(textTPause.replace(" ", ""));
		}catch( Exception e)
		{
			this.tempsPause = 0;
		   	System.out.println("erreur tempsPause = 0");
		 }
		    
		 // conversion taille
		 System.out.println("taille = " + taille);
		 try{
		   	this.taille = Integer.parseInt(textNbCases.replace(" ", ""));
		 }catch( Exception e)
		 {
			this.taille = 0;
		   	System.out.println("erreur taille = 0");
		 }
		 
		 try{
			 this.tailleCirconference = Integer.parseInt(textCirconference.replace(" ", ""));
		 }catch( Exception e)
		 {
			this.tailleCirconference = 0;
			System.out.println(" erreur tailleCirconference = 0");
		 }
		 //////////////////////////////////////////////////////////////////////////paramètres vie
		 
		 try{
				this.caseResteVivant1 = Integer.parseInt(textCaseResteVivant1.replace(" ", ""));
			}catch( Exception e)
			{
				this.caseResteVivant1 = 0;
			   	System.out.println("erreur caseResteVivant1 = 0");
			 }
		 try{
				this.caseResteVivant2 = Integer.parseInt(textCaseResteVivant2.replace(" ", ""));
			}catch( Exception e)
			{
				this.caseResteVivant2 = 0;
			   	System.out.println("erreur caseResteVivant2 = 0");
			 }
		 try{
				this.caseDevientVivant = Integer.parseInt(textCaseDevientVivant.replace(" ", ""));
			}catch( Exception e)
			{
				this.caseDevientVivant = 0;
			   	System.out.println("erreur caseDevientVivant = 0");
			 }
		    
		 System.out.println("temps pause 1 = " + tempsPause);
		 System.out.println("taille 1 = " + taille);	
	}
	



	protected MaskFormatter createFormatter(String s) {
	    MaskFormatter formatter = null;
	    try {
	        formatter = new MaskFormatter(s);
	    } catch (java.text.ParseException exc) {
	        System.err.println("formatter is bad: " + exc.getMessage());
	        System.exit(-1);
	    }
	    return formatter;
	}
     
     
     
     
}
