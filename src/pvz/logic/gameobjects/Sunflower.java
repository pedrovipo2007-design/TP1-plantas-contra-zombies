package pvz.logic.gameobjects;
import pvz.logic.Game;
import utils.Position;
public class Sunflower {
	//atributos
	
	private Position position;
	public static final int INITIAL_HEALTH = 3;
	public static final int GENERATE_AFTER_CYCLES = 1;
	public static final int COST = 50;
	private int health;
	private int cyclesSinceLastGeneration;
	private Game game;
	
	public static Object getDescription() {
		// TODO Auto-generated method stub
		return "[S]unflower: cost='20' suncoins, damage='0', endurance='1'";
	}
	
	
	
	
	public Sunflower(Position p,Game game) {
		health=INITIAL_HEALTH;
		position=p;
		cyclesSinceLastGeneration=0;
		this.game=game;
	}
	
	
	
	
	public Position getPosition() {
		return position;
	}
	
	
	
	

}
