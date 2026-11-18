package pvz.logic.actions;

import pvz.logic.GameWorld;
import utils.Position;

public class ExplosionAction implements GameAction {

    public enum Target {
        PLANTS, ZOMBIES
    }

    private final Position center;
    private final int damage;
    private final Target target;

    public ExplosionAction(Position center, int damage, Target target) {
        this.center = new Position(center.getRow(), center.getCol());
        this.damage = damage;
        this.target = target;
    }

    @Override
    public void execute(GameWorld game) {
        if (target == Target.PLANTS) {
            game.explodeZombie(center, damage);
        } else {
            game.explodePlant(center, damage);
        }
    }
}
