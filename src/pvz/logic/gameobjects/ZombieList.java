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
	
	public boolean isEmpty(Position p) {
		return (zombies[p.hashcode()]==null);
	}
	
	
	public boolean damage(Position p,int damage) {
		return true;//VOLVER
	}
	
	
	
	public void update() {
		for(int i=0;i<Game.NUM_COLS; i++) {
			for(int j=0;j<Game.NUM_ROWS;j++) {
				Position position;
				position= new Position(j,i);
				if(!isEmpty(position))zombies[position.hashcode()].update();
			}
			
		}
		
		removeDead();
	}
	
	
	public void removeDead() {
		for(int i=0;i<Game.NUM_COLS; i++) {
			for(int j=0;j<Game.NUM_ROWS;j++) {
				Position position;
				position= new Position(j,i);
				if(!isEmpty(position)) {
					if(!zombies[position.hashcode()].isAlive()) {
						zombies[position.hashcode()]=null;
					}
				}
			}
			
		}
	}
	
	public void add(Zombie z) {//voy a optar por crear el zombie y luego insertarlo en vez de insertarlo y luego darle posicion
		//busqueda binaria para encontrar donde insertar el zombie en el arraty
		
		
		zombies[z.getPosition().hashcode()]=z;
	}
	
	
	
	public Zombie firstZombieInRow(int row,int col) {//la columna desde la que dispara el lanzaguisantes
		
		while(col<Game.NUM_COLS) {
			Position position;
			position= new Position(row,col);
			if(!isEmpty(position)) {
				col++;
			}
			else return zombies[position.hashcode()];
		}
		return null;
	}
}
