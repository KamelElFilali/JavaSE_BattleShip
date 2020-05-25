package map;

import java.util.ArrayList;

import javax.swing.JPanel;

import jeu.Jeu;

public class Cases {

private ArrayList<Panel> cases; //une liste de boolean pour les cases si elle sont coches ou non
	
	public Cases()
	{
		//initialiser le tableau des boolean
		cases = new ArrayList<Panel>();
	}
	
	public ArrayList<Panel> getCases()
	//retourner le tableau de cases de la map en cas de besoin
	{
		return this.cases;
	}
	
	
//-------------------------------------------------------------------------------------------
	
	//pour dessiner les cases "le terrain de jeu"
		public void dessinerCases(Jeu fenetrePartie, JPanel fenetrePlayer)
		{
			int lrg = fenetrePartie.getLargeur();
			int htr = fenetrePartie.getHauteur();
			
			//dessiner les 100 cases du terrain
			for (int i=0; i < 100; i++)
			{
				this.cases.add(new Panel(lrg, htr, i + 1));
				this.cases.get(i).addMouseListener(fenetrePartie);
				fenetrePlayer.add(this.cases.get(i));
			}
			
		}
}
