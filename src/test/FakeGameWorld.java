package test;

import pvz.logic.GameObject;
import pvz.logic.GameWorld;
import pvz.logic.actions.GameAction;
import pvz.logic.gameobjects.Zombies;
import utils.Position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** Minimal world for deterministic game-object unit tests. */
public class FakeGameWorld implements GameWorld {
    public record Attack(Position position, int damage) {}
    public final Set<Position> occupied = new HashSet<>();
    public final List<Attack> zombieAttacks = new ArrayList<>();
    public final List<Attack> plantAttacks = new ArrayList<>();
    public final List<GameAction> pushedActions = new ArrayList<>();
    public final List<Zombies> addedObjects = new ArrayList<>();
    public int generateSunCalls;
    public int generatedCoins;
    public int zombiesKilled;
    public int zombiesAdded;
    public Position nextZombiePosition = new Position(0, 0);
    public Runnable onGenerateSun = () -> {};

    public void runPushedActions() {
        List<GameAction> pending = new ArrayList<>(pushedActions);
        pushedActions.clear();
        for (GameAction action : pending) action.execute(this);
    }

    @Override public boolean isEmpty(Position position) { return !occupied.contains(position); }
    @Override public boolean isInsideBoard(Position p) {
        return p != null && p.getRow() >= 0 && p.getRow() < NUM_ROWS && p.getCol() >= 0 && p.getCol() < NUM_COLS;
    }
    @Override public void generateCoins(int coins) { generatedCoins += coins; }
    @Override public void generateSun() { generateSunCalls++; onGenerateSun.run(); }
    @Override public boolean plantAtackReceived(Position p, int damage) {
        plantAttacks.add(new Attack(p, damage));
        return true;
    }
    @Override public Position newZombiePosition(int row) { return new Position(row, nextZombiePosition.getCol()); }
    @Override public boolean isEmptyOfZombies(Position p) { return !occupied.contains(p); }
    @Override public boolean zombieAtackReceived(Position p, int damage) {
        zombieAttacks.add(new Attack(p, damage));
        return true;
    }
    @Override public void zombieWasAdded() { zombiesAdded++; }
    @Override public void zombieReachedHouse() {}
    @Override public void zombieWasKilled() { zombiesKilled++; }
    @Override public void add(Zombies zombie) { addedObjects.add(zombie); }
    @Override public void explodePlant(Position center, int damage) { recordExplosion(center, damage, plantAttacks); }
    @Override public void explodeZombie(Position center, int damage) { recordExplosion(center, damage, zombieAttacks); }
    private void recordExplosion(Position center, int damage, List<Attack> target) {
        for (int row = center.getRow() - 1; row <= center.getRow() + 1; row++) {
            for (int col = center.getCol() - 1; col <= center.getCol() + 1; col++) {
                if (row >= 0 && row < NUM_ROWS && col >= 0 && col < NUM_COLS && (row != center.getRow() || col != center.getCol())) {
                    target.add(new Attack(new Position(row, col), damage));
                }
            }
        }
    }
    @Override public void pushAction(GameAction action) { pushedActions.add(action); }
}

