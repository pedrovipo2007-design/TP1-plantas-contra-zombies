package pvz.logic.gameobjects;

import utils.Position;

import pvz.control.Level;
import pvz.logic.Game;
import pvz.logic.gameobjects.Zombie;
import pvz.view.Messages;
public class ZombieList {
	private Zombie[] zombies;
	private int numberOfZombies;
	
	
	
	
	public ZombieList() {
		zombies= new Zombie[Game.NUM_COLS*Game.NUM_ROWS];
		numberOfZombies=0;
	}
	
	
	
	
	public void reset() {
		numberOfZombies=0;
	}
	
	
	
	public String iconInPosition(Position p) {
		 return String.format(Messages.ZOMBIE_ICON, zombies[p.getRow()*Game.NUM_COLS+p.getCol()]);
	}
	
	
	
	public void add(Zombie z) {//voy a optar por crear el zombie y luego insertarlo en vez de insertarlo y luego darle posicion
		//busqueda binaria para encontrar donde insertar el zombie en el arraty
		
		
		zombies[z.getPosition().hashcode()]=z;
	}
	
	
}



