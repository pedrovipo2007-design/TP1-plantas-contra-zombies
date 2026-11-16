package pvz.logic.gameobjects;

import pvz.logic.GameWorld;
import pvz.view.Messages;
import utils.Position;

public class CherryBomb extends Plant {
	
	// atributos
		// private Position position;ya lo lleva plant
		public static final int INITIAL_HEALTH = 2;
		public static final int DAMAGE = 10;
		public static final int COST = 50;
		private int cyclesToExplode;
		// private GameWorld game; ya lo lleva plant

		// metodos
		@Override
		public String getDescription() {
			return String.format(Messages.PLANT_DESCRIPTION, Messages.CHERRY_BOMB_NAME_SHORTCUT,
					COST, DAMAGE, INITIAL_HEALTH);
		}

		public CherryBomb(Position p, GameWorld game) {
			super(game, p);
			health = INITIAL_HEALTH;
			price = COST;
			cyclesToExplode=2;
		}

		public CherryBomb() {
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

		}

		@Override
		public void onExit() {

		}

		@Override
		public boolean update() {
			if(cyclesToExplode>0) {
				cyclesToExplode--;
			}
			else game.explodePlant(getPosition(), DAMAGE);
			return false;
		}

		@Override
		public String getName() {
			return Messages.CHERRY_BOMB_NAME;
		}
		
		@Override
		public String getIcon() {
			return String.format(Messages.GAME_OBJECT_ICON, Messages.CHERRY_BOMB_SYMBOL, this.health);
		}

}
