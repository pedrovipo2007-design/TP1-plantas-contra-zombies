package pvz.logic.gameobjects;
import pvz.logic.Game;
import utils.Position;
public class Sunflower {
	//atributos
	
	private Position position;
	public static final int INITIAL_HEALTH = 3;
	public static final int GENERATE_AFTER_CYCLES = 3;
	public static final int COST = 50;
	private int health;
	private int cyclesSinceLastGeneration;
	private Game game;
	
	public static Object getDescription() {
		// TODO Auto-generated method stub
		return "[S]unflower: cost='20' suncoins, damage='0', endurance='1'";
	}
	public int getHealth() {
		return health;
	}
	public Sunflower(Position p,Game game) {
		health=INITIAL_HEALTH;
		position=p;
		cyclesSinceLastGeneration=0;
		this.game=game;
	}
	
	public boolean isAlive() {
		return health>0;
		
	}
	
	public void update() {
		if (cyclesSinceLastGeneration==GENERATE_AFTER_CYCLES) {
			game.generateCoins(COST);
		}
		else cyclesSinceLastGeneration++;
	}
	
	public void receiveDamage(int damage) {
		health =health-damage;
	}
	
	public Position getPosition() {
		return position;
	}	

}
