package pvz.logic.gameobjects;

import pvz.logic.Game;

public class SunflowerList {
	
	private Sunflower[] sunflowers;
	private int numberOfSunflowers;
	
	public SunflowerList() {
		sunflowers= new Sunflower[Game.NUM_COLS*Game.NUM_ROWS];
		numberOfSunflowers=0;
	}
	public void reset() {
		numberOfSunflowers=0;
	}
}

