package jeu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.*;

import deroulement.Controleur;
import map.Bateaux;
import map.Cases;
import map.Panel;
import map.Ship;
import vue.*;
import vue.Menu;

public class Jeu extends JFrame implements MouseListener, ActionListener, KeyListener{
	static private final long serialVersionUID = 1L;  
	
	private JPanel fenetrePlayer1; // la fenetre du user 1
	private JPanel fenetrePlayer2; // la fenetre du user 2 ou du CPU
	
	private Cases cases1; // pour les cases de la fenetre 1
	private Bateaux bateaux1; // pour les bateaux du player 1
	private Controleur deroulement1; // le controleur 1
	
	private Cases cases2; // pour les cases de la fenetre 2
	private Bateaux bateaux2; // pour les bateaux du player 2
	private Controleur deroulement2; // le controleur 2
	
	private JLabel finJeu; //pour l'affichage de la fin du jeu
	private boolean fin;
	private boolean jeton; // le jeton des players
	
	private boolean contreCPU; //pour savoir si on joue contre le CPU ou contre un autre user
	private Random choixCPU; // choix aleatoire du CPU
	private int dernierePositionChoisisParCPU; // pour enregistrer la derniere position du CPU si il a une des cases d'un bateau
	private ArrayList<Integer> directionChoisisParCPU; //la liste de direction choisis par le CPU
	//private Timer pauseCPU; // le temps d'attente du CPU pour un clique 
	
	private Timer pauseClick;
	private boolean click;
	
	private Menu menu;
	
	/*---------------------------------------------------------------------*/

