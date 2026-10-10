package pvz.logic.gameobjects;

import pvz.logic.Game;

public class PeashooterList {
	
	private Peashooter[] peashooters;
	private int numberOfPeashooters;
	
	public PeashooterList() {
		peashooters= new Peashooter[Game.NUM_COLS*Game.NUM_ROWS];
		numberOfPeashooters=0;
	}
	
	public void reset() {
		numberOfPeashooters=0;
		// si quieres podemos vaciar el array pero creo que de todas formas le vamos a escribir por encima
	}
}
