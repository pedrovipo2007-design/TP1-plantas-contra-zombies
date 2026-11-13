package pvz.logic.gameobjects;

import pvz.logic.Game;
import pvz.logic.GameWorld;
import pvz.view.Messages;
import utils.Position;

public class Peashooter extends Plant {

	// atributos
	// private Position position;ya lo lleva plant
	public static final int INITIAL_HEALTH = 3;
	public static final int DAMAGE = 1;
	public static final int COST = 50;
	// private GameWorld game; ya lo lleva plant

	// metodos
	@Override
	public String getDescription() {

		return Messages.PEASHOOTER_DESCRIPTION;
	}

	public Peashooter(Position p, GameWorld game) {
		super(game, p);
		health = INITIAL_HEALTH;
		price = COST;
	}

	public Peashooter() {
		super();

	}

	public int getHealth() {
		return health;
	}

	public Position getPosition() {
		return new Position(position.getRow(), position.getCol());
	}

	public boolean isAlive() {
		return health > 0;
	}

	public void receiveDamage(int damage) {
		health = health - damage;
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
	public void update() {
		Position atackPos = this.position;
		while (!game.plantAtackReceived(atackPos, DAMAGE) && atackPos.getCol() < game.NUM_COLS) {
			atackPos = atackPos.right();
		}
	}

	@Override
	public String getName() {
		// TODO
		return Messages.PEASHOOTER_NAME;
	}
	
	@Override
	public String getIcon() {
		return String.format(Messages.PEASHOOTER_ICON,this.health);
	}

}