	public Jeu()
	{
		//informations de la fenetre du jeu
		this.setLocation(500,150);
		this.setSize(600,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.addKeyListener(this);
		
		//le temps d'attente apres chaque clique
		this.pauseClick = new Timer(2000, this);
		this.pauseClick.setRepeats(false);
	}
	
	/*---------------------------------------------------------------------*/
	/*pour jouer vs cpu*/
	public void start_SingelPlayer()
	{
		/*
		 *  on cree deux JPanel pour le joueur 1 et 2 
		 * 	et commence avec le joueur 1
		 * 	a chaque click on affiche l'autre fenetre
		 */
		
		// on cree la premiere fenetre et on l'affiche
		creer_fenetrePlayer1();
		this.add(this.fenetrePlayer1);
				
		//on cree la deuxieme fenetre sans l'afficher
		creer_fenetrePlayer2();
		
		// label pour la fin du jeu
		this.finJeu = new JLabel("Goooood Game !!!");
		this.finJeu.setFont(new Font("Courier New",Font.BOLD, 20));
		this.fin = false;
		
		this.click = false;
		this.contreCPU = true;
		this.choixCPU = new Random();
		//this.pauseCPU = new Timer(1000, this);
		
		// ces informations peuvent etre deplaces aux controleur
		this.dernierePositionChoisisParCPU = -1; // -1 pour une l'initialisation de la position (le CPU choisis aleatoirement en cas de -1)
		this.directionChoisisParCPU = new ArrayList<Integer>(Arrays.asList(1,2,3,4)); // les direction que le CPU peut choisir

		
		this.setVisible(true);
	}
	
	/*---------------------------------------------------------------------*/

	public void start_MultiPlayer()
	{
		/*
		 *  on cree deux JPanel pour le joueur 1 et 2 
		 * 	et commence avec le joueur 1
		 * 	a chaque click on affiche l'autre fenetre
		 */
		
		// on cree la premiere fenetre et on l'affiche
		
		creer_fenetrePlayer1();
		this.add(this.fenetrePlayer1);
		
		//on cree la deuxieme fenetre sans l'afficher
		creer_fenetrePlayer2();
		
		// label pour la fin du jeu
		this.finJeu = new JLabel("Goooood Game !!!");
		this.finJeu.setFont(new Font("Courier New",Font.BOLD, 20));
		this.fin = false;

		this.click = false;
		
		this.contreCPU = false;
		
		this.setVisible(true);
	}
	
	
	/*---------------------------------------------------------------------*/

	public void creer_fenetrePlayer1()
	{
		this.fenetrePlayer1 = new JPanel(); // le JPanel du player 1 qui va etre affiche
		
		this.fenetrePlayer1.setLayout(new GridLayout(10,10));// le layout pour le jeu
		
		this.cases1 = new Cases();
		this.cases1.dessinerCases(this, this.fenetrePlayer1); // dessiner les case du player 1 pour la premiere fois

		this.bateaux1 = new Bateaux();// creer les bateaux sur le terrain poue le player 1
		
		this.deroulement1 = new Controleur(cases1, bateaux1); //creer le controleur pour le player 1
		
		this.jeton = true; // le jeton pour les tours
		
		//ca c pour verifier que l'algorithme de positionement des bat marche bien ... on va l'effacer apres
		// ce petit code genere des fois une erreur de out of bound mais c pas un probleme on va l'effacer
		/*for (Ship bat : this.bateaux1.getBateaux())
		{
			for (Integer pnl : bat.getPositions())
			{
				this.cases1.getCases().get(pnl).check();
			}
		}*/
				
	}
	
	public void creer_fenetrePlayer2()
	{
		this.fenetrePlayer2 = new JPanel(); // le JPanel du player 2 qui va etre affiche
		
		this.fenetrePlayer2.setLayout(new GridLayout(10,10));// le layout pour le jeu
		
		this.cases2 = new Cases();
		this.cases2.dessinerCases(this, this.fenetrePlayer2); // dessiner les case du player 2 pour la premiere fois

		this.bateaux2 = new Bateaux();// creer les bateaux sur le terrain poue le player 2
		
		this.deroulement2 = new Controleur(cases2, bateaux2); //creer le controleur pour le player 2
				
		//ca c pour verifier que l'algorithme de positionement des bat marche bien ... on va l'effacer apres
		// ce petit code genere des fois une erreur de out of bound mais c pas un probleme on va l'effacer
		/*for (Ship bat : this.bateaux2.getBateaux())
		{
			for (Integer pnl : bat.getPositions())
			{
				this.cases2.getCases().get(pnl).check();
			}
		}*/
	}
	
	/*---------------------------------------------------------------------*/

	// getter pour largeur et hauteur
	public int getLargeur()
	{
		return this.getWidth() / 10;
	}
	
	public int getHauteur()
	{
		return this.getHeight() / 10;
	}
	
	
	/*---------------------------------------------------------------------*/

	//pour changer l'affichage apres passage du jeton d'un player a l'autre
	public void echangerJetonPlayers()
	{			
		//on efface le terrain actuel
		this.getContentPane().removeAll();
		
		// si c le tours du player 1 on affiche le terrain du player 2
		if (this.jeton)
		{
			this.add(this.fenetrePlayer2);
		}
		// sinon si c le tours du player 2 on affiche le terrain du player 1
		else if (!this.jeton)
		{
			this.add(this.fenetrePlayer1);
		}
		
		// on s'echange de jeton
		this.jeton = !this.jeton;
				
		// on redissine tout
		this.repaint();
		this.setVisible(true);
		
		
		if (this.contreCPU && !this.jeton)
		{
			TourCPU();
		}
		
		this.click = false;
	}
	
	// =========================================================

	public void TourCPU()
	{	
		int numCase;
		int choixDirection = 0;
		int dejaChoisie = 0;
		do
		{
			numCase = this.dernierePositionChoisisParCPU;
			
			if (this.dernierePositionChoisisParCPU == -1)
			{
				numCase = this.choixCPU.nextInt(99) + 1;
			}
			else if (this.dernierePositionChoisisParCPU != -1)
			{
				//si il reste q'une seule direction
				if (this.directionChoisisParCPU.size() == 1)
				{
					choixDirection = this.directionChoisisParCPU.get(0);
				}
				// en cas de plusieures directions restantes
				else if (this.directionChoisisParCPU.size() > 1)
				{
					choixDirection = this.choixCPU.nextInt(this.directionChoisisParCPU.size() - 1) + 1;
				}
				
				switch (choixDirection) {
				case 1:
					numCase = this.dernierePositionChoisisParCPU - 10;
					break;
				case 2:
					numCase = this.dernierePositionChoisisParCPU + 1;
					break;
				case 3:
					numCase = this.dernierePositionChoisisParCPU + 10;
					break;
				case 4:
					numCase = this.dernierePositionChoisisParCPU - 1;
					break;
				}
				
				dejaChoisie++;
			}
			
			//on peut refaire l'autre direction (l'autre cote du bateau)
			if (dejaChoisie == 50)
			{
				this.dernierePositionChoisisParCPU = -1;
				this.directionChoisisParCPU.clear();
				this.directionChoisisParCPU.addAll(Arrays.asList(1,2,3,4));
			}
			
		}while(!this.deroulement2.caseClick(numCase - 1, this));

	
		//on enregistre les donne touche ou on reinitialise-------------------------------------------------
			
		if (this.cases2.getCases().get(numCase - 1).isCollapsed())
			{
				//sauvgarder cette derniere case choisie
				this.dernierePositionChoisisParCPU = numCase;
				//si on a choisis selon direction
				if (choixDirection != 0)
				{
					this.directionChoisisParCPU.clear();
					this.directionChoisisParCPU.add(choixDirection);
				
					if (numCase < 11 || numCase > 89 || numCase % 10 == 0 || (numCase - 1) % 10 == 0 || caseCoince(numCase - 1))
					{
						this.dernierePositionChoisisParCPU = -1;
						this.directionChoisisParCPU.clear();
						this.directionChoisisParCPU.addAll(Arrays.asList(1,2,3,4));
					}
				}
				
			}
			
			if (this.cases2.getCases().get(numCase - 1).isChecked() && this.directionChoisisParCPU.size() == 1)
			{
				this.dernierePositionChoisisParCPU = -1;
				this.directionChoisisParCPU.clear();
				this.directionChoisisParCPU.addAll(Arrays.asList(1,2,3,4));
			}
			/*
			else if (this.cases2.getCases().get(numCase).isChecked())
			{
				for(int i = 0; i < this.directionChoisisParCPU.size(); i++)
				{
					if (this.directionChoisisParCPU.get(i) == choixDirection)
					{
						this.directionChoisisParCPU.remove(i);
					}
				}
			}*/	
		
		//	this.setTitle("derniere case : " + this.dernierePositionChoisisParCPU + " size : " + this.directionChoisisParCPU.size() + " dir restante : " + this.directionChoisisParCPU.get(0));	

		this.pauseClick.start(); // faire un sleep avant de changer de terrain
	}
	
	
	public boolean caseCoince(int numCase)
	{
	 	boolean coince = false;
		
	 	try {
	 		if ((this.cases2.getCases().get(numCase - 10).isCollapsed() || this.cases2.getCases().get(numCase - 10).isChecked()) && (this.cases2.getCases().get(numCase + 10).isCollapsed() || this.cases2.getCases().get(numCase + 10).isChecked()) && (this.cases2.getCases().get(numCase + 1).isCollapsed() || this.cases2.getCases().get(numCase + 1).isChecked()) && (this.cases2.getCases().get(numCase - 1).isCollapsed() || this.cases2.getCases().get(numCase - 1).isChecked()))
		 	{
		 		coince = true;
		 	}
		} catch(IndexOutOfBoundsException e) {
			coince = false;
		}

		
		return coince;
	}
	
	// =========================================================

	//pour la fin du Jeu
	public void fin()
	{
		//on efface tout et redissine le label de la fin du jeu
		this.setTitle("tout est detruit");
		this.getContentPane().removeAll();
		this.setLayout(new CardLayout(180,50));
		this.add(finJeu);
		this.repaint();
		this.setVisible(true);
		this.fin = true;
	}
	
	/*---------------------------------------------------------------------*/
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
				
		// =========================================================
		// en mode multijoueur
		if (!this.contreCPU)
			// si on joue contre un autre user
		{
			// en cliquant sur la souris on prend la case clique et on l'envoie vers le controleur 
			//si c'est le tours du player 1 n utilise le controleur 1 sinon le 2
			if (this.jeton && !this.click)
			{
				int numCase = ((Panel)e.getSource()).getNumCase();
				if (this.deroulement1.caseClick(numCase - 1, this))
				{
					this.pauseClick.start(); // faire un sleep avant de changer de terrain
					
					//annuler les autres click dans la meme fenetre
					this.click = true;
				}
				
			}
			
			else if (!this.jeton && !this.click)
			{
				int numCase = ((Panel)e.getSource()).getNumCase();
				if (this.deroulement2.caseClick(numCase - 1, this))
				{
					this.pauseClick.start(); // faire un sleep avant de changer de terrain
					
					//annuler les autres click dans la meme fenetre
					this.click = true;
				}
			}
			
		}
		
		// =========================================================
		// en mode singlePlayer
		else
			//si on joue contre le CPU
		{
			if (this.jeton && !this.click)
			{
				int numCase = ((Panel)e.getSource()).getNumCase();
				if (this.deroulement1.caseClick(numCase - 1, this))
				{
					this.pauseClick.start(); // faire un sleep avant de changer de terrain
					
					//annuler les autres click dans la meme fenetre
					this.click = true;
				}
			}
		}
		
		if (this.fin)
		{
			this.dispose();
			this.menu = new Menu("BattleShip", this);
		}
		
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	// =========================================================
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		this.echangerJetonPlayers();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyChar() == 'q')
		{
			System.exit(0);
		}
		if (e.getKeyChar() == 'm')
		{
			this.dispose();
			this.setVisible(false);
			this.menu = new Menu("BattleShip", this);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
