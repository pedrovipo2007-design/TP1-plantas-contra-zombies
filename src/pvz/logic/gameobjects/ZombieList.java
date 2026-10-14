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
		zombies= new Zombie[(1+Game.NUM_COLS)*(Game.NUM_ROWS)];
		numberOfZombies=0;
	}
	
	public boolean noMoreZombies() {
		return numberOfZombies==0;
	}
	
	public void reset() {
		numberOfZombies=0;
	}

	public String iconInPosition(Position p) {
		 return String.format(Messages.ZOMBIE_ICON, zombies[p.getRow()*Game.NUM_COLS+p.getCol()].getHealth());
	}
	
	public boolean isEmpty(Position p) {
		return (zombies[p.hashcode()]==null);
	}
	
	public void damage(Position p,int damage) {
		zombies[p.hashcode()].receiveDamage(damage);
		
	}
	
	public void update() {
		for(int i=0;i<Game.NUM_COLS; i++) {
			for(int j=0;j<Game.NUM_ROWS;j++) {
				Position position;
				position= new Position(j,i);
				if(!isEmpty(position)) {
					
					zombies[position.hashcode()].update();//para que los zombies se muevan dentro del array
					if(!zombies[position.hashcode()].getPosition().equals(position)) {
						zombies[position.hashcode()-1]=zombies[position.hashcode()];
						zombies[position.hashcode()]=null;
					}
				}
				
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
						numberOfZombies--;
					}
				}
			}
		}
	}
	
	public void add(Zombie z) {
		
		zombies[z.getPosition().Zhashcode()]=z;
		numberOfZombies++;
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
