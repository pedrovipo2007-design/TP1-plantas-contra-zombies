package pvz.logic.gameobjects;

import pvz.logic.Game;
import utils.Position;

public class Peashooter {
	
	//atributos
	private Position position;
	public static final int INITIAL_HEALTH = 3;
	public static final int DAMAGE = 1;
	public static final int COST = 50;
	private int health;
	private Game game;
	//metodos
	public static Object getDescription() {
		// TODO Auto-generated method stub
		return "[P]eashooter: cost='50' suncoins, damage='1', endurance='3'";
	}

	
	
	public Peashooter(Position p,Game game) {
		health=INITIAL_HEALTH;
		position=p;
		this.game=game;
	}
	
	
	
	
	public Position getPosition() {
		return position;
	}
	
	
	public boolean isAlive() {
		return health>0;
	}
	
	
	public void update() {
		game.attackZombie(position,DAMAGE);
	}

	
	public void receiveDamage(int damage) {
		health=health-damage;
	}
	
	
}
