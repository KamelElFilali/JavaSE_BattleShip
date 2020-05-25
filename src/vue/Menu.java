package vue;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;



import deroulement.Controleur;
import jeu.Jeu;

public class Menu extends JFrame implements ActionListener
{
	
	
	private static final long serialVersionUID = 1L;

	public static boolean begin;
	
	private JLabel titreMenu;
	private JLabel sousTitre;
	//private JPanel lePanel = new JPanel();
	
	private JPanel 	lePanel = null;
	private JButton bouton1 = new JButton("JOUEUR VS L'ORDINATEUR");
	private JButton bouton2 = new JButton("JOUEUR 1 VS JOUEUR 2");
	
	//private JMenuBar menubarPrincipal;
	private JMenu menuOptions = new JMenu("OPTIONS");
	private JMenu niveauDifficulter = new JMenu("DIFFICULTER");
	private JMenu reseau = new JMenu("RESEAU");
	
	private JMenuItem optionItemNiveauFacile = new JMenuItem("Facile");
	private JMenuItem optionItemNiveauDifficile = new JMenuItem("Difficile");
	private JMenuItem optionItemReseauxLocal = new JMenuItem("Jouer en Local");
	private JMenuItem optionItemReseauxLan = new JMenuItem("Jouer en Reseaux sur Serveur");
	
	private JButton bouton3 = new JButton("OPTIONS");
	private JButton bouton4 = new JButton("QUITTER");
	
	private Controleur unControleur;
	private Jeu unePartie;
	Jeu partie = new Jeu();
	
		
	
	
	
	public Menu(String titre,Jeu unePartie) 
	{
		
		
		
		// on donne le titre de la fenetre au constructeur de JFrame
		super(titre);

		this.unePartie = new Jeu();

		
		// preparation de la fenetre
		this.setLocation(500, 150); // positionnement
		this.setSize(600, 600); // taille
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); // fermer l'application quand on clique sur X
		this.getContentPane().setBackground(Color.BLACK); 
		
		//lePanel = new JPanel();
		
		// mise en place de l'interface
		this.setLayout(new GridLayout(6,1));
		//this.setLayout(new FlowLayout());
		
		//lePanel = (JPanel) this.getContentPane();
		//lePanel.setLayout( new FlowLayout() );
		
		//TITRE DU MENU
		Font designTitreMenu = new Font("garamond", Font.BOLD,80);
		titreMenu = new JLabel(" BATTLESHIP ",JLabel.CENTER);
		titreMenu.setFont(designTitreMenu);
		titreMenu.setForeground(Color.RED);
		//titreMenu.setBackground(Color.lightGray);
		this.add(titreMenu);
		//lePanel.add(titreMenu);
	
		
		//SOUS TITRE DU MENU
		Font designSousTitreMenu = new Font("garamond", Font.BOLD,30);
		sousTitre = new JLabel("Bienvenu dans le Jeux BattleShip",JLabel.CENTER);
		sousTitre.setFont(designSousTitreMenu);
		sousTitre.setForeground(Color.WHITE);
		//titreMenu.setBackground(Color.lightGray);
		this.add(sousTitre);
		//lePanel.add(sousTitre);
		
		bouton1.setActionCommand("JOUERVSORDI");
		bouton1.addActionListener(this);
		//bouton1.setLayout(new BoxLayout(bouton1, BoxLayout.LINE_AXIS));
		bouton1.setForeground(Color.WHITE);
		bouton1.setBackground(Color.BLACK); 
		bouton1.setBorder(BorderFactory.createLineBorder(Color.RED));
		this.add(bouton1);
		//lePanel.add(bouton1);
		
		bouton2.setActionCommand("JOUEUR1VSJOUEUR2");
		bouton2.addActionListener(this);
		//bouton2.setLayout(new BoxLayout(bouton2, BoxLayout.LINE_AXIS));
		bouton2.setForeground(Color.WHITE);
		bouton2.setBackground(Color.BLACK); 
		bouton2.setBorder(BorderFactory.createLineBorder(Color.RED));
		this.add(bouton2);
		//lePanel.add(bouton2);
		
		/*bouton3.setActionCommand("OPTIONS");
		bouton3.addActionListener(this);
		bouton3.setForeground(Color.WHITE);
		bouton3.setBackground(Color.BLACK); 
		bouton3.setBorder(BorderFactory.createLineBorder(Color.RED));
		niveauDifficulter.add(optionItemNiveauFacile);
		niveauDifficulter.add(optionItemNiveauDifficile);
		reseau.add(optionItemReseauxLocal);
		reseau.add(optionItemReseauxLan);
		menuOptions.add(niveauDifficulter);
		menuOptions.add(reseau);
		bouton3.add(menuOptions);
		this.add(bouton3);*/
		
        //this.add(menubarPrincipal);
        //this.setJMenuBar(menubarPrincipal);
	
		bouton3.setActionCommand("OPTIONS");
		bouton3.addActionListener(this);
		//bouton3.setLayout(new BoxLayout(bouton3, BoxLayout.LINE_AXIS));
		bouton3.setForeground(Color.WHITE);
		bouton3.setBackground(Color.BLACK); 
		bouton3.setBorder(BorderFactory.createLineBorder(Color.RED));
		this.add(bouton3);
		//lePanel.add(bouton3);
		 
		bouton4.setActionCommand("QUITTER");
		bouton4.addActionListener(this);
		//bouton4.setLayout(new BoxLayout(bouton4, BoxLayout.LINE_AXIS));
		bouton4.setForeground(Color.WHITE);
		bouton4.setBackground(Color.BLACK); 
		bouton4.setBorder(BorderFactory.createLineBorder(Color.RED));
		this.add(bouton4);
		//lePanel.add(bouton4);
		
		//this.setContentPane(lePanel);
		this.setVisible(true); // rendre visible la fenetre
	}


	@Override
	public void actionPerformed(ActionEvent event) 
	{
		JButton leBouton =  (JButton)(event.getSource());

		
		switch (leBouton.getActionCommand()) 
		{
		case "JOUERVSORDI":
			
			this.dispose(); // pour cacher la fenetre du menu
			partie.start_SingelPlayer();
			
			
			break;

		case  "JOUEUR1VSJOUEUR2":
			
			this.dispose(); // pour cacher la fenetre du menu
			partie.start_MultiPlayer();
			
			
			break;
			
		case  "OPTIONS":
					
					
					
					break;
		case "QUITTER":
			
			
			System.exit(0);
			
			break;
		}
		
		
	}
	
	public class ImagePanel extends JPanel
	 {
	   
		private static final long serialVersionUID = 1L;

	public java.awt.Image image;
	   BorderLayout borderLayout1 = new BorderLayout();
	 
	   public ImagePanel()
	   {
	    try
	    {
	      jbInit();
	    }
	    catch(Exception e)
	    {
	      e.printStackTrace();
	    }
	   }
	 
	   public void paintComponent(Graphics g)
	   {
	     super.paintComponent(g);
	 
	     //int imageWight = image.getWidth(this);
	     //int imageHeight = image.getHeight(this);
	 
	     java.awt.Image image;
		try {
			image = ImageIO.read(new File("World_Of_Warship_Ships_438310.jpg"));
			
			 //Pour une image de fond
		     g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
		   //g.drawImage(image, 0, 0, imageWight, imageHeight, this);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	     
	   }
	  private void jbInit() throws Exception
	  {
	    this.setLayout(borderLayout1);
	  }
	 }

}
