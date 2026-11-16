package pvz.logic.gameobjects;

import pvz.logic.GameWorld;
import pvz.view.Messages;
import utils.Position;

public class Wallnut extends Plant{
	// atributos
	public static final int INITIAL_HEALTH = 10;
	public static final int DAMAGE = 0;
	
	public static final int COST = 50;

	public Wallnut() {
		
	}

	public int getHealth() {
		return health;
	}

	public Wallnut(Position p, GameWorld game) {
		super(game,p);
		health = INITIAL_HEALTH;
		position = p;
		this.game = game;
		price=COST;
	}

	public boolean isAlive() {
		return health > 0;
	}

	@Override
	public boolean update() {
		return false;
	}

	public void receiveDamage(int damage) {
		health = health - damage;
	}

	public Position getPosition() {
		return new Position(position.getRow(), position.getCol());
	}

	@Override
	public String getDescription() {
		return String.format(Messages.PLANT_DESCRIPTION, Messages.WALL_NUT_NAME_SHORTCUT,
				COST, DAMAGE, INITIAL_HEALTH);
	}

	@Override
	public void onEnter() {
		
	}

	@Override
	public void onExit() {
		
	}

	@Override
	public String getName() {
		return Messages.WALL_NUT_NAME;
	}
	
	@Override
	public String getIcon() {
		return String.format(Messages.GAME_OBJECT_ICON, Messages.WALL_NUT_SYMBOL, this.health);
	}
}
