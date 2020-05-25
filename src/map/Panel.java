package map;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

//une classe pour dessiner les rectangle du terrain
public class Panel extends JPanel {
	private static final long serialVersionUID = 1L;

	private int largeur, hauteur;
	boolean isChecked;
	boolean isBateau;
	int numCase;
	
	/*---------------------------------------------------------------------*/

	public Panel(int lrg, int htr,int numCase)
	{
		this.largeur = lrg;
		this.hauteur = htr;
		this.isChecked = false;
		this.isBateau = false;
		this.numCase = numCase;
	}
	
	/*---------------------------------------------------------------------*/

	public int getNumCase()
	{
		return this.numCase;
	}
	
	public void check()
	{
		//apres le click le controleur appelle cette methode pour redissiner la case
		this.isChecked = true;
		this.repaint();
	}
	
	public boolean isChecked()
	{
		return this.isChecked;
	}
	
	public void Collapse()
	{
		//apres le click le controleur appelle cette methode pour redissiner la case si il ya un bateau dans cette pos
		this.isBateau = true;
		this.isChecked = false;
		this.repaint();
	}
	
	public boolean isCollapsed()
	{
		return this.isBateau;
	}

	/*---------------------------------------------------------------------*/

	public void paintComponent(Graphics g) {
	  //on represente une case avec un carree (remplie si elle checke)
	  g.drawRect(0, 0, this.largeur - 2, this.hauteur);

	  if (this.isChecked)
      {
    	  g.setColor(Color.gray);
    	  g.fillRect(0, 0, this.largeur, this.hauteur);
      }
	  else if (this.isBateau)
	  {
		  g.setColor(Color.red);
		  g.fillRect(0, 0, this.largeur, this.hauteur);
		  
		  //g.drawLine(0, 0, this.largeur, this.hauteur);
		  //g.drawLine(this.largeur, 0,0,this.hauteur);
	  }
  }
}