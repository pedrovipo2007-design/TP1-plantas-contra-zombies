package pvz.logic.gameobjects;

import pvz.logic.Game;
import pvz.view.Messages;
import utils.Position;

public class PeashooterList {
	
	private Peashooter[] peashooters;
	private int numberOfPeashooters;
	
	public PeashooterList() {
		peashooters= new Peashooter[Game.NUM_COLS*Game.NUM_ROWS];
		numberOfPeashooters=0;
	}
	
	public String iconInPosition(Position p) {
		 return String.format(Messages.PEASHOOTER_ICON, peashooters[p.getRow()*Game.NUM_COLS+p.getCol()].getHealth());
	}
	
	public void reset() {
		numberOfPeashooters=0;
		// si quieres podemos vaciar el array pero creo que de todas formas vamos a escribir por encima
	}
	
	public boolean isEmpty(Position p) {
		return (peashooters[p.hashcode()]==null);
	}
	
	public void update() {
		for(int i=0;i<Game.NUM_COLS; i++) {
			for(int j=0;j<Game.NUM_ROWS;j++) {
				Position position;
				position= new Position(j,i);
				if(!isEmpty(position))peashooters[position.hashcode()].update();
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
					if(!peashooters[position.hashcode()].isAlive()) {
						peashooters[position.hashcode()]=null;
					}
				}
			}
		}
	}
	
	
	public void add(Peashooter p) {
		
		//voy a optar por crear el zombie y luego insertarlo en vez de insertarlo y luego darle posicion
		//busqueda binaria para encontrar donde insertar el zombie en el array
		
		peashooters[p.getPosition().hashcode()]=p;
	}
	
	
	public void receiveDamage(Position pos, int damage) {
		peashooters[pos.hashcode()].receiveDamage(damage);
	}
	
}
