package pvz.logic.gameobjects;
import utils.Position;
import pvz.logic.Game;

public class Zombie {
	
	private Position position;
	public static final int INITIAL_HEALTH = 4;
	public static final int DAMAGE = 1;
	public static final int MOVE_AFTER_CYCLES = 2;
	private int health;
	private int cyclesNotMoving;
	private Game game;
	
	public Zombie(Position p,Game game) {
		position=p;
		health= INITIAL_HEALTH;
		cyclesNotMoving=0;
		this.game=game;
	}
	
	
	public int getHealth() {
		return health;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public void receiveDamage(int damage) {
		health=health-damage;
	}
	
	private void move() {
		if(cyclesNotMoving==MOVE_AFTER_CYCLES) {
			position=position.left();
			cyclesNotMoving=0;
			
		}
		else cyclesNotMoving++;
	}
	
	private void atack() {
		game.attackPlant( this.getPosition().left(),DAMAGE );
	}
	
	
	public void update() {
		if(game.isEmpty(this.getPosition().left()))move();
		else atack();
	}
	
	public boolean isAlive() {
		return health>0;
	}
	
}
