package map;

import java.util.ArrayList;

public class Ship {
		
	private ArrayList<Integer> positionShip; //une liste de position (cases du bateaux)
	private boolean isDead; //boolean pour le bateaux (mort ou non)
	
	/*---------------------------------------------------------------------*/

	public Ship()
	{
		this.positionShip = new ArrayList<Integer>();
		this.isDead = false;
	}
	
	/*---------------------------------------------------------------------*/

	//getter et setter pour positions
	
	public int getNombreCase()
	{
		return this.positionShip.size();
	}
	
	public ArrayList<Integer> getPositions()
	{
		return this.positionShip;
	}
	public void addPosition(int pos)
	{
		this.positionShip.add(pos);
	}
	
	
	//getter et setter pour isDead
	public boolean isDead()
	{
		return this.isDead;
	}
	public void dead()
	{
		this.isDead = true;
	}
	
}
