package pvz.logic.gameobjects;

import pvz.logic.GameWorld;
import pvz.logic.actions.ExplosionAction;
import pvz.view.Messages;
import utils.Position;

public class CherryBomb extends Plant {

    public static final int INITIAL_HEALTH = 2;
    public static final int DAMAGE = 10;
    public static final int COST = 50;

    private int cyclesToExplode;

    public CherryBomb(Position position, GameWorld game) {
        super(game, position);
        health = INITIAL_HEALTH;
        price = COST;
        cyclesToExplode = 2;
    }

    public CherryBomb() {
        super();
    }

    @Override
    public String getDescription() {
        return String.format(Messages.PLANT_DESCRIPTION,
                Messages.CHERRY_BOMB_NAME_SHORTCUT, COST, DAMAGE, INITIAL_HEALTH);
    }

    @Override
    public void onEnter() {
    }

    @Override
    public void onExit() {
    }

    @Override
    public boolean update() {
        if (!isAlive()) {
            return false;
        }
        if (cyclesToExplode > 1) {
            cyclesToExplode--;
        } else if (cyclesToExplode == 1) {
            cyclesToExplode = 0;
            game.pushAction(new ExplosionAction(
                    getPosition(), DAMAGE, ExplosionAction.Target.ZOMBIES));
            health = 0;
        }
        return false;
    }

    @Override
    public String getName() {
        return Messages.CHERRY_BOMB_NAME;
    }

    @Override
    public String getIcon() {
        String symbol = cyclesToExplode == 1
                ? Messages.CHERRY_BOMB_EXPLODING_SYMBOL
                : Messages.CHERRY_BOMB_SYMBOL;
        return String.format(Messages.GAME_OBJECT_ICON, symbol, health);
    }
}

