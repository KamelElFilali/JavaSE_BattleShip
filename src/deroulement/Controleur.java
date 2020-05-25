package deroulement;

import jeu.Jeu;
import map.Bateaux;
import map.Cases;

public class Controleur {
	
	Cases cases;
	Bateaux bateaux;

	/*---------------------------------------------------------------------*/

	public Controleur(Cases cases, Bateaux bateaux)
	{
		this.cases = cases;
		this.bateaux = bateaux;
	}
	
	/*---------------------------------------------------------------------*/

	//methode appelle apres chaque clique
	public boolean caseClick(int numCase, Jeu fenetrePartie)
	{
		boolean caseChecked = false;
		//on prend la case clique et on la check ou on la collapse si il ya un bateau
		
		
		try {

			//*******************************************************************************
			//verifier si la case a ete deja coche 
			if (!this.cases.getCases().get(numCase).isCollapsed() && !this.cases.getCases().get(numCase).isChecked())
			{
				//parcourir tou les bateaux
				for (int i = 0; i < this.bateaux.getNombreBateaux(); i++)
				{
					//on verefie les case d'un bateau seulement s'il n'est pas dead
					if (!this.bateaux.getBateaux().get(i).isDead())
					{
						//parcourir toute les cases du bateau
						for(int j = 0; j < this.bateaux.getBateaux().get(i).getNombreCase(); j++)
						{
							//si la case clique est l'une des cases d'un bateau
							if (numCase == this.bateaux.getBateaux().get(i).getPositions().get(j))
							{
								//faire un collapse en cas de click sur un bateau
								this.cases.getCases().get(numCase).Collapse();
								
								//on verifie si le bateau est detruit 
								boolean bateauDetruit = true;
								for (int k = 0; k < this.bateaux.getBateaux().get(i).getNombreCase(); k++)
									//on doit parcourir toute les case du bateaux si une des cases n'est pas detruite donc le bat n'est pas detruit
								{
									//on verifie les cases du terrain correspondantes aux cases des positions du bateau
									if (!this.cases.getCases().get(this.bateaux.getBateaux().get(i).getPositions().get(k)).isCollapsed())
									{						
										bateauDetruit = false;
									}
								}
								//si le boolean est true ca veut dire que toutes les cases sont detruites
								if (bateauDetruit)
								{
									this.bateaux.getBateaux().get(i).dead(); //on met le bat en mode dead
								}
								
								j = this.bateaux.getBateaux().get(i).getNombreCase() - 1;
								i = this.bateaux.getNombreBateaux() - 1;
							}
						}
					}
				}
				
				//*****************************************************************************
				//si la case clique n'est pas l'une des cases d'un bateau
				if (!this.cases.getCases().get(numCase).isCollapsed())
				{
					//gris si on a pas clique sur un bateau
					this.cases.getCases().get(numCase).check();
				}
				
				
				/*
				 * si tout les bateaux sont detruits donc le boolean reste true donc on declare la fin de la guerre :)
				 */
				boolean toutDetruit = true;
				//verifier si tout les bateaux sont detruits
				for (int k = 0; k < this.bateaux.getBateaux().size(); k++)
				{
					if (!this.bateaux.getBateaux().get(k).isDead())
					{
						toutDetruit = false;
					}
				}
				
				//si tout est detruit donc on arrete le jeu
				if (toutDetruit)
				{
					fenetrePartie.fin();
				}
			
				
				caseChecked = true;
			}
			
			return caseChecked;
		}
		
		catch (Exception e) {
			// TODO: handle exception
			return caseChecked;
		}
	}
	
}
