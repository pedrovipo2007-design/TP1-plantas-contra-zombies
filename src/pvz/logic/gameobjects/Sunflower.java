package pvz.logic.gameobjects;

import pvz.logic.GameWorld;
import pvz.view.Messages;
import utils.Position;

public class Sunflower extends Plant {

	// atributos


	public static final int INITIAL_HEALTH = 1;
	public static final int DAMAGE = 0;
	public static final int GENERATE_AFTER_CYCLES = 3;
	public static final int COST = 20;
	private int cyclesSinceLastGeneration;


	public Sunflower() {
		
	}

	public int getHealth() {
		return health;
	}

	public Sunflower(Position p, GameWorld game) {
		super(game,p);
		health = INITIAL_HEALTH;
		position = p;
		// The cycle in which the plant is added does not count toward its first sun.
		cyclesSinceLastGeneration = -1;
		this.game = game;
		price=COST;
	}

	public boolean isAlive() {
		return health > 0;
	}

	@Override
	public boolean update() {
		cyclesSinceLastGeneration++;
		if (cyclesSinceLastGeneration >= GENERATE_AFTER_CYCLES) {
			game.generateSun();
			// The cycle in which the plant is added does not count toward its first sun.
		cyclesSinceLastGeneration = -1;
		}
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
		return String.format(Messages.PLANT_DESCRIPTION, Messages.SUNFLOWER_NAME_AND_SHORTCUT,
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
		return Messages.SUNFLOWER_NAME;
	}
	
	@Override
	public String getIcon() {
		return String.format(Messages.SUNFLOWER_ICON,this.health);
	}
}
