package pvz.logic.gameobjects;

import pvz.logic.Game;
import utils.Position;
import pvz.logic.GameWorld;
import pvz.view.Messages;

public class Sunflower extends Plant {

	// atributos

	private Position position;
	public static final int INITIAL_HEALTH = 1;
	public static final int GENERATE_AFTER_CYCLES = 3;
	public static final int COST = 20;
	public static final int SUNS_PER_GENERATION = 10;

	private int health;
	private int cyclesSinceLastGeneration;
	private GameWorld game;

	public Sunflower() {
		
	}

	public int getHealth() {
		return health;
	}

	public Sunflower(Position p, GameWorld game) {
		super(game,p);
		health = INITIAL_HEALTH;
		position = p;
		cyclesSinceLastGeneration = 0;
		this.game = game;
		price=COST;
	}

	public boolean isAlive() {
		return health > 0;
	}

	@Override
	public void update() {
		if (cyclesSinceLastGeneration == GENERATE_AFTER_CYCLES) {
			game.generateCoins(SUNS_PER_GENERATION);
			cyclesSinceLastGeneration = 1;
		} else
			cyclesSinceLastGeneration++;
	}

	public void receiveDamage(int damage) {
		health = health - damage;
	}

	public Position getPosition() {
		return new Position(position.getRow(), position.getCol());
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return Messages.SUNFLOWER_DESCRIPTION;
	}

	@Override
	public void onEnter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return Messages.SUNFLOWER_NAME;
	}
	
	@Override
	public String getIcon() {
		return String.format(Messages.SUNFLOWER_ICON,this);
	}
}
