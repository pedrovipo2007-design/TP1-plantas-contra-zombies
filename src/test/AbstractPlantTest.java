package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pvz.logic.GameWorld;
import test.FakeGameWorld;
import pvz.logic.gameobjects.Plant;
import test.FakeGameWorld;
import utils.Position;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Contract shared by every plant. Each concrete plant test supplies the numbers
 * printed by the {@code list} command in the statement.
 */
abstract class AbstractPlantTest {

    protected static final Position POS = new Position(1, 3);

    protected FakeGameWorld world;
    protected Plant plant;

    protected abstract Plant create(Position position, GameWorld world);

    /** Name as printed by {@code list}, e.g. {@code [S]unflower}. */
    protected abstract String expectedListName();

    protected abstract String expectedSymbol();

    protected abstract int expectedCost();

    protected abstract int expectedDamage();

    protected abstract int expectedEndurance();

    @BeforeEach
    void setUp() {
        world = new FakeGameWorld();
        plant = create(POS, world);
    }

    // ------------------------------------------------------------------ //
    //  Identity                                                            //
    // ------------------------------------------------------------------ //

    @Test
    void initiallyAliveAndInItsPosition() {
        assertTrue(plant.isAlive());
        assertTrue(plant.isInPosition(POS));
        assertFalse(plant.isInPosition(new Position(1, 4)));
        assertFalse(plant.isInPosition(new Position(2, 3)));
    }

    @Test
    void stats_matchStatement() {
        assertEquals(expectedCost(), plant.getPrice());
        // Plant does not expose damage; the description assertion below covers it.
        assertEquals(expectedEndurance(), plant.getHealth());
    }

    @Test
    void description_matchesListFormat() {
        String expected = "%s: cost='%d' suncoins, damage='%d', endurance='%d'"
                .formatted(expectedListName(), expectedCost(), expectedDamage(), expectedEndurance());
        assertEquals(expected, plant.getDescription());
    }


    // ------------------------------------------------------------------ //
    //  Damage                                                              //
    // ------------------------------------------------------------------ //

    @Test
    void survives_enduranceMinusOneDamage() {
        plant.zombieAtackReceived(expectedEndurance() - 1);
        assertTrue(plant.isAlive());
    }

    @Test
    void dies_whenDamageEqualsEndurance() {
        plant.zombieAtackReceived(expectedEndurance());
        assertFalse(plant.isAlive());
    }

    @Test
    void dies_whenBittenOneByOne() {
        for (int i = 0; i < expectedEndurance() - 1; i++) {
            plant.zombieAtackReceived(1);
            assertTrue(plant.isAlive(), "still alive after " + (i + 1) + " bites");
        }
        plant.zombieAtackReceived(1);
        assertFalse(plant.isAlive());
    }

    @Test
    void zeroDamage_isHarmless() {
        plant.zombieAtackReceived(0);
        assertTrue(plant.isAlive());
    }

    @Test
    void zombieAtackReceived_reportsTheBite() {
        assertTrue(plant.zombieAtackReceived(1));
    }

    @Test
    void peasPassThroughPlants() {
        assertFalse(plant.plantAtackReceived(99));
        assertTrue(plant.isAlive());
    }

    // ------------------------------------------------------------------ //
    //  Life cycle                                                          //
    // ------------------------------------------------------------------ //

    @Test
    void onEnterAndOnExit_doNotTouchTheWorld() {
        plant.onEnter();
        plant.onExit();
        assertTrue(world.pushedActions.isEmpty());
        assertTrue(world.zombieAttacks.isEmpty());
        assertTrue(world.plantAttacks.isEmpty());
        assertEquals(0, world.generateSunCalls);
        assertEquals(0, world.generatedCoins);
    }


    // ------------------------------------------------------------------ //
    //  Spawn rules                                                         //
    // ------------------------------------------------------------------ //



}




