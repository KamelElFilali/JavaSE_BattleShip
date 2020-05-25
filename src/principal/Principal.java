package principal;

import vue.Menu;


import jeu.*; 

public class Principal 
{
	java.applet.AudioClip son;
	
	static public void main(String[] argv)
	{
		

		/*apres le menu du jeu on lance la partie selon ce que l'utilisateur a choisis*/
		Jeu partie = new Jeu();
	
		//Controleur unControleur = new Controleur();
		Menu afficherMenu = new Menu("BattleShip",partie);
		
	}
	
}