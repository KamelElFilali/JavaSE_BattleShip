package map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/* les informations des bateaux et leurs positions*/
public class Bateaux {
	private ArrayList<Ship> bateaux; //une liste de bateaux
	
	/*---------------------------------------------------------------------*/

	public Bateaux()
	{
		/*creer et positionner les bateaux*/
		this.bateaux = new ArrayList<Ship>();
		this.positionerShip();
	}
	
	/*---------------------------------------------------------------------*/

	public ArrayList<Ship> getBateaux()
	//retourner le tableau en cas de besoin
	{
		return bateaux;
	}
	
	public int getNombreBateaux()
	{
		return this.bateaux.size();
	}
	
	/*---------------------------------------------------------------------*/

	//pour positioner les bateaux
	public void positionerShip()
	{
		Ship ship;
		
		Random ran = new Random();
		int pos;
		boolean ilYaDeuxBateaux = false;
		ArrayList<Integer> dir = new ArrayList<Integer>();
		int directionBateau;
		
		//donner les positions au 4 bateaux
		for (int j = 2; j < 5; j++)
		{
			
			//creer une ship
			ship = new Ship();
			
			//prendre les direction disponible pour chaque bateau
			do
			{
				/* rechoisir une autre position aleatoire tanque les 4 direction sont pas diponibles
				(il ya des bateaux ou des limite de terrain dans les 4 directions) */
				pos = ran.nextInt(99); // pour ne pas tomber sur le index out bound
				//prendre les liste des dir dispo 
				dir = this.bateauProche(pos, j);
			
				//effacer les directions non autorise
				for(int k = 0; k < dir.size(); k++)
				{
					if (dir.get(k) == 0)
					{
						dir.remove(k);
						k--;
					}
				}
				
			}while(dir.isEmpty());			
				
			
			//choisir une des directions disponibles et positionner la ship
			directionBateau = dir.get(ran.nextInt(dir.size()));
			switch (directionBateau) {
			case 1:
				for (int i = 0; i < j; i++)
				{
					ship.addPosition(pos - (10 * i));
				}
					break;
			case 2:
				for (int i = 0; i < j; i++)
				{
					ship.addPosition(pos + i);
				}
					break;
			case 3:
				for (int i = 0; i < j; i++)
				{
					ship.addPosition(pos + (10 * i));
				}
				break;
			case 4:
				for (int i = 0; i < j; i++)
				{
					ship.addPosition(pos - i);
				}
				break;
			}
			
			this.bateaux.add(ship); //ajouter la ship a liste de bateaux
			
			// pour creer deux bateaux de 3 cases (si le premier est creer le boolean est false pour creer seulement deux)
			if(!ilYaDeuxBateaux && j == 3)
			{
				j--;
				ilYaDeuxBateaux = true;
			}
		}
	}
	
	/*---------------------------------------------------------------------*/

	
	//verifier chaque cases de chaque bateau existant pour voir si il est proche de la position aleatoir choisie et chaque limite du terrain pour ne pas deviser un bateau en deux 
	public ArrayList<Integer> bateauProche(int posAleatoire, int nombreCase)
	{
		ArrayList<Integer> dir = new ArrayList<Integer>(Arrays.asList(1,2,3,4)); // pour les direction autorises
		
		//verefier les 4 directions
		for (int i = 1; i <= 4; i++)
		{		
				switch (i) {
				
					//verifier en haut
						case 1:
							//verifier selon le nombre de case pour le ship qu'on positionne ("ship de 2 cases par exemple ou 4")
							for (int j = 0; j < nombreCase; j++)
							{
								//parcourir les autres bateaux existant
								for (Ship bat : this.bateaux)
								{
									//parcourir chaque case du chaque bateau
									for (int k = 0; k < bat.getNombreCase(); k++)
									{
										//si la case actuel correspond a une case d'un bateau on retourne un true
											if (posAleatoire - (10 * j) == bat.getPositions().get(k))
											{
												dir.set(i - 1, 0);
												k = bat.getNombreCase();
												j = nombreCase;
											}
									}
								}
								// si la case actuel depasse une limite on retourne un true
								if (posAleatoire - (10 * j) < 0)
								{
									dir.set(i - 1, 0);
									j = nombreCase;
								}
							}
							break;
							
					//verifier a droite
						case 2:
							for (int j = 0; j < nombreCase; j++)
							{
								//parcourir les autres bateaux existant
								for (Ship bat : this.bateaux)
								{
									for (int k = 0; k < bat.getNombreCase(); k++)
									{
										if (posAleatoire + j == bat.getPositions().get(k))
										{
											dir.set(i - 1, 0);
										}
									}
								}
								
								if ((posAleatoire + j) % 10 == 0)
								{
									dir.set(i - 1, 0);
								}
							}
							
							break;
							
					//verifier en bas
						case 3:
							for (int j = 0; j < nombreCase; j++)
							{
								//parcourir les autres bateaux existant
								for (Ship bat : this.bateaux)
								{
									for (int k = 0; k < bat.getNombreCase(); k++)
									{
										if (posAleatoire + (10 * i) == bat.getPositions().get(k))
										{
											dir.set(i - 1, 0);
											k = bat.getNombreCase();
											j = nombreCase;
										}
									}
								}
								
								if (posAleatoire + (10 * j) > 100)
								{
									dir.set(i - 1, 0);
									j = nombreCase;
								}
							}
							
					break;
					
					//verifier a gauche
						case 4:
							for (int j = 0; j < nombreCase; j++)
							{
								//parcourir les autres bateaux existant
								for (Ship bat : this.bateaux)
								{
									for (int k = 0; k < bat.getNombreCase(); k++)
									{
										if (posAleatoire - j == bat.getPositions().get(k))
										{
											dir.set(i - 1, 0);
										}
									}
								}
								
								if ((posAleatoire - j - 1) % 10 == 0)
								{
									dir.set(i - 1, 0);
								}
							}
							
						break;
						}
			}
		
		return dir;
	}
	
	
}
