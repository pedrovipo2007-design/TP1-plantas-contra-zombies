package test;

import org.junit.jupiter.api.Test;
import pvz.logic.GameWorld;
import pvz.logic.actions.ExplosionAction;
import pvz.logic.gameobjects.ExplosiveZombie;
import pvz.logic.gameobjects.Zombies;
import test.FakeGameWorld.Attack;
import utils.Position;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Explosive zombie: same speed and endurance as the common zombie, but when it
 * dies it explodes dealing 3 damage to every plant around it.
 */
class ExplosiveZombieTest extends AbstractZombieTest {

    @Override
    protected Zombies create(Position position, GameWorld world) {
        return new ExplosiveZombie(position, world);
    }

    @Override
    protected String expectedName() {
        return "Explosive Zombie";
    }

    @Override
    protected String expectedSymbol() {
        return "Ez";
    }

    @Override
    protected int expectedSpeed() {
        return 2;
    }

    @Override
    protected int expectedEndurance() {
        return 5;
    }

    private static Set<Position> neighbours(Position p) {
        return Set.of(new Position(p.getRow(), p.getCol() - 1), new Position(p.getRow(), p.getCol() + 1),
                new Position(p.getRow() - 1, p.getCol()), new Position(p.getRow() + 1, p.getCol()),
                new Position(p.getRow() - 1, p.getCol() - 1), new Position(p.getRow() - 1, p.getCol() + 1),
                new Position(p.getRow() + 1, p.getCol() - 1), new Position(p.getRow() + 1, p.getCol() + 1));
    }

    @Test
    void doesNotExplode_whileItIsAlive() {
        for (int i = 0; i < 6; i++) {
            zombie.update();
        }
        assertTrue(world.pushedActions.isEmpty());
    }

    @Test
    void onExit_queuesExactlyOneExplosion() {
        zombie.plantAtackReceived(5);
        zombie.onExit();

        assertEquals(1, world.pushedActions.size());
        assertInstanceOf(ExplosionAction.class, world.pushedActions.get(0));
    }

    @Test
    void explosion_isNotExecutedImmediately() {
        zombie.plantAtackReceived(5);
        zombie.onExit();
        assertTrue(world.zombieAttacks.isEmpty(),
                "the explosion must wait in the action stack until the game runs it");
    }

    @Test
    void explosion_hits3DamageOnEveryAdjacentCell_includingDiagonals() {
        Position center = new Position(1, 5);
        Zombies centralZombie = create(center, world);
        centralZombie.plantAtackReceived(5);
        centralZombie.onExit();
        world.runPushedActions();

        Set<Position> hit = new HashSet<>();
        for (Attack attack : world.zombieAttacks) {
            assertEquals(3, attack.damage());
            hit.add(attack.position());
        }
        assertEquals(neighbours(center), hit);
        assertEquals(8, world.zombieAttacks.size(), "each neighbour is attacked exactly once");
    }

    @Test
    void explosion_hurtsPlantsOnly_neverZombies() {
        zombie.plantAtackReceived(5);
        zombie.onExit();
        world.runPushedActions();
        assertTrue(world.plantAttacks.isEmpty());
    }

    @Test
    void explosion_doesNotHitItsOwnCell() {
        zombie.plantAtackReceived(5);
        zombie.onExit();
        world.runPushedActions();
        for (Attack attack : world.zombieAttacks) {
            assertNotEquals(START, attack.position());
        }
    }

    @Test
    void stillCountsAsAKilledZombie() {
        zombie.plantAtackReceived(5);
        zombie.onExit();
        assertEquals(1, world.zombiesKilled);
    }
}





