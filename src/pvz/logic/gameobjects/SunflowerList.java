package pvz.logic.gameobjects;

import pvz.logic.Game;
import utils.Position;

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
	
	
	
	
	public void add(Sunflower s) {//voy a optar por crear el zombie y luego insertarlo en vez de insertarlo y luego darle posicion
		//busqueda binaria para encontrar donde insertar el zombie en el arraty
		
		
		sunflowers[s.getPosition().hashcode()]=s;
	}
}


